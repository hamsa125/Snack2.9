package com.company;


import java.io.*;

/** Keeps track of, and display the user's score
 *
 */


public class Score {

    protected static int score;
    public static int highScore = 0;
    protected static int increment;

    public Score(){
        score = 0;
        increment = 1;  //how many points for eating a kibble
        //Possible TODO get more points for eating kibbles, the longer the snake gets?
    }

    public static void resetScore() {
        score = 0;
    }

    public static void increaseScore() {

        score = score + increment;

    }

    public int getScore(){
        return score;
    }

    //Checks if current score is greater than the current high score.
    //updates high score and returns true if so.

    public boolean gameOver(){

        if (score > highScore) {
            highScore = score;
            return true;
        }
        return false;
    }

    //These methods are useful for displaying score at the end of the game

    public String getStringScore() {
        return Integer.toString(score);
    }

    public String newHighScore() {

        if (score > highScore) {
            highScore = score;
            //write to text
//            try {
//              //  setHiScore( highScore);
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
            return "New High Score!!";
        } else {
            return "";
        }
    }

    public String getStringHighScore() {

        return Integer.toString(highScore);
    }


    //get highest Score from file
//    public static void getHiScore()throws IOException {
//        BufferedReader br = new BufferedReader( new FileReader("highscore-memory.txt"));
//
//        try {
//            StringBuilder sb = new StringBuilder();
//            String line = br.readLine();
//
//            while (line != null) {
//                sb.append(line);
//               // sb.append(System.lineSeparator());
//                line = br.readLine();
//            }
//            String everything = sb.toString();
//            System.out.print(everything);
//        } finally {
//            br.close();
//        }
//
//    }
//    //wirte new high Score
//    public static void setHiScore(int highScore)throws IOException {
//        try {
//            BufferedWriter writer = new BufferedWriter(new FileWriter("image/high score-memory.txt"));
//            writer.write(highScore);
//            //Close writer
//            writer.close();;
//        }catch (Exception e){System.out.print(e);}
//
//
//    }

}
