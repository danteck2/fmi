# coding=utf-8
"""
Ex. 1
Definiți clasa DateRange care primește la inițializare o valoare start și o
valoare end, ambele fiind obiecte datetime.date.
Adăugați proprietatea:
    - days - returnează numărul de zile din intervalul [start, end]
Adăugați metoda:
    - contains - primește un obiect date și returnează True dacă data se
                  află în intervalul [start, end].
"""

from datetime import datetime, date

class DateRange(object):
    def __init__(self, start, end):
        self.start = start
        self.end = end
        pass

    def set_days(self):
        self.interval = self.end - self.start
        pass

    def get_days(self):
        return (self.end - self.start).days

    days = property(get_days, set_days)

    def contains(self, obj):
        if obj >= self.start and obj <= self.end :
            return True
        else:
            return False

"""
Ex. 2
Definiți clasa Car cu atributele: brand, model, daily_price.
 Adăugați metoda:
    - get_rental_price(period) - returnează prețul de închiriere pentru
    perioada respectivă.
Permiteți afișarea informațiilor despre mașină în formatul:
    "Acesta este un brand model și prețul la închiriere este daily_price"
"""


class Car(object):

    def __init__(self, brand, model, daily_price):
        self.brand = brand
        self.model = model
        self.daily_price = daily_price
        pass

    def get_rental_price(self, period):
        return period.days * self.daily_price

    def __str__(self):
        return "Acesta este un " + self.brand + " " +  self.model + " si pretul la inchiriere este " + str(self.daily_price)


"""
Ex. 3
Definiți clasa Limousine care extinde clasa Car, și are în plus următoarele
atribute: driver_salary. La prețul de închiriere va adăuga salariul șoferului.
"""


class Limousine(Car):
    def __init__(self, brand, model, daily_price, driver_salary):
        super(Limousine, self).__init__(brand, model, daily_price)
        self.driver_salary = driver_salary
        pass
    def get_rental_price(self, period):
        return period.days * self.daily_price + self.driver_salary
        
"""
Ex. 4
Definiți clasa Person cu atributele: first_name, last_name și birthday.
"""


class Person(object):
    def __init__(self, first_name, last_name, birthday):
        self.first_name = first_name
        self.last_name = last_name
        self.birthday = birthday
        pass

"""
Ex. 5
Definiți clasa CarRental cu atributele: cars. Scrieți metodele:
    - add_car(car) - adaugă o mașină în centrul de închirieri
    - get_cars - returnează o listă cu mașinile filtrate după brand și preț
    maxim.
    - get_price(customer, car, period) - returnează prețul final al tranzacției.
      Dacă ziua de naștere a clientului este în intervalul închirierii, se
      aplică o reducere de 10%.

"""


class CarRental(object):
    cars_list = list()
    def __init__(self):
        pass

    def add_car(self, car):
        self.cars_list.append(car)
        pass

    def get_cars(self, brand=None, max_price=None):
        if brand == None and max_price == None:
            return self.cars_list
        if brand == None:
            return [car for car in self.cars_list if car.daily_price <= max_price]
        if max_price == None:
            return [car for car in self.cars_list if car.brand == brand]
        return [car for car in self.cars_list if car.brand == brand and car.daily_price <= max_price]
        

    def get_price(self, customer, car, period):
        if period.contains(customer.birthday):
            return (0.9 * car.get_rental_price(period))
        else:
            return car.get_rental_price(period)
        pass


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
    def __init__(self, x, y):
        self.x = x
        self.y = y
        pass

    def __add__(self, p2):
        return Point((self.x + p2.x), (self.y + p2.y))

    def __sub__(self, p2):
        return Point((self.x - p2.x), (self.y - p2.y))

# Functia ajutatoare folosita la testare.
def test(got, expected):
    if got == expected:
        prefix = ' OK '
    else:
        prefix = '  X '
    print '{0} got: {1}, expected: {2}'.format(prefix, got, expected)


# Functia care testeaza rezultatele.
def main():
    from datetime import date, timedelta
    print "\nTeste pentru clasa DateRange"
    start = date(2012, 6, 24)
    end = date(2014, 1, 1)
    dr = DateRange(start, end)

    test(dr.contains(date(2012, 6, 24)), True)
    test(dr.contains(date(2014, 1, 1)), True)
    test(dr.contains(date(2013, 6, 24)), True)
    test(dr.contains(date(2014, 6, 24)), False)
    test(dr.days, 556)

    print "\nTeste pentru clasa Car"
    c = Car('Volvo', 'S60', 500)
    dr = DateRange(date.today(), date.today() + timedelta(days=7))
    test(c.get_rental_price(dr), 3500)
    test(str(c), "Acesta este un Volvo S60 si pretul la inchiriere este 500")

    print "\nTeste pentru clasa Limousine"
    l = Limousine('Mercedes', 'Diplomat Edition', 1200, 800)
    dr = DateRange(date.today(), date.today() + timedelta(days=3))
    test(l.get_rental_price(dr), 4400)

    print "\nTeste pentru clasa CarRental"
    c2 = Car('Mercedes', 'C-Class', 700)
    cr = CarRental()
    cr.add_car(c)
    cr.add_car(l)
    cr.add_car(c2)

    test(cr.get_cars(), [c, l, c2])
    test(cr.get_cars('Mercedes'), [l, c2])
    test(cr.get_cars('Mercedes', 700), [c2])
    test(cr.get_cars(max_price=600), [c])
    test(cr.get_cars(max_price=400), [])

    p = Person('Jane', 'Geller', date(1992, 12, 5))
    p2 = Person('John', 'Stain', date(1990, 12, 15))
    dr = DateRange(date(2015, 12, 1), date(2015, 12, 10))
    test(cr.get_price(p, c, dr), 4050)
    test(cr.get_price(p2, c2, dr), 6300)

    print "\nTeste pentru clasa Point"
    p1 = Point(1, 2)
    p2 = Point(3, 3)

    p3 = p1 + p2
    test(p3.x, p1.x + p2.x)
    test(p3.y, p1.y + p2.y)

    p3 = p1 - p2
    test(p3.x, p1.x - p2.x)
    test(p3.y, p1.y - p2.y)


if __name__ == '__main__':
    main()