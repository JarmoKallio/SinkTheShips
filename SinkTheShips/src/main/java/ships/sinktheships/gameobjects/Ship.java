/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gameobjects;

/**
 * Laivoja käytetään vastustajan laivojen tulittamiseen ja osumien ottamiseen,
 * niillä on sijaintimuuttujat, tiedot tyypistä ja metodeja esim tarkistamaan
 * onko osa laivaa ilmoitetussa koordinaattipisteessä.
 *
 * @author jambo
 */
public class Ship {

    private String shipTypeName;
    private int shipTypeId;
    private int size;
    private int sternXcoordinate;    //laivan perän sijainti
    private int sternYcoordinate;
    private int shipAngle;         //angle:   0      perä keskellä ja laiva "viisarina"
    private int damages; //     270   90
    private boolean destroyed; //        180
    private int[] coverageX;
    private int[] coverageY;

    /**
     * Luo laiva-olion.
     *
     * @param typeId laivan tyyppi, voi olla numero välillä 1-5
     * @param sternXcoordinate laivan perään x koordinaatti
     * @param sternYcoordinate perän y koordinaatti
     * @param shipAngle mihin laivan nokka osoittaa perästä katsottuna
     */
    public Ship(int typeId, int sternXcoordinate, int sternYcoordinate, int shipAngle) {

        this.destroyed = false;
        this.shipTypeName = shipName(typeId - 1);
        this.shipTypeId = typeId;     // shipTypeId on numero 1-5, 1=sub, jne
        this.size = shipSizeTable(typeId - 1); //laivan pituus ruutuina typeId pienimmillään1 joten vähennys koska metodi hakee tablesta
        this.sternXcoordinate = sternXcoordinate;
        this.sternYcoordinate = sternYcoordinate;
        this.shipAngle = shipAngle;
        this.damages = 0;

        this.coverageX = initializeCoverage(true, this.size, this.sternXcoordinate, this.shipAngle);
        this.coverageY = initializeCoverage(false, this.size, this.sternYcoordinate, this.shipAngle);

    }

    private String shipName(int index) {
        String[] names = {"Submarine", "Destroyer", "Cruiser", "Battleship", "Carrier"};
        return names[index];
    }

    private int shipSizeTable(int index) {
        int[] sizes = {2, 3, 4, 5, 6};
        return sizes[index];
    }

    public String getName() {
        return shipTypeName;
    }

    public int getSize() {
        return size;
    }

    /**
     * Tarkistaa onko osa laivaa annetussa sijainnissa.
     *
     * @param coordX tarkistettavan pisteen x-sijainti
     * @param coordY vastaavasti pisteen y-sijainti
     * @return boolean löytyikö laivaa vai ei
     */
    public boolean isThisShipHere(int coordX, int coordY) {
        if (tableHasInteger(coverageX, coordX) && tableHasInteger(coverageY, coordY)) {
            return true;
        } else {
            return false;
        }
    }

    public boolean isThisShipHere(Coordinate coordinate) {
        return isThisShipHere(coordinate.getxCoordinate(), coordinate.getyCoordinate());
    }

    private boolean tableHasInteger(int[] table, int testedValue) {
        if (table.length != 0) {
            for (int x : table) {
                if (x == testedValue) {
                    return true;
                }
            }
        }
        return false;
    }

    public void setDamages(int damages) {
        this.damages = damages;
        if (this.damages >= this.size) {
            this.destroyed = true;
        }
    }

    public boolean isDestroyed() {
        return destroyed;
    }

    public int getDamages() {
        return damages;
    }

    public int[] getCoverageX() {
        return coverageX;
    }

    public int[] getCoverageY() {
        return coverageY;
    }

    private int[] initializeCoverage(boolean xCoord, int size, int sternPos, int angle) {
        int[] partCoverage;
        if (xCoord) {
            partCoverage = fillTable(size, sternPos, angle == 270 || angle == 90, angle == 270);
        } else {
            partCoverage = fillTable(size, sternPos, angle == 0 || angle == 180, angle == 180);
        }

        return partCoverage;
    }

    private int[] fillTable(int size, int startValue, boolean cumulativ, boolean subtract) {
        //helper of the initializeCoverage method
        int[] table = new int[size];
        int addedVal = 0;
        if (cumulativ) {
            addedVal = 1;
        }
        if (subtract) {
            addedVal = -1;
        }
        table[0] = startValue;
        for (int i = 1; i < size; i++) {
            table[i] = table[i - 1] + addedVal;
        }
        return table;
    }

    /**
     * Vaihtaa laivan perän x-koordinaatin.
     *
     * @param x laivan perän uusi x-koordinaatti
     */
    public void newSternXcoordinate(int x) { //vaihtaa koko laivan x-sijainnin
        this.sternXcoordinate = x;
        this.coverageX = initializeCoverage(true, this.size, this.sternXcoordinate, this.shipAngle);

    }

    /**
     * Vaihtaa laivan perän y-koordinaatin.
     *
     * @param y laivan perän uusi y-koordinaatti
     */
    public void newSternYcoordinate(int y) {
        this.sternYcoordinate = y;
        this.coverageY = initializeCoverage(false, this.size, this.sternYcoordinate, this.shipAngle);
    }

    /**
     * Muuttaa laivan kulmaa. Oletusarvo on uudella laivalla niin, että perä eli
     * stern on alhaalla ja nokka osoittaa ylöspäin. Laiva on siis y-akselin
     * suuntainen. Metodi muuttaa kulmaa plussaamalla nykyiseen kulmaan 90
     * astetta JA alustaa laivan sijainnin uudestaan. Sekä x- että y-sijainnit
     * tai coveeraget niinkuin tässä on ny käytetty.
     */
    public void changeShipAngle() {
        this.shipAngle = this.shipAngle + 90;
        if (this.shipAngle == 360) {
            this.shipAngle = 0;
        }

        this.coverageX = initializeCoverage(true, this.size, this.sternXcoordinate, this.shipAngle);
        this.coverageY = initializeCoverage(false, this.size, this.sternYcoordinate, this.shipAngle);

    }

    public int getShipType() {
        return shipTypeId;
    }

    public int getSternXcoordinate() {
        return sternXcoordinate;
    }

    public int getSternYcoordinate() {
        return sternYcoordinate;
    }

    public int getShipAngle() {
        return this.shipAngle;
    }

    public void setShipAngle(int newAngle) {
        this.shipAngle = newAngle;
    }
}
