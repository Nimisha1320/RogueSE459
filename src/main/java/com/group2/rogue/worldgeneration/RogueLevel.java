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
                    roomWidth = random.nextInt(maxRoomSize - minRoomSize + 1) + minRoomSize;
                    roomHeight = random.nextInt(maxRoomSize - minRoomSize + 1) + minRoomSize;

                    int spacingX = random.nextInt(3) + 2;
                    int spacingY = random.nextInt(2) + 1;
                    int startX = col * (levelWidth / 3) + spacingX;
                    int startY = row * (levelHeight / 3) + spacingY;

                    if (canPlaceRoom(startX, startY, roomWidth, roomHeight)) {
                        roomExists[row][col] = true;
                        makeRoom(startX, startY, roomWidth, roomHeight);
                    }

                    // roomExists[row][col] = true;
                    // roomWidth = random.nextInt(maxRoomSize - minRoomSize + 1) + minRoomSize;
                    // roomHeight = random.nextInt(maxRoomSize - minRoomSize + 1) + minRoomSize;
                    // makeRoom(row, col, roomWidth, roomHeight);
                }
            }
        }
    }

    private static boolean canPlaceRoom(int startX, int startY, int width, int height) {
        int padding = 2;
        
        if (startX - padding < 0 || startY - padding < 0 || 
            startX + width + padding >= levelWidth || 
            startY + height + padding >= levelHeight) {
            return false;
        }
        
        for (int y = startY - padding; y <= startY + height + padding; y++) {
            for (int x = startX - padding; x <= startX + width + padding; x++) {
                if (level[y][x] != EMPTY) {
                    return false;
                }
            }
        }
        return true;
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
