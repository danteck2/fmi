#include <iostream>

using namespace std;

int main()
{
    int a[100], n, x, i, gasit=0;
    cout<<"n=";cin>>n;
    for(i=0; i<n; i++)
    cin>>a[i];
    cout<<endl<<"x=";cin>>x;
    for(i=0; i<=n; i++)
        if(x==a[i])
            gasit=1;
    if(gasit==1)
        cout<<"Numarul "<<x<<" se gaseste in sir.";
    else
        cout<<"Numarul "<<x<<" nu se gaseste in sir.";
    return 0;
}
