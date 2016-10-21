/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.logic;

/**
 * Luokan olio kutsuu säännöllisesti Outputs-piirtoluokan päivitysmetodia.
 *
 * @author jambo
 */
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import ships.sinktheships.gui.Outputs;

/**
 * Konstruktori.
 *
 * @author jambo
 */
public class Scheduler {

    private Outputs drawer;

    /**
     * Metodi kutsuu säännöllisesti parametrina annetun luokan metodia.
     *
     * @param drawer Outputs
     */
    public Scheduler(Outputs drawer) {
        this.drawer = drawer;

        final ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        service.scheduleWithFixedDelay(new Runnable() {
            @Override
            public void run() {
                drawer.update();
                //drawer.test();
            }
        }, 0, 100, TimeUnit.MILLISECONDS);
    }
}
