///NUME PRENUME GRUPA C++11 GCC Compiler
#include <iostream>
#include <cstring>
#include <cstdlib>
#include <vector>

using namespace std;

class Angajat
{
    char numpr[50];
    char dataint[20];//data intrarii in vigoare a contractului
    int prima;
public:
    Angajat(char _numpr[], char _dataint[], int _prima);
    ~Angajat();
    char* GetNum();
    char* GetDataint();
    int GetPrima();
    virtual void Afisare()=0;
    virtual void AfisareCopii()=0;
    virtual void AfisareWeek2()=0;
    virtual void AfisareOra2015()=0;
};
class AngajatPer : public Angajat
{
    int copii;
public:
    AngajatPer(char _numpr[], char _dataint[], int _prima, int _copii);
    ~AngajatPer();
    virtual void Afisare();
    virtual void AfisareCopii();
    virtual void AfisareWeek2();
    virtual void AfisareOra2015();
};
class AngajatTESA : public Angajat
{
    int week;
public:
    AngajatTESA(char _numpr[], char _dataint[], int _prima, int _week);
    ~AngajatTESA();
    virtual void Afisare();
    virtual void AfisareCopii();
    virtual void AfisareWeek2();
    virtual void AfisareOra2015();
};
class AngajatOra : public Angajat
{
    char dataout[20];
public:
    AngajatOra(char _numpr[], char _dataint[], int _prima, char _dataout[]);
    ~AngajatOra();
    virtual void Afisare();
    virtual void AfisareCopii();
    virtual void AfisareWeek2();
    virtual void AfisareOra2015();
};
class Firma
{
    vector<Angajat *> angajati;
    int prima;
public:
    Firma();
    ~Firma();
    void Citire();
    void Afisare();
    void AfisareCopii();
    void AfisareWeek2();
    void AfisareOra2015();
};
Angajat::Angajat(char _numpr[], char _dataint[], int _prima)
{
    strcpy(numpr, _numpr);
    strcpy(dataint, _dataint);
    prima = _prima;
}
Angajat::~Angajat()
{
    //
}
char* Angajat::GetNum()
{
    return numpr;
}
char* Angajat::GetDataint()
{
    return dataint;
}
int Angajat::GetPrima()
{
    return prima;
}
AngajatPer::AngajatPer(char _numpr[], char _dataint[], int _prima, int _copii) : Angajat(_numpr, _dataint, _prima)
{
    copii = _copii;
}
AngajatPer::~AngajatPer()
{
    //
}
void AngajatPer::Afisare()
{
    char _numpr[50], _dataint[20];
    int _prima = GetPrima();
    strcpy(_numpr, GetNum());
    strcpy(_dataint, GetDataint());
    cout<<_numpr<<", contract permanent, "<<_dataint<<", lucrativ, minori: "<<copii<<", prima: "<<_prima<<" RON";
}
void AngajatPer::AfisareCopii()
{
    if(copii>0)
    {
        char _numpr[50], _dataint[20];
        int _prima = GetPrima();
        strcpy(_numpr, GetNum());
        strcpy(_dataint, GetDataint());
        cout<<_numpr<<", contract permanent, "<<_dataint<<", lucrativ, minori: "<<copii<<", prima: "<<_prima<<" RON";
    }
}
void AngajatPer::AfisareOra2015()
{
    //
}
void AngajatPer::AfisareWeek2()
{
    //
}
AngajatTESA::AngajatTESA(char _numpr[], char _dataint[], int _prima, int _week) : Angajat(_numpr, _dataint, _prima)
{
    week = _week;
}
AngajatTESA::~AngajatTESA()
{
    //
}
void AngajatTESA::Afisare()
{
    char _numpr[50], _dataint[20];
    int _prima = GetPrima();
    strcpy(_numpr, GetNum());
    strcpy(_dataint, GetDataint());
    cout<<_numpr<<", contract permanent, "<<_dataint<<", TESA, weekend: "<<week<<", prima: "<<_prima<<" RON";
}
void AngajatTESA::AfisareCopii()
{
    //
}
void AngajatTESA::AfisareOra2015()
{
    //
}
void AngajatTESA::AfisareWeek2()
{
    if(week == 2)
    {
        char _numpr[50], _dataint[20];
        int _prima = GetPrima();
        strcpy(_numpr, GetNum());
        strcpy(_dataint, GetDataint());
        cout<<_numpr<<", contract permanent, "<<_dataint<<", TESA, weekend: "<<week<<", prima: "<<_prima<<" RON";
    }
}
AngajatOra::AngajatOra(char _numpr[], char _dataint[], int _prima, char _dataout[]) : Angajat(_numpr, _dataint, _prima)
{
    strcpy(dataout, _dataout);
}
AngajatOra::~AngajatOra()
{
    //
}
void AngajatOra::Afisare()
{
    char _numpr[50], _dataint[20];
    int _prima = GetPrima();
    strcpy(_numpr, GetNum());
    strcpy(_dataint, GetDataint());
    cout<<_numpr<<", plata cu ora, "<<_dataint<<" - "<<dataout<<", prima: "<<_prima<<" RON";
}
void AngajatOra::AfisareCopii()
{
    //
}
void AngajatOra::AfisareOra2015()
{
    char _anul[20], _luna[20];
    int anul, luna;
    strcpy(_anul, dataout+6);
    anul = atoi(_anul);
    strcpy(_luna, dataout+3);
    strncpy(_luna, _luna, 2);
    luna = atoi(_luna);
    if(anul>2014)
    {
        if(luna>2)
        {
            char _numpr[50], _dataint[20];
            int _prima = GetPrima();
            strcpy(_numpr, GetNum());
            strcpy(_dataint, GetDataint());
            cout<<_numpr<<", plata cu ora, "<<_dataint<<" - "<<dataout<<", prima: "<<_prima<<" RON";
        }
    }
}
void AngajatOra::AfisareWeek2()
{
    //
}
Firma::Firma()
{
    cout<<endl<<"Introduceti valoarea primei fixe: ";
    cin>>prima;
}
Firma::~Firma()
{
    //
}
void Firma::Citire()
{
    int ok=0;
    do
    {
        char _numpr[50], _dataint[20];
        int tip;
        cout<<endl<<"Introduceti numele si prenumele: "; cin.get(); cin.getline(_numpr, 49);
        cout<<"Introduceti data intrarii in vigoare a contractului: "; cin.getline(_dataint, 19);
        do
        {
            cout<<"Introduceti tipul contractului 1. Plata cu ora, 2. Permanent, 3. Permanent TESA: "; cin>>tip;
            if(tip<0 && tip>4) cout<<"Ati introdus o valoare gresita!"<<endl;
        }while(tip<0 && tip>4);
        if(tip == 1)
        {
            char _dataout[20], aux[20];
            int _prima = prima;
            cout<<"Introduceti data terminarii contractului: "; cin.get(); cin.getline(_dataout, 19);
            strcpy(aux, _dataout+6);
            if(strcmp(aux, "2015")<0) _prima=_prima/2;
            angajati.push_back(new AngajatOra(_numpr, _dataint, _prima, _dataout));
        }
        else if(tip == 2)
        {
            int _copii, _prima=prima, an;
            char aux[20];
            cout<<"Introduceti nr de copii minori: "; cin>>_copii;
            strcpy(aux, _dataint+6);
            an = atoi(aux);
            an = an-2014;
            an = an*(-1);
            an = an*_copii;
            an = an*4;
            _prima = _prima + an;
            angajati.push_back(new AngajatPer(_numpr, _dataint, _prima, _copii));
        }
        else
        {
            int _week, _prima=prima;
            cout<<"Introduceti weekendul din decembrie pentru minivacanta: "; cin>>_week;
            angajati.push_back(new AngajatTESA(_numpr, _dataint, _prima, _week));
        }
        cout<<endl<<"Doriti sa mai adaugati un angajat?"<<endl<<"0. NU"<<endl<<"1. DA"<<endl;
        cin>>ok;
    }while(ok==1);
}
void Firma::Afisare()
{
    for(auto it : angajati)
    {
        cout<<endl;
        it->Afisare();
    }
}
void Firma::AfisareCopii()
{
    for(auto it : angajati)
    {
        cout<<endl;
        it->AfisareCopii();
    }
}
void Firma::AfisareWeek2()
{
    for(auto it : angajati)
    {
        cout<<endl;
        it->AfisareWeek2();
    }
}
void Firma::AfisareOra2015()
{
    for(auto it : angajati)
    {
        cout<<endl;
        it->AfisareOra2015();
    }
}
int main()
{
    Firma X;
    X.Citire();
    cout<<endl<<"1. Afisare tuturor Angajatilor"<<endl;
    X.Afisare(); ///1. Afisare tuturor Angajatilor
    cout<<endl<<endl<<"2. Afisarea angajatilor cu copii minori in ingrijire";
    X.AfisareCopii();///2. Afisarea angajatilor cu copii minori in ingrijire
    cout<<endl<<"3. Afisarea angajatilor care aleg pentru mini vacanta weekendul 2 din decembrie"<<endl;
    X.AfisareWeek2();///3. Afisarea angajatilor care aleg pentru mini vacanta weekendul 2 din decembrie
    cout<<endl<<"4. Afisarea angajatilor in regim plata cu ora cu contract pana cel putin martie 2015"<<endl;
    X.AfisareOra2015();/// 4. Afisarea angajatilor in regim plata cu ora cu contract pana cel putin martie 2015
    return 0;
}
