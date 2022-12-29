package main;

import java.net.URL;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    URL[] soundURL = new URL[30];

    public Sound() {

        soundURL[0] = getClass().getResource("/music/ending.wav");
        soundURL[1] = getClass().getResource("/music/finalboss.wav");
        soundURL[2] = getClass().getResource("/music/game-over.wav");
        soundURL[3] = getClass().getResource("/music/Minecraft.wav");
        soundURL[4] = getClass().getResource("/music/Pokemon.wav");
        soundURL[5] = getClass().getResource("/music/win_pokemon.wav");

    }

    public void setFile(int i) {

        try {

            AudioInputStream inputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(inputStream);

        } catch (Exception e) {

        }

    }

    public void play() {
        clip.start();
    }

    public void loop() {
clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}
