public class LightForest extends Island implements LightForestRequirements {
    protected boolean shelterBuilt;
    private boolean animal;

    public LightForest() {
        super("You are in the Light Forest. There are tall trees whose leaves are high above you. The forest extends for miles around.");
        this.shelterBuilt = false;
    }

    @Override
    protected boolean canBuildShelter() {
        return true;  // Shelter building allowed here
    }

    @Override
    public void buildShelter() {
    super.buildShelter();  // Use parent logic
    if (shelterBuilt) {
        System.out.println("Your shelter blends perfectly among the tall trees.");
    }   
    }

    @Override
    public void describe(){
        System.out.println(description);
    }

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

    public boolean getAnimal(){
        return animal;
    }

    public void setAnimal(boolean b){
        animal = b;
    }

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
    


    public void petAnimal(){
        System.out.println("You quietly wait for the animal to approach you before patting it gently on the snout.");
        adjustLuck(2);
    }

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

    @Override
    public Island moveNorth() {
        System.out.println("You head down towards the North Shore. Before you reach the forest edge, you find a white structure with pillars and small statues. You decide to go inside.");
        return northExit;
    }

    @Override
    public Island moveSouth() {
        System.out.println("You are at a waterfall that flows into a fresh water stream headed towards the South Shore.");
        return southExit;
    }

    @Override
    public Island moveEast() {
        System.out.println("You head east, deeper into the forest, but find yourself getting lost. You decide to head back.");
        return this;
    }

    @Override
    public Island moveWest() {
        System.out.println("You head up towards the Mountains and you see a cave along the path. There appears to be an opening to the cave.");
        return westExit;
    }
}
