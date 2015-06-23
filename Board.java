import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JPanel;
import javax.swing.Timer;
import java.io.File;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Score score;
    private Fila fila = new Fila();
    
    private boolean isPlaying = true;
    private String direcao = "parado";

    private Font font;
       
    public Board() {

        addKeyListener(new TAdapter());
        
        setFocusable(true);        
        setDoubleBuffered(true);
        setBackground(Color.WHITE);

        score = new Score();
        add(score);       
        
        timer = new Timer(5, this);
        timer.start();
        
        Snake snake = fila.getHead();
        while (snake.getProximo() != null) {
            add(snake);
            snake = snake.getProximo();
        }
    }


    public void paint(Graphics g) {
        super.paint(g);
        
        score.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D)g;
        
        if (isPlaying) {
            Snake head = fila.getHead();
            if(head.getProximo() == null) {
                g2d.drawImage(head.getImage(), head.getX(), head.getY(), this);
            } else {
                while (head.getProximo() != null) {
                    add(head);
                    head = head.getProximo();
                }
            }
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }


    public void paintIntro(Graphics g) {
        if(isPlaying){
            isPlaying = false;
            Graphics2D g2d = (Graphics2D) g;
            try{
                File file = new File("fonts/VT323-Regular.ttf");
                font = Font.createFont(Font.TRUETYPE_FONT, file);
                GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
                ge.registerFont(font);
                font = font.deriveFont(Font.PLAIN,40);
                g2d.setFont(font);
            }catch (Exception e){
                System.out.println(e.toString());
            }   
            g2d.drawString("S N A K E: " + this.score, 300, 300);
        }
    }
    
    public void actionPerformed(ActionEvent e) {        
        repaint();
        mover();        
    }
    
    public void mover() {
        switch (direcao) {
            case "esquerda":
                fila.getHead().setX(-1);
                fila.getHead().setY(0);
                break;
                
            case "direita":
                fila.getHead().setX(1);
                fila.getHead().setY(0);
                break;
                
            case "cima":
                fila.getHead().setX(0);
                fila.getHead().setY(-1);
                break;
                
            case "baixo":
                fila.getHead().setX(0);
                fila.getHead().setY(1);
                break;
                
            case "parado":
                fila.getHead().setX(0);
                fila.getHead().setY(0);
                break;
        }
    }
    
    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            
            // Obtém o código da tecla
            int key =  e.getKeyCode();

            switch (key){
                case KeyEvent.VK_ENTER:
                    fila.inserir(new Snake());
                    break;
                    
                case KeyEvent.VK_LEFT:
                    direcao = "esquerda";
                    fila.getHead().setImage(direcao);
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    direcao = "direita";
                    fila.getHead().setImage(direcao);
                    break;
                    
                case KeyEvent.VK_UP:
                    direcao = "cima";
                    fila.getHead().setImage(direcao);
                    break;
                    
                case KeyEvent.VK_DOWN:
                    direcao = "baixo";
                    fila.getHead().setImage(direcao);
                    break;
            }
            
        }
    }
    
}