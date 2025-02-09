package com.group2.rogue;

import com.group2.rogue.worldgeneration.World;
import java.util.Scanner;

public class App {
    public static void main(String[] args) {
        World world = new World();
        world.generateWorld();
        world.placePlayer();

        Scanner scanner = new Scanner(System.in);
        while (true) {
            world.displayWorld();
            System.out.print("Move (WASD): ");
            String input = scanner.nextLine().toUpperCase();

            if (input.equals("Q")) { // Quit game
                System.out.println("Exiting...");
                break;
            }

            if (input.length() == 1) {
                world.movePlayer(input.charAt(0));
            }
        }
        scanner.close();
    }
}
