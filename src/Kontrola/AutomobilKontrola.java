package Kontrola;

import Entiteti.EAutomobil;
import Entiteti.EModel;

import java.io.*;
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
    public boolean sacuvajAutomobil(EAutomobil a){
        try {
            PrintWriter pw = new PrintWriter(new FileWriter("src/res/automobili.txt", true),true);
            pw.println(a);
            pw.close();

        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
