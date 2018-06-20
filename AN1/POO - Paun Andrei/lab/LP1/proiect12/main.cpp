#include <iostream>
#include <fstream>
#include "complex.h" //adaugare clase
#include "numar_complex.h"
#include "matrice.h"

using namespace std;
//citirea din fisier

int main()
{
	matrice m1, m2, m3, m4;//m3 suma m4 produs
	int oks=0, okp=0;
	int switch1=1;
	while(switch1 != 0)
	{
		cout<<"Matrice de numere complexe"<<endl;
		cout<<"1. Citire matrice 1: "<<endl;//din fisier 1 2 3 sau tastatura
		cout<<"2. Citire matrice 2: "<<endl;
		cout<<"3. Suma matrici: intre 1 si 2 "<<endl;
		cout<<"4. Produs matrici: intre 1 si 2 "<<endl;
		cout<<"5. Determinantul unei matrici "<<endl;
		cout<<"6. Inversa unei matrici "<<endl;
		cout<<"0. Exit "<<endl;
		cin>>switch1;
		switch(switch1)
		{
		case 1:
			{
				int z;
				cout<<"1. Pentru citirea de la tastatura apasati"<<endl;
				cout<<"2. Pentru citirea din fisier 1"<<endl;
				cout<<"3. Pentru citirea din fisier 2"<<endl;
				cout<<"4. Pentru citirea din fisier 3"<<endl;
				cin>>z;
				switch(z)
				{
					case 1:{m1.set_matrice(); break;}
					case 2:{m1.set_matrice_txt1(); break;}
					case 3:{m1.set_matrice_txt2(); break;}
					case 4:{m1.set_matrice_txt3(); break;}
					default: cout<<"Ati introdus valoare gresita!"<<endl;
				}
				cout<<"Matrice 1 este"<<endl;
				m1.show_matrice();
				break;
			}
		case 2:
			{
				int z;
				cout<<"1. Pentru citirea de la tastatura apasati"<<endl;
				cout<<"2. Pentru citirea din fisier 1"<<endl;
				cout<<"3. Pentru citirea din fisier 2"<<endl;
				cout<<"4. Pentru citirea din fisier 3"<<endl;
				cin>>z;
				switch(z)
				{
					case 1:{m2.set_matrice(); break;}
					case 2:{m2.set_matrice_txt1(); break;}
					case 3:{m2.set_matrice_txt2(); break;}
					case 4:{m2.set_matrice_txt3(); break;}
					default: cout<<"Ati introdus valoare gresita!"<<endl;
				}
				cout<<"Matrice 1 este"<<endl;
				m2.show_matrice();
				break;
			}		
		case 3:
			{
				m3=m1+m2;
				oks=1;
				cout<<"Suma matricelor 1 si 2 este"<<endl;
				m3.show_matrice();
				break;
			}	
		case 4:
			{
				m4=m1*m2;
				okp=1;
				cout<<"Produsul matricelor 1 si 2 este"<<endl;
				m4.show_matrice();
				break;
			}
		case 5:
			{
				int u;
				cout<<"1. Pentru determinant matrice 1"<<endl;
				cout<<"2. Pentru determinant matrice 2"<<endl;
				cout<<"3. Pentru determinant matrice suma"<<endl;
				cout<<"4. Pentru determinant matrice produs"<<endl;
				cin>>u;
				switch(u)
				{
					case 1:
					{
						numar_complex det1; 
						det1=m1.determinant();
						cout<<endl<<"Determinatul matricii 1 = "<<det1.get_a_nr_complex()<<' '<<det1.get_b_nr_complex()<<' '<<det1.get_i_nr_complex()<<' '<<endl<<endl; 
						break;
					}
					case 2:
					{
						numar_complex det2; 
						det2=m2.determinant();
						cout<<endl<<"Determinatul matricii 2 = "<<det2.get_a_nr_complex()<<' '<<det2.get_b_nr_complex()<<' '<<det2.get_i_nr_complex()<<' '<<endl<<endl; 
						break;
					}
					case 3:
					{
						if(oks==1)
						{
							numar_complex det3; 
							det3=m3.determinant();
							cout<<endl<<"Determinatul matricii suma = "<<det3.get_a_nr_complex()<<' '<<det3.get_b_nr_complex()<<' '<<det3.get_i_nr_complex()<<' '<<endl<<endl; 
						}
						else cout<<"Nu a fost calculata suma!"<<endl;
						break;
					}
					case 4:
					{
						if(okp==1)
						{
							numar_complex det4; 
							det4=m4.determinant();
							cout<<endl<<"Determinatul matricii produs = "<<det4.get_a_nr_complex()<<' '<<det4.get_b_nr_complex()<<' '<<det4.get_i_nr_complex()<<' '<<endl<<endl; 
						}
						else cout<<"Nu a fost calculat produsul!"<<endl;
						break;
					}
					default: cout<<"Ati introdus valoare gresita!"<<endl;
				}
				break;
			}
		case 6:
			{
				int q;
				cout<<"1. Pentru inversa matrice 1"<<endl;
				cout<<"2. Pentru inversa matrice 2"<<endl;
				cout<<"3. Pentru inversa matrice suma"<<endl;
				cout<<"4. Pentru inversa matrice produs"<<endl;
				cin>>q;
				switch(q)
				{
					case 1:
					{
						m1.inversa();
						break;
					}
					case 2:
					{
						m2.inversa();
						break;
					}
					case 3:
					{
						if(oks==1)
						{
							m3.inversa();
						}
						else cout<<"Nu a fost calculata suma!"<<endl;
						break;
					}
					case 4:
					{
						if(okp==1)
						{
							m4.inversa();
						}
						else cout<<"Nu a fost calculat produsul!"<<endl;
						break;
					}
					default: cout<<"Ati introdus valoare gresita!"<<endl;
				}
				break;
			}
		case 0:
			{
				cout<<"Iesire"<<endl;
				break;
			}
		default: cout<<"Ati introdus valoare gresita!"<<endl;
		}
	}

	return 0;
}