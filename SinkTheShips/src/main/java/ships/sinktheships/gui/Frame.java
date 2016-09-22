/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gui;

import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Toolkit;
import java.awt.Dimension;

/**
 *
 * @author jambo
 */
public class Frame {

    private JFrame frame;

    public Frame(String name, int xsize, int ysize) {
        this.frame = new JFrame(name);
        frame.setSize(xsize, ysize);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.addKeyListener (this); HUOM TÄRKEE muuten controllit ei toimi
        //frame.add(screen);
        centerFrame();
    }

    private void centerFrame() {
        Dimension xy = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation(xy.width / 2 - this.frame.getWidth() / 2, xy.height / 2 - this.frame.getHeight() / 2);
    }

}
