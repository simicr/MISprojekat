package GUI;

import Entiteti.EKorisnik;
import Kontrola.KorisnikKontrola;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PretragaKorisnikaForma extends Application {



    private KorisnikKontrola kk = new KorisnikKontrola();

    private Stage primaryStage;
    private Scene scene;
    private ListView izborKorisnika = new ListView();
    private List<EKorisnik> korisnici = new ArrayList<>();
    private Button izaberi = new Button("IZABERI\n");
    private EKorisnik izabran;
    private VBox vb = new VBox();

    public static void main(String[] args) {
        launch(args);
    }


    public PretragaKorisnikaForma(){
        korisnici = kk.pronadjiSveKorisnike();
        korisnici.stream().forEach(x -> izborKorisnika.getItems().add(x));
        izaberi.setOnAction( e -> izaberiKorisnika() );

        if(scene == null){
            scene = generisiGui();
        }
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Biranje korisnika za zakazivanje");
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    public EKorisnik getIzabran(){
        return izabran;
    }
    public Scene generisiGui(){
        vb.getChildren().add(izborKorisnika);
        vb.getChildren().add(izaberi);
        return new Scene(vb);
    }
    public void izaberiKorisnika(){
        int index = izborKorisnika.getSelectionModel().getSelectedIndex();

        if(index == -1){
            new Alert(Alert.AlertType.ERROR, "Treba izabrati korisnika prvo").showAndWait();
            return;
        }

        izabran = korisnici.get(index);
        primaryStage.close();
    }
}
