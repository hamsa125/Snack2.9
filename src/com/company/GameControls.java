package com.company;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.concurrent.TimeUnit;

import static com.company.SnakeGame.clockInterval;
import static com.company.SnakeGame.getGameStage;

public class GameControls implements KeyListener{

    public static boolean paused = true;
    public static boolean isWall = false;
    public static boolean isbulidWall = false;

    Snake snake;

    GameControls(Snake s){
        this.snake = s;
    }

    public void keyPressed(KeyEvent ev) {
        //keyPressed events are for catching events like function keys, enter, arrow keys
        //We want to listen for arrow keys to move snake
        //Has to id if user pressed arrow key, and if so, send info to Snake object

        //is game running? No? tell the game to draw grid, start, etc.

        //Get the component which generated this event
        //Hopefully, a DrawSnakeGamePanel object.
        //It would be a good idea to catch a ClassCastException here.

        DrawSnakeGamePanel panel = (DrawSnakeGamePanel)ev.getComponent();

        if (SnakeGame.getGameStage() == SnakeGame.BEFORE_GAME){
            //Start the game
            if(ev.getKeyCode()==KeyEvent.VK_Q){
                System.exit(0);}
            if (ev.getKeyCode()==KeyEvent.VK_ENTER){
                SnakeGame.setGameStage(SnakeGame.DURING_GAME);
            SnakeGame.newGame();
            panel.repaint();}
            if(ev.getKeyCode()==KeyEvent.VK_F){SnakeGame.clockInterval =100;}
            if(ev.getKeyCode()==KeyEvent.VK_S){SnakeGame.clockInterval =600;}
            if(ev.getKeyCode()==KeyEvent.VK_N){SnakeGame.xPixelMaxDimension = 601 ; SnakeGame.yPixelMaxDimension = 601;SnakeGame.snakeFrame.setSize(601,602);}
                if(ev.getKeyCode()==KeyEvent.VK_M){SnakeGame.xPixelMaxDimension = 1201 ; SnakeGame.yPixelMaxDimension = 601;SnakeGame.snakeFrame.setSize(1201,601);}

            return;
        }

        if (SnakeGame.getGameStage() == SnakeGame.GAME_OVER){
            snake.reset();
            Score.resetScore();

            //Need to start the timer and start the game again
            if(ev.getKeyCode()==KeyEvent.VK_ENTER){
            SnakeGame.newGame();
            SnakeGame.setGameStage(SnakeGame.DURING_GAME);
            panel.repaint();}
            if(ev.getKeyCode()==KeyEvent.VK_Q){System.exit(0);}
            return;
        }


        if (ev.getKeyCode() == KeyEvent.VK_DOWN) {
            //System.out.println("snake down");
            snake.snakeDown();
        }
        if (ev.getKeyCode() == KeyEvent.VK_UP) {
            //System.out.println("snake up");
            snake.snakeUp();
        }
        if (ev.getKeyCode() == KeyEvent.VK_LEFT) {
            //System.out.println("snake left");
            snake.snakeLeft();
        }
        if (ev.getKeyCode() == KeyEvent.VK_RIGHT) {
            //System.out.println("snake right");
            snake.snakeRight();
        }
        if (ev.getKeyCode() == KeyEvent.VK_Q) {
            System.exit(0);    //quit if user presses the q key.
        }

        if (ev.getKeyCode()==KeyEvent.VK_W)    {if(!isWall){isWall=true;}else{isWall = false;}System.out.print(isWall);}
        if (ev.getKeyCode()==KeyEvent.VK_SPACE){if(!isbulidWall){isbulidWall=true;}else{isbulidWall = false;}System.out.print(isbulidWall);}
        //if (ev.getKeyCode()==KeyEvent.VK_W){try{SnakeGame.timer.wait();SnakeGame.setClockInterval(getGameStage()+(SnakeGame.getClockInterval()/2)); SnakeGame.timer.scheduleAtFixedRate(SnakeGame.clockTick, 0 , SnakeGame.getClockInterval());;}catch(Exception e){System.out.print(e);}}
        //if (ev.getKeyCode()==KeyEvent.VK_S){SnakeGame.setClockInterval(getGameStage()+(SnakeGame.getClockInterval()*2));}

        //easteregg
        if (ev.getKeyCode()==KeyEvent.VK_H) { SnakeGame.setGameStage(SnakeGame.GAME_WON);}



        // fix pause

            if(ev.getKeyCode() == KeyEvent.VK_P || ev.getKeyCode() == KeyEvent.VK_O) {
                try {

                    if (!paused) {
                        SnakeGame.newGame();


                        paused = true;
                    }
                   else {
                        SnakeGame.timer.cancel();

                        paused =false;
                    }
                } catch (Exception e) {
                }
            }



    }


    public static void pause(KeyEvent ev){}

    @Override
    public void keyReleased(KeyEvent ev) {
        //We don't care about keyReleased events, but are required to implement this method anyway.

    }


    @Override
    public void keyTyped(KeyEvent ev) {
// MOVED q FOR QUIT, CODE JUST DID NOT WORK HERE
    }

}