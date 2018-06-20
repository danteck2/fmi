//
// Created by xiodine on 11/23/15.
//

#ifndef GEOMCOMP_ENGINE_H
#define GEOMCOMP_ENGINE_H

#include <SDL2/SDL.h>
#include "camera.h"
#include "algqueue.h"
#include "font.h"
#include <memory>
#include <unordered_map>
#include <vector>


class engine {


    void initOpenGL();

    void initScreen();

    static bool singleInstance;
    SDL_Window *window;

    std::unique_ptr<camera> camera_;

    std::unordered_map<int, bool> keystate_;

    std::vector<action> actions;

    int engineTicks_;
    double inside_;

    void tickAdd();

    font font_;

public:


    engine();

    ~engine();

    void run();

    static bool running;

    void events();

    void draw();

    void tick();

    static int WIDTH;
    static int HEIGHT;
    static const int FPS = 1000 / 60;
    static int ENGINE_TICKS_ADD;
    static constexpr double MOUSE_SMOOTHING = 100.0;
    static constexpr double KEY_SMOOTHING = 10.0;
};


#endif //GEOMCOMP_ENGINE_H
