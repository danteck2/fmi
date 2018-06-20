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

void adaug_dupa_val_x(int x, int i)
{
    nod *p;
    p=new nod;
    p=prim;
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


void adaug_medie()
{
    if(prim==NULL) cout<<"Lista e vida";
    else
    {
        int a=0, b=0, x=0;
        nod *p;
        p=new nod;
        p=prim;
        while(p->urm!=NULL)
        {
            a=p->info;
            b=p->urm->info;
            x=(a+b)/2;
            adaug_dupa_val_x(a, x);
            p=p->urm->urm;
        }
    }
}

int main()
{
    creare();
    afisare();
    adaug_medie();
    afisare();
    return 0;
}
