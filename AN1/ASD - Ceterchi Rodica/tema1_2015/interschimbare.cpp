#include <iostream>

using namespace std;

int main()
{
    int a[100], aux, ok=1;
    cout<<"Introduceti marimea: "<<endl;
    int n;
    cin>>n;
    for(int i=0; i<n; i++)
		cin>>a[i];
    while(ok!=0)
    {
        ok=0;
        for(int i=0; i<n-1; i++)
            if(a[i+1]<a[i]){aux=a[i]; a[i]=a[i+1]; a[i+1]=aux; ok=1;}

    }

    cout<<endl;
    for(int i=0; i<n; i++)
        cout<<a[i]<<" ";
    cout<<endl;
    return 0;
}
