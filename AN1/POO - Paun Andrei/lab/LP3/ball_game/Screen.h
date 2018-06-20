#ifndef SCREEN_H
#define SCREEN_H

#if defined(__linux__) || defined(__APPLE__)
	#include <SDL2/SDL.h>
#elif defined(_WIN32)
	#include <SDL.h>
#endif

#include <cstddef>

#include "Image.h"
#include "Vector2.h"

class Screen
{
private:
	static SDL_Surface* _surface;

public:
	static void Init ();
	static void Quit ();

	static void Draw (Image* image, const Vector2& pos);

	static void Render ();
	static void Clear ();

	static std::size_t GetWidth ();
	static std::size_t GetHeight ();
};

#endif
