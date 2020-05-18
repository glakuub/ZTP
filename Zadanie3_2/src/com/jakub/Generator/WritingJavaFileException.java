package com.jakub.Generator;

public class WritingJavaFileException extends Exception {
    public WritingJavaFileException(String message){
        super(message);
    }

    public WritingJavaFileException(){
        super();
    }
}
