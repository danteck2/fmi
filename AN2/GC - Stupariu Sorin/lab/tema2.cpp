/*
    Fie ABCD un patrulater in R^2 (coordonatele varfurilor fiind cunoscute)
    a) Sa se decida daca ABCD este patrulater convex ( cu viraje )
    b) Sa se decida dca un punct M apartine acoperirii convexe a multimii { A, B, C, D } ( cu arie )
*/
#include <iostream>

using namespace std;
struct Punct{
float x,y;};
float mod(int a)
{
    return a>=0 ? a:-a;
}
float static viraj(Punct a, Punct b, Punct c)
    {
        float x=(a.x*b.y + b.x*c.y + a.y*c.x - b.y*c.x - a.x*c.y - a.y*b.x);
        return x;
    }
float static arie(Punct a, Punct b, Punct c)
    {
        float x=0.5 * mod((a.x*b.y + b.x*c.y + a.y*c.x - b.y*c.x - a.x*c.y - a.y*b.x));
        return x;
    }
bool static inter(Punct A, Punct B, Punct C, Punct M)
    {
        return (arie(M,A,B) + arie(M, B, C) + arie(M,A,C)  ==  arie(A,B,C) ? true:false);
    }
int main()
{
    Punct A,B,C,D,M;

    cout<<"\nA:";
    cin>>A.x>>A.y;

    cout<<"\nB:";
    cin>>B.x>>B.y;

    cout<<"\nC:";
    cin>>C.x>>C.y;

    cout<<"\nD:";
    cin>>D.x>>D.y;

    float v1,v2,v3;
    v1=viraj(A,B,C);
    v2=viraj(B,C,D);
    v3=viraj(C,D,A);

    if((v1>0 && v2>0 && v3>0) || (v1<0 && v2<0 && v3<0))
    {
        cout<<"\nPatrulaterul este convex\n";
    }
    else
    {
        cout<<"\nPatrulaterul nu este convex\n";
    }

        cout<<"\nM:";
        cin>>M.x>>M.y;
        if(inter(A,B,C,M) || inter(A,B,D,M) || inter(B,C,D,M) || inter(A,C,D,M))
            cout<<"\nPunctul este in interiorul acoperirii convexe\n";
        else
            cout<<"\nPunctul nu este in interiorul acoperirii convexe\n";
    //cout << "Hello world!" << endl;
    return 0;
}
