import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Card {

    /**
     * Card.java
     * A card has a suit (spade, heart, diamond, club) and a face (ace, 2, 3, ..., 10, Jack, Queen, King).
     * This class provides methods for drawing and constructing a card.
     */
    private String suit;
    private String face;

    private BufferedImage backImg;
    private BufferedImage faceImg;

    private Color color;

    private int cornerX, cornerY;
    private int rightX;
    private int bottomY;

    public static final int HEIGHT = 97, WIDTH = 73;

    /**
     * All possible suits a card may have
     */
    public static final String[] SUITS = {"S", "H", "D", "C"};
    /**
     * All possible face values a card may have
     */
    public static final String[] FACES = {"A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K"};

    public boolean faceDown;

    /**
     * No-arg constructor that initializes the card to the Ace of Spades
     */
    public Card() {
        this("A", "S");
    }
    /**
     * Constructor that suits the card suit to s and the card face to f and the location to (0, 0)
     * @param s the suit of the card
     * @param f the face of the card
     */
    public Card(String s, String f) {
        this(s, f, 0, 0);
    }
    /**
     * Constructor that initializes all variables to passed in parameters
     *
     * @param f the face of the card
     * @param s the suit of the card
     * @param x the top-left x coordinate of the card
     * @param y the top-left y coordinate of the card
     */
    public Card(String f, String s, int x, int y)
    {
        setFace(f);
        setSuit(s);
        setLocation(x, y);
        initFaceImage();
        initBackImage();
        faceDown = true;
    }
    /**
     * Draws the card to a graphics context
     * @param graphic the graphics context to draw the card on
     */
    public void draw(Graphics graphic)
    {
        if (!faceDown)
        {
            graphic.drawImage(faceImg, cornerX, cornerY, null);
        }
        else
            {
                graphic.drawImage(backImg, cornerX, cornerY, null);
            }
    }
    /**
     * Initializes the face image of the card depending on what suit and face the card is
     */
    private void initFaceImage() {
        try {
            switch (suit) {
                case "S":
                    switch(face){
                        case "A":
                            faceImg=ImageIO.read(new File("images/Frunza/as.gif"));
                            color=Color.black;
                            break;
                        case "2":
                            faceImg=ImageIO.read(new File("images/Frunza/2s.gif"));
                            color=Color.black;
                            break;
                        case "3":
                            faceImg=ImageIO.read(new File("images/Frunza/3s.gif"));
                            color=Color.black;
                            break;
                        case "4":
                            faceImg=ImageIO.read(new File("images/Frunza/4s.gif"));
                            color=Color.black;
                            break;
                        case "5":
                            faceImg=ImageIO.read(new File("images/Frunza/5s.gif"));
                            color=Color.black;
                            break;
                        case "6":
                            faceImg=ImageIO.read(new File("images/Frunza/6s.gif"));
                            color=Color.black;
                            break;
                        case "7":
                            faceImg=ImageIO.read(new File("images/Frunza/7s.gif"));
                            color=Color.black;
                            break;
                        case "8":
                            faceImg=ImageIO.read(new File("images/Frunza/8s.gif"));
                            color=Color.black;
                            break;
                        case "9":
                            faceImg=ImageIO.read(new File("images/Frunza/9s.gif"));
                            color=Color.black;
                            break;
                        case "10":
                            faceImg=ImageIO.read(new File("images/Frunza/ts.gif"));
                            color=Color.black;
                            break;
                        case "J":
                            faceImg=ImageIO.read(new File("images/Frunza/js.gif"));
                            color=Color.black;
                            break;
                        case "Q":
                            faceImg=ImageIO.read(new File("images/Frunza/qs.gif"));
                            color=Color.black;
                            break;
                        case "K":
                            faceImg=ImageIO.read(new File("images/Frunza/ks.gif"));
                            color=Color.black;
                            break;
                    }
                    break;
                case "H":
                    switch(face){
                        case "A":
                            faceImg=ImageIO.read(new File("images/Inima/ah.gif"));
                            color=Color.red;
                            break;
                        case "2":
                            faceImg=ImageIO.read(new File("images/Inima/2h.gif"));
                            color=Color.red;
                            break;
                        case "3":
                            faceImg=ImageIO.read(new File("images/Inima/3h.gif"));
                            color=Color.red;
                            break;
                        case "4":
                            faceImg=ImageIO.read(new File("images/Inima/4h.gif"));
                            color=Color.red;
                            break;
                        case "5":
                            faceImg=ImageIO.read(new File("images/Inima/5h.gif"));
                            color=Color.red;
                            break;
                        case "6":
                            faceImg=ImageIO.read(new File("images/Inima/6h.gif"));
                            color=Color.red;
                            break;
                        case "7":
                            faceImg=ImageIO.read(new File("images/Inima/7h.gif"));
                            color=Color.red;
                            break;
                        case "8":
                            faceImg=ImageIO.read(new File("images/Inima/8h.gif"));
                            color=Color.red;
                            break;
                        case "9":
                            faceImg=ImageIO.read(new File("images/Inima/9h.gif"));
                            color=Color.red;
                            break;
                        case "10":
                            faceImg=ImageIO.read(new File("images/Inima/th.gif"));
                            color=Color.red;
                            break;
                        case "J":
                            faceImg=ImageIO.read(new File("images/Inima/jh.gif"));
                            color=Color.red;
                            break;
                        case "Q":
                            faceImg=ImageIO.read(new File("images/Inima/qh.gif"));
                            color=Color.red;
                            break;
                        case "K":
                            faceImg=ImageIO.read(new File("images/Inima/kh.gif"));
                            color=Color.red;
                            break;
                    }
                    break;
                case "D":
                    switch(face){
                        case "A":
                            faceImg=ImageIO.read(new File("images/Romb/ad.gif"));
                            color=Color.red;
                            break;
                        case "2":
                            faceImg=ImageIO.read(new File("images/Romb/2d.gif"));
                            color=Color.red;
                            break;
                        case "3":
                            faceImg=ImageIO.read(new File("images/Romb/3d.gif"));
                            color=Color.red;
                            break;
                        case "4":
                            faceImg=ImageIO.read(new File("images/Romb/4d.gif"));
                            color=Color.red;
                            break;
                        case "5":
                            faceImg=ImageIO.read(new File("images/Romb/5d.gif"));
                            color=Color.red;
                            break;
                        case "6":
                            faceImg=ImageIO.read(new File("images/Romb/6d.gif"));
                            color=Color.red;
                            break;
                        case "7":
                            faceImg=ImageIO.read(new File("images/Romb/7d.gif"));
                            color=Color.red;
                            break;
                        case "8":
                            faceImg=ImageIO.read(new File("images/Romb/8d.gif"));
                            color=Color.red;
                            break;
                        case "9":
                            faceImg=ImageIO.read(new File("images/Romb/9d.gif"));
                            color=Color.red;
                            break;
                        case "10":
                            faceImg=ImageIO.read(new File("images/Romb/td.gif"));
                            color=Color.red;
                            break;
                        case "J":
                            faceImg=ImageIO.read(new File("images/Romb/jd.gif"));
                            color=Color.red;
                            break;
                        case "Q":
                            faceImg=ImageIO.read(new File("images/Romb/qd.gif"));
                            color=Color.red;
                            break;
                        case "K":
                            faceImg=ImageIO.read(new File("images/Romb/kd.gif"));
                            color=Color.red;
                            break;
                    }
                    break;
                case "C":
                    switch(face){
                        case "A":
                            faceImg=ImageIO.read(new File("images/Trefla/ac.gif"));
                            color=Color.black;
                            break;
                        case "2":
                            faceImg=ImageIO.read(new File("images/Trefla/2c.gif"));
                            color=Color.black;
                            break;
                        case "3":
                            faceImg=ImageIO.read(new File("images/Trefla/3c.gif"));
                            color=Color.black;
                            break;
                        case "4":
                            faceImg=ImageIO.read(new File("images/Trefla/4c.gif"));
                            color=Color.black;
                            break;
                        case "5":
                            faceImg=ImageIO.read(new File("images/Trefla/5c.gif"));
                            color=Color.black;
                            break;
                        case "6":
                            faceImg=ImageIO.read(new File("images/Trefla/6c.gif"));
                            color=Color.black;
                            break;
                        case "7":
                            faceImg=ImageIO.read(new File("images/Trefla/7c.gif"));
                            color=Color.black;
                            break;
                        case "8":
                            faceImg=ImageIO.read(new File("images/Trefla/8c.gif"));
                            color=Color.black;
                            break;
                        case "9":
                            faceImg=ImageIO.read(new File("images/Trefla/9c.gif"));
                            color=Color.black;
                            break;
                        case "10":
                            faceImg=ImageIO.read(new File("images/Trefla/tc.gif"));
                            color=Color.black;
                            break;
                        case "J":
                            faceImg=ImageIO.read(new File("images/Trefla/jc.gif"));
                            color=Color.black;
                            break;
                        case "Q":
                            faceImg=ImageIO.read(new File("images/Trefla/qc.gif"));
                            color=Color.black;
                            break;
                        case "K":
                            faceImg=ImageIO.read(new File("images/Trefla/kc.gif"));
                            color=Color.black;
                            break;
                    }
                    break;
                default:
            }
        } catch (IOException ioException) {
            System.out.println("Error reading image.");
        }
    }
    /**
     * Initializes the back image of the card
     */
    private void initBackImage()
    {
        try
        {
            backImg=ImageIO.read(new File("images/b.gif"));
        }
        catch(IOException ioException)
        {
            System.out.println("Error reading image.");
        }
    }
    /**
     * Sets the suit to s if it's valid
     * @param s the new suit
     */
    private void setSuit(String s)
    {
        if (isValidSuit(s))
            suit = s;
    }
    /**
     * Sets the face to s if it's valid
     * @param s the new face
     */
    private void setFace(String s)
    {
        if (isValidFace(s))
            face = s;
    }
    /**
     * Determines if the passed in suit is valid
     * @param s the suit
     * @return whether s is valid
     */
    private boolean isValidSuit(String s)
    {
        for (int i = 0; i < SUITS.length; i++)
        {
            if (s.equals(SUITS[i]))
            {
                return true;
            }
        }
        return false;
    }
    /**
     * Determines if the passed in face is valid
     * @param s the face
     * @return whether s is valid
     */
    private boolean isValidFace(String s)
    {
        for (int i = 0; i < FACES.length; i++)
        {
            if (s.equals(FACES[i]))
            {
                return true;
            }
        }
        return false;
    }
    /**
     * @return the card suit
     */
    public String getSuit() {
        return suit;
    }
    /**
     * @return the card face
     */
    public String getFace() {
        return face;
    }
    /**
     * Returns the index in the faces array of the passed in the string
     * @param s the string to check
     * @return the index in the faces array of s or -1 if not found
     */
    public static int getFaceIndex(String s)
    {
        for (int i = 0; i < FACES.length; i++)
        {
            if (s.equals(FACES[i]))
            {
                return i;
            }
        }
        return -1;
    }
    /**
     * Returns the index in the suits array of the passed in the string
     * @param s the string to check
     * @return the index in the suits array of s or -1 if not found
     */
    public static int getSuitIndex(String s)
    {
        for (int i = 0; i < SUITS.length; i++)
            if (s.equals(SUITS[i])) return i;
        return -1;
    }
    /**
     * @return the top left x coordinate of the card
     */
    public int getcornerX() {
        return cornerX;
    }
    /**
     * @return the top left y coordinate of the card
     */
    public int getcornerY() {
        return cornerY;
    }
    /**
     * @return the x coordinate of the right side of the card
     */
    public int getRightX() {
        return rightX;
    }
    /**
     * @return the y coordinate of the bottom of the card
     */
    public int getBottomY() {
        return bottomY;
    }
    /**
     * Sets the top left corner of the card to (x, y)
     * @param x the top-left x coordinate of the card
     * @param y the top-left y coordinate of the card
     */
    public void setLocation(int x, int y)
    {
        cornerX = x;
        cornerY = y;
        assignVertices();
    }
    /**
     * @return the string: "<face> of <suit>"
     */
    public String toString() {
        return face + " of " + suit;
    }
    /**
     * Assigns the rightX and bottomY vertices based on the top left corner location
     */
    private void assignVertices()
    {
        rightX = cornerX + WIDTH;
        bottomY   = cornerY + HEIGHT;
    }
    /**
     * @return the color of the card
     */
    public Color getColor() {
        return color;
    }

}
