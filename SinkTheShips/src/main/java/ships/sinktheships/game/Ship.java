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
        this.type = type;
        this.size = shipSize(type);
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
        if (coverageX[0] <= coordX && coverageX[this.size - 1] >= coordY && coverageY[0] <= coordY
                && coverageY[this.size - 1] >= coordY) {
            return true;
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

    public int[] initializeCoverage(boolean xCoord, int size, int sternPos, int angle) {
        int[] partCoverage;

        if (angle == 0) {
            if (xCoord) {
                partCoverage = fillTable(size, sternPos, false, false);
            } else {
                partCoverage = fillTable(size, sternPos, true, false);
            }
        } else if (angle == 90) {
            if (xCoord) {
                partCoverage = fillTable(size, sternPos, true, false);
            } else {
                partCoverage = fillTable(size, sternPos, false, false);
            }
        } else if (angle == 180) {
            if (xCoord) {
                partCoverage = fillTable(size, sternPos, false, false);
            } else {
                partCoverage = fillTable(size, sternPos, true, true);
            }
        } else if (xCoord) {  //loput vastaa tapausta angle==270
            partCoverage = fillTable(size, sternPos, true, true);
        } else {
            partCoverage = fillTable(size, sternPos, false, false);
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

}