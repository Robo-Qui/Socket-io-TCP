package fr.istic.pr.echomt;

import fr.istic.pr.echo.ClientHandler;

import java.io.*;
import java.net.Socket;

/**
 * Handle client with a BufferedReader & PrintWriter
 */
public class ClientHandlerCharMT implements ClientHandler, Runnable {

    private final Socket socket;

    /**
     * Constructor
     *
     * @param socket, the client socket
     */
    public ClientHandlerCharMT(Socket socket) {
        this.socket = socket;
    }


    @Override
    public void handle() {
        try {
            System.out.println("Handler started for " + this.socket.getInetAddress() + ".");

            //Init input
            InputStream in = this.socket.getInputStream();
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

            //Init line buffer
            String thisLine;

            //Init output
            OutputStream out = this.socket.getOutputStream();
            PrintWriter printWriter = new PrintWriter(out);

            while ((thisLine = bufferedReader.readLine()) != null) {
                //Log
                printWriter.println(thisLine);
                System.out.println("Handler for " + this.socket.getInetAddress() + " wrote " + thisLine);

                //Send line
                printWriter.flush();
            }

            this.socket.close();
            System.out.println("Client " + this.socket.getInetAddress() + " is disconnected");


        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }


    }

    @Override
    public void run() {
        handle();
    }
}
