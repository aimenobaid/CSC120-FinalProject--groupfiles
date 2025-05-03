import javax.swing.JDialog;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import java.awt.BorderLayout;
import java.awt.Frame;


public class Mountain extends Island //implements MountainRequirements 
{
    public boolean atPeak;
    public boolean inCave;

    /**
     * Constructor for the Mountain class.
     * Initializes the mountain with a name and description.
     * Sets the initial state of atPeak and inCave to false.
     * @param name The name of the mountain.
     * @param description The description of the mountain.
     */
    public Mountain() {
        super("You've arrived at the Misty Mountain. There are paths leading up to the peak with rocks and coal along the way.");
        this.atPeak = false;
        this.inCave = false;
    }

    /**
     * Prints description of the mountain to the player.
     */
    @Override
    public void describe() {
        System.out.println(description);
    }

    /**
     * Allows player to collect various items.
     * Prints collection message, adds item to inventory, and increments action counter.
     * If item isn't recognized, prints an error message.
     * @param item The item to be collected (rock, stick, coal).
     */
    @Override
    public void collectItem(String item){
        incrementActions();

        switch(item.toLowerCase()) {
            case "rock":
                inventory.put("rock", inventory.getOrDefault("rock", 0) + 1);
                System.out.println("You pried a rock loose from the cliffside.");
                break;
            case "stick":
                inventory.put("stick", inventory.getOrDefault("stick", 0) + 1);
                System.out.println("You gathered a sturdy stick near a pine tree.");
                break;
            case "coal":
                inventory.put("coal", inventory.getOrDefault("coal", 0) + 1);
                System.out.println("You mined some coal from the mountain.");
                break;
            default:
                System.out.println("There's no such item here to collect.");
                return;
        }
}

    /**
     * Allows player to build a shelter if they have enough resources.
     * If shelter is already built, prints a message and returns.
     * If player has enough rocks and sticks, deducts them from inventory and builds the shelter.
     * If luck points are sufficient, prints success message; otherwise, prints failure message and deducts luck points.
     */
    @Override
    public void buildShelter() {
        if (shelterBuilt) {
            System.out.println("You already have a shelter on the mountain.");
            return;
        }
        if (getItemCount("rock") >= 3 && getItemCount("stick") >= 3) {
            inventory.put("rock", inventory.get("rock") - 3);
            inventory.put("stick", inventory.get("stick") - 3);

            if (luckPoints >= 50) {
                System.out.println("You managed to build a sturdy shelter on the mountain slope.");
                shelterBuilt = true;
            } else {
                System.out.println("You built a shelter... but it collapses. You lose 5 luck points :(");
                adjustLuck(-25);
            }
        } else {
            System.out.println("You need 3 rocks and 3 sticks to build a shelter.");
        }
        incrementActions();
    }

    /**
     * Allows player to reach the peak of the mountain. Prints a message to prompt them to view the map.
     * Sets the atPeak variable to true.
     */
    public void climbMountain() {
        System.out.println("You climb to the peak of the mountain. The view is breathtaking! You can see the entire island from here.");
        atPeak = true;
    }


    /**
     * Displays a map of the island if the player is at the peak.
     * If not at the peak, prints a message indicating that they need to climb to the peak first.
     */
    public void viewMap(){
        if (atPeak) {
            System.out.println("You arrive at the peak and can look over the entire island from here! The view is breathtaking!");
            
            // Create a JDialog with no parent so you can't continue play until closing the map
            JDialog map = new JDialog((Frame)null, "Island Map", true);
            

            ImageIcon mapIcon = new ImageIcon("islandMap.png");
            JLabel mapLabel = new JLabel(mapIcon);
            map.getContentPane().add(mapLabel, BorderLayout.CENTER);
            
            map.pack();
            map.setLocationRelativeTo(null);
            map.setAlwaysOnTop(true);
            map.setVisible(atPeak); // Show the map if at peak

        } else {
            System.out.println("You need to climb to the peak to see the map view.");
        }
    }
    
    /**
     * Displays possible commands for the player specific to the location and several conditions.
     */
    @Override
    public void help(){
        String help = """

        📍 You are on the misty Mountain.
        Available Commands:
        inventory, stats, help
        look around
        go north / south / east / west
        collect coal / rock / stick / supplies
        drink, eat
        build fire
        """;
        
        if (!shelterBuilt) {
            help += """
            build shelter
            """;
        } else{
            help += """
            rest
            """;
        }
        if (atPeak) {
            help += """
            view map
            """;
        } else {
            help += """
            climb mountain
            """; 
        }
        System.out.println(help);
    }
    // ======  Movement methods  ====== //
    
    /**
     * Moves the player to the North Shore and prints a message about their path.
     * @return Their new location (North Shore).
     */
    @Override
    public Island moveNorth() {
        System.out.println("You head downwards and arrive at the North Shore");
        return northExit;
    }

    /**
     * Moves the player to the South Shore and prints a message about their path.
     * @return Their new location (South Shore).
     */
    @Override
    public Island moveSouth() {
        System.out.println("You follow a path around the side of the mountain heading south.");
        return southExit;
    }

    /**
     * Moves the player to the Light Forest and prints a message about their path.
     * @return Their new location (Light Forest).
     */
    @Override
    public Island moveEast() {
        System.out.println("You head down towards the Light Forest and you encounter cave along the path.");
        return eastExit;
    }

    /**
     * Attempts to move the player west but prints a message indicating that they cannot go further west.
     * @return The current location (Mountain).
     */
    @Override
    public Island moveWest() {
        System.out.println("You begin to head down to the Dark Forest but you reach a steep cliff! You cannot go further west from here.");
        return this;
    }
    
}
