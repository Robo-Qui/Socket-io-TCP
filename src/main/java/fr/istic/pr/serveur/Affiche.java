package fr.istic.pr.serveur;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

/**
 * Affiche class<bR>
 * Print the test.html file
 */
public class Affiche {
    /**
     * Main
     *
     * @param args _
     * @throws IOException, exceptions for FileReader
     */
    public static void main(String[] args) throws IOException {
        //Prepare file
        String path = "test.html";
        File f = new File("./www/" + path);

        if (f.exists() && !f.isDirectory()) {
            System.out.println(f.getAbsolutePath());
            System.out.println(f.exists());

            //Read
            BufferedReader fin = new BufferedReader(new FileReader(f));
            String line;
            while ((line = fin.readLine()) != null) {
                System.out.println(line);
            }

            fin.close();
        }
    }
}