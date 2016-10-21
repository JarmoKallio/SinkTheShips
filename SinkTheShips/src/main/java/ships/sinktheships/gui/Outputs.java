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
import ships.sinktheships.gameobjects.Radar;
import ships.sinktheships.gameobjects.Ship;
import ships.sinktheships.gameobjects.Shot;
import ships.sinktheships.logic.DrawCommand;
import ships.sinktheships.logic.Logic;
import ships.sinktheships.logic.Screen;
import ships.sinktheships.logic.Options;

/**
 * Luokka huolehtii kuvien ja tekstin piirtämisestä JFrame-ikkunaan. Tämä käy
 * niin, että logiikka-luokasta haetaan uusin Screen ja sen tarjoama
 * DrawCommand-olio, joka sitten sisältää joukon arvoja joiden avulla tiedetään,
 * mitä näytölle kulloinkin tulisi piirtää.
 *
 * @author jambo
 */
public class Outputs extends JPanel {

    private ArrayList<Text> textLines = new ArrayList<>();
    private Font font = new Font("Dialog", 0, 20);
    private FontMetrics metrics;

    private ArrayList<Ship> ships = new ArrayList<Ship>();
    private ArrayList<Shot> playerShots = new ArrayList<Shot>();
    private ArrayList<Shot> adversaryShots = new ArrayList<Shot>();
    private Ship selectedShip;
    private Player currentPlayer;
    private Player adversary;
    private Coordinate targetCoordinate;

    private boolean drawText = false;
    private boolean drawBackround = false;
    private boolean drawShipsAndGrid = false;
    private boolean drawGridNumbers = false;
    private boolean drawShots = false;
    private boolean highlightSelectedShip = false;
    private boolean drawTargetCoordinate = false;
    private boolean drawRadar = false;

    //tätä käytetään esim laivoja valitessa, koska halutaan 
    //päivittää drawCommand vaikkei uutta olekaan tarjolla, sillä
    //esim tässä tapauksessa valittu laiva muuttuu MUTTA uutta drawCommandii
    //ei luoda
    private boolean changeInExistingCommandParameters = false;

    private Color backroundColor = new Color(102, 179, 255);
    private Color adwGridBackround = new Color(0, 26, 0);
    private Color adwGridLine = new Color(0, 204, 0);
    private Color playerGridBackround = new Color(0, 163, 204);
    private Color shipColor = new Color(122, 122, 82);
    private Color shipHighlighted = new Color(244, 241, 66);
    //private boolean drawBattle = false;

    private DrawCommand currentDrawCommand;
    private Logic logic;
    private Screen activeScreen;

    /**
     * Luo uuden Outputs-olion.
     *
     * @param logiikka käytetty logiikka-olio
     */
    public Outputs(Logic logiikka) {
        this.logic = logiikka;

        activeScreen = this.logic.getActiveScreen();
        this.currentDrawCommand = activeScreen.getDrawCommand();
        executeCurrentDrawCommand();

    }

    /**
     * Tätä metodia kutsumalla käydään hakemassa uusi DrawCommand-piirtokomento,
     * tai vaihtoehtoisesti piirretään uudestaan nykyinen piirtokomento.
     */
    public void update() {
        activeScreen = this.logic.getActiveScreen();

        DrawCommand oldCommand = this.currentDrawCommand;
        DrawCommand newCommand = activeScreen.getDrawCommand();

        if (oldCommand.equals(newCommand) && (!newCommand.parametersChanged())) {
            repaint();
        } else {
            this.currentDrawCommand = newCommand;
            executeCurrentDrawCommand();
        }

    }

    private void executeCurrentDrawCommand() {
        resetBooleans();
        setBooleans();
        checkAndGetShipsIfNeeded();
        checkShipPlacer();
        checkHighlightFunction();
        checkIfTargetCoordinateDrawn();
        repaint();

    }

    private void loadShots() {
        if (drawShots) {
            this.adversaryShots = adversary.getShotsOnThisOnesGrid();
            this.playerShots = currentPlayer.getShotsOnThisOnesGrid();
        }
    }

    private void checkIfShotsDrawn() {
        if (this.currentDrawCommand.areShotsDrawn()) {
            this.drawShots = true;
        }
    }

    private void checkIfTargetCoordinateDrawn() {
        if (this.currentDrawCommand.isDrawTargetCoordinate()) {
            this.drawTargetCoordinate = true;
            this.targetCoordinate = this.currentDrawCommand.getTargetCoordinate();
        }
    }

    private void checkAndGetShipsIfNeeded() {
        if (drawShipsAndGrid) {
            this.ships.clear();
            ArrayList<Ship> playersShips = this.currentDrawCommand.getPlayer().getShips();
            int size = playersShips.size();
            if (size != 0) {
                for (Ship x : playersShips) {
                    this.ships.add(x);
                }
            }
        }
    }

    private void checkShipPlacer() {
        if (this.currentDrawCommand.isDrawShipPlacer()) {
            this.ships.add(this.currentDrawCommand.getPlacer().getShip());
        }
    }

    private void checkHighlightFunction() {
        if (this.currentDrawCommand.isHighlightSelectedShip()) {
            this.highlightSelectedShip = true;
            this.selectedShip = this.currentDrawCommand.getSelectedShip();
        }
    }

    private void resetBooleans() {
        this.drawBackround = false;
        this.drawText = false;
        this.drawShipsAndGrid = false;
        this.drawGridNumbers = false;
        this.drawShots = false;
        this.highlightSelectedShip = false;
        this.drawTargetCoordinate = false;
        this.drawRadar = false;

    }

    private void setBooleans() {
        this.drawBackround = this.currentDrawCommand.isBackroundDrawn();
        this.drawText = this.currentDrawCommand.isTextDrawn();
        this.textLines = this.currentDrawCommand.getTexts();
        this.drawShipsAndGrid = this.currentDrawCommand.areShipsAndGridDrawn();
        this.drawGridNumbers = this.currentDrawCommand.areGridNumbersDrawn();
        this.drawShots = this.currentDrawCommand.areShotsDrawn();
        this.drawTargetCoordinate = this.currentDrawCommand.isDrawTargetCoordinate();
        this.drawRadar = this.currentDrawCommand.isDrawRadar();

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
        if (highlightSelectedShip) {
            drawSelectedShip(g);
        }

        if (drawRadar) {
            drawRadar(g);
        }
        if (drawShots) {
            drawShots(g);
        }
        if (drawTargetCoordinate) {
            drawTargetCoordinate(g);
        }

    }

    private void drawRadar(Graphics g) {
        Radar radar = logic.getNextPlayer().getRadar();
        if (radar.isActive() && radar.thereIsRadarImage()) {
            Coordinate coordinate = radar.getCoordinate();
            drawPointInRadar(g, coordinate);
        }
    }

    private void drawPointInRadar(Graphics g, Coordinate coordinate) {
        g.setColor(this.adwGridLine);
        g.fillRect(360 + ((coordinate.getxCoordinate() - 1) * 30), 600 - ((coordinate.getyCoordinate() - 1) * 30), 30, 30);

    }

    private void drawTargetCoordinate(Graphics g) {
        drawOneShot(g, targetCoordinate, shipHighlighted, false);
    }

    private void drawSelectedShip(Graphics g) {
        drawOneShip(g, selectedShip, shipHighlighted);
    }

    private void drawShots(Graphics g) {
        drawShotsSelectAndDraw(g, true);
        drawShotsSelectAndDraw(g, false);
    }

    private void drawShotsSelectAndDraw(Graphics g, boolean onOwnGrid) {
        ArrayList<Shot> shots;
        if (onOwnGrid) {
            shots = logic.getActivePlayer().getShotsOnThisOnesGrid();
        } else {
            shots = logic.getNextPlayer().getShotsOnThisOnesGrid();
        }

        for (Shot x : shots) {
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
            drawOneShip(g, x, this.shipColor);
        }

    }

    private void drawOneShip(Graphics g, Ship ship, Color color) {
        int xpos = ship.getSternXcoordinate();
        int ypos = ship.getSternYcoordinate();

        //piirretään vielä loput laivasta..
        int shipAngle = ship.getShipAngle();

        if (shipAngle == 0) {
            drawOneShipWithAngle(g, ship, 0, 1, color);
        } else if (shipAngle == 90) {
            drawOneShipWithAngle(g, ship, 1, 0, color);
        } else if (shipAngle == 180) {
            drawOneShipWithAngle(g, ship, 0, -1, color);
        } else {
            drawOneShipWithAngle(g, ship, -1, 0, color);
        }

    }

    private void drawOneShipWithAngle(Graphics g, Ship ship, int xAdd, int yAdd, Color color) {
        int xpos = ship.getSternXcoordinate();
        int ypos = ship.getSternYcoordinate();
        int shipLenght = ship.getSize();

        for (int i = 0; i < shipLenght; i++) {
            drawShipPart(g, xpos + (xAdd * i), ypos + (yAdd * i), color);
        }
    }

    private void drawShipPart(Graphics g, int positionX, int positionY, Color color) {
        g.setColor(color);
        g.fillRect(60 + ((positionX - 1) * 30), 600 - ((positionY - 1) * 30), 30, 30);
    }

    private void drawBackround(Graphics g) {
        g.setColor(backroundColor);
        g.fillRect(0, 0, Options.getWindowSizeX(), Options.getWindowSizeY());

    }

    private void drawTextLines(Graphics g) {
        g.setColor(Color.black);
        g.setFont(font);
        metrics = g.getFontMetrics(font);

        for (Text text : textLines) {
            String textLine = text.getText();
            int lineNumber = text.getLineNumber();
            int positionX = (Options.getWindowSizeX() - metrics.stringWidth(textLine)) / 2;
            drawText(g, textLine, positionX, 30 * lineNumber);
        }
    }

    private void drawText(Graphics g, String textLine, int positionX, int positionY) {
        g.drawString(textLine, positionX, positionY);
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

}
