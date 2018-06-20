#!/usr/bin/python
# -*- coding: utf-8 -*-


# 1. În fișierul "ids.txt" se află CNP-uri, fiecare CNP ocupând o
# linie a fișierului. Scrieți o funcție Python care citește fiecare CNP
# din fișier, scrie la sfârșitul fișierului o linie cu 10 simboluri '#', iar
# apoi, pe fiecare linie, CNP-urile corecte din fișier.

# Prima cifră a CNP-ului trebuie să fie cuprinsă între 1 şi 5
# Următoarele 6 cifre reprezintă data naşterii în formatul yymmdd
# După data naşterii urmează încă 6 cifre 
 
def correct_ids():
    # TO DO
    f = open('ids.txt', 'rw+')
    lista = []
    while(True):
        line = f.readline()
        if line == "":
            break
        lista.append(line.strip())
    f.write('\n##################')
    for j in range(0, len(lista)):
        list_of_ints = [int(i) for i in str(lista[j])]
        if list_of_ints[0] < 6 and list_of_ints[0] > 0 :
            if ((list_of_ints[3]*10) + list_of_ints[4]) < 12 and ((list_of_ints[5]*10) + list_of_ints[6]) < 31:
                if len(list_of_ints) == 13 :
                    f.write('\n')
                    f.write(lista[j])
    f.close()
    
    return


# 2. Să se scrie o funcție Python care primește un număr variabil de argumente
# și care returnează un dicționar format din numerele prime și numerele
# perfecte* primite ca parametri. Se pot folosi funcții auxiliare.
# *Un număr se numește perfect dacă suma divizorilor săi proprii, fără numărul
# în sine, este egală cu acesta (6=1+2+3)
#  Ex: Pentru datele de intrare 3, 4, 15, 7, 6, 2, 28, 13, 8
#      output-ul va fi de forma
#         {
#             'prime': [3, 7, 2, 13],
#             'perfect': [6, 28]
#         }

def prime(num):
    if num > 1:
        for i in range(2,num):
            if (num % i) == 0:
                break
        else:
            return True
           
    else:
        return False
        
def perfect(num):
    sum = 0
    for x in range(1, num):
        if num % x == 0:
            sum += x
    return sum == num
    
def prime_perfect(*args):
    # TO DO
    print args
    output = {}
    for i in range(0, len(args)):
        if prime(args[i]) == True:
            if output.has_key('prime'):
                output['prime'].append(args[i])
            else:
                output['prime'] = [args[i],]
        else:
            if perfect(args[i]) == True:
                if output.has_key('perfect'):
                    output['perfect'].append(args[i])
                else:
                    output['perfect'] = [args[i],]
    print output
    return

# 3. Să se scrie o funcție Python care primește ca parametru o propoziție.
# Funcția va returna propoziția în care cuvintele de pe poziții pare vor avea
# literele în ordine inversă, iar cuvintele de pe poziții impare vor fi
# eliminate.

def reversed_string(a_string):
    return a_string[::-1]
    
def odd_sentence(sentence):
    # TO DO
    words = sentence.split()
    words_result = [] 
    for i in range(0, len(words)):
        if (i % 2) == 0:
            words_result.append( reversed_string(words[i]))
    print words_result
    return


# 4. În fișierul "words.txt" se află pe fiecare linie câte un cuvânt. Scrieți un
# program care:
#     a) alege aletor un cuvânt din fișier
#     b) face o permutare aleatoare a acestui cuvânt
#     c) afișează pentru utilizator acea permutare (anagramă)
#     d) îi oferă utilizatorului 3 șanse să ghicească cuvântul original

# Găsiţi informaţii despre modulul random în secţiunea Suport de curs--> Operaţii de bază

import random
import re
def guess_anagram():
    # TO DO
    f = open('words.txt', 'r')
    lista = f.read()
    lista1 = re.sub("[^\w]", " ",  lista).split()
    word = random.choice(lista1)
    letterlist = list(word)
    random.shuffle(letterlist)
    print letterlist
    for i in range(0, 3):
        input = raw_input()
        if input == word:
            print 'Ai ghicit'
    f.close()
    return


# 5. Scrieți o funcție care să returneze o listă cu datele tuturor zilelor de
# miercuri ale unei luni date ca parametru (ca întreg). Se consideră că luna
# dată ca parametru este din anul curent. Datele vor fi returnate ca string-uri
# în formatul yyyy-mm-dd.
from datetime import datetime
import calendar
def get_wednesdays(month):
    # TO DO
    yyyy = datetime.now().year
    list = []
    for i in calendar.monthcalendar(yyyy, month):
        if i[2] != 0:
            list.append(str(yyyy)+'-'+str(month)+'-'+str(i[2]))
    return list

# Funcție care compară rezultatele obținute în urma apelării unei funcții cu
# cele așteptate (corecte).
def test(got, expected):
    if got == expected:
        prefix = ' OK '
    else:
        prefix = '  X '
    print '{0} got: {1}, expected: {2}'.format(prefix, got, expected)


# Funcția de mai jos testează rezultatele funcțiilor din exerciții
def main():
    print '1. CNP-uri corecte'
    print 'Fără teste \n'

    print '2. Numere prime sau perfecte'
    test(prime_perfect(3, 4, 15, 7, 6, 2, 28, 13, 8),
         {'prime': [3, 7, 2, 13], 'perfect': [6, 28]})
    test(prime_perfect(3, 4, 15, 7, 2, 13),
         {'prime': [3, 7, 2, 13], 'perfect': []})
    print

    print '3. Propoziție modificată'
    test(odd_sentence('The weather is cold'), 'ehT si')
    test(odd_sentence('Hello'), 'olleH')
    print

    print '4. Ghicire anagramă'
    print 'Fără teste \n'

    print '5. Ziua de miercuri'
    test(get_wednesdays(10),
         ['2016-10-05', '2016-10-12', '2016-10-19', '2016-10-26'])

if __name__ == '__main__':
    main()
