
import Data.List
import Test.QuickCheck
import Control.Monad
{-
Definim un tip de date pentru a reprezenta o rețea de mulțimi de puncte în plan:
-}

type Punct = (Int,Int)
data Multime = X 
             | Y 
             | DX Int Multime 
             | DY Int Multime 
             | U Multime Multime
{-
Planul este centrat în punctul (0,0).  Prima coordonată a unui punct (coordonata x) reprezintă distanța pe orizontală de la origine iar a doua (coordonata y) reprezintă distanța pe verticală.  Prin convenție, coordonatele x cresc spre dreapta, în timp ce coordonatele y cresc în sus.  

Constructorul X reprezintă mulțimea punctelor de pe axa x, adică punctele care au coordonata y zero:
... (-2,0) (-1,0) (0,0) (1,0) (2,0) ...


Constructorul Y reprezintă mulțimea punctelor de pe axa y, adică punctele care au coordonata x zero:
... (0,-2) (0,-1) (0,0) (0,1) (0,2) ...

Constructorul DX dx p, unde dx este un întreg și p e o mulțime de puncte, reprezintă punctele p deplasate la dreapta cu dx. De exemplu, DX 2 Y are ca rezultat axa y deplasată cu două unități la dreapta:
... (2,-2) (2,-1) (2,0) (2,1) (2,2) ...

Observație 1:  DX dx X și X denotă aceeași mulțime de puncte, deoarece prin deplasarea axei x pe orizontală se obține tot axa x.

Observație 2: Un punct (x,y) aparține lui DX dx p dacă și numai dacă punctul (x-dx,y) aparține lui p.

Constructorul DY dy p, unde dy este un întreg și p e o mulțime de puncte, reprezintă punctele p deplasate în sus cu dx. De exemplu, DY 3 X are ca rezultat axa X deplasată cu două unități în sus:
... (-2,3) (-1,3) (0,3) (1,3) (2,3) ...

Observație 1:  DY dy Y și Y denotă aceeași mulțime de puncte, deoarece prin deplasarea axei y pe verticală se obține tot axa y.

Observație 2: Un punct (x,y) aparține lui DY dy p dacă și numai dacă punctul (x,y-dy) aparține lui p.

Constructorul U p q, unde p și q sunt mulțimi de puncte, reprezintă reuniunea punctelor din p și q.  De exemplu,
U (U X Y) (U (DY 3 X) (DX 2 Y))
reprezintă mulțimea de puncte de forma

... (-2,0) (-1,0) (0,0) (1,0) (2,0) ...
... (0,-2) (0,-1) (0,0) (0,1) (0,2) ...

... (-2,3) (-1,3) (0,3) (1,3) (2,3) ...
... (2,-2) (2,-1) (2,0) (2,1) (2,2) ...

Cerințe:
 Scrieți o funcție
-} 

apartine :: Punct -> Multime -> Bool
(_,y) `apartine` X         = y == 0
(x,_) `apartine` Y         = x == 0
(x,y) `apartine` (DX d m)  = (x - d, y) `apartine` m
(x,y) `apartine` (DY d m)  = (x, y - d) `apartine` m
(x,y) `apartine` (U m1 m2) = (x, y) `apartine` m1 || (x,y) `apartine` m2

{-
 care determină dacă un punct aparține unei mulțimi de puncte date.  De exemplu:

  apartine (3,0) X == True
  apartine (0,1) Y == True
  apartine (3,3) (DY 3 X) == True
  apartine (2,1) (DX 2 Y) == True
  apartine (3,0) (U X Y) == True
  apartine (0,1) (U X Y) == True
  apartine (3,3) (U (DY 3 X) (DX 2 Y)) == True
  apartine (2,1) (U (DY 3 X) (DX 2 Y)) == True
  apartine (3,0) (U (U X Y) (U (DX 2 Y) (DY 3 X))) == True
  apartine (0,1) (U (U X Y) (U (DX 2 Y) (DY 3 X))) == True
  apartine (3,3) (U (U X Y) (U (DX 2 Y) (DY 3 X))) == True
  apartine (2,1) (U (U X Y) (U (DX 2 Y) (DY 3 X))) == True
  apartine (1,1) X == False
  apartine (1,1) Y == False
  apartine (1,1) (DY 3 X) == False
  apartine (1,1) (DX 2 Y) == False
  apartine (1,1) (U X Y) == False
  apartine (1,1) (U X Y) == False
  apartine (1,1) (U (DY 3 X) (DX 2 Y)) == False
  apartine (1,1) (U (DY 3 X) (DX 2 Y)) == False
  apartine (1,1) (U (U X Y) (U (DX 2 Y) (DY 3 X))) == False

Scrieți o funcție 
-}

nrAxe :: Multime -> Int
nrAxe X = 1
nrAxe Y = 1
nrAxe (DX _ m) = nrAxe m
nrAxe (DY _ m) = nrAxe m
nrAxe (U m1 m2) = nrAxe m1 + nrAxe m2

{-
 care numără de câte ori apare X sau Y în descrierea unei mulțimi de puncte. Fiecare axă trebuie numărată câte o dată pentru fiecare apariție a sa. De exemplu:

  nrAxe X == 1
  nrAxe Y == 1
  nrAxe (U X Y) == 2
  nrAxe (U (DY 3 X) (DX 2 Y)) == 2
  nrAxe (U (U X Y) (U (DX 2 Y) (DY 3 X))) == 4
  nrAxe (U (U X Y) X) == 3 



Se consideră următorul tip algebric de date:
-}

data Expr = Const Int
          | Neg Expr
          | Expr :+: Expr
          | Expr :*: Expr
  deriving (Eq, Show)
            
data Op = NEG | PLUS | TIMES
  deriving (Eq, Show)

data Atom = AConst Int | AOp Op
  deriving (Eq, Show)

type Polish = [Atom]


{-
Cerințe:
Să se scrie o funcție 
-}

fp :: Expr -> Polish
fp (Const x) = [AConst x]
fp (Neg e) = AOp NEG : fp e
fp (e1 :+: e2) = AOp PLUS : fp e1 ++ fp e2
fp (e1 :*: e2) = AOp TIMES : fp e1 ++ fp e2


{-
care asociază unei expresii aritmetice date scrierea ei în forma poloneză: o listă de Atomi, obținută prin parcurgerea în preordine a arborelui asociat expresiei (operațiile precedând reprezentărilor operanzilor).
Exemple:
* forma poloneză a expresiei 5 * 3 este * 5 3 
  fp (Const 5 :*: Const 3) == [AOp TIMES, AConst 5, AConst 3]

* forma poloneză a expresiei −(7 * 3) este  - * 7 3
  fp (Neg (Const 7 :*: Const 3)) == [AOp NEG, AOp TIMES, AConst 7, AConst 3]

* forma poloneză a expresiei (5 + −3) * 17 este * + 5 - 3 17
  fp ((Const 5 :+: Neg (Const 3)) :*: Const 17) == [AOp TIMES, AOp PLUS, AConst 5, AOp NEG, AConst 3, AConst 17]

* forma poloneză a expresiei (15 + (7 * (2 + 1))) * 3 este * + 15 * 7 + 2 1 3
  fp ((Const 15 :+: (Const 7 :*: (Const 2 :+: Const 1))) :*: Const 3) == [AOp TIMES, AOp PLUS, AConst 15, AOp TIMES, AConst 7, AOp PLUS, AConst 2, AConst 1, AConst 3]

Definiți o funcție 
-}


newtype PolishParser a = PolishParser {apply :: Polish -> (Polish, Maybe a)}

instance Functor PolishParser where
  fmap f pa = PolishParser (\p -> let (p', ma) = apply pa p in (p', fmap f ma))

instance Applicative PolishParser where
  pure x = PolishParser (\p -> (p,Just x))
  pf <*> pa = PolishParser (\ p -> 
    let (p',mf) = apply pf p
        (p'',ma) = apply pa p'
     in (p'', mf <*> ma))

instance Monad PolishParser where
  pa >>= k = PolishParser (\ p -> 
    let (p', ma) = apply pa p
     in case ma of
          Nothing -> (p, Nothing)
          Just a -> apply (k a) p')

readAtom :: PolishParser Atom
readAtom = PolishParser (\p -> case p of [] -> (p,Nothing); (h:t) -> (t, Just h))

readExp :: PolishParser Expr
readExp = readAtom >>= \a -> case a of
    AConst x -> return (Const x)
    AOp NEG -> readExp >>= return . Neg
    AOp PLUS -> bOp (:+:)
    AOp TIMES -> bOp (:*:)
  where
  bOp op = readExp >>= \e1 -> readExp >>= \e2 -> return (e1 `op` e2)


rfp :: Polish -> Maybe Expr
rfp = snd . apply readExp 


{-
astfel încât rfp . fp = Just . id

Introducem un tip de date ce reprezinta o colectie de puncte (o tabela). 
-}

type Point =(Integer, Integer)

data Points = Rectangle Point Point
            | Union Points Points
            | Difference Points Points 
  deriving (Show)


instance Arbitrary Points where
    arbitrary  =  sized points
        where
          points n | n <= 0     =  rectangle
                   | otherwise  =  oneof [ rectangle
                                       , liftM2 Union subpoints subpoints
                                       , liftM2 Difference subpoints subpoints
                                       ]
                 where
                   rectangle = liftM4 (\x0 y0 x1 y1 -> Rectangle (x0,y0) (x1,y1)) arbitrary arbitrary arbitrary arbitrary
                   subpoints  =  points (n `div` 2)


type InfinitePoint = (InfiniteInteger, InfiniteInteger)

data PointsBoolean = BRectangle InfinitePoint InfinitePoint
                   | All [PointsBoolean]
                   | Any [PointsBoolean]
                   | Comp PointsBoolean
--                   | Empty = Any []
--                   | Full  = All []
  deriving (Show)


data InfiniteInteger = Finite Integer | Inf | NInf
  deriving Eq

instance Show InfiniteInteger where
  show (Finite i) = show i
  show Inf = "infinity"
  show NInf = "-infinity"

instance Ord InfiniteInteger where
  Finite i <= Finite j = i <= j
  _ <= Inf = True
  x <= NInf = x == NInf
  NInf <= _ = True
  Inf <= x = x == Inf

instance Num InfiniteInteger where
  (Finite i) + (Finite j) = Finite (i + j)
  (Finite i) + infinity = infinity
  infinity + (Finite i) = infinity
  Inf + Inf = Inf
  NInf + NInf = NInf
  inf + ninf = error "cannot add inifinities of opposite sign"
  (Finite i) * (Finite j) = Finite (i * j)
  (Finite i) * infinity 
    | i > 0 = infinity
    | i < 0 = negate infinity
    | i == 0 = error "cannot multiply inifinity with 0"
  infinity * (Finite i) = (Finite i) * infinity
  Inf * Inf = Inf
  NInf * NInf = Inf
  Inf * NInf = NInf
  NInf * Inf = NInf
  abs (Finite i) = Finite (abs i)
  abs Inf = Inf
  abs NInf = Inf
  signum (Finite i) = Finite (signum i)
  signum Inf = Finite 1
  signum NInf = Finite (-1)
  fromInteger = Finite
  negate (Finite i) = Finite (negate i)
  negate Inf = NInf
  negate NInf = Inf

{-
Tabela incepe cu punctul (0,0) stanga jos. 
Constructorul Rectangle selecteaza toate punctele dintr-o forma rectangulara.
De pilda, Rectangle (0,0) (2,1) da colturile din stanga jos si dreapta sus ale unui dreptunghi si include punctele (0,0) ; (1,0) ;(2,0) ; (0,1) ; (1,1) ; (2,1)

Union combina doua colectii de puncte iar Difference contine acele puncte care sunt in prima colectie dar nu sunt in a doua. 			  

Scrieti o functie perimeter care calculeaza perimetrul celui mai mic dreptunghi care cuprinde complet o colectie de puncte.
-}



pointsBA :: Points -> PointsBoolean
pointsBA (Rectangle (x0,y0) (x1,y1)) = BRectangle (Finite x0, Finite y0) (Finite x1, Finite y1)
pointsBA (Union m1 m2) = Any [pointsBA m1, pointsBA m2]
pointsBA (Difference m1 m2) = All [pointsBA m1, Comp (pointsBA m2)]

dnfP :: PointsBoolean -> PointsBoolean
dnfP m@(BRectangle _ _) = Any [All [m]]
dnfP (Any ms) = foldr agg (Any []) (map dnfP ms)
  where 
  agg _ t@(Any [All []]) = t
  agg t@(Any [All []]) _ = t
  agg (Any xs) (Any r) = Any (xs ++ r)
  agg pb1 pb2 = error $ "Cannot agg1 " ++ show pb1 ++ " and " ++ show pb2
dnfP (Comp (Any ms)) = dnfP (All (map Comp ms))
dnfP (Comp (All ms)) = dnfP (Any (map Comp ms))
dnfP (Comp (Comp m)) = dnfP m
dnfP (Comp (BRectangle (x0,y0) (x1,y1))) = dnfP $ Any[BRectangle (-Inf,-Inf) (x0,Inf), BRectangle (x0, y1) (Inf, Inf), BRectangle (x0,-Inf) (Inf, y0), BRectangle (x1,y0) (Inf, y1)]
dnfP (All ms) = foldr agg (Any [All []]) (map dnfP ms)
  where
  agg _ (Any []) = Any []
  agg (Any[]) _ = Any []
  agg (Any ds) (Any ds') = Any [All (cs ++ cs') | All cs <- ds, All cs' <- ds']
  agg pb1 pb2 = error $ "Cannot agg2 " ++ show pb1 ++ " and " ++ show pb2

simplify :: PointsBoolean -> PointsBoolean
simplify (Any alls) = Any $ filter isRectangle $ map simplifyAll alls
  where
  simplifyAll (All ms) = foldr intersect (BRectangle (-Inf,-Inf) (Inf,Inf)) ms
  intersect (BRectangle (x0,y0) (x1,y1)) (BRectangle (x0', y0') (x1', y1')) =
    BRectangle (max x0 x0', max y0 y0') (min x1 x1', min y1 y1')
  isRectangle (BRectangle (x0,y0) (x1,y1)) = x0 <= x1 && y0 <= y1

bBox :: PointsBoolean -> PointsBoolean
bBox m = BRectangle (minimum xs, minimum ys) (maximum xs, maximum ys)
  where
  points (Any rs) = foldr (\(BRectangle p0 p1) ps -> p0:p1:ps) [] rs
  ps = points m
  ps' = if null ps then [(0,0)] else ps
  xs = map fst ps'
  ys = map snd ps'

bPerimeter :: Points -> Integer
bPerimeter m = i
  where
  BRectangle (x0,y0) (x1,y1) = bBox $ simplify $ dnfP $ pointsBA m
  Finite i =  2 * (x1 - x0 + y1 - y0)


points :: Points -> [Point]
points (Rectangle (x0,y0) (x1,y1)) = [(x,y) | x <- [x0..x1], y <- [y0..y1]]
points (Union m1 m2) = nub (points m1 ++ points m2)
points (Difference m1 m2) = points m1 \\ points m2

box :: Points -> Points
box m = Rectangle (minimum xs, minimum ys) (maximum xs, maximum ys)
  where
  ps = points m
  ps' = if null ps then [(0,0)] else ps
  xs = map fst ps'
  ys = map snd ps'

perimeter :: Points -> Integer
perimeter m = 2 * (x1 - x0 + y1 - y0)
  where
  Rectangle (x0,y0) (x1,y1) = box m


prop_perimeter :: Points -> Bool
prop_perimeter m = show (perimeter m) == show (bPerimeter m)

{-
 Scrieti o functie distance care calculeaza distanta dintre doua colectii de puncte ca reprezentand distanta intre coltul dreapta-sus al dreptunghiului minimal care cuprinde prima colectie si coltul stanga-jos al dreptunghiului minimal care cuprinde cea de-a doua colectie.
-}

distance :: Points -> Points -> Float
distance m m' = sqrt $ fromIntegral $  (x1-x0')^2 + (y1-y0')^2
  where
  Rectangle (x0,y0) (x1,y1)     = box m
  Rectangle (x0',y0') (x1',y1') = box m'

{-
Introducem un tip de date ce reprezinta o expresie booleana formată din literali / variabile (Lit), negație (Not), conjuncție (And), disjuncție (Or) și implicație (:->:), in care conjuncțiile si disjuncțiile au un număr arbitrar de termeni.-} 

data Exp = Lit String
         | Not Exp
         | And [Exp]
         | Or [Exp]
         | Exp :->: Exp
  deriving (Show)

{-
Un atom este fie un literal (Lit), fie negația unui literal. O expresie este în formă normală disjunctivă dacă este o disjuncție de conjuncții de atomi.

Să se scrie o funcție care dată fiind o expresie are ca rezultat forma normală disjunctivă a acelei expresii.
-}

dnf :: Exp -> Exp
dnf m@(Lit _) = Or [And [m]]
dnf (Or ms) = foldr agg (Or []) (map dnf ms)
  where 
  agg _ t@(Or [And []]) = t
  agg t@(Or [And []]) _ = t
  agg (Or xs) (Or r) = Or (xs ++ r)
  agg pb1 pb2 = error $ "Cannot agg1 " ++ show pb1 ++ " and " ++ show pb2
dnf (Not (Or ms)) = dnf (And (map Not ms))
dnf (Not (And ms)) = dnf (Or (map Not ms))
dnf (Not (Not m)) = dnf m
dnf a@(Not (Lit _)) = Or [And [a]]
dnf (Not (e1 :->: e2)) = dnf (And [e1, Not e2])
dnf (And ms) = foldr agg (Or [And []]) (map dnf ms)
  where
  agg _ (Or []) = Or []
  agg (Or []) _ = Or []
  agg (Or ds) (Or ds') = Or [And (cs ++ cs') | And cs <- ds, And cs' <- ds']
  agg pb1 pb2 = error $ "Cannot agg2 " ++ show pb1 ++ " and " ++ show pb2
dnf (e1 :->: e2) = dnf $ Or [Not e1, e2]

{-
   dnf ((Lit "a" :->: Lit "b") :->: Lit "c")
     = Or [And [Lit "a",Not (Lit "b")],And [Lit "c"]]
   dnf (Lit "a" :->: (Lit "b" :->: Lit "c"))
     = Or [And [Not (Lit "b")],And [Lit "c"],And [Not (Lit "a")]]

• a → b ≡ ¬a ∨ b
• ¬¬a = a
• ¬ /\ (a 1 , . . . , a n ) = \/(¬a 1 , . . . , ¬a n )
• ¬ \/ (a 1 , . . . , a n ) = /\(¬a 1 , . . . , ¬a n )
• /\ (a 1 , . . . , a i , /\(b 1 , . . . , b m ), a i+1 , . . . , a n ) = /\ (a 1 , . . . , a n , b 1 , . . . , b m )
• \/ (a 1 , . . . , a i , \/ (b 1 , . . . , b m ), a i+1 , . . . , a n ) = \/ (a 1 , . . . , a n , b 1 , . . . , b m )
• /\ (a 1 , . . . , a i , \/ (b 1 , . . . , b m ), a i+1 , . . . , a n ) = \/ (/\ (b 1 , a 1 , . . . , a n ), . . . , /\ (b m , . . . , a n ))

-}
