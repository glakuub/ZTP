package com.jakub.model;

import java.util.ArrayList;

public class HigherEducation {
    private ArrayList<University> universities;
    private static String NAME = "Higher education \n";

    public HigherEducation(){
        universities = new ArrayList<>();
    }

    public static HigherEducation createRandom(int universitiesNumber, int facultiesNumber, int studentsNumber){
        HigherEducation newHigherEducation = new HigherEducation();
        for (int i = 0; i < universitiesNumber ; i++) {
            newHigherEducation.universities.add(University.createRandom(facultiesNumber,studentsNumber));
        }
        return newHigherEducation;
    }

    public HigherEducation(HigherEducation higherEducation){
        universities = new ArrayList<>();
        higherEducation.universities.forEach(u -> this.universities.add(new University(u)));
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder(NAME);
        universities.forEach(u->stringBuilder.append(u));
        return stringBuilder.toString();
    }

    @Override
    public Object clone(){
        HigherEducation higherEducation;
        try{
            higherEducation = (HigherEducation)super.clone();
        }catch (CloneNotSupportedException e){
            higherEducation = new HigherEducation();
        }
        for(University university : universities){
            higherEducation.universities.add((University)university.clone());
        }
        return higherEducation;
    }
}
