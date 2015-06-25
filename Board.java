import java.awt.*;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Random;
import java.awt.Rectangle;

import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.Timer;
import java.io.File;

public class Board extends JPanel implements ActionListener {

    private Timer timer;
    private Score score;
    private Fila fila = new Fila();
    private Food food;
    private Snake head;
    
    private boolean isPlaying = true;
    private String direcao = "parado";
    private boolean gameOver = false;
    private int movespeed = 1;

    private Font font;
       
    public Board() {

        addKeyListener(new TAdapter());
        
        setFocusable(true);        
        setDoubleBuffered(true);
        setBackground(Color.WHITE);

        score = new Score();
        add(score);       
        
        timer = new Timer(300, this);
        timer.start();

        food = new Food();
        
        head = new Snake();
        fila.inserir(head);
        add(head);
    }


    public void paint(Graphics g) {
        super.paint(g);
        
        score.paintComponent(g);
        
        Graphics2D g2d = (Graphics2D)g;
        
        if (isPlaying) {
           Snake aux = fila.getSnake();
           while (aux != null) {
               g2d.drawImage(aux.getImage(), aux.getX(), aux.getY(), this);               
               aux = aux.getProximo();
           }
           fila.getHead().setHitbox(fila.getHead().getX(), fila.getHead().getY(), fila.getHead().getWidth(), fila.getHead().getHeight());
           g2d.drawImage(food.getImage(), food.getX(), food.getY(), this);
           colisao();
           for (int i = 4; i < fila.getSize(); i++) {
                if (fila.getHead().getHitbox().intersects(fila.getSnake(i).getHitbox())) {
                    gameOver = true;
                }
           }
                      
        } else {
            gameOver = true;
        }

        Toolkit.getDefaultToolkit().sync();
        g.dispose();
        
    }

    public void colisao() {
        if (fila.getHead().getHitbox().intersects(food.getHitbox())) {
            food.randomize();
            score.addScore(10);
            fila.setMax((fila.getMax())+1);
        }
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
        if(!gameOver){
            mover();
            if (fila.getSize() > fila.getMax()) fila.remover();
        } else {
            JOptionPane.showMessageDialog (null, "Game over!\n Your score was: " + score.getScore());
            System.exit(0);
        }
    }
    
    
    public void mover() {
        switch (direcao) {
            case "esquerda":
                head = new Snake(fila.getHead().getX()-55, fila.getHead().getY(), "esquerda");
                fila.getHead().setImageBody();
                fila.inserir(head);             
                if (fila.getHead().getX() < 0) gameOver = true; 
                break;
                
            case "direita":
                head = new Snake(fila.getHead().getX()+45, fila.getHead().getY(), "direita");
                fila.getHead().setImageBody();
                fila.inserir(head);        
                if (fila.getHead().getX() > (800 - fila.getHead().getWidth())) gameOver = true; 
                break;
                
            case "cima":
                head = new Snake(fila.getHead().getX(), fila.getHead().getY()-55, "cima");
                fila.getHead().setImageBody();
                fila.inserir(head);        
                if (fila.getHead().getY() < 0) gameOver = true; 
                break;
                
            case "baixo":
                head = new Snake(fila.getHead().getX(), fila.getHead().getY()+35, "baixo");
                fila.getHead().setImageBody();
                fila.inserir(head);        
                if (fila.getHead().getY() > (600 - fila.getHead().getHeight())) gameOver = true;
                break;
        }
        
    }
    
    private class TAdapter extends KeyAdapter {

        public void keyPressed(KeyEvent e) {
            
            // Obtém o código da tecla
            int key =  e.getKeyCode();

            switch (key){
                case KeyEvent.VK_ENTER:
                    //fila.inserir(new Snake());
                    break;
                    
                case KeyEvent.VK_LEFT:
                    if (direcao != "direita") {
                        direcao = "esquerda";
                    }
                    break;
                    
                case KeyEvent.VK_RIGHT:
                    if (direcao != "esquerda") {
                        direcao = "direita";
                    }
                    break;
                    
                case KeyEvent.VK_UP:
                    if (direcao != "baixo") {
                        direcao = "cima";
                    }
                    break;
                    
                case KeyEvent.VK_DOWN:
                    if (direcao != "cima") {    
                        direcao = "baixo";
                    }
                    break;
            }
        }
    }
    
}