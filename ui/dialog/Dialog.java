package ui.dialog;

import java.util.ArrayList;
import javax.swing.JOptionPane;
import player.*;

public class Dialog {

    protected static ArrayList<Player> allp = new ArrayList<>();
    private static int numPlayers = 0;

    public static void askPlayers() {
        
        boolean validInput = false;

        while (!validInput) {
            try {

                String input = JOptionPane.showInputDialog(null, "Combien de joueurs participent à la partie?", "Skyjo", JOptionPane.QUESTION_MESSAGE);

                if (input == null) {
                    System.exit(0);
                }

                numPlayers = Integer.parseInt(input);

                if (numPlayers < 2 || numPlayers > 8) {
                    throw new IllegalArgumentException("Le nombre de joueurs doit être compris entre 2 et 8.");
                }

                //allp.setPlayerNumber(numPlayers);

                validInput = true;

            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Veuillez entrer un nombre entier valide.", "Erreur", JOptionPane.ERROR_MESSAGE);

            } catch (IllegalArgumentException e) {
                JOptionPane.showMessageDialog(null, e.getMessage(), "Erreur", JOptionPane.ERROR_MESSAGE);
            }
        }
          
        ArrayList<String> namesAlreadyEntered = new ArrayList<>();

        for (int i = 0; i < numPlayers; i++) {

            boolean nameIsValid = false;

            while (!nameIsValid) {

                String input = JOptionPane.showInputDialog(null, "Nom du joueur " + (i+1) + " : ", "Skyjo", JOptionPane.QUESTION_MESSAGE);

                if (input == null || input.equals("")) {
                    System.exit(0); 
                }

                if (namesAlreadyEntered.contains(input)) {
                    JOptionPane.showMessageDialog(null, "Un joueur avec ce nom existe déjà, veuillez saisir un autre nom.", "Erreur", JOptionPane.ERROR_MESSAGE);

                } else {
                    allp.add(new Player(input,0));
                    namesAlreadyEntered.add(input);
                    nameIsValid = true;
                }
            }
        }

        new AllPlayers(allp, numPlayers);

        String[] playerNamesArray = new String[numPlayers];
        for (int i = 0; i < numPlayers; i++) {
            playerNamesArray[i] = AllPlayers.allplayers.get(i).getName();
        }

        JOptionPane.showMessageDialog(null, "Les noms des joueurs sont :\n" + String.join(",", playerNamesArray), "Skyjo", JOptionPane.INFORMATION_MESSAGE);

        System.out.println("ok !");
    }

}
