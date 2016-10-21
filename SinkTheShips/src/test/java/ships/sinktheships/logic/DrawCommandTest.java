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
import ships.sinktheships.gui.Text;

/**
 *
 * @author jambo
 */
public class DrawCommandTest {

    public DrawCommandTest() {
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
    public void addTextWorksWithReset() {
        DrawCommand command = new DrawCommand();
        command.addText(new Text("B", 1));
        command.addText(new Text("B", 2));
        command.resetTexts();
        assertEquals(true, command.getTexts().isEmpty());
    }

    @Test
    public void addTextWorks() {
        DrawCommand command = new DrawCommand();
        command.addText(new Text("B", 1));
        command.addText(new Text("B", 2));

        assertEquals("B", command.getTexts().get(0).getText());
    }

    @Test
    public void addTextWorksWhenSameLineNumber() {
        DrawCommand command = new DrawCommand();
        command.addText(new Text("B", 1));
        command.addText(new Text("Boo", 1));

        assertEquals("Boo", command.getTexts().get(0).getText());
    }

    @Test
    public void resetWorks() {
        DrawCommand command = new DrawCommand();
        command.addText(new Text("B", 1));
        command.addText(new Text("Boo", 1));

        command.setDrawBackround(true);
        command.setDrawText(true);
        command.setDrawShipsAndGrid(true);
        command.setDrawGridNumbers(true);
        command.setDrawShots(true);
        command.sethighlightSelectedShip(true);
        command.setDrawTargetCoordinate(true);
        command.setDrawRadar(true);
        command.reset();

        assertEquals(true, command.getTexts().isEmpty());
        assertEquals(false, command.isBackroundDrawn());
        assertEquals(false, command.isTextDrawn());
        assertEquals(false, command.areShipsAndGridDrawn());
        assertEquals(false, command.areGridNumbersDrawn());
        assertEquals(false, command.areShotsDrawn());
        assertEquals(false, command.isDrawTargetCoordinate());
    }
}
//drawBackround = false;
//        drawText = false;
//        drawShipsAndGrid = false;
//        drawGridNumbers = false;
//        drawShots = false;
//        drawTargetCoordinate = false;
//        containsPlayers = false;
//        textLines.clear();
