/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.game;

import ships.sinktheships.game.Player;
import ships.sinktheships.game.Ship;
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
        Ship destroyerr = new Ship(player.getName(), 2, 0, 0, 0); //pitäis olla Cruiser
        player.addToFleet(destroyerr);
        List<Ship> testi = player.getShips();
        Ship x = testi.get(0);
        assertEquals("Cruiser", x.getName());
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    // @Test
    // public void hello() {}
}
