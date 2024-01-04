package game.cards;

public class Cards {
    // Définit le nombre de types différents de cartes dans la classe.
    protected static int NUMTYPECARDS;
    
    // Définit la valeur minimale qu'une carte peut avoir.
    protected static final int LOWERVAL = -6;
  
    private int value; // La valeur de la carte.

    // L'état de la carte (visible ou caché).
    private int state;
    public static final int STATE_HIDDEN = 0;
    public static final int STATE_VISIBLE = 1;

    private String name; // Nom de la carte.

    public Cards(int value) {
        // Constructeur Cards qui initialise la valeur et l'état de la carte.
        setCardValue(value);
    }

    public void setCardName(String name) {
        // Modifie le nom de la carte.
        this.name = name;
    }

    public String getCardName() {
        // Renvoie le nom de la carte.
        return this.name;
    }

    public void setCardState(int state) {
        // Modifie l'état de la carte.
        this.state = state;
    }

    public int getCardState() {
        // Renvoie l'état de la carte.
        return this.state;
    }

    private void setCardValue(int value) {
        // Modifie la valeur de la carte.
        this.value = value;
    }

    public int getCardValue() {
        // Renvoie la valeur de la carte.
        return this.value;
    }
}
