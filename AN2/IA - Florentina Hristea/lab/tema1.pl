%Ex1
culoare(morcov, portocaliu).
culoare(portocala, portocaliu).
culoare(mar, verde).
culoare(fasole, verde).
culoare(mar, rosu).
culoare(fasole, galbena).
culoare(iarba, verde).
culoare(trifoiul, verde).
leguma(morcov).
leguma(fasole).
fruct(mar).
fruct(portocala).
plantafurajera(iarba).
plantafurajera(trifoiul).
maidulce(mar, portocala). % mar mai dulce decat portocala
maidulce(portocala, fasole).
aliment(X):- leguma(X); fruct(X).
%acelasi_tip(-Aliment1, -Aliment2)
acelasi_tip(Aliment1, Aliment2):- (leguma(Aliment1), leguma(Aliment2)); (fruct(Aliment1), fruct(Aliment2)).
acelasi_tip(Aliment1, Aliment2):- culoare(Aliment1, Y),culoare(Aliment2, Z), Y==Z.

%Exemple de interogari
/*
| ?- acelasi_tip(morcov, portocala).          
yes
| ?- acelasi_tip(mar, fasole).                
yes
| ?- acelasi_tip(mar, portocala).             
yes
| ?- acelasi_tip(morcov, fasole).             
yes
| ?- acelasi_tip(mar, fasole).                  
yes
| ?- acelasi_tip(morcov, portocala).            
yes
*/

%Ex2
%calculeaza_numar(-Nr)
cifra1(4).
cifra1(8).
cifra2(2).
cifra2(3).
cifra2(4).
cifra3(0). 
cifra3(1).
%suma_cifre(X, Y):- ((X + Y) mod 2) =\= 0.
%calculeaza_numar(Nr):- cifra1(Nr), cifra2(Nr), cifra4(Nr).



%Exemple de interogari
/*

*/

mmicnr(X1,X2):- X1 < X2.
mmarenr(X1,X2):- X1 > X2.
mmicenr(X1,X2):- X1 =< X2.
mmareenr(X1,X2):- X1 >= X2.
egalexp(X1,X2):- X1 =:= X2. 
diferitexp(X1,X2):- X1 =\= X2.
egalterm(X1,X2):- X1 == X2.
diferitterm(X1,X2):- X1 \== X2.

mmicterm(X1,X2):- X1 @< X2.
mmareterm(X1,X2):- X1 @> X2.
mmiceeterm(X1,X2):- X1 @=< X2.
mmareeterm(X1,X2):- X1 @>= X2.

/*
%compara ca termeni
| ?- egalterm(2+3, 5*1).
no
%compara aritmetic
| ?- egalexp(2+3, 5*1).
yes
| ?- 
*/

culoare(verde).
culoare(rosu).
culoare(albastru).
culoare(mov).

/*
| ?- culoare(rosu).
yes
| ?- culoare(mov). 
yes
| ?- culoare(X).  
X = verde ? n
X = rosu ? n
X = albastru ? n
X = mov ? n
no
| ?- 
*/

pisica('Miaunel').
tigru('Tigrila').
%felina(X):-pisica(X);tigru(X).
felina(X):-pisica(X).
felina(X):-tigru(X).

/*
| ?- felina('Miaunel').
yes
| ?- felina('Tigrila').
yes
| ?- felina('Tiga').   
no
*/

cap(ploua, umbrela).
cap(ninge, caciula).
cap(soare, palarie).
haina(ger, palton).
haina(frig, jacheta).
haina(caldut, vesta).
haina(cald, fara_haina).
stare(-20, 0, ger).
stare(0, 10, frig).
stare(10, 20, caldut).
stare(20, 40, cald).
incaltaminte(polei, _, cizme).
incaltaminte(baltoace, _, ghete).
incaltaminte(uscat, frig, adidasi).
incaltaminte(uscat, cald, sandale).

%azi(-3, ninge, polei, C, H, I).
azi(T, P, S, C, H, I):- cap(P, C), 
						stare(T1, T2, Stare),
						T1 =< T, T < T2, 
						haina(Stare, H), 
						incaltaminte(S, Stare, I).
						
/*
| ?- azi(-3, ninge, polei, C, H, I).
C = caciula,
H = palton,
I = cizme ? n
no
| ?- azi(5, soare, uscat, C, H, I). 
C = palarie,
H = jacheta,
I = adidasi ? n
no
*/

max3(X, Y, Z, M):- X >= Y, X >= Z, M is X. 
max3(X, Y, Z, M):- Y >= X, Y >= Z, M is Y. 
max3(X, Y, Z, M):- Z >= X, Z >= Y, M is Z. 

/*
| ?- max3(1, 2, 3, M).
M = 3 ? n
no
| ?- max3(1, 3, 2, M).
M = 3 ? n
no
| ?- max3(3, 1, 2, M).
M = 3 ? n
no
*/

membru(ionel,echipa1).
membru(gigel,echipa1).
membru(danel,echipa2).
membru(tudorel,echipa2).
adversari(X,Y):- membru(X,Z1), membru(Y,Z2), X \== Y, Z1 \== Z2.
colegi(X,Y):- membru(X,Z1), membru(Y,Z2), X \== Y, Z1 == Z2.

/*
| ?- adversari(X, Y).          
X = ionel,
Y = danel ? n
X = ionel,
Y = tudorel ? n
X = gigel,
Y = danel ? n
X = gigel,
Y = tudorel ? n
X = danel,
Y = ionel ? n
X = danel,
Y = gigel ? n
X = tudorel,
Y = ionel ? n
X = tudorel,
Y = gigel ? n
no

| ?- colegi(X, Y).
X = ionel,
Y = ionel ? n
X = ionel,
Y = gigel ? n
X = gigel,
Y = ionel ? n
X = gigel,
Y = gigel ? n
X = danel,
Y = danel ? n
X = danel,
Y = tudorel ? n
X = tudorel,
Y = danel ? n
X = tudorel,
Y = tudorel ? n
no
% problema e in X == Y

*/

obiect(a).
obiect(b).
obiect(c).
permutari(X,Y,Z):-obiect(X), obiect(Y), X\==Y, obiect(Z), Y\==Z, X\==Z.

/*
| ?- permutari(X, Y, Z).
X = a,
Y = b,
Z = c ? n
X = a,
Y = c,
Z = b ? n
X = b,
Y = a,
Z = c ? n
X = b,
Y = c,
Z = a ? n
X = c,
Y = a,
Z = b ? n
X = c,
Y = b,
Z = a ? n
no

*/