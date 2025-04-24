public class LightForest extends Island implements LightForestRequirements {
    protected boolean shelterBuilt;

    public LightForest() {
        super("Light Forest", "You are in the Light Forest.");
        this.shelterBuilt = false;
    }

    
    public void collectItem(String item){
        inventory.put(item, inventory.getOrDefault(item, 0) + 1);
        incrementActions();
        
        switch(item.toLowerCase()) {
            case "rock":
                System.out.println("You collected a rock from the forest floor.");
                break;
            case "stick":
                System.out.println("You gathered a sturdy stick from the forest.");
                break;
            //add cases for other items
            default:
                System.out.println("There's no such item here.");
        }
    }
    
    public void describe(){

    }

    public void forage(){
        
    }
    
    public void collectStick(){

    }

    public void collectRock(){

    }

    public void petAnimal(){

    }

    public void lookAround(){

    }

    public void buildShelter(){

    }

    @Override
    public Island moveNorth() {
        System.out.println("You head down towards the North Shore. Move North again to reach the shore.");
        return this;
    }

    @Override
    public Island moveSouth() {
        System.out.println("You are at a waterfall that flows into a fresh water stream headed towards the South Shore.");
        return this;
    }

    @Override
    public Island moveEast() {
        System.out.println("You head east, deeper into the forest, but find yourself getting lost. You decide to head back.");
        return this;
    }

    @Override
    public Island moveWest() {
        System.out.println("You head up towards the Mountains and you see a cave along the path. There appears to be an opening to the cave.");
        return this;
    }
}
