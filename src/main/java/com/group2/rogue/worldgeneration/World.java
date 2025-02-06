package com.group2.rogue.worldgeneration;

import com.group2.rogue.player.Player;

public class World {
    
    private static final RogueLevel[] levels = new RogueLevel[9];
    private static RogueLevel currLevel;
    private static Player player;

    public static void generateWorld(){
        for(int i = 0; i < levels.length; i++){
            levels[i] = new RogueLevel();
            levels[i].generateLevel();
        }
        currLevel = levels[0];
        currRepresentation();
    }

    public static void currRepresentation(){
        currLevel.printLevel();
    }

}
