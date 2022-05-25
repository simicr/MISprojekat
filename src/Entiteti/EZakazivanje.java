package Entiteti;

import java.util.Date;
import java.util.List;
import java.util.Objects;


public class EZakazivanje {
    private int id;
    private boolean otkazano;
    private Date datum;

    private List<EUsluga> usluge;
    private EAutomobil automobil;
    private EMehanicar mehanicar;

    public EZakazivanje(boolean otkazano, Date datum, List<EUsluga> usluge, EAutomobil automobil, EMehanicar mehanicar) {
        this.otkazano = otkazano;
        this.datum = datum;
        this.usluge = usluge;
        this.automobil = automobil;
        this.mehanicar = mehanicar;
        this.id = this.hashCode();
    }

    public int getId() {
        return id;
    }

    public boolean isOtkazano() {
        return otkazano;
    }

    public Date getDatum() {
        return datum;
    }

    public List<EUsluga> getUsluge() {
        return usluge;
    }

    public EAutomobil getAutomobil() {
        return automobil;
    }

    public EMehanicar getMehanicar() {
        return mehanicar;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EZakazivanje that = (EZakazivanje) o;
        return otkazano == that.otkazano && Objects.equals(datum, that.datum) && Objects.equals(usluge, that.usluge) && Objects.equals(automobil, that.automobil) && Objects.equals(mehanicar, that.mehanicar);
    }

    @Override
    public int hashCode() {
        return Objects.hash(otkazano, datum, usluge, automobil, mehanicar);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder();
        sb.append("[");
        usluge.stream().map(EUsluga::getId).forEach(x -> sb.append(x + ","));
        sb.append("]");
        return  id +
                "," + otkazano +
                "," + datum +
                "," + sb +
                "," + automobil.getBrSasija() +
                "," + mehanicar.getId();
    }
}
