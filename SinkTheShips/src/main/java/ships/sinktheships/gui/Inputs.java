/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gui;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import ships.sinktheships.logic.Logic;
import ships.sinktheships.logic.Screen;

/**
 * Luokkaa käytetään näppäimistön kuunteluun, siinä toteutetaan käyttäjän
 * syötteiden käsittely niin, ettei muiden luokkien tarvitse implementoida näitä
 * juttuja.
 *
 * @author jambo
 */
public class Inputs implements KeyListener {

    private Logic logiikka;
    private Screen activeLoop;

    /**
     * Luo uuden imputs olion joka siis kuuntelee näppäimistöä.
     *
     * @param logiikka käytetty logiikaluokan ilmentymä, jonka avulla tehdään
     * päivityksiä kulloiseenkin screeniin
     */
    public Inputs(Logic logiikka) {
        //tähän timer looppi joka käy muuttamassa logiikan tilaa kun jotain kirjoitetaan jne.
        //logiikka (Logic) ei tiedä eri kontrolleista, tässä eli Inputs-luokassa
        //päätetään mitä logiikka-luokan toimintoa milläkin näppäilyllä käytetään..
        this.logiikka = logiikka;
        activeLoop = logiikka.getActiveScreen();

    }

    @Override
    public void keyPressed(KeyEvent e) {

        activeLoop = logiikka.getActiveScreen();

        int keyCode = e.getKeyCode();
        char keyChar = e.getKeyChar();

        numbersOrLettersPressed(keyChar);

        if (keyCode == KeyEvent.VK_ENTER) {
            activeLoop.enterPressed();
        }
        if (keyCode == KeyEvent.VK_BACK_SPACE) {
            activeLoop.backSpacePressed();
        }
        if (keyCode == KeyEvent.VK_Q) {
            if (activeLoop.qPressedToQuit()) {
                Frame.quit();
            }
            activeLoop.qPressed();
        }
        if (keyCode == KeyEvent.VK_O) {
            activeLoop.oPressed();
        }
        if (keyCode == KeyEvent.VK_R) {
            activeLoop.rPressed();
        }
        if (keyCode == KeyEvent.VK_DOWN) {
            activeLoop.downPressed();
        }

        if (keyCode == KeyEvent.VK_UP) {
            activeLoop.upPressed();
        }

        if (keyCode == KeyEvent.VK_LEFT) {
            activeLoop.leftPressed();
        }

        if (keyCode == KeyEvent.VK_RIGHT) {
            activeLoop.rightPressed();
        }

        logiikka.updateActiveScreen();
    }

    private void numbersOrLettersPressed(char key) {
        char newTypedKey = key;
        //tarkastetaan et vaan numeroita tai kirjaimia
        String accepted = " 1234567890qwertyuiopåasdfghjklöäzxcvbnmQWERTYUIOPÅASDFGHJKLÖÄZXCVBNMüÜ";
        if (accepted.contains(newTypedKey + "")) {
            activeLoop.keyTyped(newTypedKey + "");
        }
        String numberInput = "1234567890";
        if (numberInput.contains(newTypedKey + "")) {
            activeLoop.numberTyped(newTypedKey + "");
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

}
