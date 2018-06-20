#include <iostream>

/*
 * Libraria cstdlib este echivalentul din standardul C++ pentru libraria
 * stdlib.h din standardul C. Atentie, conform standardului C++, pentru
 * a include o librarie in sursele voastre nu mai este necesar sa ii includem
 * extensia (.h, .hpp sau .hxx) atunci cand o includem. Pentru a putea folosi
 * totusi librariile clasice din C in C++ (stdio.h, stdlib.h, string.h, time.h
 * si altele), s-au reimplementat aceste librarie, s-au adus la standardul C++
 * si s-au redenumit cu caracterul 'c' ca prefix (stdio.h -> cstdio, stdlib.h ->
 * cstdlib, string.h -> cstring, time.h -> ctime si tot asa)
*/

#include <cstdlib>

#include <ctime>

#include "List.h"

int main()
{
	const int testSize = 40;

	List *list = new List ();

	srand (time (NULL));

	for (int i=0;i<testSize;i++) {
		list->PushBack (rand () % 100);
	}

	std::cout << "Forward iteration:" << std::endl;
	for (ListIterator it = list->Begin (); it != NULL; it ++) {
		std::cout << it.GetInfo () << " ";
	}
	std::cout << std::endl;

	std::cout << "Reverse iteration: " << std::endl;
	for (ListIterator it = list->End (); it != NULL; it --) {
		std::cout << it.GetInfo () << " ";
	}
	std::cout << std::endl;
}
