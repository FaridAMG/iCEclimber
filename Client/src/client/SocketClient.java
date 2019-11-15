package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;


/**
 * This class implements java socket client and was retrieved from: 
 * https://www.journaldev.com/741/java-socket-programming-server-client
 * it was first created by:
 * @author pankaj
 *
 */
public class SocketClient {
	
    BufferedReader in = null; 
    PrintWriter out = null; 
    static Socket myClientSocket = null;


    public static void main(String[] args) throws UnknownHostException, IOException, ClassNotFoundException, InterruptedException{
        //get the localhost IP address, if server is running on some other IP, you need to use that
        InetAddress host = InetAddress.getLocalHost();
        //establish socket connection to server
        myClientSocket = new Socket(host.getHostName(), 8080);
        BufferedReader in = null; 
        PrintWriter out = null; 
        while(true){
                
            //write to socket using ObjectOutputStream
            out = new PrintWriter(new OutputStreamWriter(myClientSocket.getOutputStream())); 
            out.println("Client sends...: " + "Hello my frind I am the client"); 
            out.flush(); 
             
             
            //read the server response message
         	in = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream())); 
         	String serverResponse = (String) in.readLine(); 
            System.out.println("Server Says :" + serverResponse);
            
            
            
            in.close(); 
            out.close(); 
            myClientSocket.close(); 
            System.out.println("...Im done, good bye user. ");
            Thread.sleep(100);
            
        }
    }
}






