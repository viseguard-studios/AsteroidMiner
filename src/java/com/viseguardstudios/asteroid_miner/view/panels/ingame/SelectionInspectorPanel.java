package com.viseguardstudios.asteroid_miner.view.panels.ingame;

import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.Entity;
import com.viseguardstudios.asteroid_miner.util.StateChangedListener;

import javax.swing.*;
import java.awt.*;

public class SelectionInspectorPanel extends JPanel implements StateChangedListener {


    private Scene scene;

    private Entity selected;

    JTextArea t2;

    public SelectionInspectorPanel() {
        scene = Engine.getInstance().getScene();
        scene.getManager().addListener(this);

        this.setPreferredSize(new Dimension(200,200));

        this.setBackground(new Color(60,63,65));
        this.setForeground(new Color(187,187,187));

        GridLayout layoutMgr = new GridLayout(2,0);
        this.setLayout(layoutMgr);

        String[] data = {"one", "two", "three", "four"};
        ListModel<String> entityList = new AbstractListModel<String>() {
            @Override
            public int getSize() {
                return scene.getEntities().size();
            }

            @Override
            public String getElementAt(int index) {
                return scene.getEntities().get(index).getName();
            }
        };

        JList<String> myList = new JList<String>(entityList);
        JScrollPane scroll = new JScrollPane(myList);
        this.add(scroll);
        myList.addListSelectionListener(i->{ selectionChanged(myList.getSelectedIndex()); });

        t2 = new JTextArea();
        t2.setEditable(false);

        this.add(t2);
    }

    void selectionChanged(int index){
        var sel = scene.getEntities().get(index);
        //System.out.println(sel.getName());
        scene.getManager().setSelectedEntity(sel);

    }

    @Override
    public void stateChanged() {
        if(selected != scene.getManager().getSelectedEntity()){
            selected = scene.getManager().getSelectedEntity();
            //var text = selected.printStatus();
            //t2.setText(text);
        }
        if(selected != null) {
            var text = selected.printStatus();
            t2.setText(text);
        }
        else {
            t2.setText("");
        }
    }
}
