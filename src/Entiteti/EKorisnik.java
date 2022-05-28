package Entiteti;

import java.util.Objects;

public class EKorisnik {
    private String ime;
    private String prezime;
    private String username;
    private String brTelefona;
    private String jmbg;
    private int id;

    public EKorisnik(String ime, String prezime, String username, String brTelefona, String jmbg) {
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.brTelefona = brTelefona;
        this.jmbg = jmbg;
        this.id = this.hashCode();
    }

    public String getIme() {
        return ime;
    }
    public String getPrezime() {
        return prezime;
    }
    public String getUsername() {
        return username;
    }
    public String getBrTelefona() {
        return brTelefona;
    }
    public String getJmbg() {
        return jmbg;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EKorisnik eKorisnik = (EKorisnik) o;
        return Objects.equals(ime, eKorisnik.ime) && Objects.equals(prezime, eKorisnik.prezime) && Objects.equals(username, eKorisnik.username) && Objects.equals(brTelefona, eKorisnik.brTelefona) && Objects.equals(jmbg, eKorisnik.jmbg);
    }

    @Override
    public int hashCode() {
        return Objects.hash(ime, prezime, username, brTelefona, jmbg);
    }


    public String toCSV() {
        return  id + "," + ime +
                "," + prezime  +
                "," + username +
                "," + brTelefona +
                "," + jmbg;
    }

    @Override
    public String toString() {
        return "ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", username='" + username + '\'' +
                ", brTelefona='" + brTelefona + '\'' +
                ", jmbg='" + jmbg;
    }
}
