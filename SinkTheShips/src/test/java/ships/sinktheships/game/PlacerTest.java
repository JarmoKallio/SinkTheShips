/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.game;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import ships.sinktheships.gui.Output;
import ships.sinktheships.gui.Inputs;
import ships.sinktheships.game.Options;

/**
 *
 * @author jambo
 */
public class PlacerTest {

    Inputs input = new Inputs();
    Output output = new Output();
    Player player = new Player("Pena", 0);

    Placer placer = new Placer(player, input, output);

    public PlacerTest() {
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
    public void inRangeWorksWhenShipInRange() {

        Ship testShip = new Ship("Pena", 3, 0, 0, 0);
        placer.addShip(testShip);
        assertEquals(true, placer.inRange(testShip));
    }

    @Test
    public void inRangeWorksWhenShipNotInRange() {

        Ship testShip = new Ship("Pena", 3, -10, -20, 0);
        placer.addShip(testShip);
        assertEquals(false, placer.inRange(testShip));
    }

    @Test
    public void inRangeWorksWhenShipPartiallyInRange() {

        Ship testShip = new Ship("Pena", 3, -1, 0, 0);
        placer.addShip(testShip);
        assertEquals(false, placer.inRange(testShip));
    }

    @Test
    public void inRangeWorksWhenShipPartiallyInRangeUsingAngle() {

        Ship testShip = new Ship("Pena", 3, 0, 0, 180);
        placer.addShip(testShip);
        assertEquals(false, placer.inRange(testShip));
    }

    @Test
    public void moveShipAndCheckWorksWhenOnlyShipAndMovesOutOfRange() { //pitäisi pysyä 
        ///muuttumattomana

        Ship testShip = new Ship("Pena", 3, 0, 0, 0);
        int[] coverageFirstX = testShip.getCoverageX();
        int[] coverageFirstY = testShip.getCoverageY();
        placer.addShip(testShip);
        placer.moveShipAndCheck(-1, true);

        for (int i = 0; i < 4; i++) {
            assertEquals(coverageFirstX[i], testShip.getCoverageX()[i]);
            assertEquals(coverageFirstY[i], testShip.getCoverageY()[i]);
        }
    }

    @Test
    public void moveShipAndCheckWorksWhenOnlyShipAndMovesWithin() { //pitäisi 
        ///muuttua ilmoitetun määrän

        Ship testShip = new Ship("Pena", 3, 0, 0, 0);
        int[] coverageExpectedX = {1, 1, 1, 1};
        int[] coverageExpectedY = {0, 1, 2, 3};
        placer.addShip(testShip);
        placer.moveShipAndCheck(1, true);
        int[] coverageActualX = testShip.getCoverageX();
        int[] coverageActualY = testShip.getCoverageY();

        for (int i = 0; i < 4; i++) {
            assertEquals(coverageExpectedX[i], coverageActualX[i]);
            assertEquals(coverageExpectedY[i], coverageActualY[i]);
        }
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
