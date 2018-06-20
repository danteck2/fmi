#include <iostream>

using namespace std;

class punct
{
    public:
    double x,y,z;
    punct (double xt,double yt,double zt)
    {
        x=xt;
        y=yt;
        z=zt;
    }
    punct () {};
    void read ()
    {
        cout<<"x,y,z"<<endl;
        cin>>x>>y>>z;
    }
    double getl(punct k)
    {
        if ( k.x!=0)
        return this->x/k.x;
        if ( k.y!=0)
        return this->y/k.y;
        if ( k.z!=0)
        return this->z/k.z;


    }
};

class coliniare
{
        punct a,b,c;
    public:
         coliniare()
        {
            a.read();
            b.read();
            c.read();
        }
        int test ()
        {
            if(a.x-b.x==0 && a.y-b.y==0 && a.z-b.z==0)
               {
                   cout<<"A1=A2*1 +A3*0 ";
                    return 1;
               }
            if(c.x-b.x==0 && c.y-b.y==0 && c.z-b.z==0)
                {
                   cout<<"A3=A2*1 +A1*0 ";
                    return 1;
                }
            if(a.x-c.x==0 && a.y-c.y==0 && a.z-c.z==0)
                {
                   cout<<"A1=A3*1 +A2*0 ";
                    return 1;
                }
            punct v1(a.x-b.x, a.y-b.y, a.z-b.z);
            punct v2(b.x-c.x, b.y-c.y, b.z-c.z);
            double l=v1.getl(v2);
            if(l* v2.x==v1.x && l* v2.y==v1.y && l *v2.z==v1.z)
                {
                    cout<<"A1="<<1+l<<"*A2+"<<-l<<"*A3 ";
                    return 1;
                }
            return 0;
        }

};
int main()
{
    coliniare p;
    if(p.test())
        cout<<"coliniare";
    else
        cout<<"NU sunt coliniare";
    return 0;
}
