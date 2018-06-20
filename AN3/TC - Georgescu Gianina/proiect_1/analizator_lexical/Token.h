#ifndef TOKEN_H_
#define TOKEN_H_


#include <string>

class Analyzer;

class Token {
public:
	Token(int type, int value);
	std::string get_string(const Analyzer& lexical_analyzer);

private:
	int type;
	int value;
};

#endif /* TOKEN_H_ */
