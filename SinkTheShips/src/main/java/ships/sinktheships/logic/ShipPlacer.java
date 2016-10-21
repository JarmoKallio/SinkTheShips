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
 * Apuluokka, jonka avulla tarkistetaan etteivät pelaajan laivat päädy
 * esimerkiksi päällekäin.
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

    /**
     * Lisää laivan.
     *
     * @param ship laiva
     */
    public void addShip(Ship ship) {
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
        return shipIsInRange() && shipDoesNotCollideWithPlayersOtherShips(player);
    }

    /**
     * Kertoo onko laiva peliruudukon sisällä.
     *
     * @return boolean kyllä tai ei
     */
    public boolean shipIsInRange() {

        if (!checkRange(this.ship.getCoverageX(), "X")) {
            return false;
        } else if (!checkRange(this.ship.getCoverageY(), "Y")) {
            return false;
        }
        return true;
    }

    private boolean checkRange(int[] coverage, String q) {
        for (int i : coverage) {

            if (i < 1 || i > 10) {  //toistaiseksi ruudukko on neliö
                return false;
            }
        }
        return true;
    }

    private boolean shipDoesNotCollideWithPlayersOtherShips(Player player) {
        ArrayList<Ship> playersShips = player.getShips();
        System.out.println(playersShips.size());
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
