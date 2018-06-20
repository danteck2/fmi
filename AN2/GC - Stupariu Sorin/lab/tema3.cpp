#include <iostream>

using namespace std;
class Segment
{
  int a,b,c;
  public:
    Segment(int x1,int x2,int y1,int y2)
    {
        a=y1-y2;
        b=x2-x1;
        c=x1*y2-y1*x2;
    }
    int geta()
    {
        return a;
    }
    int getb()
    {
         return b;
    }
    int getc()
    {
        return c;
    }
};

int main()
{
    int a[5][3],i,x,y,det;
    cout<<"Dati coordonatele punctelor: "<<endl;
    for(i=1;i<5;i++)
    {
        cout<<"A"<<i<<":";
        cin>>a[i][1];
        cin>>a[i][2];
    }
    Segment s1(a[1][1],a[2][1],a[1][2],a[2][2]),s2(a[3][1],a[4][1],a[3][2],a[4][2]);
    det=s1.geta()*s2.getb()-s1.getb()*s2.geta();
    if(det!=0)
    {
        x=(-s1.getc()*s2.getb()+s1.getb()*s2.getc())/det;
        y=(-s1.geta()*s2.getc()+s1.getc()*s2.geta())/det;
        if(((x>=a[1][1] && x<=a[2][1]) || (x>=a[2][1] &&x<=a[1][1])) &&((y>=a[1][2]&& y<=a[2][2]) ||(y>=a[2][1] && y<=a[1][2])))
            cout<<"Segmentele se intersecteaza in punctul M("<<x<<","<<y<<")"<<endl;
         else cout<<"Cele 2 segmente nu au puncte comune."<<endl;
    }
    else
    {
       if((s1.geta()*s2.getc()-s2.geta()*s1.getc())!=0 || (s1.getb()*s2.getc()-s2.getb()*s1.getc())!=0)  ///rangul =2
         cout<<"Cele 2 segmente nu au puncte comune."<<endl;
       else
       {
        if(s1.geta()!=0 || s2.geta()!=0 || s1.getb()!=0 || s2.getb()!=0 || s1.getc()!=0 || s2.getc()!=0)
        {
            if((a[1][1]<a[3][1] && a[1][1]<a[4][1] && a[2][1]<a[3][1] && a[2][1]<a[4][1]) || (a[1][2]<a[3][2] &&
              a[1][2]<a[4][2] && a[2][2]<a[3][2] && a[2][2]<a[4][2]) || (a[3][1]<a[1][1] && a[4][1]<a[1][1] &&
              a[3][1]<a[2][1] && a[4][1]<a[2][1]) || (a[3][2]<a[1][2] && a[4][2]<a[1][2] && a[3][2]<a[2][2] &&
              a[4][2]<a[2][2]))  cout<<"Cele 2 segmente nu au puncte comune."<<endl;
            else
            {
                if(a[1][1]==a[2][1] && a[2][1]==a[3][1] && a[3][1]==a[4][1])
                {
                    if((a[1][2]<=a[3][2] && a[3][2]<=a[2][2] && a[2][2]<=a[4][2]) || (a[1][2]>=a[3][2] && a[3][2]>=a[2][2] && a[2][2]>=a[4][2]))
                       cout<<"Intersectia este segmentul format din punctele A2 si A3"<<endl;
                     else if((a[1][2]<=a[4][2] && a[4][2]<=a[2][2] && a[2][2]<=a[3][2]) || (a[1][2]>=a[4][2] && a[4][2]>=a[2][2] && a[2][2]>=a[3][2]))
                        cout<<"Intersectia este segmentul format din punctele A2 si A4"<<endl;
                     else if((a[2][2]<=a[3][2] && a[3][2]<=a[1][2] && a[1][2]<=a[4][2]) || (a[2][2]>=a[3][2] && a[3][2]>=a[1][2] && a[1][2]>=a[4][2]))
                        cout<<"Intersectia este segmentul format din punctele A1 si A3"<<endl;
                     else if((a[2][2]<=a[4][2] && a[4][2]<=a[1][2] && a[1][2]<=a[3][2]) || (a[2][2]>=a[4][2] && a[4][2]>=a[1][2] && a[1][2]>=a[3][2]))
                        cout<<"Intersectia este segmentul format din punctele A1 si A4"<<endl;
                     else if((a[1][2]<=a[3][2] && a[3][2]<=a[4][2] && a[4][2]<=a[2][2]) || (a[1][2]>=a[3][2] && a[3][2]>=a[4][2] && a[4][2]>=a[2][2]))
                        cout<<"Intersectia este segmentul format din punctele A3 si A4"<<endl;
                      else if((a[2][2]<=a[3][2] && a[3][2]<=a[4][2] && a[4][2]<=a[1][2]) || (a[2][2]>=a[3][2] && a[3][2]>=a[4][2] && a[4][2]>=a[1][2]))
                        cout<<"Intersectia este segmentul format din punctele A3 si A4"<<endl;
                      else if((a[3][2]<=a[1][2] && a[1][2]<=a[2][2] && a[2][2]<=a[4][2]) || (a[3][2]>=a[1][2] && a[1][2]>=a[2][2] && a[2][2]>=a[4][2]))
                        cout<<"Intersectia este segmentul format din punctele A1 si A2"<<endl;
                      else if((a[3][2]<=a[2][2] && a[2][2]<=a[1][2] && a[1][2]<=a[4][2]) || (a[3][2]>=a[2][2] && a[2][2]>=a[1][2] && a[1][2]>=a[4][2]))
                        cout<<"Intersectia este segmentul format din punctele A1 si A2"<<endl;

                }
                else
                {
                    if((a[1][1]<=a[3][1] && a[3][1]<=a[2][1] && a[2][1]<=a[4][1]) || (a[1][1]>=a[3][1] && a[3][1]>=a[2][1] && a[2][1]>=a[4][1]))
                        cout<<"Intersectia este segmentul format din punctele A2 si A3"<<endl;
                    else  if((a[1][1]<=a[4][1] && a[4][1]<=a[2][1] && a[2][1]<=a[3][1]) || (a[1][1]>=a[4][1] && a[4][1]>=a[2][1] && a[2][1]>=a[3][1]))
                        cout<<"Intersectia este segmentul format din punctele A2 si A4"<<endl;
                    else  if((a[2][1]<=a[3][1] && a[3][1]<=a[1][1] && a[1][1]<=a[4][1]) || (a[2][1]>a[3][1] && a[3][1]>a[1][1] && a[1][1]>a[4][1]))
                        cout<<"Intersectia este segmentul format din punctele A1 si A3"<<endl;
                     else if((a[2][1]<=a[4][1] && a[4][1]<=a[1][1] && a[1][1]<=a[3][1]) || (a[2][1]>a[4][1] && a[4][1]>a[1][1] && a[1][1]>a[3][1]))
                        cout<<"Intersectia este segmentul format din punctele A1 si A4"<<endl;
                    else  if((a[1][1]<=a[3][1] && a[3][1]<=a[4][1] && a[4][1]<=a[2][1]) || (a[1][1]>a[3][1] && a[3][1]>a[4][1] && a[4][1]>a[2][1]))
                        cout<<"Intersectia este segmentul format din punctele A3 si A4"<<endl;
                   else   if((a[2][1]<=a[3][1] && a[3][1]<=a[4][1] && a[4][1]<=a[1][1]) || (a[2][1]>a[3][1] && a[3][1]>a[4][1] && a[4][1]>a[1][1]))
                        cout<<"Intersectia este segmentul format din punctele A3 si A4"<<endl;
                    else  if((a[3][1]<=a[1][1] && a[1][1]<=a[2][1] && a[2][1]<=a[4][1]) || (a[3][1]>a[1][1] && a[1][1]>a[2][1] && a[2][1]>a[4][1]))
                        cout<<"Intersectia este segmentul format din punctele A1 si A2"<<endl;
                    else  if((a[3][1]<=a[2][1] && a[2][1]<=a[1][1] && a[1][1]<=a[4][1]) || (a[3][1]>a[2][1] && a[2][1]>a[1][1] && a[1][1]>a[4][1]))
                        cout<<"Intersectia este segmentul format din punctele A1 si A2"<<endl;
                }
            }

        }
       }
    }
    return 0;
}


