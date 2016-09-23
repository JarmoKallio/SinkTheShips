/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.logic;

/**
 *
 * @author jambo
 */
import ships.sinktheships.gui.Inputs;
import ships.sinktheships.gui.Output;
import ships.sinktheships.game.Player;

import java.util.ArrayList;
import java.util.Random;

import javax.swing.Timer;
import ships.sinktheships.game.Placer;
import ships.sinktheships.game.Sea;
import ships.sinktheships.game.Ship;
//timer.start();

/**
 *
 * @author jambo
 */
public class Logic {

    private Inputs input;
    private Output output;
    private ArrayList<Player> players;
    private int nOfPlayers;

    //public Timer timer = new Timer(30, this); laitetaan viittaan controlls luokkaan
    public Logic() {
        gameLoopStartScreen();
        //tässä paranee alustaa esim Inputs, Output,
        this.input = new Inputs();
        this.output = new Output();
        this.players = new ArrayList();
        this.nOfPlayers = 2;
    }

    public static void main(String[] args) {
        //   Logic ajo = new Logic();  //aloittaa ohjelman...

        System.out.println("hellou");

    }

    private void gameLoopStartScreen() {
        //vaihtoehtoina exit, options -> gameLoopOptions() (yllärii), start new game, jos viimeisin niin 
        //ohjaa gameLoopPlayerName()
        System.out.println("Hello hey");
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
        int angle = 0;
        int type = 1;

        while (!x.hasPlacedShips()) {
            //pitää luoda ruudukko jne
            //jokaista laivatyyppiä yksi

            output.printOnScreen("Place a ship by pressing enter, move around with arrows,"
                    + "rotate with r.");
            //laiva aluksi ruudun keskellä, ei anna laittaa kahta laivaa päällekäin
            Placer placer = new Placer(x, input, output);
            Ship ship = new Ship(x.getName(), type, 0, 0, angle);
            placer.addShip(ship);
            placer.moveShipAroundUntilEnter();
            type++;
        }

        output.clearScreen();
        if (players.get((playerId - 1) * (playerId - 1)).hasPlacedShips()) {
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

            output.drawAdersaryGrid();
            output.drawPlayerGrid();

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
