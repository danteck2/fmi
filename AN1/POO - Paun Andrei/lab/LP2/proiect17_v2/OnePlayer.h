#include "Player2Out.h"

class OnePlayer : protected Player
{
private:
	bool will_quit;
	SDL_Surface* gKeyPressSurfaces[KEY_PRESS_SURFACE_TOTAL];
	void updateInput();
public:
	OnePlayer();
	~OnePlayer();
	void updateSurfaces();
	bool GetQuit();
};