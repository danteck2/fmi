#include "Input.h"

bool Input::_keyState[KEYS_COUNT];
bool Input::_lastKeyState[KEYS_COUNT];
bool Input::_lastMouseState[4];
bool Input::_mouseState[4];
bool Input::_sdlQuit (false);
Vector2 Input::_resizeEvent (Vector2::Zero);

void Input::UpdateState ()
{
	for (int i=0;i<KEYS_COUNT;i++) {
		_lastKeyState [i] = _keyState [i];
	}

	for (int i=1;i<4;i++) {
		_lastMouseState [i] = _mouseState [i];
	}

	_resizeEvent = Vector2::Zero;

	SDL_Event event;

    while(SDL_PollEvent(&event))
    {
        switch(event.type)
        {
			case SDL_QUIT:
				_sdlQuit = true;
				break;
			// case SDL_VIDEORESIZE :
			// 	_resizeEvent.SetX (event.resize.w);
			// 	_resizeEvent.SetY (event.resize.h);
			// 	break;
            case SDL_KEYDOWN:
				if ((int)event.key.keysym.sym >= KEYS_COUNT) {
					break;
				}
				_keyState[(int)event.key.keysym.sym] = true;
            	break;
            case SDL_KEYUP:
				if ((int)event.key.keysym.sym >= KEYS_COUNT) {
					break;
				}
				_keyState[(int)event.key.keysym.sym] = false;
                break;
            case SDL_MOUSEBUTTONDOWN:
				_mouseState [(int)event.button.button] = true;
				break;
			case SDL_MOUSEBUTTONUP:
				_mouseState [(int)event.button.button] = false;
				break;
        }
    }
}

bool Input::GetKey (unsigned char ch)
{
	return _keyState [(int)ch];
}

bool Input::GetKeyDown (unsigned char key)
{
	return _keyState [(int)key] && !_lastKeyState [(int)key];
}

bool Input::GetKeyUp (unsigned char key)
{
	return !_keyState [(int)key] && _lastKeyState [(int)key];
}

bool Input::GetMouseButton (Uint8 button)
{
	return _mouseState [button];
}

bool Input::GetMouseButtonDown (Uint8 button)
{
	return _mouseState [button] && !_lastMouseState [button];
}

bool Input::GetMouseButtonUp (Uint8 button)
{
	return !_mouseState [button] && _lastMouseState [button];
}

Vector2 Input::GetMousePosition ()
{
	int x = 0, y = 0;

	SDL_GetMouseState(&x, &y);

	Vector2 position;
	position.SetX ((float) x);
	position.SetY ((float) y);

	return position;
}

bool Input::GetQuit ()
{
	return _sdlQuit;
}

Vector2 Input::GetResizeEvent ()
{
	return _resizeEvent;
}
