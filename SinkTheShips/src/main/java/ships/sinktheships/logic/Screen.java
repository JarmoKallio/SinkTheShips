/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.logic;

/**
 * Logiikkaluokka, joka toimii niin, että Inputs- ja Outputs-luokista käydään
 * tekemässä Screeneihin muutoksia ja hakemassa tietoja.
 *
 * @author jambo
 */
public class Screen {

    /**
     * Yleensä palauttaa seuraavan halutun Screenin.
     *
     * @return normistiScreen
     */
    public Screen getReturnScreen() {
        return null;
    }

    /**
     * Palauttaa yleensä DrawCommand-piirtokomennon. Outputs kutsuu tätä.
     *
     * @return piirtokomento
     */
    public DrawCommand getDrawCommand() {
        return null;
    }

    /**
     * Mitä tehdään kun enteriä painettu. Inputs kutsuu tätä.
     */
    public void enterPressed() {

    }

    /**
     * mitä tehdään kun q painettu. Inputs kutsuu tätä.
     */
    public void qPressed() {

    }

    /**
     * Mitä tehdään kun vasen nuoli painettu. Inputs kutsuu tätä.
     */
    public void leftPressed() {

    }

    /**
     * Mitä tehdään kun oikea nuoli painettu. Inputs kutsuu tätä.
     */
    public void rightPressed() {

    }

    /**
     * Mitä tehdään kun nuoli ylös painettu. Inputs kutsuu tätä.
     */
    public void upPressed() {

    }

    /**
     * Mitä tehdään kun nuoli alas painettu. Inputs kutsuu tätä.
     */
    public void downPressed() {

    }

    /**
     * Mitä tehdään kun backspace painettu. Inputs kutsuu tätä.
     */
    public void backSpacePressed() {

    }

    /**
     * Mitä tehdään kun r painettu. Inputs kutsuu tätä.
     */
    public void rPressed() {

    }

    /**
     * Mitä tehdään kun o painettu. Inputs kutsuu tätä.
     */
    public void oPressed() {

    }

    /**
     * Mitä tehdään kun annettua merkkiä painettu. Inputs kutsuu tätä.
     *
     * @param key merkki
     */
    public void keyTyped(String key) {

    }

    /**
     * Mitä tehdään kun annettua numeromerkkiä painettu. Inputs kutsuu tätä.
     *
     * @param key merkki
     */
    public void numberTyped(String key) {

    }

    /**
     * Mitä tehdään kun q painettu. Inputs kutsuu tätä. Q:n painamisesta seuraa,
     * että kutsutaan sekä tätä että ainaisempaa missä mainittiin q.
     *
     * @return boolean
     */
    public boolean qPressedToQuit() {
        return false;
    }
}
