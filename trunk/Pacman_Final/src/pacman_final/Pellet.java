package pacman_final;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Pellet extends Sprite
{

    private Image pelletSprite;
    private Image currentSprite;
    private boolean eaten;
    
    public Pellet()
    {
        eaten = false;
        try
        {
            pelletSprite = ImageIO.read(getClass().getResource("/pacman_sprites/pellet.png"));
            currentSprite = pelletSprite;
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public void update()
    {
        if(eaten == false)
        {
            currentSprite = pelletSprite;
        }
        else
        {
            currentSprite = null;
        }
    }
    
    @Override
    public void draw(Graphics g, int xPos, int yPos) {
        super.setxPos(xPos);
        super.setyPos(yPos);
        g.drawImage(currentSprite, xPos - 3, yPos - 3, null);
    }
    
    /**
     * @return the currentSprite
     */
    public Image getCurrentSprite() {
        return currentSprite;
    }
    
    public void setCurrentSprite(Image currentSprite) {
        this.currentSprite = currentSprite;
    }
}