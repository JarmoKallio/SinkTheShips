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
public class RadarTest {

    public RadarTest() {
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
    public void thereShouldBeNoImageWhenNoShots() {
        Ship q1 = new Ship(1, 0, 0, 0);
        Radar radar = new Radar(q1);
        assertEquals(false, radar.thereIsRadarImage());
    }

    @Test
    public void thereShouldBeImageWhenSixShots() {
        Ship q1 = new Ship(1, 0, 0, 0);
        Radar radar = new Radar(q1);
        radar.shipHasShot();
        radar.shipHasShot();
        radar.shipHasShot();
        radar.shipHasShot();
        radar.shipHasShot();
        radar.shipHasShot();
        assertEquals(true, radar.thereIsRadarImage());
    }
}
