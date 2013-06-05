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
    int pinkyI;
    int pinkyJ;
    int clydeI;
    int clydeJ;
    int inkyI;
    int inkyJ;
    boolean updateThreadRun = false;
    public static EmptySprite empty = new EmptySprite();
    RedGhost blinky;
    PinkGhost pinky;
    YellowGhost clyde;
    BlueGhost inky;
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
        pinky = new PinkGhost();
        clyde = new YellowGhost();
        inky = new BlueGhost();
        grid[35][54].setSpriteContained(pacman);
        paci = 35;
        pacj = 54;
        grid[grid.length / 2][grid[0].length / 2].setSpriteContained(blinky);
        grid[(grid.length / 2) + 4][grid[0].length / 2].setSpriteContained(pinky);
        grid[(grid.length / 2) - 4][grid[0].length / 2].setSpriteContained(clyde);
        grid[(grid.length / 2) + 8][grid[0].length / 2].setSpriteContained(inky);
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

    public int reverse(int dir) {
        if (dir == 0) {
            return 1;
        } else if (dir == 1) {
            return 0;
        } else if (dir == 2) {
            return 3;
        } else if (dir == 3) {
            return 2;
        }
        return -1;
    }

    public boolean isSpotEmpty(Tile[][] grid, int i, int j) {
        if (grid[i][j].getSpriteContained().equals(GUIPanel.empty)) {
            return true;
        }
        return false;
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
                } else if (grid[i][j].getX() == pinky.getxPos() && grid[i][j].getY() == pinky.getyPos()) {
                    pinkyI = i;
                    pinkyJ = j;
                    movePinky(i, j);
                } else if (grid[i][j].getX() == clyde.getxPos() && grid[i][j].getY() == clyde.getyPos()) {
                    clydeI = i;
                    clydeJ = j;
                    moveClyde(i, j);
                } else if(grid[i][j].getX() == inky.getxPos() && grid[i][j].getY() == inky.getyPos()) {
                    inkyI = i;
                    inkyJ = j;
                    moveInky(i, j);
                }
            }
        }
    }
    
    public void moveInky(int i, int j)
    {
        //First we need to get an offset from pacman's position- two in front of pacman
        int gotoi;
        int gotoj;
        if(pacman.getDirection() == Direction.UP)
        {
            gotoj = pacj - 3; //This is because two would be right in front of him.
            gotoi = paci;
        } else if(pacman.getDirection() == Direction.LEFT) {
            gotoj = pacj;
            gotoi = paci - 3; //Again, 3 because of the offset of the sprite
        } else if(pacman.getDirection() == Direction.RIGHT) {
            gotoj = pacj;
            gotoi = paci + 2; //No offset on this one
        } else {
            gotoj = pacj + 2;
            gotoi = paci;
        }
        //Now we need to use a bit of the slope formula, to calculate the needed x and y positions
        int offseti = blinkyI - gotoi;
        int offsetj = blinkyJ - gotoj;
        
        int togoi = gotoi + offseti;
        int togoj = gotoj + offsetj;
        
        inky.turnDir(grid, togoi, togoj, i, j);
        if (inky.getDirection() == Direction.UP && isSpotEmpty(grid, i, j - 1) && isSpotEmpty(grid, i - 1, j - 2) && isSpotEmpty(grid, i, j - 2)) {
            grid[i][j].setSpriteContained(empty);
            grid[i][j - 1].setSpriteContained(inky);
        } else if (inky.getDirection() == Direction.LEFT && isSpotEmpty(grid, i - 1, j) && isSpotEmpty(grid, i - 2, j) && isSpotEmpty(grid, i - 2, j - 1)) {
            grid[i][j].setSpriteContained(empty);
            grid[i - 1][j].setSpriteContained(inky);
        } else if (inky.getDirection() == Direction.DOWN && isSpotEmpty(grid, i, j + 1) && isSpotEmpty(grid, i - 1, j + 1)) {
            grid[i][j].setSpriteContained(empty);
            grid[i][j + 1].setSpriteContained(inky);
        } else if (inky.getDirection() == Direction.RIGHT && isSpotEmpty(grid, i + 1, j) && isSpotEmpty(grid, i + 1, j - 1)) {
            grid[i][j].setSpriteContained(empty);
            grid[i + 1][j].setSpriteContained(inky);
        } else {
            inky.setDirection(reverse(inky.getDirection()));
        }
        inky.update();
    }

    public void moveClyde(int i, int j) {
        //We need to add distance to move clyde whenever he is within 8 of pacman
        //he needs to move to the bottom left
        if (isFarEnough(i, j) == true) {
            clyde.turnDir(grid, paci, pacj, i, j);
        } else {
            clyde.turnDir(grid, grid.length - 1, grid[0].length - 1, i, j);
        }
        if (clyde.getDirection() == Direction.UP && isSpotEmpty(grid, i, j - 1) && isSpotEmpty(grid, i - 1, j - 2) && isSpotEmpty(grid, i, j - 2)) {
            grid[i][j].setSpriteContained(empty);
            grid[i][j - 1].setSpriteContained(clyde);
        } else if (clyde.getDirection() == Direction.LEFT && isSpotEmpty(grid, i - 1, j) && isSpotEmpty(grid, i - 2, j) && isSpotEmpty(grid, i - 2, j - 1)) {
            grid[i][j].setSpriteContained(empty);
            grid[i - 1][j].setSpriteContained(clyde);
        } else if (clyde.getDirection() == Direction.DOWN && isSpotEmpty(grid, i, j + 1) && isSpotEmpty(grid, i - 1, j + 1)) {
            grid[i][j].setSpriteContained(empty);
            grid[i][j + 1].setSpriteContained(clyde);
        } else if (clyde.getDirection() == Direction.RIGHT && isSpotEmpty(grid, i + 1, j) && isSpotEmpty(grid, i + 1, j - 1)) {
            grid[i][j].setSpriteContained(empty);
            grid[i + 1][j].setSpriteContained(clyde);
        } else {
            clyde.setDirection(reverse(clyde.getDirection()));
        }
        clyde.update();
    }

    //This method is used by Clyde to determine distance from pacman, and whether
    //He should chase or run back to the corner
    public boolean isFarEnough(int x, int y) {
        int sqOfX = (x - paci) * (x - paci);
        int sqOfY = (y - pacj) * (y - pacj);
        if (Math.sqrt(sqOfX + sqOfY) > 8) {
            return true;
        }
        return false;
    }

    public void movePinky(int i, int j) {
        if (pacman.getDirection() == Direction.UP) {
            pinky.turnDir(grid, paci, pacj - 4, i, j);
        } else if (pacman.getDirection() == Direction.LEFT) {
            pinky.turnDir(grid, paci - 4, pacj, i, j);
        } else if (pacman.getDirection() == Direction.RIGHT) {
            pinky.turnDir(grid, paci + 4, pacj, i, j);
        } else {
            pinky.turnDir(grid, paci, pacj + 4, i, j);
        }
        if (pinky.getDirection() == Direction.UP && isSpotEmpty(grid, i, j - 1) && isSpotEmpty(grid, i - 1, j - 2) && isSpotEmpty(grid, i, j - 2)) {
            grid[i][j].setSpriteContained(empty);
            grid[i][j - 1].setSpriteContained(pinky);
        } else if (pinky.getDirection() == Direction.LEFT && isSpotEmpty(grid, i - 1, j) && isSpotEmpty(grid, i - 2, j) && isSpotEmpty(grid, i - 2, j - 1)) {
            grid[i][j].setSpriteContained(empty);
            grid[i - 1][j].setSpriteContained(pinky);
        } else if (pinky.getDirection() == Direction.DOWN && isSpotEmpty(grid, i, j + 1) && isSpotEmpty(grid, i - 1, j + 1)) {
            grid[i][j].setSpriteContained(empty);
            grid[i][j + 1].setSpriteContained(pinky);
        } else if (pinky.getDirection() == Direction.RIGHT && isSpotEmpty(grid, i + 1, j) && isSpotEmpty(grid, i + 1, j - 1)) {
            grid[i][j].setSpriteContained(empty);
            grid[i + 1][j].setSpriteContained(pinky);
        } else {
            pinky.setDirection(reverse(pinky.getDirection()));
        }
        pinky.update();
    }

    public void moveBlinky(int i, int j) {
        blinky.turnDir(grid, paci, pacj, i, j);
        if (blinky.getDirection() == Direction.UP && isSpotEmpty(grid, i, j - 1) && isSpotEmpty(grid, i - 1, j - 2) && isSpotEmpty(grid, i, j - 2)) {
            grid[i][j].setSpriteContained(empty);
            grid[i][j - 1].setSpriteContained(blinky);
        } else if (blinky.getDirection() == Direction.LEFT && isSpotEmpty(grid, i - 1, j) && isSpotEmpty(grid, i - 2, j) && isSpotEmpty(grid, i - 2, j - 1)) {
            grid[i][j].setSpriteContained(empty);
            grid[i - 1][j].setSpriteContained(blinky);
        } else if (blinky.getDirection() == Direction.DOWN && isSpotEmpty(grid, i, j + 1) && isSpotEmpty(grid, i - 1, j + 1)) {
            grid[i][j].setSpriteContained(empty);
            grid[i][j + 1].setSpriteContained(blinky);
        } else if (blinky.getDirection() == Direction.RIGHT && isSpotEmpty(grid, i + 1, j) && isSpotEmpty(grid, i + 1, j - 1)) {
            grid[i][j].setSpriteContained(empty);
            grid[i + 1][j].setSpriteContained(blinky);
        } else {
            blinky.setDirection(reverse(blinky.getDirection()));
        }
        blinky.update();
    }

    public void movePac(int i, int j) {
        if (pacDir == Direction.UP) {
            //System.out.println("Got to if statement");
            if (isSpotEmpty(grid,i - 1,j - 2) && isSpotEmpty(grid,i,j - 2)) {
                pacman.setDirection(Direction.UP);
                grid[i][j].setSpriteContained(empty);
                grid[i][j - 1].setSpriteContained(pacman);
                pacman.update();
	    }
        } else if (pacDir == Direction.DOWN) {
            if (isSpotEmpty(grid,i,j + 1) && (j + 1 != grid[0].length - 2) && isSpotEmpty(grid,i - 1,j + 1)) {
                pacman.setDirection(Direction.DOWN);
                grid[i][j].setSpriteContained(empty);
                grid[i][j + 1].setSpriteContained(pacman);
                pacman.update();
            }
        } else if (pacDir == Direction.LEFT) {
            if (isSpotEmpty(grid,i - 2,j) && (i - 2 != -1) && isSpotEmpty(grid,i - 2,j - 1)) {
                pacman.setDirection(Direction.LEFT);
                grid[i][j].setSpriteContained(empty);
                grid[i - 1][j].setSpriteContained(pacman);
                pacman.update();
            }
        } else if ((pacDir == Direction.RIGHT)) {
            if (isSpotEmpty(grid,i + 1,j) && (i + 1 != grid.length - 2) && isSpotEmpty(grid,i + 1,j - 1)) {
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
            SoundEffects.playCredit();
            pause(2000);
            SoundEffects.playEntry();
            pause(5000);
            SoundEffects.startAlarm();
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
