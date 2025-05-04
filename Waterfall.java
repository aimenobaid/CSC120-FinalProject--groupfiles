public class Waterfall extends Island implements WaterfallRequirements {
   
    public boolean treasureFound = false;

    /**
     * Constructor for the Waterfall class.
     * Initializes the waterfall with a description.
     * @param description The description of the waterfall.
     */
    public Waterfall() {
        super("Waterfall", "You have reached a waterfall that flows into a fresh water stream headed towards the South Shore. The water is clear and you can see fish swimming in the stream. There are rocks along the shore, and a path leading to a small alcove behind the waterfall.");
    }

    /**
     * Prints description of the waterfall.
     */
    @Override
    public void describe() {
        System.out.println(description);
    }

    /**
     * Allows player to collect items from the waterfall and prints a collection message.
     * Increments the action count and adds item to inventory. Prints error message if item is not recognized.
     * @param item The item to be collected (rock, stick, coal).
     */
    @Override
    public void collectItem(String item){
        incrementActions();
    
        switch(item.toLowerCase()) {
            case "rock":
                inventory.put("rock", inventory.getOrDefault("rock", 0) + 1);
                System.out.println("You collected a rock near the waterfall.");
                break;
            case "stick":
                inventory.put("stick", inventory.getOrDefault("stick", 0) + 1);
                System.out.println("You gathered a stick near the waterfall.");
                break;
            case "water":
                inventory.put("water", inventory.getOrDefault("water", 0) + 1);
                System.out.println("You collected fresh water from the waterfall.");
                break;
            case "treasure":
                if (!treasureFound) {
                    System.out.println("You collect the treasure from the chest. Your luck increases by 5!");
                    Island.adjustLuck(5);
                    treasureFound = true;
                } else {
                    System.out.println("You already collected the treasure.");
                }
                break;
            default:
                System.out.println("There's no such item here to collect.");
                return;
        }
    }

    /**
     * Overrides the newDay() method to reset the treasureFound boolean to false.
     */
    @Override
    public void newDay() {
        treasureFound = false;
    }

    /**
     * Prints a message indicating that the player has entered the alcove behind the waterfall.
     */
    public void enterAlcove() {
        System.out.println("You enter the alcove behind the waterfall. It is dark and damp, but you can see a small chest hidden in the corner.");
    }

    /**
     * Prints a message indicating that the player has opened the chest and found treasure inside.
     */
    public void openChest() {
        System.out.println("You open the chest and find treasure inside! Collect it to increase your luck.");
    }

    /**
     * Prints a message indicating that the player has left the alcove and returned to the waterfall.
     */
    public void leaveAlcove() {
        System.out.println("You leave the alcove and step back out into the sunlight. The waterfall is beautiful, and you can hear the sound of the water rushing by.");
    }

    /**
     * Prints a message indicating that the player cannot swim at the waterfall.
     */
    public void swim(){
        System.out.println("The water is rough and choppy. You should not swim here. Try farther downstream.");
    }


    /**
     * Displays possible commands for the player specific to the location and several conditions.

     */
    @Override
    public void help() {
        String help = """
        📍 You are at the Waterfall.
        Available Commands:
        inventory, stats, help, quit
        look around
        go north / south / east / west
        collect rock / stick / water / treasure
        drink, eat
        build fire
        enter alcove
        open chest
        leave alcove
        """;
    System.out.println(help);
    }

    /**
     * Moves the player to the Mountain and prints a message about their path.
     * @return Their new location (Mountain).
     */
    @Override
    public Island moveNorth() {
        System.out.println("You return to the mountain path and begin climbing towards Misty Mountain peak.");
        return northExit;
    }

    /**
     * Moves the player to the stream and prints a message about their path.
     * @return Their new location (Stream).
     */
    @Override
    public Island moveSouth() {
        System.out.println("You follow the stream south towards the South Shore. Move south again to reach the beach.");
        return southExit;
    }


    /**
     * Moves the player to the Light Forest and prints a message about their path.
     * @return Their new location (Light Forest).
     */
    @Override
    public Island moveEast() {
        System.out.println("You walk into the woods and enter the Light Forest. Through the trees you can see the ruins of a temple. A path going east leads deeper into the forest. You can hear birds and other animals chirping in the distance.");
        return eastExit;
    }

    /**
     * Moves the player to the Dark Forest and prints a message about their path.
     * @return Their new location (Dark Forest).
     */
    @Override
    public Island moveWest() {
        System.out.println("You walk into the woods and enter the Dark Forest. The trees are thick and the air is heavy with the smell of damp earth. A path going west leads deeper into the forest. You notice large paw prints in the mud.");
        return westExit;
    }

}
