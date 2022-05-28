package Entiteti;

import java.util.Objects;

public class EUsluga {
    private String naziv;
    private String opis;
    private double cena;
    private double trajanje;
    private int id;

    public EUsluga(String naziv, String opis, double cena, double trajanje) {
        this.naziv = naziv;
        this.opis = opis;
        this.cena = cena;
        this.trajanje = trajanje;
        this.id = hashCode();
    }

    public String getNaziv() {
        return naziv;
    }

    public String getOpis() {
        return opis;
    }

    public double getCena() {
        return cena;
    }

    public double getTrajanje() {
        return trajanje;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EUsluga eUsluga = (EUsluga) o;
        return Double.compare(eUsluga.cena, cena) == 0 && Double.compare(eUsluga.trajanje, trajanje) == 0 && Objects.equals(naziv, eUsluga.naziv) && Objects.equals(opis, eUsluga.opis);
    }

    @Override
    public int hashCode() {
        return Objects.hash(naziv, opis, cena, trajanje);
    }


    public String toCSV() {
        return  id +
                "," + naziv  +
                "," + opis  +
                "," + cena +
                "," + trajanje;
    }

    @Override
    public String toString() {
        return "naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", cena=" + cena +
                ", trajanje=" + trajanje;
    }
}
