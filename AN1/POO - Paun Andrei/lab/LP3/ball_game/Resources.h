#ifndef RESOURCES_H
#define RESOURCES_H

#include <string>

#include "Image.h"

class Resources
{
public:
	static Image* LoadImage (const std::string& filename);
};

#endif