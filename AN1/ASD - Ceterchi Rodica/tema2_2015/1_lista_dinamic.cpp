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

void adaug_inceput(int x)
{
    nod *p;
    p=new nod;
    p->info=x;
    p->urm=prim;
    prim=p;
}

void adaug_dupa_val_x(int x)
{
    nod *p;
    p=new nod;
    p=prim;
    int i;cout<<"Introduceti valoare element: "; cin>>i;
    while(p->urm!=NULL && p->info!=x) p=p->urm;
    if(p->urm==NULL) adaug_final(i);
    else
    {
        nod *a;
        a=new nod;
        a->info=i;
        a->urm=p->urm;
        p->urm=a;
    }
}

void caut_dupa_val_x(int x)
{
    if(prim==NULL) cout<<"Lista e vida";
    else
    {
        int ok=0;
        nod *p;
        p=new nod;
        p=prim;
        while(p->urm!=NULL)
        {
            if(p->info==x) ok++;
            p=p->urm;
        }
        if(ok==0) cout<<endl<<"x nu este in lista";
        else cout<<endl<<"x este in lista de "<<ok<<" ori"<<endl;
    }
}

void caut_dupa_poz_x(int x)
{
    if(prim==NULL) cout<<"Lista e vida";
    else
    {
        int ok=0;
        nod *p;
        p=new nod;
        p=prim;
        while(ok<x-1)
        {
            ok++;
            p=p->urm;
        }
        ok++;
        cout<<endl<<"pe pozitia "<<ok<<" este element cu valoarea: "<<p->info<<endl;
    }
}

void sterg_dupa_val_x(int x)
{
    if(prim==NULL) cout<<"Lista e vida";
    else
    {
        nod *p, *aux;
        p=new nod;
        aux=new nod;
        p=prim;
        while(p->urm->info!=x) p=p->urm;
        p=p->urm;
        aux=p->urm;
        p->urm=p->urm->urm;
        delete aux;
    }
}

void sterg_dupa_poz_x(int x)
{
    if(prim==NULL) cout<<"Lista e vida";
    else
    {
        int ok=0;
        nod *p, *aux;
        p=new nod;
        aux=new nod;
        p=prim;
        while(ok<x-1) {ok++; p=p->urm;}
        aux=p->urm;
        p->urm=p->urm->urm;
        delete aux;
    }
}
void sterg_tot()
{
    nod *p;
    if(prim!=NULL)
    while(prim!=NULL)
    {
        p=prim;
        prim=prim->urm;
        delete p;
    }
}


int main()
{
    int x, ok;
    while(ok!=9)
    {
        cout<<endl<<"Meniu"<<endl;
        cout<<"0: Afisarea listei"<<endl;
        cout<<"1: Adaugarea unui nou element la finalul listei"<<endl;
        cout<<"2: Adaugarea unui nou element la inceputul listei"<<endl;
        cout<<"3: Adaugarea unui nou element in interiorul listei dupa valoarea lui x"<<endl;
        cout<<"4: Cautarea unui element dupa valoare x"<<endl;
        cout<<"5: Cautarea unui element dupa pozitie x"<<endl;
        cout<<"6: Stergerea unui element dupa valoare x"<<endl;
        cout<<"7: Stergerea unui element dupa pozitie x"<<endl;
        cout<<"8: Stergerea intregii liste"<<endl;
        cout<<"9: Esire"<<endl;
        cin>>ok;
        switch(ok)
        {
        case 0:
            {
                afisare();
                break;
            }
        case 1:
            {
                cout<<endl<<"Introduceti valoarea: ";x=0;cin>>x;
                adaug_final(x);
                afisare();
                break;
            }
        case 2:
            {
                cout<<endl<<"Introduceti valoarea: ";x=0;cin>>x;
                adaug_inceput(x);
                afisare();
                break;
            }
        case 3:
            {
                cout<<endl<<"Introduceti x=";x=0;cin>>x;
                adaug_dupa_val_x(x);
                afisare();
                break;
            }
        case 4:
            {
                cout<<endl<<"Introduceti x=";x=0;cin>>x;
                caut_dupa_val_x(x);
                afisare();
                break;
            }
        case 5:
            {
                cout<<endl<<"Introduceti x=";x=0;cin>>x;
                caut_dupa_poz_x(x);
                afisare();
                break;
            }
        case 6:
            {
                cout<<endl<<"Introduceti x=";x=0;cin>>x;
                sterg_dupa_val_x(x);
                afisare();
                break;
            }
        case 7:
            {
                cout<<endl<<"Introduceti x=";x=0;cin>>x;
                sterg_dupa_poz_x(x);
                afisare();
                break;
            }
        case 8:
            {
                sterg_tot();
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
