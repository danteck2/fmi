#include "SDL_declaration.h"

class InputManager
{
private:
	static InputManager* instance; //singleton instance
	const uint8_t* keyboard; //SDL stare interna tastaura
	bool keyDown[KEYBOARD_SIZE]; //vector taste down
	bool keyUp[KEYBOARD_SIZE]; //vector taste up
	bool mouseDown[MOUSE_MAX];
	bool mouseUp[MOUSE_MAX];
	bool will_quit; //q sau esc
	bool isLocked; //salveaza daca InputManager este blocat sau nu
public:
	static InputManager* getInstance();
	void update();//la fiecare frame este improspatat vectorul de taste, butoane...
	bool isKeyDown(int key);//returneaza daca tasta este apasata acum
	bool isKeyUp(int key);//returneaza daca tasta a fost apasata acum
	bool shift();//Shift stanga sau Shift dreapta este apasat
	bool ctrl();//Ctrl stanga sau Ctrl dreapta este apasat
	bool alt();//Alt stanga sau Alt dreapta este apasat
	bool isMouseDown(MouseButton button);
	bool isMouseUp(MouseButton button);
	bool isKeyPressed(KeyboardKey key); //tasta este apasata continu acum
	bool quitRequested(); //q or esc
	bool isLock();//get->isLocked
	void lock();//blocheaza InputManager toate metodele returneaza fals //CAPSLOCK
	void unlock();////deblocheaza InputManager toate metodele returneaza normal
private:
	InputManager() : keyboard(nullptr), will_quit(false), isLocked(false){} //constructor
	~InputManager();
	InputManager(InputManager const&) {};//elimina posibilitatea de a folosi copyconstr sau = pt singleton
	void operator=(InputManager const&) {};
};