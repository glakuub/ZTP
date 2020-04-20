package com.jakub.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class University implements Cloneable, Serializable {
    private String name;
    private ArrayList<Faculty> faculties;
    private static String NAME_PREFIX = "University ";
    private static int NAME_BOUND = 100;
    private static String TAB = "\t";
    private static String NEW_LINE = "\n";
    private static Random random;
    static {
        random = new Random();
    }

    public University(String name){
        this.name = name;
        faculties = new ArrayList<Faculty>();
    }

    public static University createRandom(int facltiesNumber, int studentsNumber){
        University newUniversity = new University(NAME_PREFIX + random.nextInt(NAME_BOUND));
        for (int i = 0; i < facltiesNumber ; i++) {
            newUniversity.faculties.add(Faculty.createRandom(studentsNumber));
        }
        return newUniversity;
    }

    public University(University university){
        name = university.name;
        faculties = new ArrayList<>();
        university.faculties.forEach(f->this.faculties.add(new Faculty(f)));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(TAB + name + NEW_LINE);
        faculties.forEach(f->stringBuilder.append(f));
        return stringBuilder.toString();
    }

    @Override
    public Object clone(){
        University university;
        try{
            university = (University) super.clone();
            university.faculties = new ArrayList<>();
        }catch (CloneNotSupportedException e){
            university = new University(this.name);
        }
        for(Faculty faculty : faculties){
            university.faculties.add((Faculty)faculty.clone());
        }
        return university;
    }

    public boolean isDeepCopy(University university){
        if(this == university || this.faculties == university.faculties){
            return false;
        }else{
            if(this.faculties.size() == university.faculties.size()){
                for (int i = 0; i < this.faculties.size(); i++) {
                    if(!this.faculties.get(i).isDeepCopy(university.faculties.get(i)))
                        return false;
                }
            }
        }

        return this.name.equals(university.name);
    }
}
