/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.logic;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ships.sinktheships.gameobjects.Player;
import ships.sinktheships.gameobjects.Ship;

/**
 *
 * @author jambo
 */
public class ShipPlacerTest {

    ShipPlacer placer = new ShipPlacer();

    public ShipPlacerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    @Test
    public void addShipWorks() {
        Ship testShip = new Ship(3, -1, 0, 0);
        placer.addShip(testShip);
        assertEquals(3, placer.getShip().getShipType());
    }

    @Test
    public void changeShipAngleWorks() {
        Ship testShip = new Ship(3, -1, 0, 0);
        placer.addShip(testShip);
        placer.changeShipAngle();
        assertEquals(90, testShip.getShipAngle());
    }

//    @Test
//    public void shipDoesNotCollideWithPlayersOtherShipsWorksWhenCollision() {
//        Player player = new Player("Keijo", 0);
//        Ship testShip1 = new Ship(3, 0, 0, 0);
//        Ship testShip2 = new Ship(4, 0, 0, 0);
//        player.addToFleet(testShip1);
//
//        placer.addShip(testShip2);
//        assertEquals(false, placer.shipFitsInWithPlayersOtherShipsAndGrid(player));
//        //olettaa että ruudukko ainakin 5x5
//    }

//    @Test
//    public void shipDoesNotCollideWithPlayersOtherShipsWorksWhenNoCollision() {
//        Player player = new Player("Keijo", 0);
//        Ship testShip1 = new Ship(3, 0, 0, 0);
//        Ship testShip2 = new Ship(4, 2, 0, 0);
//        player.addToFleet(testShip1);
//
//        placer.addShip(testShip2);
//        assertEquals(true, placer.shipFitsInWithPlayersOtherShipsAndGrid(player));
//        //olettaa että ruudukko ainakin 5x5
//    }

//    @Test
//    public void shipIsInRangeWorksWhenInRange() {
//        Player player = new Player("Keijo", 0);
//        Ship testShip = new Ship(3, 0, 0, 0);
//
//        placer.addShip(testShip);
//        assertEquals(true, placer.shipFitsInWithPlayersOtherShipsAndGrid(player));
//        //olettaa että ruudukko ainakin 5x5
//    }

//    @Test
//    public void shipIsInRangeWorksWhenNotInRange() {
//        Player player = new Player("Keijo", 0);
//        Ship testShip = new Ship(3, -4, 0, 0);
//
//        placer.addShip(testShip);
//        assertEquals(false, placer.shipFitsInWithPlayersOtherShipsAndGrid(player));
//        //olettaa että ruudukko ainakin 5x5
//    }
//
//    @Test
//    public void moveShipIntoThisDirectionWorksXdirPlus() {
//        Ship testShip = new Ship(3, -4, 0, 0);
//
//        placer.addShip(testShip);
//        placer.moveShipIntoThisDirection(2, true);
//        assertEquals(-2, testShip.getSternXcoordinate());
//        //olettaa että ruudukko ainakin 5x5
//    }
//
//    @Test
//    public void moveShipIntoThisDirectionWorksXdirMinus() {
//        Ship testShip = new Ship(3, -4, 0, 0);
//
//        placer.addShip(testShip);
//        placer.moveShipIntoThisDirection(-3, true);
//        assertEquals(-7, testShip.getSternXcoordinate());
//        //olettaa että ruudukko ainakin 5x5
//    }
//
//    @Test
//    public void moveShipIntoThisDirectionWorksYdirPlus() {
//        Ship testShip = new Ship(3, -4, 0, 0);
//
//        placer.addShip(testShip);
//        placer.moveShipIntoThisDirection(2, false);
//        assertEquals(2, testShip.getSternYcoordinate());
//        //olettaa että ruudukko ainakin 5x5
//    }
//
//    @Test
//    public void moveShipIntoThisDirectionWorksYdirMinus() {
//        Ship testShip = new Ship(3, -4, 0, 0);
//
//        placer.addShip(testShip);
//        placer.moveShipIntoThisDirection(-2, false);
//        assertEquals(-2, testShip.getSternYcoordinate());
//        //olettaa että ruudukko ainakin 5x5
//    }
//
//    @Test
//    public void shipFitsInWithPlayersOtherShipsAndGridWorksWhenTrue1() {
//        Player player = new Player("Keijo", 0);
//        Ship testShip = new Ship(3, 1, 0, 0);
//        placer.addShip(testShip);
//
//        assertEquals(true, placer.shipFitsInWithPlayersOtherShipsAndGrid(player));
//        //olettaa että ruudukko ainakin 5x5
//    }
//    
//    @Test
//    public void shipFitsInWithPlayersOtherShipsAndGridWorksWhenTrue2() {
//        Player player = new Player("Keijo", 0);
//        Ship testShip1 = new Ship(3, 1, 0, 0);
//        Ship testShip2 = new Ship(3, 2, 0, 0);
//        placer.addShip(testShip1);
//        player.addToFleet(testShip2);
//        assertEquals(true, placer.shipFitsInWithPlayersOtherShipsAndGrid(player));
//        //olettaa että ruudukko ainakin 5x5
//    }
//
//    @Test
//    public void shipFitsInWithPlayersOtherShipsAndGridWorksWhenFalse1() {
//        Player player = new Player("Keijo", 1);
//        Ship testShip = new Ship(3, -4, 0, 0);
//
//        placer.addShip(testShip);
//        assertEquals(false, placer.shipFitsInWithPlayersOtherShipsAndGrid(player));
//        //olettaa että ruudukko ainakin 5x5
//    }

    @Test
    public void shipFitsInWithPlayersOtherShipsAndGridWorksWhenFalse2() {
        Player player = new Player("Keijo", 1);
        Ship testShip1 = new Ship(3, 0, 0, 0);
        Ship testShip2 = new Ship(3, 0, 0, 90);
        placer.addShip(testShip1);
        player.addToFleet(testShip2);
        assertEquals(false, placer.shipFitsInWithPlayersOtherShipsAndGrid(player));
        //olettaa että ruudukko ainakin 5x5
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
