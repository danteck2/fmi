#include "InputManagerOut.h"

class Player
{
private:
	bool will_quit;
	SDL_Surface* gKeyPressSurfaces[KEY_PRESS_SURFACE_TOTAL];
	virtual void updateInput();
protected:
	SDL_Surface* loadSurface(std::string path);
public:
	Player();
	~Player();
	virtual void updateSurfaces();
	virtual bool GetQuit();
};