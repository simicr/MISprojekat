package GUI;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

public class ZakazivanjeForma extends Application {

    private BorderPane bp = new BorderPane();
    private GridPane gp = new GridPane();
    private HBox hb = new HBox();
    private Button izaberiKorisnika = new Button("DODAJ\nKORISNIKA");
    private Button izaberiUslugu = new Button("DODAJ\nUSLUGU");
    private Button izaberiAuto = new Button("IZABERI\nAUTO");
    private Button sacuvaj = new Button("SACUVAJ\nZAKAZIVANJE");
    private Label korisnikPolje = new Label("Korisnik");
    private Label automobilPolje = new Label("Automobil");
    private Label uslugePolje = new Label("Usluge");

    private TextField korisnik = new TextField();
    private TextField auto = new TextField();
    private TextArea usluge = new TextArea();
    private DatePicker datum = new DatePicker();

    public static void main(String[] args) {
        launch(args);
    }

    public ZakazivanjeForma(){
        korisnik.setEditable(false);
        auto.setEditable(false);
        usluge.setEditable(false);
    }
    @Override
    public void start(Stage primaryStage) {
        generisiGui();
        Scene scene = new Scene(bp, 700, 300);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public void generisiGui(){
        generisiCentar();
        generisiDugmice();
        bp.setCenter(gp);
        bp.setBottom(hb);
    }
    public void generisiCentar(){
        gp.add(korisnikPolje, 0, 0);
        gp.add(korisnik,1,0);
        gp.add(automobilPolje,0,1);
        gp.add(auto,1,1);
        gp.add(uslugePolje, 0,2);
        gp.add(usluge, 1,2);
    }
    public void generisiDugmice(){
        hb.getChildren().add(izaberiKorisnika);
        hb.getChildren().add(izaberiAuto);
        hb.getChildren().add(izaberiUslugu);
        hb.getChildren().add(sacuvaj);
        hb.setSpacing(5);
        hb.setPadding(new Insets(15));
    }
}
