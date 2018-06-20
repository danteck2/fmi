//
// Created by xiodine on 11/23/15.
//

#include <SDL2/SDL_opengl.h>
#include <GL/glu.h>
#include "engine.h"
#include "circle.h"


void engine::run() {

    initScreen();
    initOpenGL();

    running = true;
    long lTick = SDL_GetTicks();
    while (running) {

        glClear(GL_COLOR_BUFFER_BIT | GL_DEPTH_BUFFER_BIT);

        events();

        while (SDL_GetTicks() - lTick > FPS) {
            lTick += FPS;
            tick();
        }

        camera_->push();

        draw();

        camera_->pop();

        SDL_GL_SwapWindow(window);

        SDL_Delay(FPS / 2);
    }
}

engine::~engine() {
    singleInstance = false;
    SDL_Quit();
}

void engine::initOpenGL() {

    glClearDepth(1.0f);
    glClearColor(1.0f, 1.0f, 1.0f, 1.0f);


    // depth testing
    glEnable(GL_DEPTH_TEST);
    glDepthFunc(GL_LEQUAL);


    glHint(GL_PERSPECTIVE_CORRECTION_HINT, GL_NICEST);

    glEnable(GL_BLEND);
    glBlendFunc(GL_SRC_ALPHA, GL_ONE_MINUS_SRC_ALPHA);

    glMatrixMode(GL_PROJECTION);
    glLoadIdentity();
    glViewport(0, 0, WIDTH, HEIGHT);
    gluPerspective(45.0, (double) WIDTH / HEIGHT, 0.1, 100);
    glMatrixMode(GL_MODELVIEW);
    glLoadIdentity();

    glPointSize(10);
    glEnable(GL_TEXTURE_2D);
}

void engine::initScreen() {
    SDL_Init(SDL_INIT_VIDEO);

    SDL_Rect r;
    if (!SDL_GetDisplayBounds(0, &r)) {
        WIDTH = r.w;
        HEIGHT = r.h;
    }

    window = SDL_CreateWindow("Neagu Rares, Bucur Radu, Vlad Bulete - Proiect 3",
                              SDL_WINDOWPOS_CENTERED, SDL_WINDOWPOS_CENTERED,
                              WIDTH, HEIGHT, SDL_WINDOW_OPENGL | SDL_WINDOW_FULLSCREEN);
    SDL_GL_CreateContext(window);

    SDL_SetRelativeMouseMode(SDL_TRUE);
}

engine::engine() : camera_(new camera(0.5, 2, 10, 0, 15)), keystate_(), inside_(-2), font_(), engineTicks_(0) {
    if (singleInstance)
        throw "cannot run multiple instances";
    singleInstance = true;
}

bool engine::singleInstance = false;
bool engine::running = false;
int engine::WIDTH = 800;
int engine::HEIGHT = 600;

void engine::events() {
    SDL_Event event;
    while (SDL_PollEvent(&event)) {
        if (event.type == SDL_QUIT || (event.type == SDL_KEYDOWN && event.key.keysym.sym == SDLK_ESCAPE))
            running = false;

        if (event.type == SDL_KEYDOWN && event.key.keysym.sym == SDLK_r)
            ENGINE_TICKS_ADD = 1;

        if (event.type == SDL_MOUSEMOTION)
            camera_->deltaView(event.motion.xrel / MOUSE_SMOOTHING, event.motion.yrel / MOUSE_SMOOTHING);

        if (event.type == SDL_KEYDOWN)
            keystate_[event.key.keysym.sym] = true;
        if (event.type == SDL_KEYUP)
            keystate_[event.key.keysym.sym] = false;
    }
}

void engine::draw() {
    static GLuint displayList = 0;
    glColor4f(1, 1, 1, 1);
    if (displayList == 0) {
        displayList = glGenLists(1);
        glNewList(displayList, GL_COMPILE);
        glLineWidth(5);
        glBegin(GL_LINES);
        glColor3d(0.001, 0.001, 1);
        glVertex3d(0.001, 0.001, -1000);
        glVertex3d(0.001, 0.001, 1000);
        glColor3d(1, 0.001, 0.001);
        glVertex3d(-1000, 0.001, 0.001);
        glVertex3d(1000, 0.001, 0.001);
        glColor3d(0.001, 1, 0.001);
        glVertex3d(0.001, -1000, 0.001);
        glVertex3d(0.001, 1000, 0.001);
        glEnd();


        glLineWidth(0.1);
        glColor3d(0.8, 0.8, 0.8);
        glBegin(GL_LINES);
        for (int i = -1000; i < 1000; i++) {
            if (i == 0)
                continue;

            glVertex3d(i + 0.0015, -1000, 0.0015);
            glVertex3d(i + 0.0015, 1000, 0.0015);

            glVertex3d(-1000, i + 0.0015, 0.0015);
            glVertex3d(1000, i + 0.0015, 0.0015);

        }
        glEnd();
        glColor4f(1, 1, 1, 0.5);
        glBindTexture(GL_TEXTURE_2D, 0);
        for (int i = -10; i <= 10; i++) {
            if (i == 0)
                continue;
            font_.print(std::to_string(i).c_str(), 0.003, i + 0.003, 0.003);
            font_.print(std::to_string(i).c_str(), i + 0.003, 0.003, 0.003);
        }

        font_.print("0", 0, 0, 0.001);
        glBindTexture(GL_TEXTURE_2D, 0);
        glEndList();
    }
    glBindTexture(GL_TEXTURE_2D, 0);
    glBegin(GL_TRIANGLE_FAN);
    if (inside_ == -2)
        glColor3d(0.3, 0.3, 0.3);
    else if (inside_ == -1)
        glColor3d(0.7, 0, 0);
    else if (inside_ == 0)
        glColor3d(0, 0, 0.7);
    else
        glColor3d(0, 0.7, 0);
    for (const auto &elem : actions) {
        if (elem.getType() != action::ADD_POINT)
            continue;
        if (elem.getPoint()[point::Z] != 0)
            continue;
        const auto &point = elem.getPoint();
        glVertex3d(point[point::X],
                   point[point::Y],
                   point[point::Z]);
    }
    glEnd();

    glColor3d(1, 0, 1);
    glLineWidth(3);
    glBegin(GL_LINE_LOOP);
    for (const auto &elem : actions) {
        if (elem.getType() != action::ADD_HIGHLIGHT)
            continue;
        const auto &point = elem.getPoint();
        glVertex3d(point[point::X],
                   point[point::Y],
                   point[point::Z] + 0.004);
    }
    glEnd();

    for (const auto &elem : actions) {
        if (elem.getType() != action::ADD_POINT)
            continue;

        const auto &point = elem.getPoint();
        if (point[point::Z] == 0)
            glColor3d(0, 0, 0);
        else
            glColor3d(1, 0, 0);
        auto newpoint = point;
        newpoint[point::Z] += 0.003;
        circle(newpoint, 0.1).gl(font_);
        newpoint[point::Z] -= 0.003 * 2;
        circle(newpoint, 0.1).gl(font_);

    }

    glCallList(displayList);
    glColor4f(1, 1, 1, 1);
    int index = 0;
    for (const auto &elem : actions) {
        index++;
        if (elem.getType() != action::ADD_POINT)
            continue;

        const auto &point = elem.getPoint();
        font_.print(std::to_string(index) + " (" + std::to_string(point[0]) + " " +
                    std::to_string(point[1]) + ")",
                    point[0] - 0.5,
                    point[1] + 0.1,
                    point[2] + 0.01, 0.1);
        glBindTexture(GL_TEXTURE_2D, 0);
    }


}

void engine::tick() {
    camera_->tick(keystate_);
    engineTicks_++;
    if (engineTicks_ >= ENGINE_TICKS_ADD) {
        engineTicks_ = 0;
        tickAdd();
    }
}

void engine::tickAdd() {
    if (algqueue::singleton().empty())
        return;

    const auto &x = algqueue::singleton().front();
    if (x.getType() == action::ADD_RESULT) {
        inside_ = x.getPoint()[point::X];
    }
    actions.push_back(x);
    algqueue::singleton().pop();
}

int engine::ENGINE_TICKS_ADD = 60;