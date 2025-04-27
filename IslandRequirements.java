
public interface IslandRequirements {
    Island moveNorth();
    Island moveSouth();
    Island moveEast();
    Island moveWest();
    // Common actions across all islands
    void collectItem(String item); // generalized collecting
    void fight(); // luck-based combat
    void buildShelter();
    void buildFire();
    void lookAround();
}
    

