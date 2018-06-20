//
// Created by xiodine on 11/24/15.
//

#include <utility>
#include "algqueue.h"

algqueue &algqueue::singleton() {
    if (!singleton_)
        singleton_ = std::unique_ptr<algqueue>(new algqueue());
    return *singleton_.get();
}

std::unique_ptr<algqueue> algqueue::singleton_ = nullptr;