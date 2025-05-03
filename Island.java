import java.util.HashMap;
import java.util.Random;

public abstract class Island implements IslandRequirements {

    public static Waterfall getWaterfallInstance() {
        return waterfallInstance;
    }

    public static void setWaterfallInstance(Waterfall waterfallInstance) {
        Island.waterfallInstance = waterfallInstance;
    }
    protected String name;
    protected String description;
    protected boolean opponent = false;

    protected static int luckPoints = 50; // example starting value
    protected static HashMap<String, Integer> inventory = new HashMap<>();
    
    protected static int currentDay = 1;
    protected static int actionsToday = 0;
    protected static final int ACTIONS_PER_DAY = 20;
    protected static final int RESCUE_DAY = 10;

    // New movement references
    protected Island northExit;
    protected Island southExit;
    protected Island eastExit;
    protected Island westExit;

    public static SouthShore southShoreInstance; 
    public static Waterfall waterfallInstance;

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

        if(actionsToday%5==0){
            Player globalPlayer = Player.getInstance();
            globalPlayer.increaseHunger(5);
            globalPlayer.increaseThirst(5);
        }
        System.out.println("You are getting tired. Your hunger and thirst are increasing.");
    }

    public void newDay() {
    }

    public static void advanceDay() {
        currentDay++;
        actionsToday = 0;
    
        System.out.println("Night falls. You survived Day " + (currentDay - 1) + ". A new day begins.");
        System.out.println("📆 Day " + currentDay);
    
        // === RESCUE CHECK === //
        if (currentDay >= RESCUE_DAY) {
            System.out.println("You hear a distant rumble... a boat appears on the horizon!");
            System.out.println("You wave frantically, and they spot you. You're rescued!");
            System.out.println("🏆 YOU WIN! You survived " + currentDay + " days.");
            System.exit(0);
        }
        // === DAILY PLAYER STATUS CHANGES ===
        Player globalPlayer = Player.getInstance();
        globalPlayer.increaseHunger(10);
        globalPlayer.increaseThirst(10);
        globalPlayer.heal(5); // Recover small amount of health overnight

        // === RESET LOCATIONS THAT NEED DAILY RESET ===
        // South Shore supplies
        if (southShoreInstance != null) {
            southShoreInstance.newDay();
        }
        if (waterfallInstance != null) {
            waterfallInstance.newDay();
        }
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
    public boolean fight() {
        Random rand = new Random();
        int chance = rand.nextInt(100) + 1;
        incrementActions(); //moved this up (it was after the else) bc it said it was unreachable

        if (opponent) {
            System.out.println("You engage in a fight...");
            if (chance <= luckPoints) {
                System.out.println("You win the fight!");
                adjustLuck(5);
                return true; 
            } else {
                System.out.println("You lost the fight...idiot");
                adjustLuck(-30);
                return false;
            }
        } else {
            System.out.println("Who are you fighting? There is no threat here.");
            return false;
        }
            
    }
    /** Exploring */
    public void lookAround() {
        System.out.println("You look around: " + description);
        incrementActions();
    }

    // ====== LUCK SYSTEM ==== //
    public static void adjustLuck(int change) {
        if (luckPoints >= 100) {
            System.out.println("You hear a distant rumble... a boat appears on the horizon!");
            System.out.println("You wave frantically, and they spot you. You're rescued!");
            System.out.println("🏆 YOU WIN! You survived " + currentDay + " days.");
            System.exit(0);
        }
        
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