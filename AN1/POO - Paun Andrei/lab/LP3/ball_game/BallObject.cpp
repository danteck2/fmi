#include "BallObject.h"

#include "Resources.h"

#include "Screen.h"
#include "Input.h"
#include "GameTime.h"
#include "Vector2.h"

#include <cstdlib>

//#define BALL_SPEED 1000

BallObject::BallObject () :
	//_speed (BALL_SPEED),
	_position (200, 250)
{
	_image = Resources::LoadImage ("Assets/Images/Ball.png");
	R = 32;
	ground = 160;
	dx = 0;
	dy = 0;
	w = 700;
	h = 600;
	// This need to be deleted too
	//_position = Vector2 (rand () % Screen::GetWidth (), rand () % Screen::GetHeight ());
}

BallObject::~BallObject ()
{
	delete _image;
}

void BallObject::Draw ()
{
	Screen::Draw (_image, _position);
}

void BallObject::Update ()
{
	x = _position.GetX();
	y = _position.GetY();

	oldX = x;
	oldY = y;

	dy = dy - 0.9;

	x += int(dx);
	y += int(dy);

	if (y < ground)
	{
		y = ground; dy = -dy*0.9; dx = dx*0.9;
	}
	if (x<R - 40) { x = R - 40; dx = -dx; }
	if (x>w - R + 70) { x = w - R + 70; dx = -dx; }



	if ((abs(w / 2 - x))<30)
		if ((y - R<h / 2) && (oldY>h / 2)) { y = h / 2 + R; dy = -dy; }


	if (y<h / 2)
	{
		if ((oldX<w / 2) && (x + R>w / 2)) { x = w / 2 - R; dx = -dx; }
		if ((oldX>w / 2) && (x - R<w / 2)) { x = w / 2 + R + 20; dx = -dx; }
		//if (x == 200) x = w / 2+R+20;

	}


	if (dy>22) dy = 22;        if (dx>22)  dx = 22;
	if (dy<-22) dy = -22;      if (dx<-22) dx = -22;
	if (x == 200 && y == 250) { dx = dy = 0; }
	if (x == 400 && y == 400) { dx = dy = 0; }

	_position.SetX(x);
	_position.SetY(y);

//	 Uncomment this to switch the controls of the ball to keyboard

	//_destination.SetX(400.0f);
	//_destination.SetY(200.0f);

	//// Vector2 velocity = _destination - _position;
	
	// if (Input::GetKey ('w')) {
	// 	velocity += Vector2::Up;
	// }
	// if (Input::GetKey ('d')) {
	// 	velocity += Vector2::Right;
	// }
	// if (Input::GetKey ('s')) {
	// 	velocity += Vector2::Down;
	// }
	// if (Input::GetKey ('a')) {
	// 	velocity += Vector2::Left;
	// }

	//Vector2 velocity = Vector2::Zero;

	// if (velocity == Vector2::Zero) {
	// 	return;
	// }

	// if (velocity.SqrMagnitude () < 0.1f) {
	// 	return;
	// }

	// velocity.Normalize ();

	// _position += velocity * _speed * GameTime::GetDeltaTime ();
	

	//_destination = Input::GetMousePosition ();
	//_destination.SetY (Screen::GetHeight () - _destination.GetY ());

	//Vector2 velocity = _destination - _position;

	//if (velocity.SqrMagnitude () < 0.1f) {
	//	return;
	//}

	//velocity.Normalize ();

	//_position += velocity * _speed * GameTime::GetDeltaTime ();
}

Vector2 BallObject::GetPosition()
{
	return _position;
}

void BallObject::SetPosition(Vector2 aux_position)
{
	_position = aux_position;
}


float BallObject::Getdx()
{
	return dx;
}

float BallObject::Getdy()
{
	return dy;
}

void BallObject::Setdx(float aux_dx)
{
	dx = aux_dx;
}
void BallObject::Setdy(float aux_dy)
{
	dy = aux_dy;
}
