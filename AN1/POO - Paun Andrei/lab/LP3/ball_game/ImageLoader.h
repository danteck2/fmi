#ifndef IMAGELOADER_H
#define IMAGELOADER_H

#include "ResourceLoader.h"

#include <string>

class ImageLoader : public ResourceLoader
{
public:
	Object* Load(const std::string& filename);
};

#endif