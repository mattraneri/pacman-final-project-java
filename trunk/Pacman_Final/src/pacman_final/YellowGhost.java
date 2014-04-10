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
public class YellowGhost extends Ghost {

    public YellowGhost() {
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
            this.setCurrentSprite(upSprite1);
        } catch (IOException e) {
            //This condition will never be met.
        }
    }

    @Override
    public void move(int i, int j, Pacman pacman, Tile[][] grid, int paci, int pacj) {
        //We need to add distance to move this whenever he is within 8 of pacman
        //he needs to move to the bottom left
        if (GUIPanel.isFarEnough(i, j) == true) {
            this.turnDir(grid, paci, pacj, i, j);
        } else {
            this.turnDir(grid, grid.length - 1, grid[0].length - 1, i, j);
        }
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
