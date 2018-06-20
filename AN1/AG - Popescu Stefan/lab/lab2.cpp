#include <iostream>
#include <fstream>

using namespace std;

ifstream fin("AG.in");
ofstream fout("AG.out");

void citire(int *v,int n)
{
    int i,x;

    for(i=0;i<n;i++)
    {
        fin>>x;
        v[i]=x;
    }
}

void afisare(int *v,int n)
{
    int i;
    for(i=0;i<n;i++)
        cout<<v[i]<<" ";
}

void ordonare(int *v,int *w,int n)
{
    int i,j;
    for(i=0;i<n-1;i++)
        for(j=i+1;j<n;j++)
            if(v[j]>v[i])
            {
                v[j]=v[i]+v[j]-(v[i]=v[j]);
                w[j]=w[i]+w[j]-(w[i]=w[j]);
            }
}

int verificare_neor(int *v,int n)
{
    int suma=0,i;
    for(i=0;i<n;i++)
        suma+=v[i];
    if(suma!=0 && suma%2==0)
        return 1;
    return 0;
}

void initializare_w(int *w,int n)
{
    int i;
    for(i=0;i<n;i++)
        w[i]=i+1;
}
void afisare_w(int *w,int n)
{
    int i;
    for(i=0;i<n;i++)
        cout<<w[i]<<" ";
}

int verif_c(int *v,int n)
{
    int i,k=0;
    for(i=0;i<n;i++)
        if(!v[i])
            k++;
    if(k==n)
        return 0;
    return 1;
}

int verificare_arbore(int *v,int n)
{
    int suma=0,i;
    for(i=0;i<n;i++)
        suma+=v[i];
    if(suma==2*(n-1))
        return 1;
    return 0;
}

int verificare_graf_simplu_conex(int *v,int n)
{
    int i,suma=0;
    for(i=0;i<n;i++)
        suma+=v[i];
    if(suma>=2*(n-1))
        return 1;
    return 0;
}

int main()
{
    int *v,*w,n,i;

    fin>>n;
    v=new int [n];
    w=new int [n];

    citire(v,n);
    initializare_w(w,n);



    if(verificare_graf_simplu_conex(v,n)==1)
    {
        int x,j;
        while(verif_c(v,n)==1)
        {
             ordonare(v,w,n);
             i=0;
             j=i+1;
             if(v[i]!=0)
             {
                 while(v[i]!=0 && v[j]!=0)
                 {
                     cout<<"("<<w[i]<<","<<w[j]<<") ";
                   v[j]--;
                   v[i]--;
                   j++;
                 }
             }
        }
    }
    else
        cout<<"Multisetul e gresit!"<<'\n';

    delete v;
    delete w;
    return 0;
}
