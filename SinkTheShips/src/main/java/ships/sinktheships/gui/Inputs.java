/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import ships.sinktheships.gameobjects.Coordinate;
import ships.sinktheships.gameobjects.Player;

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
    private boolean recordXcoordinate = true;

    private boolean somethingHasBeenTyped = false;

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

    public boolean somethingHasBeenTyped() { //käytetään loopeissa odottamisen apuna
        somethingHasBeenTyped = false;
        return true;
    }

    @Override
    public void keyPressed(KeyEvent e) {
        somethingHasBeenTyped = true;
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
        somethingHasBeenTyped = true;
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

    public String userInputString(DrawOnScreen drawOnScreen, String message) throws InterruptedException {
        String string = "_";
        while (true) {
            drawOnScreen.newLineOfText(message, 1);
            drawOnScreen.addLineOfText(string, 3);
            if (this.isPressedEnter()) {
                break;
            } else if (this.isPressedBackSpace()) {
                string = shortenString(string);
                continue;
            } else if (this.isSomethingTypedAndNotUsed()) {
                char potentialValue = this.getPressedKey();
                if (potentialValue != '_') {
                    if (string.length() < 15) {
                        string += potentialValue;
                    }
                }
            }
            Thread.sleep(70);
        }
        return string;
    }

    public Coordinate userInputCoordinate(DrawOnScreen drawOnScreen, Player currentPlayer, Player adversary) throws InterruptedException {
        Coordinate coord = new Coordinate(0, 0);
        int number = 0;
        String stringX = "" + 0;
        String stringY = "" + 0;
        String current = "";
        while (true) {

            drawOnScreen.drawBattle(currentPlayer, adversary);
            if (recordXcoordinate) {
                drawOnScreen.addLineOfText("X: < " + stringX + " >  Y:  " + stringY + "  ", 4);
            } else {
                drawOnScreen.addLineOfText("X:   " + stringX + "    Y: <" + stringY + " >", 4);
            }

            if (this.isPressedEnter()) {

                coord = new Coordinate(Integer.parseInt(shortenLongString(stringX)), Integer.parseInt(shortenLongString(stringY)));
                break;

            } else if (this.isPressedLeft()) {
                recordXcoordinate = true;
            } else if (this.isPressedRight()) {
                recordXcoordinate = false;
            }
            if (this.isPressedBackSpace()) {
                if (recordXcoordinate) {
                    stringX = shortenString(stringX);
                } else {
                    stringY = shortenString(stringY);
                }
            } else if (this.isSomethingTypedAndNotUsed()) {
                int potentialValue = this.getPressedKeyIfNumber();
                if (recordXcoordinate) {
                    stringX = stringX + potentialValue;
                } else {
                    stringY = stringY + potentialValue;
                }
            }

            Thread.sleep(70);

        }
        return coord;
    }

    private String shortenLongString(String string) {
        if (string.length() >= 4) {
            string = string.substring(0, 3);
        }
        return string;
    }

    private String shortenString(String string) {
        if (string.length() == 1) {
            return "";
        } else if (string.length() > 1) {
            return string.substring(0, string.length() - 1);
        }
        return "";  //jos pituus esim nolla
    }

    private int getPressedKeyIfNumber() {
        somethingTypedAndNotUsed = false;
        char returnValue = getPressedKey();
        String accepted = "1234567890";

        if (accepted.contains(returnValue + "")) {
            zeroKeyValues();
            return Integer.parseInt(returnValue + "");
        }

        return 0;
    }

}
