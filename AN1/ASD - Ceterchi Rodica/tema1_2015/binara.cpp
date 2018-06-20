#include <iostream>

using namespace std;

int main()
{
    int a[100], n, k;
    cout<<"n=";cin>>n;
    for(int i=0; i<n; i++)
        cin>>a[i];
    cout<<endl<<"k=";cin>>k;
    int mij,st=1,dr=n,ok=0;
    do
    {
        mij=(st+dr)/2;
        if(a[mij]==k) ok=1;
        else if(a[mij]<k) st=mij+1;
        else dr=mij-1;
    }while(st<=dr && ok==0);
    if (ok==1) cout<<"elementul este in sir";
    else cout<<"elementul nu este in sir";
    return 0;
}
