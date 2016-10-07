/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.logic;

import ships.sinktheships.gameobjects.Player;
import ships.sinktheships.gameobjects.Ship;
import java.util.ArrayList;

/**
 * Gameloops -luokan apuluokka, pitää huolta laivojen asettelusta
 * koordinaatistoon niin, etteivät laivat päädy päällekäin tai koordinaatiston
 * ulkopuolelle.
 *
 *
 * @author jambo
 */
public class ShipPlacer {

    private Ship ship;

    private int rangeXmin = Options.getXmin();
    private int rangeXmax = Options.getXmax();
    private int rangeYmin = Options.getYmin();
    private int rangeYmax = Options.getYmax();

    void addShip(Ship ship) {
        this.ship = ship;
    }

    /**
     * Metodi muuttaa laivan koordinaattitietoja eli siirtää sitä.
     *
     * @param amount liikutettava määrä negatiivinen tai positiivinen
     * @param moveAlongXaxis true liikuttaa x ja false y akselia pitkin
     */
    public void moveShipIntoThisDirection(int amount, boolean moveAlongXaxis) {
        if (moveAlongXaxis) {
            this.ship.newSternXcoordinate(this.ship.getSternXcoordinate() + amount);
        } else {
            this.ship.newSternYcoordinate(this.ship.getSternYcoordinate() + amount);
        }
    }

    /**
     * Tarkistusmetodi jolla nähdään että laiva ei mene vanhojen laivojen päälle
     * tai koordinaatiston ulkopuolelle.
     *
     * @param player pelaaja jonka laivastoon verrataan
     * @return boolean arvo, sopiiko laiva muiden laivojen sekaan ja ruudukolle
     */
    public boolean shipFitsInWithPlayersOtherShipsAndGrid(Player player) {
        if (shipIsInRange() && shipDoesNotCollideWithPlayersOtherShips(player)) {
            return true;
        }
        return false;
    }

    /**
     * Kertoo onko laiva peliruudukon sisällä.
     *
     * @return boolean kyllä tai ei
     */
    public boolean shipIsInRange() {
        if (isCoverageInRange(rangeYmin, rangeYmax, this.ship.getCoverageY())) {
            if (isCoverageInRange(rangeXmin, rangeXmax, this.ship.getCoverageX())) {
                return true;
            }
        }
        return false;

    }

    private boolean isCoverageInRange(int rangeMin, int rangeMax, int[] coverage) {
        for (int x : coverage) {
            if (x > rangeMax || x < rangeMin) {
                return false;
            }
        }
        return true;
    }

    private boolean shipDoesNotCollideWithPlayersOtherShips(Player player) {
        //pitää tarkistaa/ladata pelaajan muut laivat ja tarkistaa että yhteensopiva
        ArrayList<Ship> playersShips = player.getShips();
        if (playersShips.isEmpty()) {
            return true;
        }
        for (Ship x : playersShips) {
            int size = ship.getSize();
            for (int index = 0; index < size; index++) {
                if (x.isThisShipHere(ship.getCoverageX()[index], ship.getCoverageY()[index])) {
                    return false;
                }
            }
        }
        return true;
    }

    void changeShipAngle() {
        this.ship.changeShipAngle();
    }

    /**
     * Palauttaa ShipPlacerissä parhaillaan olevan laivan.
     *
     * @return paluuarvona laiva
     */
    public Ship getShip() {
        return ship;
    }

}
