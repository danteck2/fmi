//
// Created by xiodine on 11/25/15.
//

#ifndef GEOMCOMP_CIRCLE_H
#define GEOMCOMP_CIRCLE_H


#include <utility>
#include "point.h"
#include "font.h"

class circle {
    point origin_;
    double radius_;
public:
    constexpr circle(point origin, double radius) noexcept : origin_(std::move(origin)), radius_(radius) { }

    void gl(font &font);
};


#endif //GEOMCOMP_CIRCLE_H
