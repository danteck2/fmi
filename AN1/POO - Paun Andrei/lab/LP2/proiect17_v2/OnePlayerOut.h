#include "OnePlayer.h"

OnePlayer::OnePlayer()
{
	gKeyPressSurfaces[KEY_PRESS_SURFACE_DEFAULT_3] = loadSurface("OnePlayer_default.bmp");
	gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_DEFAULT_3];
	gKeyPressSurfaces[KEY_PRESS_SURFACE_CTRL_Z] = loadSurface("OnePlayer_CTRL.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_SHIFT_Z] = loadSurface("OnePlayer_SHIFT.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_ALT_Z] = loadSurface("OnePlayer_ALT.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_W] = loadSurface("Player_W.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_S] = loadSurface("Player_S.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_A] = loadSurface("Player_A.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_D] = loadSurface("Player_D.bmp");
	updateSurfaces();
}
OnePlayer::~OnePlayer()
{
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_DEFAULT_3]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_DEFAULT_3] = NULL;
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_CTRL_Z]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_CTRL_Z] = NULL;
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_SHIFT_Z]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_SHIFT_Z] = NULL;
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_ALT_Z]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_ALT_Z] = NULL;
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_W]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_W] = NULL;
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_S]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_S] = NULL;
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_A]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_A] = NULL;
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_D]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_D] = NULL;
}
void OnePlayer::updateInput()
{
	will_quit = GetQuit();
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
	if (input->isKeyDown(KEY_Z))
	{
		if (input->ctrl())
			gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_CTRL_Z];
		if (input->shift())
			gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_SHIFT_Z];
		if (input->alt())
			gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_ALT_Z];
	}
}
void OnePlayer::updateSurfaces()
{
	updateInput();
	//Aplica imaginea curenta
	SDL_BlitSurface(gCurrentSurface, NULL, gScreenSurface, NULL);
	SDL_UpdateWindowSurface(gWindow);
}
bool OnePlayer::GetQuit()
{
	return will_quit;
}