/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gameobjects;

/**
 * Laivoja käytetään vastustajan laivojen tulittamiseen ja osumien ottamiseen,
 * niillä on sijaintimuuttujat, tiedot tyypistä ja metodeja esimerkiksi
 * tarkistamaan onko osa laivaa ilmoitetussa koordinaattipisteessä.
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

    /**
     * Palautta laivan nimen, eli esimerkiksi submarine.
     *
     * @return laivan nimi
     */
    public String getName() {
        return shipTypeName;
    }

    /**
     * Palauttaa laivan koon, joka on siis sama kuin laivan täyttämien
     * koordinaatistoruutujen määrä.
     *
     * @return kokonaisluku laivan koko
     */
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

    /**
     * Kertoo löytyykö tämä laiva annetusta koordinaatista.
     *
     * @param coordinate annettu koordinaatti
     * @return palauttaa true jos osa laivaa on annetussa koordinaatissa, muuten
     * false
     */
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

    /**
     * Asettaa laivalle uudet damaget tai vauriot. Jos vaurioita on yhtä paljon
     * kuin laivan koko numeroina, asetetaan laiva tuhoutuneeksi. Tuhoutuneeseen
     * laivaan ei lisätä vaurioita.
     *
     * @param damages laivan vaurioiden uusi määrä
     */
    public void setDamages(int damages) {
        if (!this.destroyed) {
            System.out.println("dama" + this.damages);
            this.damages = damages;
            if (this.damages >= this.size) {
                this.destroyed = true;
            }
        }
    }

    /**
     * Kertoo onko laiva tuhoutunut.
     *
     * @return totta jos laiva tuhoutunut
     */
    public boolean isDestroyed() {
        return destroyed;
    }

    /**
     * Palauttaa laivan vaurioiden määrä.
     *
     * @return vaurioiden määrä
     */
    public int getDamages() {
        return damages;
    }

    /**
     * Kertoo montako kertaa laiva voi vielä tulittaa. Laiva voi tulittaa
     * kokonsa verran, josta vähennetään vaurioiden lukumäärä.
     *
     * @return kokonaisluku montako kertaa laiva voi tulittaa
     */
    public int canFireXTimes() {
        return this.size - this.damages;
    }

    /**
     * Kertoo laivan palasten sijaintien x-koordinaatit.
     *
     * @return taulukko jossa x-koordinaatit
     */
    public int[] getCoverageX() {
        return coverageX;
    }

    /**
     * Kertoo laivan palasten sijaintien y-koordinaatit.
     *
     * @return taulukko josta löytyy y-koordinaatit
     */
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

    /**
     * Palauttaa laivan tyyppi id:n eli numeron välillä 1-5, sukellusveneen id
     * on 1 ja lentotukialuksen 5.
     *
     * @return integer joka riippuu laivan tyypistä
     */
    public int getShipType() {
        return shipTypeId;
    }

    /**
     * Palauttaa laivan perän x-koordinaatin.
     *
     * @return perän x-koordinaatti
     */
    public int getSternXcoordinate() {
        return sternXcoordinate;
    }

    /**
     * Palauttaa laivan perän y-koordinaatin.
     *
     * @return perän y-koordinaatti
     */
    public int getSternYcoordinate() {
        return sternYcoordinate;
    }

    /**
     * Palauttaa laivan kulman. Kulma voi olla 0, 90, 180 tai 270. Kulma on se
     * kulma, joka syntyy kun laiva toimii ns viisarina ja laivan perän
     * koordinaatti on viisarin akseli. Ajatellaan, että laiva on kuin kellon
     * viisari, kun se osoittaa kahteentoista, kulma on nolla, kulma kasvaa
     * myötäpäivään, eli kello kolme tarkoitta 90 astetta.
     *
     * @return laivan kulma.
     */
    public int getShipAngle() {
        return this.shipAngle;
    }

    /**
     * Asettaa uuden kulman.
     *
     * @param newAngle uusi kulma
     */
    public void setShipAngle(int newAngle) {
        this.shipAngle = newAngle;
    }

    /**
     * Palauttaa laivan perän sijainnin Coordinate-oliona, koordinaattina.
     *
     * @return perän koordinaatti
     */
    public Coordinate getSternCoordinate() {
        return new Coordinate(sternXcoordinate, sternYcoordinate);
    }
}
