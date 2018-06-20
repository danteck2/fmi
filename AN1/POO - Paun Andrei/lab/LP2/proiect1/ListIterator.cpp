/*
 * Pentru ca am inclus header-ul ListIterator.h care include la randul lui
 * header-ul Nod.h, din acest fisier am acces la tipul de data "ListIterator"
 * dar si la tipul de data "Nod", fara sa includ explicit acest tip de data.
 * As putea sa o fac, dar ar fi totusi redundant si va fi ignorat de compilator
*/

#include "ListIterator.h"

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
 * adresa nodului current cu valoarea NULL (0).
 *
 * Atentie, aici se foloseste o lista de initializari.
 * Mai exact:
 *
 *		ListIterator::ListIterator ()
 *		{
 *			_current = NULL;
 *		}
 *
 *		Se inlocuieste cu:
 *
 *		ListIterator::ListIterator () :
 *			_current (NULL)
 *		{
 *
 *		}
 *
 * Au rezultate identice. Atributele private ale clasei sunt initializate, cu
 * singura precizare, ca cele doua forme sunt, din punct de vedere ale
 * principiilor de Programare Orientata pe Obiecte, diferite. Prima forma
 * initializeaza atributele prin atribuire (se apeleaza operatorul "="). A
 * doua forma apeleaza direct constructorul atributelor (in cazul nostru
 * constructorul unei adrese, care este un tip de data basic)
 *
 * Observatii:
 * 		1. Apelul constructorilor se face inainte de executarea instructiunilor
 *	din interiorul corpului constructorului.
 *		2. Putem sa apelam oricare constructor al atributelor, pentru oricare
 *	dintre ele, unele putand sa lipseasca, atata timp cat aceste apeluri le vom
 *	separa prin virgula, unele de altele.
 *		2. Ordinea in care se apeleaza constructorii nu este data de cea in
 *	care ii declaram in prototipul clasei (ListIterator.h), NU de cea in care
 *	ii scriem in lista de initializare (este valabil in mod general, in cazul
 *	de fata avem doar un singur atribut :) ).
*/

ListIterator::ListIterator () :
	_current (NULL)
{

}

/*
 * Constructor de copiere. La orice moment in care se va face o copie
 * a unui ListIterator, se va apela aceasta functie, chiar daca operatia de
 * copiere nu se face explicit de catre voi. De exemplu:
 *
 *		void f (ListIterator a);
 *
 * In functia declarata mai sus, cand se apeleaza functia cu un obiect de
 * tip ListIterator, functia va face o copie a acelui obiect si acea copie
 * se va folosi in interiorul functiei. Compilatorul va sti ca atunci cand
 * face copia sa apeleze functia voastra scrisa mai jos.
 *
 * In aceasta metoda am acces la elementele private ale parametrului
 * "other" pentru ca este de acelasi tip de date ca si clasa care
 * implementeaza constructorul (amandoua sunt de tip ListIterator).
*/

ListIterator::ListIterator (const ListIterator& other) :
	_current (other._current)
{

}

/*
 * Metoda publica ce intoarce valoarea informatiei nodului curent,
 * informatie care este la origine privata
*/

int ListIterator::GetInfo () const
{
	return _current->GetInfo ();
}

/*
 * Supraincarcare operator++ folosit pentru trecerea la urmatorul nod din lista
 * Atentie, acest operator este operator ++ prefixat. Practic el va putea fi
 * apelat prin
 *
 *      ++ iterator;
 *
 * Operatorul ++ post-fixat este implementat mai jos.
*/

ListIterator& ListIterator::operator++ ()
{
	_current = _current->GetNext ();

	return *this;
}

/*
 * Supraincarcare operator-- folosit pentru trecerea la nodul anterior din
 * lista. Atentie, acest operator este operator -- prefixat. Practic el va
 * putea fi apelat prin
 *
 *      -- iterator;
 *
 * Operatorul ++ post-fixat este implementat mai jos.
*/

ListIterator& ListIterator::operator-- ()
{
	_current = _current->GetPrev ();

	return *this;
}

/*
 * Supraincarcare operator++ folosit pentru trecerea la nodul anterior din
 * lista. Atentie, acest operator este operator ++ post-fixat. Practic el va
 * putea fi apelat prin
 *
 *      iterator ++;
 *
 * Operatorul ++ post-fixat se supraincarca primind un atribut de tip int
 * dummy, nefolosit propriu-zis, dar necesar pentru a specifica faptul ca
 * este supraincarcat in mod post-fix.
*/

ListIterator ListIterator::operator++ (int dummyValue)
{
	ListIterator temp (*this);
	++ (*this);
	return temp;
}

/*
 * Supraincarcare operator-- folosit pentru trecerea la nodul anterior din
 * lista. Atentie, acest operator este operator -- post-fixat. Practic el va
 * putea fi apelat prin
 *
 *      iterator --;
 *
 * Operatorul -- post-fixat se supraincarca primind un atribut de tip int
 * dummy, nefolosit propriu-zis, dar necesar pentru a specifica faptul ca
 * este supraincarcat in mod post-fix.
*/

ListIterator ListIterator::operator-- (int dummyValue)
{
	ListIterator temp (*this);
	-- (*this);
	return temp;
}

/*
 * Supraincarcare operator== folosit pentru compararea iteratorului cu un
 * pointer la un obiect de tip Nod. Acest operator s-a supraincarcat doar
 * pentru a putea compara un obiect de tip ListIterator cu valoarea NULL
 * pentru a avea o forma mai eleganta de a verifica daca s-a ajuns la
 * finalul (sau inceputul) listei.
*/

bool ListIterator::operator== (Nod* isNULL)
{
	return _current == isNULL;
}

/*
 * Supraincarcare operator== folosit pentru compararea iteratorului cu un
 * alt operator. Acest operator poate fi folosit la compararea a doi iteratori
 * intre ei, eventual la compararea chiar cu obiectul obtinut de la:
 *
 *      ListIterator List::Begin ();
 *
 *  sau
 *
 *      ListIterator List::End ();
*/

bool ListIterator::operator== (const ListIterator& other)
{
	return _current == other._current;
}

/*
 * Supraincarcare operator!= folosit pentru inversa compararii de egalitate,
 * implementat mai sus prin operatorul ==. Acest operator s-a supraincarcat
 * doar pentru a putea compara un obiect de tip ListIterator cu valoarea NULL
 * pentru a avea o forma mai eleganta de a verifica daca s-a ajuns la
 * finalul (sau inceputul) listei.
*/

bool ListIterator::operator!= (Nod* isNULL)
{
	return ! ((*this) == isNULL);
}

/*
 * Supraincarcare operator!= folosit pentru inversa compararii de egalitate,
 * implementat mai sus prin operatorul ==. Acest operator poate fi folosit
 * la compararea a doi iteratori intre ei, eventual la compararea chiar cu
 * obiectul obtinut de la:
 *
 *      ListIterator List::Begin ();
 *
 *  sau
 *
 *      ListIterator List::End ();
*/

bool ListIterator::operator!= (const ListIterator& other)
{
	return ! ((*this) == other);
}

/*
 * Functie externa care atribuie obiectului de tip iterator o adresa specifica
 * unui Nod. Aceasta functie a fost declarata friend in clasa ListIterator
 * pentru a avea acces la atributele private din interiorul clasei. Cum doar
 * clasa List are initial acces la nodurile interne ale listei, informatie
 * utila se poate atribui clasei ListIterator doar din interiorul clasei List.
 *
 * Aceasta functie este folosita in interiorul metodelor Begin () si End () din
 * interiorul clasei List. A se observa ca aceasta functie poate fi apelata
 * de catre oricine, atribuind un obiect de tip Nod* de oriunde, dar informatie
 * utila se poate furniza doar din interiorul clasei List, ceea ce se si
 * intampla.
 *
 * O alta metoda de a simula functionalitatea descrisa mai sus era sa atribuim
 * clasa ListIterator ca o clasa friend in interiorul clasei ListIterator, dar
 * s-a preferat aceasta solutie pentru ca lasa acces doar la functionalitatea
 * de interes pentru clasa List, nu la tot prototipul clasei ListIterator.
*/

void SetInfo (ListIterator& iterator, Nod* nod)
{
	iterator._current = nod;
}
