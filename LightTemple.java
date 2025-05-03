public class LightTemple extends Island implements LightForestRequirements {

    public LightTemple(){
        super("Light Temple", "You are in the Light Temple. The white marble walls are solid and strong. Against one is a collection of strange humanoid figures.");
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
            //add cases for other items
            default:
                System.out.println("There's no such item here.");
                return;
        }
    }
    
    @Override
    public void describe(){
        System.out.println(description);
    }

    public void pray(){
        System.out.println("You take a moment to pray at the temple's altar. You wasted about an hour of your time.");
        adjustLuck(-2);
    }

    public void forage(){
        System.out.println("You forage in a small bush near the temple.");
        if(luckPoints >= 50){
            collectItem("berry");
            adjustLuck(1);
        }
        if(luckPoints < 50){
            System.out.println("You didn't find anything in the bushes near the temple.");
        }
    }

    @Override
    public void help() {
    String help = """
        📍 You are in the Light Temple.
        Available Commands:
        - go north / south / east / west
        - collect rock / stick / berries
        - forage
        - pray
        - build fire
        - look around
        - rest
        - inventory, stats, help, quit
        """;
    System.out.println(help);
    }

    @Override
    public Island moveNorth() {
        System.out.println("");
        return northExit;
    }

    @Override
    public Island moveSouth() {
        System.out.println("");
        return southExit;
    }

    @Override
    public Island moveEast() {
        System.out.println("");
        return eastExit;
    }

    @Override
    public Island moveWest() {
        System.out.println("");
        return westExit;
    }
}
