#include "GameTime.h"

uint32_t GameTime::_currentTimeMS (0);
uint32_t GameTime::_deltaTimeMS (0);

float GameTime::GetDeltaTime()
{
	return _deltaTimeMS / 1000.0f;
}

float GameTime::GetTime()
{
	return _currentTimeMS;
}

void GameTime::Init()
{
	_currentTimeMS = SDL_GetTicks();
}

void GameTime::UpdateFrame()
{
	uint32_t lastTimeMS = _currentTimeMS;
	_currentTimeMS = SDL_GetTicks();

	_deltaTimeMS = _currentTimeMS - lastTimeMS;
}

unsigned int GameTime::GetDeltaTimeMS ()
{
	return _deltaTimeMS;
}

/*
 * Get ticks since the start of the game until the start of current frame.
*/

unsigned int GameTime::GetTimeMS ()
{
	return _currentTimeMS;
}

/*
 * Get ticks since the start of the game until the current moment.
*/

unsigned int GameTime::GetElapsedTimeMS ()
{
	return SDL_GetTicks ();
}
