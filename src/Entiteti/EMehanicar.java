package Entiteti;

import java.util.Objects;

public class EMehanicar {
    private int sifra;
    private String ime;
    private String prezime;
    private String username;

    //dodamo zakazivanja ako ne zelimo da ostavimo vezu


    public EMehanicar(int sifra, String ime, String prezime, String username) {
        this.sifra = sifra;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
    }

    public int getSifra() {
        return sifra;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        EMehanicar that = (EMehanicar) o;
        return sifra == that.sifra && Objects.equals(ime, that.ime) && Objects.equals(prezime, that.prezime) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(sifra, ime, prezime, username);
    }

    @Override
    public String toString() {
        return "EMehanicar{" +
                "sifra=" + sifra +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", username='" + username + '\'' +
                '}';
    }
}
