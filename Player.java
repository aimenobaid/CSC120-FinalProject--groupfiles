public class Player {
    private String name;
    private int health;
    private int thirst;
    private int hunger;
    private Island currentLocation;
    private static Player instance;

    /**
     * Constructor for the Player class.
     * Initializes the player with a name, health, thirst, hunger, and starting location.
     * @param name
     * @param startingLocation
     */
    public Player(String name, Island startingLocation) {
        this.name = name;
        this.health = 100;
        this.thirst = 50;
        this.hunger = 50;
        this.currentLocation = startingLocation;
        instance = this;
    }

    /**
     * Changes the player's health by a specified amount.
     * Positive values increase health, negative values decrease it.
     * @param x
     */
    public void changeHealth(int x){
        health += x;
    }

    /**
     * Gets the player's name.
     * @return name of player
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the instance of the Player class.
     * Allows global access to the player object.
     * @return instance of Player
     */
    public static Player getInstance() {
        return instance; 
    }
    
    /**
     * Increases the player's hunger by a specified amount.
     * Actually decreases hunger, so hunger = 0 is when the player dies, but takes a positive number as input if player needs to eat.
     * @param amount
     */
    public void increaseHunger(int amount) {
        hunger -= amount;
        if (hunger < 0) hunger = 0;
    }
    
    /**
     * Increases the player's thirst by a specified amount.
     * Actually decreases thirst, so thirst = 0 is when the player dies, but takes a positive number as input if player needs to drink.
     * @param amount
     */
    public void increaseThirst(int amount) {
        thirst -= amount;
        if (thirst < 0) thirst = 0;
    }
    
    /**
     * Increases the player's health by a specified amount. Maximum health is 100.
     * @param amount
     */
    public void heal(int amount) {
        health = Math.min(100, health + amount);
    }

    /**
     * Moves the player to a new location and describes that location.
     * @param newLocation Island child class to move to.
     */
    public void moveTo(Island newLocation) {
        this.currentLocation = newLocation;
        newLocation.describe();
    }

    /**
     * Gets the current location of the player.
     * @return Island child class where the player is located.
     */
    public Island getLocation() {
        return currentLocation;
    }

    /**
     * Displays the player's current stats: health, thirst, and hunger.
     */
    public void displayStats() {
        System.out.println("Health: " + health + " | Thirst: " + thirst + " | Hunger: " + hunger);
    }

    /**
     * Allows the player to drink water if they have it in their inventory.
     * Removes one water item from inventory and decreases thirst by 20 if they drink.
     * If they don't have water, prints a message indicating that they can't drink.
     */
    public void drink() {
        if (Island.getItemCount("water") > 0) {
            Island.inventory.put("water", Island.getItemCount("water") - 1);
            thirst = Math.min(thirst - 20, 100);
            System.out.println("You drank some water.");
            Island.incrementActions();
        } else {
            System.out.println("You don't have any water to drink.");
        }
    }
    
    /**
     * Allows the player to eat food if they have it in their inventory.
     * Removes one food item from inventory and decreases hunger by a certain amount based on the food type.
     * If they don't have food, prints a message indicating that they can't eat.
     */
    public void eat() {
        if (Island.getItemCount("fish") > 0) {
            Island.inventory.put("fish", Island.getItemCount("fish") - 1);
            hunger = Math.min(hunger - 20, 100);
            System.out.println("You ate a fish.");
        } else if (Island.getItemCount("bread") > 0) {
            Island.inventory.put("bread", Island.getItemCount("bread") - 1);
            hunger = Math.min(hunger - 15, 100);
            System.out.println("You ate a piece of bread.");
        } else if (Island.getItemCount("berries") > 0) {
            Island.inventory.put("berries", Island.getItemCount("berries") - 1);
            hunger = Math.min(hunger - 10, 100);
            System.out.println("You snacked on some berries.");
        } else {
            System.out.println("You don't have any food.");
            return;
        }
        Island.incrementActions();
    }

    /**
     * Allows the player to rest in a shelter if they have one.
     * Prints a message based on the location of the shelter. Increases thirst, hunger, and health,and advances a day.
     * If they don't have a shelter, prints a message indicating that they can't rest.
     */
    public void rest() {
        if (currentLocation.hasShelter()) {
            switch (currentLocation.getName()) {
                case "North Shore" -> System.out.println("You rest in your seaside shelter, lulled to sleep by the sound of waves.");
                case "Light Forest" -> System.out.println("You rest peacefully among the tall trees, hidden from danger.");
                case "Mountain" -> System.out.println("You brace against the cold wind in your mountain shelter, but survive the night.");
                case "Dark Forest" -> System.out.println("You rest uneasily, alert to every sound in the darkness... but you make it through.");
                default -> System.out.println("You rest in your shelter. The night passes safely.");
            }
            thirst = Math.min(thirst + 30, 100);
            hunger = Math.min(hunger + 30, 100);
            health = Math.min(health + 20, 100);
            Island.advanceDay();
        } else {
            System.out.println("You can't rest here. You need to find or build a shelter first.");
        }
    }

    /**
     * Checks if the player has died based on their health, thirst, and hunger levels.
     * If hunger or thirst reaches 100, the player dies. If health reaches 0, the player dies.
     * Prints a message indicating the cause of death.
     * @return ture if the player has died, false otherwise.
     */
    public boolean die() {
        if (health <= 0) {
            System.out.println("Your injuries were too much. You collapse and die...");
            return true;
        } 
        if (hunger >= 100) {
            System.out.println("You starved to death. Survival on this island was brutal. RIP 🪦");
            return true;
        }
        if (thirst >= 100) {
            System.out.println("You died of thirst. You should have searched for water sooner. RIP 🪦");
            return true;
        }
        return false;
    }
}
