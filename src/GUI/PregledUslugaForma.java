package GUI;

import Entiteti.EUsluga;
import Kontrola.UslugaKontrola;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class PregledUslugaForma extends Application {


    private ZakazivanjeForma zf;
    private DetaljiUslugaForma duf;

    private UslugaKontrola uk = new UslugaKontrola();

    private Stage primaryStage;
    private Scene scene;

    private BorderPane bp = new BorderPane();
    private HBox hb = new HBox();

    private List<EUsluga> spisakUsluga = new ArrayList<>();
    private ListView prikazUsluga = new ListView();
    private List<EUsluga> vecIzabraneUsluge = new ArrayList<>();
    private EUsluga izabranaUsluga = new EUsluga("","",0,0);

    private Button prikaziDetalje = new Button("DETALJI\n");
    private Button dodajDugme = new Button("DODAJ\n");



    public static void main(String[] args) {
        launch(args);
    }

    public EUsluga getIzabrana() { return izabranaUsluga; }

    public PregledUslugaForma(){
        spisakUsluga = uk.vratiSveUsluge();
        spisakUsluga.stream().forEach( x-> prikazUsluga.getItems().add(x));
        scene = generisiGUI();
        dodajDugme.setOnAction( e -> zakazi());
        prikaziDetalje.setOnAction( e -> prikazi());
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setTitle("Biranje usluge");
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    public Scene generisiGUI(){
        hb.getChildren().add(dodajDugme);
        hb.getChildren().add(prikaziDetalje);
        hb.setPadding(new Insets(5));
        hb.setSpacing(15);
        bp.setBottom(hb);
        bp.setCenter(prikazUsluga);
        return new Scene(bp, 400, 400);
    }

    public void zakazi() {
        int index = prikazUsluga.getSelectionModel().getSelectedIndex();

        if ( index == -1){
            new Alert(Alert.AlertType.ERROR, "Potrebno je izabrati jednu uslugu").showAndWait();
            return;
        }

        EUsluga eu = spisakUsluga.get(index);
        if (vecIzabraneUsluge.contains(eu)) {
            new Alert(Alert.AlertType.ERROR, "Vec ste izabrali ovu uslugu").showAndWait();
            return;
        }

        vecIzabraneUsluge.add(eu);
        izabranaUsluga = eu;
        primaryStage.close();
    }

    public void prikazi() {
        int index = prikazUsluga.getSelectionModel().getSelectedIndex();

        if ( index == -1){
            new Alert(Alert.AlertType.ERROR, "Potrebno je izabrati jednu uslugu").showAndWait();
            return;
        }
        izabranaUsluga = spisakUsluga.get(index);

        if (duf == null){
            duf = new DetaljiUslugaForma(this);
        }
        duf.start(new Stage());
    }
}
