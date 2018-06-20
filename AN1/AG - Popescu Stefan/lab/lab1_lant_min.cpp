#include <iostream>
#include <fstream>
using namespace std;

int n, m, v[100][100], viz[100], c[100], t[100];//c coada
ifstream fin("GRAF.IN");

void drum(int y)
{
    if(t[y]!=0)
        drum(t[y]);
    cout<<y<<" ";
}

void citire()
{
    fin>>n>>m;
    int i,x,y;
    for(i=1;i<=m;i++)
    {
        fin>>x>>y;
        v[x][0]++;
        v[x][v[x][0]]=y;
        v[y][0]++;
        v[y][v[y][0]]=x;
    }
}

void lant(int x)
{
    int i,p,u;
    viz[x]=1;
    c[1]=x;
    p=u=1;
    while(p<=u)
    {
        x=c[p];
        p++;
        for(i=1;i<=v[x][0];i++)
        if(viz[v[x][i]]==0)
        {
            viz[v[x][i]]=1+viz[x];
            t[v[x][i]]=x;
            u++;
            c[u]=v[x][i];
        }
    }
}

int main()
{
    int x,y,n,i;
    citire();
    cout<<"Introduceti x: ";  cin>>x;
    cout<<"Introduceti y: ";  cin>>y;
    lant(x);
    cout<<"Lantul minim de la x la y este: ";
    drum(y);

 return 0;
}
