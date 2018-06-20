#include "OnePlayerOut.h"

class TwoPlayer : protected Player
{
private:
	bool will_quit;
	SDL_Surface* gKeyPressSurfaces[KEY_PRESS_SURFACE_TOTAL];
	void updateInput();
public:
	TwoPlayer();
	~TwoPlayer();
	void updateSurfaces();
	bool GetQuit();
};