package GUI;

import Entiteti.EModel;
import Kontrola.AutomobilKontrola;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.List;

public class DodavanjeAutomobilaForma extends Application {
    // info
    private List<EModel> sviModeli;
    // Forma
    private ProdavacForma pf;
    private UclanjivanjeForma uf;
    // Kontrola
    private AutomobilKontrola ak;
    // Elementi
    private BorderPane bp;
    private Button sacuvajDugme;
    private ListView modeli;


    public DodavanjeAutomobilaForma(UclanjivanjeForma uf){
        super();
        this.uf = uf;
        this.sacuvajDugme = new Button("IZABERI");
        this.ak = new AutomobilKontrola();
        this.bp = new BorderPane();
        this.modeli = new ListView();
        this.sviModeli = new ArrayList<>();
    }
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        generisiModele();
        bp.setBottom(sacuvajDugme);
        sacuvajDugme.setOnAction(e -> sacuvaj(primaryStage));
        Scene scene = new Scene(bp);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Biranje modela");
        primaryStage.show();
    }

    public void generisiModele() {
        modeli = new ListView<>();
        sviModeli = ak.vratiSveModele();
        sviModeli.stream().forEach(x -> modeli.getItems().add(x));
        bp.setCenter(modeli);
    }

    public void sacuvaj(Stage primaryStage) {
        int i = modeli.getSelectionModel().getSelectedIndex();
        uf.setIzabran(sviModeli.get(i - 1));
        uf.getModelTF().setText(sviModeli.get(i - 1).toString());
        primaryStage.close();
    }
}
