#include "Resources.h"

#include "ImageLoader.h"

Image* Resources::LoadImage(const std::string& filename)
{
	ImageLoader* imageLoader = new ImageLoader();

	Image* texture = (Image*) imageLoader->Load(filename);

	delete imageLoader;

	return texture;
}

