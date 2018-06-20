#ifndef SCENEOBJECT_H
#define SCENEOBJECT_H

#include "Object.h"
#include "Vector2.h"

class SceneObject : public Object
{
private:
	Vector2 posit;
public:
	SceneObject ();
	virtual ~SceneObject ();

	virtual void Update ();
	virtual void Draw ();
	virtual Vector2 GetPosition();
	virtual void SetPosition(Vector2 aux_position);
	virtual float Getdx();
	virtual float Getdy();
	virtual void Setdx(float aux_dx);
	virtual void Setdy(float aux_dy);
};

#endif