/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gui;

/**
 * Luokan olioilla on pala tekstiä ja rivinumero.
 *
 * @author jambo
 */
public class Text {

    private String text;
    private int lineNumber;

    /**
     * Luo uuden teksti-olion.
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

    /**
     * Muutta nykyisen tekstisisällön.
     *
     * @param text uusi tekstisisältö
     */
    public void updateText(String text) {
        this.text = text;
    }

    /**
     * Muuttaa rivinumeron.
     *
     * @param number rivinumero
     */
    public void updateLineNumber(int number) {
        this.lineNumber = number;
    }
}
