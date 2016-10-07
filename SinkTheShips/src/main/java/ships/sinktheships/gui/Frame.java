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
import java.awt.event.KeyListener;

/**
 * Hallinnoi Swing ikkunoita.
 *
 * @author jambo
 */
public class Frame {

    private JFrame frame;

    /**
     * Asettaa ikkunan koon ja pari muuta parametria.
     *
     * @param xsize ikkunan x-koko
     * @param ysize vastaavasti ikkunan y-koko
     */
    public Frame(int xsize, int ysize) {
        this.frame = new JFrame();

        frame.setSize(xsize, ysize);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        //frame.addKeyListener (this); HUOM TÄRKEE muuten controllit ei toimi
        centerFrame();
    }

    private void centerFrame() {
        Dimension xy = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation(xy.width / 2 - this.frame.getWidth() / 2, xy.height / 2 - this.frame.getHeight() / 2);
    }

    /**
     * Lisää JFrameen KeyListener-olion.
     *
     * @param listener edellämainittu keylistener -olio
     */
    public void addInputToFrame(KeyListener listener) {
        this.frame.addKeyListener(listener);  //tarkist tämä, pitäskö import imput..
    }

    /**
     * Lisää JFrameen DrawOnScreen-luokan olion joka vastaa piirtämisestä ja
     * extends JPanel.
     *
     * @param textPanel piirtoluokan olio
     */
    public void addOutputToFrame(DrawOnScreen textPanel) {
        this.frame.add(textPanel);
    }

    /**
     * Lopettaa koko ohjelman ajon.
     */
    public void quit() {
        System.exit(0);
    }
}
