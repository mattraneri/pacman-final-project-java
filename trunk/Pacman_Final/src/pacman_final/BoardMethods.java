/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman_final;

/**
 *
 * @author matt
 */
public class BoardMethods {

    public static void setup(Tile[][] grid) {
        for (int i = 2; i < grid[0].length; i = i + 2) {
            drawDots(grid, 2, 27, i, i + 1);
        }

        //I hate this part.
        drawBox(grid, 3, 13, 3, 13);
        drawBox(grid, 15, 28, 3, 13);
        drawBox(grid, 30, 40, 0, 13);
        drawBox(grid, 42, 54, 3, 13);
        drawBox(grid, 56, 66, 3, 13);
        drawBox(grid, 3, 13, 15, 20);
        drawBox(grid, 0, 13, 22, 33);
        drawBox(grid, 15, 17, 15, 33);
        drawBox(grid, 15, 32, 23, 25);
        drawBox(grid, 19, 50, 15, 21);
        drawBox(grid, 52, 54, 15, 33);
        drawBox(grid, grid.length - 32, grid.length - 16, 23, 25);
        drawBox(grid, 34, 36, 21, 25);
        drawBox(grid, 19, 50, 27, 30);
        drawBox(grid, grid.length - 14, grid.length - 1, 22, 33);
        drawBox(grid, grid.length - 14, grid.length - 4, 15, 20);
        //for the bottom half we can just invert the positions using grid.length...
        //Bottom half start...
        drawBox(grid, 3, 13, grid.length - 15, grid.length - 6);
        drawBox(grid, 15, 28, grid.length - 15, grid.length - 6);
        drawBox(grid, 30, 40, grid.length - 15, grid.length - 3);
        drawBox(grid, 42, 54, grid.length - 15, grid.length - 6);
        drawBox(grid, 56, 66, grid.length - 15, grid.length - 6);
        drawBox(grid, 3, 13, grid.length - 22, grid.length - 17);
        drawBox(grid, 0, 13, grid.length - 35, grid.length - 24);
        drawBox(grid, 15, 17, grid.length - 35, grid.length - 17);
        drawBox(grid, 15, 32, grid.length - 27, grid.length - 25);
        drawBox(grid, 19, 50, grid.length - 23, grid.length - 17);
        drawBox(grid, 52, 54, grid.length - 35, grid.length - 17);
        drawBox(grid, grid.length - 32, grid.length - 16, grid.length - 27, grid.length - 25);
        drawBox(grid, 34, 36, grid.length - 27, grid.length - 23);
        drawBox(grid, 19, 50, grid.length - 32, grid.length - 29);
        drawBox(grid, grid.length - 14, grid.length - 1, grid.length - 35, grid.length - 24);
        drawBox(grid, grid.length - 14, grid.length - 4, grid.length - 22, grid.length - 17);
        clearDots(grid);
    }
    /*
     * Precondition: The p1 and p2 variables must be in the top left corner of the desired
     * draw location for the box.
     */

    public static void drawBox(Tile[][] grid, int p1, int p2, int pp1, int pp2) {
        for (int i = p1; i < p2; i++) {
            for (int j = pp1; j < pp2; j++) {
                grid[i][j].setSpriteContained(new CollisionWall(false));
            }
        }
        for (int i = p1 + 1; i < p2 - 1; i++) {
            for (int j = pp1 + 1; j < pp2 - 1; j++) {
                grid[i][j].setSpriteContained(new EmptySprite());
            }
        }
    }

    public static void drawDots(Tile[][] grid, int p1, int p2, int pp1, int pp2) {
        for (int i = p1; i < p2; i = i + 2) {
            for (int j = pp1; j < pp2; j = j + 2) {
                grid[i][j].setSpriteContained(GUIPanel.dot);
            }
        }
    }

    public static void clearDots(Tile[][] grid) {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 2; j < grid[0].length; j++) {
                if (grid[i][j].getSpriteContained().equals(GUIPanel.empty) && grid[i][j + 1].equals(new CollisionWall())) {
                    grid[i][j].setSpriteContained(GUIPanel.empty);
                }
            }
        }
    }
}
