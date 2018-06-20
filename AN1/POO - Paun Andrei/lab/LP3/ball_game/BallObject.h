#ifndef BALLOBJECT_H
#define BALLOBJECT_H

#include "Image.h"

#include "SceneObject.h"
#include "Vector2.h"

class BallObject : public SceneObject
{
private:
	Image* _image;
	float _speed;
	Vector2 _position;
	Vector2 _destination;
	int x, y, R;
	int w, h;
	int oldX, oldY;
	float dx, dy;
	int ground;
public:
	BallObject ();
	~BallObject ();

	virtual void Draw ();
	virtual void Update ();
	virtual Vector2 GetPosition();
	virtual void SetPosition(Vector2 aux_position);
	virtual float Getdx();
	virtual float Getdy();
	virtual void Setdx(float aux_dx);
	virtual void Setdy(float aux_dy);
};

#endif