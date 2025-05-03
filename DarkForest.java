public class DarkForest extends Island implements DarkForestRequirements{

    public DarkForest() {
        super("Dark Forest", "You are in the Dark Forest.");
        super.opponent = true;
    }

    @Override
    public void describe(){
        System.out.println(description);
    }

    @Override
    protected boolean canBuildShelter() {
        return true;  // Shelter is possible, but risky
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
        
            if (luckPoints >= 80) {
                System.out.println("You carefully construct a hidden shelter, safe from the lurking creatures.");
                shelterBuilt = true;
            } else {
                // TRIGGER VOLCANIC EXPLOSION
                if (volcanicEruption()) {
                    Player globalPlayer = Player.getInstance();
                    globalPlayer.changeHealth(-101);
                    globalPlayer.die();
                } else {
                    System.out.println("As night falls, your fragile shelter collapses and you're ambushed by forest creatures! You lose 10 luck points.");
                    adjustLuck(-10);
                }
            }
        } else {
            System.out.println("You need 3 rocks and 3 sticks to build a shelter.");
        }
        incrementActions();
    }

    @Override
    public void collectItem(String item){
        incrementActions();
    
        switch(item.toLowerCase()) {
            case "rock":
                inventory.put("rock", inventory.getOrDefault("rock", 0) + 1);
                System.out.println("You collected a rock from the forest floor.");
                break;
            case "stick":
                inventory.put("stick", inventory.getOrDefault("stick", 0) + 1);
                System.out.println("You gathered a sturdy stick from the forest.");
                break;
            default:
                System.out.println("There's no such item here to collect.");
                return;
        }
    }

    public void forage(){
        System.out.println("You forage in a small bush, and find a few berries. But they have suspicious dark spots and you don't see any animals nearby. You leave them on the bush and wipe their juices on your pants.");
        if(luckPoints >= 50){
            adjustLuck(-1);
        }
        if(luckPoints < 50){
            System.out.println("You didn't find anything in the bush.");
        }
    }

    @Override
    public boolean volcanicEruption(){
        if(luckPoints < 40){
            System.out.println("You feel a sudden rumbling of the ground, and turn towards the Mountain. The top of the mountain shakes, before exploding into a cloud of black dust and ash. You're frozen for a moment, before you see hot lava and pyroclastic flow begin to rush down the side of the mountain. You have no chance of running.");
            return true;
        }
        return false;
    }

    @Override
    public void help() {
        String help = """
            📍 You are in the Dark Forest.
            Available Commands:
            - go north / south / east / west
            - collect rock / stick
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
        System.out.println("You move deeper into the forest. As the vegetation grows denser and darker, you hear a rumble to your left. You crouch down quickly into the bush.");
        System.out.println("A strange creature emerges out of the bushes. It walks upright, with a snout like a monkey's and strong gorilla arms. It has orange and black fur like a tiger's. It moves west through the bushes, dragging its long tail behind. You decide to follow.");
        System.out.println("The Tiger Monkey is approaching a brown structure, where it remains for a moment. You hear it rustle around before leaving the structure again, and moves into the forest. You go inside the structure.");
        return westExit;
    }

    @Override
    public Island moveEast() {
        System.out.println("You head up towards the Mountains.");
        return eastExit;
    }
}
