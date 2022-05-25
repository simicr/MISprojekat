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
    private KorisnikKontrola kk = new KorisnikKontrola();
    private AutomobilKontrola ak = new AutomobilKontrola();
    //info
    private List<EModel> sacuvani;
    private EModel izabran;
    // Elementi
    private GridPane gp = new GridPane();
    private Label ime = new Label("Ime");
    private Label prezime = new Label("Prezime");
    private Label jmbg = new Label("JMBG");
    private Label username = new Label("Username");
    private Label brojTelefona = new Label("Broj telefona");
    private TextField[] unos = new TextField[6];
    private Button model = new Button("MODEL");
    private Button sacuvajDugme = new Button("SACUVAJ");;



    public static void main(String[] args) {
        launch(args);
    }

    public UclanjivanjeForma(ProdavacForma odakle){
        super();
        pf = odakle;
        for(int i = 0; i < 6; i++){
            unos[i] = new TextField();
        }
<<<<<<< HEAD
=======
        model = new Button("MODEL");
>>>>>>> 569b6a6f3378233e42a2c781278ed7b33e069bac
        model.setOnAction(e ->
        {
            EModel m = dodajAutomobil();
            sacuvani.add(m);
        });
<<<<<<< HEAD
=======

        sacuvajDugme = new Button("SACUVAJ");
        sacuvani = new ArrayList<>();
>>>>>>> 569b6a6f3378233e42a2c781278ed7b33e069bac
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

        boolean nepravilno = Arrays.stream(unos).anyMatch(x ->x.getText().equals(""));
        if(nepravilno) {
            new Alert(Alert.AlertType.ERROR, "Nisu unesena sva polja").showAndWait();
            return;
        }
        EKorisnik k = new EKorisnik(unos[0].getText(), unos[1].getText(), unos[2].getText(), unos[4].getText(), unos[3].getText());
        boolean korisnikDodat = kk.sacuvajKorisnika(k);
        boolean automobilDodat = true;

        for(EModel m: sacuvani) {
            automobilDodat = automobilDodat && ak.sacuvajAutomobil(new EAutomobil(m, k));
        }
        if(korisnikDodat && automobilDodat){
            new Alert(Alert.AlertType.INFORMATION, "Korisnik je uspesno sacuvan").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Nije sacuvan novi korisnik").showAndWait();
        }
        pf.generisiKorisnike();
        primaryStage.close();
    }
    public EModel dodajAutomobil() {
        if (daf == null){
            daf = new DodavanjeAutomobilaForma(this);
        }
        daf.start(new Stage());
        return daf.getIzabran();
    }
}
