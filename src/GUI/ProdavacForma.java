package GUI;

import Entiteti.EKorisnik;
import Kontrola.KorisnikKontrola;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.List;


public class ProdavacForma extends Application {



    private List<EKorisnik> info;
    // Druge forme
    private UclanjivanjeForma uf;
    private DodavanjeAutomobilaForma df;
    // Kontrole
    private KorisnikKontrola kk = new KorisnikKontrola();
    // Elementi GUIa
    private Scene scene;
    private BorderPane bp = new BorderPane();
    private HBox dno = new HBox();
    private ListView korisnici = new ListView<>();
    private Button dodajK = new Button("DODAJ\nKORISNIKA");
    private Button dodajA = new Button("OBRISI\nAUTO");
    private Button obrisi = new Button("DODAJ\nAUTO");



    public static void main(String[] args) {
        launch(args);
    }

    public ProdavacForma(){
        super();
        this.dodajK.setOnAction(e -> uclaniKorisnika());
        this.dodajA.setOnAction( e -> dodajAutomobil());
    }


    @Override
    public void start(Stage primaryStage) {
        scene = scenaZaProdavca();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Korisnici sistema");
        primaryStage.show();
    }

    public Scene scenaZaProdavca(){
        generisiDno();
        generisiKorisnike();
        return new Scene(bp, 500,300);
    }

    public void generisiDno(){
        dno.setPadding(new Insets(5));
        dno.getChildren().add(dodajK);
        dno.getChildren().add(dodajA);
        dno.getChildren().add(obrisi);
        dno.setSpacing(40);
        bp.setBottom(dno);
    }
    public void generisiKorisnike(){
        info = kk.pronadjiSveKorisnike();
        info.stream().forEach(x -> korisnici.getItems().add(x));
        bp.setCenter(korisnici);
    }

    public void uclaniKorisnika(){
        if(uf == null){
             uf = new UclanjivanjeForma(this);
        }
        uf.start(new Stage());
    }

    public void dodajAutomobil() {
        int i = korisnici.getSelectionModel().getSelectedIndex();
        if( i == -1){
            new Alert(Alert.AlertType.ERROR,"Nije izabran korisnik").showAndWait();
            return;
        }
        if ( df == null){
            df = new DodavanjeAutomobilaForma(this, info.get(i));
        }
        df.start(new Stage());
    }

}
