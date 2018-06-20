-- Voi face mai multe solutii la fiecare problema (le voi numi f, f2, f3
--  si asa mai departe) la fiecare functie f pe care trebuie sa o scrieti

-- Functiile care pot fi folosite sunt descrise la sfarsitul fisierului
-- categoria A - functii de baza
-- categoria B - functii din biblioteci (fara map, filter, fold)
-- categoria C - map, filter, fold 
import Data.Char
import Test.QuickCheck
 
type Cifra = Int
type Numar = [Cifra]

-- In acest test vom implementa cateva operatii pe numere mari.
-- O Cifra este un numar intreg intre 0 si 9.
-- Un Numar este o lista de Cifre. E.g., [2,1,4]
-- Numarul intreg reprezentat de un Numar n este obtinut
-- prin alipirea cifrelor lui n de la stanga la dreapta,
-- ignorand cifrele de 0 de la inceputul numarului.
-- E.g., numarul corespunzator lui [0, 0, 0, 2, 1, 4] este 214.
-- Prin conventie lista vida de cifre [] poate reprezenta nr. 0


-- 1a (0,5p). Scrieti o functie care date fiind un Numar n si o lungime l,
-- adauga l cifre de 0 la stanga lui n.
-- E.g., lungimePlus [2, 1, 4] 3 = [0, 0, 0, 2, 1, 4]
lungimePlus :: Numar -> Int -> Numar

-- Adrian: Sunt diferite solutii aici, putem cu recursivitate
--         Daca adaugam 0 zerouri, avem acelasi numar
lungimePlus numar 0  = numar
--         Altfel daca adaugam `zerouri` zerouri, e ca si cum am adauga (zerouri - 1) zerouri
--         la sirul cu un 0 in fata
lungimePlus numar zerouri = 
    lungimePlus (0:numar) (zerouri - 1)

-- Adrian: Alta optiune e sa facem o lista cu `zerouri` de 0 si sa o concatenam
--         Una din metoda e folosind descrieri de liste (list comprehension)
lungimePlus2 numar zerouri =
    [0 | x <- [1..zerouri]] ++ numar

-- Adrian: Alta optiune e folosind map, putem "mapa" sirul [1, 2, ..., zerouri] la
--         [0, 0, 0, ..., 0] (zerouri de 0) si dupa sa concatenam
return0 :: Int -> Int
return0 _ = 0
lungimePlus3 numar zerouri =
    (map return0 [1..zerouri]) ++ numar

-- 1b (1p). Scrieti o functie care ia ca argument o pereche de numere
-- si calculeaza perechea de numere cu numar egal de cifre 
-- obtinuta prin adaugarea de zerouri la stanga numerelor date ca argument.
-- E.g., normalizeazaLungime ([1,2], [3,4,5,6]) = ([0,0,1,2], [3,4,5,6])
-- E.g., normalizeazaLungime ([1,2], []) = ([1,2], [0,0])
normalizeazaLungime :: (Numar, Numar) -> (Numar, Numar)

-- Adrian: Mare grija este un singur argument (o pereche) si se returneaza o singura valoare
--         (o pereche)
normalizeazaLungime (a, b) =
    let la = length a in -- punem in la lungimea lui a, ca sa nu scriem de multe ori
    let lb = length b in -- punem in lb lungimea lui b
    if la < lb then -- daca a e mai mic, trebuie sa-l normalizam (folosind lungimePlus)
        (lungimePlus a (lb - la), b)
    else -- daca b e mai mic(sau egal, ca merge lungimePlus si cu 0 ca argument)
        (a, lungimePlus b (la - lb))


-- 2a (0,75p). Scrieti o functie care ia doua Numere *de aceeasi lungime* ca argumente
-- si verifica daca primul Numar este mai mic sau egal cu al doilea.
-- Puteti folosi doar recursie si functii din categoria A
-- E.g., [1,2,3] `lteN` [1,2,1] = False
-- E.g., [0,2,3] `lteN` [1,2,1] = True
lteN :: Numar -> Numar -> Bool

-- Adrian: Facem cu recursivitate (ca asa se cere)
--         Pasul terminal, listele au aceeasi lungime, deci daca una e vida si cealalta e
--         Si lista vida prin conventie e 0, deci 0 <= 0 care e adevarat
lteN [] [] = True
--         Altfel comparam cifra cu cifra
lteN (h1:t1) (h2:t2) = 
    if h1 /= h2 then -- Daca sunt diferite, nu mai conteaza restul cifrelor
        h1 < h2
    else -- Daca sunt egale, continuam recursiv
        lteN t1 t2

-- Adrian: O metoda mai lunga (dar complet echivalenta) este
lteN2 [] [] = True
lteN2 (h1:t1) (h2:t2) =
    if h1 < h2 then
        True
    else if h1 > h2 then
        False
    else
        lteN2 t1 t2
-- 2b (0,25p).  Scrieti o functie care ia doua Numere ca argumente
-- si verifica daca primul Numar este mai mic sau egal cu al doilea
lte :: Numar -> Numar -> Bool

-- Adrian: Putem as folosim lteN, dar pentru asta trebuie ca numerele
--         sa aiba aceeasi lungime
lte a b =
    let (x, y) = normalizeazaLungime (a, b) in
    lteN x y


-- 3a (1p). Scrieti o functie care primeste ca argument o lista de
-- numere naturale intre 0 si 81, reprezentand rezultatele brute
-- ale unei operatii asupra unui numar, si calculeaza o pereche
-- formata dintr-o Cifra c si o lista de cifre cs, unde cs are aceeasi 
-- lungime ca lista initiala, fiind obtinuta prin propagarea 
-- depasirilor de cifre de la dreapta la stanga, iar c este cifra 
-- care reprezinta depasirea in plus.
-- E.g. propagaFold' [1, 1] = (0, [1, 1])    -- obtinut din 10 + 1
-- E.g. propagaFold' [1, 10] = (0, [2, 0])   -- obtinut din 19 + 1
-- E.g. propagaFold' [10, 1] = (1, [0, 1])   -- obtinut din 30 + 71
-- E.g. propagaFold' [81, 81] = (8, [9, 1])  -- obtinut din 9*99
-- Folositi doar functii din categoriile A, B, si C
-- Fara recursie sau descrieri de liste.

-- Adrian: trebuie cu fold, sper ca va explicati intre voi
--         Asta e funcita de fold, primeste o pereche (rest, cifre) si o cifra noua
--         Si da noua pereche (rest, cifre)
--         Fiindca facem foldr primul argument e tipul unul element din lista,
--         al doilea al acumulatorului
propaga cifra (rest, lista_cifre) =
    let total = cifra + rest in
--        Vom folosi (total - (total `mod` 10)) `div` 10
--        in loc de total `div` 10 ca sa mearga si la negative
--        Mai exact daca cifra e -1 (obtinut dintr-o scadere)
--        Vrem sa punem 9 si restul -1
    ((total - (total `mod` 10)) `div` 10, (total `mod` 10):lista_cifre)
propagaFold' :: [Int] -> (Cifra, [Cifra])
propagaFold' lista_cifre = foldr propaga (0, []) lista_cifre

-- 3b (0,5p).  Scrieti o functie care primeste ca argument o lista
-- de numere naturale ca la (3a) si calculeaza numarul corespunzator ei
-- obtinut prin propagarea depasirilor.
-- E.g. propagaFold [1, 1] =  [0, 1, 1]   -- obtinut din 10 + 1
-- E.g. propagaFold [1, 10] = [0, 2, 0]   -- obtinut din 19 + 1
-- E.g. propagaFold [10, 1] = [1, 0, 1]   -- obtinut din 30 + 71
-- E.g. propagaFold [81, 81] = [8, 9, 1]  -- obtinut din 9*99
propagaFold :: [Int] -> Numar

-- Adrian: Practic vrem rezultatul de la propagaFold' dar cu primul element din pereche
--         bagat in lista
propagaFold lista_cifre =
    let (rest, lista) = propagaFold' lista_cifre in
    (rest:lista)

-- 4a (0,75p). Scrieti o functie care primeste ca argument doua liste de cifre
-- *de lungime egala* cs1 si cs2, si calculeaza lista de intregi cs
-- cu proprietatea ca pentru toti i, cs !! i == cs1 !! i + cs2 !! i
-- 
-- E.g., [7,2,3] `plusLista` [4,5,7] = [11,7,10]
-- Folositi doar recursie si functii din categoria A

-- Adrian: Cu recursie si pattern matching
--         Trebuie adunat cifra cu cifra
plusLista :: [Cifra] -> [Cifra] -> [Int]
--         Mai intai pas terminal
plusLista [] [] = []
plusLista (head1:tail1) (head2:tail2) =
    (head1 + head2):(plusLista tail1 tail2)

-- Adrian: Se poate scrie si cu list comprehension (desi nu e permis aici)
--         Nu putem insa sa facem [x1 + x2 | x1 <- a, x2 <- b] deoarece
--         asta ne va da toate sumele posibile de 2 elemente (unul din a, altul din b)
--         trebuie termen cu termen, iar pentru asta putem folosi zip
plusLista2 a b = [x + y | (x, y) <- zip a b]

-- Adrian: Sau cu map (dar cu functie ajutatoare)
adunaPereche (x, y) = x + y
plusLista3 a b = map adunaPereche (zip a b)

-- 4b (0,25p). Scrieti o functie care primeste ca argument doua Numere
-- si calculeaza suma lor
-- E.g., [7,2,3] `plus` [4,5,7] = [1,1,8,0]
-- E.g., [7,3] `plus` [4,5,7] = [5,3,0]
plus :: Numar -> Numar -> Numar

-- Adrian: Trebuie sa aiba aceeasi lungime, si dupa le adunam (cu plusLista)
--         iar dupa la facem propagaFold sa propagam restul
plus a b = 
    let (x, y) = normalizeazaLungime (a, b) in
    let rezultat = plusLista x y in
    -- trebuie verificat daca prima cifra e 0 (ca restul sigur nu sunt)
    let (head:tail) = propagaFold rezultat in
    if head == 0 then
        tail
    else
        head:tail

-- 5a (0,75p). Scrieti o functie care primeste ca argument doua liste de cifre
-- *de lungime egala* cs1 si cs2, si calculeaza lista de intregi cs
-- cu proprietatea ca pentru toti i, cs !! i == cs1 !! i + cs2 !! i
-- E.g., [7,2,3] `minusLista` [4,5,7] = [3,-3,-4]
-- Folositi doar descrieri de liste si functii din categoriile A si B
-- Fara recursie
minusLista :: [Cifra] -> [Cifra] -> [Int]

-- Adrian: Ca la plusLista2
minusLista a b = [x - y | (x, y) <- zip a b]

-- Adrian: Cu recursie ca la plusLista (dar nu e permis aici)
minusLista2 [] [] = []
minusLista2 (head1:tail1) (head2:tail2) =
    (head1 - head2):(minusLista2 tail1 tail2)

-- Adrian: Sau cu higher-order functions (map)
scadePereche (x, y) = x - y
minusLista3 a b = map scadePereche (zip a b)

-- 5b (0,25p). Scrieti o functie care primeste ca argument doua Numere
-- si calculeaza diferenta lor, daca primul este mai mare sau egal decat al doilea.
-- In caz contrar, esueaza cu mesajul "Numere negative neimplementate"
-- E.g., [7,2,3] `minus` [4,5,7] = [2,6,6]
-- E.g., [7,3] `minus` [4,5,7]       *** Exception: Numere negative neimplementate
minus :: Numar -> Numar -> Numar

-- Adrian: Mai intai sa aruncam exceptia (folosind error)
minus a b = 
    if not (lte b a) then -- nu e corect lte a b, ar esua cand a == b
        error "Numere negative neimplementate"
    else
        let (x, y) = normalizeazaLungime (a, b) in -- le punem la aceeasi lungime
        let rezultat = minusLista x y in -- le scadem
        let rezultatcu0 = propagaFold rezultat in -- propagaFold merge si la numere negative
        dropWhile (==0) rezultatcu0 -- vrem sa ignoram 0-urile din fata (folosim dropWhile)

-- 6a (0,75p). Scrieti o functie care primeste ca argument o Cifra c si un Numar n
-- si calculeaza Numarul obtinut prin inmultirea lui n cu c.
-- E.g., 4 `mulC` [1,0,4] = [4,1,6]
-- E.g., 9 `mulC` [9,9] = [8,9,1]
-- Folositi doar functii din categoriile A, B, si C, si functiile definite mai sus.
-- Fara recursie sau descrieri de liste.
mulC :: Cifra -> Numar -> Numar

-- Adrian: Mai intai vrem sa inmultam cifra `cifra` cu fiecare cifra din `numar`
--         si peste asta sa folosim propagaFold
mulC cifra numar =
    let rezultat = map (*cifra) numar in
    dropWhile (==0) (propagaFold rezultat) -- sa eliminam 0-urile din fata

-- 6b (0,25). Scrieti o functie care primeste ca argument un Numar n 
-- si calculeaza Numarul obtinut prin inmultirea lui n cu 10.
-- E.g., mul10 [3,4,5] = [3,4,5,0]
-- E.g., mul10 [3,5] = [3,5,0]
mul10 :: Numar -> Numar

-- Adrian: adica sa adaugam 0 in coada
mul10 numar = numar ++ [0]

-- 7 (2p). Scrieti o functie care primeste ca argument doua Numere
-- si calculeaza Numarul obtinut prin imultirea celor doua numere.
-- E.g., [1,2] `mul` [5,3] = [6,3,6]
-- E.g., [9,9,9] `mul` [9,9,9] = [9,9,8,0,0,1]
-- (32 de simboluri)
mul :: Numar -> Numar -> Numar
-- Adrian: putem face cu fold
--         de exemplu: luam prima cifra si inmultim cu a doua
--         (la [1, 2] `mul` [5, 3])
--         1 `mulC` [5, 3] == [5, 3]
--         Inmultim cu 10 si adunam recursiv pe ce da mai depart
mul numar1 numar2 =
    let mulFold rezultat cifra = (mul10 rezultat) `plus` (cifra `mulC` numar1)
    in
    foldl mulFold [] numar2

-- Adrian: O alta metoda este urmatoare (cu recursie)
--         Pasul terminal e inmultirea cu 0 (care da 0)
mul2 [] numar = []
mul2 (head:tail) numar = 
    let mul10dexori numar x = if x == 0 then numar
                              else mul10dexori (mul10 numar) (x - 1)
    in
    (mul10dexori (head `mulC` numar) (length tail)) `plus` (tail `mul2` numar)

{- Catcgoria A. Functii de baza
div, mod :: Integral a => a -> a -> a
even, odd :: Integral a => a -> Bool
(+), (*), (-), (/) :: Num a => a -> a -> a
(<), (<=), (>), (>=) :: Ord => a -> a -> Bool
(==), (/=) :: Eq a => a -> a -> Bool
(&&), (||) :: Bool -> Bool -> Bool
not :: Bool -> Bool
max, min :: Ord a => a -> a -> a
isAlpha, isAlphaNum, isLower, isUpper, isDigit :: Char -> Bool
toLower, toUpper :: Char -> Char
ord :: Char -> Int
chr :: Int -> Char
Intervale
[first..], [first,second..], [first..last], [first,second..last]
-}

{- Categoria B. Funetii din biblioteci
sum, product :: (Num a) => [a] -> a        
sum [1.0,2.0,3.0] = 6.0                    
product [1,2,3,4] = 24                     

and, or :: [Bool] -> Bool
and [True,False,True] = False
or [True,False,True] = True

maximum, minimum :: (Ord a) => [a] -> a
maximum [3,1,4,2]  =  4                    
minimum [3,1,4,2]  =  1

reverse :: [a] -> [a]
reverse "goodbye" = "eybdoog"

concat :: [[a]] -> [a]                     
concat ["go","od","bye"]  =  "goodbye"     

(++) :: [a] -> [a] -> [a]
"good" ++ "bye" = "goodbye"

(!!) :: [a] -> Int -> a                    
[9,7,5] !! 1  =  7                         

length :: [a] -> Int
length [9,7,5]  =  3

head :: [a] -> a                           
head "goodbye" = 'g'                       

tail :: [a] -> [a]
tail "goodbye" = "oodbye"

init :: [a] -> [a]                         
init "goodbye" = "goodby"                  

last :: [a] -> a
last "goodbye" = 'e'

takeWhile :: (a->Bool) -> [a] -> [a]       
takeWhile isLower "goodBye" = "good"       

take :: Int -> [a] -> [a]
take 4 "goodbye" = "good"

dropWhile :: (a->Bool) -> [a] -> [a]       
dropWhile isLower "goodBye" = "Bye"        

drop :: Int -> [a] -> [a]
drop 4 "goodbye" = "bye"

elem :: (Eq a) => a -> [a] -> Bool         
elem 'd' "goodbye" = True                  

replicate :: Int -> a -> [a]
replicate 5 '*' = "*****"

zip :: [a] -> [b] -> [(a,b)]
zip [1,2,3,4] [1,4,9] = [(1,1),(2,4),(3,9)
-}

{- Categoria C. Map, Filter, Fold
map :: (a -> b) -> [a] -> [b]
map (+3) [1,2] = [4,5]

filter :: (a -> Bool) -> [a] -> [a]
filter even [1,2,3,4] = [2,4]

foldr :: (a -> b -> b) -> b -> [a] -> b
foldr max 0 [1,2,3,4] = 4

(.) :: (b -> c) -> (a -> b) -> a -> c
($) :: (a -> b) -> a -> b
(*2) . (+3) $ 7 = 20

flip :: (a -> b -> c) -> b -> a -> c
flip (-) 2 3 = 1

curry :: ((a, b) -> c) -> a -> b -> c
curry snd 1 2 = 2
uncurry :: a -> b -> c -> (a, b) -> c
uncurry (*) (3,7) = 21
-}
