#ifndef LIST_H
#define LIST_H

#include "Nod.h"
#include "ListIterator.h"

class List
{
private:
	Nod* _first;
	Nod* _last;
	unsigned int _size;

public:
	List ();
	List (const List& other);
	~List ();

	void PushBack (int info);
	void PushFront (int info);

	void PopBack ();
	void PopFront ();

	ListIterator Begin ();
	ListIterator End ();
};

#endif