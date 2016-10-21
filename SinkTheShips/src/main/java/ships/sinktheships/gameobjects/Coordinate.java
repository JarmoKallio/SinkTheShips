/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gameobjects;

/**
 * Luokan alkioilla kaksi muuttujaa, x- ja y- koordinaatit.
 *
 * @author jambo
 */
public class Coordinate {

    private int xCoordinate;
    private int yCoordinate;

    /**
     * Oliolle asetetaan x- ja y- koordinaatit.
     *
     * @param xCoordinate koordinaatti
     * @param yCoordinate koordinaatti
     */
    public Coordinate(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    /**
     * Setteri.
     *
     * @param xCoordinate koordinaatti
     */
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    /**
     * Setteri.
     *
     * @param yCoordinate koordinaatti
     */
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    /**
     * Getteri.
     *
     * @return x-koordinaatti
     */
    public int getxCoordinate() {
        return xCoordinate;
    }

    /**
     * Getteri.
     *
     * @return y-koordinaatti
     */
    public int getyCoordinate() {
        return yCoordinate;
    }

    @Override
    public String toString() {
        return "x: " + this.xCoordinate + "  y: " + this.yCoordinate;
    }

}
