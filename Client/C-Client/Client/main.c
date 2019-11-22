



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

#define PORT 8080

char str[4];

json_object* createJson(int player, int x, int y, int attack, int vision) {

    char formatedPlayer[4];

    snprintf(str, player, "%s", formatedPlayer);


    /*Creating a json object*/
    json_object * jobj1 = json_object_new_object();
    /*Creating a json integer*/
    json_object *jint1 = json_object_new_int(attack);
    /*Form the json object*/
    json_object_object_add(jobj1,"at", jint1);
/*Creating a json object*/
    json_object * jobj2 = json_object_new_object();
    /*Creating a json integer*/
    json_object *jint2 = json_object_new_int(x);
    /*Form the json object*/
    json_object_object_add(jobj2,"equis", jint2);
/*Creating a json object*/
    json_object * jobj3 = json_object_new_object();
    /*Creating a json integer*/
    json_object *jint3 = json_object_new_int(y);
    /*Form the json object*/
    json_object_object_add(jobj3,"ye", jint3);
/*Creating a json object*/
    json_object * jobj4 = json_object_new_object();
    /*Creating a json integer*/
    json_object *jint4 = json_object_new_int(vision);
    /*Form the json object*/
    json_object_object_add(jobj4,"vis", jint4);
    /*Creating a json object*/
    json_object * jobj = json_object_new_object();
    /*Creating a json array*/
    json_object *jarray = json_object_new_array();
    /*Creating json strings*/
    json_object *jstring1 = json_object_new_string(json_object_to_json_string(jobj1));
    json_object *jstring2 = json_object_new_string(json_object_to_json_string(jobj2));
    json_object *jstring3 = json_object_new_string(json_object_to_json_string(jobj3));
    json_object *jstring4 = json_object_new_string(json_object_to_json_string(jobj4));
    /*Adding the above created json strings to the array*/
    json_object_array_add(jarray,jstring1);
    json_object_array_add(jarray,jstring2);
    json_object_array_add(jarray,jstring3);
    json_object_array_add(jarray,jstring4);
    /*Form the json object*/
    json_object_object_add(jobj, formatedPlayer, jarray);


    return jobj;

}

void main(){

    json_object* jobj = createJson(4, 1, 0, 0, 1);
    printf("The JSON created is: %s\n", json_object_to_json_string_ext(jobj,JSON_C_TO_STRING_PLAIN ));



   /* int clientSocket;
    struct sockaddr_in serverAddr;
    char buffer[1000000];
    char sendbuffer[1000000] = "Hello from client \r\n";

    clientSocket = socket(PF_INET, SOCK_STREAM, 0);
    printf("[+]Client Socket Created Sucessfully.\n");

    memset(&serverAddr, '\0', sizeof(serverAddr));
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(PORT);
    serverAddr.sin_addr.s_addr = inet_addr("127.0.0.1");

    connect(clientSocket, (struct sockaddr*)&serverAddr, sizeof(serverAddr));
    printf("[+]Connected to Server.\n");


    while (sendbuffer != "close") {

        // gets info from user
        printf("Enter your message to server : ");
        scanf("%s", sendbuffer);

        // send_all_data function sends al data available
        int was_it_all = send(clientSocket, sendbuffer, 1024, 0);


        //recv recives all data from client
        recv(clientSocket, buffer, 1024, 0);
        printf("[+]Data Recv: %s\n", buffer);

    }


    printf("[+]Closing the connection.\n");
*/
}



