public class Player {
    private String name;
    private int health;
    private int thirst;
    private int hunger;
    private Island currentLocation;
    private static Player instance;

    public Player(String name, Island startingLocation) {
        this.name = name;
        this.health = 100;
        this.thirst = 50;
        this.hunger = 50;
        this.currentLocation = startingLocation;
        instance = this;
    }

    public void changeHealth(int x){
        health += x;
    }

    public String getName() {
        return name;
    }

    public static Player getInstance() {
        return instance;  // <<< This allows global access to the player
    }
    
    public void increaseHunger(int amount) {
        hunger -= amount;
        if (hunger < 0) hunger = 0;
    }
    
    public void increaseThirst(int amount) {
        thirst -= amount;
        if (thirst < 0) thirst = 0;
    }
    
    public void heal(int amount) {
        health = Math.min(100, health + amount);
    }

    public void moveTo(Island newLocation) {
        this.currentLocation = newLocation;
        newLocation.describe();
    }

    public Island getLocation() {
        return currentLocation;
    }

    public void displayStats() {
        System.out.println("Health: " + health + " | Thirst: " + thirst + " | Hunger: " + hunger);
    }

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
