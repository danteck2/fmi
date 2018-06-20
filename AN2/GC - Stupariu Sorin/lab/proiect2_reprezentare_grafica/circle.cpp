//
// Created by xiodine on 11/25/15.
//

#include "circle.h"
#include <SDL2/SDL_opengl.h>

#define PI 3.14159265

void circle::gl(font &font) {
    static GLuint displayList = 0;
    if (displayList == 0) {
        displayList = glGenLists(1);
        glNewList(displayList, GL_COMPILE);
        glBegin(GL_POLYGON);
        for (double i = 0; i < 2 * PI; i += PI / 180.0) {
            glVertex3d(cos(i), sin(i), 0);
        }
        glEnd();
        glEndList();
    }
    glPushMatrix();

    origin_.glTranslate();
    glScaled(radius_, radius_, radius_);
    glCallList(displayList);
    glBindTexture(GL_TEXTURE_2D, 0);
    glPopMatrix();
}
