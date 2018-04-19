import logic.Game;
import view.MainFrame;

public class PacMan2k18 {

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        Game game = mainFrame.getMainPanel().getGame();

        Thread threadG1 = new Thread(game, "G1");
        Thread threadG2 = new Thread(game, "G2");
        Thread threadG3 = new Thread(game, "G3");
        Thread threadG4 = new Thread(game, "G4");
        Thread threadPO = new Thread(game, "PO");

        threadG1.start();
        threadG2.start();
        threadG3.start();
        threadG4.start();
        threadPO.start();

        mainFrame.go();

    }
}
