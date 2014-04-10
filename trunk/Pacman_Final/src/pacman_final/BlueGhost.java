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
public class BlueGhost extends Ghost {

    public BlueGhost() {
        try {
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
        } catch (IOException e) {
            //This will never get here
        }
    }

    @Override
    public void move(int i, int j, Pacman pacman, Tile[][] grid, int paci, int pacj) {
        //First we need to get an offset from pacman's position- two in front of pacman
        int gotoi;
        int gotoj;
        if (pacman.getDirection() == Direction.UP) {
            gotoj = pacj - 3; //This is because two would be right in front of him.
            gotoi = paci;
        } else if (pacman.getDirection() == Direction.LEFT) {
            gotoj = pacj;
            gotoi = paci - 3; //Again, 3 because of the offset of the sprite
        } else if (pacman.getDirection() == Direction.RIGHT) {
            gotoj = pacj;
            gotoi = paci + 2; //No offset on this one
        } else {
            gotoj = pacj + 2;
            gotoi = paci;
        }
        //Now we need to use a bit of the slope formula, to calculate the needed x and y positions
        int offseti = GUIPanel.blinkyI - gotoi;
        int offsetj = GUIPanel.blinkyJ - gotoj;

        int togoi = gotoi + offseti;
        int togoj = gotoj + offsetj;

        this.turnDir(grid, togoi, togoj, i, j);
        if (this.getDirection() == Direction.UP && isSpotEmpty(grid, i, j - 1) && isSpotEmpty(grid, i - 1, j - 2) && isSpotEmpty(grid, i, j - 2)) {
            if (grid[i][j - 2].getSpriteContained() instanceof Pacman) {
                GUIPanel.pacmanDie();
            }
            grid[i][j].setSpriteContained(grid[i][j].getPrevSprite());
            grid[i][j - 1].setSpriteContained(this);
        } else if (this.getDirection() == Direction.LEFT && isSpotEmpty(grid, i - 1, j) && isSpotEmpty(grid, i - 2, j) && isSpotEmpty(grid, i - 2, j - 1)) {
            if (grid[i - 2][j].getSpriteContained() instanceof Pacman) {
                GUIPanel.pacmanDie();
            }
            grid[i][j].setSpriteContained(grid[i][j].getPrevSprite());
            grid[i - 1][j].setSpriteContained(this);
        } else if (this.getDirection() == Direction.DOWN && isSpotEmpty(grid, i, j + 1) && isSpotEmpty(grid, i - 1, j + 1)) {
            if (grid[i][j + 1].getSpriteContained() instanceof Pacman) {
                GUIPanel.pacmanDie();
            }
            grid[i][j].setSpriteContained(grid[i][j].getPrevSprite());
            grid[i][j + 1].setSpriteContained(this);
        } else if (this.getDirection() == Direction.RIGHT && isSpotEmpty(grid, i + 1, j) && isSpotEmpty(grid, i + 1, j - 1)) {
            if (grid[i + 1][j].getSpriteContained() instanceof Pacman) {
                GUIPanel.pacmanDie();
            }
            grid[i][j].setSpriteContained(grid[i][j].getPrevSprite());
            grid[i + 1][j].setSpriteContained(this);
        } else {
            this.setDirection(reverse(this.getDirection()));
        }
        this.update();
    }
}
