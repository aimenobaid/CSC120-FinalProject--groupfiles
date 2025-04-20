public class MtnCave extends Island {

    public MtnCave() {
        super("Mountain Cave", "You have entered a cave in the Misty Mountain. It is dark and damp. You cannot see more than a few inches in front of you. The air is mostly still except for a faint breeze that almost feels like a creature breathing softly.");
    }

    @Override
    public void describe() {
        System.out.println(description);
    }


    // Movement methods
    @Override
    public Island moveNorth() {
        System.out.println("You skirt around the side of the mountain towards the North Shore. Move North again to reach the shore.");
        return new Mountain();
    }

    @Override
    public Island moveSouth() {
        System.out.println("You walk around the side of the mountain and reach a waterfall. It flows into a fresh water stream headed towards the South Shore.");
        return new Waterfall();
    }

    @Override
    public Island moveEast() {
        System.out.println("You head down into the Light Forest. Through the trees you can see the ruins of a temple. Move East again to continue further into the forest.");
        return new LightForest();
    }

    @Override
    public Island moveWest() {
        System.out.println("You climb west towards the top of the mountain. The path is steep and rocky. Move West again to continue climbing and reach the peak.");
        return new Mountain();
    }


}
