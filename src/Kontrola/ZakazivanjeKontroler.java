package Kontrola;

import Entiteti.*;

import java.io.*;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ZakazivanjeKontroler {


    public EMehanicar vratiSlobodnogMehanicara(LocalDateTime odKada, LocalDateTime doKada){

        try (BufferedReader br = new BufferedReader(new FileReader("src/res/mehanicar.txt"))){
            String linija;
            while ((linija = br.readLine()) != null){
                String[] tokeni = linija.split(",");
                EMehanicar mehanicar = new EMehanicar(tokeni[1], tokeni[2], tokeni[3]);
                if( jeSlobodan(mehanicar, odKada, doKada)){
                    return mehanicar;
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private boolean jeSlobodan(EMehanicar mehanicar, LocalDateTime odKada, LocalDateTime doKada){

        try (BufferedReader br = new BufferedReader(new FileReader("src/res/zakazivanja.txt"));) {

            String linija;
            while ((linija = br.readLine())!= null){
               String[] tokeni = linija.split(",");
               LocalDateTime pocetak = LocalDateTime.parse(tokeni[2]);
               LocalDateTime kraj = trajaceDo(pocetak, tokeni[3]);
                if (Integer.parseInt(tokeni[5]) == mehanicar.getId()){
                    if ((kraj.compareTo(odKada) * kraj.compareTo(doKada) <= 0)
                            || (pocetak.compareTo(odKada) * pocetak.compareTo(doKada) <= 0)) {
                        return false;
                    }
                }
            }

        } catch (IOException e){
            e.printStackTrace();
        }
        return true;
    }

    private LocalDateTime trajaceDo(LocalDateTime pocetak, String obaveze){
        String[] infoObaveza = obaveze.substring(1, obaveze.length() -1).split(";");

        long minuta = Math.round(60*Arrays.stream(infoObaveza).mapToDouble( x->
                trajanje(Integer.parseInt(x))
        ).sum());

        return pocetak.plus(minuta, ChronoUnit.MINUTES);
    }

    public double trajanje(int id){

        try (BufferedReader br = new BufferedReader(new FileReader("src/res/usluga.txt"))){
            String linija;
            while ((linija = br.readLine()) != null){
                String[] tokeni = linija.split(",");
                if(Integer.parseInt(tokeni[0]) == id){
                    return Double.parseDouble(tokeni[4]);
                }
            }
        } catch (IOException e){
            e.printStackTrace();
        }
        return -1.0;
    }

    public boolean sacuvajZakazivanje(EZakazivanje z) {
        try(PrintWriter pw = new PrintWriter(new FileWriter("src/res/zakazivanja.txt",true), true);){
            pw.println(z.toCSV());
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
}
