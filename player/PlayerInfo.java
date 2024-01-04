package player;

import java.util.ArrayList;
import game.cards.Cards;

public class PlayerInfo {
    private ArrayList<Cards> selectedcards;
    private ArrayList<Cards> hand;
    private int score;

    public PlayerInfo(int score){
        this.hand = new ArrayList<>();
        this.selectedcards = new ArrayList<>();
        this.score = score;
    }

    public boolean isAllCardsVisible(){
        int cmpt=0;
        for(Cards C : hand){
            if(C.getCardState() == Cards.STATE_VISIBLE){
                cmpt++;
            }
        }
        if(cmpt == hand.size()){
            return true;
        }
        return false;
    }

    public void setHand(ArrayList<Cards> hand){
        this.hand = hand;
    }

    public void addToHand(Cards elem){
        this.hand.add(elem);
    }

    public void addToHand(Cards elem, int index){
        this.hand.add(index,elem);
    }

    public void removeFromHand(Cards elem){
        this.hand.remove(elem);
    }

    public void removeFromHand(Cards elem, int index){
        this.hand.remove(index);
    }

    public ArrayList<Cards> getHand(){
        return this.hand;
    }

    public void setScore(int s){
        this.score = s;
    }

    public void addPoints(int pts){
        this.score += pts;
    }
    
    public int getScore(){
        return this.score;
    }

    public void addToSelectedCards(Cards elem){
        this.selectedcards.add(elem);
    }

    public ArrayList<Cards> getSelectedCards(){
        return this.selectedcards;
    }

    public void clearSelectedCards(){
        this.selectedcards.clear();
        System.out.println("selected card clear --------------------");
    }

    public void getNameOfCardsInSelectedCards(){
        ArrayList<String> nameofcardsinselected = new ArrayList<>();
        for(Cards C : this.selectedcards){
            nameofcardsinselected.add(C.getCardName());
        }
        System.out.println("selected cards = " + nameofcardsinselected);
    }

    public void getNameOfCardsInPlayerHand(){
        ArrayList<String> nameofcardsinhand = new ArrayList<>();
        for(Cards C : this.hand){
            nameofcardsinhand.add(C.getCardName());
        }
        System.out.println("player hand= " + nameofcardsinhand);
    }

    public void getStateOfCardsInPlayerHand(){
        ArrayList<Integer> stateofcardsinhand = new ArrayList<>();
        for(Cards C : this.hand){
            stateofcardsinhand.add(C.getCardState());
        }
        System.out.println("player hand [state]= " + stateofcardsinhand);
    }

    public ArrayList<ArrayList<Integer>> findSetOfFourInLine() {
        ArrayList<ArrayList<Integer>> setsOfFour = new ArrayList<>();
    
        // Check for sets of four in each row
        for (int i = 0; i < this.hand.size() - 3; i += 4) {
            String cardName = this.hand.get(i).getCardName();
            if (cardName.equals(this.hand.get(i+1).getCardName())
                    && cardName.equals(this.hand.get(i+2).getCardName())
                    && cardName.equals(this.hand.get(i+3).getCardName())) {
                ArrayList<Integer> setIndexes = new ArrayList<>();
                setIndexes.add(i);
                setIndexes.add(i+1);
                setIndexes.add(i+2);
                setIndexes.add(i+3);
                setsOfFour.add(setIndexes);
            }
        }
    
        return setsOfFour;
    }
    

    public ArrayList<ArrayList<Integer>> findSetOfThreeInColumn() {
        ArrayList<ArrayList<Integer>> setsOfThree = new ArrayList<>();
    
        // Check for sets of three in each column
        for (int i = 0; i < this.hand.size() / 4; i++) {
            String cardName = this.hand.get(i).getCardName();
            if (cardName.equals(this.hand.get(i+4).getCardName())
                    && cardName.equals(this.hand.get(i+8).getCardName())) {
                ArrayList<Integer> setIndexes = new ArrayList<>();
                setIndexes.add(i);
                setIndexes.add(i+4);
                setIndexes.add(i+8);
                setsOfThree.add(setIndexes);
            }
        }
    
        return setsOfThree;
    }

    
}
