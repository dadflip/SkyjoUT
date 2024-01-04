package game.cards;

import java.util.ArrayList;
import java.util.Collections;

import player.Player;

public class DiscardPile extends Deck{

    // Crée une nouvelle ArrayList discardpile vide.
    protected ArrayList<Cards> discardpile = new ArrayList<>();

    public DiscardPile(Cards firstcard){
        // Initialise discardpile en ajoutant une première carte.
        this.discardpile.add(firstcard);
    }

    public void getNameOfCardsInDiscardPile(){
        ArrayList<String> nameofcardsindiscardpile = new ArrayList<>();
        for(Cards C : this.discardpile){
            nameofcardsindiscardpile.add(C.getCardName());
        }
        System.out.println("discard = " + nameofcardsindiscardpile);
    }

    @Override
    public Cards pickCard() {
        // Enlève et renvoie la carte au sommet de la pile de défausse (la dernière).
        return this.discardpile.remove(discardpile.size() - 1);
    }

    @Override
    public void putCard(Cards card) {
        // Ajoute une carte à la pile de défausse.
        this.discardpile.add(card);
    }

    public ArrayList<Cards> getDiscardPile(){
        // Renvoie la pile de défausse.
        return this.discardpile;
    }

    @Override
    public void shuffleCards() {
        // Mélange la pile de cartes discardpile.
        Collections.shuffle(discardpile);
    }

    @Override
    public Cards takeTopCard() {
        // Renvoie la carte au sommet de discardpile.
        return this.discardpile.get(discardpile.size() - 1);
    }

    @Override
    public void StackFromHand(Player player, Cards card) {
        // Retire la carte de la main du joueur et l'ajoute à discardpile.
        player.removeFromHand(card);
        discardpile.add(card);
    }

    public boolean isValidAdd(Cards cardToSwap) {
        int sizeofunswappablecards = 1;
        String[] unswappableCards = getDefaultCardsNameAtIndex(new int[]{12});
        if (cardToSwap.getCardName().equals(unswappableCards[sizeofunswappablecards - 1])) return false;
        return true;
    }
      
}
