public class MtnCave extends Island {

    /**
     * * Constructor for the MtnCave class. 
     * Initializes cave with a description and sets the opponent flag to true.
     * * @param description The description of the cave.
     */
    public MtnCave() {
        super("Mountain Cave", "You have entered a cave in the Misty Mountain. It is dark and damp. You cannot see more than a few inches in front of you. Suddenly a mountain troll comes out of the darkness. You must leave immediately or fight him.");
        super.opponent = true;
    }

    /**
     * Prints description of the cave.
     */
    @Override
    public void describe() {
        System.out.println(description);
    }

    /**
     * Allows player to collect items from the cave and prints a collection message.
     * Increments the action count and adds item to inventory. Prints error message if item is not recognized.
     * @param item The item to be collected (rock, stick, coal).
     */
    @Override
    public void collectItem(String item){
        incrementActions();

        switch(item.toLowerCase()) {
            case "rock":
                inventory.put("rock", inventory.getOrDefault("rock", 0) + 1);
                System.out.println("You collected a rock from the cave floor.");
                break;
            case "stick":
                inventory.put("stick", inventory.getOrDefault("stick", 0) + 1);
                System.out.println("You gathered a stick from inside the cave.");
                break;
            case "coal":
                inventory.put("coal", inventory.getOrDefault("coal", 0) + 1);
                System.out.println("You stole some coal from the mountain troll's stash.");
                break;
            default:
                System.out.println("There's no such item here to collect.");
                return;
         }
    }

    /**
     * Allows player to leave the cave and return to the mountain. Prints a message about the journey.
     * @return The next island to move to (northExit) which is the moutnain.
     */
    public Island leaveCave(){
        System.out.println("You quickly run out of the cave and step out into the light. The mountain troll cannot get you here. You are on the side of the mountain. There is a path leading up to the peak or down towards the Light Forest.");
        return northExit;
    }

    /**
     * Displays possible commands for the player specific to the location.
     */
    public void help(){
        String help = """
        📍 You are inside the Mountain Cave.
        Available Commands:
        go north / south / east / west
        collect coal / rock / stick / water / supplies
        leave cave
        fight
        drink, eat
        inventory, stats, help
        """;
        System.out.println(help);
    }
    
    /**
     * Moves the player back to the mountain and prints a message about their path.
     * @return Their new location (Mountain).
     */
    @Override
    public Island moveNorth() {
        System.out.println("You skirt around the side of the mountain towards the North Shore. Move North again to reach the shore.");
        return northExit;
    }

    /**
     * Moves the player to the waterfall and prints a message about their path.
     * @return Their new location (Waterfall).
     */
    @Override
    public Island moveSouth() {
        System.out.println("You walk around the side of the mountain and reach a waterfall. It flows into a fresh water stream headed towards the South Shore.");
        return southExit;
    }

    /**
     * Moves the player to the Light Forest and prints a message about their path.
     * @return Their new location (Light Forest).
     */
    @Override
    public Island moveEast() {
        System.out.println("You head down into the Light Forest. Through the trees you can see the ruins of a temple. Move East again to continue further into the forest.");
        return eastExit;
    }

    /**
     * Moves the player back to the mountain and prints a message about their path.
     * @return Their new location (Mountain).
     */
    @Override
    public Island moveWest() {
        System.out.println("You climb west towards the top of the mountain. The path is steep and rocky. Move West again to continue climbing and reach the peak.");
        return westExit;
    }
}
 