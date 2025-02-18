package com.group2.rogue.worldgeneration;

import java.util.ArrayList;
import java.util.List;

import com.group2.rogue.player.Player;

public class World {
    private List <RogueLevel> levels;
    private RogueLevel level;  //points to the current level
    private Player player;

    public void generateWorld() {
        levels = new ArrayList<>();   //world has 9 levels
        for (int i = 0; i < 9; i++) {
            levels.add(new RogueLevel());
        }
        level = levels.get(0);
    }

    public void placePlayer() {
        player = new Player(level);
        //player.listenForInput();
    }

    public void movePlayer(char direction) {
        player.movePlayer(direction);
    }

    public void displayWorld() {
        char[][] map = level.getMap();
        int playerX = player.getX();
        int playerY = player.getY();

        for (int y = 0; y < map.length; y++) {
            for (int x = 0; x < map[y].length; x++) {
                if (x == playerX && y == playerY) {
                    System.out.print('@'); // Player icon
                } else {
                    System.out.print(map[y][x]);
                }
            }
            System.out.println();
        }
    }
}
