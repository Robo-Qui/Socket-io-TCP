package fr.istic.pr.echo;

import java.io.*;
import java.net.Socket;

/**
 * Handle client with a BufferedReader & PrintWriter
 */
public class ClientHandlerChar implements ClientHandler {

    private final Socket socket;

    /**
     * Constructor
     *
     * @param socket, the client socket
     */
    public ClientHandlerChar(Socket socket) {
        this.socket = socket;
    }

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

        } catch (Exception e) {
            System.out.println("Error :" + e.getMessage());
        }


    }
}
