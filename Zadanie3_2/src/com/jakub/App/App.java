package com.jakub.App;

import com.jakub.Generator.Model.ClassDefinition;
import com.jakub.Generator.InvalidClassDefinitionStringException;
import com.jakub.Generator.JavaFilesGenerator;
import com.jakub.Generator.Parser;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class App {
    private static final String DEFAULT_PATH = "C:\\Users\\Jakub\\Desktop\\ztp\\defs.txt";
    private Parser parser;
    private JavaFilesGenerator javaFilesGenerator;
    private Scanner scanner;

    public App(){
        parser = new Parser();
        javaFilesGenerator = new JavaFilesGenerator();
    }

    public void Run(){
        while(true) {
            printMenu();
            var choice = readInput();
            switch (choice) {
                case 1:
                    var classDef = readClassDefinitionFromUser();
                    javaFilesGenerator.createClassFile(classDef);
                    break;
                case 2:
                    var classDefs = readClassDefinitionsFromFile(DEFAULT_PATH);
                    classDefs.forEach(def -> javaFilesGenerator.createClassFile(def));
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Niepoprawny wybór");
                    break;
            }
        }

    }

    private void printMenu(){
        System.out.println("Menu");
        System.out.println("0.Wyjście");
        System.out.println("1.Wprowadź opis klasy");
        System.out.println("2.Wczytaj opis klasy");

    }
    private int readInput(){
        if(scanner == null)
            scanner = new Scanner(System.in);

        var input = scanner.next();

        while (!tryParseInt(input)){
            System.out.println("nie int");
            input = scanner.next();
        }

        return Integer.parseInt(input);
    }
    private boolean tryParseInt(String value) {
        try {
            Integer.parseInt(value);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private ClassDefinition readClassDefinitionFromUser(){
        scanner.nextLine();
        var input = scanner.nextLine();

        while (!tryParseClassDefinition(input)){
            System.out.println("Niepoprawny opis klasy. Wprowadź inny.");
            input = scanner.nextLine();
        }

        try {
            return parser.createClassDefinition(input);
        }catch (InvalidClassDefinitionStringException icdse){
            return null;
        }

    }

    private boolean tryParseClassDefinition(String classDefinitionString){
        try{
            parser.createClassDefinition(classDefinitionString);
            return true;
        }catch (InvalidClassDefinitionStringException icdse){
            return false;
        }
    }
    private List<ClassDefinition> readClassDefinitionsFromFile(String path){
        var result = new ArrayList<ClassDefinition>();
        int currentLine = 1;
        try(var br = new BufferedReader(new FileReader(path))){

            for (String line; (line = br.readLine())!=null;) {
                try {
                    result.add(parser.createClassDefinition(line));
                } catch (InvalidClassDefinitionStringException e) {
                    System.out.println("Niepoprawna definicja klasy w linii: " + currentLine);
                }
                currentLine++;
            }

        }catch (IOException ioe){
            System.out.println("Wystąpił błąd podczas odczytywania pliku.");
        }


        return result;
    }
}
