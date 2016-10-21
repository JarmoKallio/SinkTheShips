/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gameobjects;

import ships.sinktheships.logic.Options;
import java.util.ArrayList;

/**
 * Luokkaan tallennetaan palaajan nimi, laivat, tutka, osumat ynnä muuta.
 *
 *
 * @author jambo
 */
public class Player {

    private ArrayList<Ship> fleet;   //tänne säilötään pelaajan laivat
    private ArrayList<Coordinate> coordinatesOfShots;
    private ArrayList<Shot> shotsOnThisOnesGrid;
    private String name;          //täsä tarkkaa et inputit tulee oikein...että otetaan
    private int turnsPlayed;      //vastaan vain kirjaimia
    private int id;
    private int shipsInFleet;
    private Radar radar = new Radar(); //tehdään Radar, joka ei tee mitään

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
        this.coordinatesOfShots = new ArrayList<Coordinate>();
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

    /**
     * Lisää tutka-olion pelaajalle.
     *
     * @param ship tutkan parametri
     */
    public void addRadar(Ship ship) {
        this.radar = new Radar(ship);
    }

    /**
     * Palauttaa pelaajalla kulloinkin olevan tutkan.
     *
     * @return tutka eli Radar-luokan ilmentymä
     */
    public Radar getRadar() {
        return this.radar;
    }

    /**
     * Palauttaa pelaajan koordinaatistoon tehdyt ammunnat, Shot.
     *
     * @return palauttaa kaikki Shot-oliot pelaajan niitä säilövästä
     * ArrayLististä
     */
    public ArrayList<Shot> getShotsOnThisOnesGrid() {
        return shotsOnThisOnesGrid;
    }

    /**
     * Palauttaa pelaajan laivat sisältävän listan.
     *
     * @return palauttaa pelaajan laivat ArrayListissä
     */
    public ArrayList<Ship> getShips() {
        return fleet;
    }

    /**
     * Palauttaa pelaajan nimen.
     *
     * @return pelaajan nimi
     */
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
     * Palauttaa pelaajan id:n.
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

    /**
     * Kertoo onko pelaajan kaikki laivat tuhottu.
     *
     * @return boolean onko laivat tuhottu
     */
    public boolean allShipsDestroyed() {
        if (numOfShipsDestroyed() >= this.shipsInFleet) {
            return true;
        }
        return false;
    }

    /**
     * Kertoo onko pelaajan listalla, joka säilöö ammunnankohde-koordinaatteja,
     * kyseistä koordinaattia.
     *
     * @param coordinate selvitettävä koordinaatti
     * @return boolean joka riippuu siitä löytyikö pelaajan listasta annettua
     * koordinaattia
     */
    public boolean coordinateUsed(Coordinate coordinate) {
        if (this.shotsOnThisOnesGrid.isEmpty()) {
            return false;
        }
        for (Shot shot : this.shotsOnThisOnesGrid) {
            Coordinate x = shot.getCoordinate();
            if (x.getxCoordinate() == coordinate.getxCoordinate() && x.getyCoordinate() == coordinate.getyCoordinate()) {
                return true;
            }
        }
        return false;
    }

    /**
     * Kertoo onko annetussa koordinaatissa pelaajan laivaa.
     *
     * @param coordinate annettu koordinaatti
     * @return boolean joka riippuu siitä löytyikö laivaa koordinaatista
     */
    public boolean isHereAship(Coordinate coordinate) {
        if (hasShipInThisCoordinate(coordinate)) {
            this.coordinatesOfShots.add(coordinate);
            return true;
        }
        this.coordinatesOfShots.add(coordinate);
        return false;

    }

    /**
     * Palauttaa listan koordinaatteja, joka pitää kirjaa ammunnoista pelaajan
     * koordinaatistoon.
     *
     * @return lista koordinaatteja
     */
    public ArrayList<Coordinate> getCoordinatesOfShots() {
        return coordinatesOfShots;
    }

    /**
     * Kertoo onko pelaajalla laivaa annetussa koordinaatissa.
     *
     * @param coordinate annettu koordinaatti
     * @return boolean, riippuu siitä löytyikö laivaa
     */
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
