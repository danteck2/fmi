#include <iostream>
#include<math.h>

using namespace std;
float a[3][2];
//A
//a[0][0] = 10 x
//a[0][1] = 15 y
//a[0][2] = 17 z

//B
//a[1][0] = 10
//a[1][1] = 15
//a[1][2] = 17


int i,j;
int main()
{
    for(i=0;i<3;i++)
    for(j=0;j<3;j++)
    {
        cin>>a[i][j];
    }
    int ok=0;
    if(a[0][0]==a[1][0]&&a[0][1]==a[1][1]&&a[0][2]==a[1][2])
        ok=1;
    if(a[0][0]==a[2][0]&&a[0][1]==a[2][1]&&a[0][2]==a[2][2])
               ok=2;
    if(a[2][0]==a[1][0]&&a[2][1]==a[1][1]&&a[2][2]==a[1][2])
                    ok=3;
    float c1, c2, c3;

        //else
        {
                c1=(a[1][0]-a[2][0])/(a[0][0]-a[2][0]);
                c2=(a[1][1]-a[2][1])/(a[0][1]-a[2][1]);
                c3=(a[1][2]-a[2][2])/(a[0][2]-a[2][2]);
        }
       // cout<<c1<<' '<<c2<<' '<<c3<<'\n';
       if(ok==0)
        if((isnan(c1)==1&&c2==c3))
               {
                   cout<<"punctele sunt coliniare"<<'\n';
                   cout<<"B="<<c2<<"*"<<"C+"<<1-c2<<"*A";
               }
            else
            if(isnan(c2)==1&&c1==c3)
                {
                    cout<<"punctele sunt coliniare";
                    cout<<"B="<<c3<<"*"<<"C+"<<1-c3<<"*A";
                }
            else
            if(isnan(c3)==1&&c1==c2)
            {
                    cout<<"punctele sunt coliniare";
                    cout<<"B="<<c2<<"*"<<"C+"<<1-c2<<"*A";
            }
            else
            if(c1==c2&&c2==c3)
            {
                cout<<"punctele sunt coliniare";
                    cout<<"B="<<c3<<"*"<<"C+"<<1-c2<<"*A";
            }
            else
                cout<<"punctele nu sunt coliniare"<<'\n';
       // cout<<"A="<<c1<<"*"<<"B+"<<1-c1<<"*C";
//cout<<" "<<c1<<" "<<c2<<" "<<c3;
if(ok==1)
        {
            cout<<"A si B coincid"<<endl;
          cout<<"A="<<1<<"*"<<"B+"<<0<<"*C";
            return 0;
        }

if(ok==2)
        {
            cout<<"A si C coincid"<<endl;
          cout<<"A="<<1<<"*"<<"C+"<<0<<"*B";
            return 0;
        }

if(ok==3)
        {
            cout<<"B si C coincid"<<endl;
          cout<<"B="<<1<<"*"<<"C+"<<0<<"*A";
            return 0;
        }
//cout<<"B="<<c1<<"*"<<"C+"<<1-c1<<"*A";


    //cout << "Hello world!" << endl;
    return 0;
}
