package GUI;

import Entiteti.EAutomobil;
import Entiteti.EKorisnik;
import Entiteti.EModel;
import Kontrola.AutomobilKontrola;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class DodavanjeAutomobilaForma extends Application {
    private  EKorisnik korisnik;
    // Forma
    private ProdavacForma pf;
    private UclanjivanjeForma uf;
    // Kontrola
    private AutomobilKontrola ak;
    // Elementi
    private Stage primaryStage;
    private BorderPane bp;
    private Button sacuvajDugme;
    private ListView modeli;
    private EModel izabran;
    private List<EModel> sviModeli;

    private DodavanjeAutomobilaForma(){
        super();
        this.uf = uf;
        this.sacuvajDugme = new Button("IZABERI");
        this.ak = new AutomobilKontrola();
        this.bp = new BorderPane();
        this.modeli = new ListView();
        this.sviModeli = new ArrayList<>();
    }
    public DodavanjeAutomobilaForma(UclanjivanjeForma uf){
        this();
        this.uf = uf;
    }

    public DodavanjeAutomobilaForma(ProdavacForma pf, EKorisnik korisnik) {
        this();
        this.pf = pf;
        this.korisnik = korisnik;
    }
    public static void main(String[] args) {
        launch(args);
    }

    public EModel getIzabran() {
        return izabran;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        generisiModele();
        bp.setBottom(sacuvajDugme);
        sacuvajDugme.setOnAction(e -> dodaj());
        Scene scene = new Scene(bp);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Biranje modela");
        primaryStage.showAndWait();
    }

    public void generisiModele() {
        modeli = new ListView<>();
        sviModeli = ak.vratiSveModele();
        sviModeli.stream().forEach(x -> modeli.getItems().add(x));
        bp.setCenter(modeli);
    }

    public void dodaj() {
        int i = modeli.getSelectionModel().getSelectedIndex();

        if (i == -1){
            new Alert(Alert.AlertType.ERROR, "Potrebno je izabrati model").showAndWait();
            return;
        }

        if( uf != null) {
            uf.getModelTF().setText(sviModeli.get(i).toString());
        } else if (pf != null){
            EAutomobil a = new EAutomobil(sviModeli.get(i), korisnik);
            boolean dodato = ak.sacuvajAutomobil(a);
            if(!dodato){
                new Alert(Alert.AlertType.ERROR,"Nije sacuvan automobil").showAndWait();
            } else {
                new Alert(Alert.AlertType.INFORMATION,"Uspesno sacuvan automobil").showAndWait();
            }
        }

        izabran = sviModeli.get(i);
        primaryStage.close();
    }
}
