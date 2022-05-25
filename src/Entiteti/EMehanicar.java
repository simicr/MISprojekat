package Entiteti;

import java.util.Objects;

public class EMehanicar {
    private int id;
    private String ime;
    private String prezime;
    private String username;
    


    public EMehanicar(String ime, String prezime, String username) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.username = username;
        this.id = this.hashCode();
    }

    public int getId() {
        return id;
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
        return id == that.id && Objects.equals(ime, that.ime) && Objects.equals(prezime, that.prezime) && Objects.equals(username, that.username);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, ime, prezime, username);
    }

    @Override
    public String toString() {
        return  id +
                "," + ime  +
                "," + prezime  +
                "," + username;
    }
}
