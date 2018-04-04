package data;


import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

/**
 * Cette classe lit les fichiers permettant de parametrer une partie de PacMan
 */
public class FileReader {

    /**
     * Le nom du fichier paramètre
     */
    private String fileName;

    /**
     * Initialise les attribut de classe
     * @param fileName le nom du fichier paramètre
     * @pre fileName != null
     */
    public FileReader(String fileName) {
        if (fileName != null) {
            this.fileName = fileName;
        }
    }

    /**
     * Construit un objet game à partir du fichier des paramètres
     * @return Un objet game initialisé avec les paramètre du fichier correspondant
     * @post game != null
     */
    public GameParam initGame(int gameLevel) {
        GameParam ret = null;
        try (BufferedReader br = Files.newBufferedReader(Paths.get(fileName))) {

        } catch (IOException e) {
            e.printStackTrace();
        }
        return ret;
    }
}
