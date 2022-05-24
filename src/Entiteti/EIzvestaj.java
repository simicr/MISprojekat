package Entiteti;

import java.util.Date;
import java.util.Objects;

public class EIzvestaj {

    private String opis;
    private Date datum;
    private EZakazivanje zakazivanje;
    private EMehanicar mehanicar;

    public EIzvestaj(String opis, Date datum, EZakazivanje zakazivanje, EMehanicar mehanicar) {
        this.opis = opis;
        this.datum = datum;
        this.zakazivanje = zakazivanje;
        this.mehanicar = mehanicar;
    }

    public String getOpis() {
        return opis;
    }

    public Date getDatum() {
        return datum;
    }

    public EZakazivanje getZakazivanje() {
        return zakazivanje;
    }

    public EMehanicar getMehanicar() {
        return mehanicar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EIzvestaj eIzvestaj = (EIzvestaj) o;
        return Objects.equals(opis, eIzvestaj.opis) && Objects.equals(datum, eIzvestaj.datum) && Objects.equals(zakazivanje, eIzvestaj.zakazivanje) && Objects.equals(mehanicar, eIzvestaj.mehanicar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(opis, datum, zakazivanje, mehanicar);
    }

    @Override
    public String toString() {
        return "EIzvestaj{" +
                "opis='" + opis + '\'' +
                ", datum=" + datum +
                ", zakazivanje=" + zakazivanje +
                ", mehanicar=" + mehanicar +
                '}';
    }
}