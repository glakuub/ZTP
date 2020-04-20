package com.jakub.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class HigherEducation implements Cloneable, Serializable {
    private ArrayList<University> universities;
    private String name;
    private static String NAME_PREFIX = "Higher education ";
    private static int NAME_BOUND = 10;
    private static Random random;
    static {
        random = new Random();
    }

    public HigherEducation(String name){
        this.name = name;
        universities = new ArrayList<>();
    }

    public static HigherEducation createRandom(int universitiesNumber, int facultiesNumber, int studentsNumber){

        HigherEducation newHigherEducation = new HigherEducation(NAME_PREFIX + random.nextInt(NAME_BOUND));
        for (int i = 0; i < universitiesNumber ; i++) {
            newHigherEducation.universities.add(University.createRandom(facultiesNumber,studentsNumber));
        }
        return newHigherEducation;
    }

    public HigherEducation(HigherEducation higherEducation){
        name = higherEducation.name;
        universities = new ArrayList<>();
        higherEducation.universities.forEach(u -> this.universities.add(new University(u)));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(name + "\n");
        universities.forEach(u->stringBuilder.append(u));
        return stringBuilder.toString();
    }

    @Override
    public Object clone(){
        HigherEducation higherEducation;
        try{
            higherEducation = (HigherEducation)super.clone();
            higherEducation.universities = new ArrayList<>();
        }catch (CloneNotSupportedException e){
            higherEducation = new HigherEducation(this.name);
        }
        for(University university : universities){
            higherEducation.universities.add((University)university.clone());
        }
        return higherEducation;
    }

    public boolean isDeepCopy(HigherEducation higherEducation){
        if(this == higherEducation || this.universities == higherEducation.universities)
            return false;
        else{
            if(this.universities.size() == higherEducation.universities.size()){
                for (int i = 0; i < this.universities.size() ; i++) {
                    if(!this.universities.get(i).isDeepCopy(higherEducation.universities.get(i)))
                        return false;
                }
            }
        }
        return this.name.equals(higherEducation.name);
    }
}
