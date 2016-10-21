/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.logic;

import java.util.ArrayList;
import ships.sinktheships.gameobjects.Player;
import ships.sinktheships.gui.Outputs;
import ships.sinktheships.gui.Frame;
import ships.sinktheships.gui.Inputs;

/**
 * Alustaa luokkia ohjelman suoritusta varten. Esimerkiksi luo uuden
 * päälogiikka-olion, Input ja Output- oliot sekä Scheduler-olion suorittamaan
 * ajoittaisia Outputs-piirtoluokan päivityksiä.
 *
 * @author jambo
 */
public class Initialize {

    private Inputs input;
    private Frame window;
    private Outputs drawOnScreen;

    /**
     * Suorittaa alustuksen.
     */
    public Initialize() {

        Logic logiikka = new Logic();
        this.input = new Inputs(logiikka);
        this.drawOnScreen = new Outputs(logiikka);
        window = new Frame(Options.getWindowSizeX(), Options.getWindowSizeY());
        window.addInputToFrame(this.input);

        window.addOutputToFrame(drawOnScreen);
        Scheduler sche = new Scheduler(drawOnScreen);

    }

}
