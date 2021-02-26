package com.viseguardstudios.virtual_engine;

import com.viseguardstudios.virtual_engine.util.Vector2;

public abstract class Entity {

    Vector2 position;

    protected void SetPosition(Vector2 p){
        this.position = p;
    }

    public abstract void Update();
}
