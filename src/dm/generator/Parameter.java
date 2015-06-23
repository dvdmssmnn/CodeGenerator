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

import java.io.StringWriter;
import java.util.ArrayList;
import java.util.List;

import dm.generator.description.IParseDescription;

public class Parameter {

	public static final String MAX_TYPE_LENGTH = "MAX_TYPE_LENGTH";
	public static final String MAX_VALUE_LENGTH = "MAX_VALUE_LENGTH";

	private Type type;
	private String name;
	private List<IParseDescription> descriptionParser = new ArrayList<IParseDescription>();
	private boolean outParam = false;

	public Type getType() {
		return type;
	}

	public void setType(Type type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void addDescriptionParser(IParseDescription descriptionParser) {
		if (descriptionParser == null) {
			return;
		}
		this.descriptionParser.add(descriptionParser);
	}

	public boolean isOutParam() {
		return outParam;
	}

	public void setOutParam(boolean outParam) {
		this.outParam = outParam;
	}

	public String generateCode(String dstVar) {
		StringWriter writer = new StringWriter();
		writer.append(String.format("//Parse parameter %s\n", getName()));
		writer.append(String.format("%s.description = NULL;\n", dstVar));
		writer.append(String.format("strncpy(%s.type, \"%s\", %s);\n", dstVar,
				getType().getTypeEncoding(), MAX_TYPE_LENGTH));
		// writer.append(String.format(
		// "snprintf(%s.value, %s, \"%s\", (%s)%s);\n", dstVar,
		// MAX_VALUE_LENGTH, getType().getFormatSpecifier(), getType()
		// .getTypeCast(), getName()));
		writer.append(getType().generateValueCode(
				String.format("%s.value", dstVar), getName()));
		//
		if (Enum.enums.get(getType().toString()) != null) {
			writer.append(Enum.enums.get(getType().toString()).generateSwitch(
					getName(), String.format("%s.description", dstVar)));
		} else {
			writer.append(type.generateDescriptionCode(dstVar, getName()));
		}

		for (IParseDescription p : this.descriptionParser) {
			writer.append(p.generateCode(String
					.format("%s.description", dstVar)));
		}
		return writer.toString();
	}

	public String getInfoVarName() {
		return String.format("%s_arg", getName());
	}

}
