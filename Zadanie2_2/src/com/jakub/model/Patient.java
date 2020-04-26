package com.jakub.model;

import java.util.Date;

public class Patient {

    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private BloodType bloodType;
    private Patient(Builder builder){
        this.firstName = builder.firstName;
        this.lastName = builder.lastName;
        if(builder.dateOfBirth != null)
            this.dateOfBirth = builder.dateOfBirth;
        if(builder.bloodType != null){
            this.bloodType = builder.bloodType;
        }
    }

    public static class Builder{

        private String firstName;
        private String lastName;
        private Date dateOfBirth;
        private BloodType bloodType;

        public Builder(String firstName, String lastName){
            this.firstName = firstName;
            this.lastName = lastName;
        }
        public Builder dateOfBirth(Date dateOfBirth){
            this.dateOfBirth = dateOfBirth;
            return this;
        }
        public Builder bloodType(BloodType bloodType){
            this.bloodType = bloodType;
            return this;
        }
        public Patient build(){
            return new Patient(this);
        }

    }

    @Override
    public String toString() {
        return "Patient{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", bloodType=" + bloodType +
                '}';
    }
}
