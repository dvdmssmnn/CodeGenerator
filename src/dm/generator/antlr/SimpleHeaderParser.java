// Generated from SimpleHeader.g4 by ANTLR 4.5

package dm.generator.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class SimpleHeaderParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, LBRACE=12, RBRACE=13, LPAREN=14, RPAREN=15, LBRACKET=16, 
		RBRACKET=17, SEMICOLON=18, COMMA=19, STAR=20, CONST=21, UNSIGNED=22, NUMBER=23, 
		IDENTIFIER=24, LOGICALS=25, SINGLE_COMMENT=26, WS=27;
	public static final int
		RULE_expression = 0, RULE_type = 1, RULE_enum_specifier = 2, RULE_enum_entry = 3, 
		RULE_enum_value = 4, RULE_include = 5, RULE_typedef = 6, RULE_function_dec = 7, 
		RULE_param_list = 8, RULE_param = 9, RULE_parse = 10, RULE_parse_var = 11, 
		RULE_parse_var_out = 12, RULE_cond_parse_var = 13, RULE_condition = 14;
	public static final String[] ruleNames = {
		"expression", "type", "enum_specifier", "enum_entry", "enum_value", "include", 
		"typedef", "function_dec", "param_list", "param", "parse", "parse_var", 
		"parse_var_out", "cond_parse_var", "condition"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'enum'", "'='", "'#include'", "'#import'", "'<'", "'\"'", "'>'", 
		"'typedef'", "'?'", "'out'", "'if'", "'{'", "'}'", "'('", "')'", "'['", 
		"']'", "';'", "','", "'*'", "'const'", "'unsigned'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		"LBRACE", "RBRACE", "LPAREN", "RPAREN", "LBRACKET", "RBRACKET", "SEMICOLON", 
		"COMMA", "STAR", "CONST", "UNSIGNED", "NUMBER", "IDENTIFIER", "LOGICALS", 
		"SINGLE_COMMENT", "WS"
	};
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "SimpleHeader.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public SimpleHeaderParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class ExpressionContext extends ParserRuleContext {
		public ExpressionContext expression() {
			return getRuleContext(ExpressionContext.class,0);
		}
		public IncludeContext include() {
			return getRuleContext(IncludeContext.class,0);
		}
		public Enum_specifierContext enum_specifier() {
			return getRuleContext(Enum_specifierContext.class,0);
		}
		public TypedefContext typedef() {
			return getRuleContext(TypedefContext.class,0);
		}
		public Function_decContext function_dec() {
			return getRuleContext(Function_decContext.class,0);
		}
		public TerminalNode EOF() { return getToken(SimpleHeaderParser.EOF, 0); }
		public ExpressionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expression; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).enterExpression(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).exitExpression(this);
		}
	}

	public final ExpressionContext expression() throws RecognitionException {
		ExpressionContext _localctx = new ExpressionContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_expression);
		try {
			setState(39);
			switch (_input.LA(1)) {
			case T__0:
			case T__2:
			case T__3:
			case T__7:
			case CONST:
			case UNSIGNED:
			case IDENTIFIER:
				enterOuterAlt(_localctx, 1);
				{
				setState(34);
				switch (_input.LA(1)) {
				case T__2:
				case T__3:
					{
					setState(30);
					include();
					}
					break;
				case T__0:
					{
					setState(31);
					enum_specifier();
					}
					break;
				case T__7:
					{
					setState(32);
					typedef();
					}
					break;
				case CONST:
				case UNSIGNED:
				case IDENTIFIER:
					{
					setState(33);
					function_dec();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(36);
				expression();
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(38);
				match(EOF);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypeContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SimpleHeaderParser.IDENTIFIER, 0); }
		public TerminalNode STAR() { return getToken(SimpleHeaderParser.STAR, 0); }
		public List<TerminalNode> UNSIGNED() { return getTokens(SimpleHeaderParser.UNSIGNED); }
		public TerminalNode UNSIGNED(int i) {
			return getToken(SimpleHeaderParser.UNSIGNED, i);
		}
		public TerminalNode CONST() { return getToken(SimpleHeaderParser.CONST, 0); }
		public TypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_type; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).enterType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).exitType(this);
		}
	}

	public final TypeContext type() throws RecognitionException {
		TypeContext _localctx = new TypeContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_type);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(58);
			switch ( getInterpreter().adaptivePredict(_input,7,_ctx) ) {
			case 1:
				{
				setState(42);
				switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
				case 1:
					{
					setState(41);
					match(UNSIGNED);
					}
					break;
				}
				setState(45);
				_la = _input.LA(1);
				if (_la==CONST) {
					{
					setState(44);
					match(CONST);
					}
				}

				setState(48);
				_la = _input.LA(1);
				if (_la==UNSIGNED) {
					{
					setState(47);
					match(UNSIGNED);
					}
				}

				setState(50);
				match(IDENTIFIER);
				}
				break;
			case 2:
				{
				setState(52);
				_la = _input.LA(1);
				if (_la==UNSIGNED) {
					{
					setState(51);
					match(UNSIGNED);
					}
				}

				setState(54);
				match(IDENTIFIER);
				setState(56);
				_la = _input.LA(1);
				if (_la==CONST) {
					{
					setState(55);
					match(CONST);
					}
				}

				}
				break;
			}
			setState(61);
			_la = _input.LA(1);
			if (_la==STAR) {
				{
				setState(60);
				match(STAR);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Enum_specifierContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(SimpleHeaderParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(SimpleHeaderParser.RBRACE, 0); }
		public TerminalNode SEMICOLON() { return getToken(SimpleHeaderParser.SEMICOLON, 0); }
		public TerminalNode IDENTIFIER() { return getToken(SimpleHeaderParser.IDENTIFIER, 0); }
		public List<Enum_entryContext> enum_entry() {
			return getRuleContexts(Enum_entryContext.class);
		}
		public Enum_entryContext enum_entry(int i) {
			return getRuleContext(Enum_entryContext.class,i);
		}
		public Enum_specifierContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum_specifier; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).enterEnum_specifier(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).exitEnum_specifier(this);
		}
	}

	public final Enum_specifierContext enum_specifier() throws RecognitionException {
		Enum_specifierContext _localctx = new Enum_specifierContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_enum_specifier);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(T__0);
			setState(65);
			_la = _input.LA(1);
			if (_la==IDENTIFIER) {
				{
				setState(64);
				match(IDENTIFIER);
				}
			}

			setState(67);
			match(LBRACE);
			setState(69); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(68);
				enum_entry();
				}
				}
				setState(71); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==IDENTIFIER );
			setState(73);
			match(RBRACE);
			setState(74);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Enum_entryContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SimpleHeaderParser.IDENTIFIER, 0); }
		public Enum_valueContext enum_value() {
			return getRuleContext(Enum_valueContext.class,0);
		}
		public TerminalNode COMMA() { return getToken(SimpleHeaderParser.COMMA, 0); }
		public Enum_entryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum_entry; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).enterEnum_entry(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).exitEnum_entry(this);
		}
	}

	public final Enum_entryContext enum_entry() throws RecognitionException {
		Enum_entryContext _localctx = new Enum_entryContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_enum_entry);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(IDENTIFIER);
			setState(78);
			_la = _input.LA(1);
			if (_la==T__1) {
				{
				setState(77);
				enum_value();
				}
			}

			setState(81);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(80);
				match(COMMA);
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Enum_valueContext extends ParserRuleContext {
		public TerminalNode NUMBER() { return getToken(SimpleHeaderParser.NUMBER, 0); }
		public Enum_valueContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_enum_value; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).enterEnum_value(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).exitEnum_value(this);
		}
	}

	public final Enum_valueContext enum_value() throws RecognitionException {
		Enum_valueContext _localctx = new Enum_valueContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_enum_value);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(83);
			match(T__1);
			setState(84);
			match(NUMBER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class IncludeContext extends ParserRuleContext {
		public TerminalNode IDENTIFIER() { return getToken(SimpleHeaderParser.IDENTIFIER, 0); }
		public IncludeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_include; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).enterInclude(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).exitInclude(this);
		}
	}

	public final IncludeContext include() throws RecognitionException {
		IncludeContext _localctx = new IncludeContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_include);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86);
			_la = _input.LA(1);
			if ( !(_la==T__2 || _la==T__3) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(87);
			_la = _input.LA(1);
			if ( !(_la==T__4 || _la==T__5) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			setState(88);
			match(IDENTIFIER);
			setState(89);
			_la = _input.LA(1);
			if ( !(_la==T__5 || _la==T__6) ) {
			_errHandler.recoverInline(this);
			} else {
				consume();
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TypedefContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(SimpleHeaderParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(SimpleHeaderParser.IDENTIFIER, i);
		}
		public TerminalNode SEMICOLON() { return getToken(SimpleHeaderParser.SEMICOLON, 0); }
		public TypedefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typedef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).enterTypedef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).exitTypedef(this);
		}
	}

	public final TypedefContext typedef() throws RecognitionException {
		TypedefContext _localctx = new TypedefContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_typedef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91);
			match(T__7);
			setState(92);
			match(IDENTIFIER);
			setState(93);
			match(IDENTIFIER);
			setState(94);
			match(SEMICOLON);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Function_decContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(SimpleHeaderParser.IDENTIFIER, 0); }
		public TerminalNode LPAREN() { return getToken(SimpleHeaderParser.LPAREN, 0); }
		public Param_listContext param_list() {
			return getRuleContext(Param_listContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SimpleHeaderParser.RPAREN, 0); }
		public ParseContext parse() {
			return getRuleContext(ParseContext.class,0);
		}
		public TerminalNode SEMICOLON() { return getToken(SimpleHeaderParser.SEMICOLON, 0); }
		public Function_decContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_function_dec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).enterFunction_dec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).exitFunction_dec(this);
		}
	}

	public final Function_decContext function_dec() throws RecognitionException {
		Function_decContext _localctx = new Function_decContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_function_dec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			type();
			setState(97);
			match(IDENTIFIER);
			setState(98);
			match(LPAREN);
			setState(99);
			param_list();
			setState(100);
			match(RPAREN);
			setState(102);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(101);
				match(T__8);
				}
			}

			setState(106);
			switch (_input.LA(1)) {
			case LBRACE:
				{
				setState(104);
				parse();
				}
				break;
			case SEMICOLON:
				{
				setState(105);
				match(SEMICOLON);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Param_listContext extends ParserRuleContext {
		public List<ParamContext> param() {
			return getRuleContexts(ParamContext.class);
		}
		public ParamContext param(int i) {
			return getRuleContext(ParamContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(SimpleHeaderParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(SimpleHeaderParser.COMMA, i);
		}
		public Param_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).enterParam_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).exitParam_list(this);
		}
	}

	public final Param_listContext param_list() throws RecognitionException {
		Param_listContext _localctx = new Param_listContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_param_list);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(116);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << CONST) | (1L << UNSIGNED) | (1L << IDENTIFIER))) != 0)) {
				{
				setState(108);
				param();
				setState(113);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==COMMA) {
					{
					{
					setState(109);
					match(COMMA);
					setState(110);
					param();
					}
					}
					setState(115);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
			}

			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParamContext extends ParserRuleContext {
		public TypeContext type() {
			return getRuleContext(TypeContext.class,0);
		}
		public TerminalNode IDENTIFIER() { return getToken(SimpleHeaderParser.IDENTIFIER, 0); }
		public ParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_param; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).enterParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).exitParam(this);
		}
	}

	public final ParamContext param() throws RecognitionException {
		ParamContext _localctx = new ParamContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_param);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(118);
			type();
			setState(119);
			match(IDENTIFIER);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ParseContext extends ParserRuleContext {
		public TerminalNode LBRACE() { return getToken(SimpleHeaderParser.LBRACE, 0); }
		public TerminalNode RBRACE() { return getToken(SimpleHeaderParser.RBRACE, 0); }
		public List<TerminalNode> SEMICOLON() { return getTokens(SimpleHeaderParser.SEMICOLON); }
		public TerminalNode SEMICOLON(int i) {
			return getToken(SimpleHeaderParser.SEMICOLON, i);
		}
		public List<Parse_varContext> parse_var() {
			return getRuleContexts(Parse_varContext.class);
		}
		public Parse_varContext parse_var(int i) {
			return getRuleContext(Parse_varContext.class,i);
		}
		public List<Parse_var_outContext> parse_var_out() {
			return getRuleContexts(Parse_var_outContext.class);
		}
		public Parse_var_outContext parse_var_out(int i) {
			return getRuleContext(Parse_var_outContext.class,i);
		}
		public List<Cond_parse_varContext> cond_parse_var() {
			return getRuleContexts(Cond_parse_varContext.class);
		}
		public Cond_parse_varContext cond_parse_var(int i) {
			return getRuleContext(Cond_parse_varContext.class,i);
		}
		public ParseContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).enterParse(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).exitParse(this);
		}
	}

	public final ParseContext parse() throws RecognitionException {
		ParseContext _localctx = new ParseContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_parse);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(LBRACE);
			setState(129); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(125);
				switch (_input.LA(1)) {
				case IDENTIFIER:
					{
					setState(122);
					parse_var();
					}
					break;
				case T__9:
					{
					setState(123);
					parse_var_out();
					}
					break;
				case T__10:
					{
					setState(124);
					cond_parse_var();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(127);
				match(SEMICOLON);
				}
				}
				setState(131); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__9) | (1L << T__10) | (1L << IDENTIFIER))) != 0) );
			setState(133);
			match(RBRACE);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parse_varContext extends ParserRuleContext {
		public List<TerminalNode> IDENTIFIER() { return getTokens(SimpleHeaderParser.IDENTIFIER); }
		public TerminalNode IDENTIFIER(int i) {
			return getToken(SimpleHeaderParser.IDENTIFIER, i);
		}
		public TerminalNode LPAREN() { return getToken(SimpleHeaderParser.LPAREN, 0); }
		public TerminalNode RPAREN() { return getToken(SimpleHeaderParser.RPAREN, 0); }
		public TerminalNode COMMA() { return getToken(SimpleHeaderParser.COMMA, 0); }
		public TerminalNode NUMBER() { return getToken(SimpleHeaderParser.NUMBER, 0); }
		public Parse_varContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).enterParse_var(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).exitParse_var(this);
		}
	}

	public final Parse_varContext parse_var() throws RecognitionException {
		Parse_varContext _localctx = new Parse_varContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_parse_var);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(135);
			match(IDENTIFIER);
			setState(136);
			match(LPAREN);
			setState(137);
			match(IDENTIFIER);
			setState(146);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(138);
				match(COMMA);
				setState(144);
				switch (_input.LA(1)) {
				case STAR:
				case IDENTIFIER:
					{
					{
					setState(140);
					_la = _input.LA(1);
					if (_la==STAR) {
						{
						setState(139);
						match(STAR);
						}
					}

					setState(142);
					match(IDENTIFIER);
					}
					}
					break;
				case NUMBER:
					{
					setState(143);
					match(NUMBER);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
			}

			setState(148);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Parse_var_outContext extends ParserRuleContext {
		public TerminalNode LPAREN() { return getToken(SimpleHeaderParser.LPAREN, 0); }
		public Parse_varContext parse_var() {
			return getRuleContext(Parse_varContext.class,0);
		}
		public TerminalNode RPAREN() { return getToken(SimpleHeaderParser.RPAREN, 0); }
		public Parse_var_outContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_parse_var_out; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).enterParse_var_out(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).exitParse_var_out(this);
		}
	}

	public final Parse_var_outContext parse_var_out() throws RecognitionException {
		Parse_var_outContext _localctx = new Parse_var_outContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_parse_var_out);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(150);
			match(T__9);
			setState(151);
			match(LPAREN);
			setState(152);
			parse_var();
			setState(153);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Cond_parse_varContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public Parse_varContext parse_var() {
			return getRuleContext(Parse_varContext.class,0);
		}
		public Parse_var_outContext parse_var_out() {
			return getRuleContext(Parse_var_outContext.class,0);
		}
		public Cond_parse_varContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_cond_parse_var; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).enterCond_parse_var(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).exitCond_parse_var(this);
		}
	}

	public final Cond_parse_varContext cond_parse_var() throws RecognitionException {
		Cond_parse_varContext _localctx = new Cond_parse_varContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_cond_parse_var);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(155);
			match(T__10);
			setState(156);
			condition();
			setState(159);
			switch (_input.LA(1)) {
			case IDENTIFIER:
				{
				setState(157);
				parse_var();
				}
				break;
			case T__9:
				{
				setState(158);
				parse_var_out();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ConditionContext extends ParserRuleContext {
		public List<TerminalNode> LPAREN() { return getTokens(SimpleHeaderParser.LPAREN); }
		public TerminalNode LPAREN(int i) {
			return getToken(SimpleHeaderParser.LPAREN, i);
		}
		public List<TerminalNode> RPAREN() { return getTokens(SimpleHeaderParser.RPAREN); }
		public TerminalNode RPAREN(int i) {
			return getToken(SimpleHeaderParser.RPAREN, i);
		}
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<TerminalNode> LOGICALS() { return getTokens(SimpleHeaderParser.LOGICALS); }
		public TerminalNode LOGICALS(int i) {
			return getToken(SimpleHeaderParser.LOGICALS, i);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof SimpleHeaderListener ) ((SimpleHeaderListener)listener).exitCondition(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_condition);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(161);
			match(LPAREN);
			setState(170);
			switch (_input.LA(1)) {
			case LPAREN:
				{
				setState(162);
				condition();
				}
				break;
			case T__0:
			case T__1:
			case T__2:
			case T__3:
			case T__4:
			case T__5:
			case T__6:
			case T__7:
			case T__8:
			case T__9:
			case T__10:
			case LBRACE:
			case RBRACE:
			case RPAREN:
			case LBRACKET:
			case RBRACKET:
			case SEMICOLON:
			case COMMA:
			case STAR:
			case CONST:
			case UNSIGNED:
			case NUMBER:
			case IDENTIFIER:
			case LOGICALS:
			case SINGLE_COMMENT:
			case WS:
				{
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5) | (1L << T__6) | (1L << T__7) | (1L << T__8) | (1L << T__9) | (1L << T__10) | (1L << LBRACE) | (1L << RBRACE) | (1L << LBRACKET) | (1L << RBRACKET) | (1L << SEMICOLON) | (1L << COMMA) | (1L << STAR) | (1L << CONST) | (1L << UNSIGNED) | (1L << NUMBER) | (1L << IDENTIFIER) | (1L << LOGICALS) | (1L << SINGLE_COMMENT) | (1L << WS))) != 0)) {
					{
					setState(165);
					switch ( getInterpreter().adaptivePredict(_input,23,_ctx) ) {
					case 1:
						{
						setState(163);
						_la = _input.LA(1);
						if ( _la <= 0 || (_la==LPAREN || _la==RPAREN) ) {
						_errHandler.recoverInline(this);
						} else {
							consume();
						}
						}
						break;
					case 2:
						{
						setState(164);
						match(LOGICALS);
						}
						break;
					}
					}
					setState(169);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(172);
			match(RPAREN);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\35\u00b1\4\2\t\2"+
		"\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\3\2\3\2\3\2\3\2\5"+
		"\2%\n\2\3\2\3\2\3\2\5\2*\n\2\3\3\5\3-\n\3\3\3\5\3\60\n\3\3\3\5\3\63\n"+
		"\3\3\3\3\3\5\3\67\n\3\3\3\3\3\5\3;\n\3\5\3=\n\3\3\3\5\3@\n\3\3\4\3\4\5"+
		"\4D\n\4\3\4\3\4\6\4H\n\4\r\4\16\4I\3\4\3\4\3\4\3\5\3\5\5\5Q\n\5\3\5\5"+
		"\5T\n\5\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\5\ti\n\t\3\t\3\t\5\tm\n\t\3\n\3\n\3\n\7\nr\n\n\f\n\16\n"+
		"u\13\n\5\nw\n\n\3\13\3\13\3\13\3\f\3\f\3\f\3\f\5\f\u0080\n\f\3\f\3\f\6"+
		"\f\u0084\n\f\r\f\16\f\u0085\3\f\3\f\3\r\3\r\3\r\3\r\3\r\5\r\u008f\n\r"+
		"\3\r\3\r\5\r\u0093\n\r\5\r\u0095\n\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16"+
		"\3\17\3\17\3\17\3\17\5\17\u00a2\n\17\3\20\3\20\3\20\3\20\7\20\u00a8\n"+
		"\20\f\20\16\20\u00ab\13\20\5\20\u00ad\n\20\3\20\3\20\3\20\2\2\21\2\4\6"+
		"\b\n\f\16\20\22\24\26\30\32\34\36\2\6\3\2\5\6\3\2\7\b\3\2\b\t\3\2\20\21"+
		"\u00be\2)\3\2\2\2\4<\3\2\2\2\6A\3\2\2\2\bN\3\2\2\2\nU\3\2\2\2\fX\3\2\2"+
		"\2\16]\3\2\2\2\20b\3\2\2\2\22v\3\2\2\2\24x\3\2\2\2\26{\3\2\2\2\30\u0089"+
		"\3\2\2\2\32\u0098\3\2\2\2\34\u009d\3\2\2\2\36\u00a3\3\2\2\2 %\5\f\7\2"+
		"!%\5\6\4\2\"%\5\16\b\2#%\5\20\t\2$ \3\2\2\2$!\3\2\2\2$\"\3\2\2\2$#\3\2"+
		"\2\2%&\3\2\2\2&\'\5\2\2\2\'*\3\2\2\2(*\7\2\2\3)$\3\2\2\2)(\3\2\2\2*\3"+
		"\3\2\2\2+-\7\30\2\2,+\3\2\2\2,-\3\2\2\2-/\3\2\2\2.\60\7\27\2\2/.\3\2\2"+
		"\2/\60\3\2\2\2\60\62\3\2\2\2\61\63\7\30\2\2\62\61\3\2\2\2\62\63\3\2\2"+
		"\2\63\64\3\2\2\2\64=\7\32\2\2\65\67\7\30\2\2\66\65\3\2\2\2\66\67\3\2\2"+
		"\2\678\3\2\2\28:\7\32\2\29;\7\27\2\2:9\3\2\2\2:;\3\2\2\2;=\3\2\2\2<,\3"+
		"\2\2\2<\66\3\2\2\2=?\3\2\2\2>@\7\26\2\2?>\3\2\2\2?@\3\2\2\2@\5\3\2\2\2"+
		"AC\7\3\2\2BD\7\32\2\2CB\3\2\2\2CD\3\2\2\2DE\3\2\2\2EG\7\16\2\2FH\5\b\5"+
		"\2GF\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JK\3\2\2\2KL\7\17\2\2LM\7\24"+
		"\2\2M\7\3\2\2\2NP\7\32\2\2OQ\5\n\6\2PO\3\2\2\2PQ\3\2\2\2QS\3\2\2\2RT\7"+
		"\25\2\2SR\3\2\2\2ST\3\2\2\2T\t\3\2\2\2UV\7\4\2\2VW\7\31\2\2W\13\3\2\2"+
		"\2XY\t\2\2\2YZ\t\3\2\2Z[\7\32\2\2[\\\t\4\2\2\\\r\3\2\2\2]^\7\n\2\2^_\7"+
		"\32\2\2_`\7\32\2\2`a\7\24\2\2a\17\3\2\2\2bc\5\4\3\2cd\7\32\2\2de\7\20"+
		"\2\2ef\5\22\n\2fh\7\21\2\2gi\7\13\2\2hg\3\2\2\2hi\3\2\2\2il\3\2\2\2jm"+
		"\5\26\f\2km\7\24\2\2lj\3\2\2\2lk\3\2\2\2m\21\3\2\2\2ns\5\24\13\2op\7\25"+
		"\2\2pr\5\24\13\2qo\3\2\2\2ru\3\2\2\2sq\3\2\2\2st\3\2\2\2tw\3\2\2\2us\3"+
		"\2\2\2vn\3\2\2\2vw\3\2\2\2w\23\3\2\2\2xy\5\4\3\2yz\7\32\2\2z\25\3\2\2"+
		"\2{\u0083\7\16\2\2|\u0080\5\30\r\2}\u0080\5\32\16\2~\u0080\5\34\17\2\177"+
		"|\3\2\2\2\177}\3\2\2\2\177~\3\2\2\2\u0080\u0081\3\2\2\2\u0081\u0082\7"+
		"\24\2\2\u0082\u0084\3\2\2\2\u0083\177\3\2\2\2\u0084\u0085\3\2\2\2\u0085"+
		"\u0083\3\2\2\2\u0085\u0086\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0088\7\17"+
		"\2\2\u0088\27\3\2\2\2\u0089\u008a\7\32\2\2\u008a\u008b\7\20\2\2\u008b"+
		"\u0094\7\32\2\2\u008c\u0092\7\25\2\2\u008d\u008f\7\26\2\2\u008e\u008d"+
		"\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0093\7\32\2\2"+
		"\u0091\u0093\7\31\2\2\u0092\u008e\3\2\2\2\u0092\u0091\3\2\2\2\u0093\u0095"+
		"\3\2\2\2\u0094\u008c\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u0096\3\2\2\2\u0096"+
		"\u0097\7\21\2\2\u0097\31\3\2\2\2\u0098\u0099\7\f\2\2\u0099\u009a\7\20"+
		"\2\2\u009a\u009b\5\30\r\2\u009b\u009c\7\21\2\2\u009c\33\3\2\2\2\u009d"+
		"\u009e\7\r\2\2\u009e\u00a1\5\36\20\2\u009f\u00a2\5\30\r\2\u00a0\u00a2"+
		"\5\32\16\2\u00a1\u009f\3\2\2\2\u00a1\u00a0\3\2\2\2\u00a2\35\3\2\2\2\u00a3"+
		"\u00ac\7\20\2\2\u00a4\u00ad\5\36\20\2\u00a5\u00a8\n\5\2\2\u00a6\u00a8"+
		"\7\33\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a6\3\2\2\2\u00a8\u00ab\3\2\2\2"+
		"\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ad\3\2\2\2\u00ab\u00a9"+
		"\3\2\2\2\u00ac\u00a4\3\2\2\2\u00ac\u00a9\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\u00af\7\21\2\2\u00af\37\3\2\2\2\34$),/\62\66:<?CIPShlsv\177\u0085\u008e"+
		"\u0092\u0094\u00a1\u00a7\u00a9\u00ac";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}