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

/**
 *
 * @author matt
 */
public class SoundEffects {
    
    static Clip alarm = null;
    
    public static void playEntry() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream input;
            input = AudioSystem.getAudioInputStream(SoundEffects.class.getResource("/sounds/start.wav"));
            clip.open(input);
            clip.start();
        } catch (LineUnavailableException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedAudioFileException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void playCredit() {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream input;
            input = AudioSystem.getAudioInputStream(SoundEffects.class.getResource("/sounds/credit.wav"));
            clip.open(input);
            clip.start();
        } catch (LineUnavailableException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedAudioFileException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void startAlarm() {
        try {
            alarm = AudioSystem.getClip();
            AudioInputStream input;
            input = AudioSystem.getAudioInputStream(SoundEffects.class.getResource("/sounds/ghost_alarm.wav"));
            alarm.open(input);
            alarm.loop(Clip.LOOP_CONTINUOUSLY);
        } catch (Exception e) {
            e.printStackTrace();

        }
    }
    
    public static void stopAlarm() {
        if(alarm != null)
            alarm.stop();
    }
    
    public static void playPacmanDead()
    {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream input;
            input = AudioSystem.getAudioInputStream(SoundEffects.class.getResource("/sounds/pac_die.wav"));
            clip.open(input);
            clip.start();
        } catch (LineUnavailableException e) {
            System.out.println(e.getMessage());
        } catch (UnsupportedAudioFileException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
