package fr.istic.pr.serveur;

/*imports*/

import fr.istic.pr.echo.ClientHandler;

import java.io.*;
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

        InputStream in = null;
        BufferedReader bufferedReader = null;
        OutputStream out = null;
        PrintWriter printWriter = null;
        try {
            //Init in
            in = this.socket.getInputStream();
            bufferedReader = new BufferedReader(new InputStreamReader(in));

            //Init out
            out = this.socket.getOutputStream();
            printWriter = new PrintWriter(new OutputStreamWriter(out));


            // lit l'entête de la requête
            String buffer;
            if (bufferedReader.ready()) {
                buffer = bufferedReader.readLine();

                String[] firstLine = buffer.split(" ");
                String method = firstLine[0];
                String pagePath = firstLine[1];
                // Appelle doGet si la méthode est une méthode GET.
                if (method.equals("GET")) {
                    this.doGet(pagePath, printWriter);
                } else {
                    this.doError(printWriter);
                }
            }


            this.socket.close();
            System.out.println(this.socket.getInetAddress() + " : Disconnected.");
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void doGet(String pagepath, PrintWriter out) throws IOException {
        //Vérifie que le fichier existe
        //Prepare file
        String path = pagepath.substring(1);
        File f = new File("./www/" + path);

        // si le fichier existe :
        if (f.exists() && !f.isDirectory()) {
            System.out.println(this.socket.getInetAddress() + " : Get file " + f.getAbsolutePath());
            System.out.println(this.socket.getInetAddress() + " : File exist = " + f.exists());

            //Ecrit l'en-tête de réponse (Code, Content-type, Connexion, ...)
            /// Send headers
            out.println("HTTP/1.1 200 OK");
            out.println("Content-Type: text/html; charset=UTF-8");
            out.println("Connection: close");
            out.println();

            //Ecrit le contenu du fichier
            //Append page
            BufferedReader fin = new BufferedReader(new FileReader(f));
            String line;
            while ((line = fin.readLine()) != null) {
                out.println(line);
            }

            System.out.println(this.socket.getInetAddress() + " : Page " + pagepath + " served.");

        } else {
            // sinon
            // appelle la méthode send404.
            this.send404(out);
        }

        //Send
        out.flush();

    }

    public void send404(PrintWriter out) {
        //Envoie une réponse 404 si le fichier n'existe pas.
        out.println("HTTP/1.1 404 Not Found");
        out.println("Content-Type: text/html; charset=UTF-8");
        out.println("Connection: close");
        out.println();
        System.out.println(this.socket.getInetAddress() + " : 404 served.");
    }

    public void doError(PrintWriter out) {
        //Envoie une réponse avec le code 405 : Method Not Allowed
        out.println("HTTP/1.1 405 Method Not Allowed");
        out.println("Content-Type: text/html; charset=UTF-8");
        out.println("Connection: close");
        out.println();
        System.out.println(this.socket.getInetAddress() + " : 405 served.");
    }

    @Override
    public void run() {
        handle();
    }
}