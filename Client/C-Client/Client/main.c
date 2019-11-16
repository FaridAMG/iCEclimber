



#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <sys/types.h>
#include <netinet/in.h>
#include <arpa/inet.h>
#include <stdbool.h>

#define PORT 8080

void main(){

    int clientSocket;
    struct sockaddr_in serverAddr;
    char buffer[1024];
    char sendbuffer[1024] = "Hello from client \r\n";

    clientSocket = socket(PF_INET, SOCK_STREAM, 0);
    printf("[+]Client Socket Created Sucessfully.\n");

    memset(&serverAddr, '\0', sizeof(serverAddr));
    serverAddr.sin_family = AF_INET;
    serverAddr.sin_port = htons(PORT);
    serverAddr.sin_addr.s_addr = inet_addr("127.0.0.1");

    connect(clientSocket, (struct sockaddr*)&serverAddr, sizeof(serverAddr));
    printf("[+]Connected to Server.\n");


    int was_it_all = send_all_data(clientSocket,sendbuffer,1024);
    if(was_it_all == 1){
        printf("[+] ALL DATA HAS BEEN SENT");
    } else{
        printf("[+] NOT ALL DATA HAS BEEN SENT");
    }


    recv(clientSocket, buffer, 1024, 0);
    printf("[+]Data Recv: %s\n", buffer);
    printf("[+]Closing the connection.\n");

}


int send_all_data(int socket, void *buffer, size_t length){
    char *ptr = (char*) buffer;
    while (length > 0)
    {
        int i = send(socket, ptr, length, 0);
        if (i < 1) return 0;
        ptr += i;
        length -= i;
    }
    return 1;
}