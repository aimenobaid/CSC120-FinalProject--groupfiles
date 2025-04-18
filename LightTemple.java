public class LightTemple extends Island implements LightForestRequirements {

    public LightTemple(){
        super("Light Temple", "You are in the Light Temple.");
    }

    public void describe(){
        
    }

    public void pray(){

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
