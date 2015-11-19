package com.company;

import javax.swing.*;

/**
 * Created by Husna on 11/17/2015.
 */


    import java.awt.Color;
    import java.awt.Font;
    import java.awt.Graphics;
    import java.awt.Image;


public class a extends JFrame{
        Image image;


        public a(){
            this.setTitle("Remember g for Graphics");
            this.setSize(450,350);
            //this.setDefaultCloseOperation(JFrame.EXI­T_ON_CLOSE);
            this.setVisible(true);


        }
        public void paint(Graphics g){
            ImageIcon i = new ImageIcon("image/Abstract-background-1.png");
            image = i.getImage();
            g.drawImage(image, 0, 0, null);
            g.setColor(Color.BLACK);
            g.drawRect(40, 40, 30, 30);
            g.setColor(Color.RED);
            g.drawOval(80, 80, 30, 30);
           g.fillArc(140, 140, 30, 30, 180, 10);
            g.drawLine(100, 300, 350, 400);
            g.setFont(new Font("Arial",Font.BOLD,34));
            g.drawString("Hwello !", 150, 100);
           Image cat = new ImageIcon("image/cat.png").getImage();

            g.drawImage(cat, 100, 200, this);

        }

        public static void main(String[] args){
            new a(); }

    }