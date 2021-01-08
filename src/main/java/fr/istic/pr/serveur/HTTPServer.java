package fr.istic.pr.serveur;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Class HTTP server<br>
 * Accept connections on 8080<br>
 * Use handler to answer<br>
 * The goal is to send back the test.html page to the client
 */
public class HTTPServer {

    /**
     * Main<br>
     * Choose a type of handler and wait connections
     *
     * @param args _
     * @throws IOException if socket can't be opened
     */
    public static void main(String[] args) throws Exception {
        //Waiting on port 8080
        int listeningPort = 8080;
        ServerSocket serverSocket = new ServerSocket(listeningPort);

        /* For each client :
            1. accept connection
            2. create ClientHandler
            3. call method handle() on handler
        */
        try {
            System.out.println("Server started.");
            while (true) {

                Executor service = Executors.newFixedThreadPool(4);

                Socket clientSocket = serverSocket.accept();
                System.out.println("Client " + clientSocket.getInetAddress() + " is connected.");
                service.execute(new HTTPHandler(clientSocket));

            }
        } catch (IOException exception) {
            System.out.println("Error :" + exception.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            serverSocket.close();
            System.out.println("Server closed.");

        }


    }
}