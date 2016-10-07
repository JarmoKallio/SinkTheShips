/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.logic;

import ships.sinktheships.gui.Inputs;
import ships.sinktheships.gameobjects.Player;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.Timer;  //timer.start();
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
    private ShipPlacer shipPlacer;
    private Frame window;
    private DrawOnScreen drawOnScreen;

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
        String name = "_";
        while (true) {
            drawOnScreen.newLineOfText("Enter thy name dear player!", 1);
            drawOnScreen.addLineOfText(name, 3);
            if (input.isPressedEnter()) {
                break;
            } else if (input.isPressedBackSpace()) {
                if (name.length() == 1) {
                    name = "";
                } else if (name.length() > 1) {
                    name = name.substring(0, name.length() - 1);
                }
                continue;
            } else if (input.isSomethingTypedAndNotUsed()) {
                char potentialValue = input.getPressedKey();
                if (potentialValue != '_') {
                    name += potentialValue;
                }
            }
            Thread.sleep(70);
        }
        return name;
    }

    private void chooseWhoStarts() throws InterruptedException {
        Random random = new Random();
        int randomInt = random.nextInt(2); //palauttaa 0 tai 1
        gameLoopFirstTurn(randomInt);
    }

    private int getNextPlayersId(int playerId) {
        return (playerId - 1) * (playerId - 1);
    }

    private void gameLoopFirstTurn(int playerId) throws InterruptedException { //suoritetaan ennen ekan pelaajan vuoroa, kysyy haluaako aloittaa vuoron
        drawOnScreen.newLineOfText("It is your turn, " + players.get(playerId).getName() + "!", 1);
        drawOnScreen.addLineOfText("Press enter when ready", 2);
        waitForEnter();
        drawOnScreen.newLineOfText("", 1); //tyhjä ruutu..
  
        
        
    //    gameLoopShipPlacement(playerId);
    }

    private void waitForEnter() throws InterruptedException {
        while (true) {
            if (input.isPressedEnter()) {
                break;
            }
            Thread.sleep(60);
        }
    }

//    private void gameLoopTurnChangeScreen(int playerId) throws InterruptedException { //suoritetaan ennen pelaajan playerX vuoroa, kysyy haluaako aloittaa vuoron
//        drawOnScreen.newLineOfText("It is your turn, " + players.get(playerId).getName() + "!", 1);
//        drawOnScreen.addLineOfText("Press enter when ready", 2);
//        waitForEnter();
//        drawOnScreen.newLineOfText("", 1); //tyhjä ruutu..
//        gameLoopTurn(getNextPlayersId(playerId));
//        //ei valmis
//    }
//    private void gameLoopShipPlacement(int playerId) throws InterruptedException {
//        Player playerX = players.get(playerId);
//        giveAshipForPlayerToPlace(playerX);
//
//        if (players.get(getNextPlayersId(playerId)).hasPlacedShips()) {
//            gameLoopTurn(getNextPlayersId(playerId));
//        }
//        gameLoopShipPlacement(getNextPlayersId(playerId));
//    }

    private void giveAshipForPlayerToPlace(Player playerX) throws InterruptedException {
        int shipType = 1;  //tätä kasvatetaan loopissa ja näin saadaan kaikki erilaiset laivat
        while (!(playerX.hasPlacedShips())) {
            Ship ship = new Ship(shipType, 0, 0, 0);
            shipPlacer.addShip(ship);
            playerPlacesHerShip(playerX, ship);
            playerX.addToFleet(ship);
            shipType++;
        }
    }

    private void playerPlacesHerShip(Player playerX, Ship ship) throws InterruptedException {
        while (true) {
            drawOnScreen.newLineOfText("Move" + ship.getName() + "around with arrows.", 1);
            drawOnScreen.addLineOfText("Rotate with r. Enter when ready!", 2);
            ArrayList<Ship> allShips = new ArrayList<>();
            allShips.add(ship);
            if (!playerX.getShips().isEmpty()) {
                allShips.addAll(playerX.getShips());
            }
            drawOnScreen.placeShipsOnGrid(allShips);
            //ensimmäinen meinaa laivaa jota ollaan asettamassa, 
            //sitä ei ole vielä lisätty pelaajan laivastoon
            moveShip();
            if (input.isPressedEnter()) {
                if (shipPlacer.shipFitsInWithPlayersOtherShipsAndGrid(playerX)) {
                    playerX.addToFleet(ship);
                    break;
                } else {
                    drawOnScreen.newLineOfText("can't anchor thy ship there!", 6);
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

//    private void gameLoopTurn(int playerId) {
//        //tämän sisällä ajetaan turn cange..
//        int pId = playerId;
//        while (true) {
//            gameLoopTurnChangeScreen(pId);
//
//            output.drawAdersaryGrid(); //argumentiksi player idn mukainen player
//            output.drawPlayerGrid();
//            selectAttackingShip();
//    tähän tulee mitä vuoron aikana tehdään
//            if() { //tarkistus onko toinen voittanut
//                gameLoopWinnerFound(pId);
//            }
//            pId = getNextPlayersId(pId);
//        }
//    }
//    private void selectAttackingShip() {
//        Ship activeShip;
//        output.printOnScreen("Select a ship with wich to attack!",1);
//        //valitaan laiva..
//        //activeShip=...
//        //attack(activeShip);
//    }
//
//    private void attack(Ship ship) {
//        output.printOnScreen("Type coordinates wherer to attack! enter to submit "
//                + "a coordinate, first x, then y.",1);
//        //kuunnellaan mitä kirjoitetaan
//        //laiva voi tulittaa niin monesti kuin sillä on kokoa..
//        int roundsToFire = ship.getSize();
//
//    }
    private void gameLoopWinnerFound(int playerId) {
        //ilmoittaa voittajan ja kysyy halutaanko ottaa uusi peli jolloin palataan
        // gameLoopTurnChangeScreen():n ja kone arpoo aloittajan
        //muuten palataan gameLoopStartScreen() tilaan.
    }
}
