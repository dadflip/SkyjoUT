package ui.window;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

import game.Game;
import game.MainWindow;
import ui.window.frame.SettingsFrame;
import ui.dialog.Dialog;

public class StartFrame {

    public static void startgame() {
        // Créer la fenêtre principale
        JFrame frame = new JFrame("Menu de démarrage");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(300, 300);

        // Créer un panneau pour les boutons
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 10, 10));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        buttonPanel.setBackground(Color.WHITE);

        // Créer les boutons et les ajouter au panneau
        JButton jouerButton = new JButton("Jouer");
        jouerButton.setFont(new Font("Arial", Font.BOLD, 16));
        jouerButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        jouerButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                frame.dispose();
                // Action à exécuter lorsque le bouton "Jouer" est cliqué
                System.out.println("Le bouton Jouer a été cliqué !");
                Dialog.askPlayers();
                new Game();
                new MainWindow();
            }
        });
        jouerButton.setRolloverEnabled(true);
        buttonPanel.add(jouerButton);

        JButton sauvegardeButton = new JButton("Sauvegarde");
        sauvegardeButton.setFont(new Font("Arial", Font.BOLD, 16));
        sauvegardeButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        sauvegardeButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action à exécuter lorsque le bouton "Sauvegarde" est cliqué
                System.out.println("Le bouton Sauvegarde a été cliqué !");
            }
        });
        sauvegardeButton.setRolloverEnabled(true);
        buttonPanel.add(sauvegardeButton);

        JButton parametresButton = new JButton("Paramètres");
        parametresButton.setFont(new Font("Arial", Font.BOLD, 16));
        parametresButton.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        parametresButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                // Action à exécuter lorsque le bouton "Paramètres" est cliqué
                System.out.println("Le bouton Paramètres a été cliqué !");
                new SettingsFrame();
            }
        });
        parametresButton.setRolloverEnabled(true);
        buttonPanel.add(parametresButton);

        // Ajouter le panneau de boutons à la fenêtre principale
        frame.getContentPane().add(buttonPanel, BorderLayout.CENTER);

        // Afficher la fenêtre principale
        frame.setVisible(true);
    }

}
