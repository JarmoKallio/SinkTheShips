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

/**
 *
 * @author jambo
 */
public class LogicTest {

    public LogicTest() {
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
    public void resetWorks() {
        Logic logic = new Logic();
        logic.addNewPlayer("Bob", 0);
        logic.addNewPlayer("Bo", 1);
        logic.reset();
        assertEquals(false, logic.areThereAnyPlayers());
    }

    @Test
    public void areThereAnyPlayersWorksWhenNoPlayers() {
        Logic logic = new Logic();
        assertEquals(false, logic.areThereAnyPlayers());
    }

    @Test
    public void areThereAnyPlayersWorksWhenTwoPlayers() {
        Logic logic = new Logic();
        logic.addNewPlayer("Bob", 0);
        logic.addNewPlayer("Bo", 1);
        assertEquals(true, logic.areThereAnyPlayers());
    }

    @Test
    public void getNextPlayerWorks() {
        Logic logic = new Logic();
        logic.addNewPlayer("Bob", 0);
        logic.addNewPlayer("Bo", 1);

        assertEquals("Bob", logic.getNextPlayer().getName());
    }

    @Test
    public void addPlayerWorks() {
        Logic logic = new Logic();
        logic.addNewPlayer("Bob", 0);
        assertEquals(true, logic.areThereAnyPlayers());
    }

    @Test
    public void addPlayerWorksTestGetActivePlayer() {
        Logic logic = new Logic();
        logic.addNewPlayer("Bob", 0);
        assertEquals("Bob", logic.getActivePlayer().getName());
    }

    @Test
    public void updateActiveScreenWorks() {
        Logic logic = new Logic();
        Screen screen = logic.getActiveScreen();
        logic.updateActiveScreen();
        assertEquals(screen, logic.getActiveScreen());
    }

}
