package com.viseguardstudios.asteroid_miner.util;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Namer {

    static Map<Type, Integer> counters = new HashMap<>();

    public static String getName(Type t){

        return t.getTypeName() + "_" + getNextID(t);
    }

    public static int getNextID(Type t){

        if(counters.containsKey(t)){
            var i = counters.get(t);
            i++;
            counters.replace(t,i);
            return i;
        }

        int i = 1;

        counters.put(t, i);

        return i;
    }

}
