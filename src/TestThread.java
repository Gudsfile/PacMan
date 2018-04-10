import logic.Game;

public class TestThread extends Thread {
    private Thread t;
    private Game g;
    private static int temp = 0;

    TestThread(String name, Game g){
        super(name);
        this.g = g;
        this.start();
    }

    TestThread(String name, Thread t, Game g){
        super(name);
        this.t = t;
        this.g = g;
        this.start();
    }

    public void run() {
        int dx = (int) (Math.random() * ((399) + 1));
        int dy = (int) (Math.random() * ((399) + 1));
        int[] mo = {1,1,1,1,1,2,2,2,2,2,2,4,2,2,2,4,4,4,4,3,3,3,4};

        if (dx < 100) {
            dx = -1;
            dy = 0;
        } else if (dx < 200) {
            dx = 0;
            dy = 1;
        } else if (dx < 300) {
            dx = 1;
            dy = 0;
        } else if (dx < 400) {
            dx = 0;
            dy = -1;
        }

        switch (this.getName()) {
            case "PC":
                g.play(mo[temp]);
                temp = (temp+1)%mo.length;
                break;
            case "G1":
                g.play(g.getGameBoard().getGhostList().get(0), dx, dy);
                break;
            case "G2":
                g.play(g.getGameBoard().getGhostList().get(1), dx, dy);
                break;
            case "G3":
                g.play(g.getGameBoard().getGhostList().get(2), dx, dy);
                break;
            case "G4":
                g.play(g.getGameBoard().getGhostList().get(3), dx, dy);
                break;
            case "VE":
                System.out.println("");
                break;
        }
    }

    public void setThread(Thread t){
        this.t = t;
    }
}