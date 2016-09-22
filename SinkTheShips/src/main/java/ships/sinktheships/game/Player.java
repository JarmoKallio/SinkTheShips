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
    private boolean shipsPlaced;

    public Player(String name, int id) {
        this.name = name;
        this.fleet = new ArrayList();
        this.id = id;
        this.turnsPlayed = 0;
        this.shipsPlaced = false;
    }

    public void addToFleet(Ship ship) {
        fleet.add(ship);
    }

    public ArrayList getShips() {
        return fleet;
    }

    public String getName() {
        return name;
    }

    public void hasPlacedShips() {
        shipsPlaced = true;
    }

    public boolean getShipsPlaced() {
        return shipsPlaced;
    }

    public int getId() {
        return id;
    }

}
