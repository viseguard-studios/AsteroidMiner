package com.viseguardstudios.asteroid_miner.skeleton;

import java.util.HashMap;
import java.util.Map;

public class Registry<T> {
    String base;
    Map<String,T> entries = new HashMap<>();

    public Registry(String base) {
        this.base = base;
    }

    public void Register(String key, T reg){
        entries.put(key, reg);
    }

    public T Get(String key){
        return entries.get(key);
    }
}