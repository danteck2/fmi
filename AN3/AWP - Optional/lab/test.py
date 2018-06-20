# Test 01.11.2017

# Toate exercitiile au teste apelate sub fiecare rezolvare


# Solutie Exercitiu 1

from datetime import datetime

f = open ('age.txt','w+')

def printData(myList):
    for l in myList:
        line = ""
        line = line + l["name"]
        line = line + ", "
        
        datetime_object = datetime.strptime(l["birthday"], '%d-%m-%Y')
        year = datetime_object.year
        line = line + str(year) + "\n"
        
        f.write(line)
    f.close()
        
printData([
    {
        "name":"Ana",
        "birthday":"23-03-1988"
    },
    {
        "name":"Matei",
        "birthday":"19-09-1999"
    },
    {
        "name":"Andreea",
        "birthday":"03-12-1976"
    },
    {
        "name":"Catalin",
        "birthday":"03-07-1960"
    }])
    
    
    
    
# Solutie Exercitiu 2

def checkUppercase(myList):
    newList = [word for word in myList if word.isupper()]
    print newList
        
checkUppercase(["Apple", "ANA", "Dolar", "MOON"])
        
        
        
# Solutie Exercitiu 3

def moneyFromStudents(myDictionary):
    newDictionary = {
    }
    for stud in myDictionary:
        if stud[1] != 0:
            newDictionary[stud[0]] = stud[1] * 20
    print newDictionary
    
moneyFromStudents([("Ana",3),("Marius",0),("Andrei",1),("Ioana",4)])



# Solutie Exercitiu 4

class FidelityCard(object):
    __points = 0
    def __init__(self, first_name, last_name):
        self.first_name = first_name
        self.last_name = last_name
        pass
    
    def setPoints(self, points):
        self.__points = points
        pass

    def getPoints(self):
        return "Aveti " + str(self.__points) + " puncte"
        
    def buy(self, amount):
        self.__points += amount * 0.01
        
    def usePoints(self, points):
        if self.__points >= points:
            self.__points -= points
        else:
            print "Your points value is lower than " + str(points)
    
    points = property(getPoints, setPoints)
    
myFidelityCard = FidelityCard("Prenume", "Nume")
myFidelityCard.points = 100
myFidelityCard.buy(2000)
myFidelityCard.usePoints(600)
print myFidelityCard.getPoints()




# Solutie Exercitiu 5

class GoldFidelityCard(FidelityCard):
    def __init__(self, first_name, last_name, bonus):
        super(GoldFidelityCard, self).__init__(first_name, last_name)
        self.bonus = bonus
        pass
    
    def buy(self, amount):
        self._FidelityCard__points +=  amount * (0.01 + (self.bonus / 100.0))
    
myGoldFidelityCard = GoldFidelityCard("Prenume", "Nume", 10)
print myGoldFidelityCard.getPoints()
myGoldFidelityCard.buy(4000)
print myGoldFidelityCard.getPoints()