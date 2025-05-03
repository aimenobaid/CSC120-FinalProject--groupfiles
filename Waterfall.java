public class Waterfall extends Island implements WaterfallRequirements {
   
    public boolean treasureFound = false;

    
    public Waterfall() {
        super("You have reached a waterfall that flows into a fresh water stream headed towards the South Shore. The water is clear and you can see fish swimming in the stream. There are rocks along the shore, and a path leading to a small alcove behind the waterfall.");
    }

    @Override
    public void describe() {
        System.out.println(description);
    }

    @Override
    public void collectItem(String item){
        incrementActions();
    
        switch(item.toLowerCase()) {
            case "rock":
                inventory.put("rock", inventory.getOrDefault("rock", 0) + 1);
                System.out.println("You collected a rock near the waterfall.");
                break;
            case "stick":
                inventory.put("stick", inventory.getOrDefault("stick", 0) + 1);
                System.out.println("You gathered a stick near the waterfall.");
                break;
            case "water":
                inventory.put("water", inventory.getOrDefault("water", 0) + 1);
                System.out.println("You collected fresh water from the waterfall.");
                break;
            case "treasure":
                if (!treasureFound) {
                    System.out.println("You collect the treasure from the chest. Your luck increases by 5!");
                    Island.adjustLuck(5);
                    treasureFound = true;
                } else {
                    System.out.println("You already collected the treasure.");
                }
                break;
            default:
                System.out.println("There's no such item here to collect.");
                return;
        }
    }

    @Override
    public void newDay() {
        treasureFound = false;
    }

    
    public void enterAlcove() {
        System.out.println("You enter the alcove behind the waterfall. It is dark and damp, but you can see a small chest hidden in the corner.");
    }

    public void openChest() {
        System.out.println("You open the chest and find treasure inside! Collect it to increase your luck.");
    }

    public void leaveAlcove() {
        System.out.println("You leave the alcove and step back out into the sunlight. The waterfall is beautiful, and you can hear the sound of the water rushing by.");
    }

    public void swim(){
        System.out.println("The water is rough and choppy. You should not swim here. Try farther downstream.");
    }


    @Override
    public void help() {
        String help = """
        📍 You are at the Waterfall.
        Available Commands:
        - go north / south / east / west
        - collect rock / stick / water / treasure
        - enter alcove
        - open chest
        - leave alcove
        - build fire
        - look around
        - rest
        - inventory, stats, help, quit
        """;
    System.out.println(help);
    }

    @Override
    public Island moveNorth() {
        System.out.println("You return to the mountain path and begin climbing towards Misty Mountain peak.");
        return northExit;
    }

    @Override
    public Island moveSouth() {
        System.out.println("You follow the stream south towards the South Shore. Move south again to reach the beach.");
        return southExit;
    }

    @Override
    public Island moveEast() {
        System.out.println("You walk into the woods and enter the Light Forest. Through the trees you can see the ruins of a temple. A path going east leads deeper into the forest. You can hear birds and other animals chirping in the distance.");
        return eastExit;
    }

    @Override
    public Island moveWest() {
        System.out.println("You walk into the woods and enter the Dark Forest. The trees are thick and the air is heavy with the smell of damp earth. A path going west leads deeper into the forest. You notice large paw prints in the mud.");
        return westExit;
    }

}
