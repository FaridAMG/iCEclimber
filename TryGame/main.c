
#include "SDL.h"
#include "SDL_image.h"
#include <stdio.h>

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdbool.h>
//#include "json.c"
//#include <json-c/json.h>
//#include <json_object_private.h>

#define PORT 8080



























//struct para crear los objetos
typedef struct
{
    float xtmp,ytmp;
    int contPasos;
    float x, y, dy;
    short life;
    char *name;
    int currentSprite, walking, facingLeft, visible,shooting,facingVice;
    int alive;

    SDL_Texture *sheetTexture;
} Object;



Object popo;
Object oso;
Object foca;
Object hielo;
SDL_Texture *backgroundTexture; //para el background
int globalTime = 0;


int cont=0;


//funcion para eventos del tecado
int processEvents(SDL_Window *window)
{
    SDL_Event event;
    int done = 0;

    while(SDL_PollEvent(&event))
    {
        switch(event.type)
        {
            case SDL_WINDOWEVENT_CLOSE:
            {
                if(window)
                {
                    SDL_DestroyWindow(window);
                    window = NULL;
                    done = 1;
                }
            }
                break;
            case SDL_KEYDOWN:
            {
                switch(event.key.keysym.sym)
                {
                    case SDLK_ESCAPE:
                        done = 1;
                        break;
                }
            }
                break;
            case SDL_QUIT:
                //quit out of the game
                done = 1;
                break;
        }
    }



    const Uint8 *state = SDL_GetKeyboardState(NULL);
    if(!popo.shooting)
    {
        if(state[SDL_SCANCODE_LEFT])
        {
            popo.x -= 3;
            popo.walking = 1;
            popo.facingLeft = 1;

            if(globalTime % 6 == 0)
            {
                popo.currentSprite++;
                popo.currentSprite %= 4;
            }
        }
        else if(state[SDL_SCANCODE_RIGHT])
        {
            popo.x += 3;
            popo.walking = 1;
            popo.facingLeft = 0;

            if(globalTime % 6 == 0)
            {
                popo.currentSprite++;
                popo.currentSprite %= 4;
            }
        }
        else
        {
            popo.walking = 0;
            popo.currentSprite = 4;
        }
    }

    /*if(!popo.walking)
    {
        if(state[SDL_SCANCODE_SPACE])// && !man->dy)
        {
            if(globalTime % 6 == 0)
            {
                if(popo.currentSprite == 4)
                    popo.currentSprite = 5;
                else
                    popo.currentSprite = 4;


            }

            popo.shooting = 1;
        }
        else
        {
            popo.currentSprite = 4;
            popo.shooting = 0;
        }
    }*/

    if(state[SDL_SCANCODE_UP] && !popo.dy)
    {
        popo.dy = -8;
    }
    if(state[SDL_SCANCODE_DOWN])
    {
        //man->y += 10;
    }

    return done;
}


























//hace la renderizacion, dibuja
void doRender(SDL_Renderer *renderer)
{
    //set the drawing color to blue
    SDL_SetRenderDrawColor(renderer, 0, 0, 255, 255);

    //Clear the screen (to blue)
    SDL_RenderClear(renderer);

    //set the drawing color to white
    SDL_SetRenderDrawColor(renderer, 255, 255, 255, 255);

    //SDL_RenderFillRect(renderer, &rect);
    SDL_RenderCopy(renderer, backgroundTexture, NULL, NULL);

    //warrior
  /*  if(popo.visible)
    {

        SDL_Rect srcRect = { 40+15*popo.currentSprite, 40, 24, 32 };
        SDL_Rect rect = { popo.x, popo.y, 80, 100 };
        SDL_RenderCopyEx(renderer, popo.sheetTexture, &srcRect, &rect, 0, NULL, popo.facingLeft);
    }
*/


    //warrior
    if(popo.visible) {

        if (popo.currentSprite == 4) {
            SDL_Rect srcRect = {10, 40, 24, 32};

            SDL_Rect rect = {popo.x, popo.y, 30, 45};
            SDL_RenderCopyEx(renderer, popo.sheetTexture, &srcRect, &rect, 0, NULL, popo.facingLeft);
        }
        if (popo.currentSprite == 2) {
            SDL_Rect srcRect = {34, 40, 24, 32};

            SDL_Rect rect = {popo.x, popo.y, 30, 45};
            SDL_RenderCopyEx(renderer, popo.sheetTexture, &srcRect, &rect, 0, NULL, popo.facingLeft);
        }
        if (popo.currentSprite == 3) {
            SDL_Rect srcRect = {63, 40, 24, 32};

            SDL_Rect rect = {popo.x, popo.y, 30, 45};
            SDL_RenderCopyEx(renderer, popo.sheetTexture, &srcRect, &rect, 0, NULL, popo.facingLeft);
        }
        if (popo.currentSprite == 1) {
            SDL_Rect srcRect = {90, 40, 24, 32};

            SDL_Rect rect = {popo.x, popo.y, 30, 45};
            SDL_RenderCopyEx(renderer, popo.sheetTexture, &srcRect, &rect, 0, NULL, popo.facingLeft);
        }
        if (popo.currentSprite == 5) {
            SDL_Rect srcRect = {117, 40, 24, 32};

            SDL_Rect rect = {popo.x, popo.y, 30, 45};
            SDL_RenderCopyEx(renderer, popo.sheetTexture, &srcRect, &rect, 0, NULL, popo.facingLeft);
        }
        if (popo.currentSprite == 6) {
            SDL_Rect srcRect = {143, 40, 24, 32};

            SDL_Rect rect = {popo.x, popo.y, 30, 45};
            SDL_RenderCopyEx(renderer, popo.sheetTexture, &srcRect, &rect, 0, NULL, popo.facingLeft);
        }
    }









    //enemy
    if(foca.visible)
    {
        SDL_Rect eSrcRect = { 14, 125, 24, 22 }; //x y son la posicion en la imagen .png y w h es el tamano que se quiere cortar en la imagen
        SDL_Rect eRect = { foca.x, foca.y, 30, 40 };   //x y son las posiciones donde se va a poner en la pantalla, w h el tamano que quiere del objeto en la pantalla
        SDL_RenderCopyEx(renderer, foca.sheetTexture, &eSrcRect, &eRect, 0, NULL, foca.facingVice);
        if(foca.x<= 0) {
            if (foca.facingLeft == 1) {
                foca.facingLeft = 0;
                foca.x = 1;
            }
        }
        if(foca.x>0){

            if(foca.facingLeft==1)
                foca.x-=1;
        }

        if(foca.x>0){

                if(foca.facingLeft==0)
                    foca.x+=1;
            }
        if(foca.x>=800){


            if(foca.facingLeft==0){
                foca.facingLeft=1;
                foca.x=799;
            }

        }

        if(foca.facingLeft==0) {
            foca.facingVice = 1;
        } else
            foca.facingVice = 0;

    }

    //enemy
    if(oso.visible)
    {
        SDL_Rect eSrcRect = { 14, 125, 24, 22 }; //x y son la posicion en la imagen .png y w h es el tamano que se quiere cortar en la imagen
        SDL_Rect eRect = { foca.x, foca.y, 30, 40 };   //x y son las posiciones donde se va a poner en la pantalla, w h el tamano que quiere del objeto en la pantalla
        SDL_RenderCopyEx(renderer, foca.sheetTexture, &eSrcRect, &eRect, 0, NULL, foca.facingVice);
        if(foca.x<= 0) {
            if (foca.facingLeft == 1) {
                foca.facingLeft = 0;
                foca.x = 1;
            }
        }
        if(foca.x>0){

            if(foca.facingLeft==1)
                foca.x-=1;
        }

        if(foca.x>0){

            if(foca.facingLeft==0)
                foca.x+=1;
        }
        if(foca.x>=800){


            if(foca.facingLeft==0){
                foca.facingLeft=1;
                foca.x=799;
            }

        }

        if(foca.facingLeft==0) {
            foca.facingVice = 1;
        } else
            foca.facingVice = 0;

    }








    //hielos
    int xpos = 100;
    int ypos = 607;
    for(int i=0; i<30; i++) {

        if (hielo.visible) {
            SDL_Rect eSrcRect = {267, 262, 12, 12};
            SDL_Rect eRect = {xpos, ypos, 20, 20};
            SDL_RenderCopyEx(renderer, hielo.sheetTexture, &eSrcRect, &eRect, 0, NULL, hielo.facingLeft);
        }
        xpos += 20;
        //ypos = 400;
    }



    //hacer for con la matriz y pintar  cada dato


    //We are done drawing, "present" or show to the screen what we've drawn
    SDL_RenderPresent(renderer);
}






//para los saltos
void updateLogic()
{
    popo.y += popo.dy;
    popo.dy += 0.5;
    if(popo.y > 710)
    {
        popo.y = 710;
        popo.dy = 0;
    }



    if(popo.alive == 0 && globalTime % 6 == 0)
    {
        if(popo.currentSprite < 6)
            popo.currentSprite = 6;
        else if(popo.currentSprite >= 6)
        {
            popo.currentSprite++;
            if(popo.currentSprite > 7)
            {
                popo.visible = 0;
                popo.currentSprite = 7;
            }
        }
    }





    if(foca.alive == 0 && globalTime % 6 == 0)
    {
        if(foca.currentSprite < 6)
            foca.currentSprite = 6;
        else if(foca.currentSprite >= 6)
        {
            foca.currentSprite++;
            if(foca.currentSprite > 7)
            {
                foca.visible = 0;
                foca.currentSprite = 7;
            }
        }
    }



    globalTime++;
}




void asignacionDeVariablesIniciales(){
    popo.x = 50;
    popo.y = 400;
    popo.currentSprite = 4;
    popo.alive = 1;
    popo.visible = 1;
    popo.facingLeft = 0;



    foca.x = 655;
    foca.y = 715;
    foca.currentSprite = 4;
    foca.alive = 1;
    foca.visible = 1;
    foca.facingLeft = 1;
    foca.contPasos= foca.x;

    oso.x = 655;
    oso.y = 715;
    oso.currentSprite = 4;
    oso.alive = 1;
    oso.visible = 1;
    oso.facingLeft = 1;
    oso.contPasos= oso.x;


    hielo.x = 600;
    hielo.y = 400;
    hielo.currentSprite = 4;
    hielo.alive = 1;
    hielo.visible = 1;
    hielo.facingLeft = 1;

}



size_t getRendererSize(SDL_Renderer  *ptr )
{
    return sizeof( ptr );
}

SDL_Renderer *renderer;                // Declare a renderer
int main(int argc, char *argv[])
{
    SDL_Window *window;                    // Declare a window
    // SDL_Renderer *renderer;                // Declare a renderer

    SDL_Init(SDL_INIT_VIDEO);              // Initialize SDL2


    //funcion donde se adignan las variables iniciales
    asignacionDeVariablesIniciales();









    //Create an application window with the following settings:
    window = SDL_CreateWindow("Game Window",                     // window title
                              SDL_WINDOWPOS_UNDEFINED,           // initial x position
                              SDL_WINDOWPOS_UNDEFINED,           // initial y position
                              800,                              // width, in pixels
                              800,                               // height, in pixels
                              0                                  // flags
    );
    renderer = SDL_CreateRenderer(window, -1, SDL_RENDERER_ACCELERATED);

    SDL_RenderSetLogicalSize(renderer, 800, 800);

    //load the bg
    SDL_Surface *bg = IMG_Load("/home/edu/Desktop/TryGame/background.png");
    //load popo
    SDL_Surface *sheetPopo = IMG_Load("/home/edu/Desktop/TryGame/SpriteSheet.png");
    //load foca
    SDL_Surface *sheetFoca = IMG_Load("/home/edu/Desktop/TryGame/SpriteSheet.png");
    //load hielo
    SDL_Surface *sheetHielo = IMG_Load("/home/edu/Desktop/TryGame/SpriteSheet.png");
    //load oso
    SDL_Surface *sheetOso = IMG_Load("/home/edu/Desktop/TryGame/SpriteSheet.png");


    backgroundTexture = SDL_CreateTextureFromSurface(renderer, bg);
    SDL_FreeSurface(bg);

    popo.sheetTexture = SDL_CreateTextureFromSurface(renderer, sheetPopo);
    SDL_FreeSurface(sheetPopo);

    foca.sheetTexture = SDL_CreateTextureFromSurface(renderer, sheetFoca);
    SDL_FreeSurface(sheetFoca);

    hielo.sheetTexture = SDL_CreateTextureFromSurface(renderer, sheetHielo);
    SDL_FreeSurface(sheetHielo);







    // The window is open: enter program loop (see SDL_PollEvent)
    int done = 0;

    //Event loop
    while(!done)
    {
        //Check for events
        done = processEvents(window);

        //abre el Socket, hace las validaciones
        //socket();


        //Update logic
        updateLogic();

        //Render display
        doRender(renderer);
//        Funcionsocket();
        //cont+=2;
        //don't burn up the CPU
        SDL_Delay(10);
    }



    // Close and destroy the window
    printf("%lu", getRendererSize(renderer));

    SDL_DestroyWindow(window);
    SDL_DestroyRenderer(renderer);
    SDL_DestroyTexture(popo.sheetTexture);
    //SDL_DestroyTexture(oso.sheetTexture);
    SDL_DestroyTexture(backgroundTexture);




    // Clean up
    SDL_Quit();
    return 0;
}

