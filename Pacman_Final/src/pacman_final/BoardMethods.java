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
    public static void setup(Tile[][] grid)
    {
        drawBox(grid, 3, 13, 3, 13);
        drawBox(grid, 15, 28, 3, 13);
        drawBox(grid, 30, 40, 0, 13);
        drawBox(grid, 42, 55, 3, 13);
        drawBox(grid, 57, 66, 3, 13);
    }
    /*
     * Precondition: The p1 and p2 variables must be in the top left corner of the desired
     * draw location for the box.
     */
    public static void drawBox(Tile[][] grid, int p1, int p2, int pp1, int pp2)
    {
        for(int i = p1; i < p2; i++)
        {
            for(int j = pp1; j < pp2; j++)
            {
                grid[i][j].setSpriteContained(new CollisionWall(false));
            }
        }
        for(int i = p1 + 1; i < p2 - 1; i++)
        {
            for(int j = pp1 + 1; j < pp2 - 1; j++)
            {
                grid[i][j].setSpriteContained(new EmptySprite());
            }
        }
    }
}
