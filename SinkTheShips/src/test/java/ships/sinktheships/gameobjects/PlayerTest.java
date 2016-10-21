/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gameobjects;

import ships.sinktheships.gameobjects.Player;
import ships.sinktheships.gameobjects.Ship;
import java.util.List;
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
public class PlayerTest {

    Player player;

    public PlayerTest() {
    }

    @BeforeClass
    public static void setUpClass() {
    }

    @AfterClass
    public static void tearDownClass() {
    }

    @Before
    public void setUp() {
        player = new Player("qqq", 0);
    }

    @After
    public void tearDown() {
    }

    @Test
    public void playerIdIsZero() {
        //player = new Player("qqq", 0); 

        assertEquals(0, player.getId());
    }

    @Test
    public void playerIdIsOne() {
        player = new Player("qqq", 1);

        assertEquals(1, player.getId());
    }

    @Test
    public void playerNameIsRight() {
        assertEquals("qqq", player.getName());
    }

    @Test
    public void addToFleetWorksAndGetShipsWorks() {
        Ship destroyerr = new Ship(2, 0, 0, 0); //pitäis olla Destroyer
        player.addToFleet(destroyerr);
        List<Ship> testi = player.getShips();
        Ship x = testi.get(0);
        assertEquals("Destroyer", x.getName());
    }

    @Test
    public void hasPlacedShipsWorks() {
        player = new Player("qqq", 1);
        player.addToFleet(new Ship(2, 0, 0, 0));
        player.addToFleet(new Ship(2, 0, 0, 0));
        player.addToFleet(new Ship(2, 0, 0, 0));
        player.addToFleet(new Ship(2, 0, 0, 0));
        player.addToFleet(new Ship(2, 0, 0, 0));

        assertEquals(true, player.hasPlacedShips());
    }

    @Test
    public void hasPlacedShipsWorks2() {
        player = new Player("qqq", 1);
        player.addToFleet(new Ship(2, 0, 0, 0));
        player.addToFleet(new Ship(2, 0, 0, 0));
        player.addToFleet(new Ship(2, 0, 0, 0));
        player.addToFleet(new Ship(2, 0, 0, 0));

        assertEquals(false, player.hasPlacedShips());
    }

    @Test
    public void hasShipInThisCoordinateWorks() {
        player = new Player("qqq", 1);
        player.addToFleet(new Ship(2, 0, 0, 0));

        assertEquals(true, player.hasShipInThisCoordinate(new Coordinate(0, 0)));
    }

    @Test
    public void hasShipInThisCoordinateWorksNoShip() {
        player = new Player("qqq", 1);
        player.addToFleet(new Ship(2, 0, 0, 0));

        assertEquals(false, player.hasShipInThisCoordinate(new Coordinate(3, 3)));
    }

    @Test
    public void coordinateUsedWorks() {
        player = new Player("qqq", 1);
        Ship qq = new Ship(2, 0, 0, 0);
        player.addToFleet(qq);
        player.getShotsOnThisOnesGrid().add(new Shot(new Coordinate(1, 1), true));
        //player.getCoordinatesOfShots().add(new Coordinate(1, 1));
        //player.isHereAship(new Coordinate(1, 1));
        assertEquals(true, player.coordinateUsed(new Coordinate(1, 1)));
    }

    @Test
    public void coordinateUsedWorksNoCoordinate() {
        player = new Player("qqq", 1);
        Ship qq = new Ship(2, 0, 0, 0);
        player.addToFleet(qq);
        assertEquals(false, player.coordinateUsed(new Coordinate(1, 1)));
    }

    @Test
    public void allShipsDestroyedWorks() {
        player = new Player("qqq", 1);
        Ship q1 = new Ship(1, 0, 0, 0);
        player.addToFleet(q1);
        q1.setDamages(2);
        assertEquals(true, player.allShipsDestroyed());
    }

    @Test
    public void allShipsDestroyedWorksNotAllDestroyed() {
        player = new Player("qqq", 1);
        Ship q1 = new Ship(1, 0, 0, 0);
        player.addToFleet(q1);
        Ship q2 = new Ship(1, 0, 0, 0);
        player.addToFleet(q1);
        assertEquals(false, player.allShipsDestroyed());
    }

    @Test
    public void allShipsDestroyedWorksNoShipDestroyed() {
        player = new Player("qqq", 1);
        Ship q1 = new Ship(1, 0, 0, 0);
        player.addToFleet(q1);
        assertEquals(false, player.allShipsDestroyed());
    }

}
