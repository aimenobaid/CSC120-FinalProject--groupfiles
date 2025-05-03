import java.awt.BorderLayout;
import java.awt.Frame;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;


public class Mountain extends Island //implements MountainRequirements 
{
    public boolean atPeak;
    public boolean inCave;

    public Mountain() {
        super("Mountain", "You've arrived at the Misty Mountain. There are paths leading up to the peak with rocks and coal along the way.");
        this.atPeak = false;
        this.inCave = false;
    }

    @Override
    public void describe() {
        System.out.println(description);
    }

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
                adjustLuck(-5); // temporary will replace with a gameover sich
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

    public void climbMountain() {
        System.out.println("You climb to the peak of the mountain. The view is breathtaking! You can see the entire island from here.");
        atPeak = true;
    }


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
    
    @Override
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
    
    // Movement methods
    @Override
    public Island moveNorth() {
        System.out.println("You head downwards and arrive at the North Shore");
        return northExit;
    }

    @Override
    public Island moveSouth() {
        System.out.println("You follow a path around the side of the mountain heading south.");
        return southExit;
    }

    @Override
    public Island moveEast() {
        System.out.println("You head down towards the Light Forest and you encounter cave along the path.");
        return eastExit;
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
