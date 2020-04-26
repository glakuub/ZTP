package com.jakub.model;

import java.util.HashMap;
import java.util.Map;

public class BloodTypeFactory {

    private Map<String,BloodType> bloodTypeMap = new HashMap<>();

    public BloodType get(String symbol){
        if(bloodTypeMap.containsKey((symbol)))
            return bloodTypeMap.get(symbol);
        else {
            var newBloodType = new BloodType(symbol);
            bloodTypeMap.put(symbol,newBloodType);
            return newBloodType;
        }
    }
}
