#include <iostream>
#include <fstream>

using namespace std;

ifstream fin("GRAF.IN");

int viz[100], a[100][100], c[100], n, m,  vp, x, y, prim=1, ultim=1; //n nod m muchii

void citire()
{
    fin>>n>>m>>vp;
    for(int i=1; i<=m; i++)
    {
        fin>>x>>y;
        a[x][y]=a[y][x]=1;
    }
}

void BF(int vp)
{
    c[prim]=vp;
    viz[vp]=1;

    while(prim<=ultim)
    {
        int k=c[prim];
        for(int i=1; i<=n;i++)
            if(a[i][k]==1 && viz[i]==0)
            {
                viz[i]=1;
                ultim++;
                c[ultim]=i;
            }
            prim++;
    }
    for(int j =1; j<=ultim; j++)
        cout<<c[j]<<' ';
}
void DF(int vp)
{
cout<<vp;
viz[vp]=1;
for(int i=1; i<=n; i++)
    if(a[vp][i]==1 && viz[i]==0)
        DF(i);
}

int main()
{
    int qwerty;
    citire();
    cout<<"1. Pentru parcurgerea DF"<<endl;
    cout<<"2. Pentru parcurgerea BF"<<endl;
    cin>>qwerty;
    switch(qwerty)
    {
        case 1: {
                    cout<<"DF: ";
                    DF(vp); break;
                }
        case 2: {
                    cout<<"BF: ";
                    BF(vp);
                    break;
                }
        default : {cout<<"Ati introdus o valore gresita! "<<endl;}
    }
    return 0;
}
