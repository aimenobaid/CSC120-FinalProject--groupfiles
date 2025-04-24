import java.util.HashMap;
import java.util.Random;

public abstract class Island implements IslandRequirements {
    protected String name;
    protected String description;

    protected static int luckPoints = 50; // example starting value
    protected static HashMap<String, Integer> inventory = new HashMap<>();
    
    protected static int currentDay = 1;
    protected static int actionsToday = 0;
    protected static final int ACTIONS_PER_DAY = 20;

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

    public abstract void describe(); //what does abstract actually mean?

    public abstract void collectItem(String item);

    //should we overload collectItem() to allow for collecting multiple items at once???
    

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

    // shelter building (default version — can be overridden, to not allow building in a certain place eg.)
    public void buildShelter() {
        //should we put an if statement here to check bool shelterBuilt? (that's how you implemented it originally in NS)
        //a genuine question, not necessarily a suggestion
        if (getItemCount("rock") >= 3 && getItemCount("stick") >= 3) {
            inventory.put("rock", inventory.get("rock") - 3);
            inventory.put("stick", inventory.get("stick") - 3);
            System.out.println("You built a shelter using 3 rocks and 3 sticks.");
            incrementActions();
        } else {
            System.out.println("You need 3 rocks and 3 sticks to build a shelter.");
        }
    }

    // To be overridden by child classes that track shelter status? I mostly just needed this for rest, maybe it could be done a diff way?
    protected boolean hasShelter() {
        return false;
    }

    // Fire building with multiple options
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

    public void lookAround() {
        System.out.println("You look around: " + description);
        incrementActions();
    }

    public static void adjustLuck(int change) {
        luckPoints += change;
        System.out.println("Luck is now: " + luckPoints);
    }

    public static int getItemCount(String item) {
        return inventory.getOrDefault(item, 0);
    }

    public static void printInventory() {
        System.out.println("Current Inventory: " + inventory);
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

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

    public void rest() {
        if (hasShelter()) {
            System.out.println("You rest inside your shelter. It's safe and quiet. You feel restored.");
            adjustLuck(10);
            advanceDay();
        } else {
            System.out.println("You can't rest without a shelter. That's stupid.");
        }
    }
    
}
//just added stuff based on what we talked about and anything else I could think of, if anything is annoying to implement lmk.
//looks good to me! do you think it would be useful to have a moveItem() method? 
//I wrote one in MtnCave in order to move the rock to enter the cave but thought it might be universally useful. tbd once we build out all the locs i guess