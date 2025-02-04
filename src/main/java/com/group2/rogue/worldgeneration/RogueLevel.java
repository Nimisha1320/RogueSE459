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
                
            }
        }
    }

}
