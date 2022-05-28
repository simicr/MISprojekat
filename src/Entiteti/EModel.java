package Entiteti;

import java.util.Objects;

public class EModel {

    private int id;
    private String proizvodjac;
    private String naziv;
    private int godiste;

    public EModel(String proizvodjac, String naziv, int godiste) {
        this.proizvodjac = proizvodjac;
        this.naziv = naziv;
        this.godiste = godiste;
        this.id = this.hashCode();
    }

    public int getId() {
        return id;
    }
    public String getProizvodjac() {
        return proizvodjac;
    }
    public String getNaziv() {
        return naziv;
    }
    public int getGodiste() {
        return godiste;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EModel eModel = (EModel) o;
        return godiste == eModel.godiste && Objects.equals(proizvodjac, eModel.proizvodjac) && Objects.equals(naziv, eModel.naziv);
    }
    @Override
    public int hashCode() {
        return Objects.hash(proizvodjac, naziv, godiste);
    }


    public String toCSV() {
        return  id +
                "," + proizvodjac +
                "," + naziv +
                "," + godiste;
    }

    @Override
    public String toString() {
        return "proizvodjac='" + proizvodjac + '\'' +
                ", naziv='" + naziv + '\'' +
                ", godiste=" + godiste;
    }
}
