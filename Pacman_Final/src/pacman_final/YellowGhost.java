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
public class YellowGhost extends Ghost{
    YellowGhost()
    {
        try {
            Image upSprite1 = ImageIO.read(getClass().getResource("/Clyde_Sprites/ghost-yellow-up-1.png"));
            Image upSprite2 = ImageIO.read(getClass().getResource("/Clyde_Sprites/ghost-yellow-up-2.png"));
            Image leftSprite1 = ImageIO.read(getClass().getResource("/Clyde_Sprites/ghost-yellow-left-1.png"));
            Image leftSprite2 = ImageIO.read(getClass().getResource("/Clyde_Sprites/ghost-yellow-left-2.png"));
            Image rightSprite1 = ImageIO.read(getClass().getResource("/Clyde_Sprites/ghost-yellow-right-1.png"));
            Image rightSprite2 = ImageIO.read(getClass().getResource("/Clyde_Sprites/ghost-yellow-right-2.png"));
            Image downSprite1 = ImageIO.read(getClass().getResource("/Clyde_Sprites/ghost-yellow-down-1.png"));
            Image downSprite2 = ImageIO.read(getClass().getResource("/Clyde_Sprites/ghost-yellow-down-2.png"));
            
            this.setUpSprite1(upSprite1);
            this.setUpSprite2(upSprite2);
            this.setLeftSprite1(leftSprite1);
            this.setLeftSprite2(leftSprite2);
            this.setRightSprite1(rightSprite1);
            this.setRightSprite2(rightSprite2);
            this.setDownSprite1(downSprite1);
            this.setDownSprite2(downSprite2);
        } catch(IOException e) {
            //This condition will never be met.
        }
    }
}
