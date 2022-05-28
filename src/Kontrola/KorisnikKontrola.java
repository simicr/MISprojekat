package Kontrola;

import Entiteti.EAutomobil;
import Entiteti.EKorisnik;
import Entiteti.EModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class KorisnikKontrola {
    public List<EKorisnik> pronadjiSveKorisnike(){
        List<EKorisnik> korisnici = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/res/korisnici.txt"));) {

            String linija;
            while ((linija = br.readLine()) != null) {
                String[] tokeni = linija.split(",");
                korisnici.add(new EKorisnik(tokeni[1], tokeni[2], tokeni[3], tokeni[4],tokeni[5]));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return korisnici;
    }

    public boolean sacuvajKorisnika(EKorisnik k){
        try (PrintWriter pw = new PrintWriter(new FileWriter("src/res/korisnici.txt",true), true);) {
            pw.println(k.toCSV());
        } catch (IOException | NullPointerException e){
            e.printStackTrace();
            return false;
        }
        return  true;
    }

    public EKorisnik nadjiKorisnika( int id){

        try  (BufferedReader br = new BufferedReader(new FileReader("src/res/korisnici.txt"));) {
            String linija;
            while ((linija = br.readLine()) != null) {
                String[] tokeni = linija.split(",");
                if (id == Integer.parseInt(tokeni[0])) {
                    EKorisnik korisnik = new EKorisnik(tokeni[1],tokeni[2],tokeni[3], tokeni[4], tokeni[5]);
                    return korisnik;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
