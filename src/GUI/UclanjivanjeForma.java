package GUI;

import Entiteti.EKorisnik;
import Entiteti.EModel;
import Kontrola.KorisnikKontrola;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

public class UclanjivanjeForma extends Application {

    // Forme
    private ProdavacForma pf;
    // Kontrole
    private KorisnikKontrola kk;
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

    public TextField getModelTF() {
        return unos[5];
    }
    public void setIzabran(EModel m){
        this.izabran = m;
    }
    public UclanjivanjeForma(ProdavacForma odakle){
        pf = odakle;
        kk = new KorisnikKontrola();
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
        model.setOnAction(e -> dodajAutomobil());
        sacuvajDugme = new Button("SACUVAJ");
    }
    @Override
    public void start(Stage primaryStage) {
        generisiGui();
        Scene scena = new Scene(gp);
        sacuvajDugme.setOnAction(e -> sacuvaj(primaryStage) );
        primaryStage.setScene(scena);
        primaryStage.setTitle("Uclanjivanje korisnika");
        primaryStage.show();
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
        EKorisnik k = new EKorisnik(unos[0].getText(), unos[1].getText(), unos[2].getText(), unos[4].getText(), unos[3].getText());
        kk.sacuvajKorisnika(k);
        pf.generisiKorisnike();
        primaryStage.close();
    }
    public void dodajAutomobil() {
        DodavanjeAutomobilaForma daf = new DodavanjeAutomobilaForma(this);
        daf.start(new Stage());
    }
}
