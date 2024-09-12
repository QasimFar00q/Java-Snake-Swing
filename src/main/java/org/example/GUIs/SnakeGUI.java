package org.example.GUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.EventListener;
import java.util.Random;

import org.example.GUIs.Tile;
    public class SnakeGUI extends JPanel implements KeyListener,ActionListener {
        int boardWidth;
        int boardHeight;
        int tileSize = 25;

        Timer loop;
        int score;
        //Fruit
        Tile fruit;
        //Player
        Tile snakeHead = new Tile(0, 0);
        Color tempCol = Color.GREEN;
        boolean dead= false;
        boolean win = false;
        int velX =0;
        int velY =1;
        ArrayList<Tile> snakeBody = new ArrayList<Tile>();
        Random r;

        public SnakeGUI (int w, int h){
            this.boardHeight = h;
            this.boardWidth = w;
            setPreferredSize(new Dimension(boardWidth, boardHeight));
            setBackground(Color.BLACK);
            addKeyListener(this);
            setFocusable(true);
            setVisible(true);
            loop = new Timer(100,this);
            r = new Random();
            fruit = new Tile(r.nextInt(25)*tileSize,r.nextInt(25)*tileSize);
            score = 0;
            loop.start();

        }

        @Override
        public void paintComponent(Graphics g){
            super.paintComponent(g);

            //Grid

            for(int i = 0;i < boardHeight/tileSize;i++){
                g.drawLine(0,i*tileSize,boardWidth,i*tileSize);
                g.drawLine(i*tileSize,0,i*tileSize,boardHeight);
            }


            //player
            g.setColor(tempCol); //PLEASE CHANGE
            g.fillRect(snakeHead.x, snakeHead.y, tileSize, tileSize);

            for (int i = 0; i < snakeBody.size(); i++) {

                g.fillRect(snakeBody.get(i).x, snakeBody.get(i).y, tileSize, tileSize);


            }


            //Fruit
            g.setColor(Color.RED);
            g.fillRect(fruit.x, fruit.y, tileSize,tileSize);

            //Score

            if(dead){
                g.setColor(Color.RED);
                g.drawString("GAME OVER: " + score,25,25);
            } else if (win) {
                g.setColor(Color.GREEN);
                g.drawString("GAME WON: " + score,25,25);
            }else{
                g.setColor(Color.WHITE);
                g.drawString("Score: " + score,25,25);
            }
        }

        public void move(){
            //Load next co-ordinates in order to verify if suitable to move onto
            int newXPos = snakeHead.x;
            int newYpos = snakeHead.y;
            newXPos += velX * tileSize;
            newYpos += velY * tileSize;



            if (!gameOver(newXPos,newYpos)) {
                if(!dead) {
                    int oldXpos = snakeHead.x;
                    int oldYpos = snakeHead.y;
                    //If not dead and next tile is valid to move, proceed with move
                    snakeHead.x = newXPos;
                    snakeHead.y = newYpos;
                    if(snakeBody.size() > 0) {
                        for (int i = 0; i < snakeBody.size(); i++) {

                            int currentBodyPosX = snakeBody.get(i).x;
                            int currentBodyPosY = snakeBody.get(i).y;
                            snakeBody.get(i).x = oldXpos;
                            snakeBody.get(i).y = oldYpos;
                            oldXpos = currentBodyPosX;
                            oldYpos = currentBodyPosY;
                        }
                    }

                    if(snakeHead.x == fruit.x && snakeHead.y == fruit.y ){
                        snakeBody.add(new Tile(999,999));
                        boolean fruitValid = false;
                        while (!fruitValid) {
                            fruitValid = true;
                            fruit = new Tile(r.nextInt(25)*tileSize,r.nextInt(25)*tileSize);
                            System.out.println("Random generated");
                            for (int i = 0; i < snakeBody.size(); i++) {

                               if(fruit.x == snakeBody.get(i).x && fruit.y == snakeBody.get(i).y){
                                   fruitValid = false;
                                   System.out.println("Body Collision");
                               }
                            }
                        }
                        fruit = new Tile(r.nextInt(25)*tileSize,r.nextInt(25)*tileSize);
                        score += 1;
                        System.out.println(score);
                    }
                }
            }else{
                dead = true;
            }
        }

        @Override
        public void keyPressed(KeyEvent e) {
                if(e.getKeyCode() == KeyEvent.VK_UP && velY != 1){
                    velY =-1;
                    velX = 0;
                }else if(e.getKeyCode() == KeyEvent.VK_DOWN && velY != -1 ){
                    velY = 1;
                    velX = 0;
                }else if(e.getKeyCode() == KeyEvent.VK_LEFT && velX != 1){
                    velY = 0;
                    velX = -1;
                }else if(e.getKeyCode() == KeyEvent.VK_RIGHT && velX != -1){
                    velY = 0;
                    velX = 1;
                }

        }

        @Override
        public void actionPerformed(ActionEvent e) {
            move();
            repaint();

            if (win){
                loop.stop();
            } else if (dead) {
                loop.stop();
            }
        }

        public boolean gameOver(int newX, int newY){


            if(score >= 624){
                win = true;
                return false;
            }
            if (newX >= 625 || newX < 0 || newY >= 625 || newY < 0 ){
                return true ;
            }else{
                boolean validMove = true;
                for (int i = 0; i < snakeBody.size(); i++) {
                    if (newX == snakeBody.get(i).x && newY == snakeBody.get(i).y) {
                        validMove = false;
                    }
                }
                if (validMove){
                    return false;
                }
                return  true;
            }
        }

        //Unncessary methods
        @Override
        public void keyTyped(KeyEvent e) {

        }
        @Override
        public void keyReleased(KeyEvent e) {

        }



    }
