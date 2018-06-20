#include "GameEngine.h"

#if defined(__linux__) || defined(__APPLE__)
	#include <SDL2/SDL.h>
#elif defined(_WIN32)
    #include <SDL.h>
#endif

#include "Screen.h"

void GameEngine::Init ()
{
	SDL_Init(SDL_INIT_EVERYTHING);

	Screen::Init ();
}

void GameEngine::Quit ()
{
	Screen::Quit ();

	SDL_Quit();
}
