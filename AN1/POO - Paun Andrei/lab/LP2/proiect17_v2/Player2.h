#include "PlayerOut.h"

class Player2 : protected Player
{
private:
	bool will_quit;
	SDL_Surface* gKeyPressSurfaces[KEY_PRESS_SURFACE_TOTAL];
	void updateInput();
public:
	Player2();
	~Player2();
	void updateSurfaces();
	bool GetQuit();
};