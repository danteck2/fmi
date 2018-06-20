#include <iostream>
#include <cmath>

using namespace std;
int punct(int x){
    if(x<5)
        return x;
    return x%4;
}

int f(int A1[],int A2[], int A3[]){
    return A3[1]*A1[2]+A3[2]*A2[1]+A1[1]*A2[2]-(A1[2]*A2[1]+A1[1]*A3[2]+A3[1]*A2[2]);
}

int main(){
    int A[5][3] ,ok=1;
    cin>>A[1][1]>>A[1][2];
    cin>>A[2][1]>>A[2][2];
    cin>>A[3][1]>>A[3][2];
    cin>>A[4][1]>>A[4][2];
    for(int i=1;i<5;i++)
        if(f(A[i],A[punct(i+1)],A[punct(i+2)])*f(A[i],A[punct(i+1)],A[punct(i+3)])<0)
            ok=0;
    if(ok)
        cout<<"este convex"<<endl;
    else
        cout<<"nu este convex"<<endl;
    cout<<"Punctul de verificat:";
    int n[3];
    cin>>n[1]>>n[2];
    if( ( abs( f(A[2],A[3],A[1]) )==( abs( f(A[1],A[2],n) )+abs( f(A[2],A[3],n) )+abs( f(A[1],A[3],n) ))) ||
        ( abs( f(A[2],A[3],A[4]) )==( abs( f(A[4],A[2],n) )+abs( f(A[2],A[3],n) )+abs( f(A[4],A[3],n) ))) ||
        ( abs( f(A[1],A[3],A[4]) )==( abs( f(A[4],A[1],n) )+abs( f(A[1],A[3],n) )+abs( f(A[4],A[3],n) ))) ||
        ( abs( f(A[2],A[1],A[4]) )==( abs( f(A[4],A[2],n) )+abs( f(A[2],A[1],n) )+abs( f(A[4],A[1],n) )))  )
        cout<<"este in acoperirea convexa a multimii {A1,A2,A3,A4}";
    else
        cout<<"nu este in acoperirea convexa a multimii {A1,A2,A3,A4}";
    return 0;
}
