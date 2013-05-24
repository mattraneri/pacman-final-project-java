/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman_final;

import java.awt.Color;
import java.awt.Graphics;

/**
 *
 * @author matt
 */
public class CollisionWall extends Sprite{
    boolean drawFunc = true;
    
    public CollisionWall()
    {
        drawFunc = true;
    }
    
    public CollisionWall(boolean draw)
    {
        drawFunc = draw;
    }
    
    @Override
    public void draw(Graphics g, int xPos, int yPos) {
        g.setColor(Color.BLUE);
        if(drawFunc)
        {
            g.fillRect(xPos - 7, yPos - 7, 7, 7);
        }else{
            g.fillRect(xPos, yPos, 7, 7);
        }
    }
}
