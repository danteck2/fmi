#include <iostream>
#include <fstream>

using namespace std;

ifstream fin1("matrice.txt");
ifstream fin2("matrice2.txt");
ifstream fin3("matrice3.txt");
////////////<<<MATRICE>>>//////////
matrice::matrice(int x=1, int y=1)//int x, int y
{
	n=x;
	m=y;
	matrix=new numar_complex*[n];
	for(int i=0; i<n; i++)
		*(matrix+i)=new numar_complex[m];
	//cout<<"construct matrice"<<endl;
}
matrice::matrice(const matrice &m2)
{
	n=m2.n;
	m=m2.m;
	matrix=new numar_complex*[n];
	for(int i=0; i<n; i++)
		*(matrix+i)=new numar_complex[m];
	//cout<<"construct matrice"<<endl;

	for(int i=0; i<n; i++)
		for(int j=0; j<m; j++)
			matrix[i][j]=m2.matrix[i][j];
}
//destructori
matrice::~matrice()
{
	for(int i=n-1; i>=0; i--)
		delete [] matrix[i];
	delete [] matrix;

	//cout<<"destruct matrice"<<endl;
}

matrice matrice::operator=(const matrice m2)
{
	n=m2.n;
	m=m2.m;
	matrix=new numar_complex*[n];
	for(int i=0; i<n; i++)
		*(matrix+i)=new numar_complex[m];
	//cout<<"construct matrice"<<endl;

	for(int i=0; i<n; i++)
		for(int j=0; j<m; j++)
			matrix[i][j]=m2.matrix[i][j];
	return *this;
}
matrice matrice::operator+(const matrice m2)
{
	if(n==m2.n && m==m2.m)
	{
		matrice temp;
		temp.n=n;
		temp.m=m;
		temp.matrix=new numar_complex*[temp.n];
		for(int i=0; i<temp.n; i++)
			*(temp.matrix+i)=new numar_complex[temp.m];
		//cout<<"construct matrice"<<endl;

		for(int i=0; i<n; i++)
			for(int j=0; j<m; j++)
				temp.matrix[i][j]=matrix[i][j]+m2.matrix[i][j];
		return temp;
	}
	else 
	{
		cout<<"Suma a doua matrici cu dimensiuni diferite nu poate fi efectuata!"<<endl;
		return *this;
	}
}
matrice matrice::operator*(const matrice m2)
{
	matrice temp;
	temp.n=n;
	cout<<"temp.n="<<temp.n<<endl;
	temp.m=m2.m;
	cout<<"temp.m="<<temp.m<<endl;
	numar_complex aux;
	temp.matrix=new numar_complex*[temp.n];
	for(int i=0; i<temp.n; i++)
		*(temp.matrix+i)=new numar_complex[temp.m];
	if(temp.n != temp.m) 
	{
		cout<<"Produsul nu poate fi calculat!"<<endl;
		cout<<"Temp return NULL"<<endl;
		temp.show_matrice();
		return temp;
	}

	for(int i=0; i<temp.n; i++)
		for(int j=0; j<temp.m; j++)
		{

			for(int k=0; k<m; k++)
			{	
				//cout<<endl<<"i j k "<<i<<' '<<j<<' '<<k<<endl;
				//cout<<endl<<"matrix["<<i<<"]["<<k<<"]= "<<matrix[i][k].get_a_nr_complex()<<' '<<matrix[i][k].get_b_nr_complex()<<' '<<matrix[i][k].get_i_nr_complex()<<endl;
				//cout<<"m2.matrix["<<k<<"]["<<j<<"]= "<<m2.matrix[k][j].get_a_nr_complex()<<' '<<m2.matrix[k][j].get_b_nr_complex()<<' '<<m2.matrix[k][j].get_i_nr_complex()<<endl;
				//cout<<endl<<"aux=matrix["<<i<<"]["<<k<<"]= "<<aux.get_a_nr_complex()<<' '<<aux.get_b_nr_complex()<<' '<<aux.get_i_nr_complex()<<endl;
				aux=matrix[i][k]*m2.matrix[k][j];
				//cout<<"aux=aux*m2.matrix["<<k<<"]["<<j<<"]= "<<aux.get_a_nr_complex()<<' '<<aux.get_b_nr_complex()<<' '<<aux.get_i_nr_complex()<<endl;
				temp.matrix[i][j]=temp.matrix[i][j]+aux;
				//cout<<"temp.matrix["<<i<<"]["<<j<<"]+aux= "<<temp.matrix[i][j].get_a_nr_complex()<<' '<<temp.matrix[i][j].get_b_nr_complex()<<' '<<temp.matrix[i][j].get_i_nr_complex()<<endl;
			}
			//cout<<endl<<">>>>>>>>>>>>>>>>FINALtemp.matrix["<<i<<"]["<<j<<"]= "<<temp.matrix[i][j].get_a_nr_complex()<<' '<<temp.matrix[i][j].get_b_nr_complex()<<' '<<temp.matrix[i][j].get_i_nr_complex()<<endl;
		}
	return temp;
}

numar_complex matrice::determinant()
{	
	if(n==m)
	{
		matrice copy;
		copy=*this;
		//cout<<"~~~~~~~~~~~~~~~~~~!!!!!!~~~~~~~~~~~~~"<<copy.n<<' '<<copy.m<<endl;
		numar_complex z, negative_one, aux, det;
		if(n==2) 
		{
			det=(copy.matrix[0][0]*copy.matrix[1][1])-(copy.matrix[0][1]*copy.matrix[1][0]);
			return det;
		}
		negative_one.set_nr_complex(-1, 0, 1);
		for(int col = 0; col<copy.n; ++col)
		{
			cout << "Coloana: "<<col<<endl;
			bool found = false;
			for(int row = col; row < copy.n; ++row) 
			{
				if(copy.matrix[row][col].get_a_nr_complex()>0 && copy.matrix[row][col].get_a_nr_complex()>0) 
				{
					cout<<"Zero val rand: "<<row<<" si coloana "<<col<<endl;
					if(row!=col)
					{
						cout<<"Interschim "<<col<<" si "<<row<<endl;
						for(int i=0; i<copy.n; i++)
						{
							aux=copy.matrix[row][i];
							copy.matrix[row][i]=copy.matrix[col][i];
							copy.matrix[col][i]=aux;
						}
					}
					else
					{
						cout << "Nu sunt linii pt interschimbare"<<endl;
					}
					found = true;
					break;
				}
			}

			if(!found) 
			{
				return z;
			}

			for(int row = col + 1; row < copy.n; ++row) 
			{
				while(true) 
				{
					numar_complex del;
					del=copy.matrix[row][col]/copy.matrix[col][col];
					//cout<<"del: "<<del.get_a_nr_complex()<<' '<<del.get_b_nr_complex()<<' '<<del.get_i_nr_complex()<<endl;
					for (int j = col; j < copy.n; ++j) 
					{
						copy.matrix[row][j] = copy.matrix[row][j] - (del * copy.matrix[col][j]);
					}
					if (copy.matrix[row][col].get_a_nr_complex()==0 && copy.matrix[row][col].get_b_nr_complex()==0)
					{
						break;
					}
					else
					{
						cout<<"Interschim "<<col<<" si "<<row<<endl;
						for(int i=0; i<copy.n; i++)
						{
							aux=copy.matrix[row][i];
							copy.matrix[row][i]=copy.matrix[col][i];
							copy.matrix[col][i]=aux;
						}
					}
				}
			}
		}
		numar_complex res;
		res.set_nr_complex(1, 0, 1);
		for(int i = 0; i < copy.n; ++i) 
		{
			res=res*copy.matrix[i][i];
		}
		if(res.get_a_nr_complex()<0)
			res=res*negative_one;
		return res;
	}
	else cout<<"Determinatul poate fi calculat doar pentru matrice patratica!"<<endl;
}

void matrice::inversa()
{	
	numar_complex detif;
	detif=(*this).determinant();
	if(detif.get_a_nr_complex()!=0 || detif.get_b_nr_complex()!=0)
	{
		matrice m2, minor; //alocam m2 de dimensiune this, m2 va retine rezultatul final
		m2.n=n;
		m2.m=m;
		m2.matrix=new numar_complex*[m2.n];
		for(int i=0; i<m2.n; i++)
			m2.matrix[i]=new numar_complex[m2.m];

		numar_complex det, one, negative_one, produs;
		one.set_nr_complex(1, 0, 1);
		negative_one.set_nr_complex(-1, -1, 1);

		// 1/det
		det=one/detif;
 
		// alocare memorie
		minor.n=n-1;
		minor.m=m-1;
		numar_complex *temp = new numar_complex[(m2.n-1)*(m2.n-1)];
		minor.matrix = new numar_complex*[m2.n-1];
		for(int i=0; i<m2.n-1; i++)
			minor.matrix[i]=temp+(i*(m2.n-1));
 
		for(int j=0; j<n; j++)
		{
			for(int i=0; i<n; i++)
			{
				// gasire minoranti
				int colCount=0, rowCount=0;
 
				for(int a=0; a<n; a++)
				{
					if(a!=j)
					{
						colCount=0;
						for(int b=0; b<n; b++)
						{
							if(b!=i)
							{
								minor.matrix[rowCount][colCount] = matrix[a][b];
								colCount++;
							}
						}
						rowCount++;
					}
				}
				//cout<<"minor= "<<endl;
				//minor.show_matrice();
				produs=minor.determinant();/////
				//cout<<"produs= "<<produs.get_a_nr_complex()<<' '<<produs.get_b_nr_complex()<<' '<<produs.get_i_nr_complex()<<' '<<endl;
				//cout<<"det= "<<det.get_a_nr_complex()<<' '<<det.get_b_nr_complex()<<' '<<det.get_i_nr_complex()<<' '<<endl;
				//cout<<"i j m2.n m2.m "<<i<<' '<<j<<' '<<m2.n<<' '<<m2.m<<endl;
				//cout<<"m2= "<<endl;
				//m2.show_matrice();
				produs=produs*det;//////////
				//cout<<"produs= "<<produs.get_a_nr_complex()<<' '<<produs.get_b_nr_complex()<<' '<<produs.get_i_nr_complex()<<' '<<endl;
				m2.matrix[i][j] = produs;
				if( (i+j)%2 == 1)
					m2.matrix[i][j]=negative_one*m2.matrix[i][j];
			}
		}
		cout<<"Inversa matricii este: "<<endl;
		m2.show_matrice();
	}
	else cout<<"Matricea nu este inversabila! "<<endl; 

}
//setare matrice
void matrice::set_matrice()
{
	int a, b, c;
	cout<<endl<<"Introduceti n=";
	cin>>n;
	cout<<"Introduceti m=";
	cin>>m;
	//matrice(n); aloc matrice dinamic de n dimensiuni
	matrix=new numar_complex*[n];
	for(int i=0; i<n; i++)
		*(matrix+i)=new numar_complex[m];
	cout<<"construct matrice"<<endl;
	
	for(int i=0; i<n; i++)
	{
		for(int j=0; j<m; j++)
		{
			cout<<"Introduceti nr de pe linia "<<i+1<<" si coloana "<<j+1<<" (de forma a b i)"<<endl;
			cin>>a>>b>>c;
			matrix[i][j].set_nr_complex(a,b,c);
		}
	}
}
//istream& operator>>( istream& input, matrice& m)
//{
//	return input;
//}
void matrice::set_matrice_txt1()
{
	int a, b, c;
	cout<<"Citim pe n=";
	fin1>>n;
	cout<<n<<endl;
	cout<<"Citim pe m=";
	fin1>>m;
	cout<<m<<endl;
	//matrice(n); aloc matrice dinamic de n dimensiuni
	matrix=new numar_complex*[n];
	for(int i=0; i<n; i++)
		*(matrix+i)=new numar_complex[m];
	cout<<"construct matrice"<<endl;
	
	for(int i=0; i<n; i++)
	{
		for(int j=0; j<m; j++)
		{
			cout<<"Citesc nr de pe linia "<<i+1<<" si coloana "<<j+1<<" (de forma a b i): ";
			fin1>>a>>b>>c;
			cout<<a<<' '<<b<<' '<<c<<endl;
			matrix[i][j].set_nr_complex(a,b,c);
		}
	}
	cout<<endl;
	fin1.close();
}
void matrice::set_matrice_txt2()
{
	int a, b, c;
	cout<<"Citim pe n=";
	fin2>>n;
	cout<<n<<endl;
	cout<<"Citim pe m=";
	fin2>>m;
	cout<<m<<endl;
	//matrice(n); aloc matrice dinamic de n dimensiuni
	matrix=new numar_complex*[n];
	for(int i=0; i<n; i++)
		*(matrix+i)=new numar_complex[m];
	cout<<"construct matrice"<<endl;
	
	for(int i=0; i<n; i++)
	{
		for(int j=0; j<m; j++)
		{
			cout<<"Citesc nr de pe linia "<<i+1<<" si coloana "<<j+1<<" (de forma a b i): ";
			fin2>>a>>b>>c;
			cout<<a<<' '<<b<<' '<<c<<endl;
			matrix[i][j].set_nr_complex(a,b,c);
		}
	}
	cout<<endl;
	fin2.close();
}
void matrice::set_matrice_txt3()
{
	int a, b, c;
	cout<<"Citim pe n=";
	fin3>>n;
	cout<<n<<endl;
	cout<<"Citim pe m=";
	fin3>>m;
	cout<<m<<endl;
	//matrice(n); aloc matrice dinamic de n dimensiuni
	matrix=new numar_complex*[n];
	for(int i=0; i<n; i++)
		*(matrix+i)=new numar_complex[m];
	cout<<"construct matrice"<<endl;
	
	for(int i=0; i<n; i++)
	{
		for(int j=0; j<m; j++)
		{
			cout<<"Citesc nr de pe linia "<<i+1<<" si coloana "<<j+1<<" (de forma a b i): ";
			fin3>>a>>b>>c;
			cout<<a<<' '<<b<<' '<<c<<endl;
			matrix[i][j].set_nr_complex(a,b,c);
		}
	}
	cout<<endl;
	fin3.close();
}
void matrice::show_matrice()
{
	cout<<"|";
	for(int sp=1; sp<m*8; sp++) 
	{
		int q=0;
		if((sp+q)%8==0) {cout<<"|"; q++;}
		else cout<<"-";
	}
	cout<<"|"; 
	cout<<endl;//linia 1
	for(int i=0; i<n; i++)
	{
		cout<<"|";
		for(int sp=1; sp<m*8; sp++) 
		{
			int q=0;
			if((sp+q)%8==0) {cout<<"|"; q++;}
			else cout<<" ";
		}
		cout<<"|";
		cout<<endl;//linia2

		cout<<"|";
		for(int j=0; j<m; j++)
			cout<<' '<<matrix[i][j].get_a_nr_complex()<<' '<<matrix[i][j].get_b_nr_complex()<<' '<<matrix[i][j].get_i_nr_complex()<<" |";
		cout<<endl;

		cout<<"|";
		for(int sp=1; sp<m*8; sp++) 
		{
			int q=0;
			if((sp+q)%8==0) {cout<<"|"; q++;}
			else cout<<" ";
		}
		cout<<"|"; //linia 3

		cout<<endl;
		cout<<"|";
		for(int sp=1; sp<m*8; sp++) 
		{
			int q=0;
			if((sp+q)%8==0) {cout<<"|"; q++;}
			else cout<<"-";
		}
		cout<<"|";
		cout<<endl;
	}
}
//ostream& operator<<( ostream& output, matrice& m)
//{
//	
//	output<<"|";
//	for(int sp=1; sp<m.m*8; sp++) 
//	{
//		int q=0;
//		if((sp+q)%8==0) {output<<"|"; q++;}
//		else output<<"-";
//	}
//	output<<"|"; 
//	output<<endl;//linia 1
//	for(int i=0; i<m.n; i++)
//	{
//		output<<"|";
//		for(int sp=1; sp<m.m*8; sp++) 
//		{
//			int q=0;
//			if((sp+q)%8==0) {output<<"|"; q++;}
//			else output<<" ";
//		}
//		output<<"|";
//		output<<endl;//linia2
//
//		output<<"|";
//		for(int j=0; j<m.m; j++)
//			output<<' '<<m.matrix[i][j].get_a_nr_complex()<<' '<<m.matrix[i][j].get_b_nr_complex()<<' '<<m.matrix[i][j].get_i_nr_complex()<<" |";
//		output<<endl;
//
//		output<<"|";
//		for(int sp=1; sp<m.m*8; sp++) 
//		{
//			int q=0;
//			if((sp+q)%8==0) {output<<"|"; q++;}
//			else output<<" ";
//		}
//		output<<"|"; //linia 3
//
//		output<<endl;
//		output<<"|";
//		for(int sp=1; sp<m.m*8; sp++) 
//		{
//			int q=0;
//			if((sp+q)%8==0) {output<<"|"; q++;}
//			else output<<"-";
//		}
//		output<<"|";
//		output<<endl;
//	}
//	return output;
//}
