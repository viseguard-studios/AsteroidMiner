package com.viseguardstudios.asteroid_miner.model;

import com.viseguardstudios.asteroid_miner.skeleton.Logger;

/**
 * Egy-egy járműért (pl. telepes vagy robot) felelős osztály.
 */
public abstract class Vessel extends Entity {

    /**
     * Default constructor
     */
    public Vessel(Asteroid a) {
        a.Arrive(this);
        currentAsteroid = a;
        isHidden = false;
    }

    /**
     * Ha jelenleg el van bújva az aszteroidájában, igaz, ellenkező esetben hamis.
     */
    private boolean isHidden;

    /**
     * Az aktuális tartózkodási helyének (aszteroida) tárolására szolgál.
     */
    protected Asteroid currentAsteroid;

    /**
     * Tárolja, hogy melyik játékos irányítja az járművet.
     */
    private Player owner;

    /**
     * Az osztály konstruktora, beállítja az őt kezelő játékost illetve aszteroidát.
     * @param p 
     * @param a 
     * @return
     */
    public Vessel Vessel(Player p, Asteroid a) {
        // TODO implement here
        return null;
    }

    /**
     * Belebújik az adott aszteroidába, ha van benne elegendő hely. Ha nincsen, az aszteroida felszínén marad.
     */
    public void Hide() {
        // TODO implement here

        Logger.log("Check if is not hidden");
        if(!isHidden){
            Logger.functionCalled("currentAsteroid.Hide(this);");
            var canHide = currentAsteroid.Hide(this);
            Logger.returned();
            Logger.log("Hide, if it might be");
            isHidden = canHide;
        }
        else { Logger.log("Is already hidden, nothing more");}
    }

    /**
     * Fúr egy egységnyit az aszteroida köpenyéből, ha még nincs teljesen átfúrva. Ha át van fúrva, nem történik művelet.
     */
    public void Drill() {
        // TODO implement here

        Logger.functionCalled("currentAsteroid.Drill();");
        currentAsteroid.Drill();
        Logger.returned();
    }

    /**
     * Az jármű átlép egy szomszédos aszteroidára.
     * @param to
     */
    public void Move(Asteroid to) {
        // TODO implement here

        Logger.functionCalled("a.ReachableAsteroids()");
        var n = currentAsteroid.ReachableAsteroids();
        Logger.returned();

        Logger.log("Check if n has to: ");
        if(n.contains(to)){
            Logger.log("It has! 🎆");

            Logger.functionCalled("currentAsteroid.Depart(this);");
            this.currentAsteroid.Depart(this);
            Logger.returned();

            Logger.functionCalled("to.Arrive(this);");
            to.Arrive(this);
            Logger.returned();
        }
        else {
            Logger.log("It doesn't :(");
        }
    }

    /**
     * Absztrakt metódus, leszármazottól függően más értéket ad vissza. Meghatározza, hogy ha az adott jármű el szeretne bújni egy aszteroidában, mennyi helyre van hozzá szüksége.
     * @return
     */
    public abstract int GetHidingSpaceRequirement();

    /**
     * Felrobban  a jármű (egy aszteroida robbanásának hatására).
     */
    public void Explode() {
        // TODO implement here
    }

    /**
     * A jármű kibújik az aszteroida magjából, ha el volt bújva benne. Ha nem, nem történik művelet.
     */
    public void ExitHiding() {
        // TODO implement here

        Logger.log("Check if is hidden");
        if(isHidden){
            Logger.functionCalled("currentAsteroid.Exit(this)");
            currentAsteroid.Exit(this);
            Logger.returned();
            Logger.log("Set isHidden statement to false");
            isHidden = false;
        }
        else {
            Logger.log("Is not hidden, nothing more");
        }
    }

    /**
     * A jelenlegi aszeroida ezen keresztül szól a telep/robot-nak, hogy felrobbant.
     */
    public abstract void AsteroidExploded();

    /**
     * Örökölt függvény. Napvihar esetén hívódik meg.
     */
    public void SolarFlare() {
        // TODO implement here
    }

    /**
     * Akkor hívódik meg, ha az adott körben már minden játékos lépett. A robotok ezt használják például a mozgásra.
     * @param closeToSun
     */
    public void RoundEnd(boolean closeToSun) {
        // TODO implement here
    }

    public boolean getHidden(){
        return isHidden;
    }

    public void setHidden(boolean hidden){
        this.isHidden = hidden;
    }

}