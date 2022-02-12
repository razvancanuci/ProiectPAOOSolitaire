import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class GamePanel extends JPanel
{
    /**
     * GamePanel.java
     * The panel that Solitaire is painted on. It is responsible for drawing
     * and initializing all game objects (the deck and all piles).
     */
    private Deck deck;
    /**
     * Horizontal space between rows of cards
     */
    public static final int HORIZONTAL_SPACE = 35;
    /**
     * The game score. When the game score is 0, the game will be finished
     */
    private static int score;
    /**
     * the filed where the score is visible
     */
    private static JTextField scoreBox = new JTextField();

    /**
     * X locations of every main pile
     */
    public static final int[] MAIN_PILE_X_LOCATIONS = {(HORIZONTAL_SPACE),
            (HORIZONTAL_SPACE*2) + (Card.WIDTH),
            (HORIZONTAL_SPACE*3) + (Card.WIDTH * 2),
            (HORIZONTAL_SPACE*4) + (Card.WIDTH * 3),
            (HORIZONTAL_SPACE*5) + (Card.WIDTH * 4),
            (HORIZONTAL_SPACE*6) + (Card.WIDTH * 5),
            (HORIZONTAL_SPACE*7) + (Card.WIDTH * 6)};
    /**
     * The y location of each main pile
     */
    public static final int MAIN_PILE_Y_LOCATION = 200;
    /**
     * X locations of each suit pile
     */
    public static final int[] SUIT_PILE_X_LOCATIONS = {MAIN_PILE_X_LOCATIONS[3],
            MAIN_PILE_X_LOCATIONS[4],
            MAIN_PILE_X_LOCATIONS[5],
            MAIN_PILE_X_LOCATIONS[6]};
    /**
     * Y locations of each suit pile
     */
    public static final int SUIT_PILE_Y_LOCATION = 50;

    private final Pile[] mainPiles;
    private final Pile[] suitPiles;

    private final Pile   deckPile;
    /**
     * The selected Pile is always drawn last (so it is on top of everything else)
     */
    public Pile selectedPile;

    public Razvan razvan;

    public static int RAZVAN_XLOCATION=720;
    public static int RAZVAN_YLOCATION=600;
    /**
     * Constructor for a game panel. Adds mouse listeners and the score, initializes deck, piles and Razvan object
     * @param argument the level selected
     */
    public GamePanel(String argument) {
        setBackground(new Color(0, 200, 153));
        score=10000;
        scoreBox.setText("Score: "+score);
        makeScoreBox(argument);
        deck         = new Deck(argument);
        mainPiles    = new Pile[7];
        suitPiles    = new Pile[4];
        razvan=new Razvan(RAZVAN_XLOCATION,RAZVAN_YLOCATION);
        setInitialLayout(deck);
        deckPile     = new Pile(deck.getX() + Card.WIDTH + HORIZONTAL_SPACE, deck.getY(), Pile.DECK_PILE);
        selectedPile = null;
        MouseListener listener = new MouseListener(this);
        this.addMouseListener(listener);
        this.addMouseMotionListener(listener);
        this.setFocusable(true);
    }
    /**
     * Paints the screen on a graphics context
     * @param graphic the graphics context to paint on
     */
    protected void paintComponent(Graphics graphic) {
        super.paintComponent(graphic);
        for (int i = 0; i < mainPiles.length; i++) {
            mainPiles[i].draw(graphic);
        }
        for (int i = 0; i < suitPiles.length; i++) {
            suitPiles[i].draw(graphic);
        }
        deckPile.draw(graphic);
        deck.draw(graphic);

        if (selectedPile != null) {
            selectedPile.draw(graphic);
        }
        razvan.draw(graphic);
    }
    /**
     * @return the deck of cards
     */
    public Deck getDeck() {
        return deck;
    }
    /**
     * Sets the location of all cards to their starting points
     */
    public void setInitialLayout(Deck deck) {
        int cardNum = 0;
        for (int i = 0; i < mainPiles.length; i++) {
            mainPiles[i] = new Pile(MAIN_PILE_X_LOCATIONS[i], MAIN_PILE_Y_LOCATION, Pile.MAIN_PILE);
            for (int j = 0; j <= i; j++) {
                mainPiles[i].addToPile(deck.getCardAt(cardNum));
                if (j == i) deck.getCardAt(cardNum).faceDown = false;
                deck.removeCardAt(cardNum);

            }
        }
        for (int i = 0; i < Card.SUITS.length; i++) {
            suitPiles[i] = new Pile(SUIT_PILE_X_LOCATIONS[i], SUIT_PILE_Y_LOCATION, Pile.SUIT_PILE);
        }
    }
    /**
     * @return the mainPiles array
     */
    public Pile[] getMainPiles() {
        return mainPiles;
    }
    /**
     * @return the suitPiles array
     */
    public Pile[] getSuitPiles() {
        return suitPiles;
    }
    /**
     * Returns the deck pile
     * @return the deck pile
     */
    public Pile getDeckPile() {
        return deckPile;
    }
    /**
     * check if the suit piles are full of cards
     */
    public boolean checkForWin()
    {
        for(int i=0;i< suitPiles.length;i++)
        {
            if(suitPiles[i].getSize()<13)
            {
                return false;
            }
        }
        return true;
    }
    /**
     * initializes the score box
     * @param argument the level selected
     */
    public void makeScoreBox(String argument) {
        scoreBox.setEditable(false);
        scoreBox.setOpaque(false);
        this.add(scoreBox);
        if (argument.equals("level1")) {
            runScoreLevel1();
        }
        if(argument.equals("level2"))
        {
            runScoreLevel2();
        }
    }
    /**
     * @return the score
     */
    public int getScore()
    {
        return score;
    }
    /**
     * runs the score if the level 1 is selected
     */
    public void runScoreLevel1()
    {
        Timer timer = new Timer(4000,new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setScore();
            }
        });
        if(score!=0)
        {
            timer.start();
        }
        else
        {
            timer.stop();
        }
    }
    /**
     * runs the score if the level 2 is selected
     */
    public void runScoreLevel2()
    {
        Timer timer = new Timer(2000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                setScore();
            }
        });
        if(score!=0)
        {
            timer.start();
        }
        else
        {
            timer.stop();
        }
    }
    /**
     * modifies the score and the text in a scoreBox
     */
    public void setScore()
    {
        score-=1;
        String newScore = "Score: " + (score);
        scoreBox.setText(newScore);
        scoreBox.repaint();
    }

}
