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
        addItem("stick");
    }

    public void collectRock() {
        System.out.println("You picked up a rock from the shore.");
        addItem("rock");
    }

    public void fish() {
        System.out.println("You spear a fish from the shallows.");
        addItem("fish");
        adjustLuck(1);
    }

    public void drinkFromPool() {
        System.out.println("You drink fresh water from a clear tide pool.");
        addItem("water");
    }

    public void buildShelter() {
        if (shelterBuilt) {
            System.out.println("You've already built a shelter here.");
            return;
        }
        if (getItemCount("rock") >= 3 && getItemCount("stick") >= 3) {
            System.out.println("You build a sturdy little shelter out of rocks and sticks!");
            shelterBuilt = true;
        } else {
            System.out.println("Not enough materials. You need 3 rocks and 3 sticks.");
        }

    }

    // Movement methods
    @Override
    public Island moveNorth() {
        System.out.println("You can't go further north.");
        return this;
    }

    @Override
    public Island moveSouth() {
        return new SouthShore();
    }

    @Override
    public Island moveEast() {
        return new Stream();
    }

    @Override
    public Island moveWest() {
        System.out.println("You enter the forest.");
        return null;
    }
}
