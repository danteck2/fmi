//
// Created by xiodine on 11/24/15.
//

#include <SDL2/SDL_opengl.h>
#include <SDL2/SDL.h>
#include "camera.h"
#include "engine.h"

camera::camera(double x, double y, double z, double pitch, double yaw) : x_(x), y_(y), z_(z), pitch_(pitch), yaw_(yaw) {

}

void camera::push() {
    glPushMatrix();
    glRotated(yaw_, 1, 0, 0);
    glRotated(pitch_, 0, 1, 0);
    glTranslated(-x_, -y_, -z_);
}

void camera::pop() {
    glPopMatrix();
}

void camera::deltaPosition(double x, double y, double z) {
    x_ += x;
    y_ += y;
    z_ += z;
}

void camera::deltaView(double pitch, double yaw) {
    pitch_ += pitch;
    yaw_ += yaw;

    if (pitch_ < 0)
        pitch_ += 360.0;
    if (pitch_ > 360.0)
        pitch_ -= 360.0;

    if (yaw_ < -90)
        yaw_ = -90;
    if (yaw_ > 90)
        yaw_ = 90;

}

void camera::tick(std::unordered_map<int, bool> &keystate_) {
    static double PI = 3.14159265;
    static double DEG2RAD = PI / 180.0;
    double xDelta = 0, yDelta = 0, zDelta = 0;
    if (keystate_[SDLK_w]) {
        xDelta += sin(pitch_ * DEG2RAD);
        zDelta -= cos(pitch_ * DEG2RAD);
    }

    if (keystate_[SDLK_s]) {
        xDelta -= sin(pitch_ * DEG2RAD);
        zDelta += cos(pitch_ * DEG2RAD);
    }
    if (keystate_[SDLK_a]) {
        xDelta += sin(pitch_ * DEG2RAD - PI / 2.0);
        zDelta -= cos(pitch_ * DEG2RAD - PI / 2.0);
    }
    if (keystate_[SDLK_d]) {
        xDelta -= sin(pitch_ * DEG2RAD - PI / 2.0);
        zDelta += cos(pitch_ * DEG2RAD - PI / 2.0);
    }
    if (keystate_[SDLK_q])
        yDelta++;
    if (keystate_[SDLK_e])
        yDelta--;

    deltaPosition(xDelta / engine::KEY_SMOOTHING, yDelta / engine::KEY_SMOOTHING, zDelta / engine::KEY_SMOOTHING);
}
