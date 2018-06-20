#include <iostream>

using namespace std;

int main()
{
    int n, aux;
    cout<<"Introduceti marimea: "<<endl;
    cin>>n;
    int a[100];
    for(int i=0; i<n; i++)
        cin>>a[i];
    for(int i=0; i<n; i++)
    {
        int minv=i;
        for (int j=i+1; j<n; j++)
            if (a[j]<a[minv])
                minv=j;
        aux=a[i];
        a[i]=a[minv];
        a[minv]=aux;
    }
    for(int i=0; i<n; i++)
        cout<<a[i]<<' ';
    return 0;
}
