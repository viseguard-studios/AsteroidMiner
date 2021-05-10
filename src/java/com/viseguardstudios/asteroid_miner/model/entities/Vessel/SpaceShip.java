package com.viseguardstudios.asteroid_miner.model.entities.Vessel;

import com.viseguardstudios.asteroid_miner.model.Engine;
import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.inventory.SSInventory;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.item.TeleportGateItem;
import com.viseguardstudios.asteroid_miner.model.recipe.Recipe;
import com.viseguardstudios.asteroid_miner.model.recipe.RobotRecipe;
import com.viseguardstudios.asteroid_miner.model.recipe.SpaceStationRecipe;
import com.viseguardstudios.asteroid_miner.model.recipe.TeleportGateRecipe;
import com.viseguardstudios.asteroid_miner.util.Sprite;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * A telepesekért felelős osztály.
 */
public class SpaceShip extends Vessel {

    private static final Sprite s = new Sprite("assets/graphics/sprites/ship.png", 10);

    static List<String> actions = List.of("move","hide","drill","mine","activate","create","stash");


    /**
     * Default constructor
     */
    public SpaceShip(Asteroid a) {
        super(a);
        inventory = new SSInventory();
    }

    public SpaceShip(Asteroid a, Player owner, String name) {
        super(a);
        inventory = new SSInventory();
        this.owner = owner;
        owner.addVessel(this);
        this.name = name;
    }

    /**
     * Elem belehelyezése az aktuális aszteroidába, amin tartózkodik. Sikeres behelyezés után igazzal tér vissza, a saját raktárból eltávolítja az elemet. Sikertelen művelet után hamissal tér vissza.
     * @param i
     * @return
     */
    @Override
    public boolean placeItem(Item i) {

        if(currentAsteroid.placeItem(i)){
            inventory.removeItem(i);
            return true;
        }
        return false;
    }

    /**
     * A telepes által folyamatosan hordozott raktár.
     */
    private SSInventory inventory;

    /**
     * A telepesek által az aszteroida magjában elfoglalt hely nagyságát adja vissza.
     */
    public int GetHidingSpaceRequirement() {
        return 1;
    }

    /**
     * A telepes az aktuálisan rendelkezésére álló elemekből egy "receptet" készít.  A felhasznált elemek elhasználódnak, törlődnek a raktárból.
     */
    public boolean Craft(Recipe recipe) {
        if(!turnUsed){
            boolean cancraft = recipe.canCraft(this);
            if (cancraft){
                recipe.craft(this);
                turnUsed=true;
                return true;
            }
        }
        return false;
    }

    /**
     * A megadott tárgyon hív egy Activate()-et
     * @param i
     */
    public void ActivateItem(Item i) {
        i.activate(inventory, this);
    }

    @Override
    public void explode() {
        super.explode();
        inventory.explode();
        scene.getManager().removeSettler(this);
    }

    @Override
    public List<String> getActions() {
        return actions;
    }

    @Override
    public void doAction(String[] args) {
        if(owner.equals(Engine.getInstance().getGameManager().getCurrentPlayer()))
            super.doAction(args);
        if(args[0].equals("drill")){
            this.drill();
            scene.getManager().notifyListeners();
        }
        if(args[0].equals("mine")){
            this.mine();
            scene.getManager().notifyListeners();
        }
        if(args[0].equals("hide")){
            if(!getHidden()) {
                this.Hide();
            }else if(!turnUsed)
            {
                this.ExitHiding();
            }
            scene.getManager().notifyListeners();
        }
        if(args[0].equals("activate")) {
            if (!turnUsed) {
                List<TeleportGateItem> gates = this.getInventory().getGates();

                List<String> posib = new ArrayList<>();
                for (TeleportGateItem g : gates
                ) {
                    posib.add(g.getName());
                }
                var window = Engine.getInstance().getMainWindow();

                String res = (String) JOptionPane.showInputDialog(window, "Please choose a gate to activate: ", "Choose", JOptionPane.PLAIN_MESSAGE, null, posib.toArray(), "");

                if (res != null) {
                    for (TeleportGateItem g : getInventory().getGates()) {
                        if (g.getName().equals(res)) {
                            g.activate(getInventory(), this);
                            scene.getManager().notifyListeners();
                            break;
                        }
                    }
                }
            }
        }
            if(args[0].equals("stash")) {
                if (!turnUsed) {
                    List<Item> items = this.getInventory().getItems();

                    List<String> posib = new ArrayList<>();
                    for (Item i : items
                    ) {
                        posib.add(i.getName());
                    }
                    var window = Engine.getInstance().getMainWindow();

                    String res = (String) JOptionPane.showInputDialog(window, "Please choose an item to place: ", "Choose", JOptionPane.PLAIN_MESSAGE, null, posib.toArray(), "");

                    if (res != null) {
                        for (Item i : getInventory().getItems()) {
                            if (i.getName().equals(res)) {
                                if (currentAsteroid.placeItem(i)) inventory.removeItem(i);
                                scene.getManager().notifyListeners();
                                turnUsed=true;
                                break;
                            }
                        }
                    }
                }

        }
        if(args[0].equals("create")) {
            if (!turnUsed) {
                List<String> posib = new ArrayList<>() {
                    {
                    add("TeleportGate");
                    add("SpaceStation");
                    add("Robot");
                    }
                };
                var window = Engine.getInstance().getMainWindow();
                String res = (String) JOptionPane.showInputDialog(window, "What do you want to craft?", "Choose", JOptionPane.PLAIN_MESSAGE, null, posib.toArray(), "");
                boolean possible = false;
                switch (res){
                    case "TeleportGate":
                        possible = this.Craft(new TeleportGateRecipe());
                        scene.getManager().notifyListeners();
                        break;
                    case "Robot":
                        possible = this.Craft(new RobotRecipe());
                        scene.getManager().notifyListeners();
                        break;
                    case "SpaceStation":
                        possible = this.Craft(new SpaceStationRecipe());
                        scene.getManager().notifyListeners();
                        break;
                    default:
                        break;
                }
                String result;
                if(possible){
                    result ="You successfully crafted the recipe.";
                    turnUsed=true;
                } else
                    result="You can't craft this recipe";

                JOptionPane.showMessageDialog(window, result);
            }
        }

    }

    /**
     * A raktár getter-e
     * @return inventory
     */
    public SSInventory getInventory() {
        return inventory;
    }

    /**
     * A tulajdonos getter-e
     * @return owner
     */
    public Player getOwner() {return owner; }



    @Override
    public AsteroidPlaces getPlace() {
        return AsteroidPlaces.Vessel;
    }

    @Override
    public String printStatus() {
        String status = super.printStatus() + "\n";
        status = status + "Owner: " + owner.getName() + "\n";
        status = status + "Hidden: " + getHidden() + "\n";
        status = status.concat("Turn used: " + (turnUsed ? "true":"false")+ "\n");

        status = status.concat(currentAsteroid.getName()+"\n");

        status = status.concat("Resources:\n");
        for (var item : inventory.getItems()) {
          status =  status.concat("- "+ item.getName()+"\n");
        }

        status = status.concat("Teleport Gates:\n");
        for (var item : inventory.getGates()) {
           status = status.concat("- "+ item.getName()+"\n");
        }

        return status;
    }

    @Override
    public Sprite getSprite() {
        return s;
    }
}