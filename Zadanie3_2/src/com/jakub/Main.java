package com.jakub;

public class Main {

    public static void main(String[] args) {


        var parser = new Parser();

        try {
            var classDef = parser.createClassDefinition("class test,wiek:String='Adam' setter getter; pieniadze:float='1.5',");

            var generator = new CodeGenerator();
            System.out.println(generator.createClassString(classDef));
        } catch (InvalidClassDefinitionStringException e) {
            e.printStackTrace();
        }

    }
}
