package com.jakub;

import java.util.ArrayList;
import java.util.List;

public class ClassDefinition {

    private String name;
    private List<Attribute> attributes;
    private List<DesignPattern> designPatterns;

    public ClassDefinition() {
        attributes = new ArrayList();
        designPatterns = new ArrayList<>();
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setAttributes(List<Attribute> attributes){
        this.attributes = attributes;
    }

    public void setDesignPatterns(List<DesignPattern> designPatterns) {
        this.designPatterns = designPatterns;
    }

    public List<DesignPattern> getDesignPatterns() {
        return designPatterns;
    }

    @Override
    public String toString() {
        return "ClassDefinition{" +
                "name='" + name + '\'' +
                ", attributes=" + attributes +
                ", designPattern=" + designPatterns +
                '}';
    }

    public String getName() {
        return name;
    }

    public List<Attribute> getAttributes() {
        return attributes;
    }
}
