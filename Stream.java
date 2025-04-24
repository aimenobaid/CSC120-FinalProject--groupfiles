public class Stream extends Island {

    public Stream() {
        super("Stream", "You arrive at a freshwater stream connecting the mountain to the shore.");
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
                collect fish/rock/stick/water/supplies/etc
                swim
                drink, eat
                inventory, stats, help
                """);
    }

    
    public void collectItem(String item){
        inventory.put(item, inventory.getOrDefault(item, 0) + 1);
        incrementActions();
        
        switch(item.toLowerCase()) {
            case "rock":
                System.out.println("You collected a rock from the forest floor.");
                break;
            case "stick":
                System.out.println("You gathered a sturdy stick from the forest.");
                break;
            case "water":
                System.out.println("You collected fresh water from the stream.");
                break;
            case "fish":
                System.out.println("You speared a fish from the shallows.");
                break;
            default:
                System.out.println("There's no such item here.");
        }
    }


    public void swim() {
        System.out.println("You swim in the stream. The water is refreshing and clear.");
        Island.adjustLuck(1);
    }

    @Override
    public Island moveNorth() {
        System.out.println("You follow the stream up towards the Misty Mountain. You find yourself in front of a beautiful waterfall. There is a path leading to a small alcove behind the water.");
        return new Waterfall();
    }

    @Override
    public Island moveSouth() {
        System.out.println("You follow the stream down towards the South Shore.");
        return new SouthShore();
    }

    @Override
    public Island moveEast() {
        System.out.println("You leave the stream and enter the Light Forest. You see temple ruins up ahead.");
        return new LightForest();
    }

    @Override
    public Island moveWest() {
        System.out.println("You leave the stream and enter the dense foliage of the Dark Forest. The trees are tall and the air is thick with the smell of damp earth. You can hear the sounds of creatures in the distance.");
        return new DarkForest();  
    }
}
