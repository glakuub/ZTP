package com.jakub;

public class Wyswietlacz{

    public static void main(String[] args) {
        var wyswietlacz = new Wyswietlacz();

        var k1 = new Kontener<Zwierze>();
        k1.Dodaj(new Zwierze("zwierz",10,100));
        wyswietlacz.pokazDaneZwierzat(k1);
        //wyswietlacz.pokazDaneSsakow(k1);
        //wyswietlacz.pokazDanePsow(k1);

        System.out.println();

        var k2 = new Kontener<Ssak>();
        k2.Dodaj(new Ssak("delfin",12,1200));
        wyswietlacz.pokazDaneZwierzat(k2);
        wyswietlacz.pokazDaneSsakow(k2);
        //wyswietlacz.pokazDanePsow(k2);

        System.out.println();

        var k3 = new Kontener<Pies>();
        k3.Dodaj(new Pies("burek",3,200));
        wyswietlacz.pokazDaneZwierzat(k3);
        wyswietlacz.pokazDaneSsakow(k3);
        wyswietlacz.pokazDanePsow(k3);


        System.out.println();
        var pies = new Pies("testowy",10,10);
        pies.AdHocTest();
        pies.AdHocTest('z');
        pies.AdHocTest("ciag");

    }

    public <T1 extends Zwierze> void pokazDaneZwierzat(Kontener<T1> kontener){
        kontener.getZwierzeta().forEach(z ->System.out.println(
                "nazwa: " + z.getNazwa()
                +" wiek: " + z.getWiek()
                +" masa: "+ z.getMasa()
        ));
    }
    public <T2 extends Ssak> void pokazDaneSsakow(Kontener<T2> kontener)
    {
        kontener.getZwierzeta().forEach(z ->System.out.println(
                "nazwa: " + z.getNazwa()
                +" wiek: " + z.getWiek()
                +" masa: "+ z.getMasa()
                +" liczba kręgów: " + z.getLiczbaKregow()
                +" długość karmienia młodych: "  + z.getDlugoscKarmienia()
        ));
    }
    public <T3 extends Pies> void pokazDanePsow(Kontener<T3> kontener)
    {
        kontener.getZwierzeta().forEach(z ->System.out.println(
                "nazwa: " + z.getNazwa()
                +" wiek: " + z.getWiek()
                +" masa: "+ z.getMasa()
                +" liczba kręgów: " + z.getLiczbaKregow()
                +" długość karmienia młodych: "  + z.getDlugoscKarmienia()
                +" rasa: "+z.getRasa()
                +" liczba młodych w miocie: " + z.getLiczbaPotomstwaWMiocie()
        ));
    }
}
