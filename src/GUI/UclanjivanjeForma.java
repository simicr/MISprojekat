package GUI;

import Entiteti.EAutomobil;
import Entiteti.EKorisnik;
import Entiteti.EModel;
import Kontrola.AutomobilKontrola;
import Kontrola.KorisnikKontrola;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class UclanjivanjeForma extends Application {

    // Forme
    private ProdavacForma pf;
    private DodavanjeAutomobilaForma daf;
    // Kontrole
    private KorisnikKontrola kk;
    private AutomobilKontrola ak;
    //info
    private EModel izabran;
    // Elementi
    private GridPane gp;
    private Label ime;
    private Label prezime;
    private Label jmbg;
    private Label username;
    private Label brojTelefona;
    private TextField[] unos;
    private Button model;
    private Button sacuvajDugme;



    public static void main(String[] args) {
        launch(args);
    }

    public UclanjivanjeForma(ProdavacForma odakle){
        super();
        pf = odakle;

        kk = new KorisnikKontrola();
        ak = new AutomobilKontrola();

        gp = new GridPane();
        ime = new Label("Ime");
        prezime = new Label("Prezime");
        jmbg = new Label("JMBG");
        brojTelefona = new Label("Broj telefona");
        username = new Label("Username");
        unos = new TextField[6];
        for(int i = 0; i < 6; i++){
            unos[i] = new TextField();
        }

        model = new Button("MODEL");
        model.setOnAction(e ->
        {
            EModel m = dodaj();
            izabran = m;
        });
        sacuvajDugme = new Button("SACUVAJ");
    }
    public TextField getModelTF() {
        return unos[5];
    }
    @Override
    public void start(Stage primaryStage) {
        generisiGui();
        Scene scena = new Scene(gp, 400, 300);
        sacuvajDugme.setOnAction(e -> sacuvaj(primaryStage) );
        primaryStage.setScene(scena);
        primaryStage.setTitle("Uclanjivanje korisnika");
        primaryStage.showAndWait();
    }
    public void generisiGui(){
        gp.add(ime, 0, 0);
        gp.add(prezime, 0,1);
        gp.add(username, 0,2);
        gp.add(jmbg,0,3);
        gp.add(brojTelefona, 0,4);
        gp.add(model,0,5);
        gp.add(sacuvajDugme, 0,6);
        for(int i = 0; i < 6; i++){
            gp.add(unos[i] ,1 , i);
        }
        unos[5].setEditable(false);
    }
    public void sacuvaj(Stage primaryStage) {

        boolean nepravilno = Arrays.stream(unos).anyMatch(x ->x.getText().equals(""));
        if(nepravilno) {
            new Alert(Alert.AlertType.ERROR, "Nisu unesena sva polja").showAndWait();
            return;
        }
        EKorisnik k = new EKorisnik(unos[0].getText(), unos[1].getText(), unos[2].getText(), unos[4].getText(), unos[3].getText());
        boolean korisnikDodat = kk.sacuvajKorisnika(k);
        boolean automobilDodat =  ak.sacuvajAutomobil(new EAutomobil(izabran, k));

        if(korisnikDodat && automobilDodat){
            new Alert(Alert.AlertType.INFORMATION, "Korisnik je uspesno sacuvan").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Nije sacuvan novi korisnik").showAndWait();
        }
        pf.generisiKorisnike();
        primaryStage.close();
    }
    public EModel dodaj() {
        if (daf == null){
            daf = new DodavanjeAutomobilaForma(this);
        }
        daf.start(new Stage());
        return daf.getIzabran();
    }
}
