import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.*;
import javax.swing.*;
import javax.swing.ImageIcon;
import java.awt.Rectangle;

/**
 * Write a description of class Snake here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class Snake extends JPanel
{
    private String headLeft = "images/headL.png";
    private String headRight = "images/headR.png";
    private String headUp = "images/headU.png";
    private String headDown = "images/headD.png";
    private String body = "images/body.png";
    private int x;
    private int y;
    private int width;
    private int height;
    private Image image;
    private Snake proximo;
    private int index;
    private String direcao;
    private Rectangle hitbox;
    
    public Snake() {
        this(400, 300, "esquerda");
    }
    
    public Snake(int x, int y, String direcao) {
        ImageIcon ii;
        if (index == 0) {
            ii = new ImageIcon(this.getClass().getResource(headLeft));
        } else {
            switch(direcao) {
                case "cima":
                    ii = new ImageIcon(this.getClass().getResource(headUp));
                    width = image.getWidth(null);
                    height = image.getHeight(null);
                    break;
                case "baixo":
                    ii = new ImageIcon(this.getClass().getResource(headDown));
                    width = image.getWidth(null);
                    height = image.getHeight(null);
                    break;
                case "direita":
                    ii = new ImageIcon(this.getClass().getResource(headRight));
                    width = image.getWidth(null);
                    height = image.getHeight(null);
                    break;
                case "esquerda":
                    ii = new ImageIcon(this.getClass().getResource(headLeft));
                    width = image.getWidth(null);
                    height = image.getHeight(null);
                    break;
            }
        }
        this.x = x;
        this.y = y;
    }
    
    public Snake getProximo() {
        return this.proximo;
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
    
    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public Rectangle getHitbox(){
        return hitbox;
    }
    
    public void setProximo(Snake _snake) {
        this.proximo = _snake;
    }
    
    public void setHitbox(int p1, int p2, int largura, int altura) {
        hitbox = new Rectangle(p1, p2, largura, altura);
    }
    
    public void setImage(){
        ImageIcon ii = new ImageIcon(this.getClass().getResource(body));
        image = ii.getImage();
        width = image.getWidth(null);
        height = image.getHeight(null);
        setHitbox(x, y, width, height);
    }
}
