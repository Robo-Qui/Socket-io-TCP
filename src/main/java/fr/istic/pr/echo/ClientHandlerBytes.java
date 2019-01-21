
package fr.istic.pr.echo;

import java.net.Socket;

public class ClientHandlerBytes implements ClientHandler {

    private Socket socket;

    public ClientHandlerBytes(Socket socket){
        this.socket = socket;
    }

    public void handle() {
        //Lecture du message dans un buffer de bytes
        //Envoie du buffer de bytes.
    }
}
