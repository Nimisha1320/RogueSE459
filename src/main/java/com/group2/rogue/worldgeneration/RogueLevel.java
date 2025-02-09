package com.group2.rogue.worldgeneration;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class RogueLevel {
    private static final int LEVEL_WIDTH = 80;
    private static final int LEVEL_HEIGHT = 24;
    private static final char FLOOR = '.';
    private static final char WALL = '#';
    private static final char EMPTY = ' ';
    private static final char STAIRS = '%';

    private static final int MAX_ROOMS = 8;
    private static final int MIN_ROOM_SIZE = 4;
    private static final int MAX_ROOM_SIZE = 10;

    private final Random random = new Random();
    private final char[][] level = new char[LEVEL_HEIGHT][LEVEL_WIDTH];
    private final List<int[]> roomCenters = new ArrayList<>();

    public RogueLevel() {
        generateLevel();
    }

    public void generateLevel() {
        initializeLevel();
        generateRooms();
        connectRooms();
        placeStairs();
    }

    public char[][] getMap() {
        return level;
    }

    public int[] getStartingRoom() {
        if (!roomCenters.isEmpty()) {
            return roomCenters.get(0); // Return the center of the first generated room
        }
        return new int[] { LEVEL_WIDTH / 2, LEVEL_HEIGHT / 2 }; // Fallback
    }

    private void initializeLevel() {
        for (int y = 0; y < LEVEL_HEIGHT; y++) {
            for (int x = 0; x < LEVEL_WIDTH; x++) {
                level[y][x] = EMPTY;
            }
        }
    }

    private void generateRooms() {
        int roomCount = 0;
        while (roomCount < MAX_ROOMS) {
            int w = random.nextInt(MAX_ROOM_SIZE - MIN_ROOM_SIZE) + MIN_ROOM_SIZE;
            int h = random.nextInt(MAX_ROOM_SIZE - MIN_ROOM_SIZE) + MIN_ROOM_SIZE;
            int x = random.nextInt(LEVEL_WIDTH - w - 1) + 1;
            int y = random.nextInt(LEVEL_HEIGHT - h - 1) + 1;

            if (canPlaceRoom(x, y, w, h)) {
                createRoom(x, y, w, h);
                roomCenters.add(new int[] { x + w / 2, y + h / 2 });
                roomCount++;
            }
        }
    }

    private boolean canPlaceRoom(int x, int y, int w, int h) {
        for (int i = y - 1; i <= y + h; i++) {
            for (int j = x - 1; j <= x + w; j++) {
                if (i < 0 || j < 0 || i >= LEVEL_HEIGHT || j >= LEVEL_WIDTH || level[i][j] != EMPTY) {
                    return false;
                }
            }
        }
        return true;
    }

    private void createRoom(int x, int y, int w, int h) {
        for (int i = y; i < y + h; i++) {
            for (int j = x; j < x + w; j++) {
                level[i][j] = FLOOR;
            }
        }
        for (int i = y - 1; i <= y + h; i++) {
            if (i >= 0 && i < LEVEL_HEIGHT) {
                if (x - 1 >= 0)
                    level[i][x - 1] = WALL;
                if (x + w < LEVEL_WIDTH)
                    level[i][x + w] = WALL;
            }
        }
        for (int j = x - 1; j <= x + w; j++) {
            if (j >= 0 && j < LEVEL_WIDTH) {
                if (y - 1 >= 0)
                    level[y - 1][j] = WALL;
                if (y + h < LEVEL_HEIGHT)
                    level[y + h][j] = WALL;
            }
        }
    }

    private void connectRooms() {
        for (int i = 1; i < roomCenters.size(); i++) {
            int[] prev = roomCenters.get(i - 1);
            int[] current = roomCenters.get(i);
            createCorridor(prev[0], prev[1], current[0], current[1]);
        }
    }

    private void createCorridor(int x1, int y1, int x2, int y2) {
        while (x1 != x2) {
            if (x1 < x2)
                x1++;
            else
                x1--;
            level[y1][x1] = FLOOR;
        }
        while (y1 != y2) {
            if (y1 < y2)
                y1++;
            else
                y1--;
            level[y1][x1] = FLOOR;
        }
    }

    private void placeStairs() {
        if (!roomCenters.isEmpty()) {
            int[] lastRoom = roomCenters.get(roomCenters.size() - 1);
            level[lastRoom[1]][lastRoom[0]] = STAIRS;
        }
    }

    public void printLevel() {
        for (char[] row : level) {
            System.out.println(new String(row));
        }
    }
}
