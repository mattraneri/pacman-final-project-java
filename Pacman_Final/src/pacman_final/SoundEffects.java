/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman_final;

import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.net.URL;

/**
 *
 * @author matt
 */
public class SoundEffects {
    static boolean alarmPlay = true;
    static boolean openClose = false;

    public static void play(String effect) {
        try {
            URL resource = SoundEffects.class.getResource("/sounds/" + effect + ".wav");
            AudioInputStream input = AudioSystem.getAudioInputStream(resource);
            Clip clip = AudioSystem.getClip();
            clip.open(input);
            clip.start();
        } catch (UnsupportedAudioFileException e) {
        } catch (IOException e) {
        } catch (LineUnavailableException e) {
        }
    }

    static class AlarmThread extends Thread {

        String effect;

        public AlarmThread(String eff) {
            effect = eff;
        }

        @Override
        public void run() {
            try {
                URL resource = SoundEffects.class.getResource("/sounds/" + effect + ".wav");
                AudioInputStream input = AudioSystem.getAudioInputStream(resource);
                Clip clip = AudioSystem.getClip();
                clip.open(input);
                while(alarmPlay) {
                    clip.start();
                    clip.loop(Clip.LOOP_CONTINUOUSLY);
                }
                clip.stop();
            } catch (IOException e) {
            } catch (LineUnavailableException e) {
            } catch (UnsupportedAudioFileException e) {
            }
        }

    }

    public static void playAlarm(String effect) {
        alarmPlay = true;
        AlarmThread at = new AlarmThread(effect);
        at.start();
    }

    public static void playEntry() {
        play("start");
    }

    public static void playCredit() {
        play("credit");
    }

    public static void startAlarm() {
        playAlarm("ghost_alarm");
    }

    public static void stopAlarm() {
        alarmPlay = false;
    }

    public static void contactDot() {
        if (!openClose) {
            playOpen();
        } else {
            playClose();
        }
        openClose = !openClose;
    }

    public static void playClose() {
        play("pacman_close");
    }

    public static void playOpen() {
        play("pacman_open");
    }

    public static void playPacmanDead() {
        play("pac_die");
    }
}
