/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman_final;

import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author matt
 */
public class BlueGhost extends Ghost{
    public BlueGhost()
    {
        try{
            Image upSprite1 = ImageIO.read(getClass().getResource("/Inky_Sprites/ghost-blue-up-1.png"));
            Image upSprite2 = ImageIO.read(getClass().getResource("/Inky_Sprites/ghost-blue-up-2.png"));
            Image leftSprite1 = ImageIO.read(getClass().getResource("/Inky_Sprites/ghost-blue-left-1.png"));
            Image leftSprite2 = ImageIO.read(getClass().getResource("/Inky_Sprites/ghost-blue-left-2.png"));
            Image rightSprite1 = ImageIO.read(getClass().getResource("/Inky_Sprites/ghost-blue-right-1.png"));
            Image rightSprite2 = ImageIO.read(getClass().getResource("/Inky_Sprites/ghost-blue-right-2.png"));
            Image downSprite1 = ImageIO.read(getClass().getResource("/Inky_Sprites/ghost-blue-down-1.png"));
            Image downSprite2 = ImageIO.read(getClass().getResource("/Inky_Sprites/ghost-blue-down-2.png"));
            
            this.setUpSprite1(upSprite1);
            this.setUpSprite2(upSprite2);
            this.setLeftSprite1(leftSprite1);
            this.setLeftSprite2(leftSprite2);
            this.setRightSprite1(rightSprite1);
            this.setRightSprite2(rightSprite2);
            this.setDownSprite1(downSprite1);
            this.setDownSprite2(downSprite2);
            this.setCurrentSprite(upSprite1);
        } catch(IOException e) {
            //This will never get here
        }
    }
}
