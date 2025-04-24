public class LightTemple extends Island implements LightForestRequirements {

    public LightTemple(){
        super("Light Temple", "You are in the Light Temple. Move North again to continue to the North Shore.");
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
        System.out.println(description);
    }

    public void pray(){
        System.out.println("You take a moment to pray at the temple's altar.");
        adjustLuck(-2);
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
        System.out.println("You head down towards the Light Forest and you see a cave along the path. There appears to be an opening to the cave. Move East again to continue on to the forest.");
        return this;
    }

    @Override
    public Island moveWest() {
        System.out.println("You begin to head down to the Dark Forest but you reach a steep cliff! You cannot go further west from here.");
        return this;
    }
}
