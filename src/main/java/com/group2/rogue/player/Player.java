package com.group2.rogue.player;

import com.group2.rogue.worldgeneration.RogueLevel;

import java.io.IOException;

public class Player {
    private int x, y;
    private char[][] dungeonMap;
    private static final char PLAYER_ICON = '@';
    //player stats
    private static int level = 1;
    private static int hits = 12;  //the number of health points the player has, if this number reaches 0 the player dies
    private static int strength = 16;  // strength of the player, influences how much damage they do
    private static int gold = 0;
    private static int armor = 5; 
    private static int experience = 0;

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


    private void printPosition() {
        System.out.println("Player is at (" + x + ", " + y + ")");
    }

    public String getStats() {
        return String.format("Level: %d  Gold: %d  Hp: %d(%d)  Str: %d  Armor: %d  Exp: %d/%d",
            level, gold, hits, hits, strength, armor, level, experience);
    }
    


    public int getX() { return x; }
    public int getY() { return y; }
}
