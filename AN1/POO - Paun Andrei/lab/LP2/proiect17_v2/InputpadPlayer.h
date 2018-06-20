#include "VoleyPlayerOut.h"

class InputpadPlayer : protected Player
{
private:
	bool will_quit;
	SDL_Surface* gKeyPressSurfaces[KEY_PRESS_SURFACE_TOTAL];
	void updateInput();
public:
	InputpadPlayer();
	~InputpadPlayer();
	void updateSurfaces();
	bool GetQuit();
};