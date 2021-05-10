package com.viseguardstudios.asteroid_miner.view.panels.main_menu;

import com.viseguardstudios.asteroid_miner.model.Player;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class GameSetupScreen extends JDialog {

    List<Player> players = new ArrayList<>();
    Player selected;

    JTextField name;
    JList<String> playerList;

    public GameSetupScreen() {

        setup();
    }

    public GameSetupScreen(Frame owner, boolean modal) {
        super(owner, modal);
        setup();
    }

    private void setup() {
        players.add(new Player("Player_1"));
        players.add(new Player("Player_2"));
        players.add(new Player("Player_3"));

        this.setPreferredSize(new Dimension(600,300));

        //FlowLayout layout = new FlowLayout(FlowLayout.LEFT);
        //GridLayout layout = new GridLayout(1,2);
        //BoxLayout layout = new BoxLayout(this,BoxLayout.X_AXIS);
        BorderLayout layout = new BorderLayout();
        this.setLayout(layout);

        //The players List
        {
            //Main side panel
            JPanel list = new JPanel();

            BoxLayout listLayout = new BoxLayout(list, BoxLayout.Y_AXIS);
            list.setLayout(listLayout);

            ListModel<String> entityList = new AbstractListModel<String>() {
                @Override
                public int getSize() {
                    return players.size();
                }

                @Override
                public String getElementAt(int index) {
                    return players.get(index).getName();
                }
            };

            playerList = new JList<String>(entityList);
            playerList.addListSelectionListener(i -> {
                selectionChanged(playerList.getSelectedIndex());
            });

            Dimension d = playerList.getPreferredSize();
            d.width = 150;
            playerList.setPreferredSize(d);


            JScrollPane scroll = new JScrollPane(playerList);
            list.add(scroll);


            var buttonRow = new JPanel();
            var buttonRowLayout = new FlowLayout();
            buttonRow.setLayout(buttonRowLayout);

            Button add = new Button("+");
            add.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    var p = new Player("Player_" + (players.size() + 1));
                    players.add(p);
                    playerList.updateUI();
                }
            });

            Button remove = new Button("-");
            remove.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    if (selected != null) {
                        players.remove(selected);
                        playerList.updateUI();
                        selectionChanged(-1);
                    }
                }
            });

            buttonRow.add(add);
            buttonRow.add(remove);

            list.add(buttonRow);

            this.add(list, BorderLayout.LINE_START);
        }



        {
            JPanel editor = new JPanel();
            //BoxLayout editorLayout = new BoxLayout(editor,BoxLayout.Y_AXIS);
            //editor.setLayout(editorLayout);

            name = new JTextField();
            //name.setText("asdasdas");
            name.setPreferredSize(new Dimension(200, 20));
            name.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    selected.setName(e.getActionCommand());

                    playerList.repaint();
                }
            });

            editor.add(name);

            this.add(editor, BorderLayout.CENTER);
        }

        {
            JPanel bottom = new JPanel();
            FlowLayout bottomLayout = new FlowLayout();
            bottom.setLayout(bottomLayout);

            JButton ok = new JButton("start");
            bottom.add(ok);

            JButton cancel = new JButton("cancel");
            bottom.add(cancel);

            this.add(bottom, BorderLayout.PAGE_END);
        }



        this.pack();
    }



    List<Player> showDialog() {
        isAlwaysOnTop();
        setVisible(true);



        return players;
    }

    void selectionChanged(int index){
        if(index == -1){
            selected = null;
            name.setText("");
        }
        else {
            name.setText(players.get(index).getName());
            selected = players.get(index);
        }
    }
}
