public class SouthShore extends Island implements SouthShoreRequirements {
    private boolean suppliesCollectedToday;

    public SouthShore() {
        super("South Shore", "You are on the South Shore. You see shipwreck debris and a stream.");
        this.suppliesCollectedToday = false;
    }

    @Override
    public void describe() {
        System.out.println(description);
    }

    public void collectStick() {
        System.out.println("You found a stick by the rocks.");
        addItem("stick");
    }

    public void collectRock() {
        System.out.println("You picked up a rock.");
        addItem("rock");
    }

    public void collectWater() {
        System.out.println("You collect water from the stream.");
        addItem("water");
    }

    public void collectSupplies() {
        if (!suppliesCollectedToday) {
            System.out.println("You find washed-up supplies from an old wreck.");
            addItem("supplies");
            suppliesCollectedToday = true;
        } else {
            System.out.println("Youve already scavenged everything for today.");
        }
    }

    public void buildShelter() {
        try {
            throw new Exception("Sorry, you can't live here. Not allowed haha 🏝️.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Movement methods
    @Override
    public Island moveNorth() {
        return new NorthShore();
    }

    @Override
    public Island moveSouth() {
        System.out.println("Dense forest blocks your path.");
        return this;
    }

    @Override
    public Island moveEast() {
        return new Stream();
    }

    @Override
    public Island moveWest() {
        System.out.println("You wander into a strange temple path.");
        return null;
    }
}
