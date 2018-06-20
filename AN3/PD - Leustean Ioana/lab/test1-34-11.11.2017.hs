-- Functiile care pot fi folosite sunt descrise la sfarsitul fisierului:
-- categoria A - functii de baza
-- categoria B - functii din biblioteci (fara map, filter, fold)
-- categoria C - map, filter, fold

-- Observatii:
-- Daca nu este specificat, poate fi folosit orice.
-- In rezolvarea unui exercitiu se pot folosi functii definite
-- in exercitiile anterioare.
-- Pot fi declarate oricate functii ajutatoare, atat timp cat respecta
-- conditiile problemei.


import           Data.Char
import           Data.List.Split





-- SUBIECTE

-- 1(0.5pt).
-- Scrieti un predicat care verifica ca un sir de caractere este format numai din cifre.
-- Fara recursie.

onlyDigit :: String -> Bool
onlyDigit = undefined



-- 2(0.5pt).
-- Scrieti o functie care are ca argument un sir de caractere si intoarce cel mai lung
-- prefix format numai din cifre.
-- E.g., getNoStr "12ab1" = "12",
--       getNoStr "-12" = ""
--       getNoStr "ab" = ""
-- Cu recursie, numai functii de categorie A.

getNoStr :: String -> String
getNoStr = undefined


-- 3(0.5pt).
-- Scrieti o functie care are ca argument un sir de caractere format
-- numai din cifre si intoarce numarul corespunzator.
-- Functia va intoarce valoarea 0 pentru sirul vid "".
-- Functia nu va trata cazurile de eroare, presupunem ca argumentul este corect.
-- Fara recursie, cu functii de categoria A si C.

getNo :: String -> Int
getNo = undefined


-- 4(0.5pt)
--Scrieti o functie care are ca argument un sir de caractere si intoarce
--subsirul obtinut prin eliminarea tuturor caracterelor care nu sunt cifre.
-- Fara recursie, fara descrieri de liste.
removeNotDigit :: String -> String
removeNotDigit = undefined


-- In continuare vom implementa un evaluator pentru expresii aritmetice.
-- O expresie este un sir de caractere.
-- Termenii unei expresii sunt subsirurile delimitate de '+'

-- Exemple:
---- termenii expresiei "-12 a +cd-1+--34ab" sunt "-12 a ", "cd-1", "--34ab"

-- VALOAREA UNUI TERMEN este un intreg calculat astfel:
----- - daca termenul incepe cu unul sau mai multe caractere '-', acestea se
-----   numara pentru a determina daca valorea finala este pozitiva (par) sau
-----   negativa (impar), apoi se elimina;
----- - dupa eliminarea caracterelor '-',  se retine cel mai lung prefix
-----   format numai din cifre; valoarea termenului este numarul asociat
-----   acestui prefix, cu semnul dat de paritatea caracterelor '-';
----- - valoarea unui termen fara cifre este 0;
----- - valoarea sirului vid "" este 0.
-- OBSERVATIE: calculul valorii  unui termen este inspirat de functia
--             parseInt() din JavaScript

-- Exemple: valoarea termenului "-12 a " este -12
--          valoarea termenului "cd-1" este 0
--          valoarea termenului "--34ab" este 34
--          valoarea termenului "--12.3abc" este 12
--          valoarea termenului "0u" este 0
--          valoarea termenului "-a" este 0
--          valoarea termenului "---34(" este -34
--          valoarea termenului "34 56a" este 34 deoarece ' ' nu este cifra

-- VALOAREA UNEI EXPRESII este suma valorilor termenilor.
-- Exemplu: eval "-12 a +cd-1+--34ab" = 22  (-12 + 0 + 34)

-- 5 (0.5pt).
-- Scrieti o functie care are ca argument un sir de caractere si
-- intoarce valoarea sirului vazut ca termen.
-- In definierea acestei functii, caracterul '+' nu joaca un rol special,
-- regulile de calcul pentru valoarea asociata unui sir fiind cele descrise anterior.
-- E.g., parseTerm "12+3" = 12
--       parseTerm "+3" = 0
--       parseTerm "--3" = 3

parseTerm :: String -> Integer
parseTerm = undefined


-- 6 (0.5pt).
-- Scrieti o functie care are ca argument o expresie si intoarce lista termenilor.
-- Atentie: termenii sunt subsirurile delimitate prin "+"
-- E.g. splitWhenPlus "-12 a +cd-1+--34ab" = ["-12 a ", "cd-1", "--34ab"]
--      splitWhenPlus "++12+cd-1+" = ["","","12","cd-1",""]

--  Scrieti functia splitWhenPlus folosind functia splitWhen din categoria B.

splitWhenPlus :: String ->[String]
splitWhenPlus = undefined


-- 7(0.5pt).
-- Aceeasi problema ca la 6 dar fara functii din categoria B.

termList :: String ->[String]
termList = undefined


-- 8 (0.5pt).
-- Scrieti o functie care primeste ca argument o expresie si o evalueaza
-- conform regulilor descrise anterior.
-- Exemplu: eval "-12 a +cd-1+--34ab" = 22  (-12 + 0 + 34)
-- Nu recursiv.
eval :: String -> Integer
eval = undefined




{- Categoria A. Functii de baza
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
digitToInt :: Char -> Int
Intervale
[first..], [first,second..], [first..last], [first,second..last]
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
takeWhile isLower "goodBye" = "good"

take :: Int -> [a] -> [a]
take 4 "goodbye" = "good"

dropWhile :: (a->Bool) -> [a] -> [a]
dropWhile isLower "goodBye" = "Bye"

drop :: Int -> [a] -> [a]
drop 4 "goodbye" = "bye"

splitWhen :: (a -> Bool) -> [a] -> [[a]]
splitWhen isDigit "aaa1bbb2ccc" = ["aaa","bbb","ccc"]
splitWhen isDigit "aaa1bbb23ccc" = ["aaa","bbb","","ccc"]
splitWhen isDigit "1aaa2bbb3ccc4" = ["","aaa","bbb","ccc",""]


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

foldl :: (b -> a -> b) -> b -> [a]-> b

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
