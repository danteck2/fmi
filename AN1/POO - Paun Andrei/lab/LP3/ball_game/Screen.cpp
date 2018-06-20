#include "Screen.h"

#include "Window.h"

SDL_Surface* Screen::_surface (nullptr);

void Screen::Init ()
{
	Window::Init ();

	std::size_t width = Window::GetWidth ();
	std::size_t height = Window::GetHeight ();

	_surface = SDL_CreateRGBSurface(0, (int)width, (int)height, 32,
		0x00FF0000, 0x0000FF00, 0x000000FF, 0xFF000000);
}

void Screen::Quit ()
{
	SDL_FreeSurface (_surface);	
}

void Screen::Clear ()
{
	SDL_FillRect(_surface, NULL, 0x000000);
}

void Screen::Draw (Image* image, const Vector2& pos)
{
	// Part of the screen we want to draw the sprite to
	SDL_Rect destination;
	destination.x = pos.GetX ();
	destination.y = _surface->h - pos.GetY ();
	destination.w = _surface->w;
	destination.h = _surface->h;

	SDL_BlitSurface(image->GetSurface (), NULL, _surface, &destination);
}

void Screen::Render ()
{
	Window::Render (_surface);
}

std::size_t Screen::GetWidth ()
{
	return _surface->w;
}

std::size_t Screen::GetHeight ()
{
	return _surface->h;
}