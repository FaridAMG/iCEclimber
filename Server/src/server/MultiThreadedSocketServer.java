package server;
import server.ClientServiceThread;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import Json.ClientJsonReader;
import Json.JsonUtil;
import Json.coordinates;
import game.IceClimberGame;
 
/**
 * 
 */
public class MultiThreadedSocketServer extends  GlobalServerConstants   {
 
    ServerSocket myServerSocket;
 
    /**
     * 
     */
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
    /**
     * 
     * @param args
     */
    public static void main (String[] args) 
    { 
        
		
		  IceClimberGame game = new IceClimberGame(20,20);
		  
		  game.JsonGameMatrix.ConsolePrintGameMatrix();
		  
		  game.moveNPCs();
		  
		  game.JsonGameMatrix.ConsolePrintGameMatrix();
		 

    	
    	//new MultiThreadedSocketServer();  
    	
    } 
}
    
    







//
// PRUEBAS DE LOS LECTORES Y ESCRITORES DE JSON
//


//JsonUtil jsut = new JsonUtil(20,20);
//jsut.ConsolePrintGameMatrix();
//jsut.ChangeElementInPos(10, 5, 3);
//jsut.ChangeElementInPos(9, 7, 3);
//List<coordinates> ele = jsut.GetCoordinatesOfElements(0);
//ele.forEach((coor) -> {

//	ClientJsonReader jsonrdr = new ClientJsonReader();
//	jsonrdr.insertClientMessage("{\n" + 
//			"\n" + 
//			"	\"4\": [{\n" + 
//			"		\"at\": 1,\n" + 
//			"		\"equis\": 1,\n" + 
//			"		\"ye\": -1,\n" + 
//			"		\"vis\": -1\n" + 
//			"	}],\n" + 
//			"\n" + 
//			"	\"5\": [{\n" + 
//			"		\"at\": 0,\n" + 
//			"		\"equis\": -1,\n" + 
//			"		\"ye\": -1,\n" + 
//			"		\"vis\": -1\n" + 
//			"	}]\n" + 
//			"\n" + 
//			"\n" + 
//			"}");
//	
//	System.out.println("Popo atack..:  " + jsonrdr.seeAtack(4));
//	System.out.println("Nana atack...:  " + jsonrdr.seeAtack(5));
	
//	System.out.println("Popo x Mov...:  " + jsonrdr.seeXmovment(4));
//	System.out.println("Nana x Mov  " + jsonrdr.seeXmovment(5));
//	
//	System.out.println("Popo y Mov...:  " + jsonrdr.seeYmovment(4));
//	System.out.println("Nana y Mov...:  " + jsonrdr.seeYmovment(5));
//	
//	System.out.println("Popo Vision...:  " + jsonrdr.seeVision(4));
//	System.out.println("Nana Vision...:  " + jsonrdr.seeVision(5));
	
//});
//jsut.ConsolePrintGameMatrix();

    
