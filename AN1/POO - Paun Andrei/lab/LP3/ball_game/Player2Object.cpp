#include "Player2Object.h"

#include "Resources.h"

#include "Screen.h"
#include "Input.h"
#include "GameTime.h"
#include "Vector2.h"

#include <cstdlib>


Player2Object::Player2Object() :
	_position (650, 160)
{

	_image = Resources::LoadImage ("Assets/Images/Smiley2.png");
	R = 43;
	ground = 160;
	dx = 0;
	dy = 0;
	w = 700;
	// This need to be deleted too
	//_position = Vector2 (rand () % Screen::GetWidth (), rand () % Screen::GetHeight ());
}

Player2Object::~Player2Object()
{
	delete _image;
}

void Player2Object::Draw ()
{
	Screen::Draw (_image, _position);
}

void Player2Object::Update ()
{
	x = _position.GetX();
	y = _position.GetY();
	dy = dy - 0.9;
	y += int(dy);
	if (y<ground) { y = ground; dy = 0; }
	if ((Input::GetKey('i'))) Up = true;
	if (Up && y == ground) dy += 20;  Up = false;
	if ((Input::GetKey('l'))) dx = 10;
	if ((Input::GetKey('j'))) dx = -10;
	if (!((Input::GetKey('j')) || (Input::GetKey('l')))) dx = 0;
	x += dx;
	
	if (x + R > w+53) x = w - R + 53;
	if (x - R < (w / 2)+10) x = w / 2 + R + 10;

	_position.SetX(x);
	_position.SetY(y);

//	 Uncomment this to switch the controls of the ball to keyboard

	 //Vector2 velocity = Vector2::Zero;

	 //if (Input::GetKey ('i')) {
	 //	velocity += Vector2::Up;
	 //}
	 //if (Input::GetKey ('l')) {
	 //	velocity += Vector2::Right;
	 //}
	 //if (Input::GetKey ('k')) {
	 //	velocity += Vector2::Down;
	 //}
	 //if (Input::GetKey ('j')) {
	 //	velocity += Vector2::Left;
	 //}

	 //if (velocity == Vector2::Zero) {
	 //	return;
	 //}

	 //velocity.Normalize ();

	 //_position += velocity * _speed * GameTime::GetDeltaTime ();

	//_destination = Input::GetMousePosition ();
	//_destination.SetY (Screen::GetHeight () - _destination.GetY ());

	//Vector2 velocity = _destination - _position;

	//if (velocity.SqrMagnitude () < 0.1f) {
	//	return;
	//}

	//velocity.Normalize ();

	//_position += velocity * _speed * GameTime::GetDeltaTime ();
}

Vector2 Player2Object::GetPosition()
{
	return _position;
}

void Player2Object::SetPosition(Vector2 aux_position)
{
	_position = aux_position;
}


float Player2Object::Getdx()
{
	return dx;
}

float Player2Object::Getdy()
{
	return dy;
}

void Player2Object::Setdx(float aux_dx)
{
	dx = aux_dx;
}
void Player2Object::Setdy(float aux_dy)
{
	dy = aux_dy;
}