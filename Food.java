import javax.swing.ImageIcon;
import java.awt.Image;
import java.util.Random;
import java.awt.Rectangle;

/**
 * Write a description of class Food here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Food
{
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
    private String fritas = "images/fries.png";
    private Rectangle hitbox;
    
    public Food() {
        ImageIcon ii;
        ii = new ImageIcon(this.getClass().getResource(fritas));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        randomize();
    }
    
    public void randomize() {
        Random r = new Random();
        x = 100 + r.nextInt(601);
        y = 100 + r.nextInt(401);
        hitbox = new Rectangle(x, y, width, height);
    }
    
    public int getX() {
        return x;
    }
    
    public int getY() {
        return y;
    }    
    
    public Image getImage() {
        return image;
    }
    
    public Rectangle getHitbox() {
        return hitbox;
    }
}
