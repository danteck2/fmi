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

void numar_elem_poz()
{
    int ok=0;
    if(prim==NULL) cout<<"Lista e vida";
    else
    {
        nod *p;
        p=new nod;
        p=prim;
        if(p->info%2==0)ok++;
        while(p->urm!=NULL)
        {
            p=p->urm;
            if(p->info%2!=0)ok++;
        }
    }
    cout<<endl<<"In lista sunt "<<ok<<" elemente pozitive"<<endl;
}

void media_arit_elem_neg()
{
    cout<<endl;
    int ok=0, suma=0, med=0;
    if(prim==NULL) cout<<"Lista e vida";
    else
    {
        nod *p;
        p=new nod;
        p=prim;
        if(p->info%2!=0)ok++;
        while(p->urm!=NULL)
        {
            p=p->urm;
            if(p->info%2==0) {suma=suma+p->info; ok++; cout<<' '<<p->info;}
        }
    }
    if(ok==0) cout<<"In lista nu sunt elemente negative";
    else
    {
        med=suma/ok;
        cout<<endl<<"In lista sunt "<<ok<<" elemente negative "<<endl<<"Suma lor este "<<suma<<" iar media aritmetica "<<med;
    }
}

int main()
{
    creare();
    afisare();
    numar_elem_poz();
    media_arit_elem_neg();
    return 0;
}
