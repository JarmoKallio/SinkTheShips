/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gameobjects;

import ships.sinktheships.logic.Options;
import java.util.ArrayList;

/**
 * Pelaajat voivat tallentaa nimensä, muuten luokka huolehtii pelaajalle
 * kuuluvista laivoista, jne.
 *
 * @author jambo
 */
public class Player {

    private ArrayList<Ship> fleet;   //tänne säilötään pelaajan laivat
    private ArrayList<Shot> shotsOnThisOnesGrid;
    private String name;          //täsä tarkkaa et inputit tulee oikein...että otetaan
    private int turnsPlayed;      //vastaan vain kirjaimia
    private int id;
    private int shipsInFleet;

    /**
     * Luo pelaaja-olion.
     *
     * @param name Pelaajan itse valitsema nimi
     * @param id numero jolla pelaaja tunnistetaan. 0 tai 1
     */
    public Player(String name, int id) {
        this.name = name;
        this.fleet = new ArrayList<Ship>();
        this.shotsOnThisOnesGrid = new ArrayList<Shot>();
        this.id = id;
        this.turnsPlayed = 0;
        this.shipsInFleet = 0;
    }

    /**
     * Lisää laivan pelaajan laivasto-ArrayListiin.
     *
     * @param ship Laiva talletetaan pelaajan listaan
     */
    public void addToFleet(Ship ship) {

        if (this.shipsInFleet < Options.getMaxFleetSize()) {
            fleet.add(ship);
            this.shipsInFleet++;
        }

    }

    public ArrayList<Shot> getShotsOnThisOnesGrid() {
        return shotsOnThisOnesGrid;
    }

    /**
     * Paluuarvona pelaajan laivat sisältävä lista.
     *
     * @return palauttaa pelaajan laivat ArrayListissä
     */
    public ArrayList<Ship> getShips() {
        return fleet;
    }

    public String getName() {
        return name;
    }

    /**
     * Onko pelaaja asettanut laivansa. Tarkistaa onko pelaaja jo asettanut
     * kaikki laivansa ns pelilaudalle eli koordinaatistoon.
     *
     * @return kyllä tai ei riippuu onko laivat asetettu
     */
    public boolean hasPlacedShips() {
        if (this.shipsInFleet == Options.getMaxFleetSize()) {
            return true;
        }

        return false;
    }

    /**
     * Pelaajan idn palautus.
     *
     * @return pelaajan id
     */
    public int getId() {
        return id;
    }

    private int numOfShipsDestroyed() {
        int number = 0;

        for (Ship x : fleet) {
            if (x.isDestroyed()) {
                number++;
            }
        }

        return number;
    }

    public boolean allShipsDestroyed() {
        if (numOfShipsDestroyed() >= this.shipsInFleet) {
            return true;
        }
        return false;
    }

    public boolean addShot(Coordinate coordinate) {

        if (this.hasShipInThisCoordinate(coordinate)) {
            if (this.shotsOnThisOnesGrid.contains(new Shot(coordinate, true))) {
                return false;
            }
            this.shotsOnThisOnesGrid.add(new Shot(coordinate, true));

        } else {
            if (this.shotsOnThisOnesGrid.contains(new Shot(coordinate, false))) {
                return false;
            }
            this.shotsOnThisOnesGrid.add(new Shot(coordinate, false));
        }

        return true;
    }

    public boolean hasShipInThisCoordinate(Coordinate coordinate) {
        for (Ship x : this.fleet) {
            if (x.isThisShipHere(coordinate)) {
                int dam = x.getDamages();
                x.setDamages(dam + 1);
                return true;
            }
        }
        return false;
    }
}
