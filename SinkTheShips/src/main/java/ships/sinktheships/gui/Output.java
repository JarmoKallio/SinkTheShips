/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import ships.sinktheships.gameobjects.Ship;

/**
 * Luokkaa käytetään yksinomaan antamaan käskyjä swing-käyttöliittymälle, kuten
 * piirtämään tekstiä ja kuvioita.
 *
 * @author jambo
 */
public class Output implements ActionListener {

    /**
     *
     * @param text
     */
    public void printOnScreen(String text) {
        //kirjoittanee ruudulle tekstiä

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    public void clearScreen() {
        //tyhjentää tekstit ruudulta    

    }

    /**
     *
     * @param ship
     * @param ships
     */
    public void drawShipsOnGrid(Ship ship, ArrayList ships) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    public void drawAdersaryGrid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     *
     */
    public void drawPlayerGrid() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
