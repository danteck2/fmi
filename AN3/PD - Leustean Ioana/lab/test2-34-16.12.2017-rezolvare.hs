
-- Pentru toate functiile pe care le scrieti in afara instantelor precizati tipul functiei

import Data.Foldable



-- SUBIECTE

-- Definim  expresii parametrizate astfel:

data  Exp a =  O                    -- expresia vida
             | V a                 -- variabile parametrizate
             |(Exp a) :|: (Exp a)  
              deriving Eq

--Exemplu: 

test1 = (((V 1) :|: O) :|: ((V 2) :|: (V 3))) :: Exp Int
test2 =  ((((V 'a') :|: (V 'b')) :|: (V 'u')) :|: (((V 'c') :|: (V 'd')) :|: (V 'v'))) :: Exp Char
              

-- 1)(0.5pt). Daca a este instanta a clasei Show, faceti (Exp a) instanta a clasei Show  astfel incat 
---- show  test1 = ((V1 | O) | (V2 | V3))
---- show test2 = (((V'a' | V'b') | V'u') | ((V'c' | V'd') | V'v'))

instance (Show a) => Show (Exp a) where
  show O ="O"
  show (V x) = "V"++(show x)
  show (x :|: y) = "("++(show x)  ++ " | " ++ (show y) ++ ")"
  
--2) (0.75 pt) 
-- 2a) (0.5 pt) Faceti Exp a instanta a clasei Foldable

instance Foldable Exp  where
   foldMap f O = mempty
   foldMap f (V x) = f x
   foldMap f (x :|: y) = (foldMap f x) `mappend` (foldMap f y )
   
--2b) (0.25 pt) 
--   Dati un exemplu de aplicare a functiilor foldMap si foldr pentru o instanta concreta a lui 
--   (Exp a) (de exemplu (Exp Int) sau (Exp String)) si pentru o expresie care are cel putin 5 variabile.   

-- 3) (0.75 pt) Definim clasa Eval a astfel:

class Eval a where
  eval :: a -> Int
  

-- 3a) (0,5 pt)  Daca a este instanta a clasei Eval, faceti (Exp a) instanta a clasei Eval  astfel incat
---------------- :|: sa fie interpretat ca + (adunarea intregilor) si O sa fie interpretat ca 0

instance (Eval a) => Eval (Exp a) where
  eval O = 0
  eval (V x) = eval x
  eval  (x :|: y) = (eval x)  + (eval y) 
  
  
-- 3b) (0.25 pt) 
--   Dati un exemplu de  evaluare pentru o instanta concreta a lui (Exp a) (de exemplu (Exp Int) sau (Exp String)) 
--   si pentru o expresie care are cel putin 5 variabile.

instance Eval Int where  
   eval = id

-- 4) (0.5pt)  Definiti tipul de date (Arb a) care contine arbori binari in care atat nodurile interne cat si frunzele sunt etichetate
----  cu date de tipul (Exp a) si dati un exemplu de arbore care sa aiba cel putin patru frunze    
  
data Arb a = Nil 
            | L (Exp a)
            | N (Exp a) (Arb a) (Arb a)
            deriving (Eq, Show)
         
-- 5) (0.5 pt) Scrieti o functie toArb care are ca argument o lista de valori de tipul (Exp a) si intoarce un rezultat de tipul 
--            (Arb a), respectand urmatoarele cerinte:
--   c1) precizati tipul functiei 
--   c2) a (parametrul de tip) trebuie sa fie o instanta a clasei Eval, aceasta cerinta fiind o constrangere de tip
--   c3) arborele rezultat sa fie un arbore binar de sortare, sortarea facandu-se dupa valorile pe care functia
--       eval le asociaza expresiilor de tip (Exp a)
--  Exemplu: daca         list = [e1,e1,e3,e4,e5] este o lista de expresii si 
--               map eval list = [2,3,5,1,0] = [eval e1, eval e2, eval e3, eval e4, eval e5]
--  atunci aborele construit este     
--                                           e1
--                                           /\
--                                         e4  e2 
--                                         /    \
--                                       e5      e3                                   
--    toArb = undefined  

toArb :: (Eval a) => [Exp a] -> Arb a
toArb []  = Nil
toArb [x] = L x
toArb (x:xs) = ins x (toArb xs)

ins :: (Eval a) => Exp a -> Arb a -> Arb a
ins x Nil = L x
ins x (L y) = if ((eval x) < (eval y)) 
               then  N y (L x) Nil 
               else  N y Nil (L x)
ins x (N y a1 a2) = if ((eval x) < (eval y)) 
                     then  N y (ins x a1) Nil 
                     else  N y Nil (ins x a2)           
            