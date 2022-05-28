package Kontrola;

import Entiteti.EKorisnik;
import Entiteti.EUsluga;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class UslugaKontrola {

    public List<EUsluga> vratiSveUsluge(){
        List<EUsluga> usluge = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/res/usluga.txt"));) {

            String linija;
            while ((linija = br.readLine()) != null) {
                String[] tokeni = linija.split(",");
                usluge.add(new EUsluga(tokeni[1],tokeni[2],Double.parseDouble(tokeni[3]),Double.parseDouble(tokeni[4])));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return usluge;
    }

}
