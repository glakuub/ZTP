package com.jakub;

public class Main {

    public static void main(String[] args) {


        var parser = new Parser();

        try {
            var classDef = parser.createClassDefinition("class Test,wiek:String='Adam' setter getter; pieniadze:float='1.5',builder singleton");

            var generator = new JavaFilesGenerator();
            generator.createClassFile(classDef);

        } catch (InvalidClassDefinitionStringException e) {
            e.printStackTrace();
        }

    }
}
