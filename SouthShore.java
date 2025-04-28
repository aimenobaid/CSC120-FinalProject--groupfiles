public class SouthShore extends Island implements SouthShoreRequirements {
    private boolean suppliesCollectedToday;

    public SouthShore() {
        super("South Shore", "You are on the South Shore. You see shipwreck debris on the shore and a stream winding down to the water from a mountain peak in the distance.");
        this.suppliesCollectedToday = false;
    }

    @Override
    public void describe() {
        System.out.println(description);
    }

    @Override
    public void help() {
        String help = """
        📍 You are on the South Shore.
        Available Commands:
        - go north / south / east / west
        - collect rock / stick / water / supplies / fish
        - drink, eat
        - build fire
        - look around
        - inventory, stats, help, quit
            """;
        System.out.println(help);
    }

    @Override
    public void collectItem(String item){
        inventory.put(item, inventory.getOrDefault(item, 0) + 1);
        incrementActions();
        
        switch(item.toLowerCase()) {
            case "rock":
                System.out.println("You found a rock by the shore.");
                break;
            case "stick":
                System.out.println("You gathered a stick from the water's edge.");
                break;
            case "water":
                System.out.println("You collected fresh water from the base of the stream.");
                break;
            case "supplies":
                if (!suppliesCollectedToday) {
                    System.out.println("You find washed-up supplies from an old wreck.");
                    collectItem("supplies");
                    suppliesCollectedToday = true;
                } else {
                    System.out.println("Youve already scavenged everything for today.");
                }
                break;
            case "fish":
                System.out.println("You speared a fish from the shallows.");
                break;
            default:
                System.out.println("There's no such item here.");
        }
    }
    /* this will be needed for later when we need to reset supplies on the south shore daily 
    but it'll require making advance day not static and changing a few other things too so i'm 
    putting a pin in it for now and we can discusss how to do it together
    */
    // @Override
    // public void advanceDay() {
    // super.advanceDay();
    // suppliesCollectedToday = false;
    // } 
     
    @Override
    public void buildShelter() {
        try {
            throw new Exception("The south shore is not a good place to build a shelter. It is windy and exposed. Try somewhere else.");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    // Movement methods
    @Override
    public Island moveNorth() {
        System.out.println("You follow the stream up towards the Misty Mountain. There is a waterfall up ahead. Move north again to reach the waterfall.");
        return northExit;
    }

    @Override
    public Island moveSouth() {
        System.out.println("You walk to the water's edge. You can't go further south. You will drown.");
        return this;
    }

    @Override
    public Island moveEast() {
        System.out.println("You walk along the shore and enter the Light Forest. You see the ruins of temples in the distance.");
        return eastExit;
    }

    @Override
    public Island moveWest() {
        System.out.println("You enter the Dark Forest. The foliage is dense and the trees are tall. You can hear the sounds of creatures in the distance.");
        return westExit; 
    }
}
