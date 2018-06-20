#include "Background.h"

#include "Resources.h"

#include "Screen.h"
#include "GameTime.h"
#include "Vector2.h"

#include <cstdlib>

Background::Background()
{
	_image = Resources::LoadImage("Assets/Images/strand1.bmp");
	_position = Vector2(0, Screen::GetHeight());
}

Background::~Background()
{
	delete _image;
}

void Background::Draw()
{
	Screen::Draw(_image, _position);
}
