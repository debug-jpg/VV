package main;


import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.FloatControl;
import java.net.URL;

@SuppressWarnings("all")
public class Sound {

    Clip clip;
    URL soundURL[] = new URL[30];
    FloatControl control;
    int volumeScale = 3;
    float volume;

    public Sound() {

        soundURL[0] = getClass().getResource("/sound/bgm1.wav");
        soundURL[1] = getClass().getResource("/sound/bgm2.wav");
        soundURL[2] = getClass().getResource("/sound/bonus_hint.wav");
        soundURL[3] = getClass().getResource("/sound/click.wav");
        soundURL[4] = getClass().getResource("/sound/correct.wav");
        soundURL[5] = getClass().getResource("/sound/incorrect.wav");
        soundURL[15] = getClass().getResource("/sound/dramaturgy.wav");
        soundURL[16] = getClass().getResource("/sound/literary_nonsense.wav");
        soundURL[17] = getClass().getResource("/sound/gunjo_sanka.wav");
        soundURL[18] = getClass().getResource("/sound/cup_noodle.wav");

    }

    public void setFile(int i) {

        try {

            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            control = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            checkVolume();

        }
        catch (Exception e) {
            e.printStackTrace();
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

    public void checkVolume() {

        switch (volumeScale) {
            case 0:
                volume = -80f;
                break;
            case 1:
                volume = -20f;
                break;
            case 2:
                volume = -12f;
                break;
            case 3:
                volume = -5f;
                break;
            case 4:
                volume = 1f;
                break;
            case 5:
                volume = 6f;
                break;
        }

        control.setValue(volume);

    }
}


















