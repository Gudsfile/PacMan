package data;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import java.io.FileWriter;

/**
 * Cette classe écrit dans un fichier JSON les scores des joueurs
 */
public class ScoreWritter {

    /**
     * L'emplacement du fichier des scores
     */
    private static final String FILENAME = "res/scores.json";

    /**
     * Méthode permettant d'écrire un nouveau score dans le fichier scores.json
     * La donnée "Highscore" est réécrite si le nouveau score est supérieur
     *
     * @param name  le nom du joueur
     * @param score le score du joueur
     * @pre name != null
     * @pre {@code score >= 0}
     */
    public static void writeScore(String name, int score) {
        if (name != null && score >= 0) {
            JSONParser parser = new JSONParser();
            try {
                JSONObject scoresJSON = (JSONObject) parser.parse(new java.io.FileReader(FILENAME));
                JSONArray scoreList = (JSONArray) scoresJSON.get("Scores");
                JSONObject newScoreJson = new JSONObject();
                long highscore = (long) scoresJSON.get("Highscore");

                JSONObject scoreToAdd = new JSONObject();
                scoreToAdd.put("Name", name);
                scoreToAdd.put("Score", score);
                scoreList.add(scoreToAdd);

                if (score > highscore) {
                    newScoreJson.put("Highscore", score);
                } else {
                    newScoreJson.put("Highscore", highscore);
                }
                newScoreJson.put("Scores", scoreList);

                FileWriter fileWriter = new FileWriter(FILENAME);
                fileWriter.write(new GsonBuilder().setPrettyPrinting().create().toJson(new JsonParser().parse(newScoreJson.toString())));
                fileWriter.flush();
            } catch (Exception e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Error : nom ou score invalide");
        }
    }
}
