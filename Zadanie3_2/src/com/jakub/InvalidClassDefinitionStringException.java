package com.jakub;

public class InvalidClassDefinitionStringException extends Exception {

    public InvalidClassDefinitionStringException(String message){
        super(message);
    }
    public InvalidClassDefinitionStringException(){
        super();
    }
}
