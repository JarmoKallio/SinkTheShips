/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.logic;

import java.util.ArrayList;
import ships.sinktheships.gameobjects.Player;

/**
 * Ohjelman päälogiikkaluokka, johon tallennetaan ja josta haetaan uusin
 * screeni, kuten AttackScreen tai VictoryScreen. Myös pelaajat tallennetaan
 * tähän päälogiikkaan.
 *
 * @author jambo
 */
public class Logic {

    private boolean stateChanged = true;

    private ArrayList<Player> players = new ArrayList<>();
    private Player activePlayer;

    private int numberOfPlayers;

    private Screen activeScreen = new StartScreen(this); //tästä alkaa

    /**
     * Lisää uuden pelaajan.
     *
     * @param name pelaajan nimi
     * @param index pelaajan indeksi, sijainti players-ArrayListissä
     */
    public void addNewPlayer(String name, int index) {
        Player newPlayer = new Player(name, index);
        players.add(newPlayer);
        setActivePlayer(newPlayer);
    }

    /**
     * Tätä käyttää Inputs-luokka jälkeen. Kun näppäimistöä painetaan, pyytää
     * Inputs Logic-luokan olioa päivittämään activeScreenin. stateChanged
     * kertoo Outputs -piirtoluokalle, että jokin on muuttunut ja kuva
     * piirretään mahdollisesti uudestaan.
     */
    public void updateActiveScreen() {
        this.activeScreen = this.activeScreen.getReturnScreen();
        this.stateChanged = true;
    }

    /**
     * Palauttaa aktiivisen Screen -olion, eli esimerkiksi PlayerChangeScreen.
     *
     * @return Screen
     */
    public Screen getActiveScreen() {
        return this.activeScreen;
    }

    /**
     * Palauttaa pelaajan, jonka vuoro on pelata.
     *
     * @return Player
     */
    public Player getActivePlayer() {
        return this.activePlayer;
    }

    /**
     * Asettaa pelaajan activePlayer:iksi.
     *
     * @param player aktiivinen pelaaja, joka siis tekee siirtoja jne
     */
    public void setActivePlayer(Player player) {
        this.activePlayer = player;
    }

    /**
     * Palauttaa listalta seuraavan pelaajan, jos kutsutaan kahdesti saadaan
     * sama pelaaja kuin mitä oli alussa.
     *
     * @return Player
     */
    public Player getNextPlayer() {
        if (this.activePlayer.getId() == 0) {
            return players.get(1);
        }
        return players.get(0);
    }

    /**
     * Kertoo onko players -pelaajalista tyhjä.
     *
     * @return boolean
     */
    public boolean areThereAnyPlayers() {
        if (this.players.isEmpty()) {
            return false;
        }
        return true;
    }

    /**
     * Käytetään kun peli on loppunut ja halutaan aloittaa uusi peli.
     */
    public void reset() {
        stateChanged = true;
        players = new ArrayList<>();

    }

}
