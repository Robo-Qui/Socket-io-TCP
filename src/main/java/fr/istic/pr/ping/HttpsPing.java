package fr.istic.pr.ping;

import javax.net.ssl.SSLSocketFactory;
import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;

/**
 * Class HttpsPing<br>
 * Show :<br>
 * - Response time<br>
 * - Response Code<br>
 * - Body
 */
public class HttpsPing {

    /**
     * Inner class, contains information of the response
     */
    public static class PingInfo {
        /**
         * Response time
         */
        long time;
        /**
         * Response code
         */
        int code;

        @Override
        public String toString() {
            return String.format("[code %d -- %d ms]", code, time);
        }
    }

    /**
     * Main
     *
     * @param args _
     * @throws UnknownHostException, host is not found
     * @throws IOException,          exception for ping() function
     */
    public static void main(String[] args) throws UnknownHostException, IOException {
        System.out.println(ping("www.google.fr", 443));
    }

    /**
     * ping method, ping a specific host/port
     *
     * @param host, host address
     * @param port, host port
     * @return PingInfo, object that contains info about the response
     * @throws UnknownHostException, host not found
     * @throws IOException,          exception for Socket
     */
    public static PingInfo ping(String host, int port) throws UnknownHostException, IOException {
        //Create object to return
        PingInfo info = new PingInfo();

        //Start timer
        long time = System.currentTimeMillis();

        //Create socket to connect to host
        SSLSocketFactory factory = (SSLSocketFactory) SSLSocketFactory.getDefault();
        Socket socket = factory.createSocket(host, port);

        // USE PrintWriter and BufferedReader
        OutputStream out = socket.getOutputStream();
        PrintWriter printWriter = new PrintWriter(new OutputStreamWriter(out, StandardCharsets.UTF_8));
        printWriter.println();
        /// Send headers
        printWriter.println("GET / HTTP/1.1");
        // Also put host
        printWriter.println("Host : " + host);
        // Header should also contains this to ask the website to close connection after response
        printWriter.println("Connection: close");
        // end with empty line
        printWriter.println();
        //Send
        printWriter.flush();

        // Read response
        InputStream in = socket.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(in));

        info.code = Integer.parseInt(bufferedReader.readLine().split(" ")[1]);

        String buffer = " ";
        //Skip headers
        while (bufferedReader.ready() && !buffer.equals("")) {
            buffer = bufferedReader.readLine();
        }

        //Print page
        while (bufferedReader.ready()) {
            buffer = bufferedReader.readLine();
            System.out.println(buffer);
        }

        info.time = System.currentTimeMillis() - time;
        return info;
    }

}
