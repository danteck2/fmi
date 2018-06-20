#include "Game.h"
#include "GameEngine.h"

int main (int argc, char** argv)
{
	GameEngine::Init ();

	Game::Instance ()->Start ();

	GameEngine::Quit ();
}