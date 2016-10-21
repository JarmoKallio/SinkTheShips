/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.logic;

import ships.sinktheships.gameobjects.Player;
import ships.sinktheships.gameobjects.Ship;
import ships.sinktheships.gui.Text;

/**
 * Screen-luokan perivä logiikkaluokka, jota käytetään kun jompikumpi pelaaja on
 * voittanut pelin.
 *
 * @author jambo
 */
public class VictoryScreen extends Screen {

    private Logic logic;
    private DrawCommand command;
    private Screen returnScreen = this;
    private Player currentPlayer;
    private Player adversary;

    private Ship selectedShip;

    /**
     * Konstruktori.
     *
     * @param logic päälogiikka
     * @param drawcommand piirtokomento, sama kuin edeltävässä Screenissä jotta
     * laivat säilyvät ruudulla
     */
    public VictoryScreen(Logic logic, DrawCommand drawcommand) {
        this.logic = logic;
        this.currentPlayer = logic.getActivePlayer();
        this.adversary = logic.getNextPlayer();
        this.command = drawcommand;
        command.resetTexts();
        command.addText(new Text("Victory! " + this.currentPlayer.getName() + " has won the game.", 2));
        command.addText(new Text("Press enter to go back to menu.", 4));
        command.setPlayer(currentPlayer);
        command.setAdversary(adversary);
        command.setDrawShots(true);
        command.setAdversary(adversary);
        command.setPlayer(currentPlayer);
        command.sethighlightSelectedShip(false);
        command.setDrawTargetCoordinate(false);

    }

    @Override
    public Screen getReturnScreen() {
        return returnScreen;
    }

    @Override
    public DrawCommand getDrawCommand() {
        return this.command;
    }

    @Override
    public void enterPressed() {
        logic.reset();
        PlayerNameScreen.resetTimesUsed();
        this.returnScreen = new StartScreen(logic);
    }
}
