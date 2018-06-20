#include "Player.h"

Player::Player()
{
	gKeyPressSurfaces[KEY_PRESS_SURFACE_DEFAULT_1] = loadSurface("Player_default.bmp");
	gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_DEFAULT_1];
	gKeyPressSurfaces[KEY_PRESS_SURFACE_W] = loadSurface("Player_W.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_S] = loadSurface("Player_S.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_A] = loadSurface("Player_A.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_D] = loadSurface("Player_D.bmp");
	updateSurfaces();
}
Player::~Player()
{
		SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_DEFAULT_1]);
		gKeyPressSurfaces[KEY_PRESS_SURFACE_DEFAULT_1] = NULL;
		SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_W]);
		gKeyPressSurfaces[KEY_PRESS_SURFACE_W] = NULL;
		SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_S]);
		gKeyPressSurfaces[KEY_PRESS_SURFACE_S] = NULL;
		SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_A]);
		gKeyPressSurfaces[KEY_PRESS_SURFACE_A] = NULL;
		SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_D]);
		gKeyPressSurfaces[KEY_PRESS_SURFACE_D] = NULL;
}
void Player::updateInput()
{
	InputManager* input = InputManager::getInstance();
	//input->update();
	will_quit = input->quitRequested();
	if ((input->isKeyDown(KEY_ESCAPE)) || (input->isKeyDown(KEY_Q)))
		will_quit = true;
	if (input->isKeyDown(KEY_W) || input->isKeyDown(KEY_UP))
		gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_W];
	if (input->isKeyDown(KEY_S) || input->isKeyDown(KEY_DOWN))
		gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_S];
	if (input->isKeyDown(KEY_A) || input->isKeyDown(KEY_LEFT))
		gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_A];
	if (input->isKeyDown(KEY_D) || input->isKeyDown(KEY_RIGHT))
		gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_D];
}
void Player::updateSurfaces()
{
	updateInput();
	//Aplica imaginea curenta
	SDL_BlitSurface(gCurrentSurface, NULL, gScreenSurface, NULL);
	SDL_UpdateWindowSurface(gWindow);
}
bool Player::GetQuit()
{
	return will_quit;
}
SDL_Surface* Player::loadSurface(std::string path)
{
	//Incarca adresa specifica img
	SDL_Surface* loadedSurface = SDL_LoadBMP(path.c_str());
	return loadedSurface;
}