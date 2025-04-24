import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Island start = new NorthShore();
        Player player = new Player("Explorer", start);

        System.out.println("🌴 Welcome to the Island Survival Game 🌴");
        start.describe();

        while (true) {
            System.out.print("\n> ");
            String input = scanner.nextLine().trim().toLowerCase();


            //can we add a case to allow for collecting multiple items at once? like 'collect 3 rocks'
            switch (input) {
                case "go north" -> player.moveTo(player.getLocation().moveNorth());
                case "go south" -> player.moveTo(player.getLocation().moveSouth());
                case "go east"  -> player.moveTo(player.getLocation().moveEast());
                case "go west"  -> player.moveTo(player.getLocation().moveWest());
                case "inventory" -> Island.printInventory();
                case "stats" -> player.displayStats();
                case "drink" -> player.drink();
                case "eat" -> player.eat();
                case "collect rock" -> {
                    if (player.getLocation() instanceof NorthShore ns) ns.collectRock();
                    else if (player.getLocation() instanceof SouthShore ss) ss.collectRock();
                    else if (player.getLocation() instanceof  Mountain) mm.collectRock();
                }
                case "collect stick" -> {
                    if (player.getLocation() instanceof NorthShore ns) ns.collectStick();
                    else if (player.getLocation() instanceof SouthShore ss) ss.collectStick();
                }
                case "collect water" -> {
                    if (player.getLocation() instanceof NorthShore ns) ns.drinkFromPool();
                    else if (player.getLocation() instanceof SouthShore ss) ss.collectWater();
                }
                case "collect supplies" -> {
                    if (player.getLocation() instanceof SouthShore ss) ss.collectSupplies();
                    else System.out.println("There's nothing to collect here.");
                }
                case "fish" -> {
                    if (player.getLocation() instanceof NorthShore ns) ns.fish();
                    else System.out.println("You can't fish here.");
                }
                case "build shelter" -> {
                    if (player.getLocation() instanceof NorthShore ns) ns.buildShelter();
                    else if (player.getLocation() instanceof SouthShore ss) ss.buildShelter();
                    else System.out.println("This isn't a place you can build.");
                }
                case "help" -> System.out.println("""
                        Commands:
                        go north/south/east/west
                        collect rock/stick/water/supplies/etc
                        fish
                        build shelter
                        drink, eat
                        inventory, stats, help
                        """);
                case "quit" -> {
                    System.out.println("Thanks for playing!");
                    return;
                }
                default -> System.out.println("Unknown command. Type 'help' for options.");
            }
        }
    }
    
}
