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
public class ShotTest {

    public ShotTest() {
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
    public void isHitWhenHit() {
        Shot shot = new Shot(new Coordinate(0, 0), true);
        assertEquals(true, shot.isHit());
    }

    @Test
    public void isNotHitWhenNot() {
        Shot shot = new Shot(new Coordinate(0, 0), false);
        assertEquals(false, shot.isHit());
    }
}
