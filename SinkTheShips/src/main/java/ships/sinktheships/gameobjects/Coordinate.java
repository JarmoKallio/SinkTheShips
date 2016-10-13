/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gameobjects;

/**
 *
 * @author jambo
 */
public class Coordinate {

    private int xCoordinate;
    private int yCoordinate;

    public Coordinate(int xCoordinate, int yCoordinate) {
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
    }

    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }

    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }

    public int getxCoordinate() {
        return xCoordinate;
    }

    public int getyCoordinate() {
        return yCoordinate;
    }

    @Override
    public String toString() {
        return "x: " + this.xCoordinate + "  y: " + this.yCoordinate;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.hashCode() == this.hashCode()) {
            return true;
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Integer.parseInt(xCoordinate + "" + yCoordinate);
    }

}
