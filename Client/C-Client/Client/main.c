#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdbool.h>
#include "cJSON.h"

#define PORT 8080

char str[4];

/**
 *
 * @param str
 */
void removeSpaces(char *str)
{
    // To keep track of non-space character count
    int count = 0;

    // Traverse the given string. If current character
    // is not space, then place it at index 'count++'
    for (int i = 0; str[i]; i++)
        if (str[i] != ' ')
            str[count++] = str[i]; // here count is
    // incremented
    str[count] = '\0';
}

/**
 *
 * @param str
 */
void removeBackSlash(char *str)
{
    // To keep track of non-space character count
    int count = 0;

    // Traverse the given string. If current character
    // is not space, then place it at index 'count++'
    for (int i = 0; str[i]; i++)
        if (str[i] != '\\')
            str[count++] = str[i]; // here count is
    // incremented
    str[count] = '\0';
}

/**
 *
 * @param x1
 * @param y1
 * @param att1
 * @param vis1
 * @param x2
 * @param y2
 * @param att2
 * @param vis2
 * @return
 */
char* generateJson(int x1, int y1, int att1, int vis1, int x2, int y2, int att2, int vis2) {
    char* out;
    cJSON* root;
    cJSON* nana;
    cJSON* popo;
    cJSON* array4;
    cJSON* array5;
    root = cJSON_CreateObject();
    array4 = cJSON_CreateArray();
    array5 = cJSON_CreateArray();
    cJSON_AddItemToObject(root, "4", array4);
    cJSON_AddItemToObject(root, "5", array5);
    cJSON_AddItemToArray(array4, popo = cJSON_CreateObject());
    cJSON_AddItemToObject(popo, "att", cJSON_CreateNumber(att1));
    cJSON_AddItemToObject(popo, "equis", cJSON_CreateNumber(x1));
    cJSON_AddItemToObject(popo, "ye", cJSON_CreateNumber(y1));
    cJSON_AddItemToObject(popo, "vis", cJSON_CreateNumber(vis1));
    cJSON_AddItemToArray(array5, nana = cJSON_CreateObject());
    cJSON_AddItemToObject(nana, "att", cJSON_CreateNumber(att2));
    cJSON_AddItemToObject(nana, "equis", cJSON_CreateNumber(x2));
    cJSON_AddItemToObject(nana, "ye", cJSON_CreateNumber(y2));
    cJSON_AddItemToObject(nana, "vis", cJSON_CreateNumber(vis2));
    out = cJSON_Print(root);
    cJSON_Delete(root);
    return out;
}

/**
 *
 * @param in
 * @param key
 * @return
 */
int* getPos(char* in, char* key) {
    cJSON* root = cJSON_Parse(in);
    cJSON* x;
    cJSON* y;
    static int arr[2];
    cJSON* item = cJSON_GetObjectItem(root, key);
    cJSON* subitem = cJSON_GetArrayItem(item, 0);
    x = cJSON_GetObjectItem(subitem, "equis");
    y = cJSON_GetObjectItem(subitem, "ye");
    arr[0] = x->valueint;
    arr[1] = y->valueint;
    cJSON_Delete(root);
    return arr;
}

/**
 *
 * @param in
 * @param x
 * @param y
 * @return
 */
int getEle(char* in, int x, int y) {
int res;
cJSON* root = cJSON_Parse(in);
cJSON* cx;
cJSON* cy;
cJSON* item = cJSON_GetObjectItem(root, "4");
cJSON* subitem = cJSON_GetArrayItem(item, 0);
cx = cJSON_GetObjectItem(subitem, "equis");
cy = cJSON_GetObjectItem(subitem, "ye");
if (x == cx->valueint && y == cy->valueint) {
    res = strtol("4", NULL, 0);
    return res;
} else {
    return 0;
}
}

void main(){




int clientSocket;
struct sockaddr_in serverAddr;
char buffer[1025];

clientSocket = socket(PF_INET, SOCK_STREAM, 0);
printf("[+]Client Socket Created Sucessfully.\n");

memset(&serverAddr, '\0', sizeof(serverAddr));
serverAddr.sin_family = AF_INET;
serverAddr.sin_port = htons(PORT);
serverAddr.sin_addr.s_addr = inet_addr("127.0.0.1");

connect(clientSocket, (struct sockaddr*)&serverAddr, sizeof(serverAddr));
printf("[+]Connected to Server.\n");

int loop = 0;

while (loop != 4) {
    const char* jobj = generateJson( 0, 0, 0, 0,0, 0, 0, 0);



    // send_all_data function sends al data available
    int was_it_all = send(clientSocket, jobj, 86, 0);


    //recv recives all data from client
    recv(clientSocket, buffer, 1025, 0);
    printf("[+]Data Recv: %s\n",buffer );


    loop ++;
}


printf("[+]Closing the connection.\n");

}
