//
// Created by xiodine on 11/25/15.
//

#ifndef GEOMCOMP_TRIANGLE_H
#define GEOMCOMP_TRIANGLE_H

#include "point.h"
#include <utility>

class triangle {
    point a_;
    point b_;
    point c_;

public:

    enum INDEX {
        A, B, C
    };

    constexpr triangle(point a, point b, point c) noexcept :
            a_(std::move(a)),
            b_(std::move(b)),
            c_(std::move(c)) { }

    constexpr point operator[](int index) const noexcept {
        return index == 0 ? a_ : (index == 1 ? b_ : c_);
    }

    // default constructors
    constexpr triangle(const triangle &triangle) noexcept = default;

    constexpr triangle(triangle &&triangle) noexcept = default;

    // default operators
    triangle &operator=(const triangle &triangle) noexcept = default;

    triangle &operator=(triangle &&triangle) noexcept = default;
};


#endif //GEOMCOMP_TRIANGLE_H
