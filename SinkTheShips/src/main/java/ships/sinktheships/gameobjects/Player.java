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
    private String name;          //täsä tarkkaa et inputit tulee oikein...että otetaan
    private int turnsPlayed;      //vastaan vain kirjaimia
    private int id;
    private int shipsInFleet;

    /**
     *
     *
     * @param name
     * @param id
     */
    public Player(String name, int id) {
        this.name = name;
        this.fleet = new ArrayList<Ship>();
        this.id = id;
        this.turnsPlayed = 0;
        this.shipsInFleet = 0;
    }

    /**
     * Lisää laivan pelaajan laivasto-ArrayListiin.
     *
     * @param ship
     */
    public void addToFleet(Ship ship) {

        if (this.shipsInFleet < Options.getMaxFleetSize()) {
            fleet.add(ship);
            this.shipsInFleet++;
        }

    }

    /**
     *
     * @return
     */
    public ArrayList getShips() {
        return fleet;
    }

    /**
     *
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * Tarkistaa onko pelaaja jo asettanut kaikki laivansa ns pelilaudalle eli
     * koordinaatistoon.
     *
     * @return
     */
    public boolean hasPlacedShips() {
        if (this.shipsInFleet == Options.getMaxFleetSize()) {
            return true;
        }

        return false;
    }

    /**
     *
     * @return
     */
    public int getId() {
        return id;
    }

}
