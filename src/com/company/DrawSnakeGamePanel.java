package com.company;


        import com.sun.javafx.scene.control.skin.ColorPickerSkin;

        import java.awt.*;
        import java.awt.image.ImageObserver;
        import java.util.LinkedList;
        import java.util.Random;

        import javax.swing.*;

        import static com.company.SnakeGame.clockInterval;
        import static com.company.SnakeGame.getClockInterval;

/** This class responsible for displaying the graphics, so the snake, grid, kibble, instruction text and high score
 *
 * @author Clara
 *
 */



public class DrawSnakeGamePanel extends JPanel {
    private Image cat;

    private Image getImage(ImageIcon name) {

        return null;

    }
    private static int gameStage = SnakeGame.BEFORE_GAME;  //use this to figure out what to paint

    private Snake snake;
    private Kibble kibble;
    private Score score;

    DrawSnakeGamePanel(Snake s, Kibble k, Score sc){
        this.snake = s;
        this.kibble = k;
        this.score = sc;
    }

    public Dimension getPreferredSize() {
        return new Dimension(SnakeGame.xPixelMaxDimension, SnakeGame.yPixelMaxDimension);
    }

    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        /* Where are we at in the game? 4 phases..
         * 1. Before game starts
         * 2. During game
         * 3. Game lost aka game over
         * 4. or, game won
         */

        gameStage = SnakeGame.getGameStage();

        switch (gameStage) {
            case 1: {
                displayInstructions(g);
                break;
            }
            case 2 : {
                displayGame(g);
                break;
            }
            case 3: {
                displayGameOver(g);
                break;
            }
            case 4: {
                displayGameWon(g);
                break;
            }
        }



    }

    private void displayGameWon(Graphics g) {
        // TODO Replace this with something really special!
        Image GO = new ImageIcon("image/trophy.jpg").getImage();
        //leaned from Java programming part 92 Graphics by Redemptie( https://www.youtube.com/watch?v=3ApnMEWsrhs )
        g.drawImage(GO,100,100,350,350,0,0,256,192,null);
        //g.clearRect(100,100,350,350);
        g.setColor(Color.getHSBColor(255,215,0));
        //g.drawString("YOU WON SNAKE!!!", 175, 328);

    }
    private void displayGameOver(Graphics g) {
        Image GO = new ImageIcon("image/gameover.jpg").getImage();
        //leaned from Java programming part 92 Graphics by Redemptie( https://www.youtube.com/watch?v=3ApnMEWsrhs )
        g.drawImage(GO,0,0,600,200,0,0,336,150,null);


        g.clearRect(100,200,350,350);
      // g.drawString("TRY AGAIN !", 150, 150);

        String textScore = score.getStringScore();
        String textHighScore = score.getStringHighScore();
        String newHighScore = score.newHighScore();

        g.drawString("SCORE = " + textScore, 150, 250);

        g.drawString("HIGH SCORE = " + textHighScore, 150, 300);
        g.drawString(newHighScore, 150, 400);

        g.drawString("Press Enter key to play again", 150, 350);
        g.drawString("Press q to quit the game",150,400);

    }

    private void displayGame(Graphics g) {
       displayGameGrid(g);//and background
        displaySnake(g);
        displayKibble(g);

        DisplayWalls(g);
        displayScore(g);
    }

    private void DisplayWalls(Graphics g) {
if (GameControls.isbulidWall) {
    g.setColor(Color.DARK_GRAY);
    //g.fillRect(0,0,SnakeGame.squareSize,SnakeGame.squareSize);
    for (int x = 0; x <= SnakeGame.xPixelMaxDimension; x++) {
        g.fillRect(x, 0, SnakeGame.squareSize, SnakeGame.squareSize);
        g.fillRect(x, SnakeGame.yPixelMaxDimension - SnakeGame.squareSize, SnakeGame.squareSize, SnakeGame.squareSize);
    }
    for (int y = 0; y <= SnakeGame.yPixelMaxDimension; y++) {
        g.fillRect(0, y, SnakeGame.squareSize, SnakeGame.squareSize);
        g.fillRect(SnakeGame.xPixelMaxDimension - SnakeGame.squareSize, y, SnakeGame.squareSize, SnakeGame.squareSize);
    }
}
        //sdddddddddddddddddddddddd
    }

    private void displayScore(Graphics g) {

        //diplay text
        g.setColor(Color.yellow);
        g.setFont(new Font("Roman",Font.BOLD,10));


        //worp
        g.drawString("Press W to Space worp : " +GameControls.isWall, 10,55);
        //score
        g.drawString("Press Space to open Old snacke lvl 1 wall maze : " +GameControls.isbulidWall, 10,65);
        //diplay text
        g.setColor(Color.white);
        g.setFont(new Font("Arial",Font.BOLD,34));
        g.drawString("score!", 150,34);
        g.drawString( score.getStringScore(), 300,34);
        //high score
       g.drawString("Hi score!", 750, 34);
        g.drawString(score.getStringHighScore(), 920, 34);
    }

    private void displayGameGrid(Graphics g) {


        int maxX = SnakeGame.xPixelMaxDimension;
        int maxY= SnakeGame.yPixelMaxDimension;
        int squareSize = SnakeGame.squareSize;

        LinkedList<Point> coordinates = snake.segmentsToDraw();
        Image sky = new ImageIcon("image/sky.jpg").getImage();


        //leaned from Java programming part 92 Graphics by Redemptie( https://www.youtube.com/watch?v=3ApnMEWsrhs )
        g.drawImage(sky,0,0,maxX,maxY,0,0,1680,1050,null);



       // g.clearRect(0, 0, maxX, maxY);


                //Test display
       // System.out.print(getClockInterval());
        //System.out.print(SnakeGame.sec);
        SnakeGame.sec++ ;
        //g.setColor(Color.black);

                                                //Draw grid - horizontal lines
//        for (int y=0; y <= maxY ; y+= squareSize){
//            g.drawLine(0, y, maxX, y);
//        }
//        //Draw grid - vertical lines
//        for (int x=0; x <= maxX ; x+= squareSize){
//            g.drawLine(x, 0, x, maxY);
//        }
    }

    private void displayKibble(Graphics g) {

        LinkedList<Point> coordinates = snake.segmentsToDraw();
        Image star = new ImageIcon("image/star.png").getImage();

        int x = kibble.getKibbleX() * SnakeGame.squareSize;
        int y = kibble.getKibbleY() * SnakeGame.squareSize;

        //leaned from Java programming part 92 Graphics by Redemptie( https://www.youtube.com/watch?v=3ApnMEWsrhs )
        g.drawImage(star,x, y,x+ SnakeGame.squareSize, y+ SnakeGame.squareSize,0,0,512,512,null);


    }


    private void displaySnake(Graphics g) {

        LinkedList<Point> coordinates = snake.segmentsToDraw();

        Image cat = new ImageIcon("image/cat.png").getImage();
        //Draw head in grey
        g.setColor(Color.blue);
        Point head = coordinates.pop();
       // (cat, 100, 200, this);

        //leaned from Java programming part 92 Graphics by Redemptie( https://www.youtube.com/watch?v=3ApnMEWsrhs )
        g.drawImage(cat,(int)head.getX(),(int)head.getY(),((int)head.getX()+SnakeGame.squareSize),((int)head.getY()+SnakeGame.squareSize),0,0,300,300,null);

        //g.fillRect((int)head.getX(), (int)head.getY(), SnakeGame.squareSize, SnakeGame.squareSize);


        //BODY

        //Draw rest of snake in green
       // g.setColor(Color.GREEN);
        //random
        final Random r=new Random();
        int COLOR = (int) (Math.random()*256 );
        ;

        for (Point p : coordinates) {
            //random color
            Color color =new Color(r.nextInt(250),r.nextInt(250),r.nextInt(250));
            g.setColor(color);
            //fill in the color
            g.fillRect((int)p.getX(), (int)p.getY(), SnakeGame.squareSize, SnakeGame.squareSize);
        }

    }

    private void displayInstructions(Graphics g) {
        LinkedList<Point> coordinates = snake.segmentsToDraw();
        //image
        Image mctc = new ImageIcon("image/mctc.png").getImage();
        //leaned from Java programming part 92 Graphics by Redemptie( https://www.youtube.com/watch?v=3ApnMEWsrhs )
        g.drawImage(mctc,0,0,100,100,0,0,125,125,null);
        //text
        g.setFont(new Font("Arial",Font.BOLD,30));
        g.drawString("Cool Snake Game",50,150);

        g.drawString("Press M or N to change window size !",50,250);

        g.drawString("Press Q to quit the game",50,300);
        g.drawString("Press F for a fast game ",50,350);
        g.drawString("Press S for a slow game ",50,400);
        g.drawString("Press the Enter key when you ready!",50,450);
    }


}