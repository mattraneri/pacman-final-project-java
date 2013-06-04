/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman_final;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.io.IOException;
import javax.imageio.ImageIO;

/**
 *
 * @author matt
 */
public class Ghost extends Sprite {

    private Image currentSprite;
    private Image upSprite1;
    private Image downSprite1;
    private Image leftSprite1;
    private Image rightSprite1;
    private Image upSprite2;
    private Image downSprite2;
    private Image leftSprite2;
    private Image rightSprite2;
    private int direction = Direction.UP;
    private boolean animator = false;

    public Ghost() {
        try {
            upSprite1 = ImageIO.read(getClass().getResource("/red_ghost_sprites/ghost-red-up-1.png"));
            downSprite1 = ImageIO.read(getClass().getResource("/red_ghost_sprites/ghost-red-down-1.png"));
            leftSprite1 = ImageIO.read(getClass().getResource("/red_ghost_sprites/ghost-red-left-1.png"));
            rightSprite1 = ImageIO.read(getClass().getResource("/red_ghost_sprites/ghost-red-right-1.png"));
            currentSprite = upSprite1;
            upSprite2 = ImageIO.read(getClass().getResource("/red_ghost_sprites/ghost-red-up-2.png"));
            downSprite2 = ImageIO.read(getClass().getResource("/red_ghost_sprites/ghost-red-down-2.png"));
            leftSprite2 = ImageIO.read(getClass().getResource("/red_ghost_sprites/ghost-red-left-2.png"));
            rightSprite2 = ImageIO.read(getClass().getResource("/red_ghost_sprites/ghost-red-right-2.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
        
    }
    
    public void turnDir(Tile[][] grid, int to1, int to2, int myI, int myJ)
    {
        //myI and myJ is where this ghost is
        //to1 and to2 are the i and j values for where it is we want the ghost to go.
        if(myJ > to2 && isSpotEmpty(grid, myI - 1, myJ - 2) && isSpotEmpty(grid, myI, myJ - 2))
        {
            this.setDirection(Direction.UP);
        }
        else if(myI > to1 && isSpotEmpty(grid, myI - 2, myJ) && isSpotEmpty(grid, myI - 2, myJ - 1))
        {
            this.setDirection(Direction.LEFT);
        }
        else if(myJ < to2 && isSpotEmpty(grid, myI, myJ + 1) && isSpotEmpty(grid, myI - 1, myJ + 1))
        {
            this.setDirection(Direction.DOWN);
        }
        else if(myI < to1 && isSpotEmpty(grid, myI + 1, myJ) && isSpotEmpty(grid, myI + 1, myJ - 1))
        {
            this.setDirection(Direction.RIGHT);
        }
        else
        {
            //All of the above conditions should be satisfied...
        }
    }
    
    public boolean isSpotEmpty(Tile[][] grid, int i, int j)
    {
        if(grid[i][j].getSpriteContained().equals(GUIPanel.empty))
        {
            return true;
        }
        return false;
    }
    

    public void update() {
        animator = !animator;
        if (direction == Direction.UP) {
            if (animator == true) {
                currentSprite = upSprite1;
            } else {
                currentSprite = upSprite2;
            }
        } else if (direction == Direction.DOWN) {
            if (animator == true) {
                currentSprite = downSprite1;
            } else {
                currentSprite = downSprite2;
            }
        } else if (direction == Direction.LEFT) {
            if (animator == true) {
                currentSprite = leftSprite1;
            } else {
                currentSprite = leftSprite2;
            }
        } else if (direction == Direction.RIGHT) {
            if (animator == true) {
                currentSprite = rightSprite1;
            } else {
                currentSprite = rightSprite2;
            }
        }
    }

    @Override
    public void draw(Graphics g, int xPos, int yPos) {
        super.setxPos(xPos);
        super.setyPos(yPos);
        g.drawImage(currentSprite, xPos - 7, yPos - 7, null);
        g.setColor(Color.GREEN);
        g.fillRect(xPos, yPos, 7, 7);
    }

    /**
     * @return the currentSprite
     */
    public Image getCurrentSprite() {
        return currentSprite;
    }

    /**
     * @param currentSprite the currentSprite to set
     */
    public void setCurrentSprite(Image currentSprite) {
        this.currentSprite = currentSprite;
    }

    /**
     * @return the upSprite1
     */
    public Image getUpSprite1() {
        return upSprite1;
    }

    /**
     * @param upSprite1 the upSprite1 to set
     */
    public void setUpSprite1(Image upSprite1) {
        this.upSprite1 = upSprite1;
    }

    /**
     * @return the downSprite1
     */
    public Image getDownSprite1() {
        return downSprite1;
    }

    /**
     * @param downSprite1 the downSprite1 to set
     */
    public void setDownSprite1(Image downSprite1) {
        this.downSprite1 = downSprite1;
    }

    /**
     * @return the leftSprite1
     */
    public Image getLeftSprite1() {
        return leftSprite1;
    }

    /**
     * @param leftSprite1 the leftSprite1 to set
     */
    public void setLeftSprite1(Image leftSprite1) {
        this.leftSprite1 = leftSprite1;
    }

    /**
     * @return the rightSprite1
     */
    public Image getRightSprite1() {
        return rightSprite1;
    }

    /**
     * @param rightSprite1 the rightSprite1 to set
     */
    public void setRightSprite1(Image rightSprite1) {
        this.rightSprite1 = rightSprite1;
    }

    /**
     * @return the upSprite2
     */
    public Image getUpSprite2() {
        return upSprite2;
    }

    /**
     * @param upSprite2 the upSprite2 to set
     */
    public void setUpSprite2(Image upSprite2) {
        this.upSprite2 = upSprite2;
    }

    /**
     * @return the downSprite2
     */
    public Image getDownSprite2() {
        return downSprite2;
    }

    /**
     * @param downSprite2 the downSprite2 to set
     */
    public void setDownSprite2(Image downSprite2) {
        this.downSprite2 = downSprite2;
    }

    /**
     * @return the leftSprite2
     */
    public Image getLeftSprite2() {
        return leftSprite2;
    }

    /**
     * @param leftSprite2 the leftSprite2 to set
     */
    public void setLeftSprite2(Image leftSprite2) {
        this.leftSprite2 = leftSprite2;
    }

    /**
     * @return the rightSprite2
     */
    public Image getRightSprite2() {
        return rightSprite2;
    }

    /**
     * @param rightSprite2 the rightSprite2 to set
     */
    public void setRightSprite2(Image rightSprite2) {
        this.rightSprite2 = rightSprite2;
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
