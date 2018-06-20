//
// Created by xiodine on 11/25/15.
//

#include <SDL2/SDL_opengl.h>
#include "point.h"


double &point::operator[](int index) noexcept {
    return index == 0 ? x_ : (index == 1 ? y_ : z_);
}

void point::glTranslate() const noexcept {
    glTranslated(x_, y_, z_);
}
