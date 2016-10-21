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
        centerFrame();
        frame.setResizable(false);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

    }

    private void centerFrame() {
        Dimension xy = Toolkit.getDefaultToolkit().getScreenSize();
        this.frame.setLocation(xy.width / 2 - this.frame.getWidth() / 2, xy.height / 2 - this.frame.getHeight() / 2);
    }

    /**
     * Viimeisenä kutsuttava metodi, joka asettaa JFramen näkyväksi.
     */
    public void ready() {
        //frame.pack();
        frame.setVisible(true);
    }

    /**
     * Lisää JFrameen KeyListener-olion.
     *
     * @param listener edellämainittu keylistener -olio
     */
    public void addInputToFrame(KeyListener listener) {
        this.frame.addKeyListener(listener);  //tarkist tämä, pitäskö import imput..
        frame.setVisible(true);
    }

    /**
     * Lisää JFrameen Outputs-luokan olion joka vastaa piirtämisestä ja extends
     * JPanel.
     *
     * @param textPanel piirtoluokan olio
     * @return true kun suoritettu, tämä ei kuitenkään ehkä pidä paikkansa mutta
     * metodi saa pysyä tälläisenä
     */
    public boolean addOutputToFrame(Outputs textPanel) {
        this.frame.add(textPanel);
        return true;
    }

    /**
     * Lopettaa koko ohjelman ajon.
     */
    public static void quit() {
        System.exit(0);
    }
}
