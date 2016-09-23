/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.game;

import ships.sinktheships.game.Ship;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jambo
 */
public class ShipTest {

    Ship ship;

    public ShipTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        ship = new Ship("name", 1, 0, 0, 0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shipNameWorks() {
        assertEquals(ship.getName(), "Destroyer");

    }

    @Test
    public void isThisShipHereWorksWhenThereShouldBeShip() {

        assertEquals(true, ship.isThisShipHere(0, 0));

    }

    @Test
    public void isThisShipHereWorksWhenThereShouldBeShip2() {
        ship = new Ship("name", 2, 0, 0, 0);
        assertEquals(true, ship.isThisShipHere(0, 2));

    }

    @Test
    public void isThisShipHereWorksWhenThereShouldBeShipAngleDiff() {
        ship = new Ship("name", 4, 0, 0, 90);
        assertEquals(true, ship.isThisShipHere(3, 0));

    }

    @Test
    public void isThisShipHereWorksWhenThereShouldBeShipAngleDiffAndNegative() {
        ship = new Ship("name", 4, 0, 0, 180);
        assertEquals(true, ship.isThisShipHere(0, -4));

    }

    @Test
    public void fillTableWorksWhenAllSame() {
        int[] table = new int[5];
        table = ship.fillTable(5, 0, false, false);

        assertEquals(0, table[4]);

    }

    @Test
    public void fillTableWorksWhenCumulative() {
        int[] table = new int[5];
        table = ship.fillTable(5, 0, true, false);

        assertEquals(4, table[4]);

    }

    @Test
    public void fillTableWorksWhenCumulativeAndSubractive() {
        int[] table = new int[5];
        table = ship.fillTable(5, 0, true, true);

        assertEquals(-4, table[4]);

    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
