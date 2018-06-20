///NUME PRENUME GRUPA C++11 GCC Compiler
#include <iostream>
#include <cstring>
#include <vector>
#include <cstdlib>

using namespace std;

class Produs
{
    char nume[50];
    int unit;
    int tip;
public:
    Produs(char _nume[], int _unit, int _tip);
    ~Produs();
};
class Lot
{
    char data[20];
    int can;//cantitate
    int pret;
    int val;
    int dis;
public:
    Lot();
    ~Lot();
};
class Magazin
{
    //vector<Produs *> produse;
    vector<Lot *> loturi;
public:
    Magazin();
    ~Magazin();
    void AdProd();
    void AfProd();
};
int main()
{

    return 0;
}
