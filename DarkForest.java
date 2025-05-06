public class DarkForest extends Island implements DarkForestRequirements{


    /**
     * Constructor for the Dark Forest class.
     * Initializes the dark forest with a description and sets the initial state of opponent to true.
     * @param description The description of the dark forest.
     */
    public DarkForest() {
        super("Dark Forest", "You are in the Dark Forest.");
        super.opponent = true;
    }

    /**
     * Prints description of the dark forest.
     */
    @Override
    public void describe(){
        System.out.println(description);
    }

    /**
     * Checks if a shelter can be built in the dark forest.
     * @return boolean indicating if a shelter can be built.
     */
    @Override
    protected boolean canBuildShelter() {
        return true;
    }

    @Override
    protected boolean hasShelter() {
        return shelterBuilt;  // Check if shelter is built
    }

    /**
     * Allows player to build a shelter if they have enough resources.
     * If shelter is already built, prints a message and returns.
     * If player has enough rocks and sticks, deducts them from inventory and builds the shelter.
     * If luck points are sufficient, prints success message; otherwise, prints failure message and either volcano errupts or luck points decrease.
     */
    @Override
    public void buildShelter() {
        if (shelterBuilt) {
            System.out.println("A crude shelter already stands here, blending into the shadows.");
            return;
        }
        if (getItemCount("rock") >= 3 && getItemCount("stick") >= 3) {
            inventory.put("rock", inventory.get("rock") - 3);
            inventory.put("stick", inventory.get("stick") - 3);
        
            if (luckPoints >= 80) {
                System.out.println("You carefully construct a hidden shelter, safe from the lurking creatures.");
                shelterBuilt = true;
            } else {
                // TRIGGER VOLCANIC EXPLOSION
                if (volcanicEruption()) {
                    Player globalPlayer = Player.getInstance();
                    globalPlayer.changeHealth(-101);
                    globalPlayer.die();
                } else {
                    System.out.println("As night falls, your fragile shelter collapses and you're ambushed by forest creatures! You lose 10 luck points.");
                    adjustLuck(-10);
                }
            }
        } else {
            System.out.println("You need 3 rocks and 3 sticks to build a shelter.");
        }
        incrementActions();
    }


    /**
     * Allows player to collect items from the dark forest and prints a collection message.
     * Increments the action count and adds item to inventory. Prints error message if item is not recognized.
     * @param item The item to be collected (rock, stick).
     */
    @Override
    public void collectItem(String item){
        incrementActions();
    
        switch(item.toLowerCase()) {
            case "rock":
                inventory.put("rock", inventory.getOrDefault("rock", 0) + 1);
                System.out.println("You collected a rock from the forest floor.");
                break;
            case "stick":
                inventory.put("stick", inventory.getOrDefault("stick", 0) + 1);
                System.out.println("You gathered a sturdy stick from the forest.");
                break;
            default:
                System.out.println("There's no such item here to collect.");
                return;
        }
    }


    /**
     * Allows player to forsge for berries in the dark forest. 
     * If luck points are sufficient they are not poisoned, otherwise they are poisoned and lose health.
     * Prints a message about the berries found. 
     */
    public void forage(){
        System.out.println("You forage in a small bush, and find a few berries. But they have suspicious dark spots and you don't see any animals nearby. You leave them on the bush and wipe their juices on your pants.");
        if(luckPoints >= 50){
            adjustLuck(-1);
        }
        if(luckPoints < 50){
            System.out.println("You didn't find anything in the bush.");
        }
    }

    /**
     * Checks if a volcanic eruption occurs based on luck points.
     * If luck points are less than 40, prints a message about the eruption.
     * @return boolean indicating if a volcanic eruption occurs.
     */
    @Override
    public boolean volcanicEruption(){
        if(luckPoints < 40){
            System.out.println("You feel a sudden rumbling of the ground, and turn towards the Mountain. The top of the mountain shakes, before exploding into a cloud of black dust and ash. You're frozen for a moment, before you see hot lava and pyroclastic flow begin to rush down the side of the mountain. You have no chance of running.");
            return true;
        }
        return false;
    }

    /**
     * Displays possible commands for the player specific to the location and several conditions.
     */
    @Override
    public void help() {
        String help = """
            📍 You are in the Dark Forest.
            Available Commands:
            - go north / south / east / west
            - collect rock / stick
            - build fire
            - look around
            - rest
            - inventory, stats, help, quit
        """;
        if (!shelterBuilt) {
            help += "- build shelter\n";
        }
        System.out.println(help);
    }

    /**
     * Moves the player to the North Shore and prints a message about their path.
     * @return Their new location (North Shore).
     */
    @Override
    public Island moveNorth() {
        System.out.println("You head down towards the North Shore. Move North again to reach the shore.");
        return northExit;
    }

    /**
     * Moves the player to the South Shore and prints a message about their path.
     * @return Their new location (South Shore).
     */
    @Override
    public Island moveSouth() {
        System.out.println("You are at a waterfall that flows into a fresh water stream headed towards the South Shore.");
        return southExit;
    }

    /**
     * Moves the player east to the Mountain and prints a message about their path.
     * @return Their new location (Mountain).
     */
    @Override
    public Island moveEast() {
        System.out.println("You head up towards the Mountains.");
        return eastExit;
    }
    
    /**
     * Attempts to move the player further west but prints a message indicating that they cannot go further west.
     * @return Their current location.
     */
    @Override
    public Island moveWest() {
        System.out.println("You move deeper into the forest. As the vegetation grows denser and darker, you hear a rumble to your left. You crouch down quickly into the bush.");
        System.out.println("A strange creature emerges out of the bushes. It walks upright, with a snout like a monkey's and strong gorilla arms. It has orange and black fur like a tiger's. It moves west through the bushes, dragging its long tail behind. You decide to follow.");
        System.out.println("The Tiger Monkey is approaching a brown structure, where it remains for a moment. You hear it rustle around before leaving the structure again, and moves into the forest. You go inside the structure.");
        return westExit;
    }

}
