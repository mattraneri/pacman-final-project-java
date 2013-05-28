/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman_final;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

/**
 *
 * @author matt
 */
public class GUIPanel extends JPanel implements KeyListener{
    
    Tile[][] grid = new Tile[70][68]; //504 504 screen rz.
    Pacman pacman = null;
    boolean updateThreadRun = false;
    int pacman1;
    int pacman2;
    EmptySprite empty = new EmptySprite();
    //Image pacSprite;// = ImageIO.read(getClass().getResource("/pacman_sprites/pac-closed.png").toURI().toURL());
    
    public GUIPanel()
    {
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                grid[i][j] = new Tile(i*7+8, j*7);//Make each one only a fraction of the sprite's size (14px)
                //the 5 is for the size of the collision grid mesh thing.
                //the pacman images are meant to be going out of bounds of the grid space.
                //So this is okay
                grid[i][j].setSpriteContained(empty);
            }
        }
        for(int i = 0; i < grid.length; i++)
        {
            grid[i][0].setSpriteContained(new CollisionWall(false));
            grid[i][grid[0].length - 1].setSpriteContained(new CollisionWall(true));
        }
        for(int i = 0; i < grid[0].length; i++)
        {
            grid[0][i].setSpriteContained(new CollisionWall(false));
            grid[grid.length - 1][i].setSpriteContained(new CollisionWall());
        }
        pacman = new Pacman();
        grid[grid.length / 2][60].setSpriteContained(pacman);
        BoardMethods.setup(grid);
        //This is just for testing the width and height of the entire thing
        
        /*int temp = 91; //Furthest to the end without touching the grid is 91, 96.  So lets change
        //The size of the grid itself.
        Pacman pacman1 = new Pacman(grid[96][temp],pacSprite);
        grid[96][temp].setSpriteContained(pacman1);*/
        
        ////////////////////////////////////////////////////
    }
    
    public void update()
    {
        move(pacman);
    }
    
    public void move(Sprite p)
    {
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                if(grid[i][j].getX() == pacman.getxPos() && grid[i][j].getY() == pacman.getyPos())
                {
                    movePac((Pacman)(p), i, j);
                }
            }
        }
    }
    
    public void movePac(Pacman p, int i, int j)
    {
        if(p.getDirection() == Direction.UP)
        {
            //System.out.println("Got to if statement");
            if(grid[i - 1][j - 2].getSpriteContained() == empty && grid[i][j - 2].getSpriteContained() == empty)
            {
                grid[i][j].setSpriteContained(empty);
                grid[i][j - 1].setSpriteContained(p);
                p.update();
                pacman1 = i;
                pacman2 = j - 1;
            }
        }
        else if(p.getDirection() == Direction.DOWN)
        {
            if((grid[i][j + 1].getSpriteContained() == empty) && (j + 1 != grid[0].length - 2) && grid[i - 1][j + 1].getSpriteContained() == empty)
            {
                grid[i][j].setSpriteContained(empty);
                grid[i][j + 1].setSpriteContained(p);
                p.update();
                pacman1 = i;
                pacman2 = j + 1;
            }
        }
        else if(p.getDirection() == Direction.LEFT)
        {
            if(grid[i - 2][j].getSpriteContained() == empty && (i - 2 != -1) && grid[i - 2][j - 1].getSpriteContained() == empty)
            {
                grid[i][j].setSpriteContained(empty);
                grid[i - 1][j].setSpriteContained(p);
                p.update();
                pacman1 = i - 1;
                pacman2 = j;
            }
        }
        else if((p.getDirection() == Direction.RIGHT))
        {
            if(grid[i + 1][j].getSpriteContained() == empty && (i + 1 != grid.length - 2) && grid[i + 1][j - 1].getSpriteContained() == empty)
            {
                grid[i][j].setSpriteContained(empty);
                grid[i + 1][j].setSpriteContained(p);
                p.update();
                pacman1 = i + 1;
                pacman2 = j;
            }
        }
    }
    
    public class updateThread extends Thread
    {

        @Override
        public void run() {
            updateThreadRun = true;
            SoundEffects.playEntry();
            pause(5000);
            while(true)
            {
                pause(100);
                update();
            }
        }
        public void pause(long time)
        {
            try{
                Thread.sleep(time);
            }catch(Exception e){
                //There was an error sleeping - this should never really get called.
                System.err.println(e.getMessage());
            }
        }
        
    }
    
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for(int i = 0; i < grid.length; i++)
        {
            for(int j = 0; j < grid[0].length; j++)
            {
                grid[i][j].draw(g);
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //WHY THEY ADDED THIS METHOD I DON'T KNOW, it's come in pointless, as it only gives
        //certain keystrokes.
    }

    @Override
    public void keyPressed(KeyEvent e) {
        //This is the useful method.
        if(e.getKeyCode() == KeyEvent.VK_UP)
        {
            pacman.setDirection(Direction.UP);
            //System.out.println("up");
        }
        else if(e.getKeyCode() == KeyEvent.VK_DOWN)
        {
            pacman.setDirection(Direction.DOWN);
            //System.out.println("down");
        }
        else if(e.getKeyCode() == KeyEvent.VK_LEFT)
        {
            pacman.setDirection(Direction.LEFT);
            //System.out.println("left");
        }
        else if(e.getKeyCode() == KeyEvent.VK_RIGHT)
        {
            pacman.setDirection(Direction.RIGHT);
            //System.out.println("right");
        }
        else if(e.getKeyCode() == KeyEvent.VK_SPACE && updateThreadRun == false)
        {
            updateThread t = new updateThread();
            t.start();
        }
        else if(e.getKeyCode() == KeyEvent.VK_ESCAPE)
        {
            System.exit(0);
        }
        else if(e.getKeyCode() == KeyEvent.VK_P) //For debugging
        {
            System.out.println(pacman1 + " " + pacman2);
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Don't do anything
    }
    
}