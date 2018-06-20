#ifndef SDL_H_DEFINED
#define SDL_H_DEFINED

// SDL 2.0, beibeh
#include <SDL.h>            // SDL2
#include <SDL_video.h>      // SDL2
//#include <SDL_image.h>      // SDL2_Image
//#include <SDL_ttf.h>        // SDL2_TTF
//#include <SDL_mixer.h>      // SDL2_Mixer
//#include <SDL2_rotozoom.h>      // SDL2_GFX
//#include <SDL2_gfxPrimitives.h> // SDL2_GFX

//#include "Timer.hpp"
//#include "Color.h"
//#include "Shapes.h"

// Shuts up the compiler about unused parameters.
#define UNUSED(x) ((void)(x))

/// Wrapper on basic SDL2 calls.
///
/// This simply initializes and finishes SDL2.
///
/// Other classes too have direct SDL calls,
/// check out `Music` and `Window`.
///
namespace SDL
{
	/// Starts SDL2, MUST be called before everything else.
	bool init();

	/// Destroys SDL2, MUST be called when program ends.
	void exit();
};

#endif /* SDL_H_DEFINED */
