/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.JPanel;
import ships.sinktheships.gameobjects.Ship;
import ships.sinktheships.logic.Options;

/**
 * Luokka huolehtii kuvien ja tekstin piirtämisestä JFrame-ikkunaan.
 *
 * @author jambo
 */
public class DrawOnScreen extends JPanel {

    private ArrayList<Text> textLines = new ArrayList<>();
    private Font font = new Font("Dialog", 0, 20);
    private FontMetrics metrics;

    private boolean drawText = false;
    private boolean drawBackround = false;
    private boolean drawShipsAndGrid = false;

    private Color backroundColor = new Color(102, 179, 255);
    private Color adwGridBackround = new Color(0, 26, 0);
    private Color adwGridLine = new Color(0, 204, 0);
    private Color playerGridBackround = new Color(0, 163, 204);
    private Color shipColor = new Color(122, 122, 82);
    //private boolean drawBattle = false;

    /**
     * Lisää textLines-listaan yhden Text-olion lisää.
     *
     * @param text pätkä tekstiä
     * @param lineNumber rivinumero johon tekstin halutaan ilmestyvän
     */
    public void addLineOfText(String text, int lineNumber) {
        drawText = true;
        drawBackround = true;
        Text lineOfText = new Text(text, lineNumber);
        this.textLines.add(lineOfText);
        repaint();
    }

    /**
     * Ei vielä toteutettu.
     */
    public void drawShipPlacementGrid() {

    }

    /**
     * Poistaa kaikki textLines-listalla olevat tekstipätkät.
     */
    public void clearAllLines() {
        this.textLines.clear();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (drawBackround) {
            drawBackround(g);
        }
        if (drawText) {
            drawText(g);
        }
        if (drawShipsAndGrid) {
            drawShipsPlacer(g);
        }

        drawText = false;
        drawBackround = false;
        drawShipsAndGrid = false;
    }

    private void drawBattle(Graphics g) {
        //pidettävä huolta että näytetään aina oikea ruudukko
    }

    private void drawShipsPlacer(Graphics g) {
        //piirreään ruudukko ja siihen laivat(nykyinen ja muut jos on)

//drawShips(g);
    }

    private void drawGrids(Graphics g) {
        g.setColor(Color.white);
        //g.drawLine(int x1, int y1, int x2, int y2);
        int startposX = 60; //ruudukon vasen kulma
        int startposY = 330;
        //eka ruudukoidne pohjat, vasen sininen, oikee tumma

        drawGrid(g, playerGridBackround, Color.white, startposX, startposY);
        drawGrid(g, adwGridBackround, adwGridLine, startposX + 300, startposY);

    }

    private void drawGrid(Graphics g, Color backround, Color line, int startposX, int startposY) {
        g.setColor(backround);
        g.fillRect(startposX, startposY, 300, 300);
        g.setColor(line);
        drawLines(g, true, startposX, startposY);

    }

    private void drawLines(Graphics g, boolean verticalLInes, int startposX, int startposY) {
        if (verticalLInes) {
            for (int i = 0; i < 10; i++) {
                int gab = 3 * i;
                g.drawLine(startposX + gab, startposY, startposX + 300 + gab, startposY);
            }
        } else {
            for (int i = 0; i < 10; i++) {
                int gab = 3 * i;
                g.drawLine(startposX, startposY + gab, startposX, startposY + 300 + gab);
            }
        }

    }

    private void drawShips(Graphics g) {
        g.setColor(Color.white);

    }

    private void drawBackround(Graphics g) {
        g.setColor(backroundColor);
        g.fillRect(0, 0, Options.getWindowSizeX(), Options.getWindowSizeY());

    }

    private void drawText(Graphics g) {
        for (Text text : textLines) {
            g.setColor(Color.black);
            g.setFont(font);
            metrics = g.getFontMetrics(font);
            String textLine = text.getText();
            int lineNumber = text.getLineNumber();
            int position = (Options.getWindowSizeX() - metrics.stringWidth(textLine)) / 2;
            g.drawString(textLine, position, 20 * lineNumber);
        }
    }

    /**
     * Maalaa koko ruudun ensin siniseksi ja sitten kirjoittaa tekstiä.
     *
     * @param text tekstiä
     * @param lineNumber mille "riville" teksti tulee, ykkösestä eteenpäin
     */
    public void newLineOfText(String text, int lineNumber) {
        this.textLines.clear();
        addLineOfText(text, lineNumber);
    }

    /**
     * Piirtää annetut laivat koordinaatistoon.
     *
     * @param ships laivoja sisältävä ArrayList
     */
    public void placeShipsOnGrid(ArrayList ships) {
        drawText = true;
        drawBackround = true;
        drawShipsAndGrid = true;
        this.textLines.add(new Text("Place your ship!", 1));
        this.textLines.add(new Text("Move with arrows, rotate with r.", 2));
        this.textLines.add(new Text("Press enter when ready.", 2));
        repaint();
    }

}
