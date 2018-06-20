#include <stdio.h>
#include <string>
#include "InputpadPlayerOut.h"

int main(int argc, char* args[])
{
	//Intitializare SDL
	init();

	InputManager* inputmain = InputManager::getInstance();
	inputmain->update();
	//Player player;
	//while (!player.GetQuit())
	//{
	//	inputmain->update();
	//	player.updateSurfaces();
	//}
	//Player2 player2;
	//while (!player2.GetQuit())
	//{
	//	inputmain->update();
	//	player2.updateSurfaces();
	//}
	//OnePlayer oneplayer;
	//while (!oneplayer.GetQuit())
	//{
	//	inputmain->update();
	//	oneplayer.updateSurfaces();
	//}
	//TwoPlayer twoplayer;
	//while (!twoplayer.GetQuit())
	//{
	//	inputmain->update();
	//	twoplayer.updateSurfaces();
	//}
	//VoleyPlayer voleyplayer;
	//while (!voleyplayer.GetQuit())
	//{
	//	inputmain->update();
	//	voleyplayer.updateSurfaces();
	//}
	InputpadPlayer inputpadplayer;
	while (!inputpadplayer.GetQuit())
	{
		inputmain->update();
		inputpadplayer.updateSurfaces();
	}
	close();
	return 0;
}