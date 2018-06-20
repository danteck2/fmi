#include <iostream>
#include <cmath>
#include <sstream>

#include "Vector2.h"

Vector2::Vector2(void) :
	_x(0.0f),
	_y(0.0f)
{
	// Nothing
}

Vector2::Vector2(float newX, float newY) :
	_x(newX),
	_y(newY)
{
	// Nothing
}

Vector2::Vector2(const Vector2& other) :
	_x (other._x),
	_y (other._y)
{

}

// Constant Vectors that I use more often
Vector2 Vector2::Up(0.0, 1.0);
Vector2 Vector2::Down(0.0, -1.0);
Vector2 Vector2::Left(-1.0, 0.0);
Vector2 Vector2::Right(1.0, 0.0);
Vector2 Vector2::Back(0.0, 0.0);
Vector2 Vector2::Forward(0.0, 0.0);
Vector2 Vector2::One(1.0, 1.0);
Vector2 Vector2::Zero(0.0, 0.0);

float Vector2::GetX () const
{
	return _x;
}

float Vector2::GetY () const
{
	return _y;
}

void Vector2::SetX (float x)
{
	_x = x;
}

void Vector2::SetY (float y)
{
	_y = y;
}

float Vector2::SqrDistance (const Vector2& a, const Vector2& b)
{
	return (a._x - b._x) * (a._x - b._x) +
		(a._y - b._y) * (a._y - b._y);
}

float Vector2::Distance (const Vector2& a, const Vector2& b)
{
	return sqrt (SqrDistance (a, b));
}

void Vector2::Normalize(void) {
	float length = sqrt (_x * _x + _y * _y);

	_x /= length;
	_y /= length;
}

Vector2 Vector2::Normalized (void) {
	Vector2 result;

	float length = sqrt (_x * _x + _y * _y);

	result._x = _x / length;
	result._y = _y / length;

	return result;
}

float Vector2::Magnitude (void) const {
	return sqrt (SqrMagnitude ());
}

float Vector2::SqrMagnitude (void) const {
	return _x * _x + _y * _y;
}

Vector2 & Vector2::operator=(const Vector2& other) {

	this->_x = other._x;
	this->_y = other._y;

	return *this;
}

Vector2 & Vector2::operator+=(const Vector2 &other) {

	this->_x += other._x;
	this->_y += other._y;

	return *this;
}

Vector2 Vector2::operator+(const Vector2 &other) const {
	Vector2 result = *this;
	result += other;
	return result;
}

Vector2 & Vector2::operator-=(const Vector2 &other) {

	this->_x -= other._x;
	this->_y -= other._y;

	return *this;
}

Vector2 Vector2::operator-(const Vector2 &other) const {
    Vector2 result = *this;
    result -= other;
    return result;
}

Vector2 & Vector2::operator*=(const Vector2 &other) {

	_x *= other._x;
	_y *= other._y;

	return *this;
}

Vector2 Vector2::operator*(const Vector2 &other) const {
    Vector2 result = *this;
    result *= other;
    return result;
}

Vector2 & Vector2::operator*=(float other) {

	_x *= other;
	_y *= other;

	return *this;
}

Vector2 Vector2::operator*(float other) const {
	Vector2 result = *this;
	result *= other;;
	return result;
}

Vector2& Vector2::operator+=(float other)
{
	_x += other;
	_y += other;

	return *this;
}

Vector2 Vector2::operator+(float other) const {
	Vector2 result = *this;
	result *= other;
	return result;
}

bool Vector2::operator==(const Vector2& other) {
	return _x == other._x && _y == other._y;
}

bool Vector2::operator!=(const Vector2& other) {
	return !(*this == other);
}

std::ostream &operator<<(std::ostream &out,const Vector2& v) {
	out << v.ToString ();	
    return out;
}

std::istream &operator>>(std::istream &in, Vector2 &v) {
	in >> v._x >> v._y;
    return in;
}

std::string Vector2::ToString () const
{
	std::ostringstream ss;

	ss << "(" << _x << "," << _y << ")";

	return ss.str() ;
}