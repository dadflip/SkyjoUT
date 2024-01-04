package game.cards;

import player.Player;

/**
 * CardsGeneralActions
 */
public interface CardsGeneralActions {
    
    // Mélange le paquet de cartes.
    public void shuffleCards();
    
    // Renvoie la carte au sommet du paquet de cartes.
    public Cards takeTopCard();

    // Déplace une carte de la main du joueur vers le dessus du paquet.
    public void StackFromHand(Player player, Cards card);
}
