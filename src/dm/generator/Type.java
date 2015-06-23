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
import java.util.HashSet;
import java.util.Set;

public class Type {
	private static final Set<String> cfTypes = new HashSet<String>() {
		private static final long serialVersionUID = 1L;

		{
			add("CFTypeRef");
			add("CFHTTPMessageRef");
			add("CFReadStreamRef");
			add("CFArrayRef");
			add("CFDictionaryRef");
			add("CFURLRef");
			add("CFStringRef");
			add("CFURLRef");
			add("CFDataRef");
		}
	};

	private String type;

	public Type(String type) {
		this.type = type.trim();
	}

	public String getTypeEncoding() {
		if (type.equals("unsigned char *") || type.equals("char *")) {
			return "*";
		} else if (type.equals("int")) {
			return "i";
		} else if (type.contains("*")) {
			return "^"
					+ type.replace("const", "").replace(" ", "")
							.replace("*", "");
		}
		return type;
	}

	public String getFormatSpecifier() {
		if (type.contains("*")) {
			return ("0x%X");
		}
		return "%d";
	}

	public String getTypeCast() {
		String formatSpecifier = getFormatSpecifier();
		if (formatSpecifier.equals("%d")) {
			return "int";
		} else if (formatSpecifier.equals("%s")) {
			return "char*";
		} else if (formatSpecifier.equals("0x%X")) {
			return "register_t";
		}
		if (getTypeEncoding().equals("*")) {
			return "char*";
		} else if (type.contains("*")) {
			return "register_t";
		}
		return "int";
	}

	@Override
	public String toString() {
		return type;
	}

	public String generateDescriptionCode(String descriptionVar, String varName) {

		if (type.equals("CFStringRef")) {
			return String
					.format("if(%s) {\n"
							+ "%s.description = (char*)calloc(CFStringGetLength(%s)+1, sizeof(char));\n"
							+ "CFStringGetCString(%s, %s.description, CFStringGetLength(%s) + 1, kCFStringEncodingUTF8);\n"
							+ "}\n", varName, descriptionVar, varName, varName,
							descriptionVar, varName);
		}
		if (cfTypes.contains(type)) {
			return String
					.format("if(%s) {\n"
							+ "CFStringRef %s_description = CFCopyDescription(%s);\n"
							+ "%s.description = (char*)calloc(CFStringGetLength(%s_description) + 1, sizeof(char));\n"
							+ "memcpy(%s.description, CFStringGetCStringPtr(%s_description, kCFStringEncodingUTF8), CFStringGetLength(%s_description));\n"
							+ "}\n", varName, varName, varName, descriptionVar,
							varName, descriptionVar, varName, varName);
		}
		return "";
	}

	public String generateValueCode(String valueVar, String varName) {
		StringWriter writer = new StringWriter();
		if (type.equals("CCCryptorRef") || cfTypes.contains(type)) {
			writer.append(String.format(
					"snprintf(%s, %s, \"0x%%X\", (int)%s);\n", valueVar,
					Parameter.MAX_VALUE_LENGTH, varName));
			return writer.toString();
		} else if (type.equals("CCCryptorRef *")) {
			writer.append(String.format(
					"snprintf(%s, %s, \"0x%%X\", (int)*%s);\n", valueVar,
					Parameter.MAX_VALUE_LENGTH, varName));
			return writer.toString();
		}
		writer.append(String.format("snprintf(%s, %s, \"%s\", (%s)%s);\n",
				valueVar, Parameter.MAX_VALUE_LENGTH, getFormatSpecifier(),
				getTypeCast(), varName));
		return writer.toString();
	}
}
