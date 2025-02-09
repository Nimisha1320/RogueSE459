package com.group2.rogue.worldgeneration;

import com.group2.rogue.player.Player;

public class World {
    private RogueLevel level;
    private Player player;

    public void generateWorld() {
        level = new RogueLevel();
    }

    public void placePlayer() {
        player = new Player(level);
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
