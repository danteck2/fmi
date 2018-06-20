/*************************************************************************** 
 Vector2
 ***************************************************************************/

#ifndef VECTOR2_H
#define VECTOR2_H

#include <iostream>
#include <string>

#include "Object.h"

class Vector2 : public Object
{
public:
	static Vector2 Up;
	static Vector2 Down;
	static Vector2 Left;
	static Vector2 Right;
	static Vector2 Back;
	static Vector2 Forward;
	static Vector2 One;
	static Vector2 Zero;
private:
	float _x, _y;

public:
	Vector2 (void);
	Vector2 (const Vector2& other);
	Vector2 (float x, float y);
	
	float GetX () const;
	float GetY () const;
	void SetX (float x);
	void SetY (float y);

	// Much faster that extraction and magnitude
	static float SqrDistance (const Vector2& a, const Vector2& b);
	static float Distance (const Vector2& a, const Vector2& b);

	void Normalize(void);
	
	Vector2 Normalized ();
	float Magnitude (void) const;
	float SqrMagnitude (void) const;
	
	Vector2& operator=(const Vector2& other);
	Vector2& operator-=(const Vector2& other);
	Vector2& operator+=(const Vector2& other);
	Vector2& operator*=(const Vector2& other);
	Vector2& operator*=(float other);
	Vector2& operator+=(float other);
	
	Vector2 operator-(const Vector2& other) const;
	Vector2 operator+(const Vector2& other) const;
	Vector2 operator*(const Vector2& other) const;
	Vector2 operator*(float other) const;
	Vector2 operator+(float other) const;
	
	bool operator==(const Vector2& other);
	bool operator!=(const Vector2& other);	

	friend std::ostream &operator<<(std::ostream &out, Vector2 v);
	friend std::istream &operator>>(std::istream &in, Vector2 &v);

	// From Object
	std::string ToString () const;
};

#endif