#include<stdio.h>
#include<math.h>
typedef struct{
float a;
float b;
}complex;

void citire(complex* z,char s[])
{
	printf("Introducem nr. complex %s:\n",s);
	printf("\tpartea reala: ");
	scanf("%f",&(*z).a);
	printf("\tpartea imaginara: ");
	scanf("%f",&z->b); //alt mod de scriere
}
//afisarea unui nr. complex
void afisare(complex z,char s[])
{
	printf("Nr. complex %s: ",s);
	if(z.b>=0)
	printf("%.2f+%.2fi\n",z.a,z.b);
	else
	printf("%.2f%.2fi\n",z.a,z.b);
}

//suma a doua nr. complexe (varianta cu transfer prin tipul rezultat)
complex suma1(complex x,complex y)
{
	complex z;
	z.a=x.a+y.a;
	z.b=x.b+y.b;
return z;
}
//suma a doua nr. complexe (varianta cu transfer prin linia de parametri)
void suma2(complex x,complex y,complex* z)
{
	z->a=x.a+y.a; //(*z).a=x.a+y.a;
	z->b=x.b+y.b;
}
//produsul a doua nr. complexe
complex produs(complex x,complex y)
{
	complex z;
	z.a=x.a*y.a-x.b*y.b;
	z.b=x.a*y.b+x.b*y.a;
	return z;
}
//functia principala in rulare
void main()
{
	complex z1,z2;
	//citirea celor doua nr. complexe
	citire(&z1,"z1");
	citire(&z2,"z2");
	printf("\n");
	//afisarea celor doua nr. complexe
	afisare(z1,"z1");
	afisare(z2,"z2");
	printf("\n");
	//modulele celor doua nr. complexe
	printf("Modulul lui z1: %.2f\n",modul(z1));
	printf("Modulul lui z2: %.2f\n",modul(z2));
	printf("\n");
	afisare(conjugat(z1),"conjugat z1");
	afisare(conjugat(z2),"conjugat z2");
	complex s1;
	s1=suma1(z1,z2);
	afisare(s1,"z1+z2");
	complex s2;
	suma2(z1,z2,&s2);
	afisare(s2,"z1+z2");
	complex p;
	p=produs(z1,z2);
	afisare(p,"z1*z2");

	getchar();
	int k;
	scanf("%d", &k);
}
