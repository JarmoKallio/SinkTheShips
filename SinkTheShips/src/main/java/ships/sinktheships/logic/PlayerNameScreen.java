/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.logic;

import ships.sinktheships.gui.Text;

/**
 * Screen-luokan perivä logiikkaluokka, jota käytetään kun halutaan pelaajan
 * antavan nimensä.
 *
 * @author jambo
 */
public class PlayerNameScreen extends Screen {

    private static int timesUsed = 0;
    private Logic logic;

    private DrawCommand command;
    private Screen returnScreen = this;
    private String playerName = "_";
    private Text playerNameText = new Text("_", 3);

    /**
     * Konstruktori, alustaa piirtokomennon.
     *
     * @param logic päälogiikka
     */
    public PlayerNameScreen(Logic logic) {
        this.logic = logic;
        this.command = new DrawCommand();

        command.addText(playerNameText);
        command.setDrawBackround(true);
        command.setDrawText(true);
        command.addText(new Text("Enter thy name, who is first!", 1));

        if (logic.areThereAnyPlayers()) {
            command.addText(new Text("Now enter thy name, that who is second!", 1));
        }

    }

    /**
     * Antaa seuraavan Screenin.
     *
     * @return Screen
     */
    @Override
    public Screen getReturnScreen() {
        this.playerNameText.updateText(this.playerName);
        return returnScreen;
    }

    /**
     * Antaa piirtokomennon.
     *
     * @return DrawCommand
     */
    @Override
    public DrawCommand getDrawCommand() {
        return this.command;
    }

    /**
     * Jos luokkaa käytetty nolla kertaa luo uuden pelaaja olion, jos on
     * käytetty aikaisemmin, seuraava Screen on laivojen asettelu-Screeni.
     */
    @Override
    public void enterPressed() {
        if (timesUsed == 0) {
            logic.addNewPlayer(playerName, 0);
            this.returnScreen = new PlayerNameScreen(logic);
            timesUsed = 1;
        } else if (timesUsed == 1) {

            logic.addNewPlayer(playerName, 1);
            this.returnScreen = new ShipPlacementScreen(logic, 1);
        }
    }

    /**
     * Lisää pelajan nimeen annetun String-pätkän.
     *
     * @param key String
     */
    @Override
    public void keyTyped(String key) { //vaan kirjaimet ja numerot kelpaa..
        this.playerName += key;
    }

    /**
     * Poistaa pelaajan nimestä viimeisimmän merkin.
     */
    @Override
    public void backSpacePressed() {
        this.playerName = removeLastCharacterOfString(this.playerName);

    }

    private String removeLastCharacterOfString(String string) {
        if (string.isEmpty()) {
            return string;
        } else if (string.length() == 1) {
            return "";

        } else {
            return string.substring(0, string.length() - 2); //vika index ==length-1->siitä väh 1
        }

    }

    /**
     * Nollaan muuttujan. Tätä käytetään, kun aloitetaan kokonaan uusi peli
     * aloitusruudusta.
     */
    public static void resetTimesUsed() {
        timesUsed = 0;
    }

}
