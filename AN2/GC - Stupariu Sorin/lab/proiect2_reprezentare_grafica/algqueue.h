//
// Created by xiodine on 11/24/15.
//

#ifndef GEOMCOMP_ALGQUEUE_H
#define GEOMCOMP_ALGQUEUE_H

#include "point.h"
#include <queue>
#include <memory>

class action {
public:
    enum TYPE {
        ADD_POINT, ADD_RESULT, ADD_HIGHLIGHT
    };

private:

    TYPE type_;
    point point_;

public:

    constexpr action(action::TYPE type, point point) noexcept : type_(type), point_(std::move(point)) { }

    constexpr action::TYPE getType() const noexcept { return type_; }

    constexpr const point &getPoint() const noexcept { return point_; }

    // default constructors
    constexpr action(const action &action) noexcept = default;

    constexpr action(action &&action) noexcept = default;

    // default operators
    action &operator=(const action &action) noexcept = default;

    action &operator=(action &&action) noexcept = default;

};


class algqueue : public std::queue<action> {
    static std::unique_ptr<algqueue> singleton_;
public:
    static algqueue &singleton();
};


#endif //GEOMCOMP_ALGQUEUE_H
