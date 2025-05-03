public class TigerMonkeyHut extends Island{
    private boolean suppliesCollectedToday;

    /**
     * Constructor for the Tiger Monkey Hut class.
     * Initializes the light forest with a description and sets the initial state of suppliesCollectedToday to false.
     * @param description The description of the Tiger Monkey Hut.
     */
    public TigerMonkeyHut(){
        super("You are inside the Hut of the Tiger Monkey. It's a small lean-to shelter, carefully woven out of dried leaves and sticks. You wonder how such a creature could build an elaborate structure like this. In the corner is a small chest.");
        this.suppliesCollectedToday = false;
    }

    /**
     * Prints description of the Tiger Monkey Hut.
     */
    @Override
    public void describe(){
        System.out.println(description);
    }

    /**
     * Allows player to collect items from the light forest and prints a collection message.
     * Increments the action count and adds item to inventory. Prints error message if item is not recognized.
     * @param item The item to be collected (rock, stick, supplies).
     */
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

    /**
     * Collects supplies from the hut.
     * Sets suppliesCollectedToday to true.
     */
    public void collectSupplies(){
        System.out.println("You collect the supplies from the chest.");
        suppliesCollectedToday = true;
    }

    /**
     * Opens the chest in the hut.
     * If luckPoints are less than 70, finds strange trinkets.
     * If luckPoints are 70 or more, find a flare to be rescued.
     */
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

    /**
     * Checks if the player has already collected supplies today.
     * @return true if supplies have been collected today, false otherwise.
     */
    @Override
    public void newDay() {
        suppliesCollectedToday = false;
    }
    
    /**
     * Allows the player to leave the hut.
     * If the player is in a fight, they cannot leave.
     * @return Their new location (Dark Forest).
     */
    public Island leaveHut(){
        if(opponent == true){
            System.out.println("You can't leave now, you're in the middle of a fight!");
            return null;
        }
        else{
            System.out.println("You quietly exit the hut and step back into the forest.");
            return northExit;
        }
    }

    /**
     * Checks if the player is in a fight with the Tiger Monkey.
     * If luckPoints are less than or equal to 50 and supplies have been collected today, the player fights the Tiger Monkey.
     * If luckPoints are greater than 50 and supplies have been collected today, the player hears a rustle outside.
     * @return true if the player is in a fight, false otherwise.
     */
    public boolean FightMonkey(){
        if(luckPoints <= 50 && suppliesCollectedToday){
            System.out.println("As you rummage around in the hut, the Tiger Monkey appears in the doorway. He lets out a horrible roar, and begins barrelling towards you.");
            opponent = true;
            if(!fight()){
                System.out.println("You lost your fight with the Tiger Monkey!");
                return false;
            }
            else{
                System.out.println("You knock the Tiger Monkey unconscious. He falls to the hard floor. You live to steal another day. Maybe it's time to get out of here?");
                opponent = false;
                return true;
            }
        }
        else if(luckPoints > 50 && suppliesCollectedToday){
            System.out.println("You hear a rustle outside. The Tiger Monkey could be nearby. You should get out of here soon.");
            return true;
        }
        return true;
    }

    /**
     * Displays possible commands for the player specific to the location and several conditions.
     */
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

    /**
     * Moves the player to the Dark Forest and prints a message about their path.
     * @return Their new location (Dark Forest).
     */
    @Override
    public Island moveNorth() {
        System.out.println("You move North into the Dark Forest. Move North again to reach the North Shore.");
        return northExit;
    }

    /**
     * Moves the player to the Waterfall and prints a message about their path.
     * @return Their new location (WaterFall).
     */
    @Override
    public Island moveSouth() {
        System.out.println("You are at a waterfall that flows into a fresh water stream headed towards the South Shore.");
        return southExit;
    }

    /**
     * Moves the player to the Mountain and prints a message about their path.
     * @return Their new location (Mountain).
     */
    @Override
    public Island moveEast() {
        System.out.println("You head up towards the Mountains.");
        return eastExit;
    }

    /**
     * Attempts to move the player further west but prints a message indicating that they cannot go further west.
     * @return Their current location.
     */
    @Override
    public Island moveWest() {
        System.out.println("You move deeper into the Dark Forest, but slowly find the vegetation too dense to walk through.");
        return this;
    }

}
