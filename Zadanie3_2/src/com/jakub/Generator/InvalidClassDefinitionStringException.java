package com.jakub.Generator;

public class InvalidClassDefinitionStringException extends Exception {

    public InvalidClassDefinitionStringException(String message){
        super(message);
    }
    public InvalidClassDefinitionStringException(){
        super();
    }
}
