package view;

import logic.Game;

import javax.swing.*;
import java.awt.*;

public class MainPanel extends JPanel {
    private static final String GAME_PANEL = "Game Panel";
    private static final String WIN_PANEL = "Win Panel";
    private static final String LOSE_PANEL = "Lose Panel";
    public static final String[] KEY_TEXTS = {GAME_PANEL, WIN_PANEL, LOSE_PANEL};
    private Game game;
    private GamePanel gamePanel;
    private LPanel losePanel;
    private WPanel winPanel;
    private CardLayout cardlayout = new CardLayout();
    private JPanel cards = new JPanel(cardlayout);

    public MainPanel() {
        this.game = new Game(2);
        build();
    }

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

    public void swapView(String key) {
        cardlayout.show(cards, key);
    }

    public void startNewGame() {
        this.game = new Game(1);
        this.swapView(MainPanel.KEY_TEXTS[0]);
        build();
    }

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
