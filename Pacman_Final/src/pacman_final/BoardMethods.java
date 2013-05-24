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
        grid[2][5].setSpriteContained(new CollisionWall(false));
        grid[2][6].setSpriteContained(new CollisionWall(false));
        grid[2][7].setSpriteContained(new CollisionWall(false));
        grid[2][8].setSpriteContained(new CollisionWall(false));
        grid[2][9].setSpriteContained(new CollisionWall(false));
        grid[2][10].setSpriteContained(new CollisionWall(false));
    }
}
