#include "InputManager.h"

InputManager* InputManager::getInstance()
{
	if (!instance)
		instance = new InputManager();
	return instance;
}
void InputManager::update()
{
	int i;
	for (i = 0; i < KEYBOARD_SIZE; i++)
	{
		keyDown[i] = false;
		keyUp[i] = false;
	}
	for (i = 0; i < MOUSE_MAX; i++)
	{
		mouseDown[i] = false;
		mouseUp[i] = false;
	}
	SDL_Event event; //include SDL_KeyboardEvent, SDL_MouseButtonEvent si SDL_QuitEvent
	while (SDL_PollEvent(&event))
	{
		switch (event.type)
		{
		case SDL_QUIT:
			will_quit = true;
			break;

		case SDL_KEYDOWN:
		{
			keyboard = SDL_GetKeyboardState(nullptr);
			int index = event.key.keysym.scancode;
			keyDown[index] = true;
		}
		break;

		case SDL_KEYUP:
		{
			keyboard = SDL_GetKeyboardState(nullptr);
			int index = event.key.keysym.scancode;
			keyUp[index] = true;
		}
		break;

		case SDL_MOUSEBUTTONDOWN:
			if (event.button.button == SDL_BUTTON_LEFT)
				mouseDown[MOUSE_LEFT] = true;
			else if (event.button.button == SDL_BUTTON_RIGHT)
				mouseDown[MOUSE_RIGHT] = true;
			break;

		case SDL_MOUSEBUTTONUP:
			if (event.button.button == SDL_BUTTON_LEFT)
				mouseUp[MOUSE_LEFT] = true;
			else if (event.button.button == SDL_BUTTON_RIGHT)
				mouseUp[MOUSE_RIGHT] = true;
			break;

		default:
			break;
		}
	}
}
bool InputManager::isKeyDown(int key)
{
	if (key == KEY_CAPSLOCK) return (keyDown[key]);
	if (isLocked) return false;
	if (key < 0 || key >= KEYBOARD_SIZE)
		return false;
	return (keyDown[key]);
}
bool InputManager::isKeyUp(int key)
{
	if (isLocked) return false;
	if (key < 0 || key >= KEYBOARD_SIZE)
		return false;
	return (keyUp[key]);
}
bool InputManager::shift()
{
	return (instance->isKeyPressed(KEY_LEFT_SHIFT) || instance->isKeyPressed(KEY_RIGHT_SHIFT));
}
bool InputManager::ctrl()
{
	return (instance->isKeyPressed(KEY_LEFT_CTRL) || instance->isKeyPressed(KEY_RIGHT_CTRL));
}
bool InputManager::alt()
{
	return (instance->isKeyPressed(KEY_LEFT_ALT) || instance->isKeyPressed(KEY_RIGHT_ALT));
}
bool InputManager::isMouseDown(MouseButton button)
{
	if (isLocked) return false;
	if (button == MOUSE_MAX)
		return false;
	return mouseDown[button];
}
bool InputManager::isMouseUp(MouseButton button)
{
	if (isLocked) return false;
	if (button == MOUSE_MAX)
		return false;
	return mouseUp[button];
}
bool InputManager::isKeyPressed(KeyboardKey key)
{
	if (isLocked) return false;
	if (!(keyboard))
		return false;
	int sdl_key = static_cast<int>(key);
	if (keyboard[sdl_key])
		return true;
	return false;
}
bool InputManager::quitRequested()
{
	return (will_quit);
}
bool InputManager::isLock()
{
	return isLocked;
}
void InputManager::lock()
{
	isLocked = true;
}
void InputManager::unlock()
{
	isLocked = false;
}
InputManager* InputManager::instance = nullptr;