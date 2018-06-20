 %Test 1 Laborator
 %Varianta 2
 
 % Ex 1
 %verif(+Lista).
 primul_element_din_lista(Lista, X) :- primul_element_din_lista(H2|T2), X is H2.
 primul_element_din_lista(NULL, _).
 verif(Lista):- verif(H|T), 
	H >= 0, H =< 99999, % verific daca H e numar  
	primul_element_din_lista(T, X) , % X este prim element din Tail
	if(X / 1000 =< 10) Y is  trunc(X / 1000), % Prima cifra din nr X daca x e de 4 cifre
	if(X / 100 =< 10) Y is  trunc(X / 100),   % Prima cifra din nr X daca x e de 3 cifre
	if(X / 10 =< 10) Y is  trunc(X / 10), 	  % Prima cifra din nr X daca x e de 2 cifre
	if(X / 10 =< 1) Y is  X mod 10, 	  % Prima cifra din nr X daca x e de 1 cifre
	H mod 10 =:= Y, %verific daca Ultima cifra a lui H este egala cu prima a lui Y
	verif(T). % Parcurg recursiv
	verif(NULL).

adauga_elem_lista(Lista, X) :- Lista is (X|Lista).
adauga_elem_lista(_, NULL).
adauga_lista_in_lista(Lista, ListaRez) :- adauga_lista_in_lista((H|Lista), ListaRez), 
	adauga_elem_lista(ListaRez, H), adauga_lista_in_lista(Lista, ListaRez).
adauga_lista_in_lista(NULL, NULL).
lista_de_atomi(L) :- lista_de_atomi(X|T), 
	atom_chars(X,Lchr), %descompunerea atomului X in caractere rezultand lista Lchr
	adauga_lista_in_lista(Lchr, Lrez), 
	lista_de_atomi(T). 
lista_de_atomi(NULL).

gen_grupuri(L,  Lsublist) :- gen_grupuri((H|L), (H2|Lsublist)), H2 < H, 
	adauga_elem_lista(H, Lsublist), %adaug pe H in sublista
	gen_grupuri(L, Lsublist).
gen_grupuri(NULL, _).

matrice_zero(L, N):- N =:= 0.
matrice_zero(L, N):- N =:= 1. % NU STIU

%Ex 2
%orar(zi, ora_incepere, lista_materii)
cauta_in_lista((H|L), M) :- H == M, cauta_in_lista(L, M). % cauta daca materia M este in lista
cauta_in_lista(NULL, _).

orar(luni, 10, [matematica, romana, engleza, matematica]).
orar(marti, 7,[matematica, informatica, engleza, romana]).
orar(miercui, 8, [matematica, matematica, istorie,geografie, informatica]).
orar(joi,9,[informatica, romana, romana]).
orar(vineri,11, [engleza, sport, desen]).

:-dynamic orar/3.

assert(orar(sambata, 6, [matematica, informatica, psihologie])). % adaug in baza de cunostinte
afisare_zile_orar :- orar(X, _, _), write(X). % afisez numelor zilelor din orar
afisare_zile_materie(Materie) :- orar(X, _, L), cauta_in_lista(L, Materie), write(X).
afisare_zile_materie(NULLL).

pred(Materie, ListaOre) :- orar(_,X, M), 
	cauta_in_lista(M, Materie), % cauta materia in M
	cauta_in_lista(ListaOre, X), % cauta ora in ListaOre
	write(X).
% 2. d Care este mai inportant faptul ca e mai dinmineata, sau faptul cas mai multe materii
calc_nr_materii(H|T, X) :- X is X + 1, calc_nr_materii(T, X).
calc_nr_materii(NULL, X).
ziua_cea_mai_grea :- orar(X, Y, _), min(Y), write(X). % afiseaza ziua cea mai devreme
ziua_cea_mai_grea :- orar(X, _, L), calc_nr_materii(L, Rez), min(Rez), write(X).

lista_materilor :- orar(_, _, L), write(L).


%Ex4
5.
abcd.
3.
10.
miau.
8.
5.


pred(FisIn, FisOut, Nrsir):- seeing(Input_curent),
				   see(Fisin),
				   repeat,
					 read(X),
					 X =< 0,
					 X >= 9999,
					 Nrsir is (Nrsir + X),
					   (X==end_of_file
					   ;
					   write(X),nl,fail),
				   !,
				   seen,
				   see(Input_curent),
				   open(FisOut,write,Stream),
                    write(Stream,Nrsir),
                    nl(Stream),
                    close(Stream). % Afiseaza suma
					
%Ex 5
pred(List, N) :- cauta_in_lista(List, N).