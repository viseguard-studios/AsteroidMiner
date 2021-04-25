package com.viseguardstudios.asteroid_miner.view.panels.ingame;

import com.viseguardstudios.asteroid_miner.util.StateChangedListener;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ConsoleLogPanel extends JPanel implements StateChangedListener {

    private List<String> log = new ArrayList<>();


    public ConsoleLogPanel() {

    }

    @Override
    public void stateChanged() {

    }
}
