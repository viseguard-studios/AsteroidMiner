package com.viseguardstudios.asteroid_miner.model.entities.Vessel;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;
import com.viseguardstudios.asteroid_miner.model.Player;
import com.viseguardstudios.asteroid_miner.model.entities.Entity;
import com.viseguardstudios.asteroid_miner.model.entities.MovableEntity;
import com.viseguardstudios.asteroid_miner.model.inventory.IInventory;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.recipe.Recipe;
import com.viseguardstudios.asteroid_miner.skeleton.Logger;

/**
 * Egy-egy járműért (pl. telepes vagy robot) felelős osztály.
 */
public abstract class Vessel extends MovableEntity {

    /**
     * Default constructor
     */
    public Vessel(Asteroid a) {
        a.arrive(this);
        arriveTo(a);
        isHidden = false;
    }

    /**
     * Ha jelenleg el van bújva az aszteroidájában, igaz, ellenkező esetben hamis.
     */
    private boolean isHidden;

    /**
     * Tárolja, hogy melyik játékos irányítja az járművet.
     */
    protected Player owner;

    /**
     * Item áthelyezése
     * @return
     */
    public abstract boolean placeItem(Item i);

    /**
     * Belebújik az adott aszteroidába, ha van benne elegendő hely. Ha nincsen, az aszteroida felszínén marad.
     */
    public boolean Hide() {
        if(!isHidden){
            var canHide = currentAsteroid.Hide(this);
            isHidden = canHide;
            return isHidden;
        }
        return false;
    }

    /**
     * Fúr egy egységnyit az aszteroida köpenyéből, ha még nincs teljesen átfúrva. Ha át van fúrva, nem történik művelet.
     */
    public boolean drill() {
        if(turnUsed)
            return false;

        var res =currentAsteroid.Drill();
        if(res)
            turnUsed = true;
        return res;
    }

    /**
     * Bányászat, ha lehetséges
     */
    public Item mine(){
        if(turnUsed){
            return null;
        }

        Item mined = null;
        if(!isHidden){
            mined = currentAsteroid.Mine();
            if(mined != null)
                this.getInventory().insertItem(mined);

            turnUsed = true;
        }
        return mined;
    }

    /**
     * Absztrakt metódus, leszármazottól függően más értéket ad vissza. Meghatározza, hogy ha az adott jármű el szeretne bújni egy aszteroidában, mennyi helyre van hozzá szüksége.
     * @return
     */
    public abstract int GetHidingSpaceRequirement();

    /**
     * Felrobban  a jármű (egy aszteroida robbanásának hatására).
     */
    public void explode() {
        super.explode();
        scene.removeEntity(this);
    }

    /**
     * A jármű kibújik az aszteroida magjából, ha el volt bújva benne. Ha nem, nem történik művelet.
     */
    public boolean ExitHiding() {
        if(isHidden){
            currentAsteroid.Exit(this);
            isHidden = false;
            return true;
        }
        return false;
    }


    /**
     * Örökölt függvény. Napvihar esetén hívódik meg.
     * Minden vűtjármű megsemmisül, ha nincs elbújva.
     */
    public void SolarFlare() {
        if(!isHidden){
            explode();
        }
    }

    /**
     * Akkor hívódik meg, ha az adott körben már minden játékos lépett. A robotok ezt használják például a mozgásra.
     * @param closeToSun
     */
    public void roundEnd(boolean closeToSun) {
        super.roundEnd(closeToSun);
    }

    /**
     * Craft-oláshoz szükséges, csak Spaceship tud craftolni
     */
    public abstract boolean Craft(Recipe recipe);

    /**
     * Beállítja,hogy melyik játékos a tulajdonosa az űrjárműnek.
     * @param p
     */
    public void setOwner(Player p){
        owner = p;
    }

    public boolean getHidden(){
        return isHidden;
    }

    public void setHidden(boolean hidden){
        this.isHidden = hidden;
    }

    public abstract IInventory getInventory();

}