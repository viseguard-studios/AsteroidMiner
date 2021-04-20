package com.viseguardstudios.asteroid_miner.map_loader;

import com.viseguardstudios.asteroid_miner.model.Scene;

public abstract class EntityCreator {

    protected  Scene scene;
    //use it to knows which scene is it in
    public EntityCreator(Scene scene)
    {
        this.scene=scene;
    }

    //must do something
    public abstract void create();


}
