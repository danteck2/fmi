#include <iostream>

using namespace std;

int main()
{
    int n, aux;
    cout<<"Introduceti marimea: "<<endl;
    cin>>n;
    int a[100];
    for (int i=0; i<n; i++)
        cin>>a[i];
    for(int i=0; i<n; i++)
    {
        aux=a[i];
        int j=i-1;
        while(j>=0 && a[j]>aux)
        {
            a[j+1]=a[j];
            j--;
        }
        a[j+1]=aux;
    }
    for(int i=0; i<n; i++)
        cout<<a[i]<<' ';
    return 0;
}
