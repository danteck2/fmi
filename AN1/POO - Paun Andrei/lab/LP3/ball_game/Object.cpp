#include "Object.h"

#include <string>

Object::~Object()
{

}

std::string Object::ToString() const
{
	return "";
}

bool Object::Equal(Object* other)
{
	// compare pointers
	return this == other;
}
