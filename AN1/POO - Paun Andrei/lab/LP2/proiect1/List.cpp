/*
 * Pentru ca am inclus header-ul List.h care include la randul lui
 * header-ul Nod.h, din acest fisier am acces la tipul de data "List"
 * dar si la tipul de data "Nod", fara sa includ explicit acest tip de data.
 * As putea sa o fac, dar ar fi totusi redundant si va fi ignorat de compilator
*/

#include "List.h"

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
 * adresesele primului si ultimului nod din lista cu valoarea NULL.
 *
 * Atentie, aici se foloseste o lista de initializari.
 * Mai exact:
 *
 *		List::List ()
 *		{
 *			_first = NULL;
 *			_last = NULL;
 *		}
 *
 *		Se inlocuieste cu:
 *
 *		List::List () :
 *			_first (NULL),
 *			_last (NULL)
 *		{
 *
 *		}
 *
 * Au rezultate identice. Atributele private ale clasei sunt initializate, cu
 * singura precizare, ca cele doua forme sunt, din punct de vedere ale
 * principiilor de Programare Orientata pe Obiecte, diferite. Prima forma
 * initializeaza atributele prin atribuire (se apeleaza operatorul "="). A
 * doua forma apeleaza direct constructorul atributelor (in cazul nostru
 * constructorul unor adrese, care sunt un tip de data basic)
 *
 * Observatii:
 * 		1. Apelul constructorilor se face inainte de executarea instructiunilor
 *	din interiorul corpului constructorului.
 *		2. Putem sa apelam oricare constructor al atributelor, pentru oricare
 *	dintre ele, unele putand sa lipseasca, atata timp cat aceste apeluri le vom
 *	separa prin virgula, unele de altele.
 *		2. Ordinea in care se apeleaza constructorii nu este data de cea in
 *	care ii declaram in prototipul clasei (List.h), NU de cea in care
 *	ii scriem in lista de initializare.
*/

List::List () :
	_first (NULL),
	_last (NULL)
{

}

/*
 * Constructor de copiere. La orice moment in care se va face o copie
 * a unui List, se va apela aceasta functie, chiar daca operatia de
 * copiere nu se face explicit de catre voi. De exemplu:
 *
 *		void f (List a);
 *
 * In functia declarata mai sus, cand se apeleaza functia cu un obiect de
 * tip List, functia va face o copie a acelui obiect si acea copie
 * se va folosi in interiorul functiei. Compilatorul va sti ca atunci cand
 * face copia sa apeleze functia voastra scrisa mai jos.
 *
 * In aceasta metoda am acces la elementele private ale parametrului
 * "other" pentru ca este de acelasi tip de data ca si clasa care
 * implementeaza constructorul (amandoua sunt de tip List).
*/

List::List (const List& other) :
	_size (other._size)
{
	Nod* iterator = other._first;

	while (iterator != NULL) {
		PushBack (iterator->GetInfo ());

		iterator = iterator->GetNext ();
	}
}

/*
 * Destructor. Se apeleaza inainte sa se dealoce memoria unui obiect de tip
 * List. Se poate apela implicit de catre compilator in cazul dealocarii unui
 * obiect local, global sau static:
 *
 *		{ List list; }
 *
 * In cazul de mai sus, se creeaza obiectul si se distruge imediat, primul
 * pas din acest proces fiind apelarea metodei de mai jos, destructorul.
 * Acesta se mai poate apela implicit si in cazul memoriei alocate dinamic,
 * dar este necesar apelul operatorului delete:
 *
 *		List *list = new List ();
 *		delete list;
 *
 * De asemenea, se poate apela si explicit. Exista situatii in care se doreste
 * acest comportament:
 *
 *		List *list = new List ();
 *		list->~List ();
 *
 * Atentie, in cazul exemplului de mai sus nu se dealoca memoria pentru
 * obiectul list, doar se apeleaza destructorul ca pe o metoda normala.
 *
 * Pentru aceasta clasa, destructorul sterge toate obiectele de tip nod ce
 * formeaza structura de lista alocata dinamic.
 */

List::~List ()
{
	Nod* iterator = _first;

	while (iterator != NULL) {
		Nod* purged = iterator;
		iterator = iterator->GetNext ();

		delete purged;
	}

	_first = _last = NULL;
}

/*
 * Metoda publica ce permite adaugarea unui nou element la finalul listei.
 *
 * Interfata metodei primeste doar un obiect de tip int, dar intern
 * se aloca memorie pentru pentru un nou obiect de tip Nod si sa se
 * formeaza legaturile spre acesta.
 *
 * In acelasi timp se pastreaza in mod coerent structura interna a clasei:
 * valorile lui _first si _last.
*/

void List::PushBack (int info)
{
	Nod* nod = new Nod (info);

	if (_first == NULL) {
		_first = _last = nod;

		return ;
	}

	_last->SetNext (nod); nod->SetPrev (_last);
	_last = nod;
}

/*
 * Metoda publica ce permite adaugarea unui nou element la inceputul listei.
 *
 * Interfata metodei primeste doar un obiect de tip int, dar intern
 * se aloca memorie pentru pentru un nou obiect de tip Nod si sa se
 * formeaza legaturile spre acesta.
 *
 * In acelasi timp se pastreaza in mod coerent structura interna a clasei:
 * valorile lui _first si _last.
*/

void List::PushFront (int info)
{
	Nod* nod = new Nod (info);

	if (_first == NULL) {
		_first = _last = nod;

		return ;
	}

	nod->SetNext (_first); _first->SetPrev (nod);
	_first = nod;
}

/*
 * Metoda publica ce permite stergerea ultimului element al listei.
 *
 * Intern, metoda reface legaturile dintre noduri, elibereaza memoria pentru
 * elementul sters si pastreaza coerenta structura interna a clase: valorile
 * _first si _last.
*/

void List::PopBack ()
{
	if (_last == NULL) {
		return;
	}

	Nod* purged = _last;
	Nod* prev = _last->GetPrev ();

	delete purged;

	if (prev == NULL) {
		_first = _last = NULL;
		return ;
	}

	prev->SetNext (NULL);
	_last = prev;
}

/*
 * Metoda publica ce permite stergerea primului element al listei.
 *
 * Intern, metoda reface legaturile dintre noduri, elibereaza memoria pentru
 * elementul sters si pastreaza coerenta structura interna a clase: valorile
 * _first si _last.
*/

void List::PopFront ()
{
	if (_first == NULL) {
		return ;
	}

	Nod* purged = _first;
	Nod* next = _first->GetNext ();

	delete purged;

	if (next == NULL) {
		_first = _last = NULL;
		return ;
	}

	next->SetPrev (NULL);
	_first = next;
}

/*
 * Metoda publica ce permite obtinerea unui obiect de tip ListIterator ce
 * pastreaza informatii despre structura interna a listei. Obiectul returnat
 * este initializat cu nodul de inceput al listei.
 *
 * A se observa ca s-a apelat functia:
 *
 * 		void SetInfo (ListIterator& listIterator, Nod* nod);
 *
 * Aceasta este o functie libera ce se gaseste in definitia clasei
 * ListIterator si este folosita pentru a seta ListIteratorul pe un anumit nod
 * intern al listei. Functia este FRIEND in clasa ListIterator, special pentru
 * a avea acces la nodul curent pe care il pastreaza ListIteratorul, informatie
 * la care in mod normal nu am avea acces extern, deoarece este definita ca
 * atribut privat in clasa.
 *
 * In corpul functiei se creeaza un obiect de tip ListIterator. Acesta este
 * returnat ulterior in exteriorul apelului metodei.
*/

ListIterator List::Begin ()
{
	ListIterator listIterator;

	SetInfo (listIterator, _first);

	return listIterator;
}

/*
 * Metoda publica ce permite obtinerea unui obiect de tip ListIterator ce
 * pastreaza informatii despre structura interna a listei. Obiectul returnat
 * este initializat cu nodul de final al listei.
 *
 * A se observa ca s-a apelat functia:
 *
 * 		void SetInfo (ListIterator& listIterator, Nod* nod);
 *
 * Aceasta este o functie libera ce se gaseste in definitia clasei
 * ListIterator si este folosita pentru a seta ListIteratorul pe un anumit nod
 * intern al listei. Functia este FRIEND in clasa ListIterator, special pentru
 * a avea acces la nodul curent pe care il pastreaza ListIteratorul, informatie
 * la care in mod normal nu am avea acces extern, deoarece este definita ca
 * atribut privat in clasa.
*/

ListIterator List::End ()
{
	ListIterator listIterator;

	SetInfo (listIterator, _last);

	return listIterator;
}
