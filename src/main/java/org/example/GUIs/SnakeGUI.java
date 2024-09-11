package org.example.GUIs;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.EventListener;

import org.example.GUIs.Tile;
    public class SnakeGUI extends JPanel implements KeyListener,ActionListener {
        int boardWidth;
        int boardHeight;
        int tileSize = 25;

        Timer loop;
        //entities
        Tile snakeHead = new Tile(0, 0);
        Color tempCol = Color.GREEN;

        int velX =0;
        int velY =1;
        public SnakeGUI (int w, int h){
            this.boardHeight = h;
            this.boardWidth = w;
            setPreferredSize(new Dimension(boardWidth, boardHeight));
            setBackground(Color.BLACK);
            addKeyListener(this);
            setFocusable(true);

            loop = new Timer(100,this);

            System.out.println("Timer made");
            loop.start();
            System.out.println("Timer Ran");
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
        }

        public void move(){
            snakeHead.x +=velX*tileSize;
            snakeHead.y +=velY*tileSize;
        }

        @Override
        public void keyPressed(KeyEvent e) {
            System.out.println("keyPressed");
            System.out.println("Key pressed: " + e.getKeyChar());

            if(e.getKeyCode() == KeyEvent.VK_UP){
                velY =-1;
                velX = 0;
            }else if(e.getKeyCode() == KeyEvent.VK_DOWN){
                velY = 1;
                velX = 0;
            }else if(e.getKeyCode() == KeyEvent.VK_LEFT){
                velY = 0;
                velX = -1;
            }else if(e.getKeyCode() == KeyEvent.VK_RIGHT){
                velY = 0;
                velX = 1;
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {


            move();
            repaint();
        }


        //Unncessary methods
        @Override
        public void keyTyped(KeyEvent e) {

        }
        @Override
        public void keyReleased(KeyEvent e) {

        }



    }
