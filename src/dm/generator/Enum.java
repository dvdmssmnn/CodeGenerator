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
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Enum {
	private String name;
	private List<EnumValue> values = new ArrayList<EnumValue>();

	public static Map<String, Enum> enums = new HashMap<String, Enum>();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<EnumValue> getValues() {
		return values;
	}

	public void setValues(List<EnumValue> values) {
		this.values = values;
	}

	public void addValue(EnumValue value) {
		for (EnumValue v : values) {
			if (v.getValue().equals(value.getValue())) {
				return;
			}
		}
		values.add(value);
	}

	@Override
	public String toString() {
		StringWriter writer = new StringWriter();
		writer.append(name);
		writer.append("\n");
		for (EnumValue v : values) {
			writer.append(String.format("\t%s = %s\n", v.getName(),
					v.getValue()));
		}
		return writer.toString();
	}

	public String generateSwitch(String variableName, String dstVar) {
		StringWriter writer = new StringWriter();

		writer.append(String.format("switch (%s) {\n", variableName));

		for (EnumValue value : values) {
			writer.append(String.format("case %s:\n", value.getValue()));
			writer.append(String.format(
					"%s = (char*)calloc(%d, sizeof(char));\n", dstVar, value
							.getName().length() + 1));
			writer.append(String.format("strncpy(%s, \"%s\", %d);\n", dstVar,
					value.getName(), value.getName().length()));
			writer.append("break;\n");
		}

		writer.append("}");

		return writer.toString();
	}

	public void removeDuplicateValues() {
		for (int i = 0; i < values.size(); ++i) {
			for (int j = 0; j < values.size(); ++j) {
				if (i == j) {
					continue;
				}

				if (values.get(i).getValue().equals(values.get(j).getValue())) {
					values.remove(j);
					j--;
				}
			}
		}
	}

}
