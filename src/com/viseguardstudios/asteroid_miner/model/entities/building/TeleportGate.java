package com.viseguardstudios.asteroid_miner.model.entities.building;

import com.viseguardstudios.asteroid_miner.model.entities.Asteroid;

import java.util.*;

/**
 * A teleportkapukat reprezentáló osztály.
 */
public class TeleportGate extends Building {

    /**
     * az eddig lerakott teleportkapuk azonosítókkal párosítva.
     */
    private static TreeMap<TeleportGate,Integer> idList;
    private int gateId;
    private TeleportGate pair = null;
    /**
    * Az aszteroida alapértelmezetten aktív, deaktiválódik a saját vagy a párja robbanása során
     */
    private boolean active = true;

    /**
     * Default constructor
     */
    public TeleportGate(int id) {
        id =gateId;
    }

    @Override
    public void RoundEnd(boolean closeToSun) {}

    /**
     * Napszél éri a kaput, aminek hatására egy véletlenszerű, szomszédos aszteroidára mozog át.
     */
    @Override
    public void SolarFlare() {
        ArrayList<Asteroid> neighbours = (ArrayList<Asteroid>) currentAsteroid.getPhysicalNeighbours();
        if(neighbours.size()==0) return; //ha nincs szomszéd, nem tud mozogni
        int bound = neighbours.size()-1;
        //választ egy random indexet a szomszédok listájának lehetséges indexei közül
        Random rand = new Random();
        int chosen = rand.nextInt(bound);
        Asteroid to = neighbours.get(chosen);
        //vándorlás a választott aszteroidára
        currentAsteroid.removeBuilding(this);
        to.AddBuilding(this);
    }


    public void setPair(TeleportGate tg){
        if(pair ==null && active)
             pair = tg;
    }

    /**
     * Visszaadja az ebből a kapuból elérhető extra aszteroidát, ha van párja.
     * @return
     */
    public Asteroid getRoutes() {
       if(pair==null) return null;
       return pair.getCurrentAsteroid();
    }

    /**
     * Akkor hívódik meg ha a párja megsemmisül és ezáltal ez az oldal deaktiválódik.
     */
    public void PairDestroyed() {
        this.pair = null;
        active=false;
    }

    /**
     * Akkor hívódik meg ha az épület felrobban. Ekkor szól a szomszédjának is a PairDestroyed() függvénnyel.
     */
    @Override
    public void explode() {
        if(pair != null)
            pair.PairDestroyed();
        active = false;
    }

    @Override
    public AsteroidPlaces getPlace() {
        //TODO If it started to wander it should return vessel to ensure it has space
        return AsteroidPlaces.Orbit;
    }

    /**
     *Teleportkapu párjának megkeresése ID alapján, ha létezik
     */
    public TeleportGate getIdPair(int id, TeleportGate tg){
        for(Map.Entry<TeleportGate,Integer> i: idList.entrySet()) {
            if(i.getValue().equals(id) && !i.getKey().equals(tg)) //id megegyezik és nem saját maga a kapu
                return i.getKey();
        }
        return null; //ha nem találta meg
    }

    /**
     * Új elem hozzáadása az idListhez
     * @param tg
     * @param id
     */

    public void addIdListItem(TeleportGate tg, int id){
        int idCount = 0;
        for(Integer i: idList.values()) {
            if(i.equals(id))
                idCount++;
        } //csak akkor rakhatja bele, ha max 1 ilyen kapu van ezzel az azonosítóval
        if(idCount<2)
            idList.put(tg,id);
    }

    /**
     * Azonosító generálása újonnan létrehozott aszteroidának -> ellenőrzi, hogy ne legyen duplikáció
     * @return
     */

    public int generateId(){
        Random rand = new Random();
        boolean equal = true;
        int idCount=0;
        int id = -1;
        //egye
        while(equal) {
            id = rand.nextInt(1000);
            //végignézi a listát, hogy van-e egyezés
            for(Integer i: idList.values()) {
                if(i.equals(id))
                    idCount++;
            } //akkor léphet ki, ha nincs sehol egyezés
            if(idCount==0)
                equal=false;
        }
        return id;
    }

}