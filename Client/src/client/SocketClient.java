package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;
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
     // Using Scanner for Getting Input from User 
        Scanner personIn = new Scanner(System.in);
        while(true){
        	
        	try {
        	
        	//Gets input from user...:
            System.out.println("Speak...:");
        	String personInStr = personIn.nextLine(); 
                
            //write to socket using ObjectOutputStream
            out = new PrintWriter(new OutputStreamWriter(myClientSocket.getOutputStream())); 
            out.println(personInStr); 
            out.flush(); 
             
             
            //read the server response message
         	in = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream())); 
         	String serverResponse = (String) in.readLine(); 
            System.out.println(serverResponse);
            
        	}
            catch(IOException ioe) 
        	{   
            in.close(); 
            out.close(); 
            personIn.close();
            myClientSocket.close(); 
            System.out.println("...Im done, good bye user. ");
            Thread.sleep(100);
        	}
            
        }
    }
}






