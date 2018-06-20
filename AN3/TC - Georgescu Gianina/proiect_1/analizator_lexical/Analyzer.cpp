#include <algorithm>
#include <iostream>
#include <fstream>
#include <string>

#include "Dfa.h"
#include "Analyzer.h"
#include "Token.h"


const std::list<std::string> Analyzer::KEYWORDS = std::list<std::string>({
	"auto", "int", "const", "short", "break", "long", "continue", "signed",
	"double", "struct", "float", "unsigned", "else", "switch", "for",
	"void", "case", "register", "default", "sizeof", "char", "return", "do",
	"static", "enum", "typedef", "goto", "volatile", "extern", "union", "if", "while"
});


Analyzer::Analyzer(const char *file_path)
	: dfa(file_path)
{
}

Token* Analyzer::get_token() {
	if(dfa.is_EOF()) {
		return NULL;
	}

	auto r = dfa.execute();
	Dfa::STATE final_state = r.first;
	std::string token_value = r.second;

	if(final_state == Dfa::ERROR) {
		std::cout<< "EROARE  - " << token_value
				<< " POZITIA - " << dfa.get_position();
		return NULL;
	}

	TOKEN_TYPE token_type;
	if(std::find(Analyzer::KEYWORDS.begin(),
			Analyzer::KEYWORDS.end(), token_value) != Analyzer::KEYWORDS.end()) {
		token_type = KEYWORD;
	} else {
		token_type = get_token_type(final_state);
	}

	if(token_type == INVALID_TOKEN) {
		return NULL;
	}
    if(token_type == COMMENT){
        return get_token();
    }
	if(token_type == WHITESPACE) {
		return get_token();
	}

	int string_list_index;
	std::vector<std::string>::iterator it;
	if((it = std::find(strings_list.begin(), strings_list.end(),
			token_value)) != strings_list.end()) {
		string_list_index = it - strings_list.begin();
	} else {
		strings_list.push_back(token_value);
		string_list_index = strings_list.size() - 1;
	}

	return new Token(token_type, string_list_index);
}

Analyzer::TOKEN_TYPE Analyzer::get_token_type(Dfa::STATE final_state) const
{
	switch(final_state) {
	case Dfa::STRING_END:
		return STRING;
	case Dfa::CHAR_END:
		return CHAR;
	case Dfa::MULTI_LINE_COMMENT_END:
	case Dfa::SINGLE_LINE_COMMENT:
		return COMMENT;
	case Dfa::NON_TOKEN_SEPARATOR:
		return WHITESPACE;
	case Dfa::NUMBER:
	case Dfa::NUMBER_U:
	case Dfa::NUMBER_L:
	case Dfa::NUMBER_UL:
	case Dfa::ZERO:
		return INTEGER;
	case Dfa::HEXA:
		return HEXADECIMAL;
	case Dfa::FLOAT_NUMBER:
	case Dfa::EXPONENT:
	case Dfa::EXPONENT_VALUE:
	case Dfa::FLOAT_NUMBER_L:
		return FLOAT;
	case Dfa::PLUS:
	case Dfa::MINUS:
	case Dfa::STAR:
	case Dfa::SLASH:
	case Dfa::MOD:
	case Dfa::EQUAL:
	case Dfa::LESS_THAN:
	case Dfa::GREATER_THAN:
	case Dfa::AND:
	case Dfa::NOT:
	case Dfa::OR:
	case Dfa::XOR:
	case Dfa::SHIFT_RIGHT:
	case Dfa::SHIFT_LEFT:
	case Dfa::POINT:
		return OPERATOR;
	case Dfa::SEPARATOR:
		return SEPARATOR;
	case Dfa::IDENTIFIER:
		return IDENTIFIER;
	default:
		return INVALID_TOKEN;
	}
}

std::string Analyzer::get_value(int i) const
{
	return strings_list[i];
}
