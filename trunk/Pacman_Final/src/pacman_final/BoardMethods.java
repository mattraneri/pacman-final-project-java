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
        drawLine(grid, 10, 20, 15, 25);
    }
    
    public static void drawLine(Tile[][] grid, int p1, int p2, int pp1, int pp2)
    {
        for(int i = p1; i < p2; i++)
        {
            for(int j = pp1; j < pp2; j++)
            {
                grid[i][j].setSpriteContained(new CollisionWall(false));
            }
        }
    }
}
