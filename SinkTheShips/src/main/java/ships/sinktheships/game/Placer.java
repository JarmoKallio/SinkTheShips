/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.game;

import ships.sinktheships.gui.Inputs;
import ships.sinktheships.gui.Output;

/**
 *
 * @author jambo
 */
public class Placer {

    Player player;
    Inputs input;
    Output output;
    Ship ship;

    public Placer(Player player, Inputs input, Output output) {
        this.player = player;
        this.input = input;
        this.output = output;

    }

    private void getPlayersShips(Player player) {

    }

    public void addShip(Ship ship) {
        this.ship = ship;
    }

    public void moveShipAroundUntilEnter() {
        int coordX = 0;
        int coordY = 0;
        //pelaajalta kysytään koordinaattipari ja laiva asetetaan siihen jos 
        //siinä ei ole muita laivoja
        while (true) {
            output.clearScreen();
            output.drawShipsOnGrid(this.ship, this.player.getShips());
            output.printOnScreen("Move ship around with arrows. Rotate with r. Enter when ready!");

            if (input.keyHasBeenPressedUp()) {
                moveShipAndCheck(1, false);
            } else if (input.keyHasBeenPressedDown()) {
                moveShipAndCheck(-1, false);
            } else if (input.keyHasBeenPressedLeft()) {
                moveShipAndCheck(-1, true);
            } else if (input.keyHasBeenPressedRight()) {
                moveShipAndCheck(1, true);
            } else if (input.keyHasBeenPressedR()) {
                this.ship.changeAngle();
                if (!inRange(this.ship)) {
                    this.ship.unChangeAngle();
                }
            }

            output.clearScreen();
            output.drawShipsOnGrid(this.ship, this.player.getShips());

            if (input.enterHasBeenPressed()) {
                break;
            }

            this.player.addToFleet(this.ship);

        }

    }

    public void moveShipAndCheck(int amount, boolean xDir) {
        if (xDir) {
            this.ship.newCoordX(this.ship.getSternX() + amount);
            if (!inRange(this.ship)) {
                this.ship.newCoordX(this.ship.getSternX() - amount);
            }
        } else {
            this.ship.newCoordY(this.ship.getSternY() + amount);
            if (!inRange(this.ship)) {
                this.ship.newCoordY(this.ship.getSternY() - amount);
            }
        }

    }

    public boolean checkForOtherShips() {
        return true;
    }

    public boolean inRange(Ship ship) {
        int rangeXmin = Options.getXmin();
        int rangeXmax = Options.getXmax();
        int rangeYmin = Options.getYmin();
        int rangeYmax = Options.getYmax();
        int[] coverageX = this.ship.getCoverageX();
        int[] coverageY = this.ship.getCoverageY();
        if (check(rangeYmin, rangeYmax, coverageY)) {
            if (check(rangeXmin, rangeXmax, coverageX)) {
                return true;
            }
        }
        return false;

    }

    private boolean check(int rangeMin, int rangeMax, int[] coverage) {
        for (int x : coverage) {
            if (x > rangeMax || x < rangeMin) {
                return false;
            }
        }

        return true;
    }

}
