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
    }

    /**
     * Ha jelenleg el van bújva az aszteroidájában, igaz, ellenkező esetben hamis.
     */
    private boolean isHidden;

    /**
     * Az aktuális tartózkodási helyének (aszteroida) tárolására szolgál.
     */
    private Asteroid currentAsteroid;

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

        if(currentAsteroid!= null){
            Logger.log("currentAsteroid.Hide(this)");
            currentAsteroid.Hide(this);
            Logger.returned();

        }
    }

    /**
     * Fúr egy egységnyit az aszteroida köpenyéből, ha még nincs teljesen átfúrva. Ha át van fúrva, nem történik művelet.
     */
    public void Drill() {
        // TODO implement here
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
    }

    /**
     * A jelenlegi aszeroida ezen keresztül szól a telep/robot-nak, hogy felrobbant.
     */
    public abstract void AsteroidExploded();

    /**
     * Örökölt függvény. Napvihar esetén hívódik meg.
     * Minden vűtjármű megsemmisül, ha nincs elbújva.
     */
    public void SolarFlare() {
        if(!isHidden){
            Logger.log("this.Die();");
            this.Die();
            Logger.returned();
        }
    }

    /**
     * Akkor hívódik meg, ha az adott körben már minden játékos lépett. A robotok ezt használják például a mozgásra.
     * @param closeToSun
     */
    public void RoundEnd(boolean closeToSun) {
        // TODO implement here
    }

    /**
     * Beállítja,hogy melyik játékos a tulajdonosa az űrjárműnek.
     * @param p
     */
    public void setOwner(Player p){
        owner = p;
    }

    /**
     * Megsemmisül az adott űrjármú.
     */
    public void Die(){
    //todo
    }


}