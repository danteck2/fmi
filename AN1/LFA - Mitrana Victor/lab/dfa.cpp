#include <iostream>
#include <fstream>
#include <cstring>

using namespace std;
ifstream fin("DFA.in");
ofstream fout("DFA.out");
int md[200][200]; //starile
int Q[100]; //nr stari
char V[200]; //litere/cuvant
int F[100]; //starile finale

int delta(int q, char a)
{
    return md[q][a];
  //  delta[1][a]=2;
}

int delta_tilda(int q, char *s)
{
    if(strlen(s)==1)
        return delta(q, s[0]);
    else
        return delta_tilda(delta(q, s[0]), s+1);
}

int main()
{
    int n_stari, n_litere, n_st_fin, n_tranz,n_cuv, q0, q, r, q_curent, ok;
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
            md[q][c]=r;
        }
    fin>>n_cuv;

    for(int i=0; i<n_cuv; i++)
        {
            fin>>str;
            q_curent=delta_tilda(q0, str);

            ok=0;
            for(int i=0; i<n_st_fin; i++)
                if(q_curent==F[i]) {ok=1;break;}
            if(ok==1)fout<<"DA\n";
            else fout<<"NU\n";
        }

    return 0;
}
