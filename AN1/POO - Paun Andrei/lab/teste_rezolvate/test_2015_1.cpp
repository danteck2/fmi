///NUME PRENUME GRUPA C++11 GCC Compiler
#include <iostream>
#include <vector>
#include <string>

using namespace std;

class Ingredient
{
private:
    string denIng; //denumirea ingredientului
    double pretIng; // pretul unei unitati
    int cantIng; // cantitatea de ingredient folosita
    string masIng; //unitatea de masura pt ingredient
    bool dispoIng; // disponibilitate
public:
    Ingredient(string _denIng, double _pretIng, int _cantIng, string _masIng);
    ~Ingredient();
    void Citire_Ingredient();
    void operator=(Ingredient _Ing);
    void operator+(int _cantIng);
    void operator++(int i=1);
    void operator-=(int _pretIng);
    void del();
    string Get_denIng(); // returneaza denumirea ingredientului
    void Afisare();
    //friend stream &operator >>(Ingredient )
};

class Pizza
{
private:
    int codPizza; //codul numeric al sortimentului
    string denPizza; //denumire pizza
    vector<Ingredient*> ingPizza; //lista de ingrediente care intra in componeta
    int nringPizza; // nr de ingrediente
    bool vegPizza; //vegetariana sau nu
public:
    Pizza(string _denPizza, vector<Ingredient*> _ingPizza, int _nringPizza,  bool _vegPizza);
    ~Pizza();
    void operator=(Pizza _pizza);
    void operator-(Ingredient _Ing);
    void operator+(const Ingredient _Ing);
    void nume(string _denPizza);
    string nume();
};

Ingredient::Ingredient(string _denIng, double _pretIng, int _cantIng, string _masIng)
    : dispoIng(true)
{
    denIng = _denIng;
    pretIng = _pretIng;
    cantIng = _cantIng;
    masIng = _masIng;
}
Ingredient::~Ingredient()
{
    ///
}
void Ingredient::Citire_Ingredient()
{
        cout << endl << "Denumirea: ";
        string _denIng;
        getline(cin,_denIng);
        cout << endl << "Pretul: ";
        double _pretIng;
        cin>>_pretIng;
        cout << endl << "Cantitatea: ";
        int _cantIng;
        cin>>_cantIng;
        cout << endl << "Unitatea de masura(Ex: kg, felii): ";
        string _masIng;
        cin>>masIng;

        denIng = _denIng;
        pretIng = _pretIng;
        cantIng = _cantIng;
        masIng = _masIng;
        dispoIng = true;
}
void Ingredient::operator=(Ingredient _Ing)
{
    denIng = _Ing.denIng;
    pretIng = _Ing.pretIng;
    cantIng = _Ing.cantIng;
    masIng = _Ing.masIng;
}
void Ingredient::operator+(int _cantIng)
{
    if(dispoIng==true)
        cantIng = cantIng + _cantIng;
    else
        cout<<endl<<"Ingridientul nu este disponibil!!!";
}
void Ingredient::operator++(int i)
{
    if(dispoIng==true)
    cantIng++;
    else
        cout<<endl<<"Ingridientul nu este disponibil!!!";
}
void Ingredient::operator-=(int _pretIng)
{
    if(dispoIng==true)
    pretIng = pretIng - _pretIng;
    else
        cout<<endl<<"Ingridientul nu este disponibil!!!";
}
void Ingredient::del()
{
    dispoIng = false;
}
string Ingredient::Get_denIng()
{
    return denIng;
}
void Ingredient::Afisare()
{
    cout<<"Denumire "<<denIng<<endl;


}
Pizza::Pizza(string _denPizza, vector<Ingredient*> _ingPizza, int _nringPizza,  bool _vegPizza)
{
    denPizza = _denPizza;
    ingPizza = _ingPizza;
    nringPizza = _nringPizza;
    vegPizza = _vegPizza;
}
Pizza::~Pizza()
{
    ///
}
void Pizza::operator=(Pizza _pizza)
{
    _pizza.denPizza = denPizza;
    _pizza.ingPizza = ingPizza;
    _pizza.nringPizza = nringPizza;
    _pizza.vegPizza = vegPizza;
}
void Pizza::operator-(Ingredient _Ing)
{
    for(auto it : ingPizza)
    {
        string Ing;
        Ing = it->Get_denIng();
        if(Ing.compare(_Ing.Get_denIng())==0) delete it;
    }
}
void Pizza::operator+(const Ingredient _Ing)
{
   // ingPizza.push_back(_Ing);

}
void Pizza::nume(string _denPizza)
{
    denPizza = _denPizza;
}
string Pizza::nume()
{
    return denPizza;
}
int main()
{
    Ingredient lista[3] = {Ingredient("pui", 15, 1, "kg"), Ingredient("sare", 0.75, 10, "gram"),Ingredient("salam", 1.5, 5, "felii") }, i1("sunca", 3, 2, "felii");
    lista[1].Citire_Ingredient();
    lista[1]=lista[2];
    lista[1]+3;
    lista[1]++;
    lista[0]-=2.5;
    lista[2].del();

    for(int i=0; i<4; i++)
    {
        lista[i].Afisare();
        //it->Afisare();
    }

    return 0;
}
