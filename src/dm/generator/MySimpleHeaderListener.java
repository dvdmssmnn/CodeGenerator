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

import org.antlr.v4.runtime.tree.ParseTree;

import dm.generator.antlr.SimpleHeaderBaseListener;
import dm.generator.antlr.SimpleHeaderParser.Cond_parse_varContext;
import dm.generator.antlr.SimpleHeaderParser.ConditionContext;
import dm.generator.antlr.SimpleHeaderParser.Enum_entryContext;
import dm.generator.antlr.SimpleHeaderParser.Enum_specifierContext;
import dm.generator.antlr.SimpleHeaderParser.Enum_valueContext;
import dm.generator.antlr.SimpleHeaderParser.Function_decContext;
import dm.generator.antlr.SimpleHeaderParser.IncludeContext;
import dm.generator.antlr.SimpleHeaderParser.ParamContext;
import dm.generator.antlr.SimpleHeaderParser.Parse_varContext;
import dm.generator.antlr.SimpleHeaderParser.TypedefContext;
import dm.generator.description.Base64Description;
import dm.generator.description.CStringDescription;
import dm.generator.description.ConditionalDescription;
import dm.generator.description.IParseDescription;
import dm.generator.description.NSDataDescription;
import dm.generator.description.UTF8Description;

public class MySimpleHeaderListener extends SimpleHeaderBaseListener {

	private Function currentFunction = null;
	private List<Function> functions = new ArrayList<Function>();
	private String clazz;
	private Enum currentEnum = null;
	private ConditionalDescription currentCondition = null;

	private List<String> includes = new ArrayList<String>();

	public MySimpleHeaderListener(String clazz) {
		this.clazz = clazz;
	}

	@Override
	public void enterEnum_specifier(Enum_specifierContext ctx) {
		currentEnum = new Enum();
	}

	@Override
	public void exitEnum_specifier(Enum_specifierContext ctx) {
		if (!ctx.children.get(1).getText().equals("{")) {
			currentEnum.setName(ctx.children.get(1).getText());
		}
		if (currentEnum.getName() != null) {
			Enum.enums.put(currentEnum.getName(), currentEnum);
			currentEnum.removeDuplicateValues();
		}
	}

	@Override
	public void enterFunction_dec(Function_decContext ctx) {
		currentFunction = new Function();
		currentFunction.setClazz(clazz);
	}

	@Override
	public void exitFunction_dec(Function_decContext ctx) {
		String returnType = "";
		for (int i = 0; i < ctx.children.get(0).getChildCount(); ++i) {
			ParseTree c = ctx.children.get(0).getChild(i);
			returnType += " " + c.getText();
		}
		currentFunction.setReturnType(new Type(returnType));
		currentFunction.setName(ctx.children.get(1).getText());
		currentFunction.setFindSymbolByName(ctx.children.get(5).getText()
				.equals("?"));

		this.functions.add(currentFunction);

		currentFunction = null;
	}

	@Override
	public void exitParam(ParamContext ctx) {
		Parameter p = new Parameter();

		String typeString = "";
		for (int i = 0; i < ctx.children.get(0).getChildCount(); ++i) {
			if (i != 0) {
				typeString += " ";
			}
			typeString += ctx.children.get(0).getChild(i).getText();
		}
		p.setType(new Type(typeString));
		p.setName(ctx.children.get(1).getText());
		currentFunction.addParameter(p);
	}

	@Override
	public void exitInclude(IncludeContext ctx) {
		includes.add(String.format("%s %s%s%s", ctx.children.get(0).getText(),
				ctx.children.get(1).getText(), ctx.children.get(2).getText(),
				ctx.children.get(3).getText()));

	}

	@Override
	public void enterEnum_entry(Enum_entryContext ctx) {
		currentEnum.addValue(new EnumValue());
	}

	@Override
	public void exitEnum_entry(Enum_entryContext ctx) {
		EnumValue a = null;
		if (currentEnum.getValues().size() > 1) {
			a = currentEnum.getValues().get(currentEnum.getValues().size() - 2);
		}
		EnumValue b = currentEnum.getValues().get(
				currentEnum.getValues().size() - 1);
		b.setName(ctx.start.getText());

		if (b.getValue() == null) {
			if (a == null) {
				b.setValue("0");
			} else {
				b.setValue(String.format("%d",
						Integer.valueOf(a.getValue()) + 1));
				;
			}
		}
	}

	@Override
	public void exitEnum_value(Enum_valueContext ctx) {
		String stringVal = ctx.stop.getText();
		if (stringVal.startsWith("0x")) {
			stringVal = stringVal.replace("0x", "");
			stringVal = String.valueOf(Integer.parseInt(stringVal, 16));
		}
		currentEnum.getValues().get(currentEnum.getValues().size() - 1)
				.setValue(stringVal);
	}

	@Override
	public void exitTypedef(TypedefContext ctx) {
		if (currentEnum != null) {
			currentEnum.setName(ctx.children.get(ctx.children.size() - 2)
					.getText());
			Enum.enums.put(currentEnum.getName(), currentEnum);
			currentEnum.removeDuplicateValues();
		}
	}

	public String generateCode() {
		StringWriter writer = new StringWriter();

		writer.append(getLicenseText());
		writer.append("\n");

		writer.append(String.format("#import \"HookHelper.h\"\n\n"));
		writer.append("#import \"SQLiteStorage.h\"\n");
		writer.append("#import \"ThreadStorage.h\"\n");
		writer.append("#import \"callLogManager.h\"\n");
		writer.append("#import <vector>\n");
		writer.append("#import <string>\n");
		writer.append("#import <pthread.h>\n");
		writer.append("#import <pthread.h>\n");
		writer.append("#import \"Config.h\"\n");
		writer.append("#import \"fishhook.h\"\n");
		writer.append("#import <semaphore.h>\n");
		writer.append("#import <dlfcn.h>\n");

		for (String include : includes) {
			writer.append(String.format("%s\n", include));
		}

		writer.append("\n");
		writer.append("using namespace std;\n");

		for (Function f : functions) {
			writer.append(String.format("%s;\n\n", f.getHookFunctionHeader()));
		}

		writer.append(generateConstructor());

		for (Function f : functions) {
			writer.append(String.format("%s\n", f.generateHookCode()));
		}

		return writer.toString();
	}

	public String generateConstructor() {
		StringWriter writer = new StringWriter();

		for (Function f : functions) {
			writer.append(String.format(
					"%s;\n",
					f.getFunctionPointerVariable(Function.ORIGINAL_PREFIX
							+ f.getName())));
		}

		writer.append("__attribute__((constructor))\n"
				+ "static void initialize() {\n");
		writer.append("    dispatch_async(dispatch_get_main_queue(), ^{\n");
		writer.append(String.format("struct rebinding rebinds[%d];\n",
				functions.size()));
		for (int i = 0; i < functions.size(); ++i) {
			Function f = functions.get(i);
			if (f.shouldFindSymbolByName()) {
				writer.append(String.format(
						"%s%s = (%s)dlsym(RTLD_DEFAULT, \"%s\");\n",
						Function.ORIGINAL_PREFIX, f.getName(),
						f.getFunctionPointerTypeCast(), f.getName()));
			} else {
				writer.append(String.format("%s%s = (%s)%s;\n",
						Function.ORIGINAL_PREFIX, f.getName(),
						f.getFunctionPointerTypeCast(), f.getName()));
			}
			writer.append(String.format("rebinds[%d].name = (char*) \"%s\";\n",
					i, f.getName()));
			writer.append(String.format(
					"rebinds[%d].replacement = (void*) %s;\n",
					i,
					String.format("%s%s", Function.HOOK_FUNCTION_PREFIX,
							f.getName())));
		}
		writer.append(String.format("rebind_symbols(rebinds, %d);\n",
				functions.size()));

		writer.append("});\n");
		writer.append("}\n\n");

		return writer.toString();
	}

	@Override
	public void exitParse_var(Parse_varContext ctx) {
		boolean outParam = ctx.getParent().getStart().getText().equals("out");
		String type = ctx.getStart().getText();
		String paramName = null;
		IParseDescription parser = null;
		if (type.equals("base64")) {
			paramName = ctx.children.get(2).getText();
			String lenName = ctx.children.get(4).getText();
			parser = new Base64Description(paramName, lenName);
		} else if (type.equals("data")) {
			paramName = ctx.children.get(2).getText();
			String lenName = ctx.children.get(4).getText();
			if (lenName.equals("*")) {
				lenName = String.format("(%s ? (*%s) : 0)", ctx.children.get(5)
						.getText(), ctx.children.get(5).getText());
			}
			parser = new NSDataDescription(paramName, lenName);
		} else if (type.equals("utf8")) {
			paramName = ctx.children.get(2).getText();
			String lenName = ctx.children.get(4).getText();
			parser = new UTF8Description(paramName, lenName);
		} else if (type.equals("std")) {
			paramName = ctx.children.get(2).getText();
		} else if (type.equals("cstring")) {
			paramName = ctx.children.get(2).getText();
			parser = new CStringDescription(ctx.children.get(2).getText());
		}

		currentFunction.getParameter(paramName).setOutParam(outParam);
		if (currentCondition == null) {
			currentFunction.getParameter(paramName)
					.addDescriptionParser(parser);
		} else {
			currentCondition.setChild(parser);
			currentFunction.getParameter(paramName).addDescriptionParser(
					currentCondition);
		}
	}

	@Override
	public void enterCond_parse_var(Cond_parse_varContext ctx) {
		currentCondition = new ConditionalDescription();
	}

	@Override
	public void exitCond_parse_var(Cond_parse_varContext ctx) {
		currentCondition = null;
	}

	@Override
	public void exitCondition(ConditionContext ctx) {
		currentCondition.setCondition("if" + ctx.getText());
	}

	private String getLicenseText() {
		return "//Copyright (c) 2015, David Missmann\n"
				+ "//All rights reserved.\n"
				+ "//\n"
				+ "//Redistribution and use in source and binary forms, with or without modification,\n"
				+ "//are permitted provided that the following conditions are met:\n"
				+ "//\n"
				+ "//1. Redistributions of source code must retain the above copyright notice, this list of conditions and the following\n"
				+ "//disclaimer.\n"
				+ "//\n"
				+ "//2. Redistributions in binary form must reproduce the above copyright notice, this list of conditions and the following\n"
				+ "//disclaimer in the documentation and/or other materials provided with the distribution.\n"
				+ "//\n"
				+ "//THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS \"AS IS\" AND ANY EXPRESS OR IMPLIED WARRANTIES,\n"
				+ "//INCLUDING, BUT NOT LIMITED TO, THE IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE ARE\n"
				+ "//DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDER OR CONTRIBUTORS BE LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL,\n"
				+ "//SPECIAL, EXEMPLARY, OR CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF SUBSTITUTE GOODS OR\n"
				+ "//SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY,\n"
				+ "//WHETHER IN CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE) ARISING IN ANY WAY OUT OF THE USE\n"
				+ "//OF THIS SOFTWARE, EVEN IF ADVISED OF THE POSSIBILITY OF SUCH DAMAGE.\n";
	}
}
