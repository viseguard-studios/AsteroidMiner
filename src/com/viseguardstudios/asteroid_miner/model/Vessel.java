package com.viseguardstudios.asteroid_miner.model;

/**
 * Egy-egy járműért (pl. telepes vagy robot) felelős osztály.
 */
public abstract class Vessel extends Entity {

    /**
     * Default constructor
     */
    public Vessel() {
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
        // TODO implement here
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

        LOG.CallFunc("a.ReachableAsteroids()");
        var n = currentAsteroid.ReachableAsteroids();
        LOG.Returned();

        LOG.Log("Check if n has to: ");
        if(n.contains(to)){
            LOG.Log("It has! 🎆");
        }
        else {
            LOG.Log("It doesn't :(");
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
     */
    public void SolarFlare() {
        // TODO implement here
    }

    /**
     * @param closeToSun
     */
    public void RoundEnd(bool closeToSun) {
        // TODO implement here
    }

    /**
     * Akkor hívódik meg, ha az adott körben már minden játékos lépett. A robotok ezt használják például a mozgásra.
     * @param closeToSun
     */
    public abstract void RoundEnd(bool closeToSun);

    /**
     * Napviharról értesíti az egységet.
     */
    public abstract void SolarFlare();

}