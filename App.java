import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

import ui.window.StartFrame;

public class App {
    public static void main(String[] args) {
        StartFrame.startgame();
        //new MainWindow();
        try {
            // Ouvrir le fichier audio
            AudioInputStream audioIn = AudioSystem.getAudioInputStream(App.class.getResource("ui/audio/Pixel.wav"));

            // Obtenir un lecteur de musique
            Clip clip = AudioSystem.getClip();

            // Charger la musique dans le lecteur
            clip.open(audioIn);

            // Jouer la musique en boucle
            clip.loop(Clip.LOOP_CONTINUOUSLY);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
