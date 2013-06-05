/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package pacman_final;

import java.awt.BorderLayout;
import javax.swing.JFrame;

/**
 *
 * @author matt
 */
public class GUIFrame extends JFrame {

    static boolean gameReset = false;

    public GUIFrame() {
        this.setSize(500, 498);
        this.setResizable(false);
        this.setTitle("Pacman- Matt and Harry Final Project");
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        while (true) {
            GUIPanel p = new GUIPanel();
            this.setLayout(new BorderLayout());
            this.add(p, BorderLayout.CENTER);
            this.setVisible(true);
            p.requestFocus();
            while (gameReset == false) {
                pause(10);
                p.repaint();
            }
            p = null;
            gameReset = false;
        }
    }

    public static void reset() {
        gameReset = true;
    }

    public void pause(long time) {
        try {
            Thread.sleep(time);
        } catch (Exception e) {
        }
    }
}
