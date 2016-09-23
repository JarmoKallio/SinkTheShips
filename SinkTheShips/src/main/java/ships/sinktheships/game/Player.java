/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.game;

import java.util.ArrayList;

/**
 *
 * @author jambo
 */
public class Player {

    private ArrayList fleet;   //tänne säilötään pelaajan laivat
    private String name;          //täsä tarkkaa et inputit tulee oikein...että otetaan
    private int turnsPlayed;      //vastaan vain kirjaimia
    private int id;
    private int shipsInFleet;

    public Player(String name, int id) {
        this.name = name;
        this.fleet = new ArrayList();
        this.id = id;
        this.turnsPlayed = 0;
        this.shipsInFleet = 0;
    }

    public void addToFleet(Ship ship) {

        if (this.shipsInFleet < Options.getMaxFleetSize()) {
            fleet.add(ship);
            this.shipsInFleet++;
        }

    }

    public ArrayList getShips() {
        return fleet;
    }

    public String getName() {
        return name;
    }

    public boolean hasPlacedShips() {
        if (this.shipsInFleet == Options.getMaxFleetSize()) {
            return true;
        }

        return false;
    }

    public int getId() {
        return id;
    }

}
