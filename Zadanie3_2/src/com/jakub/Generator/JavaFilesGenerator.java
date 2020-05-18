package com.jakub.Generator;

import com.jakub.Generator.Model.ClassDefinition;

import java.io.*;
import java.util.Scanner;

public class JavaFilesGenerator {

    private static final String DEFAULT_PATH="C:\\Users\\Jakub\\Desktop\\ztp";
    private static final CodeGenerator codeGenerator = new CodeGenerator();
    private static final String JAVA_EXTENSION =".java";

    public void createClassFile(ClassDefinition classDefinition, String directoryPath){

        var className = classDefinition.getName()+JAVA_EXTENSION;
        var classString = codeGenerator.createClassString(classDefinition);
        try {

            saveStringAsFile(classString, directoryPath, className);
            System.out.println("Utworzono plik: "+className + " w katalogu: "+ DEFAULT_PATH);


        }catch (WritingJavaFileException wjfe){

            System.out.println("Nie udalo sie zapisac do pliku. Czy sprobowac ponownie? (T/N)");
            var scanner = new Scanner(System.in);
            var input = scanner.next();

            if(input.equals("T")){
                try {
                    saveStringAsFile(classString,directoryPath,className);
                }catch (WritingJavaFileException wjfe2){
                    System.out.println("Nie udało się zapisać do pliku");
                }
            }

        }
    }

    public void createClassFile(ClassDefinition classDefinition){
        createClassFile(classDefinition, DEFAULT_PATH);
    }

    private void saveStringAsFile(String string, String path, String fileName) throws WritingJavaFileException {

        try(var bw = new BufferedWriter(new FileWriter(path+"\\"+fileName))){

            bw.write(string);

        }catch (IOException e){
            throw new WritingJavaFileException();
        }
    }
}
