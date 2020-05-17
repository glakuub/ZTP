package com.jakub;

import java.io.*;

public class JavaFilesGenerator {

    private static final String DEFAULT_PATH="C:\\Users\\Jakub\\Desktop";
    private static final CodeGenerator codeGenerator = new CodeGenerator();
    private static final String JAVA_EXTENSION =".java";

    public void createClassFile(ClassDefinition classDefinition, String directoryPath){

        var className = classDefinition.getName()+JAVA_EXTENSION;
        var classString = codeGenerator.createClassString(classDefinition);
        saveStringAsFile(classString,directoryPath,className);
    }

    public void createClassFile(ClassDefinition classDefinition){
        createClassFile(classDefinition, DEFAULT_PATH);
    }

    private void saveStringAsFile(String string, String path, String fileName){

        try(var bw = new BufferedWriter(new FileWriter(path+"\\"+fileName))){

            bw.write(string);

        }catch (IOException e){

        }
    }
}
