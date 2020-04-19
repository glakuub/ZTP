package com.jakub;

public class Ssak extends Zwierze {
    protected int liczbaKregow = 10;
    protected int dlugoscKarmienia = 10;

    public Ssak(String imie, int wiek, int masa) {
        super(imie, wiek, masa);
    }

    public int getLiczbaKregow() {
        return liczbaKregow;
    }

    public int getDlugoscKarmienia() {
        return dlugoscKarmienia;
    }
    public void AdHocTest(char znak){
        System.out.println("Ssak");
    }

    @Override
    public String toString() {
        return super.toString() +" Ssak{" +
                "liczbaKregow=" + liczbaKregow +
                ", dlugoscKarmienia=" + dlugoscKarmienia +
                '}';
    }
}
