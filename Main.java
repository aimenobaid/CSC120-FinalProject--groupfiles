import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        // Hardcoded locations
        NorthShore northShore = new NorthShore();
        SouthShore southShore = new SouthShore();
        Island.southShoreInstance = southShore;
        Mountain mountain = new Mountain();
        MtnCave mtnCave = new MtnCave();
        LightForest lightForest = new LightForest();
        DarkForest darkForest = new DarkForest();
        DarkForest darkForestInstance = darkForest;
        LightTemple lightTemple = new LightTemple();
        Stream stream = new Stream();
        Waterfall waterfall = new Waterfall();
        Island.waterfallInstance = waterfall;
        TigerMonkeyHut tigerMonkeyHut = new TigerMonkeyHut();


        // Setting up all the connections
        northShore.setExits(null, mountain, lightForest, darkForest);
        southShore.setExits(stream, null, lightForest, darkForest);
        mountain.setExits(northShore, waterfall, mtnCave, darkForest);
        mtnCave.setExits(mountain, waterfall, lightForest, mountain);
        lightForest.setExits(lightTemple, stream, lightForest, mountain);
        darkForest.setExits(northShore, stream, mountain, tigerMonkeyHut);
        lightTemple.setExits(northShore, stream, lightForest, darkForest);
        stream.setExits(waterfall, southShore, lightForest, darkForest);
        waterfall.setExits(mountain, stream, lightForest, darkForest);
        tigerMonkeyHut.setExits(darkForest, waterfall, mountain, null);

        // Start game
        Player player = new Player("Explorer", northShore);
        System.out.println("🌴 Welcome to the Island Survival Game 🌴");
        System.out.println("You have just washed ashore on a deserted island. Your goal is to survive as long as possible in order to be rescued. \n" +
                "You can collect items, build shelters, and explore the island. \n" +
                "Type 'help' for a list of commands.\n" +
                "Your luck begins at 50/100. You will need to keep your luck above 0 in order to survive, and achieve a luck score of 100 to be automatically rescued.\n");
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
                    //player.getLocation().fight();
                    if(player.getLocation() instanceof TigerMonkeyHut tmh){
                        if(!tmh.FightMonkey()){
                            player.changeHealth(-70);
                        }
                    }
                    else if(!player.getLocation().fight()){
                        player.changeHealth(-70);
                    }
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
                case "leave hut" ->{
                    if(player.getLocation() instanceof TigerMonkeyHut tmh){
                        tmh.leaveHut();
                    }
                }
                case "open chest" -> {
                    if (player.getLocation() instanceof Waterfall wf) {
                        wf.openChest();
                    } else if (player.getLocation() instanceof TigerMonkeyHut tmh) {
                        tmh.openChest();
                    } else {
                        System.out.println("There is no chest here.");
                    }
                }
                case "use flare" -> {
                    if (Island.getItemCount("flare") > 0) {
                        System.out.println("You fire the flare into the sky. A brilliant red light arcs high above the island...");
                        System.out.println("Moments later, you hear the sound of rotors. A helicopter appears, circling and then descending towards you!");
                        System.out.println("🏆 YOU WIN! You were rescued thanks to the flare.");
                        System.exit(0);
                    } else {
                        System.out.println("You don't have a flare to use.");
                    }
                }
                case "leave alcove" -> {
                    if (player.getLocation() instanceof Waterfall wf) wf.leaveAlcove();
                    else System.out.println("You are not in the alcove.");
                }

                //Light Forest
                case "forage" -> {
                    if(player.getLocation() instanceof LightForest lf) lf.forage();
                    if(player.getLocation() instanceof DarkForest df){
                        df.forage();
                        System.out.println("");
                    }
                    else System.out.println("You cannot forage here.");
                }

                case "pet" -> {
                    if(player.getLocation() instanceof LightForest lf && lf.getAnimal()){
                        lf.petAnimal();
                        lf.setAnimal(false);
                    }
                    else System.out.println("You pet an imaginary animal. How long has it been since you slept?");
                }

                case "pray" -> {
                    if(player.getLocation() instanceof LightTemple lt) lt.pray();
                    else System.out.println("You cannot pray here.");
                }

                case "run" ->{
                    if(player.getLocation() instanceof TigerMonkeyHut tmh){
                        tmh.run();
                        player.increaseHunger(10);
                    }
                    else System.out.println("What are you running from?");
                }
                
                default -> System.out.println("Unknown command. Type 'help' for options.");
          
            } //end of switch statement
            } //end of else statement
            // scanner.close();
            if(player.getLocation() instanceof DarkForest df){
                if(df.volcanicEruption()){
                    player.changeHealth(-101);
                    player.die();
                }
            }
            if(player.die()){
                System.out.println("You have died! You lived until Day " + Island.getCurrentDay() + ". Your stats were: ");
                player.displayStats();
                break;
            }
        } scanner.close();
    } //end of main method
} 
