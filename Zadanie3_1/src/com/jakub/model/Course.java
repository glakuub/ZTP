package com.jakub.model;

import java.util.List;

public class Course {
    public Course(String name, int ects, Type type, List<Student> signedStudents) {
        this.name = name;
        this.ects = ects;
        this.type = type;
        this.signedStudents = signedStudents;
    }

    public enum Type {LECTURE, CLASS, LAB}

    private String name;
    private int ects;
    private  Type type;
    private List<Student> signedStudents;



    public int getEcts() {
        return ects;
    }
    public Type getType() {
        return type;
    }
    public String getName() {
        return name;
    }

    public void signStudent(Student student){
        signedStudents.add(student);
    }
    @Override
    public String toString() {
        return "Course{" +
                "name='" + name + '\'' +
                ", ects=" + ects +
                ", type=" + type +
                '}';
    }
}
