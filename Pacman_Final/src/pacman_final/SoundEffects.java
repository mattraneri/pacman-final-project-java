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
    
    public static void playEntry()
    {
        try {
            Clip clip = AudioSystem.getClip();
            AudioInputStream input;
            input = AudioSystem.getAudioInputStream(SoundEffects.class.getResource("/sounds/pacman_beginning.wav"));
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
