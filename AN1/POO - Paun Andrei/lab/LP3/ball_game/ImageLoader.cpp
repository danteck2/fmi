#include "ImageLoader.h"

#ifdef _WIN32
	#include <SDL_image.h>
#elif defined(__linux__) || defined(__APPLE__)
	#include <SDL2/SDL_image.h>
#endif

#include <string>
#include <iostream>

#include "Image.h"

Object* ImageLoader::Load(const std::string& filename)
{
	SDL_Surface* img=IMG_Load(filename.c_str());

	if (!img)
	{
		std::cout << "Image \"" << filename << "\" failed to load. Program closed!";
		//exit(0);
	}

	Image* image = new Image(img, filename);

	return image;
}
