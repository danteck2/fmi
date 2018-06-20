#include "Player2.h"

Player2::Player2()
{
	gKeyPressSurfaces[KEY_PRESS_SURFACE_DEFAULT_2] = loadSurface("Player2_default.bmp");
	gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_DEFAULT_2];
	gKeyPressSurfaces[KEY_PRESS_SURFACE_I] = loadSurface("Player2_I.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_K] = loadSurface("Player2_K.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_J] = loadSurface("Player2_J.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_L] = loadSurface("Player2_L.bmp");
	updateSurfaces();
}
Player2::~Player2()
{
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_DEFAULT_2]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_DEFAULT_2] = NULL;
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_I]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_I] = NULL;
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_K]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_K] = NULL;
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_J]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_J] = NULL;
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_L]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_L] = NULL;
}
void Player2::updateInput()
{
	InputManager* input = InputManager::getInstance();
	//input->update();
	will_quit = input->quitRequested();
	if ((input->isKeyDown(KEY_ESCAPE)) || (input->isKeyDown(KEY_Q)))
		will_quit = true;
	if (input->isKeyDown(KEY_I) || input->isKeyDown(KEY_8))
		gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_I];
	if (input->isKeyDown(KEY_K) || input->isKeyDown(KEY_5))
		gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_K];
	if (input->isKeyDown(KEY_J) || input->isKeyDown(KEY_4))
		gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_J];
	if (input->isKeyDown(KEY_L) || input->isKeyDown(KEY_6))
		gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_L];
}
void Player2::updateSurfaces()
{
	updateInput();
	SDL_BlitSurface(gCurrentSurface, NULL, gScreenSurface, NULL);
	SDL_UpdateWindowSurface(gWindow);
}
bool Player2::GetQuit()
{
	return will_quit;
}