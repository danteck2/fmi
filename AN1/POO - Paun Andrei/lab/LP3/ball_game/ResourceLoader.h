#ifndef RESOURCELOADER_H
#define RESOURCELOADER_H

#include <string>

#include "Object.h"

class ResourceLoader
{
public:
	virtual Object* Load(const std::string& fileName) = 0;
};

#endif
