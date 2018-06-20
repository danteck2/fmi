///NUME PRENUME GRUPA C++11 GCC Compiler
#include <iostream>
#include <cstring>
#include <string>
#include <vector>

using namespace std;

class Cerere
{
    bool video;
    bool ecran;//1 down 0 up
    bool tabla;
public:
    Cerere(bool _video=false, bool _ecran=false, bool _tabla=false);
    ~Cerere();
    bool get_video();
    bool get_ecran();
    bool get_tabla();
    void set_video(bool x);
    void set_ecran(bool x);
    void set_tabla(bool x);
};
class Home : public Cerere
{
public:
    Home(bool _video=false, bool _ecran=false, bool _tabla=false);
    Home(Home &aux);
    ~Home();
    void Home_Porneste(char x[]);
    void Home_Opreste(char x[]);
    Home operator=(Home aux);
    Home operator+(int aux);
    Home operator+(char aux);
    char* Home_verificare();
    friend istream& operator>>(istream &i, Home &op);
};
class Education : public Cerere
{
public:
    Education(bool _video=false, bool _ecran=false, bool _tabla=false);
    Education(Education &aux);
    ~Education();
    void Edu_Triva(char sir[]);
    char* Edu_Lectie(char nume_curs[], int nr_curs);
    friend istream& operator>>(istream &i, Education &op);
};
class Sesiune
{
static int numar;
char data[20];
bool student;
Cerere cerere;
public:
    Sesiune( char _data[], bool _student, Cerere _cerere);
    ~Sesiune();
    void Afisare_data(char _data[]);
};
int Sesiune::numar = 1;
istream& operator>>(istream &i, Home &op)
{

    bool vid, ecr, tabla;
    cout<<endl<<"Introduceti: 1 videoproiector pornit, 0 videoproiector oprit ";i>>vid;
    op.set_video(vid);
    cout<<endl<<"Introduceti: 1 ecran de proiectie pornit, 0 ecran de proiectie pornit oprit ";i>>ecr;
    op.set_ecran(ecr);
    cout<<endl<<"Introduceti: 1 Tabla Smart pornit, 0 Tabla Smart oprit ";i>>tabla;
    op.set_tabla(tabla);
    cout<<endl;
    return i;
}
istream& operator>>(istream &i, Education &op)
{
    bool vid, ecr, tabla;
    cout<<endl<<"Introduceti: 1 videoproiector pornit, 0 videoproiector oprit ";i>>vid;
    op.set_video(vid);
    cout<<endl<<"Introduceti: 1 ecran de proiectie pornit, 0 ecran de proiectie pornit oprit ";i>>ecr;
    op.set_ecran(ecr);
    cout<<endl<<"Introduceti: 1 Tabla Smart pornit, 0 Tabla Smart oprit ";i>>tabla;
    op.set_tabla(tabla);
    cout<<endl;
    return i;
}
Cerere::Cerere(bool _video, bool _ecran, bool _tabla)
{
    video = _video;
    ecran = _ecran;
    tabla = _tabla;
}
Cerere::~Cerere()
{
    //
}
bool Cerere::get_ecran()
{
    return ecran;
}
bool Cerere::get_tabla()
{
    return tabla;
}
bool Cerere::get_video()
{
    return video;
}
void Cerere::set_ecran(bool x)
{
    ecran = x;
}
void Cerere::set_tabla(bool x)
{
    tabla = x;
}
void Cerere::set_video(bool x)
{
    video = x;
}
Home::Home(bool _video, bool _ecran, bool _tabla) : Cerere(_video, _ecran, _tabla)
{
   // set_ecran(_ecran);
   // set_video(_video);
   // set_tabla(_tabla);
}
Home::Home(Home &aux)
{

    bool vid, tab, ecr;
    vid = aux.get_video();
    tab = aux.get_tabla();
    ecr = aux.get_ecran();
    set_video(vid);
    set_tabla(tab);
    set_ecran(ecr);
}
Home::~Home()
{
    //
}
void Home::Home_Porneste(char x[])
{
    if(strcmp(x, "tabla")==0)
        set_tabla(true);
    if(strcmp(x, "video-proiector")==0)
        set_video(true);
    if(strcmp(x, "ecran")==0)
        set_ecran(true);
}
void Home::Home_Opreste(char x[])
{
    if(strcmp(x, "tabla")==0)
        set_tabla(false);
    if(strcmp(x, "video-proiector")==0)
        set_video(false);
    if(strcmp(x, "ecran")==0)
        set_ecran(false);
}
Home Home::operator=(Home aux)
{
    Home copie;
    copie.set_video(aux.get_video());
    copie.set_tabla(aux.get_tabla());
    copie.set_ecran(aux.get_ecran());
    return copie;
}
Home Home::operator+(int aux)
{
    if(aux == 1)
        set_video(true);
    else set_video(false);

}
Home Home::operator+(char aux)
{
    if(aux == 'E')
        set_video(true);//true inseamna down
}
char* Home::Home_verificare()
{
    if((get_ecran()==true && get_video()==true && get_tabla()==false) || (get_ecran()==false && get_video()==false && get_tabla()==true))
        return "OK";
    else
        return "Nu am inteles cerinta!";
}

Education::Education(bool _video, bool _ecran, bool _tabla) : Cerere(_video, _ecran, _tabla)
{
   // set_ecran(_ecran);
   // set_video(_video);
   // set_tabla(_tabla);
}
Education::Education(Education &aux)
{
    bool vid, tab, ecr;
    vid = aux.get_video();
    tab = aux.get_tabla();
    ecr = aux.get_ecran();
    set_video(vid);
    set_tabla(tab);
    set_ecran(ecr);
}
Education::~Education()
{
    //
}
void Education::Edu_Triva(char sir[])
{
    cout<<"Cautare "<<sir<<endl;
}
char* Education::Edu_Lectie(char nume_curs[], int nr_curs)
{
    if(strcmp(nume_curs, "Algebra")==0)
        return "Download - Algebra - curs 3\n";
    else if(strcmp(nume_curs, "POO")==0)
        return "Eroare componenta hardware \n";
}
Sesiune::Sesiune(char _data[], bool _student, Cerere _cerere)
{
    numar++;
    strcpy(data, _data);
    student = _student;
    cerere = _cerere;
}
Sesiune::~Sesiune()
{
    //
}
void Sesiune::Afisare_data(char _data[])
{
    if(strcmp(data, _data)==0)
    {
        cout<<"Numar "<<numar<<" data: "<<data<<" student: "<<student;
    }
}
int main()
{
    cout<<"-------------------start-demo-main-------------------"<<endl<<endl;
    Home h1, h2(1), h3(0,0,1); //Video - 0(of), Ecran - 0 (up), Tabla - 1(on)
    cin>>h1;
    h2.Home_Opreste("tabla");
    Home h4(h2), h5 = h2;
    h4 + 1;//se porneste video-proiectorul
    h4 + 'E'; //se coboara ecranul de proiectie
    cout<<h1.Home_verificare()<<" "<<h2.Home_verificare()<<" "<<h4.Home_verificare();

    Education e1, e2(1), e3(1, 1, 0), e4(e3);
    cin>>e1;
    cout<<endl<<e4.Edu_Lectie("Algebra", 3); //Se va afisa "Download - Algebra - Curs 3"
    e2.Edu_Triva("Data Structures"); //Se va afisa "Cautare Data Structures"
    cout<<e2.Edu_Lectie("POO", 2); //Se va afisa "Eroare componenta hardweare"

    Cerere *c = new Cerere[4];
    c[0]=h3; c[1]=h4; c[2]=e3; c[3]=e4;
    for(int i = 0; i<4; i++) cout<<c[i].get_video()<<" "<<c[i].get_ecran()<<" "<<c[i].get_tabla()<<endl;
    cout<<"-------------------end-demo-main-------------------"<<endl;
    char _data[20];
    cout<<"Introduceti data: ";cin.getline(_data,19);
    Sesiune s1(_data, true, c[0]);
    s1.Afisare_data("01.09.2016");

    return 0;
}
