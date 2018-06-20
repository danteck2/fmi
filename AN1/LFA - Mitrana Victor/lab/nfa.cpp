#include <iostream>
#include <fstream>
#include <cstring>
#include <queue>
#include <vector>
#include <set>

using namespace std;

ifstream fin("NFA.in");

int md[200][200][200]; //starile
int Q[100]; //nr stari
char V[200]; //litere/cuvant
int F[100]; //starile finale
int n_stari, n_litere, n_st_fin, n_tranz, q0, q, r, q_curent, ok;

vector<int> Qs;//
queue<vector<int> > coada;
set<vector<int> > viz;//

vector<int> delta(int q, char a)
{
    vector<int> aux;
    for(int i=1; i<=n_stari; i++)
    {
        if(md[q][a][i]==1)
        {
            aux.push_back(i);
            cout<<"q="<<q<<" a="<<a<<" i="<<i<<endl;
        }
    }
    cout<<"Sunt in stare: "<<q<<" Cu litera: "<<a<<endl;
    cout<<"aux[i] ";
    for(int i=0; i<aux.size(); i++)
        cout<<aux[i]<<' ';
    cout<<endl;
    return aux;
}

int main()
{
    char str[100];
    char c;

    fin>>n_stari;
    for(int i=0; i<n_stari; i++)
        fin>>Q[i];

    fin>>n_litere;
    for(int i=0; i<n_litere; i++)
        fin>>V[i];

    fin>>q0;

    fin>>n_st_fin;
    for(int i=0; i<n_st_fin; i++)
        fin>>F[i];

    fin>>n_tranz;
    for(int i=0; i<n_tranz; i++)
        {
            fin>>q>>c>>r;
            cout<<q<<' '<<c<<' '<<r<<endl;
            md[q][c][r]=1;
        }
    vector<int> q0vec;
    q0vec.push_back(q0);
    coada.push(q0vec);
    while(!coada.empty())
    {
        Qs = coada.front();
        coada.pop();
        viz.insert(Qs);
        for(int i=0; i<n_litere; i++)
        {
            char a_1=V[i];
            set<int>  S;
            cout<<"Qs size = "<<Qs.size()<<endl;
            for(int j=0; j<Qs.size(); j++)
            {
                cout<<endl<<"Qsssssssssssss "<<Qs[j]<<endl;
                int q_1 = Qs[j];
                vector<int> vectorumeu = delta(q_1, a_1);
                for(int j=0; j<vectorumeu.size(); j++)
                S.insert( vectorumeu[j]);
            }
           // return 0;
           cout<<"-----------------"<<endl;
           for(auto v : S)
           {
            cout<<v<<' ';
           }
            cout<<endl;

            vector<int> vector2;
            for(auto v : S)
           {
            vector2.push_back(v);
            //return 0;
            cout<<v<<"-===-==--==-=-=-=";
           }
           cout<<"aici";
           for(auto vec : viz)
           {


            for(int k = 0; k<vec.size(); k++)
                cout<<vec[k]<<' ';
           cout<<endl;
            }
            if(viz.find(vector2 ) == viz.end())
            {
                coada.push(vector2);
                cout<<"am adaugat un vector";
            }
        }
    }

    return 0;
}
