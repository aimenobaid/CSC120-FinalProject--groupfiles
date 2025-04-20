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

    public void collectStick() {
        System.out.println("You picked up a stick from the shore.");
        collectItem("stick");
    }

    public void collectRock() {
        System.out.println("You picked up a rock from the shore.");
        collectItem("rock");
    }

    public void fish() {
        System.out.println("You spear a fish from the shallows.");
        collectItem("fish");
        adjustLuck(1);
    }

    public void drinkFromPool() {
        System.out.println("You drink fresh water from a clear tide pool.");
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
