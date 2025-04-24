public class Waterfall extends Island implements WaterfallRequirements {
   
    
    public Waterfall() {
        super("Waterfall", "You have reached a waterfall that flows into a fresh water stream headed towards the South Shore. The water is clear and you can see fish swimming in the stream. There are rocks along the shore, and a path leading to a small alcove behind the waterfall.");
    }

    @Override
    public void describe() {
        System.out.println(description);
    }

    //will be @Overdride
    public void help() {
        System.out.println("""
                Commands:
                go north/south/east/west
                collect rock/stick/water/supplies/etc
                enter alcove
                open chest
                leave alcove
                drink, eat
                inventory, stats, help
                """);
    }


    public void enterAlcove() {
        System.out.println("You enter the alcove behind the waterfall. It is dark and damp, but you can see a small chest hidden in the corner.");
    }

    public void openChest() {
        System.out.println("You open the chest and find some supplies inside.");
    }

    public void leaveAlcove() {
        System.out.println("You leave the alcove and step back out into the sunlight. The waterfall is beautiful, and you can hear the sound of the water rushing by.");
    }

    ///waittt should these be under main and just be if statements instead of methods that get called just to say no?
    public void swim(){
        System.out.println("The water is rough and choppy. You should not swim here. Try farther downstream.");
    }

    public void fish() {
        System.out.println("The water is rough and choppy. It is difficult to fish here. Try farther downstream.");
    }


    // Movement methods --- idk what these should be returning tbh so rn theyre all this
    @Override
    public Island moveNorth() {
        System.out.println("You return to the mountain path and begin climbing towards Misty Mountain peak. Move North again to continue the climb.");
        return new Mountain();
    }

    @Override
    public Island moveSouth() {
        System.out.println("You follow the stream south towards the South Shore. Move south again to reach the beach.");
        return new Stream();
    }

    @Override
    public Island moveEast() {
        System.out.println("You walk into the woods and enter the Light Forest. Through the trees you can see the ruins of a temple. A path going east leads deeper into the forest. You can hear birds and other animals chirping in the distance.");
        return new LightForest();
    }

    @Override
    public Island moveWest() {
        System.out.println("You walk into the woods and enter the Dark Forest. The trees are thick and the air is heavy with the smell of damp earth. A path going west leads deeper into the forest. You notice large paw prints in the mud.");
        return this;
        //return new DarkForest(); uncomment once DarkForest.java is built
    }

}
