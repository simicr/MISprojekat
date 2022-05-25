package Entiteti;

import java.util.Objects;

public class Deo {
    private int sifra;
    private String naziv;
    private double cena;
    private EAutomobil auto;

    public Deo(String naziv, double cena, EAutomobil auto) {
        this.sifra = sifra;
        this.naziv = naziv;
        this.cena = cena;
        this.auto = auto;
        this.sifra = this.hashCode();
    }

    public int getSifra() {
        return sifra;
    }

    public String getNaziv() {
        return naziv;
    }

    public double getCena() {
        return cena;
    }

    public EAutomobil getAuto() {
        return auto;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Deo deo = (Deo) o;
        return  Double.compare(deo.cena, cena) == 0 && Objects.equals(naziv, deo.naziv) && Objects.equals(auto, deo.auto);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, cena, auto);
    }

    @Override
    public String toString() {
        return  sifra +
                "," + naziv +
                "," + cena +
                "," + auto.getBrSasija();
    }
}
