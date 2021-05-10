package com.viseguardstudios.asteroid_miner.model.entities;


import com.viseguardstudios.asteroid_miner.model.Scene;
import com.viseguardstudios.asteroid_miner.model.entities.building.Building;
import com.viseguardstudios.asteroid_miner.model.entities.Vessel.Vessel;
import com.viseguardstudios.asteroid_miner.model.inventory.AsteroidInventory;
import com.viseguardstudios.asteroid_miner.model.item.Item;
import com.viseguardstudios.asteroid_miner.model.item.resource.Resource;
import com.viseguardstudios.asteroid_miner.util.Sprite;
import com.viseguardstudios.asteroid_miner.util.Vector2;


import java.util.*;

/**
 * Egy aszteroidát jelöl. Minden példányának létrejöttekor beállítódnak a generátor által a tulajdonságai.
 */
public class Asteroid extends Entity {

    public static final Sprite basic = new Sprite("assets/graphics/sprites/ast.png", 20);
    public static final Sprite exploded_sprite = new Sprite("assets/graphics/sprites/ast_exp.png", 20);

    /**
     * Aszteroida létrehozása
     * @param name
     * @param pos
     * @param maxHidingSpace
     * @param coreSize
     * @param crustSize
     * @param exploded
     * @param revealed
     * @param visited
     */
    public Asteroid(Scene scene, String name, Vector2 pos, int maxHidingSpace, int coreSize, int crustSize, boolean exploded, boolean revealed, boolean visited){
        this.scene = scene;
        this.name = name;
        this.inventory = new AsteroidInventory();
        this.pos = pos;
        this.maxHidingSpace = maxHidingSpace;
        this.coreSize = coreSize;
        this.crustSize = crustSize;
        this.exploded = exploded;
        this.revealed = revealed;
        this.visited = visited;
    }

    /**

     * Az aszteroida magjának mérete, ennyi egységnyi nyersanyag bányászható ki belőle a játék elején, és bányászat után ennyi egységnyi item helyezhető vissza bele.
     */
    private int coreSize;

    /**
     * Igaz, ha felrobbant már az aszteroida, hamis, ha nem.
     */
    private boolean exploded;

    /**
     * Maximum ennyi telepes számára van az adott időpillanatban hely az aszteroidában elbújni. Minden telepes sikeres elbújása után csökken ez a szám.
     */
    private int maxHidingSpace;

    /**
     * Az aszteroida köpenyének mélysége. Ennyi egységet kell még fúrni benne jelenleg, hogy teljesen átfúrt legyen. Minden fúrás után csökken ez a szám.
     */
    private int crustSize;

    /**
     * Igaz, ha az aszteroida már „fel van fedve” a térképen, tehát rajta vagy bármelyik szomszédján már járt telepes vagy robot. Ellenkező esetben hamis.
     */
    private boolean revealed;

    /**
     * Igaz, ha az aszteroidán már járt telepes vagy robot, ellenkező esetben hamis.
     */
    private boolean visited;

    /**
     * Ha az aszteroidában jelenleg megbújik egy telepes, akkor eltárolja, melyik telepesről van szó. Ha nem bújik benne senki, null értéket tárol.
     */
    private Vessel hidingSpaceShip;

    /**
     * Az aszteroida raktára, ami a magba belehelyezett és jelenleg ott tárolt elemeket tartalmazza.
     */
    private AsteroidInventory inventory;

    /**
     * A szomszédos aszteroidák tárolója.
     */
    private List<Asteroid> neighbours = new LinkedList<>();

    /**
     * Az aszteroidán jelenleg állomásozó járművek, épületek tárolói.
     */
    private Set<Vessel> stationed = new HashSet<>();
    


    /**

     * Az aszteroida körül lévő, mozgó entitások.
     */
  private Set<MovableEntity> orbit = new HashSet<>();

    /**
     * Az aszteroidában jelenleg lévő, mozgó entitások. tárolója.
     */
  private Set<MovableEntity> inside = new HashSet<>();
    /**
     * Az aszteroidára épített (véglegesen elhelyezett) építmények tárolója.
     * Ki van-e már bányászva az aszteroida magja teljesen
     */
    private boolean mined;

    /**
     * Default constructor
     */
    public Asteroid(Resource r, int amount) {
        maxHidingSpace = 1;
        mined = false;
        exploded = false;
        coreSize = amount;
        crustSize = 5;
        inventory = new AsteroidInventory();
        for(int i=0; i<amount; i++){
            inventory.insertItem(r);
        }
    }

    /**
     * Default constructor
     */
    public Asteroid() {
        maxHidingSpace = 1;
        inventory = new AsteroidInventory();
        mined = false;
        exploded = false;
        coreSize = 5;
        crustSize = 5;
    }


    /**
     * Kör végén végrehajtandó dolgok
     * @param closeToSun
     */
    @Override
    public void roundEnd(boolean closeToSun) {
        super.roundEnd(closeToSun);
        inventory.roundEnd();
        if(closeToSun)
            inventory.nearSun(this);
    }

    /**
     * Felrobban az aszteroida. Felrobbantja az összes rajta tartózkodó járművet, hozzáférhetetlenné teszi a raktárat és a rajta lévő épületeket.
     */
    public void explode() {
        super.explode();
        exploded = true;
        //járműveknek, épületeknek jelzi a robbanást
        for(Vessel v: stationed)
            v.AsteroidExploded();
        for(MovableEntity o: orbit)
            o.AsteroidExploded();
        for(MovableEntity i: inside)
            i.AsteroidExploded();
        //raktár is felrobban
        inventory.explode();
    }

    @Override
    public List<String> getActions() {
        return null;
    }

    @Override
    public void doAction(String[] args) {

    }

    /**
     * Tárolja, hogy melyik aszteroidák elérhetőek jelenleg az adott aszteroidából.
     * @return
     */
    public List<Asteroid> getReachableAsteroids() {
        //természetes szomszédok
        List<Asteroid> n = new LinkedList<>(neighbours);
        //extra szomszédok, ha vannak
        for (MovableEntity orb: orbit) {
            Building b = (Building)orb;
            var r = b.getRoutes();
            if(r != null){
                n.add(r);
            }
        }
        return n;
    }

    /**
     * Egy adott jármű elhagyja az aszteroidát, törlődik az ott tartózkodók közül.
     */
/* Adams branch
    public void Depart(Vessel v) {
        MovableEntity.AsteroidPlaces place = v.getPlace();
        if (place== MovableEntity.AsteroidPlaces.Vessel){
            stationed.remove(v);
        }
        */

    public void depart(MovableEntity v) {
        if(v.getPlace() == MovableEntity.AsteroidPlaces.Vessel) {
            if(stationed.contains(v))
                stationed.remove(v);
        }
        else if (v.getPlace() == MovableEntity.AsteroidPlaces.Orbit){
            if(orbit.contains(v))
                orbit.remove(v);
        }


    }

    /**
     * Egy adott jármű érkezik az aszteroidára, regisztrálja az ott tartózkodók közé.
     */

   /* public void Arrive(Vessel v) {
        MovableEntity.AsteroidPlaces place = v.getPlace();
        if (place == MovableEntity.AsteroidPlaces.Vessel) {
            stationed.add(v);
        }
    }
*/
    public void arrive(MovableEntity v) {
        if(v.getPlace() == MovableEntity.AsteroidPlaces.Vessel) {
            if(!stationed.contains(v))
                stationed.add((Vessel) v);
        }
        else if (v.getPlace() == MovableEntity.AsteroidPlaces.Orbit){
            if(!orbit.contains(v))
                orbit.add(v);
        }
        /**
         * Szomszédok felfedése
         */
        if(!visited){
            visited=true;
            for(Asteroid n: getReachableAsteroids())
                n.Reveal();
        }
    }



    /**
     * Aszteroida felfedése a térképen, amennyiben még nem volt felfedve.
     */
    public void Reveal() {
        if(!revealed)
        revealed = true;
    }

    /**
     * Akkor hívódik meg amikor az aszteroida kérgén lévő lyukat akarják mélyíteni.
     */
    public boolean Drill() {
        if(crustSize > 0 && !exploded){
            crustSize -= 1;
            return true;
        }
        return false;
    }

    /**
     * Akkor hívódik meg amikor az aszteroidából akarnak nyersanyagot kibányászni.
     * @return
     */
    public Item Mine() {
        if(exploded || crustSize>0 || inventory.getItems().size()==0)
            return null;
        Item res = inventory.getItems().get(0);
        inventory.removeItem(res);
        if(inventory.getItems().size()==0)
            mined=true;
        return res;
    }

    /**
     * Az aszteroidában megpróbál elbújni egy jármű. Igazat ad vissza ha el tud bújni, hamisat ha nem.
     * @param v 
     * @return
     */
    public boolean Hide(Vessel v) {
        if( (inventory.getItems().size()==0) && !exploded && crustSize == 0){
            var neededSpace = v.GetHidingSpaceRequirement();
            var usedSpace = hidingSpaceShip == null? 0: 1;
            //ha van elég helye elbújni
            if(1 - usedSpace >= neededSpace){
                if(neededSpace > 0){
                    hidingSpaceShip = v;
                }
                return true;
            }
            return false; //nincs elég hely
        }
        return false; //más aszteroida követelmény nem teljesül
    }

    /**
     * Az adott jármű előbújik az aszteroidából.
     * @param v
     */
    public void Exit(Vessel v) {
        if(!v.getHidden()) return;
        if(v == hidingSpaceShip){
            hidingSpaceShip = null;
        }
    }

    /**
     * Hozzáad egy új épületet az aszteroidához, helynek megfelelően.
     * @param b
     */
    public void AddBuilding(Building b) {
/*
Adams branch
        MovableEntity.AsteroidPlaces place = b.getPlace();
        if (place== MovableEntity.AsteroidPlaces.Orbit){
            orbit.add(b);
        }
        if (place== MovableEntity.AsteroidPlaces.Inside){
            inside.add(b);
*/
        if(b.getPlace()== MovableEntity.AsteroidPlaces.Inside) {
            if(!inside.contains(b)) {
                inside.add(b);
                b.setPos(this.getPos());
            }
        }
        else if(b.getPlace()== MovableEntity.AsteroidPlaces.Orbit) {
            if(!orbit.contains(b)) {
                orbit.add(b);
                b.setPos(this.getPos());
            }

        }
    }

    /**


     * Épület eltávolítása az aszteroidáról
     * @param b
     */
    public void removeBuilding(Building b){
        if(b.getPlace()== MovableEntity.AsteroidPlaces.Inside)
            if(inside.contains(b))
                inside.remove(b);
            else if (b.getPlace() == MovableEntity.AsteroidPlaces.Orbit)
                if(orbit.contains(b))
                    orbit.remove(b);

    }

    /**
     * Elem belehelyezése a raktárba. Sikeres behelyezés után igazzal tér vissza, sikertelen művelet után hamissal tér vissza.
     * @param i 
     * @return
     */
    public boolean placeItem(Item i) {
        boolean canPlace = false;
        if(isMined() && !exploded){
            canPlace = inventory.tryInsertItem(i);
            if(canPlace)
                inventory.insertItem(i);
        }
        return canPlace;
    }

    /**
     * Elem kivétele.
     * @param itemType item típusa
     * @return null ha nincs, amúgy az első Item ami megfelel a neki
     */
    public Item pickupItem(String itemType)
    {
        List<Item> items = inventory.getItems();
        int i =0;

        while (!items.get(i).getName().equals(itemType) && i < items.size())
            i++;

        Item item = i >=items.size() ? null : items.get(i);
        if(item != null) inventory.removeItem(item);
        return item;
    }


    /**
     * Új szomszéd hozzáadása.
     */
    public void addNeighbour(Asteroid a) {
        if(!neighbours.contains(a))
             neighbours.add(a);

    }

    /**
     * Napvihar esetén hívódik meg minden entitáson, aki rajta tartózkodik.
     */
    @Override
    public void SolarFlare() {
        for(MovableEntity o : orbit)
            o.SolarFlare();
        for(MovableEntity s: stationed)
            s.SolarFlare();
    }


    @Override
    public String printStatus() {
        String status = super.printStatus() + "\n";
        status = status.concat("Crust: " + crustSize + "\n");
        status = status.concat("Vessels:" + "\n");
        for (var vessel :
                this.stationed) {
            status = status.concat("- " + vessel.getName() + "\n");
        }

        status = status.concat("Items:");
        if (crustSize <= 0) {
            for (var item : inventory.getItems()) {
                status = status.concat("- " + item.getName() + "\n");
            }
        }
        else {
            status = status.concat("?" + "\n");
        }


        status = status.concat("Orbit:" + "\n");

        for (var a : orbit) {
            status = status + "- " + a.getName() + "\n";
        }

        status = status.concat("Vessels:" + "\n");

        for (var a : stationed) {
            status = status + "- " + a.getName() + "\n";
        }

        status = status.concat("Inside:" + "\n");

        for (var a : inside) {
            status = status + "- " + a.getName() + "\n";
        }

        return status;
    }

    @Override
    public Sprite getSprite() {
        if(this.exploded){
            //return exploded_sprite;
            return Entity.missing;
        }
        return basic;
    }

    //#################################
    //Getter-Setters
    //#################################


    public int getCrustSize() {
        return crustSize;
    }

    public void setCrustSize(int crustSize) {
        try {
            if(crustSize >= 0) this.crustSize = crustSize;
            else throw new Exception("crustSize must not smaller than 0!");
        }
        catch (Exception exp){ }

    }

    public int getMaxHidingSpace() {
        return maxHidingSpace;
    }

    public void setMaxHidingSpace(int maxHidingSpace) {
        this.maxHidingSpace = maxHidingSpace;
    }

    public boolean isExploded() {
        return exploded;
    }

    public void setExploded(boolean exploded) {
        this.exploded = exploded;
    }

    public Vessel getHidingVessel() {
        return hidingSpaceShip;
    }

    public void setHidingVessel(Vessel hidingVessel) {
        this.hidingSpaceShip = hidingVessel;
    }

    public AsteroidInventory getInventory(){
        return inventory;
    }

    public void setInventory(AsteroidInventory inventory) {
        this.inventory = inventory;
    }

    public List<Asteroid> getPhysicalNeighbours(){
        return neighbours;
    }

    public boolean isMined() {
        //return mined;
        return crustSize <= 0;
    }

    public int getLocalEntityNumber(){
        return stationed.size()+ orbit.size()+inside.size();
    }
}