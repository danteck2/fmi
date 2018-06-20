#ifndef SCENE_H
#define SCENE_H

#include <vector>

#include "SceneObject.h"

class Scene
{
private:
	std::vector<SceneObject*> _objects;

public:
	Scene ();
	~Scene ();

	void Update ();
	void Display ();
	void Collision(SceneObject* b, SceneObject* p);
	void Clear ();
};

#endif