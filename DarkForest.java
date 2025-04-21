public class DarkForest extends Island implements DarkForestRequirements{
    public DarkForest() {
        super("Dark Forest", "You are in the Dark Forest.");
    }

    public void monkeyArmy(){

    }

    public void volcanicEruption(){

    }

    public void describe(){

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
    public Island moveWest() {
        System.out.println("You head east, deeper into the forest, but find yourself getting lost. You decide to head back.");
        return this;
    }

    @Override
    public Island moveEast() {
        System.out.println("You head up towards the Mountains.");
        return this;
    }
}
