/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman_final;

import java.awt.Graphics;

public class Tile {
    private int x, y;
    private Sprite spriteContained;
    private Sprite prevSprite;
    public Tile()
    {
        x = 0;
        y = 0;
        spriteContained = null;
    }
    
    public Tile(int x, int y)
    {
        this.x = x;
        this.y = y;
        this.spriteContained = null;
    }
    
    public Tile(int x, int y, Sprite s)
    {
        this.x = x;
        this.y = y;
        this.spriteContained = s;
    }

    public void draw(Graphics g)
    {
        if(spriteContained != null)
        {
            spriteContained.draw(g, getX(), getY());
        }
    }
    
    /**
     * @return the x
     */
    public int getX() {
        return x;
    }

    /**
     * @param x the x to set
     */
    public void setX(int x) {
        this.x = x;
    }

    /**
     * @return the y
     */
    public int getY() {
        return y;
    }

    /**
     * @param y the y to set
     */
    public void setY(int y) {
        this.y = y;
    }

    /**
     * @return the spriteContained
     */
    public Sprite getSpriteContained() {
        return spriteContained;
    }

    /**
     * @param spriteContained the spriteContained to set
     */
    public void setSpriteContained(Sprite spriteContained) {
        this.spriteContained = spriteContained;
    }

    /**
     * @return the prevSprite
     */
    public Sprite getPrevSprite() {
        return prevSprite;
    }

    /**
     * @param prevSprite the prevSprite to set
     */
    public void setPrevSprite(Sprite prevSprite) {
        this.prevSprite = prevSprite;
    }
}
