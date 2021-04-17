package com.viseguardstudios.asteroid_miner.util;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;

public class Namer {

    static Map<Type, Integer> counters = new HashMap<>();

    public static String getName(Type t){

        if(counters.containsKey(t)){
            var i = counters.get(t);
            i++;
            counters.replace(t,i);
            return t.getTypeName() + "_" + i;
        }

        int i = 1;

        counters.put(t, i);

        return t.getTypeName() + "_" + i;
    }

}
