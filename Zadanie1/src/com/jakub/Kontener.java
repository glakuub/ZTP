package com.jakub;

import java.util.ArrayList;

public class Kontener<T extends Zwierze > {
    private ArrayList<T> zwierzeta;

    public Kontener() {
        zwierzeta = new ArrayList<>();
    }

    public void Dodaj(T element){
        zwierzeta.add(element);
    }

    public ArrayList<T> getZwierzeta() {
        return zwierzeta;
    }

    public void WyswietlZawartosc()
    {
        for (T z: zwierzeta) {
            System.out.println(z );
        }
    }
}
