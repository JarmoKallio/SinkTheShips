/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.gameobjects;

/**
 * Luokan ilmentymiin tallennetaan tiedot koordinaatistoon tehdyistä ns
 * ammunnoista.
 *
 * @author jambo
 */
public class Sea {

    //molemmilla pelaajilla yksi, tähän tallennetaan kaikki ammunnat, osumat ja hudit JA
    // piirretään tilanne
    public static int ruudukonKoko = 13;
    public int koko;

    public Sea() {
        this.koko = ruudukonKoko;
    }

}
