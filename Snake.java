import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends JPanel
{
    private String head = "images/head.png";
    private String body = "images/body.png";
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
    private Snake proximo;
    private int index;
    
    public Snake() {
        ImageIcon ii;
        if (index == 0) {
            ii = new ImageIcon("images/"+this.getClass().getResource(head));
        } else {
            ii = new ImageIcon("images/"+this.getClass().getResource(body));
        }
        image = ii.getImage();
        x = 400;
        y = 300;
        width = image.getWidth(null);
        height = image.getHeight(null);
        index += 1;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getIndex() {
        return index;
    }
    
    public Image getImage() {
        return image;
    }

    public Snake getProximo() {
        return this.proximo;
    }
    
    public void setX(int x) {
        this.x += x;
    }
    
    public void setY(int y) {
        this.y += y;
    }
    
    public void setProximo(Snake snake) {
        this.proximo = snake;
    }
    
}
