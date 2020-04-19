package com.jakub.model;

import java.util.Random;

class Student implements Cloneable {

    private int id;
    private static Random random;
    private static final int START_INDEX = 20000;
    private static final int INDEX_RANGE = 20000;

    static {
        random = new Random();
    }

    public Student(int id){
        this.id = id;
    }

    public Student(Student student){
        this.id = student.id;
    }

    public static Student createRandom(){
        return new Student(random.nextInt(INDEX_RANGE)+START_INDEX);
    }

    @Override
    public String toString() {
        return "index: "+id + " ";
    }

    @Override
    public Object clone(){
        try{
            return (Student) super.clone();
        } catch (CloneNotSupportedException e){
            return new Student(this.id);
        }
    }
}
