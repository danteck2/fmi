#!/usr/bin/python
# -*- coding: utf-8 -*-
 
# Completați funcțiile de mai jos conform instrucțiunilor din descriere.
# Funcția main() a fost configurată pentru testarea funcțiilor cu diferite
# argumente, afișând 'OK' când codul este corect.
# Fiecare funcție conține o instrucțiune 'return' care poate fi eliminată odată
# ce corpul funcției a fost completat.
 
 
# 1. Returnați o listă cu toți divizorii numărului n (primit ca parametru).
# Exemplu:
# 15 -> [1, 3, 5, 15]
# 49 -> [1, 7, 49]
def get_dividers(n):   # tre sa mai gasesc idei
    l = []
    for i in range(1, n+1):
        if n % i == 0:
            l.append(i)
    return l
# 2. Pornind de la numărul n primit ca parametru, returnați numărul n la
# puterea n.
# Exemplu:
# 3 -> 27
# 4 -> 256
def power(n):  # tre sa mai gasesc idei
    return n ** n

# 3. Plecând de la șirul de caractere s, returnați șirul de caractere format
# prin concatenarea primelor 2 caractere ale șirului cu ultimele 2 caractere
# ale șirului. Dacă lungimea șirului este mai mică de 2, returnați șirul gol.
# Exemplu:
# 'primavara' -> 'prra'
def both_ends(s):
    if len(s) < 2:
        return ""
    aux = s[:2] + s[-2:]
    return aux
 
 
# 4. Returnați șirul de caractere obținut prin înlocuirea tuturor aparițiilor
# primului caracter al s (primit ca parametru) cu '*', mai puțin primul
# caracter.
# Exemplu:
# 'eleve' -> 'el*v*'
def replace_chars(s):
    char = s[0]
    s = s.replace(char, '*').replace('*', char, 1)
    return s
 
 
# 5. Construiți un dicționar pornind de la două liste - chei și valori -
# primite ca parametri, asociind prima cheie cu prima valoare, cea de-a doua
# cheie cu cea de-a doua valoare, etc. Dacă lista de chei este mai mare decât
# cea de valori, pentru cheile fără corespondent se va asocia valoarea
# implicită 0. Dacă lista de valori este mai mare, valorile fără corespondent
# vor fi ignorate.
# Exemplu:
# ['mere', 'banane'], [7, 5, 3] -> {'mere': 7, 'banane': 5}
# ['mere', 'pere', 'prune'], [9, 3] -> {'mere': 9, 'pere': 3, 'prune': 0}
def build_dict(keys, values):
    if len(keys) > len(values):
        for i in range(len(values), len(keys)):
            values.append(0)
    d = {}
    for i in range(0, len(keys)):
        d[keys[i]] = values[i]
    return d
 
 
# 6. Returnați lista obținută prin înlocuirea primelor k elemente ale listei l
# cu ultimele k elemente ale acesteia. Dacă nu se respectă condiția
# 0 <= k <= len(l) // 2 returnați lista vidă.
# Exemplu:
# [1, 2, 3, 4, 5, 6], 2 -> [5, 6, 3, 4, 1, 2]  ---- 6 5 3 4 2 1
def swap_k(l, k):
    if k < 0 or k >= (len(l) / 2):
        return []
    for i in range(0, k):
        aux = l[i]
        l[i] = l[len(l) - k + i]
        l[len(l) - k + i] = aux
    return l
 
 
# 7. Pornind de la lista l (primită ca parametru), construiți dicționarul care
# contorizează numărul de apariții ale fiecărui element din listă.
# Exemplu:
# ['a', 'b', 'a', 'c'] -> {'a': 2, 'b': 1, 'c': 1}
def how_many(l):
    d = {}
    for element in l:
        d[element] = l.count(element)
    return d
 
 
# Funcție care compară rezultatele obținute în urma apelării unei funcții cu
# cele așteptate (corecte).
def test(got, expected):
    if got == expected:
        prefix = ' OK '
    else:
        prefix = '  X '
    print '{0} got: {1} expected: {2}'.format(prefix, got, expected)
 
 
# Funcția de mai jos testează rezultatele funcțiilor din exerciții
def main():
    print '1. Divizorii unui număr'
    test(get_dividers(70), [1, 2, 5, 7, 10, 14, 35, 70])
    test(get_dividers(1326), [1, 2, 3, 6, 13, 17, 26, 34, 39, 51, 78, 102, 221,
                              442, 663, 1326])
    test(get_dividers(1), [1])
    test(get_dividers(4483), [1, 4483])
 
    print '2. N la puterea N'
    test(power(1), 1)
    test(power(3), 27)
    test(power(7), 823543)
    test(power(10), 10000000000)
 
    print '3. Concatenează capetele'
    test(both_ends(''), '')
    test(both_ends('a'), '')
    test(both_ends('la'), 'lala')
    test(both_ends('xyz'), 'xyyz')
    test(both_ends('a fost odata'), 'a ta')
 
    print '4. Înlocuiește cu "*"'
    test(replace_chars('bob'), 'bo*')
    test(replace_chars('orologiu'), 'or*l*giu')
    test(replace_chars('abcaabbccaaabbbccc'), 'abc**bbcc***bbbccc')
    test(replace_chars('problemă'), 'problemă')
 
    print '5. Construiește dicționarul'
    test(build_dict([], [12, 0, 1]), {})
    test(build_dict(['completed', 'in progress'], [4, 5, 7, 8]),
         {'completed': 4, 'in progress': 5})
    test(build_dict(['a', 'b', 'c'], ['hello', 'world', '!']),
         {'a': 'hello', 'b': 'world', 'c': '!'})
    test(build_dict([1, 3, 5], []), {1: 0, 3: 0, 5: 0})
 
    print '6. Rocadă în listă'
    test(swap_k([1, 2, 3], 3), [])
    test(swap_k([1, 2, 3, 4], 0), [1, 2, 3, 4])
    test(swap_k([7, 9], 1), [9, 7])
    test(swap_k([1, 2, 3, 4, 5, 6, 7, 8], 3), [6, 7, 8, 4, 5, 1, 2, 3])
 
    print '7. Numărul de apariții'
    test(how_many([6, 4, 6, 2, 4, 5, 3, 6]), {2: 1, 3: 1, 4: 2, 5: 1, 6: 3})
    test(how_many([]), {})
    test(how_many([5, '5']), {5: 1, '5': 1})
    test(how_many(['a', 'a', 'b', 'c', 'a', 'b']), {'a': 3, 'c': 1, 'b': 2})
 
 
# Executarea scriptului (./lesson_1_homework.py) va declanșa rularea funcției
# main()
if __name__ == '__main__':
    main()

