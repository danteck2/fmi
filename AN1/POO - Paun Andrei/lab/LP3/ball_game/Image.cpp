#include "Image.h"

Image::Image (SDL_Surface* surface, const std::string& name) :
	_surface (surface),
	_name (name)
{

}

Image::~Image ()
{
	SDL_FreeSurface (_surface);
}

SDL_Surface* Image::GetSurface () const
{
	return _surface;
}

std::size_t Image::GetHeight () const
{
	return _surface->h;
}

std::size_t Image::GetWidth () const
{
	return _surface->w;
}