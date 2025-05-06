public class LightForest extends Island implements LightForestRequirements {
    protected boolean shelterBuilt;
    private boolean animal;

    /**
     * Constructor for the Light Forest class.
     * Initializes the light forest with a description and sets the initial state of shelterBuilt to false.
     * @param description The description of the light forest.
     */
    public LightForest() {
        super("You are in the Light Forest. There are tall trees whose leaves are high above you. The forest extends for miles around.");
        this.shelterBuilt = false;
    }

    /**
     * Prints description of the light forest.
     */
    @Override
    public void describe(){
        System.out.println(description);
    }

    /**
     * Checks if a shelter can be built in the light forest.
     * @return boolean indicating if a shelter can be built.
     */
    @Override
    protected boolean canBuildShelter() {
        return true;  // Shelter building allowed here
    }

    @Override
    protected boolean hasShelter() {
        return shelterBuilt;  // Check if shelter is built
    }


    /**
     * Allows player to build a shelter if they have enough resources.
     * If shelter is already built, prints a message and returns.
     * If player has enough rocks and sticks, deducts them from inventory and builds the shelter.
     */
    @Override
    public void buildShelter() {
    super.buildShelter();
    if (shelterBuilt) {
        System.out.println("Your shelter blends perfectly among the tall trees.");
    }   
    }

    /**
     * Allows player to collect items from the light forest and prints a collection message.
     * Increments the action count and adds item to inventory. Prints error message if item is not recognized.
     * @param item The item to be collected (rock, stick, berries).
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
            case "berries":
                inventory.put("berries", inventory.getOrDefault("berries", 0) + 1);
                System.out.println("You collected some berries.");
                break;
            default:
                System.out.println("There's no such item here to collect.");
                return;
        }
    }

    /**
     * Allows player to forsge for berries in the light forest. 
     * If luck points are sufficient they are found.
     * Prints a message about the berries are found or not. 
     */
    public void forage(){
        System.out.println("You forage in a small bush.");
        if(luckPoints >= 50){
            collectItem("berry");
            adjustLuck(1);
        }
        if(luckPoints < 50){
            System.out.println("You didn't find anything in the bush.");
        }

        if(luckPoints%7 == 0){
            animal = true;
            System.out.println("A strange, small animal approaches you. It makes a short noise out of its pig-like snout. Its fur looks soft.");
        }
    }


    /**
     * Checks if there is an animal present in the light forest.
     * @param b boolean indicating if animal is present
     */
    public boolean getAnimal(){
        return animal;
    }

    /**
     * Sets the presence of an animal in the light forest.
     * @param b boolean indicating if animal is present
     */
    public void setAnimal(boolean b){
        animal = b;
    }

    /**
    * Allows player to pet the animal if it is present.
     */
    public void petAnimal(){
        System.out.println("You quietly wait for the animal to approach you before patting it gently on the snout.");
        adjustLuck(2);
    }

    /**
     * Displays possible commands for the player specific to the location and several conditions.
     */
    @Override
    public void help() {
    String help = """
        📍 You are in the Light Forest.
        Available Commands:
        - go north / south / east / west
        - collect rock / stick / berries
        - forage
        - build fire
        - look around
        - rest
        - inventory, stats, help, quit
    """;
    if (!shelterBuilt) {
        help += "- build shelter\n";
    }
    if(animal){
        help += "- pet animal";
    }
    System.out.println(help);   
    }

    /**
     * Moves the player to the North Shore and prints a message about their path.
     * @return Their new location (North Shore).
     */
    @Override
    public Island moveNorth() {
        System.out.println("You head down towards the North Shore. Before you reach the forest edge, you find a white structure with pillars and small statues. You decide to go inside.");
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
     * Attempts to move the player further eest but prints a message indicating that they cannot go further eest.
     * @return Their current location.
     */
    @Override
    public Island moveEast() {
        System.out.println("You head east, deeper into the forest, but find yourself getting lost. You decide to head back.");
        return this;
    }

    /**
     * Moves the player wast to the Mountain and prints a message about their path.
     * @return Their new location (Mountain).
     */
    @Override
    public Island moveWest() {
        System.out.println("You head up towards the Mountains and you see a cave along the path. There appears to be an opening to the cave.");
        return westExit;
    }
}
