
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
--getBestSugestions s = map (\(x,y) -> dict !! y) (reverse ( sortRatingsP ( (filter (\p -> fst p > 0)) (ff s) )))

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
