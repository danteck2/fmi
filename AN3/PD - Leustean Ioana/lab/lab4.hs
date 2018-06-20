-- Declarative Programming
-- Lab 4
--


import Data.Char
import Data.List
import Test.QuickCheck


-- 1.
rotate :: Int -> [Char] -> [Char]
rotate n xs = if n < (length xs) && n > 0 then take (length xs) (drop n (cycle xs))
else "Error"

-- 2.
prop_rotate :: Int -> String -> Bool
prop_rotate k str = rotate (l - m) (rotate m str) == str
                        where l = length str
                              m = if l == 0 then 0 else k `mod` l

-- 3. 
makeKey :: Int -> [(Char, Char)]
makeKey n = zip ("ABCDEFGHIJKLMNOPQRSTUVWXYZ") (rotate n "ABCDEFGHIJKLMNOPQRSTUVWXYZ")

-- 4.
lookUp :: Char -> [(Char, Char)] -> Char
lookUp l xs 
    | l>='A' && l<='Z' =  [ n | (p,n) <- xs, p == l] !! 0
    | otherwise = l
-- 5.
encipher :: Int -> Char -> Char
encipher n l = lookUp l (makeKey n) 

-- 6.
normalize :: String -> String
normalize x = [ toUpper c | c <- x, (isAlpha c) || (isDigit c)]

-- 7.
encipherStr :: Int -> String -> String
encipherStr n xs = [encipher n p | p <- normalize xs] 

-- 8.
reverseKey :: [(Char, Char)] -> [(Char, Char)]
reverseKey xs = [(n, m) | (m, n) <- xs]

-- 9.
decipher :: Int -> Char -> Char
decipher n c = lookUp c (reverseKey (makeKey n))

decipherStr :: Int -> String -> String
decipherStr n xs = [decipher n x | x <- xs]

-- 10.
prop_cipher :: Int -> String -> Bool
prop_cipher n xs | n > 0 && n < length xs = decipherStr n (encipherStr n xs) == xs
                 | otherwise = True

-- 11.
contains :: String -> String -> Bool
contains = undefined

-- 12.
candidates :: String -> [(Int, String)]
candidates = undefined



-- Optional Material

-- 13.
splitEachFive :: String -> [String]
splitEachFive [] = []
splitEachFive str | length str >= 5 = [take 5 str] ++ splitEachFive (drop 5 str)
                  | otherwise = [str ++ replicate (5-length(str)) 'X'] 
-- 14.
prop_transpose :: String -> Bool
prop_transpose str = transpose (transpose (splitEachFive str)) == splitEachFive str

-- 15.
encrypt :: Int -> String -> String
encrypt n str = concat (transpose (splitEachFive (encipherStr n str)))

-- 16.

imparteString :: Int -> String -> [String]
imparteString n [] = []
imparteString n str = [take n str] ++ imparteString n (drop n str)

decrypt :: Int -> String -> String
decrypt n str = decipherStr n $concat(transpose (imparteString ((length str) `div` 5) str))

-- Challenge (Optional)

-- 17.
countFreqs :: String -> [(Char, Int)]
countFreqs = undefined

-- 18
freqDecipher :: String -> [String]
freqDecipher = undefined

