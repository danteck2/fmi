//
// Created by xiodine on 11/24/15.
//

#ifndef GEOMCOMP_CAMERA_H
#define GEOMCOMP_CAMERA_H

#include <unordered_map>

class camera {
    double x_;
    double y_;
    double z_;
    double pitch_;
    double yaw_;

public:
    camera(double x, double y, double z, double pitch = 0, double yaw = 0);

    void push();

    void tick(std::unordered_map<int, bool> &keystate_);
    void pop();

    void deltaPosition(double x = 0, double y = 0, double z = 0);

    void deltaView(double pitch = 0, double yaw = 0);

};


#endif //GEOMCOMP_CAMERA_H
