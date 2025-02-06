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
    private static int[][] centers = new int[9][2];

    private static final int gridRows = 3;
    private static final int gridCols = 3;

    public static void main(String[] args) {
        initializeLevel();
        generateRooms();
        connectRooms();
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
        

        for (int x = startX - 1; x <= startX + width; x++) {  //two for loops to make the walls, one for horiz, one for vert
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

        int roomIndex = (startY / (levelHeight / 3)) * 3 + (startX / (levelWidth / 3));
        centers[roomIndex][0] = startX + width/2;
        centers[roomIndex][1] = startY + height/2;
    }

    private static void connectRooms(){
        for(int row = 0; row < gridRows; row++) {   //horizontal connections
            for(int col = 0; col < gridCols-1; col++) {
                if(roomExists[row][col] && roomExists[row][col+1]) {
                    int room1Index = row * 3 + col;
                    int room2Index = row * 3 + (col + 1);
                    createHallway(
                        centers[room1Index][0], centers[room1Index][1],
                        centers[room2Index][0], centers[room2Index][1]
                    );
                }
            }
        }

        for(int col = 0; col < gridCols; col++) {   //vertical connections
            for(int row = 0; row < gridRows-1; row++) {
                if(roomExists[row][col] && roomExists[row+1][col]) {
                    int room1Index = row * 3 + col;
                    int room2Index = (row + 1) * 3 + col;
                    createHallway(
                        centers[room1Index][0], centers[room1Index][1],
                        centers[room2Index][0], centers[room2Index][1]
                    );
                }
            }
        }
    }
    
    private static void createHallway(int x1, int y1, int x2, int y2) {
        for(int x = Math.min(x1, x2); x <= Math.max(x1, x2); x++) {   //draws the horizontal part first
            if(level[y1][x] == EMPTY) level[y1][x] = HALLWAY;
            if(level[y1][x] == WALL) level[y1][x] = HALLWAY;
        }
        
        for(int y = Math.min(y1, y2); y <= Math.max(y1, y2); y++) {  //draws the vertical part next
            if(level[y][x2] == EMPTY) level[y][x2] = HALLWAY;
            if(level[y][x2] == WALL) level[y][x2] = HALLWAY;
        }
    }



}


