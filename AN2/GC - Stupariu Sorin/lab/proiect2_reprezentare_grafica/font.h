//
// Created by xiodine on 11/25/15.
//

#ifndef GEOMCOMP_FONT_H
#define GEOMCOMP_FONT_H


#include <string>
#include <SDL2/SDL_ttf.h>

class font {
    TTF_Font *font_;
public:
    font();

    ~font();

    void print(std::string text, double x, double y, double z, double size = 0.2);
};


#endif //GEOMCOMP_FONT_H