import java.util.HashMap;

public abstract class Island implements IslandRequirements {
    protected String name;
    protected String description;

    protected static int luckPoints = 50; // example starting value
    protected static HashMap<String, Integer> inventory = new HashMap<>();
    
    protected static int currentDay = 1;
    protected static int actionsToday = 0;
    protected static final int ACTIONS_PER_DAY = 5;

    public Island(String name, String description) {
        this.name = name;
        this.description = description;
        if (inventory.isEmpty()) {
            inventory.put("rock", 0);
            inventory.put("stick", 0);
            inventory.put("fish", 0);
            inventory.put("supplies", 0);
            inventory.put("water", 0);
        }
    }

    public abstract void describe();

    public static void addItem(String item) {
        inventory.put(item, inventory.getOrDefault(item, 0) + 1);
    }

    public static int getItemCount(String item) {
        return inventory.getOrDefault(item, 0);
    }

    public static void adjustLuck(int change) {
        luckPoints += change;
        System.out.println("Luck is now: " + luckPoints);
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
        System.out.println("\n🌒 Night falls. You survived Day " + (currentDay - 1) + ". A new day begins.");
        System.out.println("📆 Day " + currentDay);
        // Reset daily limits like supply collection here, if needed
        SouthShore.resetDailySupplies();
    }

    public static int getCurrentDay() {
        return currentDay;
    }
}
