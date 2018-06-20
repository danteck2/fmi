///NUME PRENUME GRUPA C++11 GCC Compiler
#include <iostream>
#include <cstring>
#include <vector>

using namespace std;

class Fisa
{
    int col; //colesterol
    char datac[20];
    int tens; //tensiune
    char datat[20];
public:
    Fisa(int _col, char _datac[], int _tens, char _datat[]);
    ~Fisa();
    int GetCol();
    int GetTens();
    char* GetDatac();
    char* GetDatat();
    virtual int GetRisc(); //returneaza factor de risc
    virtual void Afisare();
};
class FisaP40 : public Fisa //peste 40 ani
{
    bool fumat;
    int sedent;
public:
    FisaP40(int _col, char _datac[], int _tens, char _datat[], bool _fumat, int _sedent);
    ~FisaP40();
    virtual int GetRisc(); //returneaza factor de risc
    virtual void Afisare();

};
class FisaCopii : public Fisa
{
    char NumPrenP[100];
    double protein;
    char datap[20];
    bool antec;
public:
    FisaCopii(int _col, char _datac[], int _tens, char _datat[], char _NumPrenP[], bool _antec, double _protein, char _datap[]);
    ~FisaCopii();
    virtual int GetRisc(); //returneaza factor de risc
    virtual void Afisare();
};
class Pacient
{
    char NumPren[50]; //nume prenume
    int varsta;
    char adresa[100];
    vector<Fisa *> fise;
public:
    Pacient(char _NumPren[], int _varsta, char _adresa[]);
    ~Pacient();
    void Afisare();
    void AfisareAFR();
    void AfisareCFR();
    void AfisareNume(char _nume[]);

};
class Medic
{
    vector<Pacient *> pacienti;
public:
    Medic();
    ~Medic();
    void Citire();
    void Afisare();
    void AfisareAFR();
    void AfisareCFR();
    void AfisareNume(char _nume[]);
};


Fisa::Fisa(int _col, char _datac[], int _tens, char _datat[])
{
    col = _col;
    tens = _tens;
    strcpy(datac, _datac);
    strcpy(datat, _datat);
}
Fisa::~Fisa()
{
    //
}
int Fisa::GetCol()
{
    return col;
}
int Fisa::GetTens()
{
    return tens;
}
char* Fisa::GetDatac()
{
    return datac;
}
char* Fisa::GetDatat()
{
    return datat;
}
int Fisa::GetRisc()
{
    int cont=0;
    if(col>240)cont++;
    if(tens>139) cont++;
    return cont;
}
void Fisa::Afisare()
{
    cout<<"Risc cardiovascular - ";
    if(col>240)
    {
        if(tens>139)
            cout<<"RIDICAT";
        else cout<<"DA";
    }
    else if(tens>139)cout<<"DA";
    else cout<<"NU";
    cout<<"; Colesterol ("<<datac<<"): "<<col<<" mg/dl; TA("<<datat<<"): "<<tens<<".";
}
FisaP40::FisaP40(int _col, char _datac[], int _tens, char _datat[], bool _fumat, int _sedent) : Fisa(_col, _datac, _tens, _datat)
{
    fumat = _fumat;
    sedent= _sedent;
}
FisaP40::~FisaP40()
{
    //
}
int FisaP40::GetRisc()
{
    int cont=0, _col, _tens;
    _col=GetCol();
    _tens=GetTens();
    if(_col>240) cont++;
    if(_tens>240) cont++;
    if(fumat == true && sedent == 3) cont++;
    return cont;
}
void FisaP40::Afisare()
{
    cout<<"Risc cardiovascular - ";
    int cont=0, _col, _tens;
    char _datac[20], _datat[20];
    _col=GetCol();
    _tens=GetTens();
    strcpy(_datac, GetDatac());
    strcpy(_datat, GetDatat());
    if(_col>240) cont++;
    if(_tens>240) cont++;
    if(fumat == true && sedent == 3) cont++;
    if(cont>1)cout<<"RIDICAT";
    else if(cont==1)cout<<"DA";
    else cout<<"NU";
    cout<<"; Colesterol ("<<_datac<<"): "<<_col<<" mg/dl; TA("<<_datat<<"): "<<_tens<<"; "<<endl<<"Fumator: ";
    if(fumat==true) cout<<"da; Sedentarism: ";
    else cout<<"nu; Sedentarism: ";
    if(sedent<2)
    {
        if(sedent<1)
            cout<<"scazut.";
        else cout<<"mediu.";
    }
    else cout<<"ridicat.";
}
FisaCopii::FisaCopii(int _col, char _datac[], int _tens, char _datat[], char _NumPrenP[], bool _antec, double _protein, char _datap[]) : Fisa(_col, _datac, _tens, _datat)
{
    strcpy(NumPrenP, _NumPrenP);
    protein = _protein;
    strcpy(datap, _datap);
    antec = _antec;
}
FisaCopii::~FisaCopii()
{
    //
}
int FisaCopii::GetRisc()
{
    int cont=0, _col, _tens;
    _col=GetCol();
    _tens=GetTens();
    if(_col>240) cont++;
    if(_tens>240) cont++;
    if(protein>0.6) cont++;
    if(antec==true) cont++;
    return cont;
}
void FisaCopii::Afisare()
{
    cout<<"Risc cardiovascular - ";
    int cont=0, _col, _tens;
    _col=GetCol();
    _tens=GetTens();
    char _datac[20], _datat[20];
    strcpy(_datac, GetDatac());
    strcpy(_datat, GetDatat());
    if(_col>240) cont++;
    if(_tens>240) cont++;
    if(protein>0.6) cont++;
    if(antec==true) cont++;
    if(cont>1)cout<<"RIDICAT";
    else if(cont==1)cout<<"DA";
    else cout<<"NU";
    cout<<"; Colesterol ("<<_datac<<"): "<<_col<<" mg/dl; TA("<<_datat<<"): "<<_tens<<"; "<<endl<<"Proteina C relativa ("<<datap<<"): "<<protein<<" mg/dl; Antecedente familie: ";
    if(antec==true) cout<<"da.";
    else cout<<"nu.";
}
Pacient::Pacient(char _NumPren[], int _varsta, char _adresa[])
{
    strcpy(NumPren, _NumPren);
    varsta = _varsta;
    strcpy(adresa, _adresa);
    if(varsta<40)
    {
        if(varsta<18)
        {
            char _datac[20], _datat[20], _datap[20], _NumPrenP[100];
            int _col, _tens;
            double _protein;
            bool _antec=false;
            cout<<endl<<"Introduceti Nume Prenume Parinti: "; cin.getline(_NumPrenP, 99);
            cout<<"Antecedente: 0 NU, 1 DA"; cin>>_antec;cin.get();
            cout<<"Data colesterol: "; cin.getline(_datac, 19);
            cout<<"Valoare colesterol: "; cin>>_col; cin.get();
            cout<<"Data TA: "; cin.getline(_datat, 19);
            cout<<"Valoare TA: "; cin>>_tens;cin.get();
            cout<<"Data Proteina C: "; cin.getline(_datap, 19);
            cout<<"Valoare Proteina C: "; cin>>_protein;
            fise.push_back(new FisaCopii(_col, _datac, _tens, _datat, _NumPrenP, _antec, _protein, _datap));
        }
        else
        {
            char _datac[20], _datat[20];
            int _col, _tens;
            cout<<"Data colesterol: "; cin.getline(_datac, 19);
            cout<<"Valoare colesterol: "; cin>>_col; cin.get();
            cout<<"Data TA: "; cin.getline(_datat, 19);
            cout<<"Valoare TA: "; cin>>_tens;
            fise.push_back(new Fisa(_col, _datac, _tens, _datat));
        }
    }
    else
    {
        char _datac[20], _datat[20];
        int _col, _tens, _sedent;
        bool _fumat=false;
        cout<<"Data colesterol: "; cin.getline(_datac, 19);
        cout<<"Valoare colesterol: "; cin>>_col; cin.get();
        cout<<"Data TA: "; cin.getline(_datat, 19);
        cout<<"Valoare TA: "; cin>>_tens;
        cout<<"Fumator: 1 DA, 0 NU "; cin>>_fumat; cin.get();
        cout<<"Sedentarism: 1 SCAZUT, 2 MEDIU, 3 RIDICAT "; cin>>_sedent;
        fise.push_back(new FisaP40(_col, _datac, _tens, _datat, _fumat, _sedent));
    }
}
Pacient::~Pacient()
{
    //
}
void Pacient::Afisare()
{
    if(varsta<18)
        cout<<"Copii"<<endl;
    else
    {
        if(varsta<40)
            cout<<"Adulti"<<endl<<"Adulti sub 40 de ani"<<endl;
        else
            cout<<"Adulti"<<endl<<"Adulti peste 40 de ani"<<endl;
    }
    cout<<NumPren<<": ";
    for(auto it : fise)
    {
        it->Afisare();
    }
}
void Pacient::AfisareAFR()
{
    int cont=0;
    if(varsta>=18)
    {
        for(auto it : fise)
        {
            cont = it->GetRisc();
        }
        if(cont>1)
        {
            cout<<"Adulti"<<endl;
            cout<<NumPren<<": ";
            for(auto it : fise)
            {
                it->Afisare();
            }
        }
    }
}
void Pacient::AfisareCFR()
{
    int cont=0;
    if(varsta<18)
    {
        for(auto it : fise)
        {
            cont = it->GetRisc();
        }
        if(cont>0)
        {
            cout<<"Copii"<<endl;
            cout<<NumPren<<": ";
            for(auto it : fise)
            {
                it->Afisare();
            }
        }
    }
}
void Pacient::AfisareNume(char _nume[])
{
    char _NumPren[50];
    strcpy(_NumPren, NumPren);
    char *p=strtok(_NumPren, " ,.");
    while(p != NULL)
    {
        if(strcmp(p, _nume)==0)
        {
            if(varsta<18)
                cout<<"Copii"<<endl;
            else
            {
                if(varsta<40)
                    cout<<"Adulti"<<endl<<"Adulti sub 40 de ani"<<endl;
                else
                    cout<<"Adulti"<<endl<<"Adulti peste 40 de ani"<<endl;
            }
            cout<<NumPren<<": ";
            for(auto it : fise)
            {
                it->Afisare();
            }
        }
        p=strtok(NULL, " ,.");
    }
}
Medic::Medic()
{
    //
}
Medic::~Medic()
{
    //
}
void Medic::Citire()
{
    int ok=0;
    do
    {
        char _NumPren[50], _adresa[100];
        int _varsta;
        cout<<endl<<"Introduceti datele pacientului: ";
        cout<<endl<<"Numele Prenumele(Ex: Ionescu Paul): "; cin.getline(_NumPren, 49);
        cout<<"Varsta: "; cin>>_varsta; cin.get();
        cout<<"Adresa: "; cin.getline(_adresa, 99);
        pacienti.push_back(new Pacient(_NumPren, _varsta, _adresa));
        cout<<endl<<"Doriti sa mai adaugati un pacient?"<<endl<<"0. NU"<<endl<<"1. DA"<<endl;
        cin>>ok;
        cin.get();
    }while(ok==1);
}
void Medic::Afisare()
{
    for (auto it : pacienti)
    {
        cout<<endl;
        it->Afisare();
    }
}
void Medic::AfisareAFR()
{
    cout<<endl<<"Adulti cu factor de risc cardiovascular ridicat";
    for (auto it : pacienti)
    {
        cout<<endl;
        it->AfisareAFR();
    }
}
void Medic::AfisareCFR()
{
    cout<<endl<<"Copii cu factor de risc cardiovascular";
    for (auto it : pacienti)
    {
        cout<<endl;
        it->AfisareCFR();
    }
}
void Medic::AfisareNume(char _nume[])
{
    cout<<endl<<"Pacienti cu numele de familie: "<<_nume;
    for (auto it : pacienti)
    {
        cout<<endl;
        it->AfisareNume(_nume);
    }
}
int main()
{
    Medic X;
    X.Citire();
    X.Afisare(); ///1. A afisa toate info medicale pt toti pacientii
    cout<<endl<<"2. A afisa toate info pt adulti cu factor de risc ridicat"<<endl;
    X.AfisareAFR();///2. A afisa toate info pt adulti cu factor de risc ridicat
    cout<<endl<<"3. A afisa toti copiii cu factor de risc"<<endl;
    X.AfisareCFR();///3. A afisa toti copiii cu factor de risc
    char _nume[50];///4. A afisa toate info med pt toti pacientii care au numele de familie egal cu un nume dat
    cout<<endl<<"Introduceti numele: ";
    cin.getline(_nume, 49);
    X.AfisareNume(_nume);
    return 0;
}
