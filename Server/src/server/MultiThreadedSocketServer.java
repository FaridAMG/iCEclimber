package server;
import server.ClientServiceThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import Json.JsonUtil;
 
 
public class MultiThreadedSocketServer extends  GlobalServerConstants   {
 
    ServerSocket myServerSocket;
 
 
    public MultiThreadedSocketServer() 
    { 
        try
        { 
            myServerSocket = new ServerSocket(8080); 
        } 
        catch(IOException ioe) 
        { 
            System.out.println("Could not create server socket on port 8080. Quitting."); 
            System.exit(-1); 
        } 
 
 
 
 
        Calendar now = Calendar.getInstance();
        SimpleDateFormat formatter = new SimpleDateFormat("E yyyy.MM.dd 'at' hh:mm:ss a zzz");
        System.out.println("It is now : " + formatter.format(now.getTime()));
 
 
 
 
        // Successfully created Server Socket. Now wait for connections. 
        while(super.ServerOn) 
        {    
            System.out.println("Server cycle is running..."); 

            try
            { 
                // Accept incoming connections. 
                Socket clientSocket = myServerSocket.accept(); 
 
                // accept() will block until a client connects to the server. 
                // If execution reaches this point, then it means that a client 
                // socket has been accepted. 
 
                // For each client, we will start a service thread to 
                // service the client requests. This is to demonstrate a 
                // MultiThreaded server. Starting a thread also lets our 
                // MultiThreadedSocketServer accept multiple connections simultaneously. 
 
                // Start a Service thread 
 
                ClientServiceThread cliThread = new ClientServiceThread(clientSocket);
                cliThread.start();
                System.out.println("Yay!!! We have a new friend!!!"); 
 
            }       
            catch(IOException ioe) 
            { 
                System.out.println("Exception encountered on accept. Ignoring. Stack Trace :"); 
                ioe.printStackTrace(); 
            } 
 
        }
 
        try
        { 
            myServerSocket.close(); 
            System.out.println("Server Stopped"); 
        } 
        catch(Exception ioe) 
        { 
            System.out.println("Problem stopping server socket"); 
            System.exit(-1); 
        } 
 
 
 
    } 
 
    public static void main (String[] args) 
    { 
        
    	JsonUtil jsut = new JsonUtil(20,20);
    	jsut.ConsolePrintGameMatrix();
    	//new MultiThreadedSocketServer();        
    } 
}
    
    
    
