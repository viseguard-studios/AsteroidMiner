package com.viseguardstudios.asteroid_miner.view;

import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.util.StateChangedListener;
import com.viseguardstudios.asteroid_miner.view.panels.ingame.EndGame;
import com.viseguardstudios.asteroid_miner.view.panels.ingame.InGameGUI;
import com.viseguardstudios.asteroid_miner.view.panels.ingame.MapViewPanel;
import com.viseguardstudios.asteroid_miner.view.panels.ingame.SelectionInspectorPanel;
import com.viseguardstudios.asteroid_miner.view.panels.main_menu.MainMenu;

import javax.swing.*;
import java.awt.*;

/**
 * The main window of the application
 * This is what hosts the MainView Component that contains all sub-parts
 */
public class MainWindow extends JFrame implements StateChangedListener {

    Engine eng;


    JPanel active;

    public MainWindow() throws HeadlessException {
        ToolTipManager.sharedInstance().setInitialDelay(100);

        eng = Engine.getInstance();
        eng.addListener(this);

        //InGameGUI gui = new InGameGUI();
        MainMenu menu = new MainMenu();

        this.add(menu);
        active = menu;

        this.pack();
        //this.setSize(new Dimension(0,500));
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        this.setResizable(false);
    }

    @Override
    public void stateChanged() {
        switch (eng.getGameState()){
            case MainMenu:
                this.remove(active);
                MainMenu menu = new MainMenu();
                this.add(menu);
                active = menu;
                break;
            case InGame:
                this.remove(active);
                InGameGUI gui = new InGameGUI();
                this.add(gui);
                active = gui;
                break;
            case Won:
                this.remove(active);
                EndGame win = new EndGame(true);
                this.add(win);
                active = win;
                break;
            case Lost:
                this.remove(active);
                EndGame lost = new EndGame(false);
                this.add(lost);
                active = lost;
                break;
        }

        this.pack();
        this.setLocationRelativeTo(null);
        this.setVisible(true);
    }

    @Override
    public void dispose() {
        super.dispose();
        System.exit(0);
    }
}