public class SouthShore extends Island implements SouthShoreRequirements {
    private boolean suppliesCollectedToday;

    /**
     * Constructor for the SouthShore class.
     * Initializes the south shore with a description and sets initial state of suppliesCollectToday to false.
     * @param description The description of the south shore.
     */
    public SouthShore() {
        super("You are on the South Shore. You see shipwreck debris on the shore and a stream winding down to the water from a mountain peak in the distance.");
        this.suppliesCollectedToday = false;
    }

    /**
     * Prints description of the South Shore.
     */
    @Override
    public void describe() {
        System.out.println(description);
    }

    /**
     * Does not allow player to build a shelter on the south shore.
     */
    @Override
    public void buildShelter() {
        try {
            throw new Exception("The south shore is not a good place to build a shelter. It is windy and exposed. Try somewhere else.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    /**
     * Allows player to collect items from the south shore and prints a collection message.
     * Increments the action count and adds item to inventory. Prints error message if item is not recognized.
     * @param item The item to be collected (rock, stick, coal).
     */
    @Override
    public void collectItem(String item){
        incrementActions();

        switch(item.toLowerCase()) {
            case "rock":
                inventory.put("rock", inventory.getOrDefault("rock", 0) + 1);
                System.out.println("You found a rock by the shore.");
                break;
            case "stick":
                inventory.put("stick", inventory.getOrDefault("stick", 0) + 1);
                System.out.println("You gathered a stick from the water's edge.");
                break;
            case "water":
                inventory.put("water", inventory.getOrDefault("water", 0) + 1);
                System.out.println("You collected fresh water from the base of the stream.");
                break;
            case "supplies":
                if (!suppliesCollectedToday) {
                    System.out.println("You find washed-up supplies from an old wreck.");
                    inventory.put("supplies", inventory.getOrDefault("supplies", 0) + 1);
                    suppliesCollectedToday = true;
                    adjustLuck(2);

                } else {
                    System.out.println("You've already scavenged everything for today.");
                }
                break;
            case "fish":
                inventory.put("fish", inventory.getOrDefault("fish", 0) + 1);
                System.out.println("You speared a fish from the shallows.");
                adjustLuck(2);
                break;
            default:
                System.out.println("There's no such item here to collect.");
                return;
        }
    }

    /**
     * Sets suppliesCollectToday to false for a new day
     */
    @Override
    public void newDay() {
        suppliesCollectedToday = false;
    }

    /**
     * Displays possible commands for the player specific to the location and several conditions.
     */
    @Override
    public void help() {
        String help = """
        📍 You are on the South Shore.
        Available Commands:
        - go north / south / east / west
        - collect rock / stick / water / supplies / fish
        - drink, eat
        - build fire
        - look around
        - inventory, stats, help, quit
            """;
        System.out.println(help);
    }


    /**
     * Moves the player to the stream and prints a message about their path.
     * @return Their new location (Stream).
     */
    @Override
    public Island moveNorth() {
        System.out.println("You follow the stream up towards the Misty Mountain. There is a waterfall up ahead. Move north again to reach the waterfall.");
        return northExit;
    }

    /**
     * Attempts to move the player south but prints a message indicating that they cannot go further south.
     * @return The current location (SouthShore).
     */
    @Override
    public Island moveSouth() {
        System.out.println("You walk to the water's edge. You can't go further south. You will drown.");
        return this;
    }

    /**
     * Moves the player to the Light Forest and prints a message about their path.
     * @return Their new location (Light Forest).
     */
    @Override
    public Island moveEast() {
        System.out.println("You walk along the shore and enter the Light Forest. You see the ruins of temples in the distance.");
        return eastExit;
    }

    /**
     * Moves the player to the Dark Forest and prints a message about their path.
     * @return Their new location (Dark Forest).
     */
    @Override
    public Island moveWest() {
        System.out.println("You enter the Dark Forest. The foliage is dense and the trees are tall. You can hear the sounds of creatures in the distance.");
        return westExit; 
    }
}
