package game;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;

import game.cards.Cards;
import ui.path.Path;

public class MainWindow extends JFrame {
    private static final long serialVersionUID = 1L;

    private JPanel centerPanel;
    private JPanel playerCardsPanel;
    private JButton[] playerCardButtons;
    private JButton drawButton;
    private JButton swapButton;
    private JLabel nameLabel;
    private JLabel scoreLabel;
    private JLabel turnLabel;
    private JLabel deckLabel;
    private JLabel discardLabel;
    private JLabel rightPanelImageLabel = new JLabel();

    private int lastClickedButtonIndex = -1;
    // private Player currentPlayer;
    // private Game gameProcess = new Game();

    public MainWindow() {
        setTitle("Jeu de cartes");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Ajout de la barre de menu
        setJMenuBar(createMenuBar());

        // Création et ajout des panneaux
        add(createTopPanel(), BorderLayout.NORTH);
        add(createCenterPanel(), BorderLayout.CENTER);
        add(createBottomPanel(), BorderLayout.SOUTH);
        add(createRightPanel(), BorderLayout.EAST);

        // Obtenir les dimensions de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        int width = screenSize.width / 2; // Diviser la largeur par deux
        int height = screenSize.height / 2; // Diviser la hauteur par deux

        setSize(width, height); // Définir la taille de la fenêtre
        setLocationRelativeTo(null);
        setVisible(true);

    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();

        JMenu fileMenu = new JMenu("Fichier");
        JMenuItem exitMenuItem = new JMenuItem("Quitter");
        exitMenuItem.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        fileMenu.add(exitMenuItem);
        menuBar.add(fileMenu);

        return menuBar;
    }

    private JPanel createTopPanel() {
        // Obtention de la taille de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Définition de la taille souhaitée pour le topPanel (1/3 de la largeur de
        // l'écran, hauteur fixe de 30 pixels)
        int topPanelWidth = screenSize.width / 2;
        int topPanelHeight = screenSize.height / 10;

        // Création du topPanel avec la taille définie
        JPanel topPanel = new JPanel();
        topPanel.setPreferredSize(new Dimension(topPanelWidth, topPanelHeight));
        topPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
        topPanel.setBackground(Color.BLACK);

        // Ajouter les images aux labels
        ImageIcon image1deck = new ImageIcon(Path.path + Path.Directory.CARDS.getValue() + Path.nameTemplate
                + "BACK" + Path.Extensions.PNG.getValue());
        ImageIcon image2discard = new ImageIcon(Path.path + Path.Directory.CARDS.getValue() + Path.nameTemplate
                + Game.dsc.takeTopCard().getCardName() + Path.Extensions.PNG.getValue());
        System.out.println("verifpath= " + Path.path + Path.Directory.CARDS.getValue() + Path.nameTemplate
                + Game.dck.takeTopCard().getCardName() + Path.Extensions.PNG.getValue());

        // Redimensionner les images à 5% des dimensions de l'écran
        int width = (int) (screenSize.width * 0.05);
        int height = (int) (screenSize.height * 0.05);
        Image imgDeck = image1deck.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Image imgDiscard = image2discard.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Créer les labels
        JLabel label1 = new JLabel(new ImageIcon(imgDeck));
        JLabel label2 = new JLabel(new ImageIcon(imgDiscard));

        // Utiliser un BoxLayout pour aligner les labels côte à côte
        BoxLayout layout = new BoxLayout(topPanel, BoxLayout.X_AXIS);
        topPanel.setLayout(layout);
        topPanel.add(Box.createHorizontalGlue()); // Ajouter un "glue" pour centrer les labels
        topPanel.add(label1);
        topPanel.add(Box.createHorizontalStrut(10)); // Ajouter un espace entre les labels
        topPanel.add(label2);
        topPanel.add(Box.createHorizontalGlue()); // Ajouter un "glue" pour centrer les labels

        return topPanel;
    }

    private void updateTopPanel() {
        // Charger les images
        ImageIcon icondeck = new ImageIcon(Path.path + Path.Directory.CARDS.getValue() + Path.nameTemplate
                + "BACK" + Path.Extensions.PNG.getValue());
        ImageIcon icondiscard = new ImageIcon(Path.path + Path.Directory.CARDS.getValue() + Path.nameTemplate
                + Game.dsc.takeTopCard().getCardName() + Path.Extensions.PNG.getValue());

        // Obtenir les dimensions de l'écran
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();

        // Redimensionner les images à 5% des dimensions de l'écran
        int width = (int) (screenSize.width * 0.05);
        int height = (int) (screenSize.height * 0.05);
        Image imgDeck = icondeck.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);
        Image imgDiscard = icondiscard.getImage().getScaledInstance(width, height, Image.SCALE_SMOOTH);

        // Créer les labels
        JLabel label1 = new JLabel(new ImageIcon(imgDeck));
        JLabel label2 = new JLabel(new ImageIcon(imgDiscard));

        // Ajouter les labels au topPanel
        JPanel topPanel = (JPanel) getContentPane().getComponent(0); // Récupérer le topPanel depuis le contentPane
        topPanel.removeAll(); // Retirer tous les composants existants
        topPanel.setLayout(new GridLayout(1, 2)); // Définir un layout de 2 colonnes
        topPanel.add(label1); // Ajouter le premier label
        topPanel.add(label2); // Ajouter le deuxième label

        // Rafraîchir le topPanel
        topPanel.revalidate();
        topPanel.repaint();
    }

    private JPanel createCenterPanel() {
        centerPanel = new JPanel(new BorderLayout());
        centerPanel.add(createGameCardsPanel(), BorderLayout.NORTH);
        centerPanel.add(createPlayerCardsPanel(), BorderLayout.CENTER);
        centerPanel.setBackground(Color.WHITE);
        return centerPanel;
    }

    private JPanel createRightPanel() {
        JPanel imagePanel = new JPanel(new FlowLayout());
        // Ajoutez ici des JLabels contenant vos images redimensionnées
        rightPanelImageLabel.setBackground(Color.BLACK);
        return imagePanel;
    }

    private JPanel createGameCardsPanel() {
        JPanel gameCardsPanel = new JPanel(new GridLayout(1, 2));
        gameCardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        gameCardsPanel.setBackground(Color.WHITE);
        deckLabel = new JLabel("Deck: " + Game.dck.getDeckPile().size() + " cartes", SwingConstants.CENTER);
        discardLabel = new JLabel("Défausse: " + Game.dsc.getDiscardPile().size() + " cartes", SwingConstants.CENTER);
        deckLabel.setFont(new Font("Arial", Font.BOLD, 18));
        discardLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gameCardsPanel.add(deckLabel);
        gameCardsPanel.add(discardLabel);
        return gameCardsPanel;
    }

    private void resetButtonColors() {
        for (int i = 0; i < 12; i++) {
            playerCardButtons[i].setBackground(Color.WHITE); // Réinitialiser la couleur de fond du bouton
        }
        lastClickedButtonIndex = -1; // Réinitialiser l'index du dernier bouton cliqué
    }

    private JPanel createPlayerCardsPanel() {
        playerCardsPanel = new JPanel(new GridLayout(3, 4, 10, 10));
        playerCardsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        playerCardsPanel.setBackground(Color.WHITE);

        // Initialisation des boutons représentant les cartes du joueur
        playerCardButtons = new JButton[12];
        Border border = BorderFactory.createLineBorder(Color.BLACK, 4, true);
        for (int i = 0; i < 12; i++) {
            final int buttonIndex = i; // Stocker la valeur de i dans une variable "finale" ou "effectivement finale"
            playerCardButtons[i] = new JButton("");
            Cards card = Game.getCurrentPlayer().getHand().get(i);
            if (card.getCardState() == Cards.STATE_VISIBLE) {
                playerCardButtons[i].addComponentListener((ComponentListener) new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        ImageIcon icon = new ImageIcon(Path.path + Path.Directory.CARDS.getValue() + Path.nameTemplate
                                + Game.currentPlayer.getHand().get(buttonIndex).getCardName()
                                + Path.Extensions.PNG.getValue());
                        Image image = icon.getImage().getScaledInstance(
                                (int) (playerCardButtons[buttonIndex].getWidth() * 0.95),
                                (int) (playerCardButtons[buttonIndex].getHeight() * 0.9),
                                Image.SCALE_SMOOTH);
                        playerCardButtons[buttonIndex].setIcon(new ImageIcon(image));
                    }
                });
            } else if (card.getCardState() == Cards.STATE_HIDDEN) {
                playerCardButtons[i].addComponentListener(new ComponentAdapter() {
                    @Override
                    public void componentResized(ComponentEvent e) {
                        ImageIcon icon = new ImageIcon(Path.path + Path.Directory.CARDS.getValue() + Path.nameTemplate
                                + "BACK" + Path.Extensions.PNG.getValue());
                        Image image = icon.getImage().getScaledInstance(
                                (int) (playerCardButtons[buttonIndex].getWidth() * 0.95),
                                (int) (playerCardButtons[buttonIndex].getHeight() * 0.9),
                                Image.SCALE_SMOOTH);
                        playerCardButtons[buttonIndex].setIcon(new ImageIcon(image));
                    }
                });
            }
            playerCardButtons[i].setBackground(Color.WHITE); // Colorier le bouton en noir
            playerCardButtons[i].setBorder(border);
            playerCardButtons[i].setBorderPainted(true);

            playerCardButtons[i].addMouseListener((MouseListener) new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    if (playerCardButtons[buttonIndex].getBackground() != Color.YELLOW) {
                        playerCardButtons[buttonIndex].setBackground(Color.BLUE); // Couleur de surbrillance
                    }
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    if (playerCardButtons[buttonIndex].getBackground() != Color.YELLOW) {
                        playerCardButtons[buttonIndex].setBackground(null); // Réinitialiser la couleur de fond
                    }
                }
            });

            playerCardButtons[i].addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    Cards card = null;
                    for (int j = 0; j < 12; j++) {
                        if (playerCardButtons[j] == e.getSource()) {
                            card = Game.getCurrentPlayer().getHand().get(j);
                            if (lastClickedButtonIndex != -1) {
                                playerCardButtons[lastClickedButtonIndex].setBackground(Color.WHITE); // Réinitialiser
                                                                                                      // la
                                // couleur de fond du
                                // dernier bouton cliqué
                            }
                            playerCardButtons[j].setBackground(Color.YELLOW); // Colorier le bouton cliqué en jaune
                            lastClickedButtonIndex = j; // Mettre à jour l'index du dernier bouton cliqué
                            break;
                        }
                    }
                    Game.getCurrentPlayer().addToSelectedCards(card);
                    Game.getCurrentPlayer().getNameOfCardsInSelectedCards();
                }
            });
            playerCardsPanel.add(playerCardButtons[i]);
        }

        return playerCardsPanel;
    }

    private void updatePlayerCardsPanel() {
        for (int i = 0; i < 12; i++) {
            JButton button = playerCardButtons[i];
            Cards card = Game.getCurrentPlayer().getHand().get(i);

            if (card.getCardState() == Cards.STATE_VISIBLE) {
                ImageIcon icon = new ImageIcon(Path.path + Path.Directory.CARDS.getValue() + Path.nameTemplate
                        + Game.currentPlayer.getHand().get(i).getCardName() + Path.Extensions.PNG.getValue());
                Image image = icon.getImage().getScaledInstance(
                        (int) (button.getWidth() * 0.95),
                        (int) (button.getHeight() * 0.9),
                        Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(image));
            } else if (card.getCardState() == Cards.STATE_HIDDEN) {
                ImageIcon icon = new ImageIcon(Path.path + Path.Directory.CARDS.getValue() + Path.nameTemplate
                        + "BACK" + Path.Extensions.PNG.getValue());
                Image image = icon.getImage().getScaledInstance(
                        (int) (button.getWidth() * 0.95),
                        (int) (button.getHeight() * 0.9),
                        Image.SCALE_SMOOTH);
                button.setIcon(new ImageIcon(image));
            }

        }
    }

    private JPanel createBottomPanel() {
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.add(createInfoPanel(), BorderLayout.NORTH);
        bottomPanel.add(createActionPanel(), BorderLayout.SOUTH);
        return bottomPanel;
    }

    private JPanel createInfoPanel() {
        JPanel infoPanel = new JPanel(new GridLayout(1, 3));
        nameLabel = new JLabel("Nom: " + Game.getCurrentPlayer().getName(), SwingConstants.CENTER);
        scoreLabel = new JLabel("Score: " + Game.getCurrentPlayer().getScore(), SwingConstants.CENTER);
        turnLabel = new JLabel("Tour: " + Game.getTurnCmpt(), SwingConstants.CENTER);
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        turnLabel.setFont(new Font("Arial", Font.BOLD, 18));
        infoPanel.add(nameLabel);
        infoPanel.add(scoreLabel);
        infoPanel.add(turnLabel);
        return infoPanel;
    }

    public void updateInfoPanel() {
        resetButtonColors();
        System.out.println("verif" + " name= " + Game.getCurrentPlayer().getName() + " score= "
                + Game.getCurrentPlayer().getScore() + " turn= " + Game.getTurnCmpt());
        nameLabel.setText("Nom: " + Game.getCurrentPlayer().getName());
        scoreLabel.setText("Score: " + Game.getCurrentPlayer().getScore());
        turnLabel.setText("Tour: " + Game.getTurnCmpt());
        deckLabel.setText("Deck: " + Game.dck.getDeckPile().size() + " cartes");
        discardLabel.setText("Défausse: " + Game.dsc.getDiscardPile().size() + " cartes");
        nameLabel.setFont(new Font("Arial", Font.BOLD, 18));
        scoreLabel.setFont(new Font("Arial", Font.BOLD, 18));
        turnLabel.setFont(new Font("Arial", Font.BOLD, 18));
        deckLabel.setFont(new Font("Arial", Font.BOLD, 18));
        discardLabel.setFont(new Font("Arial", Font.BOLD, 18));
    }

    private JPanel createActionPanel() {
        JPanel actionPanel = new JPanel(new GridLayout(1, 2, 10, 10));
        drawButton = createDrawButton();
        swapButton = createSwapButton();
        actionPanel.add(drawButton);
        actionPanel.add(swapButton);
        return actionPanel;
    }

    private JButton createDrawButton() {
        JButton drawButton = new JButton("Piocher");
        drawButton.setBorderPainted(false);
        drawButton.setBackground(Color.GREEN);

        drawButton.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                drawButton.setBackground(Color.WHITE); // Couleur de surbrillance
            }

            @Override
            public void mouseExited(MouseEvent e) {
                drawButton.setBackground(Color.GREEN); // Réinitialiser la couleur de fond
            }
        });

        drawButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int sz = Game.getCurrentPlayer().getSelectedCards().size();
                if (sz > 0) {
                    if (!Game.dck.getDeckPile().isEmpty()) {
                        System.out.println("nb selected cards= " + sz + " last card selected= "
                                + Game.getCurrentPlayer().getSelectedCards().get(sz - 1).getCardName());
                        Game.getCurrentPlayer().getNameOfCardsInPlayerHand();
                        Game.draw();
                        // Game.dsc.putCard(Game.getCurrentPlayer().getSelectedCards().get(sz - 1));
                        // Mettre à jour l'IHM ici si nécessaire
                        Game.play();
                        Game.nextTurn();
                        updateInfoPanel();
                        updatePlayerCardsPanel();
                        updateTopPanel();
                        Game.getCurrentPlayer().clearSelectedCards();
                    } else {
                        // fin du jeu !
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une carte", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }

            }
        });
        return drawButton;
    }

    private JButton createSwapButton() {
        JButton swapButton = new JButton("Échanger");
        swapButton.setBorderPainted(false);
        swapButton.setBackground(Color.RED);

        swapButton.addMouseListener((MouseListener) new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                swapButton.setBackground(Color.WHITE); // Couleur de surbrillance
            }

            @Override
            public void mouseExited(MouseEvent e) {
                swapButton.setBackground(Color.RED); // Réinitialiser la couleur de fond
            }
        });

        swapButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int sz = Game.getCurrentPlayer().getSelectedCards().size();
                if (sz > 0) {
                    if (!Game.dsc.getDiscardPile().isEmpty()) {
                        System.out.println("nb selected cards= " + sz + " last card selected= "
                                + Game.getCurrentPlayer().getSelectedCards().get(sz - 1).getCardName());
                        Game.getCurrentPlayer().getNameOfCardsInPlayerHand();
                        Game.swap(Game.getCurrentPlayer().getSelectedCards().get(sz - 1));
                        // Game.dsc.putCard(currentPlayer.getSelectedCards().get(sz - 1));
                        // Mettre à jour l'IHM ici si nécessaire
                        Game.play();
                        Game.nextTurn();
                        updateInfoPanel();
                        updatePlayerCardsPanel();
                        updateTopPanel();
                        Game.getCurrentPlayer().clearSelectedCards();
                    } else {
                        JOptionPane.showMessageDialog(null, "La pile de défausse est vide !", "Erreur",
                                JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    JOptionPane.showMessageDialog(null, "Veuillez sélectionner une carte", "Erreur",
                            JOptionPane.ERROR_MESSAGE);
                }

                // Mettre à jour l'IHM ici si nécessaire
            }
        });
        return swapButton;
    }

    // Getters pour les éléments de l'IHM qui nécessitent une mise à jour dynamique
    public JLabel getNameLabel() {
        return nameLabel;
    }

    public JLabel getScoreLabel() {
        return scoreLabel;
    }

    public JLabel getTurnLabel() {
        return turnLabel;
    }

    public JLabel getDeckLabel() {
        return deckLabel;
    }

    public JLabel getDiscardLabel() {
        return discardLabel;
    }

    public JButton[] getPlayerCardButtons() {
        return playerCardButtons;
    }

}
