
#include <iostream>
#include <algorithm>
#include <stdio.h>
// Original algorithm http://stackoverflow.com/questions/563198/how-do-you-detect-where-two-line-segments-intersect

struct vector2D
{
	double x = 0;
	double y = 0;


	vector2D()
	{
		x = 0;
		y = 0;
	}

	vector2D(int X, int Y)
	{
		x = X;
		y = Y;

	}

	vector2D & operator=(const vector2D &other)
	{
		x = other.x;
		y = other.y;

		return *this;
	}

	vector2D operator+(const vector2D &other)
	{
		vector2D result = *this;

		result.x = x + other.x;
		result.y = y + other.y;

		return result;
	}

	vector2D  operator-(const vector2D &other)
	{
		vector2D result = *this;

		result.x = x - other.x;
		result.y = y - other.y;

		return result;
	}

	vector2D  operator*(const double &scalar) // vector * scalar;
	{
		vector2D result = *this;

		result.x = x * scalar;
		result.y = y * scalar;

		return result;
	}

	double  operator*(const vector2D &other) // vector * scalar;
	{
		double result = 0;

		result = x* other.x + y*other.y;

		return result;
	}

	bool  operator==(const vector2D &other)
	{
		if (x == other.x && y == other.y)
			return true;
		else
			return false;
	}
};

double cross(vector2D a, vector2D b)
{
	double result = a.x * b.y - a.y * b.x;
	return result;
}

bool LessThan(vector2D A, vector2D B)
{
	if (A.x < B.x)
		return true;
	else
		if (A.x == B.y && A.y < B.y)
			return true;
		else return false;
}

void intersect(vector2D A, vector2D B, vector2D C, vector2D D)
{
	vector2D p = A;
	vector2D q = C;
	vector2D r = B - A;
	vector2D s = D - C;

	//if (abs(cross(r,s)) <= 0.01 )
	if (cross(r, s) == 0)
	{
		if (cross(q - p, r) == 0)
		{
			//coliniare

			//std::cout << "coliniare" << std::endl;

			double t0 = (q - p)*r / (r*r);
			double t1 = (q + s - p)*r / (r*r); // t1 = t0+ s*r / (r * r)

			if (0 <= t0 && t0 <= 1 || 0 <= t1 && t1 <= 1)
			{

				//std::cout << "intersectie";

				vector2D Puncte[3];
				Puncte[0] = A;
				Puncte[1] = B;
				Puncte[2] = C;
				Puncte[3] = D;

				std::sort(Puncte, Puncte + 4, LessThan);

				if (Puncte[1] == Puncte[2])
				{
					printf("Se intersecteaza in punctul %f %f", Puncte[1].x, Puncte[1].y);
				}
				else
					if (Puncte[0] == Puncte[1] && Puncte[2] == Puncte[3])
					{
						printf("Segmentele sunt identice");
					}
					else
					{
						printf("Se intersecteaza pe segmentul format din %f %f si %f %f", Puncte[1].x, Puncte[1].y, Puncte[2].x, Puncte[2].y);
					}

			}
			else
			{
				std::cout << "Segmentele nu se intersecteaza";
			}
		}
		else
		{
			//paralele nu se intersecteaza

			std::cout << "Paralele, segmentele nu se interseacteaza" << std::endl;
		}
	}

	else
	{
		double t = 0, u = 0;
		t = cross(q - p, s) / cross(r, s);
		u = cross(q - p, r) / cross(r, s);

		if (0 <= t && t <= 1 && 0 <= u && u <= 1)
		{
			// se intersecteaza
			vector2D intersectie;

			intersectie = p + r*t;

			printf("Segmentele se intersecteaza in %f %f", intersectie.x, intersectie.y);
		}

		else
		{
			// nu se intersecteaza

			std::cout << "Neparalele, segmentele nu se interseacteaza" << std::endl;
		}
	}
}



int main()

{
	//
	int z;
	float x,y;
	std::cout<<"A: ";  std::cin>>x>>y;
	vector2D A(x,y);
	std::cout<<"B: "; std::cin>>x>>y;
	vector2D B(x, y);
	std::cout<<"C: "; std::cin>>x>>y;
	vector2D C(x, y);
	std::cout<<"D: "; std::cin>>x>>y;
	vector2D D(x, y);


	intersect(A, B, C, D);

	//std::cin >> z;


	return 0;
}

