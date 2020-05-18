package com.jakub.Generator.Model;

public class Attribute {
    private String name;
    private String type;
    private String defaultValue;
    private boolean hasSetter;
    private boolean hasGetter;

    public void setName(String name) {
        this.name = name;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }

    public boolean isHasGetter() {
        return hasGetter;
    }

    public boolean isHasSetter() {
        return hasSetter;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue){
        this.defaultValue = defaultValue;
    }
    public void setHasSetter(boolean value){
        hasSetter=value;
    }
    public void setHasGetter(boolean value){
        hasGetter=value;
    }

    @Override
    public String toString() {
        return "Attribute{" +
                "name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", defaultValue='" + defaultValue + '\'' +
                ", hasSetter=" + hasSetter +
                ", hasGetter=" + hasGetter +
                '}';
    }
}
