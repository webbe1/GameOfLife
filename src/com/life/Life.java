package com.life;


public class Life {

    private boolean[][] world;
    private int size;
    private int generation;
    Life(int size){
        this.size = size;
        createNewWorld();
        this.generation = 0;
    }

    // Contains the starting scenario logic
    // Which cells are alive/dead at the start
    private void createNewWorld(){
        boolean[][] newWorld = new boolean[size][size];
        for(int row = 0; row < newWorld.length; row++ ){
            for(int col = 0; col < newWorld[row].length; col++ ){
                newWorld[row][col] = (Math.random() < 0.3);
            }
        }
        world = newWorld;
    }

    // Draws the world on the screen
    public void drawWorld(){
        System.out.println("Generation:" + generation);
        for(int row = 0; row < world.length; row++ ){
            for(int col = 0; col < world[row].length; col++ ){
                System.out.print(world[row][col] ? '@' : '.');
                System.out.print(' ');
            }
            System.out.println();
        }
    }

    // Create the next generation
    public void nextGeneration(){
        boolean[][] newWorld = new boolean[size][size];
        for(int row = 0; row < newWorld.length; row++ ){
            for(int col = 0; col < newWorld[row].length; col++ ){
                newWorld[row][col] = isAlive(row, col);
            }
        }
        world = newWorld;
        generation++;
    }

    /* Calculate if an individual cell should be alive in the next generation.
       Based on the game logic:
		Any live cell with fewer than two live neighbours dies, as if by needs caused by underpopulation.
		Any live cell with more than three live neighbours dies, as if by overcrowding.
		Any live cell with two or three live neighbours lives, unchanged, to the next generation.
		Any dead cell with exactly three live neighbours cells will come to life.
	*/
    private boolean isAlive(int row, int col){
        int liveCount = 0;
        boolean cellCurrentlyAlive = world[row][col];

        for(int r = -1; r <= 1; r++){
            int currentRow = row + r;
            currentRow = (currentRow < 0)? size - 1: currentRow;
            currentRow = (currentRow >= size)? 0 : currentRow;
            for(int c = -1; c <= 1; c++){
                int currentCol = col + c;
                currentCol = (currentCol < 0)? size - 1: currentCol;
                currentCol = (currentCol >= size)? 0 : currentCol;
                if(world[currentRow][currentCol]){
                    liveCount++;
                }
            }
        }

        // Subtract from the live count otherwise it will count itself if it is alive
        if(cellCurrentlyAlive){
            liveCount--;
        }


        if(liveCount == 2 && cellCurrentlyAlive){
            return true;
        } else if(liveCount == 3){
            return true;
        } else {
            return false;
        }
    }
}
