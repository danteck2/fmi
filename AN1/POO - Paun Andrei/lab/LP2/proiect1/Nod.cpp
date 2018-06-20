#include "Nod.h"

/*
 * Aceasta librarie este necesara pentru a folosi macroinstructiunea
 * "NULL". Aceasta nu vine odata cu limbajul C ci se gaseste in libraria
 * stdlib.h (cstdlib) ca macroinstructiune
 *
 * 		#define NULL ((void*)0)
*/

#include <cstdlib>

/*
 * Constructor fara niciun parametru care initializeaza
 * valoarea informatiei cu valoarea 0.
 *
 * Atentie, aici se foloseste o lista de initializari.
 * Mai exact:
 *
 *		Nod::Nod ()
 *		{
 *			_info = 0;
 *			_next = NULL;
 *			_prev = NULL;
 *		}
 *
 *		Se inlocuieste cu:
 *
 *		Nod::Nod () :
 *			_info (0),
 *			_next (NULL),
 *			_prev (NULL)
 *		{
 *
 *		}
 *
 * Au rezultate identice. Atributele private ale clasei sunt initializate, cu
 * singura precizare, ca cele doua forme sunt, din punct de vedere ale
 * principiilor de Programare Orientata pe Obiecte, diferite. Prima forma
 * initializeaza atributele prin atribuire (se apeleaza operatorul "="). A
 * doua forma apeleaza direct constructorul atributelor (in cazul nostru
 * constructorul unor intregi si a unor adrese, care sunt tipuri de data basic)
 *
 * Observatii:
 * 		1. Apelul constructorilor se face inainte de executarea instructiunilor
 *	din interiorul corpului constructorului.
 *		2. Putem sa apelam oricare constructor al atributelor, pentru oricare
 *	dintre ele, unele putand sa lipseasca, atata timp cat aceste apeluri le vom
 *	separa prin virgula, unele de altele.
 *		2. Ordinea in care se apeleaza constructorii nu este data de cea in
 *	care ii declaram in prototipul clasei (Nod.h), NU de cea in care ii
 *	scriem in lista de initializare
*/

Nod::Nod () :
	_info (0),
	_next (NULL),
	_prev (NULL)
{

}

/*
 * Constructor cu un parametru de tip int. Acesta foloseste
 * parametrul pentru initializarea informatiei.
*/

Nod::Nod (int info) :
	_info (info),
	_next (NULL),
	_prev (NULL)
{

}

/*
 * Constructor de copiere. La orice moment in care se va face o copie
 * a unui nod, se va apela aceasta functie, chiar daca operatia de copiere
 * nu se face explicit de catre voi. De exemplu:
 *
 *		void f (nod a);
 *
 * In functia declarata mai sus, cand se apeleaza functia cu un obiect de
 * tip nod, functia va face o copie a acelui obiect si acea copie se va folosi
 * in interiorul functiei. Compilatorul va sti ca atunci cand face copia
 * sa apeleze functia voastra scrisa mai jos.
 *
 * In aceasta metoda am acces la elementele private ale parametrului
 * "other" pentru ca este de acelasi tip de date ca si clasa care
 * implementeaza constructorul (amandoua sunt de tip nod).
*/

Nod::Nod (const Nod& other) :
	_info (other._info),
	_prev (other._prev),
	_next (other._next)
{

}

/*
 * Metoda publica ce intoarce valoarea informatiei, care este la origine
 * privata
*/

int Nod::GetInfo () const
{
	return _info;
}

/*
 * Metoda publica ce intoarce adresa spre nodul anterior, adresa ce
 * este la origine privata
*/

Nod* Nod::GetPrev () const
{
	return _prev;
}

/*
 * Metoda publica ce intoarce adresa spre urmatorul nod, adresa ce
 * este la origine privata
*/

Nod* Nod::GetNext () const
{
	return _next;
}

/*
 * Metoda publica ce modifica valoarea informatiei, care este la origine
 * privata
*/

void Nod::SetInfo (int info)
{
	_info = info;
}

/*
 * Metoda public pentru modificarea adresei spre urmatorul nod,
 * adresa ce este la origine privata
*/

void Nod::SetNext (Nod* next)
{
	_next = next;
}

/*
 * Metoda public pentru modificarea adresei spre urmatorul nod,
 * adresa ce este la origine privata
*/

void Nod::SetPrev (Nod* prev)
{
	_prev = prev;
}
