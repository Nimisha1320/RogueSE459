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

    private static final int gridRows = 3;
    private static final int gridCols = 3;

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

    private static void generateRooms() {
        int sectionWidth = levelWidth/ gridCols;
        int sectionHeight = levelHeight / gridRows;

        int maxPossibleWidth = sectionWidth-4;
        int maxPossibleHeight = sectionHeight-4;

        int effectiveMaxWidth = Math.min(maxRoomSize, maxPossibleWidth);
        int effectiveMaxHeight = Math.min(maxRoomSize, maxPossibleHeight);

        for(int row = 0; row < gridRows; row++) {
            for(int col = 0; col < gridCols; col++) {
                if(random.nextDouble() < 0.75) {  // 75% chance to place a room

                    int sectionStartX = col * sectionWidth;  //get the bounds
                    int sectionStartY = row * sectionHeight;
                    
                    int roomWidth = random.nextInt(effectiveMaxWidth - minRoomSize + 1) + minRoomSize;  //dimensions
                    int roomHeight = random.nextInt(effectiveMaxHeight - minRoomSize + 1) + minRoomSize;

                    int maxOffsetX = sectionWidth - roomWidth - 4;
                    int maxOffsetY = sectionHeight - roomHeight - 4;

                    int offsetX = (maxOffsetX > 0) ? random.nextInt(maxOffsetX + 1) : 0;
                    int offsetY = (maxOffsetY > 0) ? random.nextInt(maxOffsetY + 1) : 0;
                    
                    int roomStartX = sectionStartX + 2 + offsetX;
                    int roomStartY = sectionStartY + 2 + offsetY;

                    makeRoom(roomStartX, roomStartY, roomWidth, roomHeight);
                    roomExists[row][col] = true;
                    

                }
            }
        }

    }


    private static void makeRoom(int startX, int startY, int width, int height) {
        for (int y = startY; y < startY + height; y++) {
            for (int x = startX; x < startX + width; x++) {
                level[y][x] = FLOOR;
            }
        }
        

        for (int x = startX - 1; x <= startX + width; x++) {
            if (x >= 0 && x < levelWidth) {
                if (startY - 1 >= 0) level[startY - 1][x] = WALL;
                if (startY + height < levelHeight) level[startY + height][x] = WALL;
            }
        }
        
        for (int y = startY - 1; y <= startY + height; y++) {
            if (y >= 0 && y < levelHeight) {
                if (startX - 1 >= 0) level[y][startX - 1] = WALL;
                if (startX + width < levelWidth) level[y][startX + width] = WALL;
            }
        }
    }
    



}


