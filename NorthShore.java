public class NorthShore extends Island implements NorthShoreRequirements {
    protected boolean shelterBuilt;

    public NorthShore() {
        super("North Shore", "You are on the North Shore. There are rocks, sticks, fresh pools, and fish.");
        this.shelterBuilt = false;
    }

    @Override
    public void describe() {
        System.out.println(description);
    }

    
    public void collectItem(String item){
        inventory.put(item, inventory.getOrDefault(item, 0) + 1);
        incrementActions();
        
        switch(item.toLowerCase()) {
            case "rock":
                System.out.println("You collected a rock from the shore.");
                break;
            case "stick":
                System.out.println("You gathered a stick from the shore.");
                break;
            case "water":
                System.out.println("You collected fresh water from a tide pool.");
                break;
            case "fish":
                System.out.println("You speared a fish from the shallows.");
                break;
            case "supplies":
                System.out.println("You found some supplies washed up on the shore.");
                break;
            default:
                System.out.println("There's no such item here.");
        }
    }



    public void fish() {
        System.out.println("You spear a fish from the shallows.");
        collectItem("fish");
        adjustLuck(1);
    }

    public void collectWater() {
        System.out.println("You collect fresh water from a clear tide pool.");
        collectItem("water");
    }

    // Movement methods
    @Override
    public Island moveNorth() {
        System.out.println("You walk to the water's edge. You can't go further north.");
        return this;
    }

    @Override
    public Island moveSouth() {
        System.out.println("You follow a path leading into the forest. A mountain looms ahead of you.");
        return new Mountain();
    }

    @Override
    public Island moveEast() {
        System.out.println("You walk along the shore and enter the Light Forest. You see the ruins of temples in the distance");
        return new Stream();
    }

    @Override
    public Island moveWest() {
        System.out.println("You walk along the shore and enter the Dark Forest. The foliage is dense and the trees are tall. You can hear the sounds of creatures in the distance.");
        return null;
    }
}
