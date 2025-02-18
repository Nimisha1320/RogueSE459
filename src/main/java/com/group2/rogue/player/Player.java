package com.group2.rogue.player;

import com.group2.rogue.worldgeneration.RogueLevel;

import java.io.IOException;

public class Player {
    private int x, y;
    private char[][] dungeonMap;
    private static final char PLAYER_ICON = '@';

    public Player(RogueLevel dungeon) {
        this.dungeonMap = dungeon.getMap();
        int[] startingRoom = dungeon.getStartingRoom();

        if (startingRoom != null) {
            this.x = startingRoom[0];
            this.y = startingRoom[1];
        }


    }

    public void movePlayer(char direction) {
        int newX = x, newY = y;

        switch (direction) {
            case 'W': newY--; break;
            case 'S': newY++; break;
            case 'A': newX--; break;
            case 'D': newX++; break;
            default: return;
        }

        if (isWalkable(newX, newY)) {
            x = newX;
            y = newY;
        }
    }

    private boolean isWalkable(int newX, int newY) {
        if (newX < 0 || newY < 0 || newX >= dungeonMap[0].length || newY >= dungeonMap.length) {
            return false;
        }
        char tile = dungeonMap[newY][newX];
        return tile == '.' || tile == '+'; // can move on floor and in hallways
    }

    // public void listenForInput() {
    //     try {
    //         enableRawMode(); // Enables raw mode so we can detect key presses instantly

    //         while (true) {
    //             char key = (char) System.in.read(); // Reads a single character
    //             if (key == 'q') break; // Allow quitting with 'q'
    //             movePlayer(key);
    //         }
    //     } catch (IOException e) {
    //         e.printStackTrace();
    //     } finally {
    //         disableRawMode(); // Restore terminal settings on exit
    //     }
    // }

    private void printPosition() {
        System.out.println("Player is at (" + x + ", " + y + ")");
    }



    public int getX() { return x; }
    public int getY() { return y; }
}
