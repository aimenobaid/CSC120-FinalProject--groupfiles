import java.awt.*;
import javax.swing.*;

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
                return;
        }
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

    public void climbMountain() {
        System.out.println("You climb to the peak of the mountain. The view is breathtaking! You can see the entire island from here.");
        atPeak = true;
    }


    public void viewMap(){
        if (atPeak) {
            System.out.println("You arrive at the peak and can look over the entire island from here! The view is breathtaking!");
            
            // Create a JFrame to display the map
            JFrame frame = new JFrame();
            frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
            frame.setSize(600, 600);
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);

            JDialog dialog = new JDialog(frame, "Island Map", true);
            dialog.setDefaultCloseOperation(JDialog.DISPOSE_ON_CLOSE);
            
            ImageIcon mapIcon = new ImageIcon("islandMap.png"); // Replace with your image path
            JLabel mapLabel = new JLabel(mapIcon);

            dialog.getContentPane().add(mapLabel, BorderLayout.CENTER);
            dialog.pack();
            dialog.setLocationRelativeTo(null);

            dialog.setVisible(atPeak); // Show the map if at peak

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
