public class TigerMonkeyHut extends Island{
    private boolean suppliesCollectedToday;

    public TigerMonkeyHut(){
        super("Tiger Monkey Hut", "You are inside the Hut of the Tiger Monkey. It's a small lean-to shelter, carefully woven out of dried leaves and sticks. You wonder how such a creature could build an elaborate structure like this. In the corner is a small chest.");
    }

    @Override
    public void newDay() {
        suppliesCollectedToday = false;
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
    public void collectItem(String item){
        incrementActions();

        switch(item.toLowerCase()) {
            case "rock":
                inventory.put("rock", inventory.getOrDefault("rock", 0) + 1);
                System.out.println("You collect a rock from the Hut.");
                break;
            case "stick":
                inventory.put("stick", inventory.getOrDefault("stick", 0) + 1);
                System.out.println("You gathered a sturdy stick from the forest.");
                break;
            case "supplies":
                if(suppliesCollectedToday){
                    System.out.println("You already collected supplies from the hut today.");
                } else {
                    System.out.println("You creep inside and collect supplies from the tiger-monkey's hut.");
                    inventory.put("supplies", inventory.getOrDefault("supplies", 0) + 1);
                    suppliesCollectedToday = true;
                }
                break;
            default:
                System.out.println("There's no such item here to collect.");
                return;
        }
    }

    public void openChest(){
        System.out.println("You move over to the chest and carefully open it. The lid creaks slightly.");

        if (luckPoints < 70) {
            System.out.println("You find some strange trinkets... but nothing useful.");
        } else {
            System.out.println("You find a FLARE GUN and a single FLARE inside!");
            inventory.put("flare", inventory.getOrDefault("flare", 0) + 1);
            System.out.println("You carefully take the flare gun and flare. This could save your life.");
        }
    }

    public void collectSupplies(){
        System.out.println("You collect the supplies from the chest.");
        suppliesCollectedToday = true;
    }

    public void FightMonkey(){
        if(luckPoints <= 50 && suppliesCollectedToday){
            System.out.println("As you rummage around in the hut, the Tiger Monkey appears in the doorway. He lets out a horrible roar, and begins barrelling towards you.");
            opponent = true;
        }
        else if(luckPoints > 50 && suppliesCollectedToday){
            System.out.println("You hear a rustle outside. The Tiger Monkey could be nearby. You should get out of here soon.");
        }
    }

    @Override
    public void help() {
        String help = """
        📍 You are inside the Tiger Monkey Hut.
        Available Commands:
        - go north / south / east / west
        - collect rock / stick / supplies
        - leave hut
        - open chest
        - drink, eat
        - inventory, stats, help, quit
        """;
        
        if (inventory.getOrDefault("flare", 0) > 0) {
            help += "- use flare\n";
        }

        System.out.println(help);
    }

}
