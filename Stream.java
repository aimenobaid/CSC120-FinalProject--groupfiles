public class Stream extends Island {

    /**
     * Constructor for the Stream class. Initializes stream with a description.
     * @param description The description of the stream.
     */
    public Stream() {
        super("Stream", "You arrive at a freshwater stream connecting the mountain to the shore.");
    }

    /**
     * Prints description of the stream.
     */
    @Override
    public void describe() {
        System.out.println(description);
    }

    

    /**
     * Allows player to collect items from the stream and prints a collection message.
     * Increments the action count and adds item to inventory. Prints error message if item is not recognized.
     * @param item The item to be collected (rock, stick, coal).
     */
    @Override
    public void collectItem(String item){
    incrementActions();

    switch(item.toLowerCase()) {
        case "rock":
            inventory.put("rock", inventory.getOrDefault("rock", 0) + 1);
            System.out.println("You collected a rock from the banks of the stream.");
            break;
        case "stick":
            inventory.put("stick", inventory.getOrDefault("stick", 0) + 1);
            System.out.println("You gathered a stick near the stream.");
            break;
        case "water":
            inventory.put("water", inventory.getOrDefault("water", 0) + 1);
            System.out.println("You collected fresh water from the stream.");
            break;
        case "fish":
            inventory.put("fish", inventory.getOrDefault("fish", 0) + 1);
            System.out.println("You caught a fish from the stream.");
            break;
        default:
            System.out.println("There's no such item here to collect.");
            return;
        }
    }

    /**
     * Allows player to swim in the stream and prints a message. Increases luck points.
     */
    public void swim() {
        System.out.println("You swim in the stream. The water is refreshing and clear.");
        Island.adjustLuck(1);
    }

    /**
     * Displays possible commands for the player specific to the location and several conditions.
     */
    @Override
    public void help() {
        String help = """
            📍 You are at the Stream.
            Available Commands:
            - go north / south / east / west
            - collect rock / stick / water / fish
            - swim
            - build fire
            - look around
            - rest
            - inventory, stats, help, quit
            """;
        System.out.println(help);
    }

    /**
     * Moves the player to the waterfall and prints a message about their path.
     * @return Their new location (Waterfall).
     */
    @Override
    public Island moveNorth() {
        System.out.println("You follow the stream up towards the Misty Mountain. You find yourself in front of a beautiful waterfall. There is a path leading to a small alcove behind the water.");
        return northExit;
    }

    /**
     * Moves the player to the South Shore and prints a message about their path.
     * @return Their new location (South Shore).
     */
    @Override
    public Island moveSouth() {
        System.out.println("You follow the stream down towards the South Shore.");
        return southExit;
    }

    /**
     * Moves the player to the Light Forest and prints a message about their path.
     * @return Their new location (Light Forest).
     */
    @Override
    public Island moveEast() {
        System.out.println("You leave the stream and enter the Light Forest. You see temple ruins up ahead.");
        return eastExit;
    }

    /**
     * Moves the player to the Dark Forest and prints a message about their path.
     * @return Their new location (Dark Forest).
     */
    @Override
    public Island moveWest() {
        System.out.println("You leave the stream and enter the dense foliage of the Dark Forest. The trees are tall and the air is thick with the smell of damp earth. You can hear the sounds of creatures in the distance.");
        return westExit;  
    }
}
