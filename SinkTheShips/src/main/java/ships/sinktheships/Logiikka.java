/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships;

/**
 *
 * @author jambo
 */
import ships.sinktheships.gui.Input;
import ships.sinktheships.gui.Output;
import ships.sinktheships.game.Player;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;
//timer.start();

/**
 *
 * @author jambo
 */
public class Logiikka {

    private Input input;
    private Output output;
    private ArrayList<Player> players;
    private int nOfPlayers;

    //public Timer timer = new Timer(30, this); laitetaan viittaan controlls luokkaan
    public Logiikka() {
        gameLoopStartScreen();
        //tässä paranee alustaa esim Input, Output,
        this.input = new Input();
        this.output = new Output();
        this.players = new ArrayList();
        this.nOfPlayers = 2;
    }

    public static void main(String[] args) {
        Logiikka ajo = new Logiikka();  //aloittaa ohjelman...

    }

    private void gameLoopStartScreen() {
        //vaihtoehtoina exit, options -> gameLoopOptions() (yllärii), start new game, jos viimeisin niin 
        //ohjaa gameLoopPlayerName()
        System.out.println("Hello");
//        output.clearScreen();
//        output.printOnScreen("Welcome, my friends! Press q to quit, o for options, enter to start game!");
        //kesken
    }

    private void gameLoopOptions() {

    }

    private void gameLoopPlayerName() {
        for (int i = 0; i < nOfPlayers; i++) {

            output.printOnScreen("Enter thee name dear player! Enter when ready");
            Player player = new Player(input.type(), i); //pelaajat tulee säilöä
            players.add(player);
            output.clearScreen();
            output.printOnScreen("I thank thee, press enter!");
            input.pressedEnter();
            output.clearScreen();
        }

        chooseWhoStarts();
    }

    private void chooseWhoStarts() {
        Random random = new Random();
        int randomInt = random.nextInt(2); //palauttaa 0 tai 1
        gameLoopFirstTurn(randomInt);
    }

    private void gameLoopFirstTurn(int playerId) { //suoritetaan ennen ekan pelaajan vuoroa, kysyy haluaako aloittaa vuoron
        output.printOnScreen("Its your turn," + players.get(playerId).getName()
                + "! Press enter when ready.");
        input.pressedEnter();
        output.clearScreen();
        gameLoopPlaceShips(playerId);
    }

    private void gameLoopTurnChange(int playerId) { //suoritetaan ennen pelaajan x vuoroa, kysyy haluaako aloittaa vuoron
        output.clearScreen();
        output.printOnScreen("Its your turn," + players.get(playerId).getName()
                + "! Press enter when ready.");
        input.pressedEnter();
        output.clearScreen();
        //ei valmis

    }

    private void gameLoopPlaceShips(int playerId) {
        Player x = players.get(playerId);
        //ei valmis
        //tähän le placement of ships, kehotus et enterillä valmis
        x.hasPlacedShips();
        input.pressedEnter();
        output.clearScreen();
        if (players.get((playerId - 1) * (playerId - 1)).getShipsPlaced()) {
            //toinenkin asettanut laivat

            gameLoopTurn((playerId - 1) * (playerId - 1));
        }

        gameLoopPlaceShips((playerId - 1) * (playerId - 1)); //antaa vastakkaisen playerId:n

    }

    private void gameLoopTurn(int playerId) {
        //tämän sisällä ajetaan turn cange..
        int pId = playerId;
        while (true) {
            gameLoopTurnChange(pId);
            //tähän tulee mitä vuoron aikana tehdään

//            if() { //tarkistus onko toinen voittanut
//                gameLoopWinnerFound(pId);
//            }
            pId = (pId - 1) * (pId - 1);
        }

    }

    private void gameLoopWinnerFound(int playerId) {
        //ilmoittaa voittajan ja kysyy halutaanko ottaa uusi peli jolloin palataan
        // gameLoopTurnChange():n ja kone arpoo aloittajan
        //muuten palataan gameLoopStartScreen() tilaan.

    }

}
