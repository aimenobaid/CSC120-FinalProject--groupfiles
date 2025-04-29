public class DarkForest extends Island implements DarkForestRequirements{
    public DarkForest() {
        super("Dark Forest", "You are in the Dark Forest.");
    }

    @Override
    protected boolean canBuildShelter() {
        return true;  // Shelter is possible, but risky
    }

    public void forage(){
        System.out.println("You forage in a small bush.");
        if(luckPoints >= 50){
            collectItem("berry");
            adjustLuck(1);
        }
        if(luckPoints < 50){
            System.out.println("You didn't find anything in the bush.");
        }
    }


    @Override
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
        }
    }

    @Override
    public void buildShelter() {
        if (shelterBuilt) {
            System.out.println("A crude shelter already stands here, blending into the shadows.");
            return;
        }
        if (getItemCount("rock") >= 3 && getItemCount("stick") >= 3) {
            inventory.put("rock", inventory.get("rock") - 3);
            inventory.put("stick", inventory.get("stick") - 3);

            if (luckPoints >= 60) {
                System.out.println("You carefully construct a hidden shelter, safe from the lurking creatures.");
                shelterBuilt = true;
            } else {
                System.out.println("As night falls, your fragile shelter collapses and you're ambushed by forest creatures! You lose 10 luck points.");
                adjustLuck(-10);
            }
        } else {
            System.out.println("You need 3 rocks and 3 sticks to build a shelter.");
        }
        incrementActions();
    }

    @Override
    public void help() {
        String help = """
            📍 You are in the Dark Forest.
            Available Commands:
            - go north / south / east / west
            - collect rock / stick
            - monkey army
            - build fire
            - look around
            - rest
            - inventory, stats, help, quit
        """;
        if (!shelterBuilt) {
            help += "- build shelter\n";
        }
        System.out.println(help);
    }

    public void monkeyArmy(){

    }

    public void volcanicEruption(){
        
    }

    public void describe(){
        System.out.println(description);
    }


    @Override
    public Island moveNorth() {
        System.out.println("You head down towards the North Shore. Move North again to reach the shore.");
        return northExit;
    }

    @Override
    public Island moveSouth() {
        System.out.println("You are at a waterfall that flows into a fresh water stream headed towards the South Shore.");
        return southExit;
    }

    @Override
    public Island moveWest() {
        System.out.println("You head east, deeper into the forest, but find yourself getting lost. You decide to head back.");
        return this;
    }

    @Override
    public Island moveEast() {
        System.out.println("You head up towards the Mountains.");
        return eastExit;
    }
}
