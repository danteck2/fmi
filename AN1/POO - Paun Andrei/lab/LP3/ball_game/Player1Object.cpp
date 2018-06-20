#include "Player1Object.h"

#include "Resources.h"

#include "Screen.h"
#include "Input.h"
#include "GameTime.h"
#include "Vector2.h"

#include <cstdlib>


Player1Object::Player1Object() :
	_position (60, 160)
{
	
	_image = Resources::LoadImage ("Assets/Images/Smiley1.png");
	R = 43;
	ground = 160;
	dx = 0;
	dy = 0;
	w = 700;
	// This need to be deleted too
	//_position = Vector2 (rand () % Screen::GetWidth (), rand () % Screen::GetHeight ());
}

Player1Object::~Player1Object()
{
	delete _image;
}

void Player1Object::Draw ()
{
	Screen::Draw (_image, _position);
}

void Player1Object::Update ()
{
	x = _position.GetX();
	y = _position.GetY();
	dy = dy - 0.9;
	y += int(dy);
	if (y<ground) { y = ground; dy = 0; }
	if ((Input::GetKey('w'))) Up = true;
	if (Up && y == ground) dy += 20;  Up = false;
	if ((Input::GetKey('d'))) dx = 10;
	if ((Input::GetKey('a'))) dx = -10;
	if (!((Input::GetKey('a')) || (Input::GetKey('d')))) dx = 0;
	x += dx;
	if (x - R < -50) x = 0;
	if (x + R> w / 2) x = w / 2 - R;

	_position.SetX(x);
	_position.SetY(y);

//	 Uncomment this to switch the controls of the ball to keyboard
	 
	 //Vector2 velocity = Vector2::Zero;

	 //if (Input::GetKey ('w')) {
	 //	velocity += Vector2::Up;
	 //}
	 //if (Input::GetKey ('d')) {
	 //	velocity += Vector2::Right;
	 //}
	 //if (Input::GetKey ('s')) {
	 //	velocity += Vector2::Down;
	 //}
	 //if (Input::GetKey ('a')) {
	 //	velocity += Vector2::Left;
	 //}

	 //if (velocity == Vector2::Zero) {
	 //	return;
	 //}

	 //velocity.Normalize ();

	 //_position += velocity * 100 * GameTime::GetDeltaTime ();

	//_destination = Input::GetMousePosition ();
	//_destination.SetY (Screen::GetHeight () - _destination.GetY ());

	//Vector2 velocity = _destination - _position;

	//if (velocity.SqrMagnitude () < 0.1f) {
	//	return;
	//}

	//velocity.Normalize ();

	//_position += velocity * _speed * GameTime::GetDeltaTime ();
}

Vector2 Player1Object::GetPosition()
{
	return _position;
}

void Player1Object::SetPosition(Vector2 aux_position)
{
	_position = aux_position;
}


float Player1Object::Getdx()
{
	return dx;
}

float Player1Object::Getdy()
{
	return dy;
}

void Player1Object::Setdx(float aux_dx)
{
	dx = aux_dx;
}
void Player1Object::Setdy(float aux_dy)
{
	dy = aux_dy;
}