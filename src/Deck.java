import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.util.ArrayList;
import java.util.Random;

public class Deck {

    /**
     * Deck.java
     * A deck is a list of 52 cards.
     */
    private ArrayList<Card> deck;
    /**
     * The location of the deck at (xLocation, yLocation)
     */
    private int xLocation, yLocation;
    /**
     * No-arg constructor that adds 52 cards to the deck and shuffles it depending on the level
     * @param argument the level selected for making the shuffle
     */
    public Deck(String argument)
    {
        deck = new ArrayList<Card>();
        for (int i = 0; i < Card.SUITS.length; i++)
        {
            for (int j = 0; j < Card.FACES.length; j++)
            {
                deck.add(new Card(Card.FACES[j], Card.SUITS[i]));
            }
        }
        if(argument.equals("level2"))
        {
            shuffleDeckLevel2();
        }
        else
        {
            shuffleDeckLevel1();
        }
    }
    /**
     * Draws each card in the deck
     * @param graphic the graphics context to draw the deck on
     */
    public void draw(Graphics graphic)
    {
        for (int i = 0; i < deck.size(); i++)
        {
            this.getCardAt(i).draw(graphic);
        }
    }
    /**
     * Shuffles the deck of cards for level 1
     */
    private void shuffleDeckLevel1()
    {
        Random random=new Random();
        for (int i = 0; i < deck.size(); i++) {
            int index = i+random.nextInt(deck.size()-i);
            swap(i, index);
        }
    }
    /**
     * Shuffles the deck of cards for level 2
     */
    private void shuffleDeckLevel2()
    {
        for (int i = 0; i < deck.size(); i++)
        {
            int index = (int)(Math.random() * deck.size());
            swap(i, index);
        }
    }
    /**
     * Swaps two cards in a deck
     * @param index the first index
     * @param i the second index
     */
    private void swap(int index, int i)
    {
        Card temp = this.getCardAt(index);
        this.setCardAt(index, this.getCardAt(i));
        this.setCardAt(i, temp);
    }
    /**
     * Returns the card at the specified index
     * @param index the index of the card to return
     * @return the card at the index or null if the index is out of bounds
     */
    public Card getCardAt(int index)
    {
        if (inBounds(index))
        {
            return deck.get(index);
        }

        return null;
    }
    /**
     * Removes the card at the specified index if the index is not out of bounds
     * @param index the index of the card to remove
     */
    public void removeCardAt(int index)
    {
        if (inBounds(index))
        {
            deck.remove(index);
        }
    }
    /**
     * Sets the card at index to c
     * @param index the index to set
     * @param card the card to set
     */
    private void setCardAt(int index, Card card)
    {
        if (inBounds(index))
        {
            deck.set(index, card);
        }
    }
    /**
     * Returns whether i is a valid index in the deck
     * @param i the index to check
     * @return whether i is in bounds
     */
    private boolean inBounds(int i) {
        return (i >= 0 && i < deck.size());
    }
    /**
     * Returns the card that has been clicked, if any
     * @param event the mouse event to check
     * @return the card that was clicked or null if no cards were clicked
     */
    public boolean hasBeenClicked(MouseEvent event)
    {
        return (event.getX() >= xLocation && event.getX() <= xLocation + Card.WIDTH) &&
                (event.getY() >= yLocation && event.getY() <= yLocation + Card.HEIGHT);
    }
    /**
     * Wrapper class for ArrayList.size()
     * @return the size of the deck of cards
     */
    public int getSize() {
        return deck.size();
    }
    /**
     * Returns the card at the top of the deck (index size() - 1)
     * @return the top card
     */
    public Card getCardOnTop()
    {
        if (getSize() > 0)
            return this.getCardAt(getSize() - 1);

        return null;
    }
    /**
     * Removes the card at the top of the deck
     */
    public void removeCardOnTop()
    {
        if (getSize() > 0)
            this.removeCardAt(getSize() - 1);
    }
    /**
     * @return the x location of the deck
     */
    public int getX() {
        return xLocation;
    }
    /**
     * @return the y location of the deck
     */
    public int getY() {
        return yLocation;
    }

    /**
     * Turns all cards in the deck face down
     */
    public void turnAllCardsDown()
    {
        for (int i = 0; i < this.getSize(); i++)
        {
            deck.get(i).faceDown = true;
        }
    }

    /**
     * Adds a card to the deck
     * @param card the card to add
     */
    private void addToDeck(Card card)
    {
        this.deck.add(card);
        card.setLocation(xLocation, yLocation);
    }
    /**
     * Adds the pile p to the deck and removes the cards from p only if the deck has no cards in it
     * @param pile the pile to add
     */
    public void addToDeck(Pile pile)
    {
        if (this.getSize() == 0)
        {
            while (!pile.isEmpty())
            {
                this.addToDeck(pile.getCardAt(pile.getSize() - 1));
                pile.removeCardAt(pile.getSize() - 1);
            }
            turnAllCardsDown();
        }
    }

}
