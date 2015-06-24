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

public class Function {
	public static final String HOOK_FUNCTION_PREFIX = "____";
	public static final String ORIGINAL_PREFIX = "original_";

	private String name;
	private Type returnType;
	private String clazz;
	private List<Parameter> parameter = new ArrayList<Parameter>();
	private boolean findSymbolByName = false;

	public void addParameter(Parameter parameter) {
		this.parameter.add(parameter);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Type getReturnType() {
		return returnType;
	}

	public void setReturnType(Type returnType) {
		this.returnType = returnType;
	}

	public String getClazz() {
		return clazz;
	}

	public void setClazz(String clazz) {
		this.clazz = clazz;
	}

	public List<Parameter> getParameter() {
		return this.parameter;
	}

	public Parameter getParameter(String name) {
		for (Parameter p : parameter) {
			if (p.getName().equals(name)) {
				return p;
			}
		}
		return null;
	}

	public String generateHookCode() {
		StringWriter writer = new StringWriter();

		boolean hasReturnValue = false;
		if (!returnType.toString().equals("void")) {
			hasReturnValue = true;
		}

		StringWriter callOriginalCode = new StringWriter();
		if (hasReturnValue) {
			callOriginalCode.append(String.format("    %s return_value = ",
					returnType.toString()));
		}
		callOriginalCode.append(String.format("%s%s(", ORIGINAL_PREFIX, name));
		for (int i = 0; i < parameter.size(); ++i) {
			if (i != 0) {
				callOriginalCode.append(", ");
			}
			callOriginalCode.append(parameter.get(i).getName());
		}
		callOriginalCode.append(");\n");

		writer.append(getHookFunctionHeader());
		writer.append("\n");

		writer.append("{\n");

		writer.append("if (!is_enabled() || !enabled_) { \n");
		writer.append(callOriginalCode.toString());
		if (hasReturnValue) {
			writer.append("return return_value;\n");
		} else {
			writer.append("return;\n");
		}
		writer.append("}\n");

		writer.append("    thread_storage_t *thread_infos = get_thread_infos();\n"
				+ "set_enabled(false);\n"
				+ "    call_id_t call_id;\n"
				+ "    pthread_mutex_lock(&counter_mutex);\n"
				+ "    ++counter;\n"
				+ "    call_id = counter;\n"
				+ "    pthread_mutex_unlock(&counter_mutex);\n"
				+

				"    call_id_t caller_id = 0;\n"
				+ "    if (thread_infos->call_id_stack->size()) {\n"
				+ "        caller_id = thread_infos->call_id_stack->back();\n"
				+ "    }\n"
				+ "    thread_infos->call_id_stack->push_back(call_id);\n");

		// writer.append(String.format("printf(\"%s\\n\");", getName()));
		String parametersVar = "parameters";
		writer.append(String.format("parameter_t *%s = NULL;\n", parametersVar));
		if (parameter.size() > 0) {
			writer.append(String.format(
					"%s = (parameter_t*)calloc(%d, sizeof(parameter_t));\n",
					parametersVar, parameter.size()));
		}

		List<String> infoVarsName = new ArrayList<String>();
		boolean hasOutVariables = false;
		for (int i = 0; i < this.parameter.size(); ++i) {
			Parameter p = this.parameter.get(i);
			if (p.isOutParam()) {
				hasOutVariables = true;
			} else {
				writer.append(p.generateCode(String.format("%s[%d]",
						parametersVar, i)));
			}
		}

		if (!hasOutVariables) {
			for (String s : infoVarsName) {
				writer.append(String.format("%s.push_back(%s);\n", "args", s));
			}
		}

		StringWriter freeParametersCode = new StringWriter();
		freeParametersCode.append(String.format(
				"for(unsigned int i = 0; i < %d; ++i) {\n", parameter.size()));
		freeParametersCode.append(String.format(
				"if (%s[i].description != NULL) {\n"
						+ "free(%s[i].description);\n" + "}\n", parametersVar,
				parametersVar));
		freeParametersCode.append("}\n");
		freeParametersCode.append(String.format("free(%s);\n", parametersVar));

		StringWriter storeParamsDBCall = new StringWriter();
		storeParamsDBCall.append("if (enabled_) {\n");
		storeParamsDBCall.append("    dispatch_async(db_queue, ^{\n");
		storeParamsDBCall.append("set_enabled(false);\n");
		storeParamsDBCall
				.append(String
						.format("        insert_call(call_id, caller_id, \"%s\", \"%s\", \"0x0\", %s, %d);\n",
								clazz, name,
								this.parameter.size() > 0 ? parametersVar
										: "NULL", this.parameter.size()));
		storeParamsDBCall.append(freeParametersCode.toString());
		storeParamsDBCall.append("set_enabled(true);\n");
		storeParamsDBCall.append("    });\n");
		storeParamsDBCall.append("} else {\n");
		storeParamsDBCall.append(freeParametersCode.toString());
		storeParamsDBCall.append("}");

		if (!hasOutVariables) {
			writer.append(storeParamsDBCall.toString());
		}

		writer.append("set_enabled(true);\n");
		writer.append(callOriginalCode.toString());

		writer.append("set_enabled(false);\n");

		if (hasOutVariables) {
			for (int i = 0; i < this.parameter.size(); ++i) {
				Parameter p = this.parameter.get(i);
				if (p.isOutParam()) {
					writer.append(p.generateCode(String.format("%s[%d]",
							parametersVar, i)));
				}
			}

			for (String s : infoVarsName) {
				writer.append(String.format("%s.push_back(%s);\n", "args", s));
			}
			writer.append(storeParamsDBCall.toString());
		}

		if (hasReturnValue) {
			String returnDescriptionName = "return_param";
			writer.append(String.format("parameter_t %s;\n",
					returnDescriptionName));
			writer.append(String.format("%s.description = NULL;\n",
					returnDescriptionName));

			writer.append(String.format("strncpy(%s.type, \"%s\", %s);\n",
					returnDescriptionName, getReturnType().toString(),
					Parameter.MAX_TYPE_LENGTH));

			if (Enum.enums.get(returnType.toString()) != null) {
				writer.append(Enum.enums.get(returnType.toString())
						.generateSwitch("return_value",
								returnDescriptionName + ".description"));
			} else {
				writer.append(returnType.generateDescriptionCode(
						returnDescriptionName, "return_value"));
			}

			// writer.append(String.format(
			// "snprintf(%s.value, %s, \"%s\", (%s)%s);\n",
			// returnDescriptionName, Parameter.MAX_VALUE_LENGTH,
			// getReturnType().getFormatSpecifier(),
			// returnType.getTypeCast(), "return_value"));
			writer.append(returnType.generateValueCode(returnDescriptionName
					+ ".value", "return_value"));

			writer.append("if (enabled_) {\n");
			writer.append("    dispatch_async(db_queue, ^{\n");
			writer.append("set_enabled(false);\n");
			writer.append(String.format(
					"        insert_return(call_id, &%s);\n",
					returnDescriptionName));
			writer.append("set_enabled(true);\n");
			writer.append("    });\n");
			writer.append("}\n");
		}

		writer.append("    thread_infos->call_id_stack->pop_back();\n");
		writer.append("set_enabled(true);");

		if (hasReturnValue) {
			writer.append("    return return_value;");
		}

		writer.append("}\n\n");

		return writer.toString();
	}

	public String getHookFunctionHeader() {
		StringWriter writer = new StringWriter();

		writer.append(String.format("%s %s%s(", returnType,
				HOOK_FUNCTION_PREFIX, name));

		for (int i = 0; i < parameter.size(); ++i) {
			if (i != 0) {
				writer.append(", ");
			}
			Parameter p = parameter.get(i);
			writer.append(String.format("%s %s", p.getType(), p.getName()));
		}

		writer.append(")");

		return writer.toString();
	}

	public boolean shouldFindSymbolByName() {
		return findSymbolByName;
	}

	public void setFindSymbolByName(boolean findSymbolByName) {
		this.findSymbolByName = findSymbolByName;
	}

	public String getFunctionPointerVariable(String dstVar) {
		StringWriter writer = new StringWriter();

		writer.append(String.format("%s(*%s)(", getReturnType().toString(),
				dstVar));

		List<Parameter> parameter = getParameter();
		for (int i = 0; i < parameter.size(); ++i) {
			if (i != 0) {
				writer.append(", ");
			}
			writer.append(parameter.get(i).getType().toString());
		}

		writer.append(")");

		return writer.toString();
	}

	public String getFunctionPointerTypeCast() {
		return getFunctionPointerVariable("");
	}
}
