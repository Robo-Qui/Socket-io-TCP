package fr.istic.pr.serveur;

/*imports*/

import fr.istic.pr.echo.ClientHandler;

import java.io.PrintWriter;
import java.net.Socket;

public class HTTPHandler implements ClientHandler, Runnable {

    private Socket socket;

    /**
     * Constructor
     *
     * @param socket, the client's socket
     */
    public HTTPHandler(Socket socket) {
        this.socket = socket;
    }

    @Override
    public void handle() {
        // Crée les print writer et buffered reader

        // lit l'entête de la requête
        // Appelle doGet si la méthode est une méthode GET.
    }

    public void doGet(String pagepath, PrintWriter out) {
        //Vérifie que le fichier existe

        // si le fichier existe :
        //Ecrit l'en-tête de réponse (Code, Content-type, Connexion, ...)
        //Ecrit le contenu du fichier si il existe
        // sinon
        // appelle la méthode send404.
    }

    public void send404(PrintWriter out) {
        //Envoie une réponse 404 si le fichier n'existe pas.
    }

    public void doError(PrintWriter out) {
        //Envoie une réponse avec le code 405 : Method Not Allowed
    }

    @Override
    public void run() {
        
    }
}