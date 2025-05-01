public class TigerMonkeyHut extends Island{
    private boolean suppliesCollectedToday;

    public TigerMonkeyHut(){
        super("Tiger Monkey Hut", "You are inside the Hut of the Tiger Monkey. It's a small lean-to shelter, carefully woven out of dried leaves and sticks. You wonder how such a creature could build an elaborate structure like this. In the corner is a small chest.");
    }

    @Override
    public void describe(){
        System.out.println(description);
    }

    public Island leaveHut(){
        System.out.println("You quietly exit the hut and step back into the forest.");
        return northExit;
    }

    // Movement methods
    @Override
    public Island moveNorth() {
        System.out.println("You move North into the Dark Forest. Move North again to reach the North Shore.");
        return northExit;
    }

    @Override
    public Island moveSouth() {
        System.out.println("You are at a waterfall that flows into a fresh water stream headed towards the South Shore.");
        return southExit;
    }

    @Override
    public Island moveEast() {
        System.out.println("You head up towards the Mountains.");
        return eastExit;
    }

    @Override
    public Island moveWest() {
        System.out.println("You move deeper into the Dark Forest, but slowly find the vegetation too dense to walk through.");
        return this;
    }
    @Override
    // Common actions across all islands
    public void collectItem(String item){
        inventory.put(item, inventory.getOrDefault(item, 0) + 1);
        incrementActions();
        
        switch(item.toLowerCase()) {
            case "rock":
                System.out.println("You collect a rock from the Hut.");
                break;
            case "stick":
                System.out.println("You gathered a sturdy stick from the forest.");
                break;
            case "supplies":
                if(suppliesCollectedToday){
                    System.out.println("You already collected supplies from the hut today.");
                } else {
                    System.out.println("You creep inside and collect supplies from the tiger-monkey's hut.");
                }
            default:
                System.out.println("There's no such item here.");
        }
    }

    public void openChest(){
        System.out.println("You move over to the chest and carefully open it. The lid creaks slightly.");
        if(luckPoints < 70){

        }
        if(luckPoints >= 70){

        }
    }

    public void collectSupplies(){
        System.out.println("You collect the supplies from the chest.");
        suppliesCollectedToday = true;
    }

    public void FightMonkey(){
        if(luckPoints < 50){
            
        }
        if(luckPoints >= 50){
            
        }
    }

    @Override
    public void help() {
        String help = """
        📍 You are inside the Tiger Monkey Hut.
        Available Commands:
        go north / south / east / west
        collect rock / stick
        leave hut
        drink, eat
        inventory, stats, help
        """;
        System.out.println(help);
    }

}
