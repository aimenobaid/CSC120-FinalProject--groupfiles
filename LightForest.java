public class LightForest extends Island implements LightForestRequirements {
    protected boolean shelterBuilt;

    public LightForest() {
        super("Light Forest", "You are in the Light Forest. There are tall trees whose leaves are high above you. The forest extends for miles around.");
        this.shelterBuilt = false;
    }

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
    }
    
    public void collectStick(){
        System.out.println("You grab a stick from the base of a tree.");
        collectItem("stick");
    }

    public void collectRock(){
        System.out.println("You pick up a rock from the base of a tree.");
        collectItem("rock");
    }

    public void petAnimal(){
        System.out.println("You quietly wait for the animal to approach you, before patting it gently on the snout.");
        adjustLuck(2);
    }

    public void buildShelter(){
        this.shelterBuilt = true;
    }

    @Override
    public Island moveNorth() {
        System.out.println("You head down towards the North Shore. Before you reach the forest edge, you find a white structure with pillars and small statues. Move North again to go into the temple.");
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
