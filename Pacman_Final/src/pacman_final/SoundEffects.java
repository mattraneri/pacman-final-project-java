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
    
    static Clip alarm = null;
    static boolean openClose = false;

    public static void play(String effect) {
	try {
	    URL resource = SoundEffects.class.getResource("/sounds/" + effect + ".wav");
	    AudioInputStream input = AudioSystem.getAudioInputStream(resource);
	    Clip clip = AudioSystem.getClip();
	    clip.open(input);
	    clip.start();
	} catch (Exception e) {
	    throw new RuntimeException(e);
	}
    }

    public static void playEntry() {
	play("start");
    }

    public static void playCredit() {
	play("credit");
    }

    public static void startAlarm() {
	play("ghost_alarm");
    }
    
    public static void stopAlarm() {
        if(alarm != null)
            alarm.stop();
    }
    
    public static void contactDot()
    {
        if(openClose == false)
        {
            playOpen();
        } else {
            playClose();
        }
        openClose = !openClose;
    }
    
    public static void playClose()
    {
	play("pacman_close");
    }
    
    public static void playOpen() {
	play("pacman_open");
    }
    
    public static void playPacmanDead()
    {
	play("pac_die");
    }
}