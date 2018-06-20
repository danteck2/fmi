#!/usr/bin/python
# -*- coding: utf-8 -*-


# 1. Creați un fișier "count_lines_input.txt" și scrieți în el, de mână, câteva
# linii. Scrieți apoi o funcție în python care deschide acest fișier,
# numără câte linii conține și scrie rezultatul în "count_lines_output.txt"

def count_lines():
	f = open('count_lines_input.txt', 'r')
	lines = f.readlines()
	g = open('count_lines_output.txt', 'w')
	g.write(str(len(lines)))
	g.close()
	print len(lines)
	return


# 2. Scrieți o funcție care scrie într-un fișier un număr impar random de linii
# între 1 și 10. O astfel de linie random va conține un număr random par
# între 100 și 1000.

def write_random_lines_and_numbers():
	import random
	f = open('output2.txt', 'w')
	num_lines = random.randrange(1,10,2)
	for i in range(0, num_lines):
		line = random.randrange(100, 1000, 2)
		f.write(str(line) + '\n')
	f.close()
	return


# 3. Scrieți într-un fișier numit "my_birthday.txt", de mână, data la care
# v-ați născut în formatul `'an lună zi'` (exemplu: `'1990 8 3'`). Scrieți apoi
# o funcție care citește această informație și întoarce câte zile au trecut de
# când v-ați născut.

def my_age_in_days():
	f = open('my_birthday.txt', 'r')
	import datetime
	cont = f.read()
	date_list = cont.split()
	born = datetime.date(int(date_list[0]), int(date_list[1]), int(date_list[2]))
	now = datetime.date.today()
	return (now - born).days


# 4. Scrieți o funcție python care generează o carte random dintr-un pachet
# clasic de cărți de joc. Încercați să dați două soluții diferite.
# Exemple de output:
#
#     `'5 romb'`
#     `'K trefla'`
#     `'A rosie'`
#     `'J negru'`

def get_card():
    import random
    sb = {1 : "romb", 2 : "trefla", 3: "rosie", 4: "negru"}
    nb = {2 : '2', 3 : '3', 4 : '4', 5 : '5', 6 : '6', 7 : '7', 8 : '8', 9 : '9', 10 : '10', 11 : 'J', 12 : 'Q', 13 : 'K', 14 : 'A'}
    return nb[random.randint(2, 14)] + ' ' + sb[random.randint(1, 4)]


# 5. Scrieți o funcție care returnează o mână de 5 cărți diferite (ca la macao).
# Observație: Nu puteți apela funcția precedentă de 5 ori deoarece există
# posibilitatea să vă dea aceeași carte de mai multe ori. Exemplu de output:
#
#     `['10 romb', '4 romb', 'J negru', '7 rosie', '3 trefla']`

def get_hand_of_cards():
	hand = []
	for i in range(1, 6):
		card = get_card()
		while card in hand:
			card = get_card()
		hand.append(card)
	return hand


# 6. Scrieți o funcție care întoarce o extragere random la loto 6 din 49.
# Rezultatul va fi sub forma unei liste formată din 6 int-uri.
# Găsiți 2 soluții diferite.

def loto():
	import random
	numbers = []
	for i in range(1, 7):
		nr = random.randint(1, 49)
		while nr in numbers:
			nr = random.randint(1, 49)
		numbers.append(nr)
	return numbers


# 7. Scrieți o funcție care citește de la tastatură un număr natural și întoarce
# suma pătratelor numerelor naturale impare mai mici sau egale cu acel număr.

def sum_of_odd_squares():
    n = raw_input('n: ')
    s = 0
    for i in range(1, int(n)+1):
    	s += i**2
    return s


# 8. Știind că `re.compile(r'0745([0-9]{6})')` este un regex pentru un număr de
# telefon valid de Orange, scrieți un regex pentru un număr valid de telefon
# fix din București. Scrieți apoi o funcție care primește ca parametru un
# text(un string). Funcția folosește expresia regulată scrisă de voi și verifică
# dacă în text se găsește vreun număr valid de telefon fix din București.
# Indiciu: prefixul de București este `021`. Exemple:
#
#   any_valid_number('numarul meu este 0740123456, sa stii') -> False
#   any_valid_number('noul meu numar este 0218822555, este scris in clar') -> True
#   any_valid_number('noul meu numar este 0218822sss, dar este codificat') -> False
#   any_valid_number('Din numarul meu 021882255 lipseste o cifra') -> False


def any_valid_number(text):
    import re
    p = re.compile(r'021([0-9]{7})')
    if p.search(text):
    	return True
    return False


# 9. Scrieți o funcție care primește un parametru. Dacă acesta este 'america'
# sau 'USA' sau 'US', atunci întoarce ora și data actuală de forma:
#
#     "luna zi, an - ora:minut"
#     Exemplu: "December 31, 1999 - 03:30 PM"
#
# Dacă parametrul nu se specifică sau se specifică dar nu este niciuna dintre
# aceste valori, atunci întoarce ora și data de forma:
#
#     "ora:minut / ziua.luna"
#     Exemplu: "19:30 / 10.01"


def current_time(time_format):
	from datetime import datetime
	now = datetime.now()
	if time_format in ['america', 'USA', 'US']:
		return now.strftime('%A %d, %Y - %H:%M:%S %p')   
	return now.strftime('%H:%M / %d.%m')


# 10. Scrieți o funcție care să joace jocul "Ghicește numărul" cu un utilizator.
# Funcția alege un număr random între 1 și 20 și îi oferă utlizatorului
# posibilitatea de a ghici numărul ales din maxim 5 încercări. Dacă acesta nu
# reușește sa ghicească numărul din 5 încercări, se va afișa un mesaj
# corespunzător. Exemplu de rulare:
#
#     ~$: python tema_curs_2.py
#
#     Mă gândesc la un număr între 1 și 20... Reușești să îl ghcești din 5 încercări?
#     Încercarea 1:
#     10
#     Numărul la care m-am gândit este mai mare
#     Încercarea 2:
#     15
#     Numărul la care m-am gândit este mai mare
#     Încercarea 3:
#     17
#     Bravo, ai ghicit numărul din 3 încercări!


def guess_the_number():
	print 'Mă gândesc la un număr între 1 și 20... Reușești să îl ghcești din 5 încercări?'
	import random
	number = random.randint(1, 20)
	for i in range(1, 6):
		user = raw_input('Incercarea ' + str(i) + ':\n')
		if number > int(user):
			print 'Numărul la care m-am gândit este mai mare\n'
		if number < int(user):
			print 'Numărul la care m-am gândit este mai mic\n'
		if int(user) == number:
			print 'Bravo, ai ghicit numărul din ' + str(i) + ' încercări!'
			return
	print 'Nu ai ghicit. Numarul era ' + str(number)
	return


# 11. Scrieți o funcție care verifică dacă un string primit ca parametru este
# o adresă de email validă. O adresă de email este considerată validă dacă
# începe cu "ion_", conține doar litere mici din alfabet, conține caracterul '@'
# undeva la mijloc și este doar din TLD ".com". Exemple:
#
#     valid_email('ion_popescu@yahoo.com') -> True
#     valid_email('ion_popescu@yahoo.ro') -> False
#     valid_email('ion_popescu_yahoo.com') -> False
#     valid_email('pion_popescu@yahoo.com') -> False

def valid_email(email):
	import re
	if len(email) > 7:
		if re.match("^.+\\@(\\[?)[a-zA-Z0-9\\-\\.]+\\.([a-zA-Z]{2,3}|[0-9]{1,3})(\\]?)$", email) != None:
			if email.startswith('ion_') and email.endswith('.com'):
				return True
	return False


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
    print '1. Numărul de linii dintr-un fișier'
    print 'Fără teste \n'
    print '2. Linii random, numere random'
    print 'Fără teste \n'
    print '3. Vârsta in zile'
    print 'Fără teste \n'
    print '4. Carte de joc'
    print 'Fără teste \n'

    print '5. O mână de cărți de joc'
    unique_ok = True
    for _ in range(1000):
        hand = get_hand_of_cards()
        if len(hand) > len(set(hand)) or not hand:
            unique_ok = False
    print 'OK\n' if unique_ok else ' X: s-a generat de 2 ori aceeași carte\n'

    print '6. Extragere 6/49'
    unique_ok = True
    for _ in range(1000):
        numbers = loto()
        if len(numbers) != len(set(numbers)) or not numbers or \
                [n for n in numbers if n not in range(1, 50)]:
            unique_ok = False
    print 'OK\n' if unique_ok \
        else ' X: Numerele se repeta sau nu sunt între 1 și 49\n'

    print '7. Suma de pătrate de numere impare'
    print 'Fără teste \n'

    print '8. Număr de telefon valid din București'
    test(any_valid_number('numarul meu este 0740123456, sa stii'), False)
    test(any_valid_number('noul meu numar este 0218822555, este scris in clar'),
         True)
    test(any_valid_number('noul meu numar este 0218822sss, dar este codificat'),
         False)
    test(any_valid_number('Din numarul meu 021882255 lipseste o cifra'), False)
    print

    print '9. Timpul curent în format american'
    print 'Fără teste \n'

    print '10. Ghicește numărul'
    print 'Fără teste \n'

    print '11. Adresă de mail vaildă'
    test(valid_email('ion_popescu@yahoo.com'), True)
    test(valid_email('ion_popescu@yahoo.ro'), False)
    test(valid_email('ion_popescu_yahoo.com'), False)
    test(valid_email('pion_popescu@yahoo.com'), False)

# Executarea scriptului (./lesson_2_homework.py) va declanșa rularea funcției
# main()
if __name__ == '__main__':
    main()