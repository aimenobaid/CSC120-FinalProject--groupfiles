
public class NorthShore extends Island implements NorthShoreRequirements {

    /**
     * Constructor for the NorthShore class.
     * Initializes the north shore with a description and sets initial state of suppliesCollectToday to false.
     * @param description The description of the north shore.
     */
    public NorthShore() {
        super("North Shore", "You are on the North Shore. There are rocks, sticks, fresh pools, and fish.");
    }

    /**
     * Prints description of the South Shore.
     */
    @Override
    public void describe() {
        System.out.println(description);
    }

    /**
     * Checks if a shelter can be built in the light forest.
     * @return boolean indicating if a shelter can be built.
     */
    @Override
    protected boolean canBuildShelter() {
        return true;  // North Shore supports shelter building
    }

    /**
     * Allows player to build a shelter if they have enough resources.
     * If shelter is already built, prints a message and returns.
     * If player has enough rocks and sticks, deducts them from inventory and builds the shelter.
     */
    @Override
    public void buildShelter() {
    if (!shelterBuilt) {
        super.buildShelter();  // Use standard recipe and logic
        if (shelterBuilt) {
            System.out.println("You built a sturdy shelter overlooking the ocean, with the sound of waves to keep you company.");
        }
    } else {
        System.out.println("Your shelter stands firm on the shore, ready for another night.");
    }
    }

    /**
     * Allows player to collect items from the north shore and prints a collection message.
     * Increments the action count and adds item to inventory. Prints error message if item is not recognized.
     * @param item The item to be collected (rock, stick, fish).
     */
    @Override
    public void collectItem(String item){
        incrementActions();

        switch(item.toLowerCase()) {
            case "rock":
                inventory.put("rock", inventory.getOrDefault("rock", 0) + 1);
                System.out.println("You collected a rock from the shore.");
                break;
            case "stick":
                inventory.put("stick", inventory.getOrDefault("stick", 0) + 1);
                System.out.println("You gathered a stick from the shore.");
                break;
            case "water":
                inventory.put("water", inventory.getOrDefault("water", 0) + 1);
                System.out.println("You collected fresh water from a tide pool.");
                break;
            case "fish":
                inventory.put("fish", inventory.getOrDefault("fish", 0) + 1);
                System.out.println("You speared a fish from the shallows.");
                break;
            case "supplies":
                inventory.put("supplies", inventory.getOrDefault("supplies", 0) + 1);
                System.out.println("You found some supplies washed up on the shore.");
                adjustLuck(2);
                break;
            default:
                System.out.println("There's no such item here to collect.");
        }
    }

    /**
     * Displays possible commands for the player specific to the location and several conditions.
     */
    @Override
    public void help() {
        String help = """

            📍 You are on the North Shore.
            Available Commands:
            - go north / south / east / west
            - collect rock / stick / water / fish / supplies
            - drink, eat
            - build fire
            - look around
            - rest
            - inventory, stats, help, quit
            """;
        if (!shelterBuilt) {
            help += """
                    build shelter
                    """;
        }
        System.out.println(help);
    }



    /**
     * Attempts to move the player further north but prints a message indicating that they cannot go further.
     * @return Their current location.
     */
    @Override
    public Island moveNorth() {
        System.out.println("You walk to the water's edge. You can't go further north.");
        return this;
    }


    /**
     * Moves the player to the mountain and prints a message about their path.
     * @return Their new location (Mountains).
     */
    @Override
    public Island moveSouth() {
        System.out.println("You follow a path leading south into the forest.");
        return southExit;
    }

    /**
     * Moves the player to the Light Forest and prints a message about their path.
     * @return Their new location (Light Forest).
     */
    @Override
    public Island moveEast() {
        System.out.println("You walk along the shore and enter the Light Forest. You see the ruins of temples in the distance");
        return eastExit;
    }

    /**
     * Moves the player to the Dark Forest and prints a message about their path.
     * @return Their new location (Dark Forest).
     */
    @Override
    public Island moveWest() {
        System.out.println("You walk along the shore and enter the Dark Forest. The foliage is dense and the trees are tall. You can hear the sounds of creatures in the distance.");
        return westExit;
    }
}
