#ifndef LEXICALANALYZER_H_
#define LEXICALANALYZER_H_

#include <iostream>
#include <fstream>
#include <string>
#include <list>
#include <vector>

#include "Dfa.h"

#include "Token.h"

class Analyzer {
public:
	enum TOKEN_TYPE {
		IDENTIFIER, KEYWORD, STRING, CHAR, INTEGER, FLOAT,
		HEXADECIMAL, COMMENT, WHITESPACE, OPERATOR, SEPARATOR, ERROR,
		INVALID_TOKEN
	};

	static const std::list<std::string> KEYWORDS;
	Analyzer(const char *file_path);
	Token* get_token();
	std::string get_value(int i) const;

private:
	Analyzer::TOKEN_TYPE get_token_type(Dfa::STATE final_state) const;
	std::vector<std::string>  strings_list;
	Dfa 					  dfa;

};

#endif /* LEXICALANALYZER_H_ */
