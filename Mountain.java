public class Mountain extends Island //implements MountainRequirements
{
    public boolean shelterBuilt;
    public boolean atPeak;

    public Mountain() {
        super("Mountain", "You are at the Misty Mountain. There are paths leading up to the peak with rocks and coal along the way.");
        this.shelterBuilt = false;
    }

    @Override
    public void describe() {
        System.out.println(description);
    }
    
    public void mineRock() {
        System.out.println("You mine a rock from the mountain.");
        addItem("rock");
    }

    public void mineCoal() {
        System.out.println("You mine coal from the mountain.");
        addItem("coal");
    }

    public void climbMountain() {
        System.out.println("You climb to the peak of the mountain. The view is breathtaking! You can see the entire island from here.");
        atPeak = true;
        //add pop up map here
    }

    public void viewMap(){
        if (atPeak) {
            System.out.println("You pull out the map and see the entire island. You can see the North Shore, South Shore, Light Forest to the east, and Dark Forest to the west.");
        } else {
            System.out.println("You need to climb to the peak to see the map view.");
        }
    }

    // Movement methods --- idk what these should be returning tbh so rn theyre all this
    @Override
    public Island moveNorth() {
        System.out.println("You head down towards the North Shore. Move North again to reach the shore.");
        return this;
    }

    @Override
    public Island moveSouth() {
        System.out.println("You are at a waterfall that flows into a fresh water stream headed towards the South Shore.");
        return this;
    }

    @Override
    public Island moveEast() {
        System.out.println("You head down towards the Light Forest and you see a cave along the path. There appears to be an opening to the cave. Move East again to continue on to the forest.");
        return this;
    }

    @Override
    public Island moveWest() {
        System.out.println("You begin to head down to the Dark Forest but you reach a steep cliff! You cannot go further west from here.");
        return this;
    }


    public static void main(String[] args) {
        Mountain mountain = new Mountain();
        mountain.describe();
        mountain.mineRock();
        mountain.mineCoal();
        mountain.climbMountain();
        mountain.viewMap();
        mountain.moveNorth(); 
        mountain.moveSouth(); 
        mountain.moveEast(); 
        mountain.moveWest(); 
    }
    
}



 //attributes
    //location in the world - center of island

    //methods
    //i think fight should be inherited bc it will be needed in the dark forest as well


    //has a cliff, peak, a cave, and a waterfall
    //should cave and waterfall be separate classes? i kept peak and cliff here bc they dont do much
    //peak has a map (view of the island)
    //cave has a troll (guarding treasure? but must be lucky to kill?)
    //waterfall has a hidden cave behind it with resources

    //waterfall is south of mtn peak
    //cave is east of mtn peak
    //cliff is west of mtn peak