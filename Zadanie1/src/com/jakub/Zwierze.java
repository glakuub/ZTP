package com.jakub;

public class Zwierze {
    protected String nazwa;
    protected int wiek;
    protected int masa;
    public Zwierze(String nazwa, int wiek, int masa)
    {
        this.nazwa = nazwa;
        this.wiek = wiek;
        this.masa = masa;
    }

    public String getNazwa() {
        return nazwa;
    }

    public int getWiek() {
        return wiek;
    }

    public int getMasa() {
        return masa;
    }

    public void AdHocTest(){
        System.out.println("Zwierze");
    }

    @Override
    public String toString() {
        return "Zwierze{" +
                "imie='" + nazwa + '\'' +
                ", wiek=" + wiek +
                ", masa=" + masa +
                '}';
    }
}
