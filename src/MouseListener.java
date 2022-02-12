import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

public class MouseListener extends MouseInputAdapter
{
    /**
     * MouseListener.java
     * All collisions are put here
     */
    private final GamePanel panel;

    private JTextField razvanText;
    private JTextField winText;
    private JTextField loseText;

    private final Deck deck;

    private final Pile[] mainPiles;
    private final Pile[] suitPiles;
    private final Pile   deckPile;

    private Pile origPile;

    private int lastX, lastY;
    /**
     * Constructor for a Card Listener
     * @param panel the game panel in which to manipulate when the user clicks/drags/drops cards
     */
    public MouseListener(GamePanel panel) {
        this.panel   = panel;
        deck = panel.getDeck();
        mainPiles = panel.getMainPiles();
        suitPiles = panel.getSuitPiles();
        deckPile  = panel.getDeckPile();
        lastX = 0;
        lastY = 0;
        origPile = null;
    }
    @Override
    /**
     * Selects a card when it is clicked, or the Razvan object
     */
    public void mousePressed(MouseEvent event) {
        panel.selectedPile = getPileClicked(event);
        if (panel.selectedPile != null) {
            lastX = event.getX();
            lastY = event.getY();
        } else {
            if (deck.hasBeenClicked(event)) {
                if (deck.getSize() == 0) {
                    deck.addToDeck(deckPile);
                } else {
                        Card c = deck.getCardOnTop();
                        if (c != null) {
                            deckPile.addToPile(c);
                            deck.removeCardOnTop();
                        }
                    deckPile.turnAllCardsUp();
                }
            }
            if(panel.razvan.hasBeenClicked(event))
            {
                razvanText = new JTextField(" Am nevoie de ajutorul tau pentru a termina jocul! Pentru mai multe detalii legate de joc, apasa pe butonul 'Show Rules'.");
                razvanText.setVisible(true);
                razvanText.setEditable(false);
                razvanText.setBounds(50,600,675,30);
                razvanText.setFont(new Font("TimesNewRoman",Font.BOLD,12));
                Timer timer = new Timer(5000, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        razvanText.setVisible(false);
                    }
                });
                timer.start();
                panel.add(razvanText);
            }
        }
        panel.repaint();
    }
    @Override
    /**
     * Moves the card as it is dragged by the mouse
     */
    public void mouseDragged(MouseEvent event) {
        if (panel.selectedPile != null) {
            int newX = panel.selectedPile.getXlocation() + (event.getX() - lastX);
            int newY = panel.selectedPile.getYlocation() + (event.getY() - lastY);
            panel.selectedPile.setLocation(newX, newY);
            lastX = event.getX();
            lastY = event.getY();
        }
        panel.repaint();
    }
    @Override
    /**
     * If the game score is 0, the game will be over
     */
    public void mouseMoved(MouseEvent e)
    {
        if(panel.getScore()==0)
        {
            loseText=new JTextField("Felicitari!! "+"Ai pierdut jocul!!");
            loseText.setVisible(true);
            loseText.setEditable(false);
            loseText.setBounds(0,200,800,500);
            loseText.setFont(new Font("TimesNewRoman",Font.BOLD,36));
            loseText.setHorizontalAlignment(0);
            panel.add(loseText);
        }
    }
    @Override
    /**
     * Drops a card on a pile only if it has the right face and color and verifies if the checkForWin() is on
     */
    public void mouseReleased(MouseEvent event) {
        Pile p = panel.selectedPile;
        if (p != null) {
            boolean validDrop = false;
            for (int i = 0; i < mainPiles.length; i++) {
                if (mainPiles[i].droppedOnPile(p)) {
                    if (mainPiles[i].isEmpty()) {
                        if (p.getCardOnBottom().getFace().equals("K")) {
                            mainPiles[i].addToPile(p);
                            origPile.turnTopCardUp();
                            validDrop = true;
                        }
                    } else {
                        if (!p.getCardOnBottom().getColor().equals(mainPiles[i].getCardOnTop().getColor())) {
                            if (Card.getFaceIndex(p.getCardOnBottom().getFace()) + 1 == Card.getFaceIndex(mainPiles[i].getCardOnTop().getFace())) {
                                mainPiles[i].addToPile(p);
                                origPile.turnTopCardUp();
                                validDrop = true;
                                break;
                            }
                        }
                    }
                }
            }
            if (!validDrop) {
                for (int i = 0; i < suitPiles.length; i++) {
                    if (suitPiles[i].droppedOnPile(p)) {
                        if (suitPiles[i].isEmpty()) {
                            if (p.getSize() == 1) {
                                if (p.getCardOnBottom().getFace().equals("A")) {
                                    suitPiles[i].addToPile(p.getCardOnBottom());
                                    origPile.turnTopCardUp();
                                    validDrop = true;
                                }
                            }
                        } else {
                            if (p.getSize() == 1) {
                                if (Card.getSuitIndex(p.getCardOnBottom().getSuit()) == Card.getSuitIndex(suitPiles[i].getCardOnTop().getSuit())) {
                                    if (Card.getFaceIndex(p.getCardOnBottom().getFace()) == Card.getFaceIndex(suitPiles[i].getCardOnTop().getFace()) + 1) {
                                        suitPiles[i].addToPile(p.getCardOnBottom());
                                        origPile.turnTopCardUp();
                                        validDrop = true;
                                    }
                                }
                            }
                        }
                    }
                }
            }
            if (!validDrop) {
                if (p.getSize() == 1) {
                    origPile.addToPile(p.getCardOnBottom());
                } else {
                    origPile.addToPile(p);
                }
            }
        }
        if(panel.checkForWin())
        {
            winText=new JTextField("Felicitari!!"+"Ai terminat jocul!!");
            winText.setVisible(true);
            winText.setEditable(false);
            winText.setBounds(0,200,800,500);
            winText.setFont(new Font("TimesNewRoman",Font.BOLD,36));
            winText.setHorizontalAlignment(0);
            panel.add(winText);
        }
        panel.selectedPile = null;
        origPile = null;
        panel.repaint();
    }
    /**
     * Returns the card that was clicked or null if no card was clicked
     * @param event the mouse event to check
     * @return the card that was clicked or null if no card was clicked
     */
    private Pile getPileClicked(MouseEvent event) {
        Pile clicked;
        origPile = null;
        for (int i = 0; i < mainPiles.length; i++) {
            if ((clicked = mainPiles[i].pileHasBeenClicked(event)) != null) {
                origPile = mainPiles[i];
                return clicked;
            }
        }
        for (int i = 0; i < suitPiles.length; i++) {
            if ((clicked = suitPiles[i].pileHasBeenClicked(event)) != null) {
                origPile = suitPiles[i];
                return clicked;
            }
        }
        if ((clicked = deckPile.pileHasBeenClicked(event)) != null) origPile = deckPile;
        return clicked;
    }
}
