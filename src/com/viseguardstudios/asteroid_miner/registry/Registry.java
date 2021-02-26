package com.viseguardstudios.asteroid_miner.registry;

import java.util.HashMap;
import java.util.Map;

public class Registry<T> {
    String base;
    Map<String,T> entries = new HashMap<>();

    public Registry(String base) {
        this.base = base;
    }

    public void Register(String key, T reg){
        entries.put(base+":"+key, reg);
    }

    public T Get(String key){
        return entries.get(key);
    }
}

