package com.group2.rogue.worldgeneration;

import java.util.Random;

public class RogueLevel {
    private static final int levelWidth = 80;
    private static final int levelHeight = 24;
    private static final int minRoomSize = 3;
    private static final int maxRoomSize = 11;
    private static final char FLOOR = '.';
    private static final char WALL = '#';
    private static final char HALLWAY = '+';
    private static final char EMPTY = ' ';
    private static Random random = new Random();

    private static char[][] level = new char[levelHeight][levelWidth];
    private static boolean[][] roomExists = new boolean[3][3];

    public static void main(String[] args) {
        initializeLevel();
        generateRooms();
        printLevel();

    }

    public static void printLevel(){
        for(int i = 0; i < levelHeight; i++){
            for(int j = 0; j < levelWidth; j++){
                System.out.print(level[i][j]);
            }
            System.out.println();
        }
    }

    private static void initializeLevel(){
        for(int i = 0; i < levelHeight; i++){
            for(int j = 0; j < levelWidth; j++){
                level[i][j] = EMPTY;
            }
        }
    }

    private static void generateRooms(){
        int roomWidth;
        int roomHeight;

        for(int row = 0; row < 3; row++){
            for(int col = 0; col < 3; col++){
                if(random.nextDouble() < 0.7){
                    roomExists[row][col] = true;
                    roomWidth = random.nextInt(maxRoomSize - minRoomSize + 1) + minRoomSize;
                    roomHeight = random.nextInt(maxRoomSize - minRoomSize + 1) + minRoomSize;
                    makeRoom(row, col, roomWidth, roomHeight);
                }
            }
        }
    }

    private static void makeRoom(int row, int col, int roomWidth, int roomHeight){
        int spacingX = random.nextInt(3)+2;
        int spacingY = random.nextInt(2)+1;

        int startingX = col * (levelWidth / 3) + random.nextInt(3);
        int startingY =  row * (levelHeight / 3) + random.nextInt(2);

        if (random.nextBoolean()) {
            roomWidth += random.nextInt(3) + 2;  
        } else {
            roomHeight += random.nextInt(3) + 2; 
        }

        for (int i = startingY; i < startingY + roomHeight && i<levelHeight-1; i++){
            for(int j = startingX; j < startingX + roomWidth && j<levelWidth-1; j++){
                if (i >= 0 && i < levelHeight && j >= 0 && j < levelWidth) {
                    // If it's at the edge of the room, it's a wall
                    if (i == startingY - 1 || i == startingY + roomHeight ||
                        j == startingX - 1 || j == startingX + roomWidth) {
                        if (level[i][j] == EMPTY) {
                            level[i][j] = WALL; // Place wall only in empty spots
                        }
                    } else {
                        level[i][j] = FLOOR; // Inside the room remains floor
                    }
                }
            }
        }
    }



}
