/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.logic;

import ships.sinktheships.gui.Inputs;
import ships.sinktheships.gui.Output;
import ships.sinktheships.gameobjects.Player;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;  //timer.start();
import ships.sinktheships.gameobjects.Ship;

/**
 * Luokka huolehtii pelin koordinoinnista, erilaisten peliluuppien välillä
 * siirtymisestä jne.
 *
 * @author jambo
 */
public class GameLoops {

    private Inputs input;
    private Output output;
    private ArrayList<Player> players;
    private int numberOfPlayers;
    private ShipPlacer shipPlacer;

    //public Timer timer = new Timer(30, this); laitetaan viittaan controlls luokkaan
    /**
     *
     */
    public GameLoops() {
        gameLoopStartScreen();
        //tässä paranee alustaa esim Inputs, Output,
        this.input = new Inputs();
        this.output = new Output();
        this.players = new ArrayList();
        this.numberOfPlayers = 2;
    }

    /**
     *
     * @param args
     */
    public static void main(String[] args) {
        //   GameLoops ajo = new GameLoops();  //aloittaa ohjelman...

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

    private void gameLoopPlayerName() {
        for (int i = 0; i < numberOfPlayers; i++) {

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

    private int getNextPlayersId(int playerId) {
        return (playerId - 1) * (playerId - 1);
    }

    private void gameLoopFirstTurn(int playerId) { //suoritetaan ennen ekan pelaajan vuoroa, kysyy haluaako aloittaa vuoron
        output.printOnScreen("Its your turn," + players.get(playerId).getName()
                + "! Press enter when ready.");
        input.pressedEnter();
        output.clearScreen();
        gameLoopShipPlacement(playerId);
    }

    private void gameLoopTurnChangeScreen(int playerId) { //suoritetaan ennen pelaajan playerX vuoroa, kysyy haluaako aloittaa vuoron
        output.clearScreen();
        output.printOnScreen("Its your turn," + players.get(playerId).getName()
                + "! Press enter when ready.");
        input.pressedEnter();
        output.clearScreen();
        //ei valmis
    }

    private void gameLoopShipPlacement(int playerId) {
        Player playerX = players.get(playerId);
        giveAshipForPlayerToPlace(playerX);
        output.clearScreen();
        if (players.get(getNextPlayersId(playerId)).hasPlacedShips()) {
            gameLoopTurn(getNextPlayersId(playerId));
        }
        gameLoopShipPlacement(getNextPlayersId(playerId));
    }

    private void giveAshipForPlayerToPlace(Player playerX) {
        int shipType = 1;  //tätä kasvatetaan loopissa ja näin saadaan kaikki erilaiset laivat
        while (!playerX.hasPlacedShips()) {
            Ship ship = new Ship(shipType, 0, 0, 0);
            shipPlacer.addShip(ship);
            playerPlacesHerShip(playerX, ship);
            playerX.addToFleet(ship);
            shipType++;
        }
    }

    private void playerPlacesHerShip(Player playerX, Ship ship) {
        while (true) {
            output.clearScreen();
            output.drawShipsOnGrid(ship, playerX.getShips()); //ensimmäinen meinaa laivaa jota ollaan asettamassa, 
            //sitä ei ole vielä lisätty pelaajan laivastoon
            output.printOnScreen("Move ship around with arrows. Rotate with r. Enter when ready!");
            moveShip();
            if (input.enterHasBeenPressed()) {
                if (shipPlacer.shipFitsInWithPlayersOtherShipsAndGrid(playerX)) {
                    playerX.addToFleet(ship);
                    break;
                } else {
                    output.printOnScreen("Cant anchor ship there!");
                }
            }
        }
    }

    private void moveShip() {
        if (input.keyHasBeenPressedUp()) {
            shipPlacer.moveShipIntoThisDirection(1, false);
        } else if (input.keyHasBeenPressedDown()) {
            shipPlacer.moveShipIntoThisDirection(-1, false);
        } else if (input.keyHasBeenPressedLeft()) {
            shipPlacer.moveShipIntoThisDirection(-1, true);
        } else if (input.keyHasBeenPressedRight()) {
            shipPlacer.moveShipIntoThisDirection(1, true);
        } else if (input.keyHasBeenPressedR()) {
            shipPlacer.changeShipAngle();
        }
    }

    private void gameLoopTurn(int playerId) {
        //tämän sisällä ajetaan turn cange..
        int pId = playerId;
        while (true) {
            gameLoopTurnChangeScreen(pId);

            output.drawAdersaryGrid(); //argumentiksi player idn mukainen player
            output.drawPlayerGrid();
            selectAttackingShip();

            //tähän tulee mitä vuoron aikana tehdään
//            if() { //tarkistus onko toinen voittanut
//                gameLoopWinnerFound(pId);
//            }
            pId = getNextPlayersId(pId);
        }
    }

    private void selectAttackingShip() {
        Ship activeShip;
        output.printOnScreen("Select a ship with wich to attack!");
        //valitaan laiva..
        //activeShip=...
        //attack(activeShip);
    }

    private void attack(Ship ship) {
        output.printOnScreen("Type coordinates wherer to attack! enter to submit "
                + "a coordinate, first x, then y.");
        //kuunnellaan mitä kirjoitetaan
        //laiva voi tulittaa niin monesti kuin sillä on kokoa..
        int roundsToFire = ship.getSize();

    }

    private void gameLoopWinnerFound(int playerId) {
        //ilmoittaa voittajan ja kysyy halutaanko ottaa uusi peli jolloin palataan
        // gameLoopTurnChangeScreen():n ja kone arpoo aloittajan
        //muuten palataan gameLoopStartScreen() tilaan.
    }
}
