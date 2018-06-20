#ifndef NOD_H
#define NOD_H

class Nod
{
private:
	int _info;
	Nod* _prev;
	Nod* _next;

public:
	Nod ();
	Nod (int info);
	Nod (const Nod& other);

	int GetInfo () const;
	Nod* GetNext () const;
	Nod* GetPrev () const;

	void SetInfo (int info);
	void SetNext (Nod* next);
	void SetPrev (Nod* prev);
};

#endif