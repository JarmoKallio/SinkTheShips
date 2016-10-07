/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gui;

/**
 * Pieni luokka jonka olioilla on pala tekstiä ja rivinumero.
 *
 * @author jambo
 */
public class Text {

    private String text;
    private int lineNumber;

    /**
     * Konstruktori.
     *
     * @param text lisättävä teksti
     * @param lineNumber haluttu rivinumero
     */
    public Text(String text, int lineNumber) {
        this.text = text;
        this.lineNumber = lineNumber;
    }

    /**
     * Getteri.
     *
     * @return rivinumeron palautus
     */
    public int getLineNumber() {
        return lineNumber;
    }

    /**
     * Getteri.
     *
     * @return tekstipätkän, String, palautus
     */
    public String getText() {
        return text;
    }
}
