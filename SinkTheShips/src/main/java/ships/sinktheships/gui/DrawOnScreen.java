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
import ships.sinktheships.gameobjects.Coordinate;
import ships.sinktheships.gameobjects.Player;
import ships.sinktheships.gameobjects.Ship;
import ships.sinktheships.gameobjects.Shot;
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

    private ArrayList<Ship> ships;

    private boolean drawText = false;
    private boolean drawBackround = false;
    private boolean drawShipsAndGrid = false;
    private boolean drawGridNumbers = false;
    private boolean drawShots = false;

    private Player currentPlayer;
    private Player adversary;

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
    public void drawBattle(Player currentPlayer, Player adversary) {
        drawText = true;
        drawBackround = true;
        drawShipsAndGrid = true;
        drawGridNumbers = true;
        drawShots = true;

        this.currentPlayer = currentPlayer;
        this.adversary = adversary;

        this.textLines.clear();
        this.ships = currentPlayer.getShips();

        this.textLines.add(new Text("Type a coordinate, our guns are waiting!", 2));
        this.textLines.add(new Text("Press enter to fire.", 3));
        this.textLines.add(new Text(currentPlayer.getName()+"'s turn", 1));
        
        repaint();
    }

    private void drawShots(Graphics g) {
        drawShotsSelectAndDraw(g, true);
        drawShotsSelectAndDraw(g, false);
    }

    private void drawShotsSelectAndDraw(Graphics g, boolean onOwnGrid) {
        Player usedPlayer;
        if (onOwnGrid) {
            usedPlayer = currentPlayer;
        } else {
            usedPlayer = adversary;
        }
        for (Shot x : usedPlayer.getShotsOnThisOnesGrid()) {
            if (x.isHit()) {
                drawOneShot(g, x.getCoordinate(), Color.RED, onOwnGrid);
            } else {
                drawOneShot(g, x.getCoordinate(), Color.WHITE, onOwnGrid);
            }
        }
    }

    private void drawOneShot(Graphics g, Coordinate coordinate, Color color, boolean onOwnGrid) {
        int chaGrid = 0;
        if (!onOwnGrid) {
            chaGrid = 300;
        }
        g.setColor(color);
        g.fillOval(60 + chaGrid + ((coordinate.getxCoordinate() - 1) * 30), 600 - ((coordinate.getyCoordinate() - 1) * 30), 30, 30);
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
            drawTextLines(g);
        }
        if (drawShipsAndGrid) {
            drawShipsPlacer(g);
        }
        if (drawGridNumbers) {
            drawGridNumbers(g);
        }
        if (drawShots) {
            drawShots(g);
        }

        drawText = false;
        drawBackround = false;
        drawShipsAndGrid = false;
        drawGridNumbers = false;
        drawShots = false;
    }

    private void drawShipsPlacer(Graphics g) {
        //piirreään ruudukko ja siihen laivat(nykyinen ja muut jos on)
        drawGrids(g);
        drawShips(g);
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
        drawLines(g, false, startposX, startposY);
    }

    private void drawLines(Graphics g, boolean verticalLInes, int startposX, int startposY) {
        if (verticalLInes) {
            for (int i = 0; i < 10; i++) {
                int gab = 30 * i;
                g.drawLine(startposX + gab, startposY, startposX + gab, startposY + 300);
            }
        } else {
            for (int i = 0; i < 10; i++) {
                int gab = 30 * i;
                g.drawLine(startposX, startposY + gab, startposX + 300, startposY + gab);
            }
        }

    }

    private void drawShips(Graphics g) {
        g.setColor(Color.white);
        for (Ship x : ships) {
            drawOneShip(g, x);
        }

    }

    private void drawOneShip(Graphics g, Ship ship) {
        int xpos = ship.getSternXcoordinate();
        int ypos = ship.getSternYcoordinate();

        //piirretään vielä loput laivasta..
        int shipAngle = ship.getShipAngle();

        if (shipAngle == 0) {
            drawOneShipWithAngle(g, ship, 0, 1);
        } else if (shipAngle == 90) {
            drawOneShipWithAngle(g, ship, 1, 0);
        } else if (shipAngle == 180) {
            drawOneShipWithAngle(g, ship, 0, -1);
        } else {
            drawOneShipWithAngle(g, ship, -1, 0);
        }

    }

    private void drawOneShipWithAngle(Graphics g, Ship ship, int xAdd, int yAdd) {
        int xpos = ship.getSternXcoordinate();
        int ypos = ship.getSternYcoordinate();
        int shipLenght = ship.getSize();

        for (int i = 0; i < shipLenght; i++) {
            drawShipPart(g, xpos + (xAdd * i), ypos + (yAdd * i));
        }
    }

    private void drawShipPart(Graphics g, int positionX, int positionY) {
        g.setColor(shipColor);
        g.fillRect(60 + ((positionX - 1) * 30), 600 - ((positionY - 1) * 30), 30, 30);
    }

    private void drawBackround(Graphics g) {
        g.setColor(backroundColor);
        g.fillRect(0, 0, Options.getWindowSizeX(), Options.getWindowSizeY());

    }

    private void drawTextLines(Graphics g) {
        for (Text text : textLines) {
            g.setColor(Color.black);
            g.setFont(font);
            String textLine = text.getText();
            int lineNumber = text.getLineNumber();
            metrics = g.getFontMetrics(font);
            int positionX = (Options.getWindowSizeX() - metrics.stringWidth(textLine)) / 2;
            drawText(g, textLine, positionX, 30 * lineNumber);
        }
    }

    private void drawText(Graphics g, String textLine, int positionX, int positionY) {
        g.drawString(textLine, positionX, positionY);
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
    public void placeShipsOnGrid(ArrayList ships, String nameOfMovedShip) {
        drawText = true;
        drawBackround = true;
        drawShipsAndGrid = true;
        drawGridNumbers = true;
        this.textLines.clear();

        this.ships = ships;

        this.textLines.add(new Text("Place your " + nameOfMovedShip + "!", 1));
        this.textLines.add(new Text("Move with arrows, rotate with r.", 2));
        this.textLines.add(new Text("Press enter when ready.", 3));

        repaint();
    }

    private void drawGridNumbers(Graphics g) {
        g.setColor(Color.black);
        g.setFont(font);
        metrics = g.getFontMetrics(font);
        //int position = (Options.getWindowSizeX() - metrics.stringWidth(textLine)) / 2;
        for (int i = 10; i > 0; i--) {
            //vasen ja oikee pystyrivi
            drawText(g, "" + i, 30, 352 + ((10 - i) * 30));
            drawText(g, "" + i, 660 + 4, 352 + ((10 - i) * 30));
            //vaakarivit
            drawText(g, "" + i, 30 + ((i) * 30), 660 - 5);
            drawText(g, "" + i, 330 + ((i) * 30), 330 - 5);
        }

    }

    public void clearScreen() {
        this.clearAllLines();
        drawText = false;
        drawBackround = false;
        drawShipsAndGrid = false;
        drawGridNumbers = false;
        drawShots = false;
    }

}
