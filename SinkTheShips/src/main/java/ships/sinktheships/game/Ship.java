/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.game;

/**
 *
 * @author jambo
 */
public class Ship {

    private String player;
    private String name;
    private int type;
    private int size;
    private int sternX;    //laivan perän sijainti
    private int sternY;
    private int angle;         //angle:   0      perä keskellä ja laiva "viisarina"
    private boolean[] damages; //     270   90
    private boolean destroyed; //        180
    private int[] coverageX;
    private int[] coverageY;

    public Ship(String player, int type, int sternX, int sternY, int angleInput) {
        this.player = player;
        this.destroyed = false;
        this.name = shipName(type);
        this.type = type;     // type on numero 1-5, 1 =sub, jne
        this.size = shipSize(type - 1); //laivan pituus ruutuina
        this.sternX = sternX;
        this.sternY = sternY;
        this.angle = angleInput;
        this.damages = new boolean[this.size];
        initializeDamages();
        this.coverageX = initializeCoverage(true, this.size, this.sternX, this.angle);
        this.coverageY = initializeCoverage(false, this.size, this.sternY, this.angle);

    }

    private String shipName(int index) {
        String[] names = {"Submarine", "Destroyer", "Cruiser", "Battleship", "Carrier"};
        return names[index];
    }

    private int shipSize(int index) {
        int[] sizes = {2, 3, 4, 5, 6};
        return sizes[index];
    }

    public boolean[] getDamages() {
        return damages;
    }

    public String getName() {
        return name;
    }

    public int getSize() {
        return size;
    }

    public boolean isThisShipHere(int coordX, int coordY) {
        if (tableHasInteger(coverageX, coordX) && tableHasInteger(coverageY, coordY)) {
            return true;
        } else {
            return false;
        }
    }

    private boolean tableHasInteger(int[] table, int test) {
        for (int x : table) {
            if (x == test) {
                return true;
            }
        }
        return false;

    }

    private void updateDamages() {

    }

    private void initializeDamages() {
        for (int i = 0; i < this.damages.length; i++) {
            this.damages[i] = false;  //aluksi aina damaged on false
        }

    }

    public int[] getCoverageX() {
        return coverageX;
    }

    public int[] getCoverageY() {
        return coverageY;
    }

    public int[] initializeCoverage(boolean xCoord, int size, int sternPos, int angle) {
        int[] partCoverage;
        if (xCoord) {
            partCoverage = fillTable(size, sternPos, angle == 270 || angle == 90, angle == 270);
        } else {
            partCoverage = fillTable(size, sternPos, angle == 0 || angle == 180, angle == 180);
        }

        return partCoverage;
    }

    public int[] fillTable(int size, int startValue, boolean cumulativ, boolean subtract) {
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

    public void newCoordX(int coordX) { //vaihtaa koko laivan x-sijainnin
        this.sternX = coordX;
        this.coverageX = initializeCoverage(true, this.size, this.sternX, this.angle);

    }

    public void newCoordY(int coordY) {
        this.sternY = coordY;
        this.coverageY = initializeCoverage(false, this.size, this.sternY, this.angle);
    }

    public void changeAngle() {
        if (this.angle + 90 < 270) {
            this.angle = 0;
        } else {
            this.angle = this.angle + 90;
        }
        this.coverageX = initializeCoverage(true, this.size, this.sternX, this.angle);
        this.coverageY = initializeCoverage(false, this.size, this.sternY, this.angle);

    }

    public int getType() {
        return type;
    }

    void unChangeAngle() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public int getSternX() {
        return sternX;
    }

    public int getSternY() {
        return sternY;
    }

}
