package GUI;

import Entiteti.EUsluga;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class DetaljiUslugaForma extends Application {

    private PregledUslugaForma puf;
    private EUsluga usluga;

    private Stage primaryStage;
    private Scene scene;

    private BorderPane bp = new BorderPane();
    private TextArea opis = new TextArea();

    public static void main(String[] args) {
        launch(args);
    }

    public DetaljiUslugaForma(PregledUslugaForma puf){
        super();
        this.puf = puf;
        scene = generisiGUI();
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        this.usluga = puf.getIzabrana();
        opis.setText(String.format(" %s \n Cena: %.2f eu \n Trajanje: %.2fh\n Opis: %s \n KRAJ"
                        , usluga.getNaziv(), usluga.getCena(), usluga.getTrajanje(), usluga.getOpis()));
        primaryStage.setTitle("Detaljan opis usluge");
        primaryStage.setResizable(false);
        primaryStage.setScene(scene);
        primaryStage.showAndWait();
    }

    public Scene generisiGUI() {
        opis.setEditable(false);
        opis.setFont(new Font(24));
        opis.setMaxWidth(300);
        bp.setCenter(opis);
        return new Scene(bp);
    }
}
