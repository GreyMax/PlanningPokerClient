package planning.poker.client;

import planning.poker.Estimate;

import java.io.*;
import java.net.*;

public class Requester {

    private Socket requestSocket;
    private ObjectOutputStream out;
    private ObjectInputStream in;
    private String message;
    private boolean isRunning = true;

    public void run()
    {
        try{
            //1. creating a socket to connect to the server
            requestSocket = new Socket("localhost", 9999);
            System.out.println("Connected to localhost in port 9999");
            //2. get Input and Output streams
            out = new ObjectOutputStream(requestSocket.getOutputStream());
            out.flush();
            in = new ObjectInputStream(requestSocket.getInputStream());
            //3: Communicating with the server
            do{
                try {
                    message = (String)in.readObject();
                    System.out.println("server>" + message);
//                    sendMessage("Hi my server");
//                    message = "bye";
//                    sendMessage(message);
                }
                catch(ClassNotFoundException classNot){
                    System.err.println("data received in unknown format");
                }
            } while(isRunning);
        }
        catch(UnknownHostException unknownHost){
            System.err.println("You are trying to connect to an unknown host!");
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
        finally{
            //4: Closing connection
            try{
                in.close();
                out.close();
                requestSocket.close();
            }
            catch(IOException ioException){
                ioException.printStackTrace();
            }
        }
    }

    public void sendEstimate(Estimate estimate)
    {
        try{
            out.writeObject(estimate);
            out.flush();
            System.out.println("client>" + estimate);
        }
        catch(IOException ioException){
            ioException.printStackTrace();
        }
    }

    public void stopClient() {
        isRunning = false;
    }

//    public static void main(String args[])
//    {
//        Requester client = new Requester();
//        client.run();
//    }
}