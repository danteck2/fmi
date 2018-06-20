//
// Created by xiodine on 11/24/15.
//

#ifndef GEOMCOMP_ACTUAL_PROGRAM_H
#define GEOMCOMP_ACTUAL_PROGRAM_H

#include <vector>
#include "point.h"

class actual_program {

    std::vector<point> puncte;

    int isInsideVal;

    point tri1, tri2, tri3;

    int sign_det(point a, point b, point c);

    void isInsideCalc();

    int sign_tri(point a, point b, point c, point m);

public:
    actual_program();


};


#endif //GEOMCOMP_ACTUAL_PROGRAM_H
