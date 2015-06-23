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

package dm.generator.description;

import java.io.StringWriter;

public class NSDataDescription implements IParseDescription {

	private String dataVar;
	private String lengthVar;

	public NSDataDescription(String dataVar, String lengthVar) {
		this.dataVar = dataVar;
		this.lengthVar = lengthVar;
	}

	@Override
	public String generateCode(String dstVar) {
		StringWriter writer = new StringWriter();
		writer.append(String.format(
				"%s = (char*)calloc(2*%s+1, sizeof(char));\n", dstVar,
				lengthVar));
		writer.append(String.format(
				"NSData *%s_data = [NSData dataWithBytes:%s length:%s];\n",
				dataVar, dataVar, lengthVar));
		writer.append(String
				.format("strncpy(%s, [[[[[%s_data description] stringByReplacingOccurrencesOfString:@\"<\" withString:@\"\"] "
						+ "stringByReplacingOccurrencesOfString:@\" \" withString:@\"\"] "
						+ "stringByReplacingOccurrencesOfString:@\">\" withString:@\"\"] UTF8String], 2*%s);\n",
						dstVar, dataVar, lengthVar));
		return writer.toString();
	}

}
