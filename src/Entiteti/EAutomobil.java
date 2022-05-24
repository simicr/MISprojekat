package Entiteti;

import java.util.Objects;

public class EAutomobil {
    private String brSasija;
    private EModel model;
    private EKorisnik vlasnik;

    // dodamo zakazivanja ako ostane isto


    public EAutomobil(String brSasija, EModel model, EKorisnik vlasnik) {
        this.brSasija = brSasija;
        this.model = model;
        this.vlasnik = vlasnik;
    }

    public String getBrSasija() {
        return brSasija;
    }

    public EModel getModel() {
        return model;
    }

    public EKorisnik getVlasnik() {
        return vlasnik;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EAutomobil that = (EAutomobil) o;
        return Objects.equals(brSasija, that.brSasija) && Objects.equals(model, that.model) && Objects.equals(vlasnik, that.vlasnik);
    }

    @Override
    public int hashCode() {
        return Objects.hash(brSasija, model, vlasnik);
    }

    @Override
    public String toString() {
        return "EAutomobil{" +
                "brSasija='" + brSasija + '\'' +
                ", model=" + model +
                ", vlasnik=" + vlasnik +
                '}';
    }
}
