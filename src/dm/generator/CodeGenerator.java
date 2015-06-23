//Copyright (c) 2015, David Missmann
//All rights reserved.
//
//Redistribution and use in source and binary forms, with or without modification,
//are permitted provided that the following conditions are met:
//
//1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following
//disclaimer.
//
//2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following
//disclaimer in the documentation and/or other materials provided with the distribution.
//
//THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS" AND ANY EXPRESS OR IMPLIED WARRANTIES,
//INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE
//DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,
//SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR
//SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,
//WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE
//OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.

package dm.generator;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

import org.antlr.v4.runtime.ANTLRFileStream;
import org.antlr.v4.runtime.CommonTokenStream;
import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.DefaultParser;
import org.apache.commons.cli.Option;
import org.apache.commons.cli.OptionBuilder;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;

import dm.generator.antlr.SimpleHeaderLexer;
import dm.generator.antlr.SimpleHeaderParser;

public class CodeGenerator {

	public static void main(String[] args) throws IOException,
			InterruptedException {
		CommandLineParser commandLineParser = new DefaultParser();

		Options options = new Options();

		Option out = new Option("out", true, "Destination dir");
		options.addOption(OptionBuilder.withLongOpt("out").hasArg().create());
		options.addOption(OptionBuilder.withLongOpt("package").hasArg()
				.create());
		options.addOption(OptionBuilder.withLongOpt("in").hasArg().create());

		String inFilename = null;
		String outFilename = null;
		String packagename = null;

		try {
			CommandLine cmd = commandLineParser.parse(options, args);

			if (!cmd.hasOption("in") || !cmd.hasOption("package")
					|| !cmd.hasOption("in")) {
				System.err.println("Missing options");
				return;
			}

			inFilename = cmd.getOptionValue("in");
			outFilename = cmd.getOptionValue("out");
			packagename = cmd.getOptionValue("package");
		} catch (ParseException e) {
			e.printStackTrace();
		}

		ANTLRFileStream inFile = new ANTLRFileStream(inFilename);
		SimpleHeaderLexer lexer = new SimpleHeaderLexer(inFile);
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		SimpleHeaderParser parser = new SimpleHeaderParser(tokens);

		MySimpleHeaderListener listener = new MySimpleHeaderListener(
				packagename);
		parser.addParseListener(listener);

		parser.expression();

		FileWriter fileWriter = new FileWriter(new File(outFilename));
		fileWriter.write(listener.generateCode());

		fileWriter.flush();
		fileWriter.close();

		Process p = Runtime.getRuntime().exec(
				String.format("/usr/local/bin/astyle -n %s/%s",
						System.getProperty("user.dir"), outFilename));
		p.waitFor();
		BufferedReader b = new BufferedReader(new InputStreamReader(
				p.getInputStream()));
		String line = "";
		while ((line = b.readLine()) != null) {
			System.out.println(line);
		}
	}

}
