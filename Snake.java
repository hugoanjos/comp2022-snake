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
    private String direcao = "parado";
    
    public Snake() {
        ImageIcon ii;
        if (index == 0) {
            ii = new ImageIcon(this.getClass().getResource(headLeft));
        } else {
            ii = new ImageIcon(this.getClass().getResource(body));
        }
        image = ii.getImage();
        x = 400;
        y = 300;
        width = image.getWidth(null);
        height = image.getHeight(null);
        index += 1;
    }

    public int getWidth() {
        return width;
    }
    
    public int getHeight() {
        return height;
    }
    
    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }
    
    public String getDirecao() {
        return direcao;
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
    
    public void setDirecao(String direcao) {
        this.direcao = direcao;
    }
    
    public void setImage(String direcao) {
        ImageIcon ii;
        switch (direcao) {
            case "esquerda":
                ii = new ImageIcon(this.getClass().getResource(headLeft));
                break;
            case "direita":
                ii = new ImageIcon(this.getClass().getResource(headRight));
                break;
            case "cima":
                ii = new ImageIcon(this.getClass().getResource(headUp));
                break;
            case "baixo":
                ii = new ImageIcon(this.getClass().getResource(headDown));
                break;
            default:
                ii = new ImageIcon(this.getClass().getResource(headLeft));
                break;   
        }
        image = ii.getImage();
    }
}
