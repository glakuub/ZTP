package com.jakub.model;

public class BloodType {
    private String symbol;
    public BloodType(String symbol){
        this.symbol = symbol;
    }

    public String getSymbol() {
        return symbol;
    }

    @Override
    public String toString() {
        return "BloodType{" +
                "symbol='" + symbol + '\'' +
                '}';
    }
}
