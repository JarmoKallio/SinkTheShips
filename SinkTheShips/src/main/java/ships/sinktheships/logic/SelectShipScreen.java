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
 * Screen-luokan perivä logiikkaluokka, jonka avulla pelaaja valitsee laivan
 * koordinaatistostaan.
 *
 * @author jambo
 */
public class SelectShipScreen extends Screen {

    private static boolean shipSelected = false;

    private Logic logic;
    private DrawCommand command;
    private Screen returnScreen = this;
    private Player currentPlayer;
    private Player adversary;

    private Ship selectedShip;

    /**
     * Konstruktori. Luo uuden piirtokomennon ja asettaa muuttujille arvoja.
     *
     * @param logic päälogiikka
     */
    public SelectShipScreen(Logic logic) {
        this.logic = logic;
        this.currentPlayer = logic.getActivePlayer();
        this.adversary = logic.getNextPlayer();
        this.selectedShip = currentPlayer.getShips().get(0);

        command = new DrawCommand();
        command.addText(new Text("Battle has begun! Its " + currentPlayer.getName() + "'s turn.", 1));
        command.addText(new Text("Choose attacking ship with arrows and enter.", 2));
        command.addText(new Text("You can shoot as many times as your ship covers the grid.", 3));
        command.addText(new Text("This " + selectedShip.getName() + " can fire " + selectedShip.canFireXTimes() + " times.", 7));
        command.setDrawBackround(true);
        command.setDrawText(true);
        command.setDrawShipsAndGrid(true);
        command.setDrawGridNumbers(true);
        command.setPlayer(currentPlayer);
        command.setAdversary(adversary);
        command.setDrawShots(true);
        command.sethighlightSelectedShip(true);
        command.setSelectedShip(this.selectedShip);
        command.setDrawRadar(true);

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
        if (!this.selectedShip.isDestroyed()) {
            this.currentPlayer.addRadar(selectedShip);
            this.returnScreen = new AttackScreen(logic, this.selectedShip);
        } else {
            command.addText(new Text("This ship has ceased to exist.", 7));
            command.setParametersChanged(true);
        }

    }

    @Override
    public void leftPressed() {
        this.selectedShip = this.currentPlayer.getShips().get(previousIndex(selectedShip.getShipType() - 1));
        command.setSelectedShip(selectedShip);
        command.addText(new Text("This " + selectedShip.getName() + " can fire " + selectedShip.canFireXTimes() + " times.", 7));
        command.setParametersChanged(true);
    }

    @Override
    public void rightPressed() {
        this.selectedShip = this.currentPlayer.getShips().get(nextIndex(selectedShip.getShipType() - 1));
        command.setSelectedShip(selectedShip);
        command.addText(new Text("This " + selectedShip.getName() + " can fire " + selectedShip.canFireXTimes() + " times.", 7));
        command.setParametersChanged(true);
    }

    private int previousIndex(int x) {
        if (x != 0) {
            return x - 1;
        }
        return 4;
    }

    private int nextIndex(int x) {
        if (x != 4) {
            return x + 1;
        }
        return 0;
    }

}
