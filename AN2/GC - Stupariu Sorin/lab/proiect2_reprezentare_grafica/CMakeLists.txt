cmake_minimum_required(VERSION 3.3)
project(geomcomp)

set(CMAKE_CXX_FLAGS "${CMAKE_CXX_FLAGS} -std=c++11 -lSDLmain -lSDL2 -lGL -lGLU -lSDL2_ttf")

set(SOURCE_FILES main.cpp engine.cpp engine.h camera.cpp camera.h actual_program.cpp actual_program.h point.h algqueue.cpp algqueue.h triangle.cpp triangle.h point.cpp circle.cpp circle.h font.cpp font.h)
add_executable(geomcomp ${SOURCE_FILES})

configure_file(input.txt input.txt COPYONLY)
configure_file(font.ttf font.ttf COPYONLY)