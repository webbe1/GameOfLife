package com.life;

public class Main {

    public static void main(String[] args) throws java.lang.InterruptedException{
            Life earth = new Life(8);
            earth.drawWorld();
            int numofgenerations = 6;
            for(int x = 0; x <= numofgenerations; x++){
                Thread.sleep(200);
                earth.nextGeneration();
                earth.drawWorld();
            }
        }
    }

