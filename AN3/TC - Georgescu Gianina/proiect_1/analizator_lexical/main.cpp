#include <iostream>
#include <fstream>
#include <sstream>
#include <stdio.h>

#include "Analyzer.h"

std::ofstream fout("output.txt");

int main() {

	const char FILE_PATH[] = "input.txt";
	Analyzer analyzer(FILE_PATH);
	Token* token;


	while(token = analyzer.get_token()) {
		fout<< token->get_string(analyzer)<< "\n";
	}


	return 0;
}
