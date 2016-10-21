/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gameobjects;

/**
 * Luokan oloilla on koordinaatti ja tieto siitä, ovatko ne osumia vai huteja,
 * niitä käytetään esimerkiksi, kun piirretään osumat ja hudit
 * koordinaatistoihin.
 *
 * @author jambo
 */
public class Shot {

    private Coordinate coordinate;
    private boolean hit;

    /**
     * Luo uuden Shot-olion.
     *
     * @param coordinate ammunnan koordinaatti
     * @param hit tieto siitä, onko ammunta osuma
     */
    public Shot(Coordinate coordinate, boolean hit) {
        this.coordinate = coordinate;
        this.hit = hit;
    }

    /**
     * Palauttaa koordinaatin.
     *
     * @return koordinaatti
     */
    public Coordinate getCoordinate() {
        return coordinate;
    }

    /**
     * Kertoo, onko ammunta osuma.
     *
     * @return onko ammunta osuma
     */
    public boolean isHit() {
        return hit;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj.toString().equalsIgnoreCase(this.coordinate.toString())) {
            return true;
        }
        return false;
    }

}
