#include <iostream>
#include <fstream>

class numar_complex
{
//private:
	int a;
	int b;
	int i;
public:
	numar_complex(int c, int d, int j);//int c=0, int d=0, int j=1
	~numar_complex();
	void set_nr_complex(int c, int d, int j);
	int get_a_nr_complex();
	int get_b_nr_complex();
	int get_i_nr_complex();
	numar_complex operator+(const numar_complex nr2);
	numar_complex operator-(const numar_complex nr2);
	numar_complex operator*(const numar_complex nr2);
	numar_complex operator/(const numar_complex nr2);
};

class matrice
{
//private:
	int n; //linii
	int m; //coloane
	numar_complex **matrix;
public:
	matrice(int x, int y);//int x=1, int y=1
	matrice(const matrice &m2);
	~matrice();
	matrice operator=(const matrice m2);
	matrice operator+(const matrice m2);
	matrice operator*(const matrice m2);
	numar_complex determinant();
	void inversa();
	void set_matrice();
	//friend istream &operator>>( istream &input,const matrice &m);
	void set_matrice_txt1();
	void set_matrice_txt2();
	void set_matrice_txt3();
	void show_matrice();
	//friend ostream &operator<<( ostream &output, matrice &m);
};