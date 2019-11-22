cmake_minimum_required(VERSION 3.15)
project(Client C)

set(CMAKE_C_STANDARD 99)

set(CFLAGS += $(shell pkg-config --cflags json-c))
set(LDFLAGS += $(shell pkg-config --libs json-c))

# the `pkg_check_modules` function is created with this call
find_package(PkgConfig REQUIRED)




# these calls create special `PkgConfig::<MODULE>` variables
pkg_check_modules(MY_PKG REQUIRED IMPORTED_TARGET json-c)



add_executable(Client main.c json.c )

# inform the linker about this program's dependencies
target_link_libraries(Client
        PkgConfig::MY_PKG)

target_compile_options(Client PUBLIC ${SDL2_CFLAGS_OTHER})

