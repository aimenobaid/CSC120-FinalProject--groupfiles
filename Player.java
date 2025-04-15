public class Player {
    private String name;
    private int health;
    private int thirst;
    private int hunger;
    private Island currentLocation;

    public Player(String name, Island startingLocation) {
        this.name = name;
        this.health = 100;
        this.thirst = 50;
        this.hunger = 50;
        this.currentLocation = startingLocation;
    }

    public String getName() {
        return name;
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
            thirst = Math.min(thirst + 20, 100);
            System.out.println("You drank some water.");
        } else {
            System.out.println("You don't have any water to drink.");
        }
    }

    public void eat() {
        if (Island.getItemCount("fish") > 0) {
            hunger = Math.min(hunger + 20, 100);
            System.out.println("You ate a fish.");
        } else {
            System.out.println("You don't have any food.");
        }
    }
    public void rest() {
        if (currentLocation instanceof NorthShore north && north.isShelterBuilt()) {
            System.out.println("You rest in your shelter. You feel well-fed and hydrated.");
            thirst = Math.min(thirst + 30, 100);
            hunger = Math.min(hunger + 30, 100);
            health = Math.min(health + 20, 100);
            Island.advanceDay();
        } else {
            System.out.println("You can't rest here. Find or build a shelter first.");
        }
    }
}
