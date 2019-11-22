



#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdbool.h>
#include <json-c/json_object.h>
#include "json.c"

#define PORT 8080


void main(){

    json_object* jobj = createNewJson(4, 1, 0, 0, 1);
    printf("The JSON created is: %s\n", json_object_to_json_string(jobj));



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



