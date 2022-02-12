import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Razvan
{
    /**
     * Razvan.java
     * An object whose place is in the right corner of the frame.
     * It will put a messagebox when it is clicked.
     */
    private BufferedImage image;

    private int xLocation,yLocation;

    private static final int WIDTH=64;
    private static final int HEIGHT=64;
    /**
     * Constructor that initialize the object at x location, y location and the image
     * @param x the x location of the object
     * @param y the y location of the object
     */
    Razvan(int x,int y)
    {
        setLocation(x,y);
        initImage();
    }
    /**
     * Initializes the image of the object
     */
    void initImage()
    {
        try
        {
            image=ImageIO.read(new File("images/Razvan.png"));
        } catch(IOException ioException)
        {
            System.out.println("Error reading the image");
        }
    }
    /**
     * Sets the location of the object
     * @param x the top-left x coordinate of the object
     * @param y the top-left y coordinate of the object
     */
    public void setLocation(int x, int y)
    {
        xLocation = x;
        yLocation = y;
    }
    /**
     * Draws the object
     * @param graphic the graphics context to draw the object on
     */
    void draw(Graphics graphic)
    {
        graphic.drawImage(image,xLocation,yLocation,null);
    }

    /**
     * Returns the object that has been clicked, if any
     * @param event the mouse event to check
     * @return the object that was clicked or null if the object wasn't clicked
     */
    public boolean hasBeenClicked(MouseEvent event) {
        return (event.getX() >= xLocation && event.getX() <= xLocation + WIDTH) &&
                (event.getY() >= yLocation && event.getY() <= yLocation + HEIGHT);
    }
}

