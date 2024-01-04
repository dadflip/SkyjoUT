package player;


public class Player extends PlayerInfo {
    private String name;

    // Parameterized constructor
    public Player(String name, int score) {
        super(score);
        this.name = name;
    }

    // Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
