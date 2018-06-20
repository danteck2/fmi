#include <iostream>
#include <fstream>

numar_complex::numar_complex(int c=0, int d=0, int j=1)//int c, int d, int j
{
	a=c; b=d; i=j;
	//cout<<"construct nr complex"<<endl;
}
//destructor
numar_complex::~numar_complex()
{
	//cout<<"destruct nr complex"<<endl;
}
//setare a+bi j reprezinta puterea lui i = 1 default
void numar_complex::set_nr_complex(int c, int d, int j)
{
	a=c;
	b=d;
	if(j%4==0) {j=1; a+=b; b=0;}
	else if(j%4==1) j=1;
	else if(j%4==2) {j=1; b*=-1; a+=b; b=0; }
	else if(j%4==3) {j=1; b*=-1;}
	i=j;
}
// get-urile nr complex
int numar_complex::get_a_nr_complex()
{
	return a;
}
int numar_complex::get_b_nr_complex()
{
	return b;
}
int numar_complex::get_i_nr_complex()
{
	return i;
}
//supraincarcare operatorilor pentru nr complex
numar_complex numar_complex::operator+(const numar_complex nr2)
{
	numar_complex temp;
	temp.a = a+nr2.a;
	temp.b = b+nr2.b;
	temp.i = 1;
	return temp;
}
numar_complex numar_complex::operator-(const numar_complex nr2)
{
	numar_complex temp;
	temp.a = a-nr2.a;
	temp.b = b-nr2.b;
	temp.i = 1;
	return temp;
}
numar_complex numar_complex::operator*(const numar_complex nr2)
{
	numar_complex temp;
	temp.a = (a*nr2.a)-(nr2.b*b);
	temp.b = (b*nr2.a)+(a*nr2.b);
	temp.i = 1;
	return temp;
}
numar_complex numar_complex::operator/(const numar_complex nr2)
{
	numar_complex temp;
	temp.a = (a*nr2.a + b*nr2.b)/(nr2.a*nr2.a + nr2.b*nr2.b);
	temp.b = (b*nr2.a - a*nr2.b)/(nr2.a*nr2.a + nr2.b*nr2.b);
	temp.i = 1;
	return temp;
}