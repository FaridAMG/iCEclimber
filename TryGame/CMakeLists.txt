cmake_minimum_required(VERSION 3.15)
project(TryGame C)

set(CMAKE_C_STANDARD 99)
set(CMAKE_MODULE_PATH ${CMAKE_MODULE_PATH} "${TryGame_SOURCE_DIR}/sdlf")


add_executable(TryGame main.c)

find_package(SDL2 REQUIRED)
find_package(SDL2_image REQUIRED)
find_package(SDL2_ttf REQUIRED)

include_directories(${SDL2_INCLUDE_DIR}
        ${SDL2_IMAGE_INCLUDE_DIR}
        ${SDL2_TTF_INCLUDE_DIR})
target_link_libraries(TryGame ${SDL2_LIBRARY}
        ${SDL2_IMAGE_LIBRARIES}
        ${SDL2_TTF_LIBRARIES})