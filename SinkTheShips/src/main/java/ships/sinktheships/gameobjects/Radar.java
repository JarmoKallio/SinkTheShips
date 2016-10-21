/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gameobjects;

import java.util.Random;

/**
 * Luokan tehtävä on tarvittaessa näyttää pelaajan laivan perän sijainti
 * koordinaatistossa. Pelaajan tulittaessa laivallaan tämä luokka arpoo
 * näytetäänkä tulittaneen laivan sijainti. Tähän liittyy satunnaisuutta, jos
 * tulittaa vain kerran, ei koskaan näytetä, jos kuudesti, näytetään aina ja
 * muuten se on sattumasta kiinni.
 *
 * @author jambo
 */
public class Radar {

    private Coordinate shipLocation;
    private int timesShipHasShot = 0;
    private Ship ship;
    private boolean active;
    private boolean isVisible = false;

    /**
     * Tutkalle annetaan laiva. Tutka aktivoituu ja tallettaa laivan ja sen
     * perän sijainnin.
     *
     * @param ship annettu laiva
     */
    public Radar(Ship ship) {
        this.active = true;
        this.ship = ship;
        this.shipLocation = this.ship.getSternCoordinate();
    }

    /**
     * Vaihtoehtoinen konstruktori, jota kutsutaan ennen kuin pelaajilla on
     * vielä laivoja. Tarkoituksena vähentää if-operaatioita muista luokista.
     */
    public Radar() {
        this.active = false;
    }

    /**
     * Palauttaa tutkalle tallennetun jonkin laivan perän sijainnin.
     *
     * @return koordinaatti, laivan perän sijainti
     */
    public Coordinate getCoordinate() {
        return this.shipLocation;
    }

    /**
     * Käytetään kun laivalla on tulitettu, lisää todennäköisyyttä, että tutkaan
     * talletettu laiva tulee nähdyksi.
     */
    public void shipHasShot() {
        this.timesShipHasShot += 1;
        updateVisible();
    }

    private void updateVisible() {
        if (!isVisible) {
            int randomInt = getRandomNumberInRange(this.timesShipHasShot - 1, 5);
            long randomLong = randomInt / 5; //löytyy väliltä 0-1

            System.out.println("updating visible");
            isVisible = randomLong >= 0.5;
        }
    }

    /**
     * Kertoo näkyykö laiva tutkassa.
     *
     * @return boolean, tosi jos laiva näkyy
     */
    public boolean thereIsRadarImage() {
        return isVisible;
    }

    private int getRandomNumberInRange(int min, int max) {
        Random rand = new Random();
        return rand.nextInt((max - min) + 1) + min;
    }

    /**
     * Kertoo onko tutka aktiivinen, eli onko siihen talletettu laiva.
     *
     * @return boolean, tosi kun tutkaan talletettu laiva
     */
    public boolean isActive() {
        return active;
    }

}
