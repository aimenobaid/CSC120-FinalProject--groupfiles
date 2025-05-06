
public class NorthShore extends Island implements NorthShoreRequirements {

    public NorthShore() {
        super("You are on the North Shore. There are rocks, sticks, fresh pools, and fish.");
    }

    @Override
    public void describe() {
        System.out.println(description);
    }

    @Override
    protected boolean canBuildShelter() {
        return true;  // North Shore supports shelter building
    }

    @Override
    public void buildShelter() {
        if (!shelterBuilt) {
            super.buildShelter();  // Use standard recipe and logic
            if (shelterBuilt) {
                System.out.println("You built a sturdy shelter overlooking the ocean, with the sound of waves to keep you company.");
            }
        } else {
            System.out.println("Your shelter stands firm on the shore, ready for another night.");
        }
    }

    @Override
    protected boolean hasShelter() {
        return shelterBuilt;  // Check if shelter is built
    }

    @Override
    public void collectItem(String item){
        incrementActions();

        switch(item.toLowerCase()) {
            case "rock":
                inventory.put("rock", inventory.getOrDefault("rock", 0) + 1);
                System.out.println("You collected a rock from the shore.");
                break;
            case "stick":
                inventory.put("stick", inventory.getOrDefault("stick", 0) + 1);
                System.out.println("You gathered a stick from the shore.");
                break;
            case "water":
                inventory.put("water", inventory.getOrDefault("water", 0) + 1);
                System.out.println("You collected fresh water from a tide pool.");
                break;
            case "fish":
                inventory.put("fish", inventory.getOrDefault("fish", 0) + 1);
                System.out.println("You speared a fish from the shallows.");
                break;
            case "supplies":
                inventory.put("supplies", inventory.getOrDefault("supplies", 0) + 1);
                System.out.println("You found some supplies washed up on the shore.");
                adjustLuck(2);
                break;
            default:
                System.out.println("There's no such item here to collect.");
        }
    }

    @Override
    public void help() {
        String help = """

            📍 You are on the North Shore.
            Available Commands:
            - go north / south / east / west
            - collect rock / stick / water / fish / supplies
            - drink, eat
            - build fire
            - look around
            - rest
            - inventory, stats, help, quit
            """;
        if (!shelterBuilt) {
            help += """
                    build shelter
                    """;
        }
        System.out.println(help);
    }



    // Movement methods
    @Override
    public Island moveNorth() {
        System.out.println("You walk to the water's edge. You can't go further north.");
        return this;
    }

    @Override
    public Island moveSouth() {
        System.out.println("You follow a path leading south into the forest.");
        return southExit;
    }

    @Override
    public Island moveEast() {
        System.out.println("You walk along the shore and enter the Light Forest. You see the ruins of temples in the distance");
        return eastExit;
    }

    @Override
    public Island moveWest() {
        System.out.println("You walk along the shore and enter the Dark Forest. The foliage is dense and the trees are tall. You can hear the sounds of creatures in the distance.");
        return westExit;
    }
}
