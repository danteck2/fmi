#include <iostream>
#include <fstream>

using namespace std;

ifstream fin("GRAF.IN");

int a[20][20],c[20],viz[20];
int s[20];//elementele multimii 1
int n,m,prim,ultim,varf;

void BF() //parcurgerea in latime
{
    int k;
    while(prim<=ultim)
    {
        varf=c[prim];
        for(k=1;k<=n;k++)
            if(a[varf][k]==1&&viz[k]==0)
            {
                ultim++;
                c[ultim]=k;
                viz[k]=1;
            }
            prim++;
    }
}

int main()
{
    int x, y;
    fin>>n>>m;
    for(int i=1;i<=m;i++)
    {
        fin>>x>>y;
        a[x][y]=a[y][x]=1;
    }


cout<<"matricea de adiac "<<endl; // afisare matrice de adiacenta
for(int i=1;i<=n;i++)
 {for(int j=1;j<=n;j++)
                   cout<<a[i][j]<<" ";
  cout<<endl;
  }

    int nd,k;
    int ok=1;
    for(nd=1;nd<=n;nd++)
        if(viz[nd]==0)
        {
            for(k=1;k<=n;k++)  //golesc coada si viz
            {
                c[k]=0;
                viz[nd]=0;
            }
            prim=ultim=1;
            viz[nd]=1;
            c[prim]=nd;
            BF();
            cout<<endl;
            for(int k=1;k<=ultim-1;k++)
             for(int j=k+1;j<=ultim;j++)
                if(a[c[k]][c[j]]==1)
                   if(s[c[k]]==0&&s[c[j]]==0)
                       {s[c[k]]=1;s[c[j]]=-1;}
                    else
                        if(s[c[k]]!=0&&s[c[j]]==0)
                            s[c[j]]=-1*s[c[k]];
                else
                    if(s[c[k]]==s[c[j]]&&s[c[k]]!=0&&s[c[j]]!=0)
                        ok=0;
        }
    if(ok==0) cout<<"nu este bipartit";
        else
        {
            cout<<"este bipartit"<<endl;
        }
    return 0;
}
