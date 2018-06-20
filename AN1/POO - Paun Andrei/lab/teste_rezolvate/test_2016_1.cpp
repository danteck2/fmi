///NUME PRENUME GRUPA C++11 GCC Compiler
#include <iostream>
#include <cstring>
#include <vector>
#include <string>

using namespace std;

class Ingredient
{
    string denIng;
    double pretIng;
    int cantIng;
    char masIng[10];
public:
    Ingredient();
    Ingredient(string _denIng, double _pretIng, int _cantIng, char _masIng[]);
    ~Ingredient();
    friend istream& operator>>(istream &i,Ingredient &ob);
    friend ostream& operator<<(ostream &i, Ingredient &ob);
    Ingredient operator=(const Ingredient aux);
    Ingredient operator+(const int aux);
    Ingredient operator++(int);
    Ingredient operator-=(const double aux);
    void del();
    bool cmp(Ingredient aux);
    double GetPretIng();
    void Afisare();
};
class Pizza
{
    static int contor;
    int codPizza;
    char *denPizza;
    Ingredient ingPizza[50];///to-vector
    int nringPizza;
    bool vegPizza;
public:
    Pizza();
    Pizza(char _denPizza[], Ingredient _ingPizza[], int _nringPizza, bool _vegPizza);
    Pizza(const Pizza &aux);
    ~Pizza();
    Pizza operator=(const Pizza aux);
    Pizza operator-(Ingredient aux);
    Pizza operator+(const Ingredient aux);
    void nume(char nume[]);
    char* nume();
    double pret();
    virtual void Afisare();
    virtual void AfisareIng();
};
int Pizza::contor=0;
class PizzaSpec : public Pizza
{
    vector<Ingredient *> ingPizza;
    int nringPizza;
public:
    PizzaSpec(char _denPizza[], Ingredient _ingPizza[], int _nringPizza, bool _vegPizza, Ingredient _ingPizzaSup[], int _nringPizzaSup);
    ~PizzaSpec();
    virtual void Afisare();
    virtual void AfisareIng();
};
class Bautura
{
    char denBaut[50];
    double pretBaut;
public:
    Bautura();
    ~Bautura();
};
class OfSpec
{
    Pizza *pizza;
    Bautura bautura;
    double pretOf;
public:
    OfSpec();
    ~OfSpec();
};
class Pizzerie
{
    vector<Ingredient *> ingredi;//ingrediente
    vector<Pizza *> sorti;//sortimente
public:
    Pizzerie();
    ~Pizzerie();
    void AfisareIng();
    void AfisareSort();
};
istream& operator>>(istream &i,Ingredient &ob)
{
    cout<<endl<<"Introduceti denumirea ingridient nou: ";
    getline(cin, ob.denIng);
    cout<<"Introduceti pret: ";
    i>>ob.pretIng;
    cout<<"Introduceti cantitatea: ";
    i>>ob.cantIng;
    cout<<"Introduceti unitatea de masura: ";
    i>>ob.masIng;
    return i;
}
ostream& operator<<(ostream &i, Ingredient &ob)
{
    i<<ob.denIng<<" Pret: "<<ob.pretIng<<" Cantitate: "<<ob.cantIng<<' '<<ob.masIng;
}
Ingredient::Ingredient()
{
    //
}
Ingredient::Ingredient(string _denIng, double _pretIng, int _cantIng, char _masIng[])
{
    denIng = _denIng;
    pretIng = _pretIng;
    cantIng = _cantIng;
    strcpy(masIng, _masIng);
}
Ingredient::~Ingredient()
{
    //
}
Ingredient Ingredient::operator=(const Ingredient aux)
{
    denIng = aux.denIng;
    pretIng = aux.pretIng;
    cantIng = aux.cantIng;
    strcpy(masIng, aux.masIng);
    return *this;
}
Ingredient Ingredient::operator+(const int aux)
{
    cantIng = cantIng + aux;
    return *this;
}
Ingredient Ingredient::operator++(int)
{
    cantIng++;
    return *this;
}
Ingredient Ingredient::operator-=(const double aux)
{
    pretIng = pretIng - aux;
    return *this;
}
void Ingredient::del()
{
    cantIng = 0;
}
bool Ingredient::cmp(Ingredient aux)
{
    if(denIng.compare(aux.denIng)==0 && pretIng==aux.pretIng)
        return true;
    return false;
}
double Ingredient::GetPretIng()
{
    return pretIng*1.5;
}
void Ingredient::Afisare()
{
    cout<<"Ingredient: "<<denIng<<" pret: "<<pretIng<<" cantitate: "<<cantIng<<' '<<masIng;
}
Pizza::Pizza()
{
    denPizza = new char[50];
}
Pizza::Pizza(char _denPizza[], Ingredient _ingPizza[], int _nringPizza, bool _vegPizza)
{
    contor++;
    codPizza = contor;
    denPizza = new char[50];
    strcpy(denPizza, _denPizza);
    for(int i=0; i<_nringPizza; i++)
        ingPizza[i] = _ingPizza[i];
    nringPizza = _nringPizza;
    vegPizza = _vegPizza;
}
Pizza::Pizza(const Pizza &aux)
{
    contor++;
    codPizza = contor;
    denPizza = new char[50];
    strcpy(denPizza, aux.denPizza);
    for(int i=0; i<aux.nringPizza; i++)
        ingPizza[i] = aux.ingPizza[i];
    nringPizza = aux.nringPizza;
    vegPizza = aux.vegPizza;
}
Pizza::~Pizza()
{
    delete [] denPizza;
}
Pizza Pizza::operator=(const Pizza aux)
{
    contor++;
    codPizza = contor;
    for(int i=0; i<aux.nringPizza; i++)
        ingPizza[i] = aux.ingPizza[i];
    nringPizza = aux.nringPizza;
    vegPizza = aux.vegPizza;
    return *this;
}
Pizza Pizza::operator-(Ingredient aux)
{
    Ingredient zero;
    for(int i=0; i<nringPizza; i++)
    {
        if(aux.cmp(ingPizza[i])==true)
        {
            for(int j=i; j<nringPizza-1; j++)
                ingPizza[j] = ingPizza[j+1];
            ingPizza[nringPizza-1]=zero;
            ingPizza[nringPizza]=zero;
            nringPizza--;
        }

    }
    return *this;
}
Pizza Pizza::operator+(const Ingredient aux)
{
    Pizza copie;
    for(int i=0; i<nringPizza; i++) copie.ingPizza[i] = ingPizza[i];
    copie.nringPizza = nringPizza;
    copie.vegPizza = vegPizza;
    copie.ingPizza[copie.nringPizza] = aux;
    copie.nringPizza++;
    return copie;
}
void Pizza::nume(char nume[])
{
    denPizza = new char[strlen(nume)+1];
    strcpy(denPizza, nume);
}
char* Pizza::nume()
{
    return denPizza;
}
double Pizza::pret()
{
    double pretPizza=0;
    for(int i=0; i<nringPizza; i++)
    {
        pretPizza = pretPizza + ingPizza[i].GetPretIng();
    }
    return pretPizza;
}
void Pizza::Afisare()
{
    cout<<"Pizza "<<denPizza<<" nr ingridiente: "<<nringPizza<<" vegetariana: ";
    if(vegPizza==true) cout<<"da";
    else cout<<"nu";
    cout<<" cod: "<<codPizza;
}
void Pizza::AfisareIng()
{
    for(int i=0; i<nringPizza; i++)
    {
        cout<<endl;
        ingPizza[i].Afisare();
    }
}
int main()
{
    Ingredient lista[5] = {Ingredient("piept de pui", 15, 1, "kg"), Ingredient("sare", 0.75, 10, "gram"), Ingredient("salam", 1.5, 5, "felii"), lista[2],Ingredient()}, i1("sunca", 3, 2, "felii");
    cin >> lista[4]; //se citeste un nou ingredient
    lista[3] = lista[3] + 3;//se adauga 3 felii de salam la ingridient
    lista[1]++;//se adauga un gram de sare la ingredinent
    lista[0]-=2.5;//se scade o valoare din pretul ingredientului
    lista[2].del();//igredientul nu mai este disponibil
    for(int i=0; i<5; i++) cout<<"Ingredient: "<<lista[i]<<endl;
    Pizza p1("Rustica", lista, 4, false);   //se creaza un sortiment nou de pizza
    Pizza p2 = p1, p3;//se creeaza doua sortimente noi de pizza dintre care unul cu aceleasi ingridiente ca pizza p1
    p2 = p2 - lista[2];//se scoate ingredientul respectiv din reteta sortimentului
    p3 = p2 + i1; //se stabileste reteta pizzei p3 din ingridientele pizzei p2 la care se adauga ingridientul i1
    p3.nume("Prosciuto");//este schimbata denumirea pizzei p3
    cout<<"Pizza "<<p1.nume()<<" "<<p1.pret()<<" lei"<<endl;
    cout<<"Pizza "<<p2.nume()<<" "<<p2.pret()<<" lei"<<endl;
    cout<<"Pizza "<<p3.nume()<<" "<<p3.pret()<<" lei";
    cout<<endl<<"--------------------end demo main--------------------"<<endl<<endl;
    cout<<endl;
    p1.Afisare();
    cout<<endl;
    p1.AfisareIng();
    cout<<endl<<endl;
    p2.Afisare();
    cout<<endl;
    p2.AfisareIng();
    cout<<endl<<endl;
    p3.Afisare();
    cout<<endl;
    p3.AfisareIng();
    cout<<endl;


    return 0;
}
