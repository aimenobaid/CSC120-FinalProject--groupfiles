import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Hardcoded locations
        NorthShore northShore = new NorthShore();
        SouthShore southShore = new SouthShore();
        Mountain mountain = new Mountain();
        MtnCave mtnCave = new MtnCave();
        LightForest lightForest = new LightForest();
        DarkForest darkForest = new DarkForest();
        LightTemple lightTemple = new LightTemple();
        Stream stream = new Stream();
        Waterfall waterfall = new Waterfall();

        // Setting up all the connections

        northShore.setExits(null, mountain, lightForest, darkForest);
        southShore.setExits(stream, null, lightForest, darkForest);
        mountain.setExits(northShore, waterfall, lightForest, darkForest);
        mtnCave.setExits(mountain, waterfall, lightForest, mountain);
        lightForest.setExits(lightTemple, stream, null, mountain);
        darkForest.setExits(northShore, stream, mountain, null);
        lightTemple.setExits(northShore, stream, lightForest, darkForest);
        stream.setExits(waterfall, southShore, lightForest, darkForest);
        waterfall.setExits(mountain, stream, lightForest, darkForest);

        // Start game
        Player player = new Player("Explorer", northShore);
        System.out.println("🌴 Welcome to the Island Survival Game 🌴");
        northShore.describe();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();

            if (input.startsWith("collect ")) {
                String item = input.substring(8);
                player.getLocation().collectItem(item);
            } else{ 
    
        
            switch (input) {
                case "go north" -> player.moveTo(player.getLocation().moveNorth());
                case "go south" -> player.moveTo(player.getLocation().moveSouth());
                case "go east"  -> player.moveTo(player.getLocation().moveEast());
                case "go west"  -> player.moveTo(player.getLocation().moveWest());
                case "inventory" -> Island.printInventory();
                case "stats" -> player.displayStats();
                case "drink" -> player.drink();
                case "eat" -> player.eat();
                case "fish" -> {
                    player.getLocation().collectItem("fish");
                }
                case "build shelter" -> {
                    player.getLocation().buildShelter();
                }
                case "rest" -> player.rest();
                case "build fire" -> {
                    player.getLocation().buildFire();
                }   
                case "look around" -> player.getLocation().describe();
                case "help" -> 
                    player.getLocation().help();
                case "quit" -> {
                    System.out.println("Thanks for playing!");
                    scanner.close();
                    return;
                }
                case "fight" -> {
                    player.getLocation().fight();
                }
                case "swim" -> {
                    if (player.getLocation() instanceof Waterfall w) w.swim();
                    else if (player.getLocation() instanceof Stream s) s.swim();
                    else System.out.println("You can't swim here, the ocean is rough and you will drown.");
                }
                case "climb mountain" -> {
                    if (player.getLocation() instanceof Mountain m) m.climbMountain();
                    else System.out.println("You must be at the Misty Mountain in order to climb to its peak.");
                }
                case "push rock" -> {
                    if (player.getLocation() instanceof Mountain m) m.pushRock();
                    else System.out.println("You must be near the cave entrance on the Misty Mountain to push the rock.");
                }
                case "view map" -> {
                    if (player.getLocation() instanceof Mountain m) m.viewMap();
                    else System.out.println("You must be at the peak of the Misty Mountain to view the map.");
                }
                case "leave cave" -> {
                    if (player.getLocation() instanceof MtnCave mc) mc.leaveCave();
                    else System.out.println("You are not in the cave.");
                }
                case "enter alcove" -> {
                    if (player.getLocation() instanceof Waterfall wf) wf.enterAlcove();
                    else System.out.println("You are not at the waterfall.");
                }
                case "open chest" -> {
                    if (player.getLocation() instanceof Waterfall wf) wf.openChest();
                    else System.out.println("There is no chest here.");
                }
                case "leave alcove" -> {
                    if (player.getLocation() instanceof Waterfall wf) wf.leaveAlcove();
                    else System.out.println("You are not in the alcove.");
                }
                
                default -> System.out.println("Unknown command. Type 'help' for options.");
          
            } //end of switch statement
            } //end of else statement
            // scanner.close();
        } //end of while loop
    } //end of main method
} 
