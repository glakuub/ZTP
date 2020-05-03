package com.jakub;

import com.jakub.model.BloodTypeFactory;
import com.jakub.model.Patient;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;

public class Main {

    public static void main(String[] args) {

        var btf = new BloodTypeFactory();
        var patientList = new ArrayList<Patient>();

        patientList.add(new Patient.Builder("jan","kowalski").bloodType(btf.get("0rh+")).build());
        patientList.add(new Patient.Builder("adam","nowak").bloodType(btf.get("0rh+")).dateOfBirth(Date.from(Instant.now())).build());
        patientList.add(new Patient.Builder("jan","nowak").build());

        patientList.forEach(System.out::println);

        var tbt = btf.get("ABrh+");
        var tbt2 = btf.get("ABrh+");

        System.out.println("\nTest na równość referencji:");
        System.out.println(tbt == tbt2);

        System.out.println("\nReprezentacja obiektów:");
        System.out.println(tbt);
        System.out.println(tbt2);
    }
}
