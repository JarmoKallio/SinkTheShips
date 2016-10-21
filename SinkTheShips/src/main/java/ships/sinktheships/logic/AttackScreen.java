/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.logic;

import ships.sinktheships.gameobjects.Coordinate;
import ships.sinktheships.gameobjects.Player;
import ships.sinktheships.gameobjects.Ship;
import ships.sinktheships.gameobjects.Shot;
import ships.sinktheships.gui.Text;

/**
 * Logiikka-olio jota käytetään kun peli on tilanteessa, jossa pelaaja on
 * valinnut hyökkäävän laivan ja seuraavaksi valitsee hyökkäyskohteen
 * vaastustajan koordinaatistosta.
 *
 * @author jambo
 */
public class AttackScreen extends Screen {

    private Logic logic;
    private DrawCommand command;
    private Screen returnScreen = this;
    private Player currentPlayer;
    private Player adversary;

    private Ship selectedShip;
    private int shotsRemaining;
    private boolean firedAtLeastOnce = false;
    private Coordinate currentTargetCoordinate;

    /**
     * Luo uuden screenin ja astaa uuteen DrawCommand piirtokäskyyn joukon
     * asetuksia. Olioon ladataan päälogiikasta, Logic, ajantasainen pelaaja ja
     * vastustaja.
     *
     * @param logic käytetty päälogiikka
     * @param selectedShip pelaajan valitsema laiva
     */
    public AttackScreen(Logic logic, Ship selectedShip) {
        this.logic = logic;
        this.currentPlayer = logic.getActivePlayer();
        this.adversary = logic.getNextPlayer();
        this.selectedShip = selectedShip;
        this.shotsRemaining = this.selectedShip.getSize() - this.selectedShip.getDamages();
        this.currentTargetCoordinate = new Coordinate(5, 5);
        this.command = new DrawCommand();

        command.addText(new Text("Battle has begun! Its " + currentPlayer.getName() + "'s turn.", 1));
        command.addText(new Text("Choose target with arrows and press enter to fire.", 2));
        command.addText(new Text("The more you shoot the more probably your ship's", 3));
        command.addText(new Text("location gets revealed!", 4));
        command.addText(new Text("Press q to end turn.", 5));
        command.addText(new Text(shotsRemaining + " shots remaining.", 7));
        command.setDrawBackround(true);
        command.setDrawText(true);
        command.setDrawShipsAndGrid(true);
        command.setDrawGridNumbers(true);
        command.setPlayer(currentPlayer);
        command.setAdversary(adversary);
        command.setDrawShots(true);
        command.setAdversary(adversary);
        command.setPlayer(currentPlayer);
        command.sethighlightSelectedShip(true);
        command.setSelectedShip(this.selectedShip);
        command.setDrawTargetCoordinate(true);
        command.setTargetCoordinate(currentTargetCoordinate);
        command.setDrawRadar(true);

    }

    private void fireInThisLocation(Coordinate coordinate) {
        if (shotsRemaining > 0) {
            if (!adversary.coordinateUsed(coordinate)) {
                boolean b = adversary.isHereAship(coordinate);
                Coordinate coord = new Coordinate(coordinate.getxCoordinate(), coordinate.getyCoordinate());
                adversary.getShotsOnThisOnesGrid().add(new Shot(coord, b));

                shotsRemaining = shotsRemaining - 1;
                this.currentPlayer.getRadar().shipHasShot();
                firedAtLeastOnce = true;
                if (adversary.allShipsDestroyed()) {
                    this.returnScreen = new VictoryScreen(logic, command);
                }
                command.addText(new Text(shotsRemaining + " shots remaining.", 7));
                command.setParametersChanged(true);
            }
        }
    }

    /**
     * Palauttaa uusimman Screenin.
     *
     * @return Screen
     */
    @Override
    public Screen getReturnScreen() {
        return returnScreen;
    }

    /**
     * Palauttaa talletetun piirtokäskyn.
     *
     * @return DrawCommand piirtokäsky
     */
    @Override
    public DrawCommand getDrawCommand() {
        return this.command;
    }

    /**
     * Aloittaa tulituksen.
     */
    @Override
    public void enterPressed() {
        fireInThisLocation(currentTargetCoordinate);

    }

    /**
     * Jos pelaaja on jo kerran tulittanut, voi tällä antaa vuoron toiselle.
     */
    @Override
    public void qPressed() {
        if (firedAtLeastOnce) {
            logic.setActivePlayer(adversary);
            this.returnScreen = new PlayerChangeScreen(logic, new SelectShipScreen(logic));

        } else {
            command.addText(new Text("You must attack at least once.", 7));
            command.setParametersChanged(true);
        }
    }

    private void moveTargetCoordinate(boolean addToCoordinate, boolean xCoordinate) {
        Coordinate coordinate = this.currentTargetCoordinate;
        int x = coordinate.getxCoordinate();
        int y = coordinate.getyCoordinate();

        if (addToCoordinate) {
            if (xCoordinate) {
                coordinate.setxCoordinate(previousPosition(x));
            } else {
                coordinate.setyCoordinate(previousPosition(y));
            }
        } else if (xCoordinate) {
            coordinate.setxCoordinate(nextPosition(x));
        } else {
            coordinate.setyCoordinate(nextPosition(y));
        }
        command.setParametersChanged(true);

    }

    /**
     * Siirtää tähtäintä vasemmalle.
     */
    @Override
    public void leftPressed() {
        moveTargetCoordinate(true, true);

    }

    /**
     * Siirtää tähtäintä oikealle.
     */
    @Override
    public void rightPressed() {
        moveTargetCoordinate(false, true);

    }

    /**
     * Siirtää tähtäintä alas.
     */
    @Override
    public void downPressed() {
        moveTargetCoordinate(true, false);

    }

    /**
     * Siirtää tähtäintä ylös.
     */
    @Override
    public void upPressed() {
        moveTargetCoordinate(false, false);

    }

    private int previousPosition(int x) {
        if (x != 1) {
            return x - 1;
        }
        return 10;
    }

    private int nextPosition(int x) {
        if (x != 10) {
            return x + 1;
        }
        return 1;
    }

}
