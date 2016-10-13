/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.logic;

import java.awt.event.ActionListener;
import ships.sinktheships.gui.Inputs;
import ships.sinktheships.gameobjects.Player;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;  //timer.start();
import ships.sinktheships.gameobjects.Coordinate;
import ships.sinktheships.gameobjects.Ship;
import ships.sinktheships.gui.DrawOnScreen;
import ships.sinktheships.gui.Frame;

/**
 * Luokka huolehtii pelin koordinoinnista, erilaisten peliluuppien välillä
 * siirtymisestä jne.
 *
 * @author jambo
 */
public class GameLoops {

    private Inputs input;
    private ArrayList<Player> players;
    private int numberOfPlayers;
    private ShipPlacer shipPlacer = new ShipPlacer();
    private Frame window;
    private DrawOnScreen drawOnScreen;

   // private Timer timer = new Timer(20, this);

    //public Timer timer = new Timer(30, this); laitetaan viittaan controlls luokkaan
    /**
     * Konstruktori.
     */
    public GameLoops() {

        this.input = new Inputs();
        //tässä paranee alustaa esim Inputs, DrawBackground,frame jne
        window = new Frame(Options.getWindowSizeX(), Options.getWindowSizeY());
        window.addInputToFrame(this.input);  //refactorointii ehk...
        this.players = new ArrayList();
        this.numberOfPlayers = 2;
        drawOnScreen = new DrawOnScreen();
        window.addOutputToFrame(drawOnScreen);

        try {
            gameLoopStartScreen(); //eka looppi alkaa
        } catch (InterruptedException ex) {
        }
    }

    private void gameLoopStartScreen() throws InterruptedException {
        //vaihtoehtoina exit, options -> gameLoopOptions() (yllärii), start new game, jos viimeisin niin 
        //ohjaa gameLoopPlayerName()

        while (true) {

            drawOnScreen.clearAllLines();
            drawOnScreen.addLineOfText("Welcome, my friends!", 1);
            drawOnScreen.addLineOfText("q to quit", 2);
            drawOnScreen.addLineOfText("o for options", 3);
            drawOnScreen.addLineOfText("enter to start", 4);
            if (input.isPressedEnter()) {
                drawOnScreen.newLineOfText("Calculating", 2);
                Thread.sleep(700);
                gameLoopPlayerName();
            } else if (input.isPressedQ()) {
                drawOnScreen.newLineOfText("Quitting!!!", 2);
                Thread.sleep(700);
                window.quit();
            } else if (input.isPressedO()) {
                drawOnScreen.newLineOfText("Options", 1);
                drawOnScreen.addLineOfText("Coming soon", 2);
                Thread.sleep(700);
            }
            Thread.sleep(300);
        }

    }

    private void gameLoopPlayerName() throws InterruptedException {

        for (int i = 0; i < numberOfPlayers; i++) {
            String playerName = playerEntersHerName();
            Player player = new Player(playerName, i); //pelaajat tulee säilöä
            players.add(player);
            while (true) {
                drawOnScreen.newLineOfText("I thank thee, press enter!", 1);
                if (input.isPressedEnter()) {
                    Thread.sleep(50);
                    break;
                }
                Thread.sleep(50);
            }
        }
        chooseWhoStarts();
    }

    private String playerEntersHerName() throws InterruptedException {
        String name = input.userInputString(drawOnScreen, "Enter thy name dear player!");
        return name;
    }

    private void attackToCoordinate() {
        //kysytään koordinaattia
        drawOnScreen.newLineOfText("Where you want to attack?", 5);
        drawOnScreen.newLineOfText("Type x-coordinate and Enter, then y-coordinate and Enter.", 6);

    }

    private void chooseWhoStarts() throws InterruptedException {
        Random random = new Random();
        int randomInt = random.nextInt(2); //palauttaa 0 tai 1
        drawOnScreen.newLineOfText("We beg you, ghosts of randomness", 1);
        Thread.sleep(1100);
        drawOnScreen.addLineOfText("Tell us who shall be the first.", 2);
        Thread.sleep(1400);
        drawOnScreen.addLineOfText("The ghosts have spoken.", 4);
        Thread.sleep(1500);
        drawOnScreen.newLineOfText("", 1);
        Thread.sleep(900);

        gameLoopShipPlacement(randomInt);
    }

    private int getNextPlayersId(int playerId) {
        return (playerId - 1) * (playerId - 1);
    }

//    private void gameLoopFirstTurn(int playerId) throws InterruptedException { //suoritetaan ennen ekan pelaajan vuoroa, kysyy haluaako aloittaa vuoron
//        drawOnScreen.newLineOfText("It is your turn, " + players.get(playerId).getName() + "!", 1);
//        drawOnScreen.addLineOfText("Press enter when ready", 2);
//        waitForEnter();
//        drawOnScreen.newLineOfText("", 1); //tyhjä ruutu..
//
//        
//    }
    private void waitForEnter() throws InterruptedException {
        while (true) {
            if (input.isPressedEnter()) {
                break;
            }
            Thread.sleep(60);
        }
    }

    private void gameLoopTurnChangeScreen(int playerId) throws InterruptedException { //suoritetaan ennen pelaajan currentPlayer vuoroa, kysyy haluaako aloittaa vuoron
        drawOnScreen.clearScreen();
        drawOnScreen.newLineOfText("It is your turn, " + players.get(playerId).getName() + "!", 1);
        drawOnScreen.addLineOfText("Press enter when ready", 2);
        waitForEnter();
        drawOnScreen.newLineOfText("", 1); //tyhjä ruutu..
        Thread.sleep(1000);
        gameLoopTurn(playerId);
        //ei valmis
    }

    private void gameLoopShipPlacement(int playerId) throws InterruptedException {
        Player playerX = players.get(playerId);
        drawOnScreen.newLineOfText("Ready to place your ships, " + playerX.getName() + "?", 1);
        drawOnScreen.addLineOfText("Press enter when ready", 2);
        waitForEnter();

        giveAshipForPlayerToPlace(playerX);
        drawOnScreen.newLineOfText("Thy ships are ready, " + playerX.getName() + "!", 1);
        drawOnScreen.addLineOfText("Press enter when ready", 2);
        waitForEnter();

        if (players.get(getNextPlayersId(playerId)).hasPlacedShips()) {
            gameLoopTurnChangeScreen(getNextPlayersId(playerId));
        }

        gameLoopShipPlacement(getNextPlayersId(playerId));
    }

    private void giveAshipForPlayerToPlace(Player playerX) throws InterruptedException {
        int shipType = 1;  //tätä kasvatetaan loopissa ja näin saadaan kaikki erilaiset laivat
        while (!(playerX.hasPlacedShips())) {
            Ship ship = new Ship(shipType, 1, 1, 0);
            shipPlacer.addShip(ship);
            playerPlacesHerShip(playerX, ship);
            playerX.addToFleet(ship);
            shipType++;
        }
    }

    private void playerPlacesHerShip(Player playerX, Ship ship) throws InterruptedException {
        ArrayList<Ship> allShips = new ArrayList<>();
        allShips.add(ship);
        if (!playerX.getShips().isEmpty()) {
            allShips.addAll(playerX.getShips());
        }
        while (true) {
            drawOnScreen.newLineOfText("Place your" + ship.getName() + "!", 1);
            drawOnScreen.placeShipsOnGrid(allShips, ship.getName());

            moveShip();
            if (input.isPressedEnter()) {
                if (shipPlacer.shipFitsInWithPlayersOtherShipsAndGrid(playerX)) {
                    break;
                } else {
                    drawOnScreen.newLineOfText("can't anchor thy ship there!", 6);
                    Thread.sleep(300);
                }
            }
            Thread.sleep(60);
        }
    }

    private void moveShip() {
        if (input.isPressedUp()) {
            shipPlacer.moveShipIntoThisDirection(1, false);
        } else if (input.isPressedDown()) {
            shipPlacer.moveShipIntoThisDirection(-1, false);
        } else if (input.isPressedLeft()) {
            shipPlacer.moveShipIntoThisDirection(-1, true);
        } else if (input.isPressedRight()) {
            shipPlacer.moveShipIntoThisDirection(1, true);
        } else if (input.isPressedR()) {
            shipPlacer.changeShipAngle();
        }
    }

    private void gameLoopTurn(int playerId) throws InterruptedException {
        Coordinate coordinate;
        Player currentPlayer = players.get(playerId);
        Player adversary = players.get(getNextPlayersId(playerId));

        //selectAttackingShip();
        //seuraava on väliaikainen metodi..ei tuliteta laivalla vaan vaan tulitetaan...
        while (true) {
            coordinate = input.userInputCoordinate(drawOnScreen, currentPlayer, adversary);
            int xcoord = coordinate.getxCoordinate();
            int ycoord = coordinate.getxCoordinate();
            if (xcoord > 10 || xcoord < 1 || ycoord < 1 || ycoord > 10) {
                please(currentPlayer);
                continue;
            }

            if (!(adversary.addShot(coordinate))) {
                //eli ei oli jo ammuttu tähän kohtaan
                please(currentPlayer);
            } else {
                break;
            }

        }
        drawOnScreen.drawBattle(currentPlayer, adversary);

        Thread.sleep(1700);

//muuttuja et montako kertaa ammuttu...
        if (adversary.allShipsDestroyed()) { //tarkistus onko toinen voittanut
            gameLoopWinnerFound(currentPlayer);
        }
        playerId = getNextPlayersId(playerId);
        gameLoopTurnChangeScreen(playerId);
    }

    private void please(Player player) throws InterruptedException {
        drawOnScreen.clearScreen();
        drawOnScreen.newLineOfText(player.getName() + " please.", 5);
        Thread.sleep(800);
    }

    private void attack(Coordinate coordinate, Player player) throws InterruptedException {
        //drawOnScreen.addLineOfText("Attacking", 2);
        if (!player.addShot(coordinate)) {
            //eli ei oli jo ammuttu tähän kohtaan
        }

        Thread.sleep(100);
    }

//    private void selectAttackingShip() {
//        Ship activeShip;
//        output.printOnScreen("Select a ship with wich to attack!", 1);
//        //valitaan laiva..
//        //activeShip=...
//        //attack(activeShip);
//    }
//    private void attack(Ship ship) {
//        output.printOnScreen("Type coordinates where to attack! enter to submit "
//                + "a coordinate, first x, then y.", 1);
//        //kuunnellaan mitä kirjoitetaan
//        //laiva voi tulittaa niin monesti kuin sillä on kokoa..
//        int roundsToFire = ship.getSize();
//
//    }
    private void gameLoopWinnerFound(Player player) throws InterruptedException {
        drawOnScreen.newLineOfText("You have won the game, " + player.getName() + "!", 3);
        waitForEnter();
        //ilmoittaa voittajan ja kysyy halutaanko ottaa uusi peli jolloin palataan
        //gameLoopTurnChangeScreen():n ja kone arpoo aloittajan
        //muuten palataan gameLoopStartScreen() tilaan.
    }

}
