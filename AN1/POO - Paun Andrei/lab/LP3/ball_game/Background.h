#ifndef BACKGROUND_H
#define BACKGROUND_H

#include "Image.h"

#include "SceneObject.h"
#include "Vector2.h"

class Background : public SceneObject
{
private:
	Image* _image;
	Vector2 _position;

public:
	Background();
	~Background();

	virtual void Draw();
};

#endif