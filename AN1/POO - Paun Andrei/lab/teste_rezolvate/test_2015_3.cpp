///NUME PRENUME GRUPA C++11 GCC Compiler
#include <iostream>
#include <vector>
#include <string>

using namespace std;

class LOT
{
private:
    int cantitate;
    string data_primirii;
    int pret_per_unitate;
public:
    LOT(int _cantitate,string _data_primirii, int _pret_per_unitate);
    ~LOT();
    bool Apartine_interval(string _data1, string _data2);
    void Afisare();
};

class Produs
{
private:
    string nume;
    int tip_unitate_masura;
    vector<LOT*> loturi;
public:
    Produs(string _nume, int _masura);
    ~Produs();
    void Afisare();
    void AddLOT();
    void Afisare_LOT_perioada(string _data1, string _data2);

};

class Produs_Perisabil : public Produs
{
private:
    int perioada_valabilitate_lot;
public:
    Produs_Perisabil(string _nume, int _masura);
    ~Produs_Perisabil();
};

class Produs_Promotie : public Produs
{
private:
    int procent_discount_lot; // se aplica la LOT::pret_per_unitate
public:
    Produs_Promotie(string _nume, int _masura);
    ~Produs_Promotie();
};

class Magazin
{
private:
    vector<Produs*> produse;
public:
    Magazin();
    ~Magazin();
    void Adauga_un_produs_nou();
    void Afiseaza_toate_produsele();
    void Adauga_lot_nou(int _nr);
    void Afiseaza_toate_loturile_din_peroada_data(string _data1, string _data2);
    void Afiseaza_toate_loturile_curente_valide(); //lot de produse care pot fi comercializate
    void Vinde_cantitate_produs();
};

LOT::LOT(int _cantitate,string _data_primirii, int _pret_per_unitate)
{
    cantitate=_cantitate;
    data_primirii=_data_primirii;
    pret_per_unitate=_pret_per_unitate;
}
LOT::~LOT()
{
    //
}
bool LOT::Apartine_interval(string _data1, string _data2)
{
    if(data_primirii > _data1 && data_primirii < _data2) return true;
    return false;
}
void LOT::Afisare()
{
    cout<<"Data primirii: "<<data_primirii<<" Cantitate: "<<cantitate<<" Pret/unitate: "<<pret_per_unitate;
}
Produs::Produs(string _nume, int _masura)
{
    nume = _nume;
    tip_unitate_masura = _masura;
}
Produs::~Produs()
{
    //
}
void Produs::Afisare()
{
    cout<<"Nume: "<<nume<<" Unitate: ";
    if(tip_unitate_masura==1) cout<<"Bucata";
    else if(tip_unitate_masura==2) cout<<"Greutate";
    else if(tip_unitate_masura==3) cout<<"Volum";
}
void Produs::AddLOT()
{
    int _cantitate;
    string _data_primirii;
    int _pret_per_unitate;
    cout<<endl<<"Introduceti Data(De forma dd.mm.yyyy): ";
    cin>>_data_primirii;
    cout<<"Introduceti Cantitatea(bucati, kilograme sau litri): ";
    cin>>_cantitate;
    cout<<"Introduceti Pretul per unitate: ";
    cin>>_pret_per_unitate;
    loturi.push_back(new LOT(_cantitate, _data_primirii, _pret_per_unitate));
}
void Produs::Afisare_LOT_perioada(string _data1, string _data2)
{
	for (auto it : loturi)
	{
		if(it->Apartine_interval(_data1, _data2)) it->Afisare();
	}
}
Produs_Perisabil::Produs_Perisabil(string _nume, int _masura) : Produs(_nume, _masura)
{
    //
}
Produs_Promotie::Produs_Promotie(string _nume, int _masura) : Produs(_nume, _masura)
{
    //
}
Magazin::Magazin()
{

}
Magazin::~Magazin()
{
    for (auto it : produse)
    {
		delete it;
	}
}
void Magazin::Adauga_un_produs_nou()
{
    string _nume;
    int _masura, tip;
    cout<<endl<<"Introduce-ti numele: ";
    cin>>_nume;
    cout<<endl<<"Alegeti Unitatea de masura: 1. Bucata <> 2. Greutate <> 3. Volum: ";
    cin>>_masura;
    cout<<endl<<"Alegeti Tipul produsului: 1. Simplu <> 2. Perisabil <> 3. Promotie: ";
    cin>>tip;
    switch(tip)
    {
        case 1: {produse.push_back(new Produs(_nume, _masura)); break;}
        case 2: {produse.push_back(new Produs_Perisabil(_nume, _masura));break;}
        case 3: {produse.push_back(new Produs_Promotie(_nume, _masura));break;}
        default : {produse.push_back(new Produs(_nume, _masura));break;}
    }
}
void Magazin::Afiseaza_toate_produsele()
{
    int i=1;
	for (auto it : produse)
	{
        cout<<endl<<i<<") "; i++;
		it->Afisare();
	}
}
void Magazin::Adauga_lot_nou(int _nr)
{
    produse[_nr-1]->AddLOT();
}
void Magazin::Afiseaza_toate_loturile_din_peroada_data(string _data1, string _data2)
{
	for (auto it : produse)
	{
	    it->Afisare();
	    cout<<endl;
		it->Afisare_LOT_perioada(_data1, _data2);
	}
}

int main()
{
    int nr;
    string data1, data2;
    Magazin Carrefour;
    Carrefour.Adauga_un_produs_nou();
    Carrefour.Adauga_un_produs_nou();
    Carrefour.Afiseaza_toate_produsele();
    cout<<endl<<endl<<"Introduce-ti numarul Produs: ";
    cin>>nr;
    Carrefour.Adauga_lot_nou(nr);
    Carrefour.Adauga_lot_nou(nr);
    cout<<endl<<endl<<"Introduce-ti perioada: ";
    cin>>data1;
    cin>>data2;
    Carrefour.Afiseaza_toate_loturile_din_peroada_data(data1,data2);


    return 0;
}
