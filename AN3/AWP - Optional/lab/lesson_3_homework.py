#!/usr/bin/python
# -*- coding: utf-8 -*-
"""
Ex. 1
Definiti clasa DateFormat care primeste la initializare, si retine pe self, 
un format[1] de data. Adaugati doua metode:
    1. format - care transforma un obiect datetime primit ca paramentru in 
                string folosind formatul setat.
    2. parse  - care transforma un string primit ca paramentru intr-un obiect
                datetime. Folositi formatul specificat. 
 
1)https://docs.python.org/2/library/datetime.html#strftime-and-strptime-behavior
"""


class DateFormat(object):
	f = ''
	def __init__(self, string):
		self.f = string

	def format(self, d):
		return d.strftime(self.f)

	def parse(self, string):
		return datetime.strptime(string, self.f)
 

"""
Ex. 2
Definiti clasa DateRange care primeste la initializare o valoare start si o 
valoare end, ambele sunt obiecte datetime. Adaugati metoda:
    1. contains - primeste un obiect datetime si returneaza True daca data se 
                  afla in intervalul [start, end).
"""


class DateRange(object):
	def __init__(self, start, end):
		self.start = start
		self.end = end

	def contains(self, d):
		if self.start <= d < self.end:
			return True
		return False
	def number(self):
		if self.start == self.end:
			return 1
		return (self.end - self.start).days
        
"""
Ex. 3
Definiti clasa Hotel care primeste la initializare un pret pe noapte si un 
pret de curatenie. Adaugati metoda:
    1. price - primeste o valoare de tip DateRange si returneaza pretul de 
               cazare pentru acel interval (numarul de nopti inmultit cu pretul 
               pe noapte la care se adauga o singura data taxa de curatenie).
Ex. 4
Sa presupunem ca Hotel are o singura camera. Modificati clasa sa memoreze o 
lista de rezervari. Implementati metodele:
    1. book - primeste ca paramentru un DateRange si adauga o rezervare. 
    2. available - primeste un DateRange si returneaza True daca hotelul este 
                   disponibil in acel interval si False daca este indisponibil.
"""


class Hotel(object):
	def __init__(self, night, tax):
		self.night = night
		self.tax = tax
		self.rez = []

	def price(self, r):
		return r.number() * self.night + self.tax

	def book(self, r):
		self.rez.append(r)

	def available(self, r):
		for rezervare in self.rez:
			if rezervare.contains(r.start) or rezervare.contains(r.end):
				return False
		return True

"""
Ex. 5
Definiti clasa BiggerHotel care extinde clasa Hotel. BiggerHotel pe langa 
parametrii primiti de Hotel va primi si un numar care va indica capacitatea
hotelului(nr de camere). Modificati metodele available si book astfel incat
sa tina cont si de numarul de camere.
"""


class BiggerHotel(Hotel):
	def __init__(self, night, tax, num):
		super(BiggerHotel, self).__init__(night, tax)
		self.num = num
	def book(self, r):
		self.rez.append(r)
		self.num = self.num - 1
	
	def available(self, r):
		for rezervare in self.rez:
			if (rezervare.contains(r.start) or rezervare.contains(r.end)) and self.num < 1:
				return False
		return True

                        
"""
Ex. 6
Definiti clasa Point ce primeste doua coordonate (x, y) are metode interne 
pentru operatii aritmetice, definiti aceste metode astfel incat sa putem 
aduna/scadea doua puncte. Sugestie __add__, __sub__.
 
Exemplu:
    p1 = Point(1, 2)
    p2 = Point(2, 3)
    p3 = p1 + p2
    p4 = p1 - p2
"""


class Point(object):
	x = 0
	y = 0
	def __init__(self, x, y):
		self.x = x
		self.y = y

	def __add__(self, B):
		try:
			isinstance(B, Point)
		except ValueError:
			print "invalid operation"
		x = self.x + B.x
		y = self.y + B.y
		return Point(x, y)

	def __sub__(self, B):
		try:
			isinstance(B, Point)
		except ValueError:
			print "invalid operation"
		x = self.x - B.x
		y = self.y - B.y
		return Point(x, y)
 
 
"""
BONUS!!!!!!
Ex. 7 
Implementati clasa Complex pentru reprezentarea numerelor complexe, ce primeste 
partea reala si imaginara la initializare. Definiti operatiile de adunare, 
scadere, inmultire si impartire. Asemanator cu ex. 6.
 
Exemplu:
    c1 = Complex(1, 1)
    c2 = Complex(2, 2)
    c3 = c1 + c2
    c4 = c1 - c2
    c5 = c2 * c1
    c6 = c2 / c1
"""


class Complex(object):
	r = 0
	i = 0
	def __init__(self, re, im):
		self.r = re
		self.i = im

	def __add__(self, B):
		try:
			isinstance(B, Complex)
		except ValueError:
			print "invalid operation"
		r = self.r + B.r
		i = self.i + B.i
		return Complex(r, i)

	def __sub__(self, B):
		try:
			isinstance(B, Complex)
		except ValueError:
			print "invalid operation"
		r = self.r - B.r
		i = self.i - B.i
		return Complex(r, i)

	def __mul__(self, B):
		try:
			isinstance(B, Complex)
		except ValueError:
			print "invalid operation"
		re = self.r * B.r - self.i * B.i
		im = self.r * B.i + self.i * B.r
		return Complex(re, im)

	def __div__(self, B):
		try:
			isinstance(B, Complex)
		except ValueError:
			print "invalid operation"
		numitor = (float)(B.r * B.r + B.i * B.i)
		re = (float)(self.r*B.r+self.i*B.i)/numitor;
		im = (float)(B.r*self.i-self.r*B.i)/numitor;
		return Complex(re, im)
 

# Functia ajutatoare folosita la testare.
def test(got, expected):
    if got == expected:
        prefix = ' OK '
    else:
        prefix = '  X '
    print '{0} got: {1}, expected: {2}'.format(prefix, got, expected)
 
 
# Functia care testeaza rezultatele.
from datetime import datetime, timedelta


def main():
    print "\nTeste pentru clasa DateFormat"
    d = datetime(2012, 6, 24, 12, 23, 20, 10101)
    
    df = DateFormat("%Y-%m-%d")
    test(df.format(d), "2012-06-24")
    test(df.parse("2013-06-25"), datetime(2013, 6, 25))
    
    df = DateFormat("%d/%m/%y %H:%M:%S")
    test(df.format(d), "24/06/12 12:23:20")
    test(df.parse("25/06/13 23:23:23"), datetime(2013, 6, 25, 23, 23, 23))
    
    df = DateFormat("%j from %Y at time %H:%M:%S")
    test(df.format(d), "176 from 2012 at time 12:23:20")
    test(df.parse("175 from 2012 at time 00:00:00"), datetime(2012, 6, 23))
    
    print "\nTeste pentru clasa DateRange"
    start = datetime(2012, 6, 24)
    end = datetime(2014, 1, 1)
    dr = DateRange(start, end)
    
    test(dr.contains(datetime(2012, 6, 24)), True)
    test(dr.contains(datetime(2014, 1, 1)), False)
    test(dr.contains(datetime(2013, 6, 24)), True)
    
    print "\nTeste pentru clasa Hotel"
    hotel = Hotel(30, 15)
    
    dr = DateRange(datetime(2013, 6, 20), datetime(2013, 6, 22))
    test(hotel.price(dr), 75)
    dr = DateRange(datetime(2013, 6, 20), datetime(2013, 6, 20))
    test(hotel.price(dr), 45)
    dr = DateRange(datetime(2013, 6, 20), datetime(2013, 6, 30))
    test(hotel.price(dr), 315)
    
    print "\nTeste pentru clasa Hotel 2"
    hotel.book(DateRange(datetime(2013, 6, 20), datetime(2013, 6, 22)))
    hotel.book(DateRange(datetime(2013, 6, 25), datetime(2013, 6, 28)))
    hotel.book(DateRange(datetime(2013, 6, 21), datetime(2013, 6, 23)))
               
    dr = DateRange(datetime(2013, 6, 18), datetime(2013, 6, 20))           
    test(hotel.available(dr), True)
    
    dr = DateRange(datetime(2013, 6, 18), datetime(2013, 6, 21))           
    test(hotel.available(dr), False)
    
    dr = DateRange(datetime(2013, 6, 21), datetime(2013, 6, 24))           
    test(hotel.available(dr), False)
    
    dr = DateRange(datetime(2013, 6, 28), datetime(2013, 6, 30))           
    test(hotel.available(dr), True)
    
    print "\nTeste pentru clasa BiggerHotel"
    hotel = BiggerHotel(30, 15, 3)

    dr = DateRange(datetime(2013, 6, 20), datetime(2013, 6, 22))
    test(hotel.price(dr), 75)
    dr = DateRange(datetime(2013, 6, 20), datetime(2013, 6, 20))
    test(hotel.price(dr), 45)
    dr = DateRange(datetime(2013, 6, 20), datetime(2013, 6, 30))
    test(hotel.price(dr), 315)
    
    dr = DateRange(datetime(2013, 6, 20), datetime(2013, 6, 22))
    hotel.book(dr)
    test(hotel.available(dr), True)
    hotel.book(dr)
    test(hotel.available(dr), True)
    hotel.book(dr)
    test(hotel.available(dr), False)

    dr = DateRange(datetime(2013, 6, 21), datetime(2013, 6, 23))
    hotel.book(dr)
    test(hotel.available(dr), False)
    
    dr = DateRange(datetime(2013, 6, 22), datetime(2013, 6, 23))
    hotel.book(dr)    
    hotel.book(dr)
    test(hotel.available(dr), True)
    hotel.book(dr)
    test(hotel.available(dr), False)

    print "\nTeste pentru clasa Point"
    p1 = Point(1, 2)
    p2 = Point(3, 3)
    
    p3 = p1 + p2
    test(p3.x, p1.x + p2.x)
    test(p3.y, p1.y + p2.y)
    
    p3 = p1 - p2
    test(p3.x, p1.x - p2.x)
    test(p3.y, p1.y - p2.y)
    
    print "\nTeste pentru clasa Complex"
    c1 = Complex(1, 2)
    c2 = Complex(-2, 3)
    
    c3 = c1 + c2
    test(c3.r, -1)
    test(c3.i, 5)
    
    c3 = c1 - c2
    test(c3.r, 3)
    test(c3.i, -1)
    
    c3 = c1 * c2
    test(c3.r, -8)
    test(c3.i, -1)
    
    c3 = c2 / c1
    test(c3.r, 0.8)
    test(c3.i, 1.4)
    
if __name__ == '__main__':
    main()