#include <iostream>

using namespace std;

struct punct{
    int x,y;
};

struct ecuatia_dreptei{
    int a,b,c;
};

int main()
{
    punct a1,a2,a3,a4;

    cout<<"a1:";
    cin>>a1.x>>a1.y;

    cout<<"a2:";
    cin>>a2.x>>a2.y;

    cout<<"a3:";
    cin>>a3.x>>a3.y;

    cout<<"a4:";
    cin>>a4.x>>a4.y;

    ecuatia_dreptei a1a2, a3a4;

    a1a2.a = a1.y - a2.y;
    a1a2.b = a2.x - a1.x;
    a1a2.c = a1.x * a2.y - a2.x * a1.y;

    a3a4.a = a3.y - a4.y;
    a3a4.b = a4.x - a3.x;
    a3a4.c = a3.x * a4.y - a4.x * a3.y;

    int delta = a1a2.a * a3a4.b - a1a2.b * a3a4.a;

    if(delta != 0)

        int x = ( (-a1a2.c * a3a4.b) - ( -a3a4.c * a1a2.b ) ) /delta


}
