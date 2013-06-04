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
public class GUIPanel extends JPanel implements KeyListener {

    Tile[][] grid = new Tile[70][68]; //504 504 screen rz.
    Pacman pacman = null;
    int pacDir = -1;
    int paci;
    int pacj;
    int blinkyI;
    int blinkyJ;
    boolean updateThreadRun = false;
    public static EmptySprite empty = new EmptySprite();
    RedGhost blinky;
    //Image pacSprite;// = ImageIO.read(getClass().getResource("/pacman_sprites/pac-closed.png").toURI().toURL());

    public GUIPanel() {
        this.setBackground(Color.BLACK);
        this.addKeyListener(this);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                grid[i][j] = new Tile(i * 7 + 8, j * 7);//Make each one only a fraction of the sprite's size (14px)
                //the 5 is for the size of the collision grid mesh thing.
                //the pacman images are meant to be going out of bounds of the grid space.
                //So this is okay
                grid[i][j].setSpriteContained(empty);
            }
        }
        for (int i = 0; i < grid.length; i++) {
            grid[i][0].setSpriteContained(new CollisionWall(false));
            grid[i][grid[0].length - 1].setSpriteContained(new CollisionWall(true));
        }
        for (int i = 0; i < grid[0].length; i++) {
            grid[0][i].setSpriteContained(new CollisionWall(false));
            grid[grid.length - 1][i].setSpriteContained(new CollisionWall());
        }
        pacman = new Pacman();
        blinky = new RedGhost();
        grid[35][54].setSpriteContained(pacman);
        paci = 35;
        pacj = 54;
        grid[grid.length / 2][grid[0].length / 2].setSpriteContained(blinky);
        BoardMethods.setup(grid);
        //This is just for testing the width and height of the entire thing

        /*int temp = 91; //Furthest to the end without touching the grid is 91, 96.  So lets change
         //The size of the grid itself.
         Pacman pacman1 = new Pacman(grid[96][temp],pacSprite);
         grid[96][temp].setSpriteContained(pacman1);*/

        ////////////////////////////////////////////////////
    }

    public void update() {
        move();
    }

    public void move() {
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if (grid[i][j].getX() == pacman.getxPos() && grid[i][j].getY() == pacman.getyPos()) {
                    paci = i;
                    pacj = j;
                    movePac(i, j);
                } else if (grid[i][j].getX() == blinky.getxPos() && grid[i][j].getY() == blinky.getyPos()) {
                    blinkyI = i;
                    blinkyJ = j;
                    moveBlinky(i, j);
                }
            }
        }
    }

    public void moveBlinky(int i, int j) {
        blinky.turnDir(grid, paci, pacj, i, j);
        if (blinky.getDirection() == Direction.UP) {
            //May not need the turning logic due to the checks in turndir, we will see
            //CONTINUE HERE MATT!! =D
            //if(grid[i - 1][j - 2].getSpriteContained() == empty && grid[i][j - 2].getSpriteContained() == empty)
            //{
            //blinky.setDirection(Direction.UP);
            grid[i][j].setSpriteContained(empty);
            grid[i][j - 1].setSpriteContained(blinky);
            blinky.update();
            //}
        } else if (blinky.getDirection() == Direction.LEFT) {
            grid[i][j].setSpriteContained(empty);
            grid[i - 1][j].setSpriteContained(blinky);
            blinky.update();
        } else if (blinky.getDirection() == Direction.DOWN) {
            grid[i][j].setSpriteContained(empty);
            grid[i][j + 1].setSpriteContained(blinky);
            blinky.update();
        } else if (blinky.getDirection() == Direction.RIGHT) {
            grid[i][j].setSpriteContained(empty);
            grid[i + 1][j].setSpriteContained(blinky);
            blinky.update();
        }
    }

    public void movePac(int i, int j) {
        if (pacDir == Direction.UP) {
            //System.out.println("Got to if statement");
            if (grid[i - 1][j - 2].getSpriteContained() == empty && grid[i][j - 2].getSpriteContained() == empty) {
                pacman.setDirection(Direction.UP);
                grid[i][j].setSpriteContained(empty);
                grid[i][j - 1].setSpriteContained(pacman);
                pacman.update();
            }
        } else if (pacDir == Direction.DOWN) {
            if ((grid[i][j + 1].getSpriteContained() == empty) && (j + 1 != grid[0].length - 2) && grid[i - 1][j + 1].getSpriteContained() == empty) {
                pacman.setDirection(Direction.DOWN);
                grid[i][j].setSpriteContained(empty);
                grid[i][j + 1].setSpriteContained(pacman);
                pacman.update();
            }
        } else if (pacDir == Direction.LEFT) {
            if (grid[i - 2][j].getSpriteContained() == empty && (i - 2 != -1) && grid[i - 2][j - 1].getSpriteContained() == empty) {
                pacman.setDirection(Direction.LEFT);
                grid[i][j].setSpriteContained(empty);
                grid[i - 1][j].setSpriteContained(pacman);
                pacman.update();
            }
        } else if ((pacDir == Direction.RIGHT)) {
            if (grid[i + 1][j].getSpriteContained() == empty && (i + 1 != grid.length - 2) && grid[i + 1][j - 1].getSpriteContained() == empty) {
                pacman.setDirection(Direction.RIGHT);
                grid[i][j].setSpriteContained(empty);
                grid[i + 1][j].setSpriteContained(pacman);
                pacman.update();
            }
        }
    }

    public class updateThread extends Thread {

        @Override
        public void run() {
            updateThreadRun = true;
            //SoundEffects.playEntry();
            //pause(5000);
            while (true) {
                pause(100);
                update();
            }
        }

        public void pause(long time) {
            try {
                Thread.sleep(time);
            } catch (Exception e) {
                //There was an error sleeping - this should never really get called.
                System.err.println(e.getMessage());
            }
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
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
        if (e.getKeyCode() == KeyEvent.VK_UP) {
            pacDir = Direction.UP;
            //pacman.setDirection(Direction.UP);
            //System.out.println("up");
        } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
            pacDir = Direction.DOWN;
            //pacman.setDirection(Direction.DOWN);
            //System.out.println("down");
        } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
            pacDir = Direction.LEFT;
            //pacman.setDirection(Direction.LEFT);
            //System.out.println("left");
        } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
            pacDir = Direction.RIGHT;
            //pacman.setDirection(Direction.RIGHT);
            //System.out.println("right");
        } else if (e.getKeyCode() == KeyEvent.VK_SPACE && updateThreadRun == false) {
            updateThread t = new updateThread();
            t.start();
        } else if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
            System.exit(0);
        } else if (e.getKeyCode() == KeyEvent.VK_P) //For debugging
        {
            System.out.println(pacman.getxPos() + " " + pacman.getyPos());
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {
        //Don't do anything
    }
}