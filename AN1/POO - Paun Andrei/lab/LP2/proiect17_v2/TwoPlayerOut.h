#include "TwoPlayer.h"

TwoPlayer::TwoPlayer()
{
	gKeyPressSurfaces[KEY_PRESS_SURFACE_DEFAULT_4] = loadSurface("TwoPlayer_default.bmp");
	gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_DEFAULT_4];
	gKeyPressSurfaces[KEY_PRESS_SURFACE_CTRL_Z] = loadSurface("OnePlayer_CTRL.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_SHIFT_Z] = loadSurface("OnePlayer_SHIFT.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_ALT_Z] = loadSurface("OnePlayer_ALT.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_W] = loadSurface("Player_W.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_S] = loadSurface("Player_S.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_A] = loadSurface("Player_A.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_D] = loadSurface("Player_D.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_CTRL_SLASH] = loadSurface("TwoPlayer_CTRL.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_SHIFT_SLASH] = loadSurface("TwoPlayer_SHIFT.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_ALT_SLASH] = loadSurface("TwoPlayer_ALT.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_I] = loadSurface("Player2_I.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_K] = loadSurface("Player2_K.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_J] = loadSurface("Player2_J.bmp");
	gKeyPressSurfaces[KEY_PRESS_SURFACE_L] = loadSurface("Player2_L.bmp");
	updateSurfaces();
}
TwoPlayer::~TwoPlayer()
{
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_DEFAULT_4]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_DEFAULT_4] = NULL;
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
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_CTRL_SLASH]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_CTRL_SLASH] = NULL;
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_SHIFT_SLASH]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_SHIFT_SLASH] = NULL;
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_ALT_SLASH]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_ALT_SLASH] = NULL;
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_I]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_I] = NULL;
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_K]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_K] = NULL;
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_J]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_J] = NULL;
	SDL_FreeSurface(gKeyPressSurfaces[KEY_PRESS_SURFACE_L]);
	gKeyPressSurfaces[KEY_PRESS_SURFACE_L] = NULL;
}
void TwoPlayer::updateInput()
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
	if (input->isKeyDown(KEY_I) || input->isKeyDown(KEY_8))
		gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_I];
	if (input->isKeyDown(KEY_K) || input->isKeyDown(KEY_5))
		gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_K];
	if (input->isKeyDown(KEY_J) || input->isKeyDown(KEY_4))
		gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_J];
	if (input->isKeyDown(KEY_L) || input->isKeyDown(KEY_6))
		gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_L];
	if (input->isKeyDown(KEY_SLASH))
	{
		if (input->ctrl())
			gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_CTRL_SLASH];
		if (input->shift())
			gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_SHIFT_SLASH];
		if (input->alt())
			gCurrentSurface = gKeyPressSurfaces[KEY_PRESS_SURFACE_ALT_SLASH];
	}
}
void TwoPlayer::updateSurfaces()
{
	updateInput();
	//Aplica imaginea curenta
	SDL_BlitSurface(gCurrentSurface, NULL, gScreenSurface, NULL);
	SDL_UpdateWindowSurface(gWindow);
}
bool TwoPlayer::GetQuit()
{
	return will_quit;
}