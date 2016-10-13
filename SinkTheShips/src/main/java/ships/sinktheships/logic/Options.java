/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.logic;

/**
 * Luokkaan tallennetaan pelin asetuksia, muiden luokkien oliot voivat käydä
 * hakemassa näitä tietoja.
 *
 * @author jambo
 */
public class Options {

    private static int maxFleetSize = 5;
    private static int xMin = 1; // laivaruudukon min arvo..
    private static int xMax = 10;
    private static int yMin = 1;
    private static int yMax = 10;

    private static int windowSizeX = 720;
    private static int windowSizeY = 720;

    public static int getMaxFleetSize() {
        return maxFleetSize;
    }

    public static int getWindowSizeX() {
        return windowSizeX;
    }

    public static int getWindowSizeY() {
        return windowSizeY;
    }

    static int getXmin() {
        return xMin;
    }

    static int getXmax() {
        return xMax;
    }

    static int getYmin() {
        return yMin;
    }

    static int getYmax() {
        return yMax;
    }

}
