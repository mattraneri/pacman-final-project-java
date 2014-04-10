/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman_final;

/**
 *
 * @author matt
 */
public class RedGhost extends Ghost {
    //Red images are default, not a problem- no changing of images nessesary

    public RedGhost() {
        super();
    }

    @Override
    public void move(int i, int j, Pacman pacman, Tile[][] grid, int paci, int pacj) {
        this.turnDir(grid, paci, pacj, i, j);
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
