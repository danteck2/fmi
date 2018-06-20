#include "Analyzer.h"
#include "Token.h"

#include <string>


Token::Token(int type, int value)
{
	this->type = type;
	this->value = value;
}

std::string get_token_type(int token_type)
{
	switch(token_type) {
	case Analyzer::IDENTIFIER:
		return "IDENTIFICATOR";
	case Analyzer::KEYWORD:
		return "CUVANT CHEIE";
	case Analyzer::STRING:
		return "STRING";
	case Analyzer::CHAR:
		return "CHAR";
	case Analyzer::INTEGER:
		return "INTEGER";
	case Analyzer::FLOAT:
		return "FLOAT";
	case Analyzer::HEXADECIMAL:
		return "HEXADECIMAL";
	case Analyzer::COMMENT:
		return "COMENTARIU";
	case Analyzer::WHITESPACE:
		return "SPATIU";
	case Analyzer::OPERATOR:
		return "OPERATOR";
	case Analyzer::SEPARATOR:
		return "SEPARATOR";
	case Analyzer::ERROR:
		return "EROARE";
	case Analyzer::INVALID_TOKEN:
	default:
		return "TOKEN_GRESIT";
	}
}

std::string Token::get_string(const Analyzer& lexical_analyzer) {
	return get_token_type(this->type) + " - "
			+ lexical_analyzer.get_value(this->value);
}
