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
public class Shot {

    private Coordinate coordinate;
    private boolean hit;

    public Shot(Coordinate coordinate, boolean hit) {
        this.coordinate = coordinate;
        this.hit = hit;
    }

    public Coordinate getCoordinate() {
        return coordinate;
    }

    public boolean isHit() {
        return hit;
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
        return this.getCoordinate().hashCode();
    }

}
