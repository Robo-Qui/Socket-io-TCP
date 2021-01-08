package fr.istic.pr.echomt;

import fr.istic.pr.echo.ClientHandler;

import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Handle client with a fixed byteBuffer
 */
public class ClientHandlerBytesMT implements ClientHandler, Runnable {

    private final Socket socket;

    /**
     * Constructor
     *
     * @param socket, the client socket
     */
    public ClientHandlerBytesMT(Socket socket) {
        this.socket = socket;
    }

    public void handle() {
        try {
            //Init input
            System.out.println("Handler started for " + this.socket.getInetAddress() + ".");
            InputStream in = this.socket.getInputStream();

            //Init buffer
            byte[] buffer = new byte[8];

            //Init output
            OutputStream out = this.socket.getOutputStream();

            //Read message in byte buffer
            while (in.read(buffer) != -1) {
                //Send buffer
                out.write(buffer);

                //Print in console for log
                String s = new String(buffer, StandardCharsets.UTF_8);
                System.out.println("Handler for " + this.socket.getInetAddress() + " wrote " + s);

                //Empty buffer for next loop
                Arrays.fill(buffer, (byte) 0);
                //out.flush();
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
