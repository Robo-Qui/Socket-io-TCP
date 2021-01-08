package fr.istic.pr.echomt;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * Class Echo server<br>
 * Accept connections on 8080<br>
 * Use handler to answer<br>
 * The goal is to send back the message to the client (echo)
 *
 * @see ClientHandlerBytesMT
 * @see ClientHandlerCharMT
 */
public class EchoServerMT {

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

        //Ask for ClientHandlerBytes or ClientHandlerChar
        Scanner sc = new Scanner(System.in);
        String sb = "Choose one :\n" +
                "ClientHandlerBytesMT\t:\t0\n" +
                "ClientHandlerCharMT\t:\t1\n";
        System.out.println(sb);
        int choice = sc.nextInt();
        if (choice != 0 && choice != 1) {
            throw new Exception("This handler doesn't exist.");
        }

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

                switch (choice) {
                    case 0:
                        service.execute(new ClientHandlerBytesMT(clientSocket));
                        break;
                    case 1:
                        service.execute(new ClientHandlerCharMT(clientSocket));
                        break;
                    default:
                        throw new Exception("Error picking handler.");

                }

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