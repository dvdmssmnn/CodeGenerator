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

grammar SimpleHeader;

@header
{
package dm.generator.antlr;
}

expression      : (include
                | enum_specifier
                | typedef
                | function_dec) expression
                | EOF;

type            : (UNSIGNED? CONST? UNSIGNED? IDENTIFIER | UNSIGNED? IDENTIFIER CONST?) STAR?
                ;

enum_specifier  : 'enum' IDENTIFIER? LBRACE enum_entry+ RBRACE SEMICOLON
                ;
enum_entry      : IDENTIFIER enum_value? COMMA?
                ;

enum_value      : '=' NUMBER
                ;

include         : ('#include'|'#import') ('<'|'\"') IDENTIFIER ('>'|'\"')
                ;

typedef         : 'typedef' IDENTIFIER IDENTIFIER SEMICOLON
                ;

function_dec    : type IDENTIFIER LPAREN param_list RPAREN ('?')? (parse | SEMICOLON)
                ;

param_list      : (param (COMMA param)*)?
                ;

param           : type IDENTIFIER
                ;

parse           : LBRACE ((parse_var | parse_var_out | cond_parse_var) SEMICOLON)+  RBRACE
                ;

parse_var       : IDENTIFIER LPAREN IDENTIFIER (COMMA ((('*')? IDENTIFIER) | NUMBER))? RPAREN
                ;

parse_var_out   : 'out' LPAREN parse_var RPAREN
                ;

cond_parse_var  : 'if' condition (parse_var | parse_var_out)
                ;

condition       : LPAREN (condition | (~(LPAREN | RPAREN) | LOGICALS)*) RPAREN
                ;


LBRACE          : '{';
RBRACE          : '}';
LPAREN          : '(';
RPAREN          : ')';
LBRACKET        : '[';
RBRACKET        : ']';
SEMICOLON       : ';';
COMMA           : ',';
STAR            : '*';
CONST           : 'const';
UNSIGNED        : 'unsigned';

NUMBER          : ('-')?('0'..'9')+|('0x'('0'..'9')+);
IDENTIFIER      : ('a'..'z'|'A'..'Z'|'0'..'9'|'_'|'/'|'.')+;

LOGICALS        : ('&'|'|'|'='|'!'|'<'|'>')+;

SINGLE_COMMENT  : '//' ~('\n')* -> skip;

WS              : [ \r\t\n]+ -> skip;
