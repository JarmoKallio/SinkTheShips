/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

/**
 * Luokkaa käytetään näppäimistön kuunteluun, siinä toteutetaan käyttäjän
 * syötteiden käsittely niin, ettei muiden luokkien tarvitse implementoida näitä
 * juttuja.
 *
 * @author jambo
 */
public class Inputs implements KeyListener {

    private char recentlyTypedKey = '_';
    private boolean pressedEnter = false;
    private boolean pressedBackSpace = false;
    private boolean pressedQ = false;
    private boolean pressedO = false;
    private boolean pressedR = false;
    private boolean pressedDown = false;
    private boolean pressedUp = false;
    private boolean pressedLeft = false;
    private boolean pressedRight = false;
    private boolean somethingTypedAndNotUsed = false;

    /**
     * Palauttaa juuri painetun näppäimen char-arvon.
     *
     * @return näppäity arvo
     */
    public char getPressedKey() {
        somethingTypedAndNotUsed = false;
        char returnValue = recentlyTypedKey;
        zeroKeyValues();
        return returnValue;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        int keyCode = e.getKeyCode();
        if (keyCode == KeyEvent.VK_ENTER) {
            zeroKeyValues();
            pressedEnter = true;
        }
        if (keyCode == KeyEvent.VK_BACK_SPACE) {
            zeroKeyValues();
            pressedBackSpace = true;
        }
        if (keyCode == KeyEvent.VK_Q) {
            zeroKeyValues();
            pressedQ = true;
        }

        if (keyCode == KeyEvent.VK_O) {
            zeroKeyValues();
            pressedO = true;
        }
        if (keyCode == KeyEvent.VK_R) {
            zeroKeyValues();
            pressedR = true;
        }

        if (keyCode == KeyEvent.VK_DOWN) {
            zeroKeyValues();
            pressedDown = true;
        }

        if (keyCode == KeyEvent.VK_UP) {
            zeroKeyValues();
            pressedUp = true;
        }

        if (keyCode == KeyEvent.VK_LEFT) {
            zeroKeyValues();
            pressedLeft = true;
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
            zeroKeyValues();
            pressedRight = true;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

        char newTypedKey = e.getKeyChar();
        //tarkastetaan et vaan numeroita tai kirjaimia
        String accepted = " 1234567890qwertyuiopåasdfghjklöäzxcvbnmQWERTYUIOPÅASDFGHJKLÖÄZXCVBNMüÜ";
        if (accepted.contains(newTypedKey + "")) {
            this.recentlyTypedKey = newTypedKey;
            somethingTypedAndNotUsed = true;
        }
    }

    /**
     * Saadaan tieto, odottaako jokin hyväksyttävä näppäinmenpainallus käyttöä.
     *
     * @return boolean arvo
     */
    public boolean isSomethingTypedAndNotUsed() {
        return somethingTypedAndNotUsed;
    }

    public boolean isPressedBackSpace() {
        if (pressedBackSpace) {
            zeroKeyValues();
            return !pressedBackSpace;
        }
        return false;
    }

    public boolean isPressedDown() {
        if (pressedDown) {
            zeroKeyValues();
            return !pressedDown;
        }
        return false;
    }

    public boolean isPressedEnter() {
        if (pressedEnter) {
            zeroKeyValues();
            return !pressedEnter;
        }
        return false;
    }

    public boolean isPressedLeft() {
        if (pressedLeft) {
            zeroKeyValues();
            return !pressedLeft;
        }
        return false;
    }

    public boolean isPressedO() {
        if (pressedO) {
            zeroKeyValues();
            return !pressedO;
        }
        return false;
    }

    public boolean isPressedQ() {
        if (pressedQ) {
            zeroKeyValues();
            return !pressedQ;
        }
        return false;
    }

    public boolean isPressedR() {
        if (pressedR) {
            zeroKeyValues();
            return !pressedR;
        }
        return false;
    }

    public boolean isPressedRight() {
        if (pressedRight) {
            zeroKeyValues();
            return !pressedRight;
        }
        return false;
    }

    public boolean isPressedUp() {
        if (pressedUp) {
            zeroKeyValues();
            return !pressedUp;
        }
        return false;
    }

    private void zeroKeyValues() {
        pressedEnter = false;
        pressedBackSpace = false;
        pressedQ = false;
        pressedO = false;
        pressedR = false;
        pressedDown = false;
        pressedUp = false;
        pressedLeft = false;
        pressedRight = false;
        recentlyTypedKey = '_';
        somethingTypedAndNotUsed = false;
    }
}
