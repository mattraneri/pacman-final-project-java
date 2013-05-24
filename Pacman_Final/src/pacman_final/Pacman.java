/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman_final;

import java.awt.Graphics;
import java.awt.Image;
import javax.imageio.ImageIO;

/**
 *
 * @author matt
 */
public class Pacman extends Sprite{
    private Image spriteClose = null;
    private Image spriteDown1 = null;
    private Image spriteDown2 = null;
    private Image spriteRight1 = null;
    private Image spriteRight2 = null;
    private Image spriteLeft1 = null;
    private Image spriteLeft2 = null;
    private Image spriteUp1 = null;
    private Image spriteUp2 = null;
    private Image sprite = null;
    private int direction = Direction.LEFT;
    public Pacman()
    {
        super();
        direction = Direction.LEFT;
        try
        {
            spriteClose = ImageIO.read(getClass().getResource("/pacman_sprites/pac-closed.png"));
            spriteDown1 = ImageIO.read(getClass().getResource("/pacman_sprites/pac-down-1.png"));
            spriteDown2 = ImageIO.read(getClass().getResource("/pacman_sprites/pac-down-2.png"));
            spriteRight1 = ImageIO.read(getClass().getResource("/pacman_sprites/pac-right-1.png"));
            spriteRight2 = ImageIO.read(getClass().getResource("/pacman_sprites/pac-right-2.png"));
            spriteLeft1 = ImageIO.read(getClass().getResource("/pacman_sprites/pac-left-1.png"));
            spriteLeft2 = ImageIO.read(getClass().getResource("/pacman_sprites/pac-left-2.png"));
            spriteUp1 = ImageIO.read(getClass().getResource("/pacman_sprites/pac-up-1.png"));
            spriteUp2 = ImageIO.read(getClass().getResource("/pacman_sprites/pac-up-2.png"));
            sprite = spriteLeft1;
        }catch(Exception e)
        {
            System.out.println(e.getMessage());
        }
    }
    
    @Override
    public void draw(Graphics g, int xPos, int yPos)
    {//The minus 7 is for the grid size- the center of the grid space must be in the center of the sprite
        g.drawImage(sprite, xPos - 7, yPos- 7, null);
        super.setxPos(xPos);
        super.setyPos(yPos);
    }

    public void update()
    {
        if(this.getDirection() == Direction.DOWN)
        {
            if(sprite == spriteDown1)
            {
                sprite = spriteDown2;
            }
            else if(sprite == spriteDown2)
            {
                sprite = spriteClose;
            }
            else if(sprite == spriteClose)
            {
                sprite = spriteDown1;
            }
            else
            {
                sprite = spriteClose;
            }
        }
        else if(this.getDirection() == Direction.UP)
        {
            if(sprite == spriteUp1)
            {
                sprite = spriteUp2;
            }
            else if(sprite == spriteUp2)
            {
                sprite = spriteClose;
            }
            else if(sprite == spriteClose)
            {
                sprite = spriteUp1;
            }
            else
            {
                sprite = spriteClose;
            }
        }
        else if(this.getDirection() == Direction.LEFT)
        {
            if(sprite == spriteLeft1)
            {
                sprite = spriteLeft2;
            }
            else if(sprite == spriteLeft2)
            {
                sprite = spriteClose;
            }
            else if(sprite == spriteClose)
            {
                sprite = spriteLeft1;
            }
            else
            {
                sprite = spriteClose;
            }
        }
        else if(this.getDirection() == Direction.RIGHT)
        {
            if(sprite == spriteRight1)
            {
                sprite = spriteRight2;
            }
            else if(sprite == spriteRight2)
            {
                sprite = spriteClose;
            }
            else if(sprite == spriteClose)
            {
                sprite = spriteRight1;
            }
            else
            {
                sprite = spriteClose;
            }
        }
    }
    
    /**
     * @return the sprite
     */
    public Image getSprite() {
        return sprite;
    }

    /**
     * @param sprite the sprite to set
     */
    public void setSprite(Image sprite) {
        this.sprite = sprite;
    }


    /**
     * @return the direction
     */
    public int getDirection() {
        return direction;
    }

    /**
     * @param direction the direction to set
     */
    public void setDirection(int direction) {
        this.direction = direction;
    }

}
