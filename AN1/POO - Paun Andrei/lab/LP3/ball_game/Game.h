#ifndef GAME_H
#define GAME_H

#include "Scene.h"

class Game
{
public:
	Scene* _currentScene;

public:
	static Game* Instance ();

	void Start ();

private:
	Game ();
	~Game ();
	Game (const Game& other);
	Game& operator= (const Game& other);
};

#endif