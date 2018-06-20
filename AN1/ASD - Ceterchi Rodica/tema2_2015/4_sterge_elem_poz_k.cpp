#include <iostream>
#include <fstream>

using namespace std;

ifstream fin("lista.in");

struct nod
{
    int info;
    nod *urm;//adresa urm
} *prim;

void creare()
{
nod *p, *ult;
p= new nod;
int x;
fin>>x;
p->info=x;
prim=ult=p;
while(fin>>x)
{
p=new nod;
p->info=x;
ult->urm=p;
ult=p;
}
ult->urm=NULL;
}

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

void sterg_pe_poz_k(int k)
{
    if(prim==NULL) cout<<"Lista e vida";
    else
    {
        int ok=0;
        nod *p, *aux;
        p=new nod;
        aux=new nod;
        p=prim;
        while(ok<k-2) {ok++; p=p->urm;}
        aux=p->urm;
        p->urm=p->urm->urm;
        delete aux;
    }
}

int main()
{
    int k=0;
    creare();
    afisare();
    cout<<endl<<"Introduceti pozitia elementului care doriti sal stergeti: "; cin>>k;
    sterg_pe_poz_k(k);
    afisare();
    return 0;
}
