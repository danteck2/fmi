#include "Window.h"

#define WINDOW_WIDTH 800
#define WINDOW_HEIGHT 600

#define X_POSITION 100
#define Y_POSITION 100

#define WINDOW_FLAGS SDL_WINDOW_SHOWN

SDL_Window* Window::_window (nullptr);
SDL_Renderer* Window::_renderer (nullptr);
SDL_Texture* Window::_texture (nullptr);

void Window::Init ()
{
	_window = SDL_CreateWindow("VolleyBall",
		X_POSITION, Y_POSITION, WINDOW_WIDTH, WINDOW_HEIGHT, WINDOW_FLAGS);

	_renderer = SDL_CreateRenderer(_window, -1, 0);

	_texture = SDL_CreateTexture(_renderer, SDL_PIXELFORMAT_ARGB8888,
		SDL_TEXTUREACCESS_STREAMING, WINDOW_WIDTH, WINDOW_HEIGHT);
}

void Window::Quit ()
{
	SDL_DestroyTexture (_texture);
	SDL_DestroyRenderer (_renderer);
    SDL_DestroyWindow(_window);
}

void Window::Clear ()
{
	SDL_RenderClear(_renderer);
}

void Window::Render (SDL_Surface* surface)
{
	SDL_UpdateTexture(_texture, NULL, surface->pixels, surface->pitch);
	SDL_RenderCopy(_renderer, _texture, NULL, NULL);
	SDL_RenderPresent(_renderer);
}

std::size_t Window::GetWidth ()
{
	int width, height;

	SDL_GetWindowSize (_window, &width, &height);

	return width;
}

std::size_t Window::GetHeight ()
{
	int width, height;

	SDL_GetWindowSize (_window, &width, &height);

	return height;
}
