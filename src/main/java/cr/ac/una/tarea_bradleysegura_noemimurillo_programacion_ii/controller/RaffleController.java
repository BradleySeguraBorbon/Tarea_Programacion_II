/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.animation.PauseTransition;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.util.Duration;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class RaffleController extends Controller implements Initializable {

    @FXML
    private Label lblMainInfo;
    @FXML
    private Label lblInstructions;
    @FXML
    private MFXButton btnRaffle;
    @FXML
    private HBox hboxRow1;
    @FXML
    private HBox hboxRow2;
    @FXML
    private HBox hboxRow3;
    @FXML
    private HBox hboxRow4;
    @FXML
    private HBox hboxRow5;

    private ArrayList<Affiliated> participants;
    private ArrayList<HBox> containers;
    private final String[] styleClasses = {"jfx-lbl-raffle-heart", "jfx-lbl-raffle-square", "jfx-lbl-raffle-hexagon", "jfx-lbl-raffle-oval"};
    private Iterator<HBox> containersIterator;
    private Iterator<Node> labelsIterator;
    private Integer totalRaffleSteps, currentRaffleSteps;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*//PRUEBA

        Affiliated affiliated1 = new Affiliated("Juan", "González", "Martínez", 25, Affiliated.Sexo.MASCULINO, null, "CajaCosta");
        Affiliated affiliated2 = new Affiliated("María", "López", "Sánchez", 35, Affiliated.Sexo.FEMENINO, null, "CajaSolidaridad");
        Affiliated affiliated3 = new Affiliated("Luis", "Herrera", "Ramírez", 40, Affiliated.Sexo.MASCULINO, null, "CoopeVida");
        Affiliated affiliated4 = new Affiliated("Ana", "Jiménez", "Pérez", 30, Affiliated.Sexo.FEMENINO, null, "CajaSur");
        Affiliated affiliated5 = new Affiliated("Roberto", "Díaz", "Gutiérrez", 28, Affiliated.Sexo.MASCULINO, null, "CoopeAlianza");
        Affiliated affiliated6 = new Affiliated("Carolina", "Rodríguez", "Fernández", 45, Affiliated.Sexo.FEMENINO, null, "CajaRural");
        Affiliated affiliated7 = new Affiliated("Miguel", "Martínez", "Gómez", 22, Affiliated.Sexo.MASCULINO, null, "CoopeFortuna");
        Affiliated affiliated8 = new Affiliated("Sofía", "Sánchez", "López", 37, Affiliated.Sexo.FEMENINO, null, "CajaSegura");
        Affiliated affiliated9 = new Affiliated("Diego", "Hernández", "García", 29, Affiliated.Sexo.MASCULINO, null, "CoopeUnión");
        Affiliated affiliated10 = new Affiliated("Laura", "Fernández", "Hernández", 31, Affiliated.Sexo.FEMENINO, null, "CajaAmiga");
        Affiliated affiliated11 = new Affiliated("Javier", "Gutiérrez", "Martínez", 33, Affiliated.Sexo.MASCULINO, null, "CoopePaz");
        Affiliated affiliated12 = new Affiliated("Elena", "Pérez", "Jiménez", 27, Affiliated.Sexo.FEMENINO, null, "CajaPopular");
        Affiliated affiliated13 = new Affiliated("Andrés", "Ramírez", "Díaz", 38, Affiliated.Sexo.MASCULINO, null, "CoopeSolidaridad");
        Affiliated affiliated14 = new Affiliated("Paula", "Gómez", "Rodríguez", 42, Affiliated.Sexo.FEMENINO, null, "CajaAmistad");
        Affiliated affiliated15 = new Affiliated("Carlos", "López", "Sánchez", 24, Affiliated.Sexo.MASCULINO, null, "CoopeLibertad");
        Affiliated affiliated16 = new Affiliated("Isabel", "Martínez", "González", 36, Affiliated.Sexo.FEMENINO, null, "CajaUnida");
        Affiliated affiliated17 = new Affiliated("Ricardo", "Hernández", "Jiménez", 26, Affiliated.Sexo.MASCULINO, null, "CoopeAmor");
        Affiliated affiliated18 = new Affiliated("Adriana", "García", "Fernández", 39, Affiliated.Sexo.FEMENINO, null, "CajaFácil");
        Affiliated affiliated19 = new Affiliated("Pablo", "Díaz", "Ramírez", 32, Affiliated.Sexo.MASCULINO, null, "CoopeSeguridad");
        Affiliated affiliated20 = new Affiliated("Gabriela", "Sánchez", "Pérez", 41, Affiliated.Sexo.FEMENINO, null, "CajaRica");
        Affiliated affiliated21 = new Affiliated("Fernando", "Jiménez", "Gómez", 23, Affiliated.Sexo.MASCULINO, null, "CoopeFeliz");
        Affiliated affiliated22 = new Affiliated("Alejandra", "Martínez", "Herrera", 34, Affiliated.Sexo.FEMENINO, null, "CajaÁgil");
        Affiliated affiliated23 = new Affiliated("Óscar", "González", "López", 43, Affiliated.Sexo.MASCULINO, null, "CoopeProgreso");
        Affiliated affiliated24 = new Affiliated("Patricia", "Hernández", "Ramírez", 28, Affiliated.Sexo.FEMENINO, null, "CajaVida");
        Affiliated affiliated25 = new Affiliated("Jorge", "López", "Gutiérrez", 37, Affiliated.Sexo.MASCULINO, null, "CoopeUnidos");
        Affiliated affiliated26 = new Affiliated("Mónica", "García", "Martínez", 30, Affiliated.Sexo.FEMENINO, null, "CajaCosta");

        ArrayList<Affiliated> participantsArray = new ArrayList<>();
        participantsArray.add(affiliated1);
        participantsArray.add(affiliated2);
        participantsArray.add(affiliated3);
        participantsArray.add(affiliated4);
        participantsArray.add(affiliated5);
        participantsArray.add(affiliated6);
        participantsArray.add(affiliated7);
        participantsArray.add(affiliated8);
        participantsArray.add(affiliated9);
        participantsArray.add(affiliated10);
        participantsArray.add(affiliated11);
        participantsArray.add(affiliated12);
        participantsArray.add(affiliated13);
        participantsArray.add(affiliated14);
        participantsArray.add(affiliated15);
        participantsArray.add(affiliated16);
        participantsArray.add(affiliated17);
        participantsArray.add(affiliated18);
        participantsArray.add(affiliated19);
        participantsArray.add(affiliated20);
        participantsArray.add(affiliated21);
        participantsArray.add(affiliated22);
        participantsArray.add(affiliated23);
        participantsArray.add(affiliated24);
        participantsArray.add(affiliated25);
        participantsArray.add(affiliated26);

        for (Affiliated affiliated : participantsArray) {
            affiliated.setSpecialTickets(1);
        }

        AppContext.getInstance().set("auxAffiliated", participantsArray);
        //FIN PRUEBA */

        this.participants = new ArrayList();
        this.containers = new ArrayList();
        HBox[] containersArray = {this.hboxRow1, this.hboxRow2, this.hboxRow3, this.hboxRow4, this.hboxRow5};
        for (HBox container : containersArray) {
            this.containers.add(container);
        }
        setupLabels();
    }

    @Override
    public void initialize() {

    }
    
    public void setupParticipants() {
        if(this.participants != null) {
            this.participants.clear();
        }
        if (AppContext.getInstance().get("affiliated") != null) {
            for (Affiliated affiliated : (ArrayList<Affiliated>) AppContext.getInstance().get("affiliated")) {
                if (affiliated.getSpecialTickets() >= 3) {
                    this.participants.add(affiliated);
                }
            }
        }
    }

    public void setupLabels() {
        clearContainers(); 
        setupParticipants();
        Integer participantsNum = 0, currentContainer = 0;
        for (Affiliated participant : this.participants) {
            if (participantsNum >= 26 || currentContainer >= this.containers.size()) {
                new Mensaje().show(Alert.AlertType.INFORMATION, "CUPOS LLENOS", "Se ha alcanzado el número máximo de participantes (26)");
                break;
            }
            Label lblParticipant = new Label(participant.getName() + " " + participant.getFirstLastName());
            lblParticipant.setUserData(participant.getFolio());
            lblParticipant.getStyleClass().add(getRandomStyleClass());
            if (containers.get(currentContainer).getChildren().size() == 8) {
                currentContainer++;
                containers.get(currentContainer).getChildren().add(lblParticipant);
                participantsNum++;
                currentContainer++;
                continue;
            }
            containers.get(currentContainer).getChildren().add(lblParticipant);
            participantsNum++;
        }
    }

    public void raffle() {
        if (this.participants.size() >= 2) {
            Random numGenerator = new Random();
            totalRaffleSteps = numGenerator.nextInt(this.participants.size() * 2);
            currentRaffleSteps = 0;
            this.containersIterator = this.containers.iterator();
            this.labelsIterator = Collections.emptyIterator();
            iterateNextContainer();
        } else {
            new Mensaje().show(Alert.AlertType.ERROR, "INSUFICIENTES PARTICIPANTES", "No hay suficientes participantes para iniciar el sorteo. Se requieren al menos dos participantes para continuar.");
        }
    }

    public void iterateNextContainer() {
        if (containersIterator.hasNext()) {
            HBox currentContainer = containersIterator.next();
            labelsIterator = currentContainer.getChildren().iterator();
            iterateNextLabel();
        } else {
            this.containersIterator = this.containers.iterator();
            iterateNextContainer();
        }
    }

    public void iterateNextLabel() {
        if (labelsIterator.hasNext()) {
            Node currentLabel = labelsIterator.next();
            if (currentLabel instanceof Label) {
                PauseTransition pause = new PauseTransition(Duration.millis(200));
                pause.setOnFinished(event -> {
                    currentLabel.getStyleClass().remove("jfx-lbl-raffle-selected");
                    currentRaffleSteps++;
                    if (Objects.equals(currentRaffleSteps, totalRaffleSteps)) {
                        Affiliated winner = getWinner((String)currentLabel.getUserData());
                        new Mensaje().show(Alert.AlertType.INFORMATION, "FELICIDADES", winner.getFullName() + " , con FOLIO: " + winner.getFolio()+ " es el(la) ganador(a)");
                        for(Affiliated participant : this.participants) {
                            participant.addSpecialTickets(-1);
                            System.out.println(participant.getFullName() + " has " + participant.getSpecialTickets() + " special tickets");
                        }
                        setupLabels();
                        return;
                    }
                    iterateNextLabel();
                });
                currentLabel.getStyleClass().add("jfx-lbl-raffle-selected");
                pause.play();
            } else {
                iterateNextLabel();
            }
        } else {
            iterateNextContainer();
        }
    }
    
    public Affiliated getWinner(String folio) {
        if(folio != null) {
            for(Affiliated participant : this.participants) {
                if(participant.getFolio().equals(folio)) {
                    return participant;
                }
            }
        }
        return null;
    }

    private String getRandomStyleClass() {
        Random numGenerator = new Random();
        return this.styleClasses[numGenerator.nextInt(4)];
    }
    
    public void clearContainers() {
        for(HBox container : this.containers) {
            container.getChildren().clear();
        }
    }
}
