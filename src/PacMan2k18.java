import logic.Game;
import view.MainFrame;

public class PacMan2k18 {

    public static void main(String[] args) {
        MainFrame mainFrame = new MainFrame();
        Game game = mainFrame.getMainPanel().getGame();

        Thread[] threads = {
                new Thread(game, "G1"),
                new Thread(game, "G2"),
                new Thread(game, "G3"),
                new Thread(game, "G4"),
                new Thread(game, "PO")
        };

        startThread(threads);
        mainFrame.go();

    }

    static Thread[] updateThread(Thread[] t, Game g) {
        Thread[] res = new Thread[t.length];
        for (int i = 0; i < t.length; i++) {
            if (!t[i].isInterrupted()) {
                t[i].interrupt();
            }
            res[i] = new Thread(g, t[i].getName());
        }
        return res;
    }

    static void startThread(Thread[] t) {
        for (Thread t2 : t) {
            t2.start();
        }
    }

}
