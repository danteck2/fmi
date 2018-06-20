#ifndef WINDOW_H
#define WINDOW_H

#if defined(__linux__ )|| defined(__APPLE__)
	#include <SDL2/SDL.h>
#elif defined(_WIN32)
	#include <SDL.h>
#endif

#include <cstddef>

class Window
{
private:
	static SDL_Window* _window;
	static SDL_Renderer* _renderer;
	static SDL_Texture* _texture;

public:
	static void Init ();
	static void Quit ();

	// TODO: Kill it with fire
	static void Render (SDL_Surface* surface);
	static void Clear ();

	static std::size_t GetWidth ();
	static std::size_t GetHeight ();
};

#endif
