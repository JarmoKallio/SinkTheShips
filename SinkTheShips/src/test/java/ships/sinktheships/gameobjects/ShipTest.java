/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gameobjects;

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
        ship = new Ship(1, 0, 0, 0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void shipNameWorks() {
        assertEquals(ship.getName(), "Submarine");

    }

    @Test
    public void isThisShipHereWorksWhenThereShouldBeShip() {
        assertEquals(true, ship.isThisShipHere(0, 0));

    }

    @Test
    public void isThisShipHereWorksWhenThereShouldBeShip2() {
        ship = new Ship(2, 0, 0, 0);
        assertEquals(true, ship.isThisShipHere(0, 2));

    }

    @Test
    public void isThisShipHereWorksWhenThereShouldBeShipAngleDiff() {
        ship = new Ship(4, 0, 0, 90);
        assertEquals(true, ship.isThisShipHere(3, 0));

    }

    @Test
    public void isThisShipHereWorksWhenThereShouldBeShipAngleDiffAndNegative() {
        ship = new Ship(4, 0, 0, 180);
        assertEquals(true, ship.isThisShipHere(0, -4));

    }

    @Test
    public void changeShipAngleWorksWhenZeroTo90() {
        Ship q1 = new Ship(1, 0, 0, 0);
        q1.changeShipAngle();
        assertEquals(90, q1.getShipAngle());
    }

    @Test
    public void changeShipAngleWorksWhenBackToZero() {
        Ship q1 = new Ship(1, 0, 0, 270);
        q1.changeShipAngle();
        assertEquals(0, q1.getShipAngle());
    }

    @Test
    public void canFireXTimesWorksOneTimeLeft() {
        Ship q1 = new Ship(2, 0, 0, 270);
        q1.setDamages(2);
        assertEquals(1, q1.canFireXTimes());
    }

    @Test
    public void canFireXTimesWorksNoTimesLeft() {
        Ship q1 = new Ship(2, 0, 0, 270);
        q1.setDamages(3);
        assertEquals(0, q1.canFireXTimes());
    }

    @Test
    public void canFireXTimesNoDamages() {
        Ship q1 = new Ship(2, 0, 0, 270);
        assertEquals(3, q1.canFireXTimes());
    }

    @Test
    public void isDestroyedWorksWhenDestroyed() {
        Ship q1 = new Ship(2, 0, 0, 270);
        q1.setDamages(3);
        assertEquals(true, q1.isDestroyed());
    }

    @Test
    public void isDestroyedWorksWhenNoDamages() {
        Ship q1 = new Ship(2, 0, 0, 270);
        assertEquals(false, q1.isDestroyed());
    }

    @Test
    public void setDamagesWorksWhenOneDamage() {
        Ship q1 = new Ship(2, 0, 0, 270);
        q1.setDamages(1);
        assertEquals(2, q1.canFireXTimes());
    }

    @Test
    public void setDamagesWorksWhenDestroyed() {
        Ship q1 = new Ship(2, 0, 0, 270);
        q1.setDamages(3);
        assertEquals(0, q1.canFireXTimes());
    }

}
