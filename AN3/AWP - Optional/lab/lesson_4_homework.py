#!/usr/bin/python
# coding=utf-8
import math

### Ganditi-va la ceea ce vreti sa faceti si apoi apucati-va sa scrieti cod.
### Exercitiile sunt mai scurte decat enuntul daca le faceti cum trebuie cu
### notiunile invatate pana acum


"""
Ex1:
Implementati o functie echivalenta cu functia built-in ``map`` din Python, 
care primeste 2 parametri: o functie si un iterabil. 
Iterabilul poate fi o lista sau un string.
"""

def my_map(func, iterable):
    return [func(x) for x in iterable]


"""
Ex2:
Implementati o functie echivalenta cu functia built-in ``filter`` din Python, 
care primeste 2 parametri: o functie si un iterabil. 
Iterabilul poate fi o lista sau un string.
"""

def my_filter(func, iterable):
    if isinstance(iterable, basestring):
        return ''.join([x for x in iterable if func(x) == True])
    return [x for x in iterable if func(x) == True]

"""
Ex3:
Simulați o mică bază de date de persoane folosind un dicționar:

a) Vom avea o bază de date key -> value, key este un id, value este un dicționar
cu 2 chei: nume și vârstă. Inițializați o bază de date goală.
"""

db = {} # Modificati valoarea de initializare corespunzator

"""
b) Scrieti o clasa Person care primeste la initializare nume, varsta si sex.
Pentru a fi mai usor la gender folositi 'F' pt feminin si 'M' pt masculin.
"""

class Person(object):
    name = ''
    age = ''
    gender = ''
    def __init__(self, name, age, gender):
        self.name = name
        self.age = age
        self.gender = gender

    def roll_dice(self):
        import random
        dices = [(j, i) for i in range(1, 7) for j in range(1, 7)]
        random.shuffle(dices)
        return dices[0]

"""
c) Scrieti o functie ``person_factory`` care primeste o lista de tupluri.
Fiecare tuplu este o pereche de forma (name, age, gender). Functia intoarce o
lista de obiecte de tip Persone initializate cu valorile primite. Lista va
creea obiecte de tip Person doar daca varsta este mai mare sau egala cu 18 si
va fi ordonata dupa acest atribut.
"""

def person_factory(persons_data):
    l = [Person(x[0], x[1], x[2]) for x in persons_data]
    return sorted([x for x in l if x.age >= 18], key=lambda Person: Person.age)


"""
d) Creați o metodă ``add_persons`` care primește o bază de date și o listă de
persoane pe care le introduce în această bază de date. Apelați-o și introduceți
câteva persoane.
Atentie la felul in care alageti indexul, sa nu suprascrieti intrari care
exista deja in baza de date. Functia poate fi apelata de mai multe ori pt
a introduce persoane in baza de date
"""

def add_persons(db, persons):
    keys = [x for x in range(len(db.keys()), 1000000)] # stiu ca e o tampenie, dar nu am avut timp sa gasesc ceva mai bun.
    i = 0
    for person in persons:
        db[keys[i]] = person
        i = i + 1
    return db

"""
e) Scrieti un query pe aceasta baza de date (o functie) care intoarce Persoane
al caror nume se termina in 'escu', sunt de sex feminin si au varsta intre 20 si
30 de ani. Rezultatul intors trebuie sa fie de aceeasi forma cu baza de date,
adica un dictionar unde cheia este indexul, iar valoarea este persoana.
"""

def name_gender_query(db): 
    return {item: db[item] for item in db if db[item].name.endswith("escu") and db[item].gender == 'F' and db[item].age in range(20, 30)}


"""
f) Implementati pe clasa Person o metoda ``roll_dice``, prin care o persoana
executa o aruncare random cu 2 zaruri. Functia returneaza un tuplu reprezentand
valorile celor 2 zaruri. Exemplu: (6, 4)

Implementati apoi functia ``see_statistics`` de mai jos care:

    1. Pentru fiecare persoana din baza de date executa o aruncare a zarurilor.
    2. Intoarce un dictionar de forma {suma_zaruri: numar_persoane}. suma_zaruri
    este suma numerelor de pe fețele celor 2 zaruri, iar numar_persoane este
    numarul de persoane care au aceasta suma dupa ce au aruncat cu zarul
"""

def see_statistics(db):
    return


"""
BONUS (este un pic mai grea)
 
g) Scrieti o functie ``query`` care face un query generic pe baza de date.
Rezultatul intors este la fel ca la e)
 
Exemplu:
 
query(db, name='Gigi', age=25)
query(db, age=12, gender='M')
query(db, gender='F')
query(db, gender='F', age=25, name='Andi')
query(db, gender='F', birthday='never', age=25)
query(db, plays_poker=True, is_genius=False)
 
Toate acestea sunt query-uri valide pe structura noastra a bazei de date
"""

def query(db, **kwargs):
    return


def main():
    def _helper(ex, got, expected, data):
        try:
            assert got == expected
            print ex + " : PASS"
        except AssertionError:
            print ex + " : FAIL\n\tgot: {}\n\texpected: {}\n\tTest data was: {}"\
                .format(got, expected, data)

    # Exercitiul 1
    data_r, data_s = range(1, 10), 'A'*10
    _helper('ex1', my_map(math.sqrt, data_r), map(math.sqrt, data_r),
            'my_map(math.sqrt, {})'.format(str(data_r)))
    _helper('ex1', my_map(str.split, data_s), map(str.split, data_s),
            'my_map(math.sqrt, {})'.format(str(data_s)))

    # Exercitiul 2
    _helper('ex2',
            my_filter(lambda x: x % 2 == 0, data_r),
            filter(lambda x: x % 2 == 0, data_r),
            "my_filter(lambda x: x % 2 == 0, {})".format(str(data_r)))
    _helper('ex2',
            my_filter(lambda x: x == 'A', data_s),
            filter(lambda x: x == 'A', data_s),
            "my_filter(lambda x: x % 2 == 0, '{}')".format(str(data_s)))

    # Exercitiul 3a
    global db
    if type(db) is not dict:
        _helper('ex3 a)', db, 'db has to be intialized', db)
    else:
        _helper('ex3 a)', db, db, db)

    # Exercitiul 3b
    p = Person('A', 1, 'F')
    try:
        p = Person('A', 1, 'F')
        _helper('ex3 b)', (p.name, p.age, p.gender), ('A', 1, 'F'), None)
    except Exception:
        _helper('ex3 b)', None, "Invalid Person Instance", "Person('A', 1, 'F')")
        return

    # Exercitiul 3c
    p1 = ('C', 21, 'F')
    p2 = ('A', 15, 'M')
    p3 = ('B', 18, 'M')
    l = person_factory([p1, p2, p3])
    _helper('ex3 c)', [x.name for x in l], ['B', 'C'], [p1, p2, p3])

    # Exercitiul 3d
    test_db = {}
    add_persons(test_db, person_factory([('A', 18, 'M'), ('B', 21, 'F')]))
    add_persons(test_db, person_factory([('C', 20, 'F')]))
    _helper('ex3 d)',
            [x.name for x in sorted(test_db.values(), key=lambda x: x.name)],
            ['A', 'B', 'C'],
            [('A', 18, 'M'), ('B', 21, 'F'), ('C', 20, 'F')])

    # Exercitiul 3e
    test_db = {
        1: Person('A', 25, 'F'),
        2: Person('Popescu', 25, 'M'),
        3: Person('Popescu', 31, 'F'),
        4: Person('Popescu', 25, 'F'),
    }
    query = name_gender_query(test_db).values()[0]
    _helper('ex3 e)', (query.name, query.age, query.gender),
            ('Popescu', 25, 'F'), test_db)
    name_gender_query(test_db)


if __name__ == '__main__':
    main()