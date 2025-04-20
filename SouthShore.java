public class SouthShore extends Island implements SouthShoreRequirements {
    private boolean suppliesCollectedToday;

    public SouthShore() {
        super("South Shore", "You are on the South Shore. You see shipwreck debris on the shore and a stream winding down to the water from a mountain peak in the distance.");
        this.suppliesCollectedToday = false;
    }

    @Override
    public void describe() {
        System.out.println(description);
    }

    public void collectStick() {
        System.out.println("You found a stick by the rocks.");
        collectItem("stick");
    }

    public void collectRock() {
        System.out.println("You picked up a rock.");
        collectItem("rock");
    }

    public void collectWater() {
        System.out.println("You collect water from the stream.");
        collectItem("water");
    }

    public void collectSupplies() {
        if (!suppliesCollectedToday) {
            System.out.println("You find washed-up supplies from an old wreck.");
            collectItem("supplies");
            suppliesCollectedToday = true;
        } else {
            System.out.println("Youve already scavenged everything for today.");
        }
    }

    public void buildShelter() {
        try {
            throw new Exception("The south shore is not a good place to build a shelter. It is windy and exposed. Try somewhere else.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Movement methods
    @Override
    public Island moveNorth() {
        System.out.println("You follow the stream up towards the Misty Mountain. There is a waterfall up ahead. Move north again to reach the waterfall.");
        return new Stream();
    }

    @Override
    public Island moveSouth() {
        System.out.println("You walk to the water's edge. You can't go further south. You will drown.");
        return this;
    }

    @Override
    public Island moveEast() {
        System.out.println("You walk along the shore and enter the Light Forest. You see the ruins of temples in the distance.");
        return new LightForest();
    }

    @Override
    public Island moveWest() {
        System.out.println("You enter the Dark Forest. The foliage is dense and the trees are tall. You can hear the sounds of creatures in the distance.");
        //return new DarkForest(); uncomment once DarkForest.java is built
        return this;
    }
}
