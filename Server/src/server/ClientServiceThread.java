package server;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;




class ClientServiceThread extends GlobalServerConstants
{ 
    Socket myClientSocket;
    boolean m_bRunThread = true; 

    public ClientServiceThread() 
    { 
        super(); 
    } 

    ClientServiceThread(Socket s) 
    { 
        myClientSocket = s; 

    } 

    public void run() 
    {            
        // Obtain the input stream and the output stream for the socket 
        // A good practice is to encapsulate them with a BufferedReader 
        // and a PrintWriter as shown below. 
        BufferedReader in = null; 
        PrintWriter out = null; 

        // Print out details of this connection 
        System.out.println("Accepted Client Address - " + myClientSocket.getInetAddress().getHostName()); 

        try
        {                                
            in = new BufferedReader(new InputStreamReader(myClientSocket.getInputStream())); 
            out = new PrintWriter(new OutputStreamWriter(myClientSocket.getOutputStream())); 

            // At this point, we can read for input and reply with appropriate output. 

            // Run in a loop until m_bRunThread is set to false 
            while(m_bRunThread) 
            {                    
                // read incoming stream 
                String clientCommand = (String) in.readLine(); 
                System.out.println("Client Says :" + clientCommand);

                if(!super.ServerOn) 
                { 
                    // Special command. Quit this thread 
                    System.out.print("Server has already stopped"); 
                    out.println("Server has already stopped"); 
                    out.flush(); 
                    m_bRunThread = false;   

                } 

                if(clientCommand.equalsIgnoreCase("quit")) { 
                    // Special command. Quit this thread 
                    m_bRunThread = false;   
                    System.out.print("Stopping client thread for client : "); 
                } else if(clientCommand.equalsIgnoreCase("end")) { 
                    // Special command. Quit this thread and Stop the Server
                    m_bRunThread = false;   
                    System.out.print("Stopping client thread for client : "); 
                    super.ServerOn = false;
                } else {
                        // Process it 
                        out.println("Server Says : " + "Hello, I am server... "); 
                        out.flush(); 
                }
            } 
        } 
        catch(Exception e) 
        { 
            e.printStackTrace(); 
        } 
        finally
        { 
            // Clean up 
            try
            {                    
                in.close(); 
                out.close(); 
                myClientSocket.close(); 
                System.out.println("...Stopped"); 
            } 
            catch(IOException ioe) 
            { 
                ioe.printStackTrace(); 
            } 
        } 
    } 


} 




