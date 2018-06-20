import Data.Char
import Data.List
import Test.QuickCheck

{-
Partea 1.  Straddle Checkerboard Cipher
---------------------------------------

The straddling checkerboard is a substitution cipher.

The code
~~~~~~~~

The "key" for a straddling checkerboard is a permutation of the alphabet e.g. 'FKMCPDYEHBIGQROSAZLUTJNWVX,.', along with 2 non-zero digits, e.g., 3 and 7. A straddling checkerboard is set up something like this (using the key information above):

   0 1 2 3 4 5 6 7 8 9
   F K M   C P D   Y E
3: H B I G Q R O S A Z
7: L U T J N W V X , . 

The first row is set up with the first eight key letters, leaving two blank spots for the given digits. It has no row label. The second and third rows are labelled with the two given digits (which didn't get a letter in the top row), and then filled out with the rest of the key letters.

-}

type Litera = Char
type Alfabet = [Litera]

alfabetTest :: Alfabet
alfabetTest = "FKMCPDYEHBIGQROSAZLUTJNWVX,."

type Cifra = Int
type TextCodat = [Cifra]

type Cod = [(Litera,TextCodat)]

-- I.1 (1p) Folosind doar descrieri de liste si functii din categoriile A si B, 
-- si functia de mai sus (fara rescriere):
-- Scrieti o functie care data fiind o lista de cifre, in ordine crescatoare, 
-- calculeaza o lista de continand listele cifrelor  
-- corespunzand tuturor numerelor naturale de doua cifre 
-- care au ca prima cifra una din cifrele date, in ordine crescatoare
-- numere [1,3] = [[1,0], [1,1], ... , [1,9], [3,0], [3,1], ... , [3,9]]
numere :: [Cifra] -> [[Cifra]]
numere cifre = [[s,c] | s <- cifre, c <- [0..9]]

-- I.2 (2p) Folosind doar functii din categoriile A, B, si C 
-- (fara descrieri de liste sau rescriere)
-- Scrieti o functie care dat fiind un alfabet si doua cifre
-- creaza o lista de perechi (litera, cod), unde codul este o lista de 
-- cifre corespunzatoare cifrului Stradle Chcekerboard alcatuit pe baza 
--- alfabetului si cifrelor date.
--- Punctaj maxim pentru o rezolvare cu cel mult 38 de simboluri
codare :: Alfabet -> Cifra -> Cifra -> Cod
codare alfabet c1 c2 = zip alfabet $ (map (:[]) $ filter (/= c1) $ filter (/= c2) [0..9]) ++ numere [c1, c2]

-- codTest e definit ca un exemplu de cod care il puteti folosi 
-- in definitiile de mai jos.  
codTest :: Cod
codTest = codare alfabetTest 3 7
-- codTest = [('R',[0]),('A',[1]),('U',[2]),('I',[4]),('N',[5]),('E',[7]),('S',[8]),('T',[9]),('X',[3,0]),('-',[3,1]),('P',[3,2]),('O',[3,3]),('V',[3,4]),('L',[3,5]),('F',[3,6]),('M',[3,7]),('Z',[3,8]),('J',[3,9]),('C',[6,0]),('D',[6,1]),('G',[6,2]),('B',[6,3]),('W',[6,4]),('H',[6,5]),('K',[6,6]),('Y',[6,7]),('Q',[6,8]),('.',[6,9])]

{-
Partea II - Codare si Decodare
------------------------------

To encode a text, a letter on the top row is simply replaced by the number labelling its column. Letters on the other rows are replaced by their row number, then column number:

D E F E N  D T  H  E E A  S  T  W  A  L  L  O  F T  H  E C A  S  T  L  E
6 9 0 9 74 6 72 30 9 9 38 37 72 75 38 70 70 36 0 72 30 9 4 38 37 72 70 9

So, DEFEND THE EAST WALL OF THE CASTLE becomes 690975672309938377275387070360723094383772709. 

Decoding is simply the reverse of this process. Although the size of groups can vary, decoding is unambiguous because whenever the next element to be decoded starts with a 3 or a 7, it is a pair; otherwise, it is a single digit.
-}


type TextClar = [Litera]

textClarTest :: TextClar
textClarTest = "Defend the east wall of the castle"

textCodatTest :: TextCodat
textCodatTest = [6,9,0,9,7,4,6,7,2,3,0,9,9,3,8,3,7,7,2,7,5,3,8,7,0,7,0,3,6,0,7,2,3,0,9,4,3,8,3,7,7,2,7,0,9]


-- II.1 (1p) Folosind doar functii din categoriile A, B si descrieri de liste
-- (fara recursie)
-- Definiti o functie care ia ca argumente un cod si un text clar si 
-- calculeaza textul codat dupa algoritmul de mai sus.
-- codeaza codTest textClarTest == textCodatTest
codeaza :: Cod -> TextClar -> TextCodat
codeaza cod text = concat [c | l <- text, (l',c) <- cod, toUpper l == l']

-- II.2 (2p) Definiti functia care date fiind un cod si un text codat, 
-- decodeaza textul codat inversand operatiile descrise mai sus.
-- decodeaza codTest textCodatTest == textClarTest
decodeaza :: Cod -> TextCodat -> String
decodeaza _   []    = []
decodeaza cod (x:xs)
  | [l] <- decodeaza' [x] = l : decodeaza cod xs
  | (x':xs') <- xs        = decodeaza' [x,x'] ++ decodeaza cod xs'
  where decodeaza' codat = [l | (l,c) <- cod, c == codat]


{-  Partea III - Criptare / Decriptare

The coded text may be sent directly, but usually is first input into a second cipher stage, such as a substitution or transposition step. As a simple example, we will add a secret key number (say, 83729) using non-carrying addition:

  837298372983729837298372983729837298372983729
+ 690974672309938377275387070360723094383772709
= 427162944282657104463659953089550282655655428

Then use the same straddling checkerboard to turn it back into letters:

427162944282657104463659953089550282655655428
CMU DMECCMYMDPU FCCDO PEEPH YEPPFMYMDPPDPPCMY

The final ciphertext is then CMUDMECCMYMDPUFCCDOPEEPHYEPPFMYMDPPDPPCMY. Note that it is a different length compared to the original plaintext. 

-}

type Parola = [Int]

parolaTest :: Parola
parolaTest = [8,3,7,2,9]

textParolatTest :: TextCodat
textParolatTest = [4,2,7,1,6,2,9,4,4,2,8,2,6,5,7,1,0,4,4,6,3,6,5,9,9,5,3,0,8,9,5,5,0,2,8,2,6,5,5,6,5,5,4,2,8]

type TextCriptat = [Litera]

textCriptatTest :: TextCriptat
textCriptatTest = "CMUDMECCMYMDPUFCCDOPEEPHYEPPFMYMDPPDPPCMY"
-- decodeaza codTest textParolatTest == textCriptatTest

-- III.1 (2p) Date fiind o parola si un text codat
-- sa se translateze textul codat prin adunarea modulo 10
-- a fiecarei cifre cu cifra corespunzatoare din parola, 
-- repetand parola de cate ori e necesar.
-- translateaza parolaTest textCodatTest == textParolatTest
translateaza :: Parola -> TextCodat -> TextCodat
translateaza = (.concat.repeat) ((map ((`mod` 10) . uncurry (+)) .) . zip)

-- III.2 (0,5p) Date fiind un cod, o parola si un text clar,
-- sa se calculeze textul criptat obtinut prin
-- codarea textului, translatarea folosind parola, 
-- apoi decodarea textului codat obtinut, 
-- conform algoritmului de mai sus.
-- ATENTIE: In urma translatarii textul se poate termina cu o cifra 
-- care nu poate fi decodata singura.  Oferiti o solutie.
cripteaza :: Cod -> Parola -> TextClar -> TextCriptat
cripteaza cod parola textClar = criptat
  where codat = codeaza cod textClar
        parolat = translateaza parola codat
        criptat = decodeaza cod $ parolat ++ [0]

-- III.3 (0,5p) Date fiind un cod, o parola si un text criptat, 
-- sa se calculeze textul clar obtinut prin inversarea
-- operatiilor de criptare descrise mai sus.
decripteaza :: Cod -> Parola -> TextClar -> TextCriptat
decripteaza cod parola textCriptat = clar
  where codat = codeaza cod textCriptat
        deparolat = translateaza (map (0-) parola) codat
        clar = decodeaza cod $ init deparolat


-- (1p) din oficiu

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
n `lungimePlus` l = replicate l 0 ++ n

-- 1b (1p). Scrieti o functie care ia ca argument o pereche de numere
-- si calculeaza perechea de numere cu numar egal de cifre 
-- obtinuta prin adaugarea de zerouri la stanga numerelor date ca argument.
-- E.g., normalizeazaLungime ([1,2], [3,4,5,6]) = ([0,0,1,2], [3,4,5,6])
-- E.g., normalizeazaLungime ([1,2], []) = ([1,2], [0,0])
normalizeazaLungime :: (Numar, Numar) -> (Numar, Numar)
normalizeazaLungime (n1, n2) = (n1 `lungimePlus` (l2 - l1), n2 `lungimePlus` (l1-l2))
  where
    l1 = length n1
    l2 = length n2

-- 2a (0,75p). Scrieti o functie care ia doua Numere *de aceeasi lungime* ca argumente
-- si verifica daca primul Numar este mai mic sau egal cu al doilea.
-- Puteti folosi doar recursie si functii din categoria A
-- E.g., [1,2,3] `lteN` [1,2,1] = False
-- E.g., [0,2,3] `lteN` [1,2,1] = True
lteN :: Numar -> Numar -> Bool
[] `lteN` []           = True
(c:cs) `lteN` (c':cs') = c < c' || (c == c' && cs `lteN` cs')

-- 2b (0,25p).  Scrieti o functie care ia doua Numere ca argumente
-- si verifica daca primul Numar este mai mic sau egal cu al doilea
lte :: Numar -> Numar -> Bool
x `lte` y = x' `lteN` y'
  where
    (x',y') = normalizeazaLungime (x,y)

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
propagaFold' :: [Int] -> (Cifra, [Cifra])
propagaFold' = foldr p (0,[])
  where p c (c',n) = ((c + c') `div` 10, (c + c') `mod` 10:n)

-- 3b (0,5p).  Scrieti o functie care primeste ca argument o lista
-- de numere naturale ca la (3a) si calculeaza numarul corespunzator ei
-- obtinut prin propagarea depasirilor.
-- E.g. propagaFold [1, 1] =  [0, 1, 1]   -- obtinut din 10 + 1
-- E.g. propagaFold [1, 10] = [0, 2, 0]   -- obtinut din 19 + 1
-- E.g. propagaFold [10, 1] = [1, 1, 1]   -- obtinut din 30 + 71
-- E.g. propagaFold [81, 81] = [8, 9, 1]  -- obtinut din 9*99
propagaFold :: [Int] -> Numar
propagaFold n =  if c == 0 then n' else c:n'
  where (c,n') = propagaFold' n

-- 4a (0,75p). Scrieti o functie care primeste ca argument doua liste de cifre
-- *de lungime egala* cs1 si cs2, si calculeaza lista de intregi cs
-- cu proprietatea ca pentru toti i, cs !! i == cs1 !! i + cs2 !! i
-- 
-- E.g., [7,2,3] `plusLista` [4,5,7] = [11,7,10]
-- Folositi doar recursie si functii din categoria A
plusLista :: [Cifra] -> [Cifra] -> [Int]
plusLista [] [] = []
plusLista (cx:cxs) (cy:cys) = (cx + cy): cxs `plusLista` cys

-- 4b (0,25p). Scrieti o functie care primeste ca argument doua Numere
-- si calculeaza suma lor
-- E.g., [7,2,3] `plus` [4,5,7] = [1,1,8,0]
-- E.g., [7,3] `plus` [4,5,7] = [5,3,0]
plus x y = propagaFold $ uncurry plusLista $ normalizeazaLungime (x,y)

-- 5a (0,75p). Scrieti o functie care primeste ca argument doua liste de cifre
-- *de lungime egala* cs1 si cs2, si calculeaza lista de intregi cs
-- cu proprietatea ca pentru toti i, cs !! i == cs1 !! i + cs2 !! i
-- E.g., [7,2,3] `minusLista` [4,5,7] = [3,-3,-4]
-- Folositi doar descrieri de liste si functii din categoriile A si B
-- Fara recursie
minusLista :: [Cifra] -> [Cifra] -> [Int]
x `minusLista` y = [cx - cy | (cx,cy) <- zip x y]

-- 5b (0,25p). Scrieti o functie care primeste ca argument doua Numere
-- si calculeaza diferenta lor, daca primul este mai mare sau egal decat al doilea.
-- In caz contrar, esueaza cu mesajul "Numere negative neimplementate"
-- E.g., [7,2,3] `minus` [4,5,7] = [2,6,6]
-- E.g., [7,3] `minus` [4,5,7]       *** Exception: Numere negative neimplementate
minus x y
  | y' `lteN` x'
  = propagaFold $ minusLista x' y'
  | otherwise
  = error "Numere negative neimplementate"
  where (x', y') = normalizeazaLungime (x,y)

-- 6a (0,75p). Scrieti o functie care primeste ca argument o Cifra c si un Numar n
-- si calculeaza Numarul obtinut prin inmultirea lui n cu c.
-- E.g., 4 `mulC` [1,0,4] = [4,1,6]
-- E.g., 9 `mulC` [9,9] = [8,9,1]
-- Folositi doar functii din categoriile A, B, si C, si functiile definite mai sus.
-- Fara recursie sau descrieri de liste.
mulC :: Cifra -> Numar -> Numar
c `mulC` n = propagaFold $ map (*c) n

-- 6b (0,25). Scrieti o functie care primeste ca argument un Numar n 
-- si calculeaza Numarul obtinut prin inmultirea lui n cu 10.
-- E.g., mul10 [3,4,5] = [3,4,5,0]
-- E.g., mul10 [3,5] = [3,5,0]
mul10 :: Numar -> Numar
mul10 n = n ++ [0]

-- 7 (2p). Scrieti o functie care primeste ca argument doua Numere
-- si calculeaza Numarul obtinut prin imultirea celor doua numere.
-- E.g., [1,2] `mul` [5,3] = [6,3,6]
-- E.g., [9,9,9] `mul` [9,9,9] = [9,9,8,0,0,1]
-- (32 de simboluri)
mul :: Numar -> Numar -> Numar
mul x = fst . foldr m ([],x)
  where m c (p,x0) = (c `mulC` x0 `plus` p, mul10 x0)


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

{-
1.
Daca toate perechile de numere pare
dintr-o lista au produsul elementelor mai mare decat 10

> prodMare [(2, 8), (1, 5), (10, 10)]
True  -- 16, (1 si 5 sunt impare), 100

> prodMare [(0, 4), (100, 5)]
False  -- 0, (5 este impar)
-}
prodMare, prodMare', prodMareHOF, prodMareHOF2 :: [(Int, Int)] -> Bool
prodMare l = and [f * s > 10 | (f, s) <- l, even f, even s]

prodMare' [] = True
prodMare' ((f,s):r)
  | even f && even s = f * s > 10 && prodMare' r
  | otherwise        = False

prodMareHOF = filter (even . fst) >>> filter (even . snd) >>> filter ((>10) . uncurry (*)) >>> length >>> (>0)
prodMareHOF2 = all (\(a, b) -> even a && even b && a * b > 10)


{-
2.
Cate vocale exista intre doua litere date

> vocaleIntre 'a' 'f'
2  -- a si e
-}
vocaleIntre, vocaleIntre', vocaleIntre'' :: Char -> Char -> Int
vocaleIntre l1 l2 = length (filter (`elem` "aeiou") [l1..l2])

vocaleIntre' l1 l2 = helper [l1..l2]
  where helper "" = 0
        helper (c:cs)
          | c `elem` "aeiou"   = 1 + helper cs
          | otherwise          = helper cs

vocaleIntre'' l1 l2 = length [c | c <- [l1..l2], c `elem` "aeiou"]



{-
3.
Dispersia unui vector - diferenta la patrat a fiecarui element fata de medie, insumate

> dispersie [0, 2, 4, 6]
20.0  -- (media = 3) ~> [0-3, 2-3, 4-3, 6-3] ~> [-3^2, -1^2, 1^2, 3^2] ~> 20
-}
dispersie :: [Float] -> Float
dispersie v = sum [(x - m)^2 | x<-v]
  where m = sum v / fromIntegral (length v)



{-
> parseInt "123"
123
-}
parseInt :: String -> Int
parseInt inp
  | all isDigit inp  = helper (reverse inp) 0
  | otherwise        = error "The string is not made only of digits"
    where helper "" _ = 0
          helper (c:cs) pow = (digitToInt c * 10^pow) + helper cs (pow+1)
{-
4.
Totalul valorilor obtinute prin inmultirea cifrelor din fiecare numar mai mare decat 10
Hint1: puteti folosi functia words. ex: words "ab cd ef" = ["ab", "cd", "ef"]
Hint2: puteti folosi functia parseInt

> f "Am 3 mere, 25 pere, 31 banane si 100 prune"
13  -- (3 ignorat) + 2*5 + 3*1 + 1*0*0 ~> 10 + 3 + 0 ~> 13

-}
f :: String -> Int
f = words >>> filter (all isDigit) >>> filter (parseInt >>> (>10)) >>> map prodOfDigits >>> sum
  where prodOfDigits = map digitToInt >>> product


import Data.Char
import Data.List
import Data.Tuple

-- In acest test vom implementa un sistem simplu de sugestii pentru autocomplete / search de cuvinte
dict = ["cat", "catering", "caterpillar", "carbon", "sea", "seashore", "seagull", "season", "severe"]

-- 1.1 (1p) (Puteti folosi functii din categoria A si B)
-- Scrieti o functie care primeste un intreg - pozitia cuvantului din dictionar
-- si returneaza lungimea acestuia, sau eroare daca pozitia depaseste limitele tabloului
-- exemplu: ll 0 == 3
ll :: Int -> Int
ll poz = if poz >= 0 && poz < length dict 
         then length (dict !! poz)
         else error "index out of bound"


-- 1.2 (1p) (Puteti folosi functii din categoria A si B)
-- Scrieti o functie care primeste ca argumente doua perechi de intregi de forma
-- (Int, Int) cu semnificatia (Rating, pozitia in dictionar)
-- Si returneaza 1, daca prima pereche este mai mare sau egala decat a doua, si 0 in caz contrar
-- O pereche se considera mai mare decat cealalta daca Ratingul primei este mai mare, 
-- sau in caz de egalitate a ratingurilor, lungimea primului cuvant (cel dat de indicele din pereche) 
-- este mai mica decat lungimea cuvantului dat de indicele din a doua pereche.
-- Exemplu:
-- comp (4, 5) (3, 1) == 1   -- ratingul primului e mai mare
-- comp (4, 0) (4, 1) == 1   -- rating egal dar lungimea lui dict[0] <= lungimea lui dict[1]
-- comp (3, 0) (5, 2) == 0   -- rating primului mai mic
comp :: (Int, Int) -> (Int, Int) -> Int
comp (a1,b1) (a2,b2) | a1 > a2  || (a1 == a2 && ll b1 <= ll b2) = 1
                     | otherwise = 0

-- 2.1  (2p) (Folosing recursivitate si (daca aveti nevoie) functiile ajutatoare si cele din categoria A si B)
--      Scrieti o functie care calculeaza gradul de similaritate a doua cuvinte (rating)
--      dupa regulile urmatoare: 
--      1. Se suprapune litera cu litera un cuvant peste celalalt
--      2. Se formeaza secvente continue de lungime maximala (l1, l2, ... lk) a literelor ce coincid
--      3. Ratingul va fi suma = 2^li + 2^l2 + .. + 2^lk
-- exemplu:
--   rating "sebshote" "seashore" == 14
--  l = lungimea maximala a secventei
-- sum = suma formata din 2^lungimea maximala pentru fiecare secventa
--   s   |  s e b s h o t e
--   q   |  s e a s h o r e
--   l   |  1 2 0 1 2 3 0 1
--  sum  |  _ 4 _ _ _ 8 _ 2  = 14
-- se formeaza 3 secvente continue de lungimi maximale 2,3,1
-- suma fiind 2^2 + 2^3 + 2^1 = 4 + 8 + 2 = 14

--  rating "acat" "bcat" == 8
--  rating "coconut" "coroutine" == 6
gg :: Int -> Int
gg k = if k == 0 then 0 else 2^k 

ratingF :: String -> String -> Int -> Int -> Int
ratingF _ [] k sm = sm + gg k
ratingF [] _ k sm = sm + gg k
ratingF (s:ss) (q:qs) k sm | s == q = ratingF ss qs (k+1) sm
                           | otherwise = ratingF ss qs 0 sm + gg k
                          
rating :: String -> String -> Int
rating s q = ratingF s q 0 0

-- 2.2   (2p) (Folosind list comprehension si functiile din categoriile A si B) 
--        Scrieti o functie care returneaza ratingurile tuturor posibilitatilor de a suprapune primul cuvant peste celalalt 
--        prin shiftarea la dreapta a primului cuvant peste al doilea
-- exemplu: 
-- allOverlapsRatings "acat" "acab" == [8,0,2,0]
--             |acat    | acat     |  acab    |   acab
--             |acab    |acab      |acab      |acab
-- ratingurile   8        0            2          0

-- allOverlapsRatings "cactus" "picartous" == [2,0,6,4,0,0,0,0,0]
-- allOverlapsRatings "coconut" "coroutine" == [6,0,2,2,0,0,0,0,0]
allOverlapsRatings :: String -> String -> [Int]
allOverlapsRatings s q = [rating s (drop i q) | i <- [0..(length q)-1]]

-- 3  (2p) (Folosind functiile din categoriile A,B,C si cele definite mai sus, 
--      dar fara recursie/list comprehension) 
--        Scrieti o functie care returneaza pentru un cuvant s, 
--        lista cu ratingurile maxime de similaritate (prin suprapunere + shiftare la dreapta)
--        intre s si fiecare dintre cuvintele din dictionar 
-- exemplu:
--   suggestionsRating "tea" == [2,4,4,0,4,4,4,4,2]
--   cu semnificatia ca maximul din allOverlapsRatings "tea" "cat" este 2 ("cat" fiind primul cuvant din dictionar)
--   continuand,   maximul din allOverlapsRatings "tea" "catering" este 4 ("catering" fiind primul cuvant din dictionar)
--   etc.
-- teste aditionale:
-- suggestionsRating "shark" == [0,2,4,0,4,6,4,4,2]
-- suggestionsRating "dolphin" == [0,4,2,2,0,2,2,2,0]
-- (2p) (18 caractere)
suggestionsRating :: String -> [Int]
suggestionsRating s = map (foldr (max) 0) $ map (allOverlapsRatings s) dict

 -- helper function
ff :: String -> [(Int,Int)]
ff s = (suggestionsRating s) `zip` [0..]
-- ff "seash" == [(0,0),(2,1),(2,2),(0,3),(8,4),(32,5),(8,6),(16,7),(4,8)]

-- 4.1 (1p) (Fara restrictii, puteti folosi orice)
--      Scrieti o functie care insereaza o pereche de forma (Int, Int)
--      Intr-un tablou de astfel de perechi, sortate crescator dupa functia 
--      de comparatie comp (definita de voi mai sus)
-- helper variable
yy :: [(Int, Int)]
yy = [(2,0), (2,3), (2,1), (4,2), (5,4)]
-- exemplu: insertPg (4,7) yy == [(2,0), (2,3), (2,1), (4,2), (4,7), (5,4)]
--          insertPg (3,7) yy == [(2,0), (2,3), (2,1), (3,7), (4,2), (5,4)]

insertp :: (Int,Int) -> [(Int,Int)] -> [(Int,Int)] -> [(Int,Int)]
insertp v [] b = b ++ [v] 
insertp v (x:xs) b | comp x v == 1 = b ++ ([v,x] ++ xs)
                   | otherwise = insertp v (xs) (b ++ [x])

insertPg :: (Int,Int) -> [(Int,Int)] -> [(Int,Int)]
insertPg v xs = insertp v xs []

-- 4.2 (1p) (Folosind functia insertPg si recursivitate)
--    Scrieti o functie care sorteaza un sir de perechi crescator dupa 
--    functia de comparatie comp (definita de voi mai sus)
sortRatingsP :: [(Int,Int)] -> [(Int,Int)]
sortRatingsP [] = []
sortRatingsP (x:xs) = insertPg x $ sortRatingsP xs


-- 4.3 (1p Bonus)
--     (Folosind functiile din categoria A,B,C, si functiile definite anterior de voi
--       dar fara recursie / list comprehension)
--       Scrieti o functie care va returna o lista cu cuvintele din dictionar
--       in ordinea descrescatoare a ratingului lor fata de cuvantul s(dat ca argument de intrare)
--       dar fara cuvintele cu care au similaritate zero.
getBestSugestions :: String -> [String]
getBestSugestions s = map (\(x,y) -> dict !! y) (reverse . sortRatingsP . (filter (\p -> fst p > 0)) $ ff s)

-- teste:
-- getBestSugestions "cactus" == ["cat", "carbon", "catering", "caterpillar", "sea", "season", "seagull", "seashore"]
-- getBestSugestions "seal" == ["sea", "season", "seagull", "seashore", "severe", "catering", "caterpillar"]
-- getBestSugestions "seven" == ["severe", "sea", "season", "seagull", "seashore", "catering", "carbon", "caterpillar"]

-- zip (map (^2) a) (map (^2) b)
{- Categoria A. Funetii de baza
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
digitToInt :: Char -> Int
ord :: Char -> Int
chr :: Int -> Char
-}


{- Categoria B. Functii din biblioteci
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
takeWhile isLower "goodDay" = "good"


take :: Int -> [a] -> [a]
take 4 "goodbye" = "good"


dropWhile :: (a->Bool) -> [a] -> [a]
dropWhile isLower "goodBye" = "Bye"


drop :: Int -> [a] -> [a]
drop 4 "goodbye" = "bye"


elem :: (Eq a) => a -> [a] -> Bool
elem 'd' "gdbye" = True


replicate :: Int -> a -> [a]
replicate 5 '*' = "*****"


zip :: [a] -> [b] -> [(a,b)]
zip [1,2,3,4] [1,4,9] = [(1,1),(2,4),(3,9)


-}


{- Catgoria C. Map, Filter, Fold
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
-}


import Data.Char
import Data.List

-- In acest test vom implementa un sistem simplu de sugestii pentru autocomplete / search de cuvinte
dict = ["cat", "catering", "caterpillar", "carbon", "sea", "seashore", "seagull", "season", "severe"]

-- 1.1 (1p) (Puteti folosi functii din categoria A si B)
-- Scrieti o functie care primeste un intreg - pozitia cuvantului din dictionar
-- si returneaza lungimea acestuia, sau eroare daca pozitia depaseste limitele tabloului
-- exemplu: ll 0 == 3
ll :: Int -> Int
ll poz = if poz >= 0 && poz < length dict 
         then length (dict !! poz)
         else error "index out of bound"


-- 1.2 (1p) (Puteti folosi functii din categoria A si B)
-- Scrieti o functie care primeste ca argumente doua perechi de intregi de forma
-- (Int, Int) cu semnificatia (Rating, pozitia in dictionar)
-- Si returneaza True, daca prima pereche este mai mare sau egala decat a doua, si False in caz contrar
-- O pereche se considera mai mare decat cealalta daca Ratingul primei este mai mare, 
-- sau in caz de egalitate a ratingurilor, lungimea primului cuvant (cel dat de indicele din pereche) 
-- este mai mica decat lungimea cuvantului dat de indicele din a doua pereche.
-- Exemplu:
-- comp (4, 5) (3, 1) == True   -- ratingul primului e mai mare
-- comp (4, 0) (4, 1) == True   -- rating egal dar lungimea lui dict[0] <= lungimea lui dict[1]
-- comp (3, 0) (5, 2) == False   -- rating primului mai mic
comp :: (Int, Int) -> (Int, Int) -> Bool
comp (a1,b1) (a2,b2) | a1 > a2  || (a1 == a2 && ll b1 <= ll b2) = True
                     | otherwise = False

-- 2.1  (2p) (Folosing recursivitate si (daca aveti nevoie) functiile ajutatoare si cele din categoria A si B)
--      Scrieti o functie care calculeaza gradul de similaritate a doua cuvinte (rating)
--      dupa regulile urmatoare: 
--      1. Se suprapune litera cu litera un cuvant peste celalalt
--      2. Se parcurge de la stanga la dreapta si se aduna lungimea secventei continue de litere egale(doua cate doua din s si q)
--         care se termina in caracterul curent. Daca la un moment dat caracterele nu coincid, se reseteaza
--         lungimea secventei (se incepe de la 0 din nou)
--      3. Ratingul va fi suma lungimilor fiecarei dintre aceste secvente
-- exemplu:
--   rating "sebshote" "seashore" == 14
--  l = lungimea curenta a secventei de litere egale (doua cate doua, din s si q)
-- sum = suma formata prin adunarea lungimilor fiecarei dintre secvente
--   s   |  s e b s h o t e
--   q   |  s e a s h o r e
--   l   |  1 2 0 1 2 3 0 1
--  sum  |  1 3 3 4 6 9 9 10  = 10 (rezultatul final, suma lungimilor, a randului de mai sus notat cu l)

--  rating "acat" "bcat" == 6
--  rating "coconut" "coroutine" == 4
ratingF :: String -> String -> Int -> Int
ratingF _ [] f = 0
ratingF [] _ f = 0
ratingF (s:ss) (q:qs) f | s == q = ratingF ss qs (f+1) + f + 1
                        | otherwise = ratingF ss qs 0
                          
rating :: String -> String -> Int
rating s q = ratingF s q 0

-- 2.2   (2p) (Folosind list comprehension si functiile din categoriile A si B) 
--        Scrieti o functie care returneaza ratingurile tuturor posibilitatilor de a suprapune primul cuvant peste celalalt 
--        prin shiftarea la dreapta a primului cuvant peste al doilea
-- exemplu: 
-- allOverlapsRatings "acat" "acab" == [6,0,1,0]
--             |acat    | acat     |  acat    |   acat
--             |acab    |acab      |acab      |acab
-- ratingurile   6        0            1          0
-- dupa cum se vede in imagine este echivalent cu a verifica similaritatea dintre
--  "acat" si "acab", 
--  "acat" cu "cab", 
--  "acat" cu "ab", 
--  "acat" ci "b"

-- allOverlapsRatings "cactus" "picartous" == [1,0,4,3,0,0,0,0,0]
-- allOverlapsRatings "coconut" "coroutine" == [4,0,1,1,0,0,0,0,0]
allOverlapsRatings :: String -> String -> [Int]
allOverlapsRatings s q = [rating s (drop i q) | i <- [0..(length q)-1]]

-- 3  (1p) (Folosind functiile din categoriile A,B,C si cele definite mai sus, 
--      dar fara recursie/list comprehension) 
--        Scrieti o functie care returneaza pentru un cuvant s, 
--        lista cu ratingurile maxime de similaritate (prin suprapunere + shiftare la dreapta)
--        intre s si fiecare dintre cuvintele din dictionar 
-- exemplu:
--   suggestionsRating "tea" == [1,3,3,0,3,3,3,3,1]
--   cu semnificatia ca maximul din allOverlapsRatings "tea" "cat" este 1 ("cat" fiind primul cuvant din dictionar)
--   continuand,   maximul din allOverlapsRatings "tea" "catering" este 3 ("catering" fiind al doilea cuvant din dictionar)
--   etc.
-- teste aditionale:
-- suggestionsRating "shark" == [0,1,3,0,2,4,2,2,1]
-- suggestionsRating "dolphin" == [0,3,1,1,0,1,1,1,0]
-- (1p) (18 caractere)
suggestionsRating :: String -> [Int]
suggestionsRating s = map (foldr (max) 0) $ map (allOverlapsRatings s) dict

-- 4.1 (1p) (Fara restrictii, puteti folosi orice)
--      Scrieti o functie care insereaza o pereche de forma (Int, Int)
--      Intr-un tablou de astfel de perechi, sortate crescator dupa functia 
--      de comparatie comp (definita de voi mai sus) 
--      (complexitatea O(N) e suficient (ca nu cumva sa va apucati sa faceti cautare binara :) ))
-- helper variable / constant function
yy :: [(Int, Int)]
yy = [(2,0), (2,3), (2,1), (4,2), (5,4)]
-- exemplu: insertPg (4,7) yy == [(2,0), (2,3), (2,1), (4,2), (4,7), (5,4)]
--          insertPg (3,7) yy == [(2,0), (2,3), (2,1), (3,7), (4,2), (5,4)]

insertp :: (Int,Int) -> [(Int,Int)] -> [(Int,Int)] -> [(Int,Int)]
insertp v [] b = b ++ [v] 
insertp v (x:xs) b | comp x v = b ++ ([v,x] ++ xs)
                   | otherwise = insertp v (xs) (b ++ [x])

insertPg :: (Int,Int) -> [(Int,Int)] -> [(Int,Int)]
insertPg v xs = insertp v xs []

-- 4.2 (1p) (Folosind functia insertPg si recursivitate)
--    Scrieti o functie care sorteaza un sir de perechi crescator dupa 
--    functia de comparatie comp (definita de voi mai sus)
sortRatingsP :: [(Int,Int)] -> [(Int,Int)]
sortRatingsP [] = []
sortRatingsP (x:xs) = insertPg x $ sortRatingsP xs

-- test:
-- sortRatingsP $ reverse yy == [(2,1),(2,3),(2,0),(4,2),(5,4)]

 -- helper function
ff :: String -> [(Int,Int)]
ff s = (suggestionsRating s) `zip` [0..]
-- ff "seash" == [(0,0),(2,1),(2,2),(0,3),(8,4),(32,5),(8,6),(16,7),(4,8)]

-- 4.3 (1p Bonus)
--     (Folosind functiile din categoria A,B,C, si functiile definite anterior de voi
--       dar fara recursie / list comprehension)
--       Scrieti o functie care va returna o lista cu cuvintele din dictionar
--       in ordinea descrescatoare a ratingului lor fata de cuvantul s(dat ca argument de intrare)
--       dar fara cuvintele cu care au similaritate zero.
getBestSugestions :: String -> [String]
getBestSugestions s = map (\(x,y) -> dict !! y) (reverse . sortRatingsP . (filter (\p -> fst p > 0)) $ ff s)

-- teste:
-- getBestSugestions "cactus" == ["cat", "carbon", "catering", "caterpillar", "sea", "season", "seagull", "seashore"]
-- getBestSugestions "seal" == ["sea", "season", "seagull", "seashore", "severe", "catering", "caterpillar"]
-- getBestSugestions "seven" == ["severe", "sea", "season", "seagull", "seashore", "catering", "carbon", "caterpillar"]

-- zip (map (^2) a) (map (^2) b)
{- Categoria A. Funetii de baza
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
digitToInt :: Char -> Int
ord :: Char -> Int
chr :: Int -> Char
-}


{- Categoria B. Functii din biblioteci
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
takeWhile isLower "goodDay" = "good"


take :: Int -> [a] -> [a]
take 4 "goodbye" = "good"


dropWhile :: (a->Bool) -> [a] -> [a]
dropWhile isLower "goodBye" = "Bye"


drop :: Int -> [a] -> [a]
drop 4 "goodbye" = "bye"


elem :: (Eq a) => a -> [a] -> Bool
elem 'd' "gdbye" = True


replicate :: Int -> a -> [a]
replicate 5 '*' = "*****"


zip :: [a] -> [b] -> [(a,b)]
zip [1,2,3,4] [1,4,9] = [(1,1),(2,4),(3,9)


-}


{- Catgoria C. Map, Filter, Fold
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
-}

import Data.Char
import Test.QuickCheck

-- primele 10 numere prime, mai putin 23,31,33,37 peste care s-a sarit din cauza coliziunilor
-- indexarea in tablou este de la 0 la 9
prime = [2,3,5,7,11,13,17,19,29,39]


-- 1.  (1p) Scrieti o functie care primind numar, returneaza True daca el se gaseste in
--     lista de numere prime de mai sus
--     Puteti folosi orice.
--     Exemple:
--     Prim 3 == True
--     Prim 4 == False
prim :: Int -> Bool
prim x = elem x prime

-- 2.  (2p) Folosind recursivitate si functiile din clasa A
-- 	scrieti o functie care converteste un Int la string
-- 	puteti sa va definiti functii ajutatoare care respecta aceleasi conditii (recursivitate si functii din clasa A)
-- 	exemplu:
-- 	numToString 452 == "452"
numToString2 :: Int -> Int -> String
numToString2 0 r = [chr(r + 48)]
numToString2 q r = (numToString2 (div q 10) (mod q 10)) ++ [chr (r + 48)]

numToString :: Int -> String
numToString x = numToString2 (div x 10) (mod x 10)


-- 3.a)  (2p) Folositi doar list comprehension, si functiile definite anterior + functiile din categoria A, B
-- 	convertiti un numar la codificarea sa cu numere prime care se obtine prin inlocuirea fiecarei cifre c
--  din numarul dat cu elementul de pe pozitia c din tabloul prime (si concatenarea tuturor codificarilor
--  in ordinea cifrelor din numar, adica de la stanga la dreapta)
-- 	exemple:
-- 	enc 6 == "17"
-- 	enc 802 == "2325"     "23" + "2" + "5"
-- 	enc 1239 == "35729"   "3" + "5" + "7" + "29"
-- 	enc 9876543210 == "2923191713117532"  
enc :: Int -> String
enc n = concat [numToString (prime !! (ord x - 48)) | x <- numToString n]

-- 3.b) (2p) Primiti un sir de numere (semnal), printre care unele sunt rebundante (zgomot, erori
-- de transmisie) care sunt notate prin numere negative. Se vrea eliminarea erorilor din cadrul
-- mesajului transmis, si formarea unei codificari de numere prime, reprezentata printr-un singur
-- sir (string) format din alipirea codificarilor individuale ale fiecarui numar din mesaj, in
-- ordinea in care acestea apar in tablou (evident, fara cele care sunt zgomot)
-- (Folositi doar functiile din categoria C si functia enc de mai sus)
-- (16 simboluri)
-- exemplu:
-- encmsg [1,2,3,-3,91,-1,24,-2,13] == "35731351137"
encmsg :: [Int] -> String
encmsg = foldr (++) "" . map enc . filter (>=0)

-- 4.a) (1p)  Scrieti o functie care primeste un intreg ca parametru
--            Si returneaza pozitia lui in tabelul prime (indicele), daca acest numar face parte din lista prime
--            In caz contrar, returnati -1

v = prime `zip` [0..]

elm :: Int -> Int
elm e = if length t > 0 then head t else -1
        where t = [s|(f,s) <- v, f == e]

-- 4.b) (2p)  Scrieti o functie care decodifica un mesaj codificat, si returneaza numarul initial
--          din care s-a obtinut mesajul codificat. In caz de codificare gresita, returnati
--          eroare cu mesajul "cirfu incorect"
--          Puteti folosi orice si oricate functii ajutatoare

dec2 :: String -> Int -> Int
dec2 [] v = v
dec2 (x:[]) v = v*10 + elm (digitToInt x)
dec2 (x:y:xs) v | prim w == True = dec2 xs (v*10 + elm w)
                | prim s == True = dec2 (y:xs) (v*10 + elm s)
                | otherwise = error "cifru incorect"
                where s = (digitToInt x)
                      t = (digitToInt y)
                      w = (s*10) + t

dec :: String -> Int
dec s = dec2 s 0

-- Pentru a va testa functia de decriptare
-- cu QuickCheck
prop_dec :: Int -> Bool
prop_dec x = abs x == dec (enc (abs x))
-- trebuie sa va dea:
-- *Main> quickCheckprop_dec
-- +++ OK, passed 100 tests.


{- Categoria A. Funetii de baza
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
digitToInt :: Char -> Int
ord :: Char -> Int
chr :: Int -> Char
-}


{- Categoria B. Functii din biblioteci
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
takeWhile isLower "goodDay" = "good"


take :: Int -> [a] -> [a]
take 4 "goodbye" = "good"


dropWhile :: (a->Bool) -> [a] -> [a]
dropWhile isLower "goodBye" = "Bye"


drop :: Int -> [a] -> [a]
drop 4 "goodbye" = "bye"


elem :: (Eq a) => a -> [a] -> Bool
elem 'd' "gdbye" = True


replicate :: Int -> a -> [a]
replicate 5 '*' = "*****"


zip :: [a] -> [b] -> [(a,b)]
zip [1,2,3,4] [1,4,9] = [(1,1),(2,4),(3,9)


-}


{- Catgoria C. Map, Filter, Fold
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
-}

import Data.Char
import Test.QuickCheck

-- primele 10 numere prime, mai putin 29 peste care s-a sarit
-- indexarea in tablou este de la 0 la 9
prime = ["2","3","5","7","11","13","17","19","23","31"]

-- 1.  (1p) Scrieti o functie care primind un caracter c
--     Returneaza elementul de pe pozitia c din lista prime, daca caracterul c este cifra
--     si eroare in cazul in care c este un oarecare alt caracter
--     Puteti folosi orice.
--     Exemple:
--     charToElem '3' == "7"

charToElem :: Char -> String
charToElem x = if isDigit x == False
               then error "index out of bounds"
               else prime !! digitToInt x

-- 2.  (2p) Folosind recursivitate si functiile din clasa A
-- 	scrieti o functie care converteste un String la Int
-- 	puteti sa va definiti functii ajutatoare care respecta aceleasi conditii (recursivitate si functii din clasa A)
-- 	exemplu:
-- 	strToInt "452" == 452
strToInt2 :: String -> Int 
strToInt2 [] = 0
strToInt2 (x:xs) = (strToInt2 xs) * 10 + digitToInt x

strToInt :: String -> Int
strToInt s = strToInt2 $ reverse s

-- 3.a)  (2p) Folositi doar list comprehension, si functiile definite anterior + functiile din categoria A, B
-- 	convertiti un numar la codificarea sa cu numere prime care se obtine prin inlocuirea fiecarui caracter/cifra c
--  din stringul dat cu elementul de pe pozitia c din tabloul prime (si concatenarea tuturor codificarilor
--  in ordinea cifrelor din numar, adica de la stanga la dreapta)
-- 	exemple:
-- 	enc "6" == "17"
-- 	enc "802" == "2325"     "23" + "2" + "5"
-- 	enc "1239" == "35731"   "3" + "5" + "7" + "31"
-- 	enc "9876543210" == "3123191713117532"  

enc :: String -> String
enc s = concat [charToElem x | x <- s]

-- 3.b) (2p) Primiti un sir de stringuri (semnal) formate doar din caractere care sunt cifre, printre care unele 
-- sunt rebundante (zgomot, erori de transmisie) care sunt notate prin siruri vide. Se vrea eliminarea erorilor 
-- din cadrul mesajului transmis, si formarea unei codificari de numere prime, reprezentata printr-un singur
-- sir (string) format din alipirea codificarilor individuale ale fiecarui string din mesaj, in ordinea in care 
-- acestea apar in tablou (evident, fara cele care sunt zgomot)
-- (Folositi functiile din categoria A,B,C si functia enc de mai sus, dar fara recursie sau list comprehension)
-- (16 simboluri) 
-- ("" se considera un singur simbol, la fel si operatorii, numele de functie sau variabila, parantezele se numara separat)
-- exemplu:
-- encmsg ["1","2","3","","91","","24","","13"] == "35731351137"
encmsg :: [String] -> String
encmsg = foldr (++) "" . map enc . filter (/="")

-- 4.a) (1p)  Scrieti o functie care primeste un String ca parametru
--            Si returneaza pozitia lui in tabelul prime (indicele), daca acest string face parte din lista prime
--            In caz contrar, returnati -1
-- exemple:
-- elm "13" == 5
-- elm "2" == 0
v = prime `zip` [0..]

elm :: String -> Int
elm e = if length t > 0 then head t else -1
        where t = [s|(f,s) <- v, f == e]


-- 4.b) (2p) Cifrul nostru nu este sigur. De exemplu codificarea "2313" poate proveni de la 
--           "2" + "3" + "13" sau "2" + "31" + "3" sau "23" + "13"
--          Scrieti o functie care pentru un mesaj codificat returneaza o lista cu toate
--          variantele posibile de decodificare, iar in caz de cifru incorect (care nu poate fi decodificat)
--          returnati sir vid ([]).
--          Puteti folosi orice si oricate functii ajutatoare

i :: Int -> Char
i c = chr(c + 48)

dec2 :: String -> String -> [String]
dec2 [] s = [s]
dec2 (x:[]) s = if elm [x] /= -1 then [s ++ [i (elm [x])]] else []
dec2 (x:y:xs) ss | elW /= -1 && elS /= -1 = dec2 xs (ss ++ [i elW]) ++ dec2 (y:xs) (ss ++ [i elS]) 
                 | elW /= -1 = dec2 xs (ss ++ [i elW]) 
                 | elS /= -1 = dec2 (y:xs) (ss ++ [i elS])
                 | otherwise = []
                 where s = (digitToInt x)
                       t = (digitToInt y)
                       w = (s*10) + t
                       elW = elm [x,y]
                       elS = elm [x]
          
dec :: String -> [String]
dec s = dec2 s ""
-- teste 
-- dec "3113" == ["95", "141"]
-- dec "231" == ["09"]
-- dec "3113237" ==  ["9583","95013","14183","141013"]
-- dec "72313" == ["385", "3091", "3015"]

{- Categoria A. Funetii de baza
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
digitToInt :: Char -> Int
ord :: Char -> Int
chr :: Int -> Char
-}


{- Categoria B. Functii din biblioteci
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
takeWhile isLower "goodDay" = "good"


take :: Int -> [a] -> [a]
take 4 "goodbye" = "good"


dropWhile :: (a->Bool) -> [a] -> [a]
dropWhile isLower "goodBye" = "Bye"


drop :: Int -> [a] -> [a]
drop 4 "goodbye" = "bye"


elem :: (Eq a) => a -> [a] -> Bool
elem 'd' "gdbye" = True


replicate :: Int -> a -> [a]
replicate 5 '*' = "*****"


zip :: [a] -> [b] -> [(a,b)]
zip [1,2,3,4] [1,4,9] = [(1,1),(2,4),(3,9)


-}


{- Catgoria C. Map, Filter, Fold
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
-}


import Data.Char
import Test.QuickCheck

-- primele 10 numere prime, mai putin 29 peste care s-a sarit
-- indexarea in tablou este de la 0 la 9
prime = ["2","3","5","7","11","13","17","19","23","31"]

-- 1.  (1p) Scrieti o functie care primind un caracter c
--     Returneaza elementul de pe pozitia c din lista prime, daca caracterul c este cifra
--     si eroare in cazul in care c este un oarecare alt caracter
--     Puteti folosi orice.
--     Exemple:
--     charToElem '3' == "7"

charToElem :: Char -> String
charToElem x = if isDigit x == False
               then error "index out of bounds"
               else prime !! digitToInt x

-- 2.  (2p) Folosind recursivitate si functiile din clasa A
-- 	scrieti o functie care converteste un String la Int
-- 	puteti sa va definiti functii ajutatoare care respecta aceleasi conditii (recursivitate si functii din clasa A)
-- 	exemplu:
-- 	strToInt "452" == 452
strToInt2 :: String -> Int 
strToInt2 [] = 0
strToInt2 (x:xs) = (strToInt2 xs) * 10 + digitToInt x

strToInt :: String -> Int
strToInt s = strToInt2 $ reverse s

-- 3.a)  (2p) Folositi doar list comprehension, si functiile definite anterior + functiile din categoria A, B
-- 	convertiti un numar la codificarea sa cu numere prime care se obtine prin inlocuirea fiecarui caracter/cifra c
--  din stringul dat cu elementul de pe pozitia c din tabloul prime (si concatenarea tuturor codificarilor
--  in ordinea cifrelor din numar, adica de la stanga la dreapta)
-- 	exemple:
-- 	enc "6" == "17"
-- 	enc "802" == "2325"     "23" + "2" + "5"
-- 	enc "1239" == "35731"   "3" + "5" + "7" + "31"
-- 	enc "9876543210" == "3123191713117532"  

enc :: String -> String
enc s = concat [charToElem x | x <- s]

-- 3.b) (2p) Primiti un sir de stringuri (semnal) formate doar din caractere care sunt cifre, printre care unele 
-- sunt rebundante (zgomot, erori de transmisie) care sunt notate prin siruri vide. Se vrea eliminarea erorilor 
-- din cadrul mesajului transmis, si formarea unei codificari de numere prime, reprezentata printr-un singur
-- sir (string) format din alipirea codificarilor individuale ale fiecarui string din mesaj, in ordinea in care 
-- acestea apar in tablou (evident, fara cele care sunt zgomot)
-- (Folositi functiile din categoria A,B,C si functia enc de mai sus, dar fara recursie sau list comprehension)
-- (16 simboluri) 
-- ("" se considera un singur simbol, la fel si operatorii, numele de functie sau variabila, parantezele se numara separat)
-- exemplu:
-- encmsg ["1","2","3","","91","","24","","13"] == "35731351137"
encmsg :: [String] -> String
encmsg = foldr (++) "" . map enc . filter (/="")

-- 4.a) (1p)  Scrieti o functie care primeste un String ca parametru
--            Si returneaza pozitia lui in tabelul prime (indicele), daca acest string face parte din lista prime
--            In caz contrar, returnati -1
-- exemple:
-- elm "13" == 5
-- elm "2" == 0
v = prime `zip` [0..]

elm :: String -> Int
elm e = if length t > 0 then head t else -1
        where t = [s|(f,s) <- v, f == e]


-- 4.b) (2p) Cifrul nostru nu este sigur. De exemplu codificarea "2313" poate proveni de la 
--           "2" + "3" + "13" sau "2" + "31" + "3" sau "23" + "13"
--          Scrieti o functie care pentru un mesaj codificat returneaza o lista cu toate
--          variantele posibile de decodificare, iar in caz de cifru incorect (care nu poate fi decodificat)
--          returnati sir vid ([]).
--          Puteti folosi orice si oricate functii ajutatoare

i :: Int -> Char
i c = chr(c + 48)

dec2 :: String -> String -> [String]
dec2 [] s = [s]
dec2 (x:[]) s = if elm [x] /= -1 then [s ++ [i (elm [x])]] else []
dec2 (x:y:xs) ss | elW /= -1 && elS /= -1 = dec2 xs (ss ++ [i elW]) ++ dec2 (y:xs) (ss ++ [i elS]) 
                 | elW /= -1 = dec2 xs (ss ++ [i elW]) 
                 | elS /= -1 = dec2 (y:xs) (ss ++ [i elS])
                 | otherwise = []
                 where s = (digitToInt x)
                       t = (digitToInt y)
                       w = (s*10) + t
                       elW = elm [x,y]
                       elS = elm [x]
          
dec :: String -> [String]
dec s = dec2 s ""
-- teste 
-- dec "3113" == ["95", "141"]
-- dec "231" == ["09"]
-- dec "3113237" ==  ["9583","95013","14183","141013"]
-- dec "72313" == ["385", "3091", "3015"]

{- Categoria A. Funetii de baza
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
digitToInt :: Char -> Int
ord :: Char -> Int
chr :: Int -> Char
-}


{- Categoria B. Functii din biblioteci
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
takeWhile isLower "goodDay" = "good"


take :: Int -> [a] -> [a]
take 4 "goodbye" = "good"


dropWhile :: (a->Bool) -> [a] -> [a]
dropWhile isLower "goodBye" = "Bye"


drop :: Int -> [a] -> [a]
drop 4 "goodbye" = "bye"


elem :: (Eq a) => a -> [a] -> Bool
elem 'd' "gdbye" = True


replicate :: Int -> a -> [a]
replicate 5 '*' = "*****"


zip :: [a] -> [b] -> [(a,b)]
zip [1,2,3,4] [1,4,9] = [(1,1),(2,4),(3,9)


-}


{- Catgoria C. Map, Filter, Fold
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
-}


import Data.Char
import Test.QuickCheck

-- primele 10 numere prime, mai putin 23,31,33,37 peste care s-a sarit din cauza coliziunilor
-- indexarea in tablou este de la 0 la 9
prime = [2,3,5,7,11,13,17,19,29,39]


-- 1.  (1p) Scrieti o functie care primind numar, returneaza True daca el se gaseste in
--     lista de numere prime de mai sus
--     Puteti folosi orice.
--     Exemple:
--     Prim 3 == True
--     Prim 4 == False
prim :: Int -> Bool
prim x = elem x prime

-- 2.  (2p) Folosind recursivitate si functiile din clasa A
-- 	scrieti o functie care converteste un Int la string
-- 	puteti sa va definiti functii ajutatoare care respecta aceleasi conditii (recursivitate si functii din clasa A)
-- 	exemplu:
-- 	numToString 452 == "452"
numToString2 :: Int -> Int -> String
numToString2 0 r = [chr(r + 48)]
numToString2 q r = (numToString2 (div q 10) (mod q 10)) ++ [chr (r + 48)]

numToString :: Int -> String
numToString x = numToString2 (div x 10) (mod x 10)


-- 3.a)  (2p) Folositi doar list comprehension, si functiile definite anterior + functiile din categoria A, B
-- 	convertiti un numar la codificarea sa cu numere prime care se obtine prin inlocuirea fiecarei cifre c
--  din numarul dat cu elementul de pe pozitia c din tabloul prime (si concatenarea tuturor codificarilor
--  in ordinea cifrelor din numar, adica de la stanga la dreapta)
-- 	exemple:
-- 	enc 6 == "17"
-- 	enc 802 == "2325"     "23" + "2" + "5"
-- 	enc 1239 == "35729"   "3" + "5" + "7" + "29"
-- 	enc 9876543210 == "2923191713117532"  
enc :: Int -> String
enc n = concat [numToString (prime !! (ord x - 48)) | x <- numToString n]

-- 3.b) (2p) Primiti un sir de numere (semnal), printre care unele sunt rebundante (zgomot, erori
-- de transmisie) care sunt notate prin numere negative. Se vrea eliminarea erorilor din cadrul
-- mesajului transmis, si formarea unei codificari de numere prime, reprezentata printr-un singur
-- sir (string) format din alipirea codificarilor individuale ale fiecarui numar din mesaj, in
-- ordinea in care acestea apar in tablou (evident, fara cele care sunt zgomot)
-- (Folositi doar functiile din categoria C si functia enc de mai sus)
-- (16 simboluri)
-- exemplu:
-- encmsg [1,2,3,-3,91,-1,24,-2,13] == "35731351137"
encmsg :: [Int] -> String
encmsg = foldr (++) "" . map enc . filter (>=0)

-- 4.a) (1p)  Scrieti o functie care primeste un intreg ca parametru
--            Si returneaza pozitia lui in tabelul prime (indicele), daca acest numar face parte din lista prime
--            In caz contrar, returnati -1

v = prime `zip` [0..]

elm :: Int -> Int
elm e = if length t > 0 then head t else -1
        where t = [s|(f,s) <- v, f == e]

-- 4.b) (2p)  Scrieti o functie care decodifica un mesaj codificat, si returneaza numarul initial
--          din care s-a obtinut mesajul codificat. In caz de codificare gresita, returnati
--          eroare cu mesajul "cirfu incorect"
--          Puteti folosi orice si oricate functii ajutatoare

dec2 :: String -> Int -> Int
dec2 [] v = v
dec2 (x:[]) v = v*10 + elm (digitToInt x)
dec2 (x:y:xs) v | prim w == True = dec2 xs (v*10 + elm w)
                | prim s == True = dec2 (y:xs) (v*10 + elm s)
                | otherwise = error "cifru incorect"
                where s = (digitToInt x)
                      t = (digitToInt y)
                      w = (s*10) + t

dec :: String -> Int
dec s = dec2 s 0

-- Pentru a va testa functia de decriptare
-- cu QuickCheck
prop_dec :: Int -> Bool
prop_dec x = abs x == dec (enc (abs x))
-- trebuie sa va dea:
-- *Main> quickCheckprop_dec
-- +++ OK, passed 100 tests.


{- Categoria A. Funetii de baza
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
digitToInt :: Char -> Int
ord :: Char -> Int
chr :: Int -> Char
-}


{- Categoria B. Functii din biblioteci
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
takeWhile isLower "goodDay" = "good"


take :: Int -> [a] -> [a]
take 4 "goodbye" = "good"


dropWhile :: (a->Bool) -> [a] -> [a]
dropWhile isLower "goodBye" = "Bye"


drop :: Int -> [a] -> [a]
drop 4 "goodbye" = "bye"


elem :: (Eq a) => a -> [a] -> Bool
elem 'd' "gdbye" = True


replicate :: Int -> a -> [a]
replicate 5 '*' = "*****"


zip :: [a] -> [b] -> [(a,b)]
zip [1,2,3,4] [1,4,9] = [(1,1),(2,4),(3,9)


-}


{- Catgoria C. Map, Filter, Fold
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
-}


import Data.Char
import Data.List
import Data.Tuple

-- In acest test vom implementa un sistem simplu de sugestii pentru autocomplete / search de cuvinte
dict = ["cat", "catering", "caterpillar", "carbon", "sea", "seashore", "seagull", "season", "severe"]

-- 1.1 (1p) (Puteti folosi functii din categoria A si B)
-- Scrieti o functie care primeste un intreg - pozitia cuvantului din dictionar
-- si returneaza lungimea acestuia, sau eroare daca pozitia depaseste limitele tabloului
-- exemplu: ll 0 == 3
ll :: Int -> Int
ll poz = if poz >= 0 && poz < length dict 
         then length (dict !! poz)
         else error "index out of bound"


-- 1.2 (1p) (Puteti folosi functii din categoria A si B)
-- Scrieti o functie care primeste ca argumente doua perechi de intregi de forma
-- (Int, Int) cu semnificatia (Rating, pozitia in dictionar)
-- Si returneaza 1, daca prima pereche este mai mare sau egala decat a doua, si 0 in caz contrar
-- O pereche se considera mai mare decat cealalta daca Ratingul primei este mai mare, 
-- sau in caz de egalitate a ratingurilor, lungimea primului cuvant (cel dat de indicele din pereche) 
-- este mai mica decat lungimea cuvantului dat de indicele din a doua pereche.
-- Exemplu:
-- comp (4, 5) (3, 1) == 1   -- ratingul primului e mai mare
-- comp (4, 0) (4, 1) == 1   -- rating egal dar lungimea lui dict[0] <= lungimea lui dict[1]
-- comp (3, 0) (5, 2) == 0   -- rating primului mai mic
comp :: (Int, Int) -> (Int, Int) -> Int
comp (a1,b1) (a2,b2) | a1 > a2  || (a1 == a2 && ll b1 <= ll b2) = 1
                     | otherwise = 0

-- 2.1  (2p) (Folosing recursivitate si (daca aveti nevoie) functiile ajutatoare si cele din categoria A si B)
--      Scrieti o functie care calculeaza gradul de similaritate a doua cuvinte (rating)
--      dupa regulile urmatoare: 
--      1. Se suprapune litera cu litera un cuvant peste celalalt
--      2. Se formeaza secvente continue de lungime maximala (l1, l2, ... lk) a literelor ce coincid
--      3. Ratingul va fi suma = 2^li + 2^l2 + .. + 2^lk
-- exemplu:
--   rating "sebshote" "seashore" == 14
--  l = lungimea maximala a secventei
-- sum = suma formata din 2^lungimea maximala pentru fiecare secventa
--   s   |  s e b s h o t e
--   q   |  s e a s h o r e
--   l   |  1 2 0 1 2 3 0 1
--  sum  |  _ 4 _ _ _ 8 _ 2  = 14
-- se formeaza 3 secvente continue de lungimi maximale 2,3,1
-- suma fiind 2^2 + 2^3 + 2^1 = 4 + 8 + 2 = 14

--  rating "acat" "bcat" == 8
--  rating "coconut" "coroutine" == 6
gg :: Int -> Int
gg k = if k == 0 then 0 else 2^k 

ratingF :: String -> String -> Int -> Int -> Int
ratingF _ [] k sm = sm + gg k
ratingF [] _ k sm = sm + gg k
ratingF (s:ss) (q:qs) k sm | s == q = ratingF ss qs (k+1) sm
                           | otherwise = ratingF ss qs 0 sm + gg k
                          
rating :: String -> String -> Int
rating s q = ratingF s q 0 0

-- 2.2   (2p) (Folosind list comprehension si functiile din categoriile A si B) 
--        Scrieti o functie care returneaza ratingurile tuturor posibilitatilor de a suprapune primul cuvant peste celalalt 
--        prin shiftarea la dreapta a primului cuvant peste al doilea
-- exemplu: 
-- allOverlapsRatings "acat" "acab" == [8,0,2,0]
--             |acat    | acat     |  acab    |   acab
--             |acab    |acab      |acab      |acab
-- ratingurile   8        0            2          0

-- allOverlapsRatings "cactus" "picartous" == [2,0,6,4,0,0,0,0,0]
-- allOverlapsRatings "coconut" "coroutine" == [6,0,2,2,0,0,0,0,0]
allOverlapsRatings :: String -> String -> [Int]
allOverlapsRatings s q = [rating s (drop i q) | i <- [0..(length q)-1]]

-- 3  (2p) (Folosind functiile din categoriile A,B,C si cele definite mai sus, 
--      dar fara recursie/list comprehension) 
--        Scrieti o functie care returneaza pentru un cuvant s, 
--        lista cu ratingurile maxime de similaritate (prin suprapunere + shiftare la dreapta)
--        intre s si fiecare dintre cuvintele din dictionar 
-- exemplu:
--   suggestionsRating "tea" == [2,4,4,0,4,4,4,4,2]
--   cu semnificatia ca maximul din allOverlapsRatings "tea" "cat" este 2 ("cat" fiind primul cuvant din dictionar)
--   continuand,   maximul din allOverlapsRatings "tea" "catering" este 4 ("catering" fiind primul cuvant din dictionar)
--   etc.
-- teste aditionale:
-- suggestionsRating "shark" == [0,2,4,0,4,6,4,4,2]
-- suggestionsRating "dolphin" == [0,4,2,2,0,2,2,2,0]
-- (2p) (18 caractere)
suggestionsRating :: String -> [Int]
suggestionsRating s = map (foldr (max) 0) $ map (allOverlapsRatings s) dict

 -- helper function
ff :: String -> [(Int,Int)]
ff s = (suggestionsRating s) `zip` [0..]
-- ff "seash" == [(0,0),(2,1),(2,2),(0,3),(8,4),(32,5),(8,6),(16,7),(4,8)]

-- 4.1 (1p) (Fara restrictii, puteti folosi orice)
--      Scrieti o functie care insereaza o pereche de forma (Int, Int)
--      Intr-un tablou de astfel de perechi, sortate crescator dupa functia 
--      de comparatie comp (definita de voi mai sus)
-- helper variable
yy :: [(Int, Int)]
yy = [(2,0), (2,3), (2,1), (4,2), (5,4)]
-- exemplu: insertPg (4,7) yy == [(2,0), (2,3), (2,1), (4,2), (4,7), (5,4)]
--          insertPg (3,7) yy == [(2,0), (2,3), (2,1), (3,7), (4,2), (5,4)]

insertp :: (Int,Int) -> [(Int,Int)] -> [(Int,Int)] -> [(Int,Int)]
insertp v [] b = b ++ [v] 
insertp v (x:xs) b | comp x v == 1 = b ++ ([v,x] ++ xs)
                   | otherwise = insertp v (xs) (b ++ [x])

insertPg :: (Int,Int) -> [(Int,Int)] -> [(Int,Int)]
insertPg v xs = insertp v xs []

-- 4.2 (1p) (Folosind functia insertPg si recursivitate)
--    Scrieti o functie care sorteaza un sir de perechi crescator dupa 
--    functia de comparatie comp (definita de voi mai sus)
sortRatingsP :: [(Int,Int)] -> [(Int,Int)]
sortRatingsP [] = []
sortRatingsP (x:xs) = insertPg x $ sortRatingsP xs


-- 4.3 (1p Bonus)
--     (Folosind functiile din categoria A,B,C, si functiile definite anterior de voi
--       dar fara recursie / list comprehension)
--       Scrieti o functie care va returna o lista cu cuvintele din dictionar
--       in ordinea descrescatoare a ratingului lor fata de cuvantul s(dat ca argument de intrare)
--       dar fara cuvintele cu care au similaritate zero.
getBestSugestions :: String -> [String]
getBestSugestions s = map (\(x,y) -> dict !! y) (reverse . sortRatingsP . (filter (\p -> fst p > 0)) $ ff s)

-- teste:
-- getBestSugestions "cactus" == ["cat", "carbon", "catering", "caterpillar", "sea", "season", "seagull", "seashore"]
-- getBestSugestions "seal" == ["sea", "season", "seagull", "seashore", "severe", "catering", "caterpillar"]
-- getBestSugestions "seven" == ["severe", "sea", "season", "seagull", "seashore", "catering", "carbon", "caterpillar"]

-- zip (map (^2) a) (map (^2) b)
{- Categoria A. Funetii de baza
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
digitToInt :: Char -> Int
ord :: Char -> Int
chr :: Int -> Char
-}


{- Categoria B. Functii din biblioteci
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
takeWhile isLower "goodDay" = "good"


take :: Int -> [a] -> [a]
take 4 "goodbye" = "good"


dropWhile :: (a->Bool) -> [a] -> [a]
dropWhile isLower "goodBye" = "Bye"


drop :: Int -> [a] -> [a]
drop 4 "goodbye" = "bye"


elem :: (Eq a) => a -> [a] -> Bool
elem 'd' "gdbye" = True


replicate :: Int -> a -> [a]
replicate 5 '*' = "*****"


zip :: [a] -> [b] -> [(a,b)]
zip [1,2,3,4] [1,4,9] = [(1,1),(2,4),(3,9)


-}


{- Catgoria C. Map, Filter, Fold
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
-}


import Data.Char
import Data.List

-- In acest test vom implementa un sistem simplu de sugestii pentru autocomplete / search de cuvinte
dict = ["cat", "catering", "caterpillar", "carbon", "sea", "seashore", "seagull", "season", "severe"]

-- 1.1 (1p) (Puteti folosi functii din categoria A si B)
-- Scrieti o functie care primeste un intreg - pozitia cuvantului din dictionar
-- si returneaza lungimea acestuia, sau eroare daca pozitia depaseste limitele tabloului
-- exemplu: ll 0 == 3
ll :: Int -> Int
ll poz = if poz >= 0 && poz < length dict 
         then length (dict !! poz)
         else error "index out of bound"


-- 1.2 (1p) (Puteti folosi functii din categoria A si B)
-- Scrieti o functie care primeste ca argumente doua perechi de intregi de forma
-- (Int, Int) cu semnificatia (Rating, pozitia in dictionar)
-- Si returneaza True, daca prima pereche este mai mare sau egala decat a doua, si False in caz contrar
-- O pereche se considera mai mare decat cealalta daca Ratingul primei este mai mare, 
-- sau in caz de egalitate a ratingurilor, lungimea primului cuvant (cel dat de indicele din pereche) 
-- este mai mica decat lungimea cuvantului dat de indicele din a doua pereche.
-- Exemplu:
-- comp (4, 5) (3, 1) == True   -- ratingul primului e mai mare
-- comp (4, 0) (4, 1) == True   -- rating egal dar lungimea lui dict[0] <= lungimea lui dict[1]
-- comp (3, 0) (5, 2) == False   -- rating primului mai mic
comp :: (Int, Int) -> (Int, Int) -> Bool
comp (a1,b1) (a2,b2) | a1 > a2  || (a1 == a2 && ll b1 <= ll b2) = True
                     | otherwise = False

-- 2.1  (2p) (Folosing recursivitate si (daca aveti nevoie) functiile ajutatoare si cele din categoria A si B)
--      Scrieti o functie care calculeaza gradul de similaritate a doua cuvinte (rating)
--      dupa regulile urmatoare: 
--      1. Se suprapune litera cu litera un cuvant peste celalalt
--      2. Se parcurge de la stanga la dreapta si se aduna lungimea secventei continue de litere egale(doua cate doua din s si q)
--         care se termina in caracterul curent. Daca la un moment dat caracterele nu coincid, se reseteaza
--         lungimea secventei (se incepe de la 0 din nou)
--      3. Ratingul va fi suma lungimilor fiecarei dintre aceste secvente
-- exemplu:
--   rating "sebshote" "seashore" == 14
--  l = lungimea curenta a secventei de litere egale (doua cate doua, din s si q)
-- sum = suma formata prin adunarea lungimilor fiecarei dintre secvente
--   s   |  s e b s h o t e
--   q   |  s e a s h o r e
--   l   |  1 2 0 1 2 3 0 1
--  sum  |  1 3 3 4 6 9 9 10  = 10 (rezultatul final, suma lungimilor, a randului de mai sus notat cu l)

--  rating "acat" "bcat" == 6
--  rating "coconut" "coroutine" == 4
ratingF :: String -> String -> Int -> Int
ratingF _ [] f = 0
ratingF [] _ f = 0
ratingF (s:ss) (q:qs) f | s == q = ratingF ss qs (f+1) + f + 1
                        | otherwise = ratingF ss qs 0
                          
rating :: String -> String -> Int
rating s q = ratingF s q 0

-- 2.2   (2p) (Folosind list comprehension si functiile din categoriile A si B) 
--        Scrieti o functie care returneaza ratingurile tuturor posibilitatilor de a suprapune primul cuvant peste celalalt 
--        prin shiftarea la dreapta a primului cuvant peste al doilea
-- exemplu: 
-- allOverlapsRatings "acat" "acab" == [6,0,1,0]
--             |acat    | acat     |  acat    |   acat
--             |acab    |acab      |acab      |acab
-- ratingurile   6        0            1          0
-- dupa cum se vede in imagine este echivalent cu a verifica similaritatea dintre
--  "acat" si "acab", 
--  "acat" cu "cab", 
--  "acat" cu "ab", 
--  "acat" ci "b"

-- allOverlapsRatings "cactus" "picartous" == [1,0,4,3,0,0,0,0,0]
-- allOverlapsRatings "coconut" "coroutine" == [4,0,1,1,0,0,0,0,0]
allOverlapsRatings :: String -> String -> [Int]
allOverlapsRatings s q = [rating s (drop i q) | i <- [0..(length q)-1]]

-- 3  (1p) (Folosind functiile din categoriile A,B,C si cele definite mai sus, 
--      dar fara recursie/list comprehension) 
--        Scrieti o functie care returneaza pentru un cuvant s, 
--        lista cu ratingurile maxime de similaritate (prin suprapunere + shiftare la dreapta)
--        intre s si fiecare dintre cuvintele din dictionar 
-- exemplu:
--   suggestionsRating "tea" == [1,3,3,0,3,3,3,3,1]
--   cu semnificatia ca maximul din allOverlapsRatings "tea" "cat" este 1 ("cat" fiind primul cuvant din dictionar)
--   continuand,   maximul din allOverlapsRatings "tea" "catering" este 3 ("catering" fiind al doilea cuvant din dictionar)
--   etc.
-- teste aditionale:
-- suggestionsRating "shark" == [0,1,3,0,2,4,2,2,1]
-- suggestionsRating "dolphin" == [0,3,1,1,0,1,1,1,0]
-- (1p) (18 caractere)
suggestionsRating :: String -> [Int]
suggestionsRating s = map (foldr (max) 0) $ map (allOverlapsRatings s) dict

-- 4.1 (1p) (Fara restrictii, puteti folosi orice)
--      Scrieti o functie care insereaza o pereche de forma (Int, Int)
--      Intr-un tablou de astfel de perechi, sortate crescator dupa functia 
--      de comparatie comp (definita de voi mai sus) 
--      (complexitatea O(N) e suficient (ca nu cumva sa va apucati sa faceti cautare binara :) ))
-- helper variable / constant function
yy :: [(Int, Int)]
yy = [(2,0), (2,3), (2,1), (4,2), (5,4)]
-- exemplu: insertPg (4,7) yy == [(2,0), (2,3), (2,1), (4,2), (4,7), (5,4)]
--          insertPg (3,7) yy == [(2,0), (2,3), (2,1), (3,7), (4,2), (5,4)]

insertp :: (Int,Int) -> [(Int,Int)] -> [(Int,Int)] -> [(Int,Int)]
insertp v [] b = b ++ [v] 
insertp v (x:xs) b | comp x v = b ++ ([v,x] ++ xs)
                   | otherwise = insertp v (xs) (b ++ [x])

insertPg :: (Int,Int) -> [(Int,Int)] -> [(Int,Int)]
insertPg v xs = insertp v xs []

-- 4.2 (1p) (Folosind functia insertPg si recursivitate)
--    Scrieti o functie care sorteaza un sir de perechi crescator dupa 
--    functia de comparatie comp (definita de voi mai sus)
sortRatingsP :: [(Int,Int)] -> [(Int,Int)]
sortRatingsP [] = []
sortRatingsP (x:xs) = insertPg x $ sortRatingsP xs

-- test:
-- sortRatingsP $ reverse yy == [(2,1),(2,3),(2,0),(4,2),(5,4)]

 -- helper function
ff :: String -> [(Int,Int)]
ff s = (suggestionsRating s) `zip` [0..]
-- ff "seash" == [(0,0),(2,1),(2,2),(0,3),(8,4),(32,5),(8,6),(16,7),(4,8)]

-- 4.3 (1p Bonus)
--     (Folosind functiile din categoria A,B,C, si functiile definite anterior de voi
--       dar fara recursie / list comprehension)
--       Scrieti o functie care va returna o lista cu cuvintele din dictionar
--       in ordinea descrescatoare a ratingului lor fata de cuvantul s(dat ca argument de intrare)
--       dar fara cuvintele cu care au similaritate zero.
getBestSugestions :: String -> [String]
getBestSugestions s = map (\(x,y) -> dict !! y) (reverse . sortRatingsP . (filter (\p -> fst p > 0)) $ ff s)

-- teste:
-- getBestSugestions "cactus" == ["cat", "carbon", "catering", "caterpillar", "sea", "season", "seagull", "seashore"]
-- getBestSugestions "seal" == ["sea", "season", "seagull", "seashore", "severe", "catering", "caterpillar"]
-- getBestSugestions "seven" == ["severe", "sea", "season", "seagull", "seashore", "catering", "carbon", "caterpillar"]

-- zip (map (^2) a) (map (^2) b)
{- Categoria A. Funetii de baza
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
digitToInt :: Char -> Int
ord :: Char -> Int
chr :: Int -> Char
-}


{- Categoria B. Functii din biblioteci
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
takeWhile isLower "goodDay" = "good"


take :: Int -> [a] -> [a]
take 4 "goodbye" = "good"


dropWhile :: (a->Bool) -> [a] -> [a]
dropWhile isLower "goodBye" = "Bye"


drop :: Int -> [a] -> [a]
drop 4 "goodbye" = "bye"


elem :: (Eq a) => a -> [a] -> Bool
elem 'd' "gdbye" = True


replicate :: Int -> a -> [a]
replicate 5 '*' = "*****"


zip :: [a] -> [b] -> [(a,b)]
zip [1,2,3,4] [1,4,9] = [(1,1),(2,4),(3,9)


-}


{- Catgoria C. Map, Filter, Fold
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
-}
