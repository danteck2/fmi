#ifndef LISTITERATOR_H
#define LISTITERATOR_H

#include "Nod.h"

class ListIterator
{
private:
	Nod* _current;

public:
	ListIterator ();
	ListIterator (const ListIterator& other);

	int GetInfo () const;

	ListIterator& operator++();
	ListIterator& operator--();

	ListIterator operator++(int dummyValue);
	ListIterator operator--(int dummyValue);

	bool operator==(Nod* isNULL);
	bool operator!=(Nod* isNULL);

	bool operator==(const ListIterator& other);
	bool operator!=(const ListIterator& other);

	friend void SetInfo (ListIterator& iterator, Nod* nod);
};

#endif