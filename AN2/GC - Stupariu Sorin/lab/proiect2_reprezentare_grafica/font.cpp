//
// Created by xiodine on 11/25/15.
//

#include <SDL2/SDL.h>
#include <SDL2/SDL_opengl.h>
#include "font.h"

void font::print(std::string text, double x, double y, double z, double size) {
    static GLuint textureID = 0;

    SDL_Color color = {1, 0, 0, 1};
    SDL_Surface *surfaceMessage = TTF_RenderText_Blended(font_, text.c_str(), color);

    if (textureID != 0) {
        glDeleteTextures(1, &textureID);
    }

    glGenTextures(1, &textureID);

    glBindTexture(GL_TEXTURE_2D, textureID);

    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MIN_FILTER, GL_LINEAR);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_MAG_FILTER, GL_LINEAR);

    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_S, GL_CLAMP_TO_EDGE);
    glTexParameterf(GL_TEXTURE_2D, GL_TEXTURE_WRAP_T, GL_CLAMP_TO_EDGE);
    glTexImage2D(GL_TEXTURE_2D, 0, GL_RGBA, surfaceMessage->w, surfaceMessage->h, 0, GL_BGRA, GL_UNSIGNED_BYTE,
                 surfaceMessage->pixels);

    glBindTexture(GL_TEXTURE_2D, textureID);
    glBegin(GL_QUADS);
    double wh = surfaceMessage->w / (double) surfaceMessage->h;
    glTexCoord2d(1, 1);
    glVertex3d(x + size * wh, y, z);
    glTexCoord2d(1, 0);
    glVertex3d(x + size * wh, y + size, z);
    glTexCoord2d(0, 0);
    glVertex3d(x, y + size, z);
    glTexCoord2d(0, 1);
    glVertex3d(x, y, z);
    glEnd();

    SDL_FreeSurface(surfaceMessage);
}

font::font() : font_(nullptr) {
    TTF_Init();
    font_ = TTF_OpenFont("font.ttf", 24); //this opens a font style and sets a size
    if (font_ == nullptr) {
        SDL_Log("Oh My Goodness, an error : %s", TTF_GetError());
        return;
    }
}

font::~font() {
    if (font_ != nullptr)
        TTF_CloseFont(font_);
}
