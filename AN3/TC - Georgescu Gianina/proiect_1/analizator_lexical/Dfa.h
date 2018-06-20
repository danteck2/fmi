#ifndef DFA_H_
#define DFA_H_

#include <map>
#include <string>
#include <vector>
#include <functional>


class Dfa {
public:
	Dfa(const char* file_name);

	enum STATE {
		INITIAL = 0,
		IDENTIFIER,
		PLUS, MINUS, STAR, SLASH, MOD, EQUAL, LESS_THAN, GREATER_THAN, AND, NOT, OR, XOR,
			SHIFT_RIGHT, SHIFT_LEFT, POINT,
		NON_TOKEN_SEPARATOR, SEPARATOR,
		COMMENT, SINGLE_LINE_COMMENT, MULTI_LINE_COMMENT, MULTI_LINE_COMMENT_STAR,
			MULTI_LINE_COMMENT_END,
		CHAR, CHAR_ESCAPE, CHAR_ESCAPE_X, CHAR_ESCAPE_HEX1, CHAR_ESCAPE_NUMBER,
			CHAR_CHARACTER, CHAR_END, STRING, STRING_ESCAPE, STRING_END,
		    NUMBER, NUMBER_U, NUMBER_L, NUMBER_UL, ZERO, HEXA, FLOAT_NUMBER,
		    EXPONENT, EXPONENT_VALUE, FLOAT_NUMBER_L,
		OPERATOR,
		END, ERROR
	};

	~Dfa();

	std::pair<Dfa::STATE, std::string> execute();
	bool is_EOF();
	int get_position();

private:
	typedef bool (*funct)(char c);
	typedef std::pair<funct, STATE> transition_pair;
	typedef std::vector<transition_pair> transitions_list;

	std::map<STATE, transitions_list > transitions;
	int file_position;
	FILE *fp;

};

#endif /* DFA_H_ */
