package GUI;

import Entiteti.*;
import Kontrola.ZakazivanjeKontroler;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ZakazivanjeForma extends Application {
    // Druge forme
    private PretragaKorisnikaForma pkf;
    private ListaAutomobilaForma laf;
    private PregledUslugaForma puf;

    // Kontrola
    private ZakazivanjeKontroler zk = new ZakazivanjeKontroler();

    // Elementi grafike

    private Stage primaryStage;
    private Scene scene;
    private BorderPane bp = new BorderPane();
    private GridPane gp = new GridPane();
    private HBox hb = new HBox();

    private Button izaberiKorisnika = new Button("DODAJ\nKORISNIKA");
    private Button izaberiUslugu = new Button("DODAJ\nUSLUGU");
    private Button izaberiAuto = new Button("IZABERI\nAUTO");
    private Button sacuvajDugme = new Button("SACUVAJ\nZAKAZIVANJE");
    private Label korisnikPolje = new Label("Korisnik");
    private Label automobilPolje = new Label("Automobil");
    private Label uslugePolje = new Label("Usluge");

    private TextField korisnik = new TextField();
    private TextField auto = new TextField();
    private TextArea usluge = new TextArea();
    private DatePicker datum = new DatePicker();

    private HBox clock = new HBox();
    private ComboBox<String> sati = new ComboBox<>();
    private ComboBox<String> minuti = new ComboBox<>();

    // informacije za zakazivanje
    private EKorisnik izabranKorisnik;
    private EAutomobil izabranAutomobil;
    private List<EUsluga> izabraneUsluge = new ArrayList<>();

    public static void main(String[] args) {
        launch(args);
    }

    public ZakazivanjeForma(){
        super();
        scene = generisiGui();
        korisnik.setEditable(false);
        auto.setEditable(false);
        usluge.setEditable(false);
        for (int i = 8; i <= 19; i++){
            String prefix = "";
            if( i < 10){
                prefix = "0";
            }
            sati.getItems().add(prefix + i);
        }
        for (int i = 0; i <= 59; i++){
            String prefix = "";
            if( i < 10){
                prefix = "0";
            }
            minuti.getItems().add(prefix + i);
        }
        sati.setMaxWidth(70);
        minuti.setMaxWidth(70);

        izaberiKorisnika.setOnAction( e -> {
            izabranKorisnik = nadjiKorisnika();
            if(izabranKorisnik != null) {
                korisnik.setText(izabranKorisnik.toString());
            }
        });

        izaberiAuto.setOnAction( e -> {
            if ( izabranKorisnik == null){
                new Alert(Alert.AlertType.ERROR, "Prvo je potrebno izabrati korisnika").showAndWait();
                return;
            }
            izabranAutomobil = izaberiAutomobil();
            if (izabranAutomobil != null){
                auto.setText(izabranAutomobil.toString());
            }
        });

        izaberiUslugu.setOnAction( e -> {
            EUsluga eu = nadjiUslugu();
            izabraneUsluge.add(eu);
            usluge.setText(usluge.getText() + "\n" + eu);
        });

        sacuvajDugme.setOnAction( e -> {
           sacuvaj();
        });
    }
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.setTitle("Zakazivanje");
        primaryStage.show();
    }

    public Scene generisiGui(){
        generisiCentar();
        generisiDugmice();
        bp.setCenter(gp);
        bp.setBottom(hb);
        return new Scene(bp, 700, 300);
    }
    public void generisiCentar(){
        gp.setHgap(10);
        gp.add(korisnikPolje, 0, 0);
        gp.add(korisnik,1,0);
        gp.add(automobilPolje,0,1);
        gp.add(auto,1,1);
        gp.add(uslugePolje, 0,2);
        gp.add(usluge, 1,2);
        clock.getChildren().add(sati);
        clock.getChildren().add(new Label(":"));
        clock.getChildren().add(minuti);
        gp.add(datum, 0, 3);
        gp.add(clock, 1,3);
    }
    public void generisiDugmice(){
        hb.getChildren().add(izaberiKorisnika);
        hb.getChildren().add(izaberiAuto);
        hb.getChildren().add(izaberiUslugu);
        hb.getChildren().add(sacuvajDugme);
        hb.setSpacing(5);
        hb.setPadding(new Insets(15));
    }

    public EKorisnik nadjiKorisnika(){
        if ( pkf == null){
            pkf = new PretragaKorisnikaForma();
        }
        pkf.start(new Stage());
        return pkf.getIzabran();
    }


    public EAutomobil izaberiAutomobil() {

        if ( laf == null){
            laf = new ListaAutomobilaForma(izabranKorisnik);
        }
        laf.start(new Stage());
        return laf.getIzabran();
    }

    public EUsluga nadjiUslugu() {

        if (puf == null){
            puf = new PregledUslugaForma();
        }
        puf.start(new Stage());
        return puf.getIzabrana();
    }

    public void sacuvaj() {
        if (izabranKorisnik == null || izabraneUsluge.size() == 0 || izabranAutomobil == null
                    || datum.getValue() == null || sati.getSelectionModel().isEmpty() || minuti.getSelectionModel().isEmpty()){
            new Alert(Alert.AlertType.ERROR, "Potrebno je uneti sva polja").showAndWait();
            return;
        }

        LocalDateTime odKad = dobijTermin();
        long vreme = Math.round(60*izabraneUsluge.stream().mapToDouble(EUsluga::getTrajanje).sum());
        LocalDateTime doKad = odKad.plus(vreme, ChronoUnit.MINUTES);

        EMehanicar mehanicar = zk.vratiSlobodnogMehanicara(odKad, doKad);
        if(mehanicar == null){
            new Alert(Alert.AlertType.ERROR, "Nema slobodnog mehanicara za ovaj termin izaberite drugi").showAndWait();
            return;
        }

        boolean uspelo = zk.sacuvajZakazivanje(new EZakazivanje(false, odKad, izabraneUsluge,
                                                                        izabranAutomobil, mehanicar));

        if ( uspelo ) {
            new Alert(Alert.AlertType.INFORMATION, "Uspesno sacuvano").showAndWait();
        } else {
            new Alert(Alert.AlertType.ERROR, "Nije uspesno sacuvano").showAndWait();
        }

    }

    private LocalDateTime dobijTermin(){
        LocalDate izabranDatum = datum.getValue();
        int izabranoSati = Integer.parseInt(sati.getSelectionModel().getSelectedItem());
        int izabranoMinuti = Integer.parseInt(minuti.getSelectionModel().getSelectedItem());

        return LocalDateTime.of(izabranDatum.getYear(), izabranDatum.getMonthValue(), izabranDatum.getDayOfMonth()
                                                            ,izabranoSati, izabranoMinuti);
    }
}
