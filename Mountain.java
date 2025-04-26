public class Mountain extends Island //implements MountainRequirements
{
    public boolean shelterBuilt;
    public boolean atPeak;
    public boolean inCave;

    public Mountain() {
        super("Mountain", "You are at the Misty Mountain. There are paths leading up to the peak with rocks and coal along the way.");
        this.shelterBuilt = false;
    }

    @Override
    public void describe() {
        System.out.println(description);
    }

    //will be @Override
    public void help(){
        String help = """
                        Commands:
                        inventory, stats, help
                        go north/south/east/west
                        collect coal/rock/stick/water/supplies/etc
                        drink, eat
                        """;
        if (!shelterBuilt) {
            help += """
                    build shelter
                    """;
        }
        if (atPeak) {
            help += """
                    view map
                    """;
        } else {
            help += """
                    climb mountain to reach peak
                    """;
        }
        System.out.println(help);
    }

    public void collectItem(String item){
        inventory.put(item, inventory.getOrDefault(item, 0) + 1);
        incrementActions();
        
        switch(item.toLowerCase()) {
            case "rock":
                System.out.println("You pried a rock loose from the cliffside.");
                break;
            case "stick":
                System.out.println("You gathered a sturdy stick near a pine tree.");
                break;
            case "coal":
                System.out.println("You mined some coal from the mountain.");
                break;
            default:
                System.out.println("There's no such item here.");
        }
    }

    public void climbMountain() {
        System.out.println("You climb to the peak of the mountain. The view is breathtaking! You can see the entire island from here.");
        atPeak = true;
        //add pop up map here
    }

    //enter the cave by pushing the rock
    //might want to restrict this to only be an option if the person is at the cave entrance 
    //but that would be annoying so as long as we never suggest pushing a rock in another context in mtn its prob fine
    //its not in help() for this reason...
    public Island pushRock(){
        System.out.println("You push the rock blocking the cave entrance. It rolls away, revealing a dark cave.");
        inCave = true;
        return new MtnCave();
    }

    public void viewMap(){
        if (atPeak) {
            System.out.println("You pull out the map and see the entire island.");
            // Add code to display the map here
        } else {
            System.out.println("You need to climb to the peak to see the map view.");
        }
    }

    // Movement methods
    @Override
    public Island moveNorth() {
        System.out.println("You head downwards and arrive at the North Shore");
        return new NorthShore();
    }

    @Override
    public Island moveSouth() {
        System.out.println("You are at a waterfall that flows into a fresh water stream headed towards the South Shore.");
        return new Waterfall();
    }

    @Override
    public Island moveEast() {
        System.out.println("You head down towards the Light Forest and you see a cave along the path. There is a rock blocking what appears to be an opening to the cave. Push the rock to try to enter the cave. Move East again to continue on to the forest.");
        return new LightForest();
    }

    @Override
    public Island moveWest() {
        System.out.println("You begin to head down to the Dark Forest but you reach a steep cliff! You cannot go further west from here.");
        return this;
    }


    public static void main(String[] args) {
        Mountain mountain = new Mountain();
        mountain.describe();
        mountain.help();
        mountain.collectItem("rock");
        mountain.collectItem("stick");
        mountain.collectItem("coal");
    
        mountain.climbMountain();
        mountain.viewMap();
        mountain.moveNorth(); 
        mountain.moveSouth(); 
        mountain.moveEast(); 
        mountain.moveWest(); 
    }
    
}
