package GUI;

import Entiteti.EAutomobil;
import Entiteti.EKorisnik;
import Kontrola.AutomobilKontrola;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class ListaAutomobilaForma extends Application {


    private AutomobilKontrola ak = new AutomobilKontrola();

    private Stage primaryStage;
    private EKorisnik korisnik;
    private ListView automobiliKorisnika = new ListView();
    private List<EAutomobil> automobili = new ArrayList<>();
    private Button izaberiDugme = new Button("IZABERI\n");
    private EAutomobil izabran;
    private VBox vb = new VBox();

    public static void main(String[] args) {
        launch(args);
    }

    public ListaAutomobilaForma(EKorisnik korisnik){
        this.korisnik = korisnik;
        automobili = ak.vratiSveAutomobile(korisnik);
        automobili.stream().forEach(x -> automobiliKorisnika.getItems().add(x));


        izaberiDugme.setOnAction( e -> {
            izaberi();
        });
    }

    public EAutomobil getIzabran(){
        return izabran;
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        Scene scene = generisiGui();
        primaryStage.setScene(scene);
        primaryStage.setTitle("Biranje automobila");
        primaryStage.showAndWait();
    }

    public Scene generisiGui() {
        vb.getChildren().add(automobiliKorisnika);
        vb.getChildren().add(izaberiDugme);
        return new Scene(vb);
    }

    public void izaberi(){
        int index = automobiliKorisnika.getSelectionModel().getSelectedIndex();

        if ( index == -1){
            new Alert(Alert.AlertType.ERROR, "Treba izabrati automobil").showAndWait();
            return;
        }

        izabran = automobili.get(index);
        primaryStage.close();
    }
}
