public class LightTemple extends Island implements LightForestRequirements {

    /**
     * Constructor for the Light Temple class.
     * Initializes the light temple with a description.
     * @param description The description of the light temple.
     */
    public LightTemple(){
        super("You are in the Light Temple. The white marble walls are solid and strong. Against one is a collection of strange humanoid figures.");
    }

    /**
     * Prints description of the light temple.
     */
    @Override
    public void describe(){
        System.out.println(description);
    }

    /**
     * Allows player to collect items from around the light temple and prints a collection message.
     * Increments the action count and adds item to inventory. Prints error message if item is not recognized.
     * @param item The item to be collected (rock, stick, berries).
     */    
    @Override
    public void collectItem(String item){
        incrementActions();
    
        switch(item.toLowerCase()) {
            case "rock":
                inventory.put("rock", inventory.getOrDefault("rock", 0) + 1);
                System.out.println("You collected a rock from the temple floor.");
                break;
            case "stick":
                inventory.put("stick", inventory.getOrDefault("stick", 0) + 1);
                System.out.println("You gathered a sturdy stick near the temple.");
                break;
            case "berries":
                inventory.put("berries", inventory.getOrDefault("berries", 0) + 1);
                System.out.println("You collected berries near the temple.");
                break;
            default:
                System.out.println("There's no such item here to collect.");
                return;
        }
    }

    /**
     * Allows player to pray at the temple altar. Adjusts luck to be lower accordingly. 
     */
    public void pray(){
        System.out.println("You take a moment to pray at the temple's altar. You wasted about an hour of your time.");
        adjustLuck(-2);
    }

    /**
     * Allows player to forsge for berries near the light temple. 
     * If luck points are sufficient they are found.
     * Prints a message about the berries are found or not. 
     */
    public void forage(){
        System.out.println("You forage in a small bush near the temple.");
        if(luckPoints >= 50){
            collectItem("berry");
            adjustLuck(5);
        }
        if(luckPoints < 50){
            System.out.println("You didn't find anything in the bushes near the temple.");
        }
    }

    /**
     * Displays possible commands for the player specific to the location and several conditions.
     */
    @Override
    public void help() {
    String help = """
        📍 You are in the Light Temple.
        Available Commands:
        - go north / south / east / west
        - collect rock / stick / berries
        - forage
        - pray
        - build fire
        - look around
        - rest
        - inventory, stats, help, quit
        """;
    System.out.println(help);
    }

     /**
     * Moves the player to the North Shore and prints a message about their path.
     * @return Their new location (North Shore).
     */
    @Override
    public Island moveNorth() {
        System.out.println("You follow a path north towards the North Shore.");
        return northExit;
    }

    /**
     * Moves the player to the Light Forest and prints a message about their path.
     * @return Their new location (light forest).
     */
    @Override
    public Island moveSouth() {
        System.out.println("You go back towards the center of the forest.");
        return southExit;
    }

    /**
     * Attempts to move the player further east but prints a message indicating that they cannot go further east.
     * @return Their current location.
     */
    @Override
    public Island moveEast() {
        System.out.println("You move east into the Light Forest, but soon find yourself at lost in vegetation. You decide to turn back.");
        return this;
    }

    /**
     * Moves the player to the mountains and prints a message about their path.
     * @return Their new location (Mountain).
     */
    @Override
    public Island moveWest() {
        System.out.println("You climb west towards the top of the mountain. The path is steep and rocky. Move West again to continue climbing and reach the peak.");
        return westExit;
    }
}
