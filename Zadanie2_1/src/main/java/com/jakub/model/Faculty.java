package com.jakub.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.stream.IntStream;

public class Faculty implements Cloneable, Serializable {

    private String name;
    private ArrayList<Student> students;

    private static String NEW_LINE = "\n";
    private static String TAB = "\t";
    private static String NAME_PREFIX = "Faculty ";
    private static int NAME_BOUND = 100;
    private static Random random;
    static {
        random = new Random();
    }

    public Faculty(String name){
        this.name = name;
        students = new ArrayList<Student>();
    }

    public static Faculty createRandom(int studentsNumber){
        Faculty newFaculty = new Faculty(NAME_PREFIX + random.nextInt(NAME_BOUND));
        for (int i = 0; i <studentsNumber ; i++) {
            newFaculty.students.add(Student.createRandom());
        }
        return newFaculty;
    }

    public Faculty(Faculty faculty){
        name = faculty.name;
        students = new ArrayList<>();
        faculty.students.forEach(e->this.students.add(new Student(e)));
    }
    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(TAB + TAB + name + NEW_LINE);
        stringBuilder.append(TAB + TAB + TAB);
        students.forEach(stringBuilder::append);
        stringBuilder.append(NEW_LINE);
        return stringBuilder.toString();
    }

    @Override
    public Object clone(){
        Faculty faculty;
        try{
            faculty = (Faculty) super.clone();
            faculty.students = new ArrayList<>();
        }catch (CloneNotSupportedException e){
            faculty = new Faculty(this.name);
        }

        for (int i = 0; i < this.students.size() ; i++) {
            faculty.students.add((Student)this.students.get(i).clone());
        }
        return faculty;
    }

    public boolean isDeepCopy(Faculty faculty){
        if(this == faculty || this.students == faculty.students)
            return false;
        else{
            if(this.students.size()==faculty.students.size()){
                for (int i = 0; i < this.students.size() ; i++) {
                    if(!this.students.get(i).isDeepCopy(faculty.students.get(i))){
                        return false;
                    }
                }
            }

            return this.name.equals(faculty.name);
        }

    }


}
