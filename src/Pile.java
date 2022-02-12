import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;



public class Pile {
    /**
     * Pile.java
     * This class represents a pile of cards in solitaire. Unlike the deck, a pile does not
     * necessarily contain 52 cards, it can hold anywhere from 0 to 52 cards. Piles can have cards
     * added and removed at runtime.
     *
     * Note: the Pile class does not care how or in what order cards are added. The MouseListener class
     * is responsible for ensuring that cards are added to piles in red black red black order and that
     * the faces are descending.
     */

    private final ArrayList<Card> pile;
    /**
     * A suit pile is one of the four piles at the top right corner
     */
    public static final int SUIT_PILE = 0;
    /**
     * A main pile is one of the seven piles across the middle of the screen
     */
    public static final int MAIN_PILE = 1;
    /**
     * This indicates a pile that is being moved
     */
    public static final int TEMP_PILE = 2;
    /**
     * A pile that the user may draw cards from
     */
    public static final int DECK_PILE = 3;
    /**
     * The type of pile: either SUIT, MAIN, TEMP, or DECK
     */
    private final int type;
    /**
     * The x and y locations of the pile. Though these are not final, they should NEVER change
     */
    private int xLocation, yLocation;
    /**
     * The width of the pile. It will always be the width of a card
     */
    private final int width;
    /**
     * The height of a pile. It will change as cards are added to the pile
     */
    private int height;
    /**
     * Vertical space between cards in a pile. This only applies for cards in a MAIN_PILE or TEMP_PILE
     */
    public static final int VERTICAL_SPACE = 22;
    /**
     * Horizontal space between cards. This only applies for cards in a DECK_PILE
     */
    public static final int HORIZONTAL_SPACE = 26;
    /**
     * The top card in a deck pile. This is necessary because only one can be drawn in a deck pile.
     */
    private Card deckPileTopCard;
    /**
     * Constructs a new pile of cards with an x location and a y location of (x,y)
     * @param x the x location of the pile
     * @param y the y location of the pile
     * @param t the type of the pile
     */
    public Pile(int x, int y, int t) {
        pile   = new ArrayList<Card>();
        xLocation   = x;
        yLocation   = y;
        width  = Card.WIDTH;
        height = Card.HEIGHT;
        if (t != SUIT_PILE && t != MAIN_PILE && t != DECK_PILE)
            type = TEMP_PILE;
        else
            type = t;
        deckPileTopCard = (type == DECK_PILE) ? new Card() : null;
    }
    /**
     * Draws the pile of cards
     * @param graphic the graphics context to draw on
     */
    public void draw(Graphics graphic) {
        if (pile.size() == 0 && type != DECK_PILE) {
            graphic.setColor(Color.white);
            graphic.drawRoundRect(xLocation, yLocation, Card.WIDTH, Card.HEIGHT, 10, 10);
            return;
        }

        if (type == DECK_PILE) {
            if(deckPileTopCard!=null)
            {
                deckPileTopCard.draw(graphic);
            }
        } else {
            for (int i = 0; i < pile.size(); i++) {
                pile.get(i).draw(graphic);
            }
        }

    }
    /**
     * Adds a card to the pile and sets its location appropriately
     * @param card the card to add
     */
    public void addToPile(Card card) {
        if (card != null) pile.add(card);
        if (type != SUIT_PILE && type != DECK_PILE) {
            card.setLocation(xLocation, yLocation + pile.indexOf(card) * VERTICAL_SPACE);
            if (pile.size() > 1) height += VERTICAL_SPACE;
        } else if (type == SUIT_PILE){
            card.setLocation(xLocation, yLocation);
        } else if (type == DECK_PILE) {
            card.setLocation(xLocation, yLocation);
            updateDeckPileTopCard();
        }
    }
    /**
     * Adds a pile to the pile and sets its location appropriately
     * @param p the pile to add
     */
    public void addToPile(Pile p) {
        if (this.type == MAIN_PILE) {
            for (int i = 0; i < p.getSize(); i++) {
                this.addToPile(p.getCardAt(i));
            }
        }

    }
    /**
     * @return the card at index i or null if i is out of bounds
     */
    public Card getCardAt(int i) {
        return inBounds(i) ? pile.get(i) : null;
    }
    /**
     * Returns whether i is a valid index of the pile
     * @param i the index to check
     * @return whether i is a valid index of the pile
     */
    private boolean inBounds(int i) {
        return (i >= 0 && i < pile.size());
    }
    /**
     * Returns the pile that has been clicked, if any. If only one card has been clicked, the pile
     * will simply contain a single card
     * @param event the mouse event to check
     * @return the pile that was clicked or null if the pile was not clicked
     */
    public Pile pileHasBeenClicked(MouseEvent event) {
        if (type == DECK_PILE) {
            Card card = this.getCardOnTop();
            if (card != null) {
                if ((event.getX() >= card.getcornerX() && event.getX() <= card.getcornerX() + Card.WIDTH) &&
                        (event.getY() >= card.getcornerY() && event.getY() <= card.getcornerY() + Card.HEIGHT))
                    return this.getPileAt(this.getSize() - 1);
            }
            return null;
        }
        if(type==SUIT_PILE)
        {
            return null;
        }
        if (event.getX() < this.xLocation || event.getX() > this.xLocation + this.width)
            return null;

        if (this.getSize() == 0 && (event.getY() >= this.yLocation && event.getY() <= this.yLocation + this.height))
            return this;

        for (int i = 0; i < this.getSize() - 1; i++) {
            Card c = this.getCardAt(i);
            if (event.getY() >= c.getcornerY() && event.getY() <= c.getcornerY() + VERTICAL_SPACE && !c.faceDown) {
                return this.getPileAt(i);
            }
        }
        if (this.getSize() > 0) {
            Card card = this.getCardOnTop();
            if (event.getY() >= card.getcornerY() && event.getY() <= card.getBottomY() && !card.faceDown)
                return this.getPileAt(this.getSize() - 1);
        }
        return null;
    }
    /**
     * Returns a pile of cards from the passed in index to the end of this pile
     * @param i the index to start at
     * @return a pile of cards from the index i to the end of this pile
     */
    public Pile getPileAt(int i) {
        Pile pile = new Pile(this.getCardAt(i).getcornerX(), this.getCardAt(i).getcornerY(), TEMP_PILE);
        while (i < this.getSize()) {
            pile.addToPile(this.getCardAt(i));
            this.removeCardAt(i);
        }
        return pile;
    }
    /**
     * Removes the card at index i from the deck or does nothing if the index is out of bounds
     * @param i the index to remove
     */
    public void removeCardAt(int i) {
        if (inBounds(i))
            pile.remove(i);
    }
    /**
     * @return the card on top of the pile or null if there are no cards in the pile
     */
    public Card getCardOnTop() {
        if (pile.size() > 0)
            return pile.get(pile.size() - 1);

        return null;
    }
    /**
     * @return the card at the first position of the pile
     */
    public Card getCardOnBottom() {
        if (pile.size() > 0)
            return pile.get(0);
        return null;
    }
    /**
     * Returns whether a card has been dropped on the pile
     * @param card the card to check
     * @return whether the card has been dropped on the pile
     */
    public boolean droppedOnPile(Card card) {
        return (((card.getcornerX() >= xLocation && card.getcornerX() <= xLocation + width)     && (card.getcornerY() >= yLocation && card.getcornerY() <= yLocation + height))             ||
                ((card.getRightX() >= xLocation && card.getRightX() <= xLocation + width) && (card.getcornerY() >= yLocation && card.getcornerY() <= yLocation + height))             ||
                ((card.getcornerX() >= xLocation && card.getcornerX() <= xLocation + width)           && (card.getBottomY() >= yLocation && card.getBottomY() <= yLocation + height)) ||
                ((card.getRightX() >= xLocation && card.getRightX() <= xLocation + width) && (card.getBottomY() >= yLocation && card.getBottomY() <= yLocation + height)));
    }
    /**
     * Returns whether a pile has been dropped on this pile
     * @param pile the pile to check
     * @return whether p has been dropped on this pile
     */
    public boolean droppedOnPile(Pile pile) {
        if (pile.getSize() > 0) {
            return droppedOnPile(pile.getCardAt(0));
        }
        return false;
    }
    /**
     * @return the size of the pile
     */
    public int getSize() {
        return pile.size();
    }
    /**
     * @return the x location of the pile
     */
    public int getXlocation() {
        return xLocation;
    }
    /**
     * @return the y location of the pile
     */
    public int getYlocation() {
        return yLocation;
    }
    /**
     * Moves a TEMP_PILE. The method simply does nothing if the pile is not a TEMP_PILE.
     * It also moves the x and y location of every card in the pile
     * @param x the new x location of the pile
     * @param y the new y location of the pile
     */
    public void setLocation(int x, int y) {
        if (type == TEMP_PILE) {
            xLocation = x;
            yLocation = y;
            for (int i = 0; i < this.getSize(); i++) {
                Card c = this.getCardAt(i);
                c.setLocation(x, y + (i * VERTICAL_SPACE));
            }
        }
    }
    /**
     * @return whether a pile is empty (size == 0) or not
     */
    public boolean isEmpty() {
        return this.getSize() == 0;
    }
    /**
     * Turns the top card of the pile up, or does nothing if the size is 0
     */
    public void turnTopCardUp() {
        if (this.getSize() > 0)
            this.getCardOnTop().faceDown = false;
    }
    /**
     * Turns all cards in the pile face up
     */
    public void turnAllCardsUp() {
        for (int i = 0; i < getSize(); i++) {
            pile.get(i).faceDown = false;
        }
    }
    /**
     * Updates the top card in the deck pile and adjusts its location
     */
    public void updateDeckPileTopCard()
    {
        if(type==DECK_PILE)
        {
            deckPileTopCard=null;
        }
        if (this.getSize() >= 1) {
            for (int i = this.getSize() - 1, j = 0; i < this.getSize(); i++, j++) {
                deckPileTopCard = this.getCardAt(i);
                deckPileTopCard.setLocation(xLocation + (HORIZONTAL_SPACE), yLocation);
            }
        } else {
            for (int i = 0; i < this.getSize(); i++) {
                deckPileTopCard = this.getCardAt(i);
                deckPileTopCard.setLocation(xLocation + (HORIZONTAL_SPACE), yLocation);
            }
        }
    }
}
