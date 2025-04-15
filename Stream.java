public class Stream extends Island {

    public Stream() {
        super("Stream", "You arrive at a freshwater stream connecting the mountain to the shore.");
    }

    @Override
    public void describe() {
        System.out.println(description);
    }

    @Override
    public Island moveNorth() {
        return new NorthShore();
    }

    @Override
    public Island moveSouth() {
        return new SouthShore();
    }

    @Override
    public Island moveEast() {
        System.out.println("The stream narrows into dense foliage.");
        return this;
    }

    @Override
    public Island moveWest() {
        System.out.println("The stream winds back toward the forest.");
        return null;
    }
}
