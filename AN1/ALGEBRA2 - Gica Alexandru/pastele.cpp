#include <iostream>
#include <fstream>

using namespace std;

ofstream fout("text.out");
int main()
{

int x, a, b, c, d, e, paste;

for(x=3000; x<=5000; x++)
{
    a=x%19;
    b=x%4;
    c=x%7;
    d=((19*a)+15)%30;
    e=((2*b)+(4*c)+(6*d)+6)%7;
    paste=d+e+4;
    fout<<x<<' '<<paste<<'\n';
    //cout<<x<<' '<<paste<<'\n';
    a=0;b=0;c=0;d=0;e=0;paste=-99999;


}


    return 0;
}
