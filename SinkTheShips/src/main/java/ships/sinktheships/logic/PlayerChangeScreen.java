/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.logic;

import ships.sinktheships.gui.Text;

/**
 * Screen-luokan perivä logiikkaluokka, jota käytetään kun halutaan vaihtaa
 * pelaajaa. Näytölle ilmestyy kehotus seuraavalle pelaajalle aloittaa vuoronsa.
 * Kuten kaikki Screen-luokan perivät luokat, Inputs kutsuu tätä näppäimistön
 * käytön jälkeen ja Outputs hakee piirtokomennon.
 *
 * @author jambo
 */
public class PlayerChangeScreen extends Screen {

    private Logic logic;
    private Screen returnScreen = this;
    private Screen nextScreen;
    private DrawCommand command;

    /**
     * Uusi pelaajanvaihto-olio.
     *
     * @param logic päälogiikka
     * @param nextScreen Screen johon seuraavaksi siirrytään
     */
    public PlayerChangeScreen(Logic logic, Screen nextScreen) {
        this.logic = logic;
        this.nextScreen = nextScreen;
        this.command = new DrawCommand();
        command.addText(new Text("Its your turn now " + this.logic.getActivePlayer().getName() + "!", 1));
        //command.addText(new Text("o for options", 3)); //jos toteutetaan niin oma loop
        command.addText(new Text("press enter", 3));
        command.setDrawBackround(true);
        command.setDrawText(true);

    }

    /**
     * Antaa seuraavaksi ajettavan screenin.
     *
     * @return Screen
     */
    @Override
    public Screen getReturnScreen() {
        return returnScreen;
    }

    /**
     * Antaa uusimman piirtokomennon.
     *
     * @return DrawCommanad
     */
    @Override
    public DrawCommand getDrawCommand() {
        return this.command;
    }

    /**
     * Seuraavaksi screeniksi asetetaan konstruktotissa annettu Screen.
     * Käytännössä nyt laivan valinta.
     */
    @Override
    public void enterPressed() {
        this.returnScreen = this.nextScreen;
    }

}
