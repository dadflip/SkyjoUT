package game.cards;

import java.util.ArrayList;
import player.Player;

public abstract class Deck extends Cards implements CardsGeneralActions{

    ArrayList<Player> playerlist = player.AllPlayers.getAllPlayers();
    private String[] names = {"FX","F","E","D","C","B","A","REVIS","TIRED","GENIUS","CLEAR","MALUS","JOKER"};
    protected final int SIZEOFDECK;
    protected ArrayList<Cards> deck;
    

    public Deck() {
        // Constructeur de la classe Deck.
        // Initialise le deck avec des cartes cachées et des valeurs inférieures à LOWERVAL.
        super(LOWERVAL);
        
        // Initialise le nombre total de types de cartes disponibles pour ce deck.
        Cards.NUMTYPECARDS = this.names.length;
        
        // Calcule et définit la taille maximale du deck.
        SIZEOFDECK = 10 * this.names.length;
        
        // Crée une nouvelle ArrayList pour stocker les cartes dans le deck.
        this.deck = new ArrayList<Cards>();
        
        // Ajoute 10 cartes de chaque type de carte au deck.
        for (int i = Cards.LOWERVAL; i < Cards.NUMTYPECARDS + Cards.LOWERVAL; i++) {
            for(int j = 0; j < 10; j++){
                Cards c = new Cards(i);
                c.setCardName(names[i - Cards.LOWERVAL]);
                deck.add(c); // Ajoute la carte au deck.
            }
        }
    }
    
    public void distributeCard() {
        // Distribue 12 cartes à chaque joueur dans playerlist.
        for(Player player: playerlist){
            for(int i = 0; i<12; i++){
                int rand = (int) (Math.random() * (deck.size())); // Sélectionne une carte aléatoire du deck.
                player.addToHand(deck.get(rand)); // Ajoute la carte sélectionnée à la main du joueur.
                deck.remove(rand); // Supprime la carte sélectionnée du deck afin qu'elle ne puisse pas être distribuée à nouveau.
            }
        }
    }

    public String[] getDefaultCardsNameAtIndex(int [] i){
        String [] n = new String[i.length];
        int x = 0;
        for (int a : i){
            n[x] = this.names[a];
            x++;
        }
        return n;
    }
    
    protected abstract Cards pickCard();
    protected abstract void putCard(Cards card);
    
}
