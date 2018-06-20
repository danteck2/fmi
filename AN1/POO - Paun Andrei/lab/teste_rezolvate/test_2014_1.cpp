///NUME PRENUME GRUPA C++11 GCC Compiler
#include <iostream>
#include <cstring>
#include <vector>

using namespace std;

class RefBib
{
private:
    char Autori[100];
    char Titlul[50];
    char Anul[20];
public:
    RefBib(char _Autori[], char _Titlul[], char _Anul[]);
    ~RefBib();
    virtual void Afisare()=0;
    virtual void AfisareAn(char An[])=0;
    virtual void AfisareNume(char Nume[])=0;
    virtual void AfisareProp(char Prop[])=0;
    void Afisare_Autori();
    void Afisare_Titlul();
    void Afisare_Anul();
    char* GetAn();
    char* GetAutori();
};
class Articol : public RefBib
{
    char NumRev[100];
    char NrRev[10];
    char NrPag[10];
public:
    Articol(char _Autori[], char _Titlul[], char _Anul[], char _NumRev[], char _NrRev[], char _NrPag[]);
    ~Articol();
    void Afisare();
    void AfisareAn(char An[]);
    void AfisareNume(char Nume[]);
    void AfisareProp(char Prop[]);
};
class Carte : public RefBib
{
    char NumEdit[50];
    char OrasEdit[50];
public:
    Carte(char _Autori[], char _Titlul[], char _Anul[], char _NumEdit[], char _OrasEdit[]);
    ~Carte();
    void Afisare();
    void AfisareAn(char An[]);
    void AfisareNume(char Nume[]);
    void AfisareProp(char Prop[]);
};
class PagWeb : public RefBib
{
    char NumProp[50];//NumeleProprietarului
    char URL[100];
    char Data[20];//Data accessarii
public:
    PagWeb(char _Autori[], char _Titlul[], char _Anul[], char _NumProp[], char _URL[], char _Data[]);
    ~PagWeb();
    void Afisare();
    void AfisareAn(char An[]);
    void AfisareNume(char Nume[]);
    void AfisareProp(char Prop[]);
};
class Student
{
    vector<RefBib *> referinte;
public:
    Student();
    ~Student();
    void Citire();
    void Afisare();
    void AfisareAn(char An[]);
    void AfisareNume(char Nume[]);
    void AfisareProp(char Prop[]);
};

RefBib::RefBib(char _Autori[], char _Titlul[], char _Anul[])
{
    strcpy(Autori, _Autori);
    strcpy(Titlul,_Titlul);
    strcpy(Anul, _Anul);
}
RefBib::~RefBib()
{
    //
}
void RefBib::Afisare_Anul()
{
    cout<<Anul;
}
void RefBib::Afisare_Autori()
{
    cout<<Autori;
}
void RefBib::Afisare_Titlul()
{
    cout<<Titlul;
}
char* RefBib::GetAn()
{
    return Anul;
}
char* RefBib::GetAutori()
{
    return Autori;
}
Articol::Articol(char _Autori[], char _Titlul[], char _Anul[], char _NumRev[], char _NrRev[], char _NrPag[]) : RefBib(_Autori, _Titlul, _Anul)
{
    strcpy(NumRev, _NumRev);
    strcpy(NrRev, _NrRev);
    strcpy(NrPag, _NrPag);
}
Articol::~Articol()
{
    //
}
void Articol::Afisare()
{
    cout<<"Articole"<<endl;
    this->Afisare_Autori();
    cout<<": ";
    this->Afisare_Titlul();
    cout<<", "<<NumRev<<", ";
    this->Afisare_Anul();
    cout<<", "<<NrRev<<", "<<NrPag<<'.';
}
void Articol::AfisareAn(char An[])
{
    char _Anul[20];
    strcpy(_Anul, GetAn());
    if(strcmp(_Anul, An)==0)
    {
        cout<<"Articole"<<endl;
        this->Afisare_Autori();
        cout<<": ";
        this->Afisare_Titlul();
        cout<<", "<<NumRev<<", ";
        this->Afisare_Anul();
        cout<<", "<<NrRev<<", "<<NrPag<<'.';
    }
}
void Articol::AfisareNume(char Nume[])
{
    char _Numele[100];
    strcpy(_Numele, GetAutori());
    char *p = strtok (_Numele," ,.-");
    while (p != NULL)
    {
        if(strcmp(p, Nume)==0)
        {
            cout<<"Articole"<<endl;
            this->Afisare_Autori();
            cout<<": ";
            this->Afisare_Titlul();
            cout<<", "<<NumRev<<", ";
            this->Afisare_Anul();
            cout<<", "<<NrRev<<", "<<NrPag<<'.';
        }
        p = strtok(NULL, " ,.-");
    }
}
void Articol::AfisareProp(char Prop[])
{
    //
}
Carte::Carte(char _Autori[], char _Titlul[], char _Anul[], char _NumEdit[], char _OrasEdit[]) : RefBib(_Autori, _Titlul, _Anul)
{
    strcpy(NumEdit, _NumEdit);
    strcpy(OrasEdit, _OrasEdit);
}
Carte::~Carte()
{
    //
}
void Carte::Afisare()
{
    cout<<"Carti"<<endl;
    this->Afisare_Autori();
    cout<<": ";
    this->Afisare_Titlul();
    cout<<", "<<NumEdit<<", "<<OrasEdit<<", ";
    this->Afisare_Anul();
    cout<<'.';
}
void Carte::AfisareAn(char An[])
{
    char _Anul[20];
    strcpy(_Anul, GetAn());
    if(strcmp(_Anul, An)==0)
    {
        cout<<"Carti"<<endl;
        this->Afisare_Autori();
        cout<<": ";
        this->Afisare_Titlul();
        cout<<", "<<NumEdit<<", "<<OrasEdit<<", ";
        this->Afisare_Anul();
        cout<<'.';
    }
}
void Carte::AfisareNume(char Nume[])
{
    char _Numele[100];
    strcpy(_Numele, GetAutori());
    char *p = strtok (_Numele," ,.-");
    while (p != NULL)
    {
        if(strcmp(p, Nume)==0)
        {
            cout<<"Carti"<<endl;
            this->Afisare_Autori();
            cout<<": ";
            this->Afisare_Titlul();
            cout<<", "<<NumEdit<<", "<<OrasEdit<<", ";
            this->Afisare_Anul();
            cout<<'.';
        }
        p = strtok(NULL, " ,.-");
    }
}
void Carte::AfisareProp(char Prop[])
{
    //
}
PagWeb::PagWeb(char _Autori[], char _Titlul[], char _Anul[], char _NumProp[], char _URL[], char _Data[]) : RefBib(_Autori, _Titlul, _Anul)
{
    strcpy(NumProp, _NumProp);
    strcpy(URL, _URL);
    strcpy(Data, _Data);
}
PagWeb::~PagWeb()
{
    //
}
void PagWeb::Afisare()
{
    cout<<"Webografie"<<endl;
    if(strlen(NumProp)<2)cout<<"***: ";
    else cout<<NumProp<<": ";
    this->Afisare_Titlul();
    cout<<". "<<URL<<" (accesat "<<Data<<").";
}
void PagWeb::AfisareAn(char An[])
{
    //
}
void PagWeb::AfisareNume(char Nume[])
{
    //
}
void PagWeb::AfisareProp(char Prop[])
{
    char _Prop[100];
    strcpy(_Prop, Prop);
    if(strcmp(_Prop, NumProp)==0)
    {
        cout<<"Webografie"<<endl;
        if(strlen(NumProp)<2)cout<<"***: ";
        else cout<<NumProp<<": ";
        this->Afisare_Titlul();
        cout<<". "<<URL<<" (accesat "<<Data<<").";
    }
}
Student::Student()
{
    //
}
Student::~Student()
{
    //
}
void Student::Citire()
{
    int tip=1;
    while(tip)
    {
        cout<<"Meniu pentru adaugare referinte bibliografice"<<endl;
        cout<<"1. Articol"<<endl;
        cout<<"2. Carte"<<endl;
        cout<<"3. Pagina WEB"<<endl;
        cout<<"0. Iesire"<<endl;
        cin>>tip;
        cin.get();
        switch(tip)
        {
        case 1:
        {
            int ok=0;
            do
            {
                char _Autori[100], _Titlul[50], _NumRev[100], _NrPag[10];
                char _Anul[20], _NrRev[10];
                cout<<endl<<"Datele introduse trebuie sa fie de forma:"<<endl<<"Autor1, Autor2 ..."<<endl<<"Titlul"<<endl<<"Anul"<<endl<<"Numele Revistei"<<endl<<"Numarul Revistei"<<endl<<"Numerele Paginilor (Ex:77-104)"<<endl;
                cin.getline(_Autori, 99);
                cin.getline(_Titlul, 49);
                cin.getline(_Anul,19);
                cin.getline(_NumRev, 99);
                cin.getline(_NrRev, 9);
                cin.getline(_NrPag, 9);
                referinte.push_back(new Articol(_Autori, _Titlul, _Anul, _NumRev, _NrRev, _NrPag));
                cout<<endl<<"Doriti sa mai adaugati un articol?"<<endl<<"0. NU"<<endl<<"1. DA"<<endl;
                cin>>ok;
                cin.get();
            }
            while(ok==1);
            break;
        }
        case 2:
        {
            int ok=0;
            do
            {
                char _Autori[100], _Titlul[50], _NumEdit[50], _OrasEdit[50];
                char _Anul[10];
                cout<<endl<<"Datele introduse trebuie sa fie de forma:"<<endl<<"Autor1, Autor2 ..."<<endl<<"Titlul"<<endl<<"Anul Publicarii"<<endl<<"Numele Editurii"<<endl<<"Orasul Editurii"<<endl;
                cin.getline(_Autori, 99);
                cin.getline(_Titlul, 49);
                cin.getline(_Anul,9);
                cin.getline(_NumEdit, 49);
                cin.getline(_OrasEdit, 49);
                referinte.push_back(new Carte(_Autori, _Titlul, _Anul, _NumEdit, _OrasEdit));
                cout<<endl<<"Doriti sa mai adaugati o carte?"<<endl<<"0. NU"<<endl<<"1. DA"<<endl;
                cin>>ok;
                cin.get();
            }
            while(ok==1);
            break;
        }
        case 3:
        {
            int ok=0;
            do
            {
                char _Autori[100], _Titlul[50], _NumProp[50], _URL[100], _Data[20];
                char _Anul[10];
                cout<<endl<<"Datele introduse trebuie sa fie de forma:"<<endl<<"Numele Proprietarului"<<endl<<"Titlul"<<endl<<"URL-ul"<<endl<<"Data Accesarii"<<endl;
                cin.getline(_NumProp, 49);
                cin.getline(_Titlul, 49);
                cin.getline(_URL, 99);
                cin.getline(_Data, 19);
                referinte.push_back(new PagWeb(_Autori, _Titlul, _Anul, _NumProp, _URL, _Data));
                cout<<endl<<"Doriti sa mai adaugati o pagina WEB?"<<endl<<"0. NU"<<endl<<"1. DA"<<endl;
                cin>>ok;
                cin.get();
            }
            while(ok==1);
            break;
        }
        case 0:
        {
            cout<<endl<<"Iesire";
            break;
        }
        default :
        {
            cout<<endl<<"Ati introdus o valoare gresita"<<endl;
        }
        }
    }
}
void Student::Afisare()
{
    int i=1;
    cout<<endl<<"Bibliografie";
    for (auto it : referinte)
    {
        cout<<endl;
        i++;
        it->Afisare();
    }
}
void Student::AfisareAn(char An[])
{
    int i=1;
    cout<<endl<<"Bibliografie";
    for (auto it : referinte)
    {
        cout<<endl;
        i++;
        it->AfisareAn(An);
    }
}
void Student::AfisareNume(char Nume[])
{
    int i=1;
    cout<<endl<<"Bibliografie";
    for (auto it : referinte)
    {
        cout<<endl;
        i++;
        it->AfisareNume(Nume);
    }
}
void Student::AfisareProp(char Prop[])
{
    int i=1;
    cout<<endl<<"Bibliografie";
    for (auto it : referinte)
    {
        cout<<endl;
        i++;
        it-> AfisareProp(Prop);
    }
}
int main()
{
    Student X;
    X.Citire(); //citirea Referintelor Bibliografice
    X.Afisare(); ///1. Afisa toate referintele
    char An[20];
    cout<<endl<<"Introduceti anul publicarii: ";
    cin.getline(An, 9);
    X.AfisareAn(An);///2. Afisa toate referintele publicate intr-un an dat
    char Nume[100];
    cout<<endl<<"Introduceti numele Autorului: ";
    cin.getline(Nume, 99);
    X.AfisareNume(Nume);///3. Afisa toate toate referintele care numele autorului egal cu un nume dat
    char Prop[100];
    cout<<endl<<"Introduceti Numele Proprietarului: ";
    cin.getline(Prop, 99);
    X.AfisareProp(Prop);///4. Afisare toate referintele in format electronic care au numele proprietarului egal cu un nume dat
    return 0;
}
