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
 * Screen-luokan perivä logiikkaluokka, jota käytetään kun pelaaja asettaa
 * laivojaan koordinaatistoonsa.
 *
 * @author jambo
 */
class ShipPlacementScreen extends Screen {

    private Logic logic;
    private ShipPlacer placer;

    private DrawCommand command;
    private Screen returnScreen = this;
    private Player currentPlayer;

    public ShipPlacementScreen(Logic logic, int shipType) {
        this.logic = logic;
        this.currentPlayer = logic.getActivePlayer();

        command = new DrawCommand();
        command.addText(new Text("Place thy ships, " + currentPlayer.getName() + "!", 1));
        command.addText(new Text("Move with arrows, rotate with r, enter when ready.", 2));
        command.setDrawBackround(true);
        command.setDrawText(true);
        command.setDrawShipsAndGrid(true);
        command.setDrawGridNumbers(true);
        command.setPlayer(currentPlayer);

        placer = new ShipPlacer();
        placer.addShip(new Ship(shipType, 1, 1, 0));
        command.setPlacer(placer);
        command.setDrawShipPlacer(true);
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

        if (placer.shipFitsInWithPlayersOtherShipsAndGrid(this.currentPlayer)) {

            if (currentPlayer.hasPlacedShips() == false) {
                this.currentPlayer.addToFleet(placer.getShip());
                if (this.currentPlayer.getShips().size() < 5) {
                    this.returnScreen = new ShipPlacementScreen(logic, placer.getShip().getShipType() + 1);
                } else if (!logic.getNextPlayer().hasPlacedShips()) {
                    logic.setActivePlayer(logic.getNextPlayer());
                    this.returnScreen = new PlayerChangeScreen(logic, new ShipPlacementScreen(logic, 1));

                } else {
                    logic.setActivePlayer(logic.getNextPlayer());
                    this.returnScreen = new PlayerChangeScreen(logic, new SelectShipScreen(logic));
                }
            }
        }
    }

    @Override
    public void upPressed() {
        placer.moveShipIntoThisDirection(1, false);
    }

    @Override
    public void downPressed() {
        placer.moveShipIntoThisDirection(-1, false);
    }

    @Override
    public void leftPressed() {
        placer.moveShipIntoThisDirection(-1, true);
    }

    @Override
    public void rightPressed() {
        placer.moveShipIntoThisDirection(1, true);
    }

    @Override
    public void rPressed() {
        placer.changeShipAngle();
    }

}
