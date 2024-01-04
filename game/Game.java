package game;

import game.cards.Cards;
import game.cards.DeckPile;
import game.cards.DiscardPile;
import player.AllPlayers;
import player.Player;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Collectors;

import javax.swing.JOptionPane;

public class Game {
    private static int turncmpt = 1;

    static DeckPile dck = new DeckPile();
    static DiscardPile dsc;

    static Player currentPlayer;

    public Game() {
        initGame();
        // play();
    }

    public void initBoard() {
        dck.getNameOfCardsInDeckPile();
        System.out.println("size= " + dck.getDeckPile().size());
        dck.shuffleCards();
        dck.distributeCard();
        dsc = new DiscardPile(dck.pickCard());
        dck.getNameOfCardsInDeckPile();
        System.out.println("size= " + dck.getDeckPile().size());
        dsc.getNameOfCardsInDiscardPile();
        System.out.println("size= " + dsc.getDiscardPile().size());
    }

    private void initGame() {
        this.initBoard();
        System.out.println("--------------------------");
        currentPlayer = AllPlayers.allplayers.get(0);
    }

    public static void play() {
        System.out.println("currentp:" + currentPlayer.getName());
        System.out.println("______________PLAY________________");
        checkSetofThree();
        // Game over
        if (isEndGame()) {
            System.out.println("*** Game over! ***");

            calculateScoreAtTheEnd();

            for (int i = 0; i < AllPlayers.getPlayerNumber(); i++) {
                System.out.println(AllPlayers.getPlayerAtIndex(i).getName() + " score: "
                        + AllPlayers.getPlayerAtIndex(i).getScore());
            }

            // Trier les joueurs par score décroissant
            java.util.List<Player> sortedPlayers = AllPlayers.getAllPlayers().stream()
                    .sorted(Comparator.comparingInt(Player::getScore).reversed())
                    .collect(Collectors.toList());

            // Enregistrer le meilleur joueur dans un fichier texte
            if (!sortedPlayers.isEmpty()) {
                Player bestPlayer = sortedPlayers.get(0);
                try (PrintWriter writer = new PrintWriter("best_player.txt")) {
                    writer.println("Best player: " + bestPlayer.getName() + ", score: " + bestPlayer.getScore());
                } catch (FileNotFoundException e) {
                    System.err.println("Could not save best player to file: " + e.getMessage());
                }
                String message = "Le gagnant est " + bestPlayer.getName() + " avec un score de " + bestPlayer.getScore()
                        + " points!";
                JOptionPane.showMessageDialog(null, message, "Fin du jeu", JOptionPane.INFORMATION_MESSAGE);
            }

            // Arrêter le programme avec un code d'état 0 pour indiquer une fin normale
            System.exit(0);
        }

    }

    private static boolean isEndGame() {
        for (Player p : AllPlayers.allplayers) {
            if (p.getHand().isEmpty() || dck.getDeckPile().size() == 0 || p.isAllCardsVisible()) {
                return true;
            }
        }
        return false;
    }

    public static void nextTurn() {
        int nextPlayerIndex = (AllPlayers.allplayers.indexOf(currentPlayer) + 1) % AllPlayers.allplayers.size();
        System.out.println("__verif__");
        currentPlayer.getNameOfCardsInPlayerHand();
        currentPlayer.getStateOfCardsInPlayerHand();
        System.out.println("__next__");
        System.out.println("--> player:" + nextPlayerIndex);
        setCurrentPlayer(AllPlayers.allplayers.get(nextPlayerIndex));
        dsc.getNameOfCardsInDiscardPile();
        if (nextPlayerIndex == 0)
            turncmpt++;
    }

    public static int getTurnCmpt() {
        return turncmpt;
    }

    public static Player getCurrentPlayer() {
        return currentPlayer;
    }

    public static void setCurrentPlayer(Player p) {
        currentPlayer = p;
    }

    public Cards discardPileDraw() {
        return dsc.pickCard();
    }

    public static Cards deckPileDraw() {
        return dck.pickCard();
    }

    public void discard() {
        currentPlayer.addToHand(this.discardPileDraw());
    }

    public static void draw() {
        Cards lastcardselected = currentPlayer.getSelectedCards().get(currentPlayer.getSelectedCards().size() - 1);
        System.out.println("--" + lastcardselected);
        int indexinplayerhand = currentPlayer.getHand().indexOf(lastcardselected);
        Cards cd = deckPileDraw();
        System.out.println("card drown:" + cd.getCardName());
        calculateScoreInGame(lastcardselected);
        dsc.StackFromHand(currentPlayer, lastcardselected);
        currentPlayer.addToHand(cd, indexinplayerhand);
    }

    public static void swap(Cards card) {
        int indexinplayerhand = currentPlayer.getHand().indexOf(card);
        System.out.println("i= " + indexinplayerhand);

        do {

            Cards discardedCard = card;
            Cards pickedcard = dsc.pickCard();
            pickedcard.setCardState(Cards.STATE_VISIBLE);
            currentPlayer.addToHand(pickedcard, indexinplayerhand);
            calculateScoreInGame(card);
            dsc.StackFromHand(currentPlayer, discardedCard);
            break;

        } while (true);
        

    }

    private static void calculateScoreInGame(Cards card) {

        switch (card.getCardName()) {
            case "FX":
                getCurrentPlayer().addPoints(0);
                break;
            case "F":
                getCurrentPlayer().addPoints(1);
                break;
            case "E":
                getCurrentPlayer().addPoints(2);
                break;
            case "D":
                getCurrentPlayer().addPoints(3);
                break;
            case "C":
                getCurrentPlayer().addPoints(4);
                break;
            case "B":
                getCurrentPlayer().addPoints(5);
                break;
            case "A":
                getCurrentPlayer().addPoints(6);
                break;
            case "REVIS":
                getCurrentPlayer().addPoints(7);
                break;
            case "GENIUS":
                getCurrentPlayer().addPoints(12);
                break;
            case "JOKER":
                getCurrentPlayer().addPoints(-5);
                break;
            case "CLEAR":
                getCurrentPlayer().addPoints(-getCurrentPlayer().getScore() / 2);
                break;
            case "MALUS":
                getCurrentPlayer().addPoints(-12);
                break;
            case "TIRED":
                getCurrentPlayer().addPoints(-10);
                break;
            default:
                getCurrentPlayer().addPoints(0);
                break;
        }
    }

    private static void calculateScoreAtTheEnd() {
        for (Player p : AllPlayers.getAllPlayers()) {
            for (Cards c : p.getHand()) {
                switch (c.getCardName()) {
                    case "FX":
                        p.addPoints(0);
                        break;
                    case "F":
                        p.addPoints(1);
                        break;
                    case "E":
                        p.addPoints(2);
                        break;
                    case "D":
                        p.addPoints(3);
                        break;
                    case "C":
                        p.addPoints(4);
                        break;
                    case "B":
                        p.addPoints(5);
                        break;
                    case "A":
                        p.addPoints(6);
                        break;
                    case "REVIS":
                        p.addPoints(7);
                        break;
                    case "GENIUS":
                        p.addPoints(12);
                        break;
                    case "JOKER":
                        p.addPoints(-5);
                        break;
                    case "CLEAR":
                        p.addPoints(-p.getScore() / 2);
                        break;
                    case "MALUS":
                        p.addPoints(-12);
                        break;
                    case "TIRED":
                        p.addPoints(-10);
                        break;
                    default:
                        p.addPoints(0);
                        break;
                }
            }
        }
    }

    public static void checkSetofThree() {
        ArrayList<ArrayList<Integer>> setline = currentPlayer.findSetOfFourInLine();
        ArrayList<ArrayList<Integer>> setcolumn = currentPlayer.findSetOfThreeInColumn();
        int score = currentPlayer.getScore();

        if (!setline.isEmpty()) { // If a set of four cards in line is found
            for (ArrayList<Integer> indexes : setline) {
                for (Integer index : indexes) {
                    Cards card = currentPlayer.getHand().get(index);
                    currentPlayer.removeFromHand(card);
                    dsc.putCard(card);
                }
            }
            System.out.println("Set found! Score: " + score);
        } else if (!setcolumn.isEmpty()) { // If a set of three cards in column is found
            for (ArrayList<Integer> indexes : setcolumn) {
                for (Integer index : indexes) {
                    Cards card = currentPlayer.getHand().get(index);
                    currentPlayer.removeFromHand(card);
                    dsc.putCard(card);
                }
            }

            System.out.println("Set found! Score: " + score);
        } else { // If no sets are found
            System.out.println("no set of card");
        }

        // Update player score and check for end of game condition
        if (currentPlayer.getHand().isEmpty()) {
            throw new RuntimeException("Deck is empty. End of game.");
        }
    }
}