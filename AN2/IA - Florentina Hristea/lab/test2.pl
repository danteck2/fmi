% ex 1

arb_binar(a1,
	arb(
	  1,
	  arb(
		2,
		arb(4,null,null),
		null
	  ),
	  arb(
		3,
		arb(
		  5,
		  arb(7,null,null),
		  arb(8,null,null)
		),
		arb(6,null,null)
	  )
	)
).

afis_arb_binar(NumeArbore):-arb_binar(NumeArbore,Arbore),afis_arb_binar_aux(0,Arbore).
afis_arb_binar_aux(_, null).
afis_arb_binar_aux(Niv, arb(R,Fs,Fd)):- tab(Niv), write(R), nl, Niv1 is Niv+1, afis_arb_binar_aux(Niv1, Fs), afis_arb_binar_aux(Niv1, Fd).

tab(N):-N>0,N1 is N-1, write(' '),tab(N1).
tab(0).
					
extrage_info(null,0).
extrage_info(arb(n(_,X),_,_),X).
extrage_info(n(_,X),X).

%nr_perechi(+Nume,-Nr).
nr_perechi(Nume,Nr):-
					extrage_info(FS,Ifs),
					extrage_info(FD,Ifd),
					Ifs > Ifd,
					Nr is Nr + 1.


% ex 2 ------------------------------------------------------------

%arbore(nume, reprezentare_efectiva).
arbore(a1,
    arb('A', [
      arb('B', [
        arb('C', []),
        arb('D', []),
        arb('E', [])
      ]),
      arb('F', [
        arb('G', [])
      ]),
      arb('J', []),
      arb('K', [
        arb('L', []),
        arb('M', [])
      ])
    ])
).

afis_arb_oarecare(NumeArbore):-arbore(NumeArbore,Arbore),afis_arb_o_aux(0,Arbore).

afis_arb_o_aux(Niv, arb(R,[])):-tab(Niv), write(R), nl.
afis_arb_o_aux(Niv, arb(R,Fii)):- tab(Niv), write(R), nl, Niv1 is Niv+1, afis_arb_frati(Niv1, Fii).
afis_arb_frati(Niv, [Fiu|T]):-  afis_arb_o_aux(Niv, Fiu), afis_arb_frati(Niv, T).
afis_arb_frati(_,[]).

verifica(LFii,Paritate):- verifica_aux(LFii,Paritate,0).

verifica_aux([],_,N):- N>0.
verifica_aux([arb(R,_)|T],P,Nv):- (R mod 2=:=P -> Nn is Nv-1; Nn is Nv+1), verifica_aux(T,P,Nn).

%arb_2list(+Nume,-Mi, -Mp).

arb_2list(Nume, Mi, Mp, Niv):- (Niv mod 2) =:= 1, afis_arb_o_aux(Niv, Nume).

% Problema de cautare (Evadarea lui Mormolocel...) ---------------------------------------------

%citeste_fis(+Fisin)
citeste_fis(Fisin):- open(Fisin,read,Stream),
                     repeat,
                       read(Stream,X),
                         (X==end_of_file
                         ;
                         write(X),nl,fail),
                       !,
                       close(Stream). 
