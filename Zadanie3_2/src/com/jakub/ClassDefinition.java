package com.jakub;

import java.util.ArrayList;
import java.util.List;

public class ClassDefinition {

    private String name;
    private List<Attribute> attributes;
    private DesignPattern designPattern;

    public ClassDefinition() {
        attributes = new ArrayList();
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAttributes(List<Attribute> attributes){
        this.attributes = attributes;
    }

    @Override
    public String toString() {
        return "ClassDefinition{" +
                "name='" + name + '\'' +
                ", attributes=" + attributes +
                ", designPattern=" + designPattern +
                '}';
    }

    public String getName() {
        return name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }
}
