



#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdbool.h>
#include "json.c"
#include <json-c/json.h>
#include <json_object_private.h>

#define PORT 8080

char str[4];


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




json_object* createJson(int x1, int y1, int attack1, int vision1, int x2, int y2, int attack2, int vision2) {

    /*Creating a json object*/
    json_object * jobj = json_object_new_object();

/*Creating a json object*/

    json_object * jobj1 = json_object_new_object();

/*Creating a json integer*/

    json_object *jint1 = json_object_new_int(attack1);

    /*Form the json object*/

    json_object_object_add(jobj1,"at", jint1);

    /*Creating a json integer*/

    json_object *jint2 = json_object_new_int(x1);

    /*Form the json object*/

    json_object_object_add(jobj1,"equis", jint1);

    /*Creating a json object*/


    json_object *jint3 = json_object_new_int(y1);

    /*Form the json object*/

    json_object_object_add(jobj1,"ye", jint1);


    /*Creating a json integer*/

    json_object *jint4 = json_object_new_int(vision1);

    /*Form the json object*/

    json_object_object_add(jobj1,"vis", jint1);


    /*Creating a json array*/

    json_object *jarray1 = json_object_new_array();

    /*Creating json strings*/

    json_object *jstring1 = json_object_new_string(json_object_to_json_string(jobj1));

    /*Adding the above created json strings to the array*/

    json_object_array_add(jarray1,jstring1);


    /*Creating a json object*/

    json_object * jobj2 = json_object_new_object();

    /*Creating a json integer*/

    json_object *jint5 = json_object_new_int(attack2);

    /*Form the json object*/

    json_object_object_add(jobj2,"at", jint5);


    /*Creating a json integer*/

    json_object *jint6 = json_object_new_int(x2);

    /*Form the json object*/

    json_object_object_add(jobj2,"equis", jint6);


    /*Creating a json integer*/

    json_object *jint7 = json_object_new_int(y2);

    /*Form the json object*/

    json_object_object_add(jobj2,"ye", jint7);


    /*Creating a json integer*/

    json_object *jint8 = json_object_new_int(vision2);

    /*Form the json object*/

    json_object_object_add(jobj2,"vis", jint8);

    json_object *jarray2 = json_object_new_array();


    /*Creating json strings*/

    json_object *jstring5 = json_object_new_string(json_object_to_json_string(jobj2));

    /*Adding the above created json strings to the array*/

    json_object_array_add(jarray2,jstring5);

    json_object_object_add(jobj, "4", jarray1);
    json_object_object_add(jobj, "5", jarray2);

    return jobj;
}





void main(){

    json_object* jobj = createJson( 1, 0, 0, 1,1, 0, 0, 1);



    int clientSocket;
    struct sockaddr_in serverAddr;
    char buffer[1000000];

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

        char sendbuffer[100];   // array to hold the result.
        strcpy(sendbuffer,json_object_to_json_string_ext(jobj,JSON_C_TO_STRING_PLAIN )); // copy string one into the result.
        strcat(sendbuffer,"\r\n");
        removeSpaces(sendbuffer);
        removeBackSlash(sendbuffer);



        // send_all_data function sends al data available
        int was_it_all = send(clientSocket, sendbuffer, 1024, 0);


        //recv recives all data from client
        recv(clientSocket, buffer, 1024, 0);
        printf("[+]Data Recv: %s\n", buffer);

        loop ++;
    }


    printf("[+]Closing the connection.\n");

}



