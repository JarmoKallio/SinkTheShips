/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.logic;

import java.util.ArrayList;
import ships.sinktheships.gameobjects.Coordinate;
import ships.sinktheships.gameobjects.Player;
import ships.sinktheships.gameobjects.Ship;
import ships.sinktheships.gui.Text;

/**
 * Tämän luokan ilmentymiin säilötään joukko tietoja, joita Outputs sitten
 * käyttää piirtämään näytölle halutun tilanteen.
 *
 * @author jambo
 */
public class DrawCommand {

    private static int timesUsed = 0;

    private int id;
    private ArrayList<Text> textLines = new ArrayList<>();
    private Player player;
    private Player adversary;
    private ShipPlacer placer;
    private Ship selectedShip;
    private Coordinate targetCoordinate;

    private boolean drawBackround = false;
    private boolean drawText = false;
    private boolean drawShipsAndGrid = false;
    private boolean drawGridNumbers = false;
    private boolean drawShots = false;
    private boolean containsPlayers = false;
    private boolean drawShipPlacer = false;
    private boolean highlightSelectedShip = false;
    private boolean drawTargetCoordinate = false;
    private boolean drawRadar = false;

    private boolean parametersChanged = false;

    /**
     * Konstruktori. Antaa ilmentymälle id-muuttujan sen mukaan, montako kertaa
     * konstruktoria on kutsuttu.
     */
    public DrawCommand() {
        this.id = timesUsed;
        timesUsed++;
    }

    /**
     * Getteri, antaa piirrettävän tähtäimen.
     *
     * @return Coordinate
     */
    public Coordinate getTargetCoordinate() {
        return targetCoordinate;
    }

    /**
     * Setteri, asettaa piirrettävän tähtäimen koordinaatin.
     *
     * @param targetCoordinate Coordinate
     */
    public void setTargetCoordinate(Coordinate targetCoordinate) {
        this.targetCoordinate = targetCoordinate;
    }

    /**
     * Getteri, piirretäänkö tähtäin.
     *
     * @return boolean
     */
    public boolean isDrawTargetCoordinate() {
        return drawTargetCoordinate;
    }

    /**
     * Piirretäänkö tähtäin vastustajan koordinaatistoon.
     *
     * @param drawTargetCoordinate boolean
     */
    public void setDrawTargetCoordinate(boolean drawTargetCoordinate) {
        this.drawTargetCoordinate = drawTargetCoordinate;
    }

    /**
     * Setteri, asettaa ShipPlacer olion.
     *
     * @param placer ShipPlacer
     */
    public void setPlacer(ShipPlacer placer) {
        this.placer = placer;
    }

    /**
     * Getteri.
     *
     * @return ShipPlacer
     */
    public ShipPlacer getPlacer() {
        return placer;
    }

    /**
     * Setteri, boolean, käytetäänkö ShipPlaceriä piirretääessä.
     */
    public void setDrawShipPlacer() {
        this.drawShipPlacer = true;
    }

    /**
     * Asettaa vastustajan.
     *
     * @param adversary vastustaja
     */
    public void setAdversary(Player adversary) {
        this.adversary = adversary;
    }

    /**
     * Asettaa pelaajan.
     *
     * @param player pelaaja
     */
    public void setPlayer(Player player) {
        this.player = player;
    }

    /**
     * Setteri, piirretäänkö taustaväri.
     *
     * @param drawBackround piirretäänkö
     */
    public void setDrawBackround(boolean drawBackround) {
        this.drawBackround = drawBackround;
    }

    /**
     * Piirretäänkö koordinaatistoon numerot, setteri.
     *
     * @param drawGridNumbers piirretäänkö, boolean.
     */
    public void setDrawGridNumbers(boolean drawGridNumbers) {
        this.drawGridNumbers = drawGridNumbers;
    }

    /**
     * Piirretäänkö laivat ja koordinaatistot, setteri.
     *
     * @param drawShipsAndGrid booelan, piirretäänkö
     */
    public void setDrawShipsAndGrid(boolean drawShipsAndGrid) {
        this.drawShipsAndGrid = drawShipsAndGrid;
    }

    /**
     * Piirretäänkö ammunnat, setteri.
     *
     * @param drawShots piirretäänkö, boolean
     */
    public void setDrawShots(boolean drawShots) {
        this.drawShots = drawShots;
    }

    /**
     * Setteri, piirretäänkö tekstiä.
     *
     * @param drawText boolean, riippuu siitä piirretäänkö
     */
    public void setDrawText(boolean drawText) {
        this.drawText = drawText;
    }

    /**
     * Lisää tekstilistaan uuden Tekst-olion, korvaan vanhan jolla sama
     * rivinumero.
     *
     * @param text tekstiolio
     */
    public void addText(Text text) {
        boolean updated = false;
        if (!this.textLines.isEmpty()) {
            for (Text x : this.textLines) {
                if (x.getLineNumber() == text.getLineNumber()) {
                    x.updateText(text.getText());
                    updated = true;
                }
            }
        }
        if (!updated) {
            this.textLines.add(text);
        }
    }

    /**
     * Getteri.
     *
     * @return lista tekstejä
     */
    public ArrayList<Text> getTexts() {
        return this.textLines;
    }

    /**
     * Getteri.
     *
     * @return tallennettu pelaaja
     */
    public Player getPlayer() {
        return this.player;
    }

    /**
     * Getter.
     *
     * @return boolean
     */
    public boolean isBackroundDrawn() {
        return this.drawBackround;
    }

    /**
     * Getteri.
     *
     * @return boolean
     */
    public boolean isTextDrawn() {
        return this.drawText;
    }

    /**
     * Setteri.
     *
     * @param drawShipPlacer boolean
     */
    public void setDrawShipPlacer(boolean drawShipPlacer) {
        this.drawShipPlacer = drawShipPlacer;
    }

    /**
     * Getteri.
     *
     * @return boolean
     */
    public boolean isDrawShipPlacer() {
        return drawShipPlacer;
    }

    /**
     * Piirretäänkö laivat ja koordinaatistot.
     *
     * @return boolean
     */
    public boolean areShipsAndGridDrawn() {
        return this.drawShipsAndGrid;
    }

    /**
     * Piirretäänkö koordinaatistoon numerot.
     *
     * @return boolean
     */
    public boolean areGridNumbersDrawn() {
        return this.drawGridNumbers;
    }

    /**
     * Kertoo piirretäänkö ammunnat. Nämä haetaan sitten lopulta pelaajien
     * listoista.
     *
     * @return boolean
     */
    public boolean areShotsDrawn() {
        return this.drawShots;
    }

    /**
     * Muuttaa boolean-arvoja vääriksi ja tyhjentää tekstit.
     */
    public void reset() {
        drawBackround = false;
        drawText = false;
        drawShipsAndGrid = false;
        drawGridNumbers = false;
        drawShots = false;
        drawTargetCoordinate = false;
        containsPlayers = false;
        textLines.clear();

    }

    /**
     * Setteri. Korostetaanko valittua laivaa ruudulla.
     *
     * @param b boolean
     */
    public void sethighlightSelectedShip(boolean b) {
        this.highlightSelectedShip = b;
    }

    /**
     * Setteri.
     *
     * @param selectedShip laiva
     */
    public void setSelectedShip(Ship selectedShip) {
        this.selectedShip = selectedShip;
    }

    /**
     * Kertoo piirretäänkö näytölle valittua laivaa eri värisenä.
     *
     * @return boolean
     */
    public boolean isHighlightSelectedShip() {
        return highlightSelectedShip;
    }

    /**
     * Palauttaa valitun laivan.
     *
     * @return laiva
     */
    public Ship getSelectedShip() {
        return selectedShip;
    }

    /**
     * Getteri.
     *
     * @return integer
     */
    public int getid() {
        return this.id;
    }

    @Override
    public boolean equals(Object obj) {
        DrawCommand draw = (DrawCommand) obj;
        return this.id == draw.getid();
    }

    /**
     * Kertoo onko parametreihin tullut muutoksia.
     *
     * @return booleanF
     */
    public boolean parametersChanged() {
        return this.parametersChanged;
    }

    /**
     * Asettaa onko parametreista jotkut muuttuneet. hyödyllistä piirrettäessä.
     *
     * @param parametersChanged uusi arvo, onko parametreihin tullut muutoksia
     */
    public void setParametersChanged(boolean parametersChanged) {
        this.parametersChanged = parametersChanged;
    }

    /**
     * Tyhjentää nykyisestä tekstirivilistasta kaiken.
     */
    public void resetTexts() {
        this.textLines.clear();
    }

    /**
     * Asettaa piirretäänkö tutka.
     *
     * @param b piirretäänkö tutka
     */
    public void setDrawRadar(boolean b) {
        this.drawRadar = true;
    }

    /**
     * Piirretäänkö tutka.
     *
     * @return boolean piirretäänkö tutka
     */
    public boolean isDrawRadar() {
        return drawRadar;
    }

}
