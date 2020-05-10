package ui;

import java.io.File;
import java.net.URL;

public class PlaySound {

    // lets out doorbell sound when "Borrow" button is pressed
    public void playSound() {
        URL soundbyte = null;
        try {
            soundbyte = new File("Ding-dong.wav").toURI().toURL();
        } catch (Exception ex) {
            System.out.println("Error playing sound");
        }
        java.applet.AudioClip clip = java.applet.Applet.newAudioClip(soundbyte);
        clip.play();
    }
}
