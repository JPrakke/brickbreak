package brickBreak;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Gameplay extends JPanel implements KeyListener, ActionListener {
    private boolean play = false;

    private String title = "Welcome to Brick Breaker!";
    private String directions = "Press Any Key to continue";

    private int score = 0;
    private int totalBricks = 21;

    private Timer timer;
    private int delay = 8;

    private int playerX = 310;
    private int ballPosX = 120;
    private int ballPosY = 350;
    private int ballXDir = -1;
    private int ballYDir = -2;

    public Gameplay() {
        addKeyListener(this);
        setFocusable(true);
        setFocusTraversalKeysEnabled(false);
        timer = new Timer(delay, this);
        timer.start();
    }

    public void paint(Graphics g){
        // background

        // (x, y, width, height)

        g.setColor(Color.black);
        g.fillRect(1, 1, 700 ,600);

        //borders
        g.setColor(Color.darkGray);
        g.fillRect(0,0,3, 592);
        g.fillRect(0,0,697, 3);
        g.fillRect(697,0,3, 592);


        //paddle
        g.setColor(Color.GREEN);
        g.fillRect(playerX, 550, 100, 8);

        //ball
        g.setColor(Color.white);
        g.fillOval(ballPosX, ballPosY, 10, 10);

        //bricks
        g.setColor(Color.orange);
        g.fillRect(100,100, 75,50);

        g.dispose();


    }


    @Override
    public void actionPerformed(ActionEvent actionEvent) {
        timer.start();

        if(play){
            if(new Rectangle(ballPosX, ballPosY, 10, 10).intersects(new Rectangle(playerX, 550, 100, 8))){
                ballYDir = - ballYDir;
            }

            ballPosX += ballXDir;
            ballPosY += ballYDir;

            if(ballPosX == 10){
                ballXDir = -ballXDir;
            }
            if(ballPosX == 700){
                ballXDir = -ballXDir;
            }
            if(ballPosY == 10){
                ballYDir = -ballYDir;
            }
            if(ballPosY == 600){
                ballYDir = -ballYDir;
            }
        }
        repaint();
    }

    @Override
    public void keyTyped(KeyEvent keyEvent) { }

    @Override
    public void keyPressed(KeyEvent keyEvent) {
        if(keyEvent.getKeyCode() == KeyEvent.VK_RIGHT){
            if(playerX >= 590){
                playerX = 590;
            }else{
                moveRight();
            }
        }
        if(keyEvent.getKeyCode() == KeyEvent.VK_LEFT){
            if(playerX <= 10){
                playerX = 10;
            }else{
                moveLeft();
            }
        }
    }
    public void moveRight(){
        play = true;
        playerX += 20;
    }
    public void moveLeft(){
        play = true;
        playerX -= 20;
    }

    @Override
    public void keyReleased(KeyEvent keyEvent) { }
}
