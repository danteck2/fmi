#include <iostream>
#include <fstream>
#include <vector>
#include <string>

using namespace std;

ofstream g("DFA.out");

class tranz{
    int q1,q2;
    char a;
public:
    void setval(int,char,int);
    int i();
    char c();
    int f();
};

void tranz::setval(int qi,char c,int qf){
    q1=qi;
    a=c;
    q2=qf;
}
int tranz::i(){
    return q1;
}

char tranz::c(){
    return a;
}

int tranz::f(){
    return q2;
}

template <typename T>
void add(vector<T> &v,T x){
    unsigned int i, j;
    T aux;
    for(i=0;i<v.size();i++)
        if(v[i]==x)
            return;
    v.push_back(x);
    for(i=0;i<v.size();i++)
        for(j=i+1;j<v.size();j++)
            if(v[i]>v[j]){
                aux=v[i];
                v[i]=v[j];
                v[j]=aux;
            }
}

template <typename T>
void add(vector<T> &v,vector<T> x){
    unsigned int i, j;
    T aux;
    int ok;
    for(i=0;i<x.size();i++){
        ok=1;
        for(j=0;j<v.size();j++)
            if(v[j]==x[i])
                ok=0;
        if(ok==1)
            v.push_back(x[i]);
    }
    for(i=0;i<v.size();i++)
        for(j=i+1;j<v.size();j++)
            if(v[i]>v[j]){
                aux=v[i];
                v[i]=v[j];
                v[j]=aux;
            }
}

vector<int> delta(vector<tranz> d,int q,char a){
    unsigned int i;
    vector<int> v;
    for(i=0;i<d.size();i++)
        if((d[i].i()==q)&&(d[i].c()==a)){
                add<int>(v,d[i].f());
        }
    return v;
}

vector<int> delta(vector<tranz> d,vector<int> q,char a){
    unsigned int i;
    vector<int> v;
    for(i=0;i<q.size();i++)
        add<int>(v,delta(d,q[i],a));
    return v;
}

vector< vector<int> > stari_finale(vector< vector<int> > Q2,vector<int> F){
    unsigned int i, j, k, ok;
    vector< vector<int> > F2;
    vector<int> temp;
    for(i=0;i<Q2.size();i++){
        temp.clear();
        ok=0;
        for(j=0;j<Q2[i].size();j++)
            for(k=0;k<F.size();k++)
                if(Q2[i][j]==F[k])
                    ok=1;
        if(ok)
            temp=Q2[i];
        if(temp.size()>0)
            add< vector<int> >(F2,temp);
    }
    return F2;
}

int nr_t(vector< vector<int> > Q2,vector<char> sigma,vector<tranz> d){
    unsigned int i, j, k=0;
    for(i=0;i<Q2.size();i++)
        for(j=0;j<sigma.size();j++)
            if(delta(d,Q2[i],sigma[j]).size()>0)
                k++;
    return k;
}

void create_DFA(vector<tranz> d, vector<int> Q, vector<char> sigma,int q0, vector<int> F){
    vector< vector<int> > Q2, F2;
    vector<int> temp;
    temp.push_back(q0);
    Q2.push_back(temp);
    unsigned int i, j, k=0;
    int ok=0;
    while(ok==0){
    temp.clear();
    for(i=0;i<sigma.size();i++){
        temp=delta(d,Q2[k],sigma[i]);
        if(temp.size()>0){
               // cout<<"?"<<//!
        add< vector<int> >(Q2,temp);
        }
    }
    k++;
    if(k>=Q2.size())
        ok=1;
    }
    g<<Q2.size()<<"\n";
    for(i=0;i<Q2.size();i++){
        for(k=0;k<Q2[i].size();k++)
            g<<Q2[i][k];
        if(i<Q2.size()-1)
            g<<" ";
    }
    g<<"\n";
    g<<sigma.size()<<"\n";
    for(i=0;i<sigma.size();i++){
        g<<sigma[i];
        if(i<sigma.size()-1)
            g<<" ";
    }
    g<<"\n"<<q0<<"\n";
    F2=stari_finale(Q2,F);
    g<<F2.size()<<"\n";
    for(i=0;i<F2.size();i++){
        for(k=0;k<F2[i].size();k++)
            g<<F2[i][k];
        if(i<F2.size()-1)
            g<<" ";
    }
    g<<"\n"<<nr_t(Q2,sigma,d)<<"\n";
    for(i=0;i<Q2.size();i++){
            temp.clear();
        for(j=0;j<sigma.size();j++){
            temp=delta(d,Q2[i],sigma[j]);
            if(temp.size()>0){
                for(k=0;k<Q2[i].size();k++)
                    g<<Q2[i][k];
                g<<" "<<sigma[j]<<" ";
                for(k=0;k<temp.size();k++)
                    g<<temp[k];
                g<<"\n";
            }
        }
    }
}

int main(){
    int q0, i, n, x, y;// n nr stari,,,Q starile , F starile finale, sigma carcact
    vector<int> Q, F;
    vector<char> sigma;
    vector<tranz> d;
    ifstream f("NFA.in");
    char c;
    tranz t;
    f>>n;
    for(i=0;i<n;i++){
        f>>x;
        Q.push_back(x);
    }
    f>>n;
    for(i=0;i<n;i++){
        f>>c;
        sigma.push_back(c);
    }
    f>>q0;
     f>>n;
    for(i=0;i<n;i++){
        f>>x;
        F.push_back(x);
    }
    f>>n;
    for(i=0;i<n;i++){
        f>>x>>c>>y;
        t.setval(x,c,y);
        d.push_back(t);
    }
    create_DFA(d,Q,sigma,q0,F);
    f.close();
    g.close();
    return 0;
}
