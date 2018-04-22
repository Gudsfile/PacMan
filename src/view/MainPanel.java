package view;

import logic.Game;

import javax.swing.*;
import java.awt.*;

/**
 * Panel principal de l'affichage
 */
public class MainPanel extends JPanel {
    /**
     * String pour le cardLayout, Gamepanel
     */
    private static final String GAME_PANEL = "Game Panel";
    /**
     * String pour le cardLayout, Winpanel
     */
    private static final String WIN_PANEL = "Win Panel";
    /**
     * String pour le cardLayout, Losepanel
     */
    private static final String LOSE_PANEL = "Lose Panel";
    /**
     * Tableau contenant les clées correspondant au différents panels
     */
    public static final String[] KEY_TEXTS = {GAME_PANEL, WIN_PANEL, LOSE_PANEL};
    /**
     * Le jeu Pacman en cour
     */
    private Game game;
    /**
     * Le panel de jeu
     */
    private GamePanel gamePanel;
    /**
     * Le panel GameOver
     */
    private LPanel losePanel;
    /**
     * Le panel victoire
     */
    private WPanel winPanel;
    /**
     * Le cardlayout
     */
    private CardLayout cardlayout = new CardLayout();
    /**
     * Le panel contenant les différents panels de l'application
     */
    private JPanel cards = new JPanel(cardlayout);

    /**
     * Constructeur du mainPanel
     */
    public MainPanel() {
        this.game = new Game(1);
        build();
    }

    /**
     * Construit l'affichage
     */
    public void build() {
        JPanel container = new JPanel();
        this.gamePanel = new GamePanel(this);
        this.losePanel = new LPanel(this);
        this.winPanel = new WPanel(this);
        cards.add(gamePanel, GAME_PANEL);
        cards.add(winPanel, WIN_PANEL);
        cards.add(losePanel, LOSE_PANEL);
        setLayout(new BorderLayout());
        add(cards, BorderLayout.CENTER);
        cardlayout.first(cards);
        this.swapView(MainPanel.KEY_TEXTS[0]);
    }

    /**
     * Change de panel
     * @param key la clé du panel à afficher
     */
    public void swapView(String key) {
        cardlayout.show(cards, key);
    }

    /**
     * Lance une nouvelle partie
     */
    public void startNewGame() {
        this.game = new Game(1);
        this.swapView(MainPanel.KEY_TEXTS[0]);
        build();
    }

    /**
     * Lance le niveau suivant après une victoire
     */
    public void continueGame() {
        int life = this.game.getLife();
        int score = this.game.getFinalScore();
        this.game = new Game(this.game.getLevel() + 1);
        this.game.setLife(life);
        this.game.setFinalScore(score);
        this.swapView(MainPanel.KEY_TEXTS[0]);
        build();
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public CardLayout getCardlayout() {
        return cardlayout;
    }

    public void setCardlayout(CardLayout cardlayout) {
        this.cardlayout = cardlayout;
    }

    public GamePanel getGamePanel() {
        return gamePanel;
    }

    public void setGamePanel(GamePanel gamePanel) {
        this.gamePanel = gamePanel;
    }

    public LPanel getLosePanel() {
        return losePanel;
    }

    public void setLosePanel(LPanel losePanel) {
        this.losePanel = losePanel;
    }

    public WPanel getWinPanel() {
        return winPanel;
    }

    public void setWinPanel(WPanel winPanel) {
        this.winPanel = winPanel;
    }
}
