package game.cards;

import java.util.ArrayList;
import java.util.Collections;

import player.Player;

public class DeckPile extends Deck {

    // Crée une nouvelle ArrayList deckpile à partir de la liste deck.
    protected ArrayList<Cards> deckpile;

    public DeckPile() {
        // Initialise deckpile avec deck, qui est hérité de la classe Deck.
        this.deckpile = deck;
    }

    public void getNameOfCardsInDeckPile(){
        ArrayList<String> nameofcardsindeckpile = new ArrayList<>();
        for(Cards C : this.deckpile){
            nameofcardsindeckpile.add(C.getCardName());
        }
        System.out.println("deck= " + nameofcardsindeckpile);
    }

    @Override
    public Cards pickCard() {
        // Enlève et renvoie la carte au sommet de deckpile (la dernière).
        return this.deckpile.remove(deckpile.size() - 1);
    }

    @Override
    public void putCard(Cards card) {
        // Ajoute une carte à deckpile.
        this.deckpile.add(card);
    }

    public ArrayList<Cards> getDeckPile() {
        // Renvoie la pile de deck.
        return this.deckpile;
    }

    @Override
    public void shuffleCards() {
        // Mélange la pile de cartes deckpile.
        Collections.shuffle(deckpile);
    }

    @Override
    public Cards takeTopCard() {
        // Renvoie la carte au sommet de deckpile.
        return deckpile.get(deckpile.size() - 1);
    }

    @Override
    public void StackFromHand(Player player, Cards card) {
        // Retire la carte de la main du joueur et l'ajoute à deckpile.
        player.removeFromHand(card);
        deckpile.add(card);
    }

}
