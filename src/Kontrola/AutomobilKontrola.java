package Kontrola;

import Entiteti.EModel;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class AutomobilKontrola {

    public List<EModel> vratiSveModele() {
        List<EModel> modeli = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader("src/res/model.txt"));
            String linija;
            while ((linija = br.readLine()) != null) {
                String[] tokeni = linija.split(",");
                modeli.add(new EModel(tokeni[1], tokeni[2], Integer.parseInt(tokeni[3])));
            }

            br.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return modeli;
    }
}
