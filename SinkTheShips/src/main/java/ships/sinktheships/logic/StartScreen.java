/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ships.sinktheships.logic;

import ships.sinktheships.gui.Text;

/**
 * Screen-luokan perivä logiikkaluokka, jota käytetään kun näytetään ohjelman
 * aloitusssivu.
 *
 * @author jambo
 */
public class StartScreen extends Screen {

    private Logic logic;
    private DrawCommand command;
    private Screen returnScreen = this;

    /**
     * Konstruktori.
     *
     * @param logic päälogiikka
     */
    public StartScreen(Logic logic) {
        this.logic = logic;

        this.command = new DrawCommand();
        command.addText(new Text("Welcome, my friends!", 1));
        command.addText(new Text("q to quit", 2));
        //command.addText(new Text("o for options", 3)); //jos toteutetaan niin oma loop
        command.addText(new Text("enter to start", 3));
        command.setDrawBackround(true);
        command.setDrawText(true);

    }

    @Override
    public Screen getReturnScreen() {
        //tähän voidaan vaihtaa seuraava haluttu looppi

        return returnScreen;
    }

    @Override
    public DrawCommand getDrawCommand() {
        return this.command;
    }

    @Override
    public void enterPressed() {
        this.returnScreen = new PlayerNameScreen(logic);
    }

    @Override
    public boolean qPressedToQuit() {
        return true;
    }

}
