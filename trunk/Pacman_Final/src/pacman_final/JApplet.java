package pacman_final;

import java.io.*;
import java.awt.*;
import java.util.*;
import java.applet.*;
import javax.swing.*;
import java.awt.event.*;
import javax.swing.event.*;

public class PacmanApplet extends JApplet
{
    public static void main(String[] args)
    {
      EventQueue.invokeLater(new Runnable() {

         public void run() {
             JFrame frame = new Jframe();
             frame.setTitle("Harry and Matt Final Project");
             frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
             frame.setSize(500, 498);
             frame.setResizable(false);
             frame.setTitle("Pacman- Matt and Harry Final Project");         
             frame.setLocationRelativeTo(null);         
             frame.setDefaultCloseOperation(EXIT_ON_CLOSE);
             GUIPanel p = new GUIPanel();    
             frame.setLayout(new BorderLayout());         
             frame.add(p, BorderLayout.CENTER);         
             frame.setVisible(true);         
             p.requestFocus();         
             while(true){             
               try{Thread.sleep(10);}catch(Exception e){}             
               p.repaint();         
             }
             frame.pack();
         }
      });
    }    
@Override
    public void init() {
        System.out.println("Initializing");
        EventQueue.invokeLater(new Runnable() {

            public void run() {
                initContainer(Subway.this);
            }
        });

    }

    @Override
    public void destroy() {
        System.out.println("That's all, folks...");
    }





 }










