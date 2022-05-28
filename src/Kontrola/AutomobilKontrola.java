package Kontrola;

import Entiteti.EAutomobil;
import Entiteti.EKorisnik;
import Entiteti.EModel;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class AutomobilKontrola {

    public List<EModel> vratiSveModele() {
        List<EModel> modeli = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/res/model.txt"));) {

            String linija;
            while ((linija = br.readLine()) != null) {
                String[] tokeni = linija.split(",");
                modeli.add(new EModel(tokeni[1], tokeni[2], Integer.parseInt(tokeni[3])));
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return modeli;
    }
    public boolean sacuvajAutomobil(EAutomobil a){
        try(PrintWriter pw = new PrintWriter(new FileWriter("src/res/automobili.txt", true),true);){
            pw.println(a.toCSV());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public List<EAutomobil> vratiSveAutomobile(EKorisnik korisnik) {
        List<EAutomobil> automobili = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader("src/res/automobili.txt"));) {

            String linija;
            while ((linija = br.readLine()) != null) {
                String[] tokeni = linija.split(",");
                if (Integer.parseInt(tokeni[2]) == korisnik.getId()) {

                    EModel model = nadjiModel(Integer.parseInt(tokeni[1]));

                    automobili.add(new EAutomobil(model,korisnik));

                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return automobili;
    }

    public EModel nadjiModel(int idModela) {

        try  (BufferedReader br = new BufferedReader(new FileReader("src/res/model.txt"));) {

            String linija;
            while ((linija = br.readLine()) != null) {
                String[] tokeni = linija.split(",");

                if (idModela == Integer.parseInt(tokeni[0])) {
                    EModel model = new EModel(tokeni[1],tokeni[2],Integer.parseInt(tokeni[3]));

                    return model;
                }

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
