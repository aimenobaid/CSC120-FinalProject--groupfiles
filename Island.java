import java.util.HashMap;
import java.util.Random;

public abstract class Island implements IslandRequirements {

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
    public static TigerMonkeyHut tigerMonkeyHutInstance; 

    /**
     * Constructor for Island class
     * Initializes the island with a description and an empty inventory with available items.
     * @param description
     */
    public Island(String description) {
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

    /**
     * Defines exits for movement methods.
     * @param north as the location to the north
     * @param south as the location to the south
     * @param east as the location to the east
     * @param west as the location to the west
     */
    public void setExits(Island north, Island south, Island east, Island west) {
        this.northExit = north;
        this.southExit = south;
        this.eastExit = east;
        this.westExit = west;
    }

    /**
     * Default movement method for north. Prints generic message to move, prints error if no north exit.
     * @return the new island location if it exists, otherwise returns the current island.
     */
    public Island moveNorth() {
        if (northExit != null) {
            System.out.println("You move north.");
            return northExit;
        } else {
            System.out.println("You can't go north from here.");
            return this;
        }
    }

    /**
     * Default movement method for south. Prints generic message to move, prints error if no south exit.
     * @return the new island location if it exists, otherwise returns the current island.
     */
    public Island moveSouth() {
        if (southExit != null) {
            System.out.println("You move south.");
            return southExit;
        } else {
            System.out.println("You can't go south from here.");
            return this;
        }
    }

    /**
     * Default movement method for east. Prints generic message to move, prints error if no east exit.
     * @return the new island location if it exists, otherwise returns the current island.
     */
    public Island moveEast() {
        if (eastExit != null) {
            System.out.println("You move east.");
            return eastExit;
        } else {
            System.out.println("You can't go east from here.");
            return this;
        }
    }

    /**
     * Default movement method for west. Prints generic message to move, prints error if no west exit.
     * @return the new island location if it exists, otherwise returns the current island.
     */
    public Island moveWest() {
        if (westExit != null) {
            System.out.println("You move west.");
            return westExit;
        } else {
            System.out.println("You can't go west from here.");
            return this;
        }
    }

    // ===== Abstract Methods ===== // !!!!!!!!NEED JAVADOCS HELP!!!!!!!
    public abstract void describe(); 
    public abstract void collectItem(String item);
    public abstract void help(); 
    
    // ====== GAME PROGRESSION ====== //
    
    /**
     * Increments the number of actions taken today. If the number of actions reaches the daily limit, it advances to the next day.
     * Also increases hunger and thirst every 5 actions and alerts the player.
     */
    public static void incrementActions() {
        actionsToday++;
        if (actionsToday >= ACTIONS_PER_DAY) {
            advanceDay();
        }

        // =======This is NOT working as intended, idk why or how to fix======== //
        // if(actionsToday%5==0){
        //     Player globalPlayer = Player.getInstance();
        //     globalPlayer.increaseHunger(5);
        //     globalPlayer.increaseThirst(5);
        // }
        // System.out.println("You are getting tired. Your hunger and thirst are increasing.");
    }

    /**
     * Overridden in child classes to reset daily status changes.
     */
    public void newDay() {
    }

    /**
     * Advances the game to the next day, resets actions, and checks for rescue conditions.
     * Increases hunger and thirst, and heals the player slightly by increasing health.
     */
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
        // === DAILY PLAYER STATUS CHANGES === //
        Player globalPlayer = Player.getInstance();
        globalPlayer.increaseHunger(10);
        globalPlayer.increaseThirst(10);
        globalPlayer.heal(5); // Recover small amount of health overnight

        // === RESET LOCATIONS THAT NEED DAILY RESET === //
        if (southShoreInstance != null) {
            southShoreInstance.newDay();
        }
        if (waterfallInstance != null) {
            waterfallInstance.newDay();
        }
        if (tigerMonkeyHutInstance != null) {
            tigerMonkeyHutInstance.newDay();
        }
    }

    /**
     * Returns the current day of the game.
     * @return the current day number
     */
    public static int getCurrentDay() {
        return currentDay;
    }

    // ===== SURVIVAL ===== //

    /**
     * Builds a shelter if the player has enough materials and if the location allows it.
     * If the shelter is already built, it informs the player. Same for if the player doesn't have enough materials.
     */
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
    
    /**
     * Overridden in child classes to allow or disallow shelter building. .
     * @return boolean false, not allowed by default
     */
    protected boolean canBuildShelter() {
        return false; 
    }
    
    protected boolean shelterBuilt = false;  // Centralized shelter tracking

    /**
     * Overridden in child classes to track shelter status. 
     * @return boolean false, by defult, no shelter is built.
     */
    protected boolean hasShelter() {
        return false;
    }

    /** 
     * Allows player to build a fire if they have the required materials.
     * Alerts the player if they don't have enough materials. Increments actions.
     */
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
    
    /** 
     * Allows the player to fight an opponent if one exists. Results are determined by luck points.
     * Increments actions and checks if the player wins or loses the fight.
     * If the player wins, they gain luck points. If they lose, they lose luck points. 
     */
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
    
    /** 
     * Allows the player to look around the island and see its description.
     * Increments actions. 
     */
    public void lookAround() {
        System.out.println("You look around: " + description);
        incrementActions();
    }

    // ====== LUCK SYSTEM ==== //
    /**
     * Adjusts the player's luck points. If luck points reach 100, the player is rescued.
     * @param change the amount to change luck points by
     */
    public static void adjustLuck(int change) {
    //==============THIS ISN'T WORKING, IT DOENS't SAY YOU WIN WHEN YOU GET 100 LUCK POINTS !!! ============//
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
    /**
     * Adds an item to the inventory. If the item already exists, it increments the count.
     * @param item the item to add
     */
    public static int getItemCount(String item) {
        return inventory.getOrDefault(item, 0);
    }
    public static void printInventory() {
        System.out.println("Current Inventory: " + inventory);
    }


    // ===== Getters ===== //
    /**
     * Returns the name of the island.
     * @return the name of the island
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the description of the island.
     * @return description the description of the island
     */
    public String getDescription() {
        return description;
    }
    
}