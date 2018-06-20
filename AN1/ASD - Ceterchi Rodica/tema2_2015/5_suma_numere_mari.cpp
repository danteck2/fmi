#include <iostream>

using namespace std;

struct nod
{
    int info;
    nod *urm;//adresa urm
} *prim;

void afisare()
{
    if(prim==NULL) cout<<"Lista e vida";
    else
    {
        nod *p=prim;
        while(p!=NULL)
        {
            cout<<p->info<<' ';
            p=p->urm;
        }
        cout<<'\n';
    }
}
#include <iostream>

using namespace std;

struct nod
{
    int info;
    nod *urm;//adresa urm
} *prim;

void afisare()
{
    if(prim==NULL) cout<<"Lista e vida";
    else
    {
        nod *p=prim;
        while(p!=NULL)
        {
            cout<<p->info<<' ';
            p=p->urm;
        }
        cout<<'\n';
    }
}

void adaug_final(int x)
{
    nod *p, *ult;
    p=new nod;
    p->info=x;
    if(prim==NULL) {prim=ult=p; ult->urm=NULL;}
    else
    {
        ult=prim;
        while(ult->urm!=NULL)
            ult=ult->urm;
        ult->urm=p;
        ult=p;
        ult->urm=NULL;
    }
}

int main()
{
    int a=0, b=0, dep=0;
    while(ok!=9)
    {
        cout<<endl<<"Meniu"<<endl;
        cout<<"0: Adauga urmataorele cifre a numarului 1 si numarului 2"<<endl;
        cout<<"1: Adauga ultima cifra a numarului 1"<<endl;
        cout<<"2: Adauga ultima cifra a numarului 2"<<endl;
        cout<<"9: Introdus complet"<<endl;
        cin>>ok;
        switch(ok)
        {
        case 0:
            {
                cout<<"Introduceti urmatoarea cifra numarului 1: "; cin>>a; cout<<endl;
                cout<<"Introduceti urmatoarea cifra numarului 2: "; cin>>b;
                if((a+b)>9)
                {
                    adaug_final(0+dep);
                    dep=a+b-10;
                }
                else {adaug_final(a+b+dep); dep=0;}
                afisare()
                break;
            }
        case 1:
            {
                cout<<endl<<"Introduceti ultima cifra a numarului 1: ";cin>>a;cout<<endl;
                cout<<"Introduceti urmatoarea cifra numarului 2: "; cin>>b;cout<<endl;
                if((a+b)>9)
                {
                    adaug_final(0+dep);
                    dep=a+b-10;
                }
                else {adaug_final(a+b+dep); dep=0;}
                if(dep!==0) {cout<<"Introduceti urmatoarea cifra numarului 2: "; cin>>b; adaug_final(b+dep); }

                afisare();
                break;
            }
        case 2:
            {
                cout<<endl<<"Introduceti ultima cifra a numarului 2: ";cin>>b;cout<<endl;
                cout<<"Introduceti urmatoarea cifra numarului 1: "; cin>>a;cout<<endl;
                if((a+b)>9)
                {
                    adaug_final(0+dep);
                    dep=a+b-10;
                }
                else {adaug_final(a+b+dep); dep=0;}
                if(dep!==0) {cout<<"Introduceti urmatoarea cifra numarului 1: "; cin>>a; adaug_final(a+dep); }

                afisare();
                break;
            }

        case 9:
            {
                break;
            }
        default: cout<<"Valoarea introdusa e gresita"<<endl;
        }
    }

    return 0;
}
