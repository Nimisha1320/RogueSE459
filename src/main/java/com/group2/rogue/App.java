package com.group2.rogue;

import com.group2.rogue.worldgeneration.RogueLevel;
import com.group2.rogue.worldgeneration.World;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        // System.out.println( "Hello World!" );
        // RogueLevel.main(null);

        World world = new World();
        world.generateWorld();
        

    }
}
