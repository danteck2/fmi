#include "TwoPlayerOut.h"

class VoleyPlayer : protected Player
{
private:
	bool will_quit;
	SDL_Surface* gKeyPressSurfaces[KEY_PRESS_SURFACE_TOTAL];
	void updateInput();
public:
	VoleyPlayer();
	~VoleyPlayer();
	void updateSurfaces();
	bool GetQuit();
};