package com.jakub;

public class Pies extends Ssak {
    private String rasa = "owczarek";
    private int liczbaPotomstwaWMiocie = 2;

    public Pies(String imie, int wiek, int masa) {
        super(imie, wiek, masa);
    }

    public String getRasa() {
        return rasa;
    }

    public int getLiczbaPotomstwaWMiocie() {
        return liczbaPotomstwaWMiocie;
    }
    public void AdHocTest(String ciag){
        System.out.println("Pies");
    }
    @Override
    public String toString() {
        return super.toString() + " Pies{" +
                "rasa='" + rasa + '\'' +
                ", liczbaPotomstwaWMiocie=" + liczbaPotomstwaWMiocie +
                '}';
    }
}
