import java.util.HashMap;
import java.util.Random;

public abstract class Island implements IslandRequirements {
    protected String name;
    protected String description;

    protected static int luckPoints = 10; // example starting value
    protected static HashMap<String, Integer> inventory = new HashMap<>();
    
    protected static int currentDay = 1;
    protected static int actionsToday = 0;
    protected static final int ACTIONS_PER_DAY = 20;

    // New movement references
    protected Island northExit;
    protected Island southExit;
    protected Island eastExit;
    protected Island westExit;

    public Island(String name, String description) {
        this.name = name;
        this.description = description;
        if (inventory.isEmpty()) {
            inventory.put("rock", 0);
            inventory.put("coal", 0);
            inventory.put("stick", 0);
            inventory.put("fish", 0);
            inventory.put("berries", 0); //light forest
            inventory.put("bread", 0); //from the tiger-monkey's hut
            inventory.put("supplies", 0);
            inventory.put("water", 0);
        }
    }

    /** Define exits for movement */
    public void setExits(Island north, Island south, Island east, Island west) {
        this.northExit = north;
        this.southExit = south;
        this.eastExit = east;
        this.westExit = west;
    }

    /** Default movement methods */
    public Island moveNorth() {
        if (northExit != null) {
            System.out.println("You move north.");
            return northExit;
        } else {
            System.out.println("You can't go north from here.");
            return this;
        }
    }
    public Island moveSouth() {
        if (southExit != null) {
            System.out.println("You move south.");
            return southExit;
        } else {
            System.out.println("You can't go south from here.");
            return this;
        }
    }


    public Island moveEast() {
        if (eastExit != null) {
            System.out.println("You move east.");
            return eastExit;
        } else {
            System.out.println("You can't go east from here.");
            return this;
        }
    }

    public Island moveWest() {
        if (westExit != null) {
            System.out.println("You move west.");
            return westExit;
        } else {
            System.out.println("You can't go west from here.");
            return this;
        }
    }

    // ===== Abstract Methods ===== //
    public abstract void describe(); 
    public abstract void collectItem(String item);
    public abstract void help(); 
    
    // ====== GAME PROGRESSION ====== //
    public static void incrementActions() {
        actionsToday++;
        if (actionsToday >= ACTIONS_PER_DAY) {
            advanceDay();
        }
    }
    public static void advanceDay() {
        currentDay++;
        actionsToday = 0;
        System.out.println("Night falls. You survived Day " + (currentDay - 1) + ". A new day begins.");
        System.out.println("📆 Day " + currentDay);

    }
    public static int getCurrentDay() {
        return currentDay;
    }

    // ===== SURVIVAL ===== //
    public void buildShelter() {
        if (shelterBuilt) {
            System.out.println("You already have a shelter here.");
            return;
        }
        if (!canBuildShelter()) {
            System.out.println("You can't build a shelter here.");
            return;
        }
        if (getItemCount("rock") >= 3 && getItemCount("stick") >= 3) {
            inventory.put("rock", inventory.get("rock") - 3);
            inventory.put("stick", inventory.get("stick") - 3);
            shelterBuilt = true;
            System.out.println("You built a shelter using 3 rocks and 3 sticks.");
        } else {
            System.out.println("You need 3 rocks and 3 sticks to build a shelter.");
        }
        incrementActions();
    }
    
    protected boolean canBuildShelter() {
        return false;  // By default, shelters aren't allowed
    }
    
    protected boolean shelterBuilt = false;  // Centralized shelter tracking

    /** Override in child classes to track shelter status */
    protected boolean hasShelter() {
        return false;
    }

    /** Fire w multiple options */
    public void buildFire() {
        if (getItemCount("stick") >= 3 && getItemCount("rock") >= 2) {
            inventory.put("stick", inventory.get("stick") - 3);
            inventory.put("rock", inventory.get("rock") - 2);
            System.out.println("You built a fire using 3 sticks and 2 rocks.");
        } else if (getItemCount("stick") >= 1 && getItemCount("coal") >= 1) {
            inventory.put("stick", inventory.get("stick") - 1);
            inventory.put("coal", inventory.get("coal") - 1);
            System.out.println("You built a fire using 1 stick and 1 coal.");
        } else {
            System.out.println("You don't have the materials to build a fire.");
        }
        incrementActions();
    }
    /** Combat */
    public void fight() {
        Random rand = new Random();
        int chance = rand.nextInt(100) + 1;
        System.out.println("You engage in a fight...");
        if (chance <= luckPoints) {
            System.out.println("You win the fight!");
            adjustLuck(5);
        } else {
            System.out.println("You lost the fight...idiot");
            adjustLuck(-10);
        }
        incrementActions();
    }
    /** Exploring */
    public void lookAround() {
        System.out.println("You look around: " + description);
        incrementActions();
    }

    // ====== LUCK SYSTEM ==== //
    public static void adjustLuck(int change) {
        luckPoints += change;
        System.out.println("Luck is now: " + luckPoints);
    }

    // ===== INVENTORY ===== //
    public static int getItemCount(String item) {
        return inventory.getOrDefault(item, 0);
    }
    public static void printInventory() {
        System.out.println("Current Inventory: " + inventory);
    }


    // ===== Getters ===== //
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    
}
//just added stuff based on what we talked about and anything else I could think of, if anything is annoying to implement lmk.
//looks good to me! do you think it would be useful to have a moveItem() method? 
//I wrote one in MtnCave in order to move the rock to enter the cave but thought it might be universally useful. tbd once we build out all the locs i guess