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
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;

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
    private String[] styleClasses = {"jfx-lbl-raffle-heart", "jfx-lbl-raffle-square", "jfx.lbl-raffle-circle", "jfx-lbl-raffle-diamond"};
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //PRUEBA
        ArrayList<Affiliated> participantsArray = (ArrayList<Affiliated>)AppContext.getInstance().get("affiliated");
        for(Affiliated affiliated: participantsArray) {
            affiliated.setSpecialTickets(1);
        }
        this.participants = new ArrayList();
        this.containers = new ArrayList();
        
        if (AppContext.getInstance().get("affiliated") != null) {
            for (Affiliated affiliated : (ArrayList<Affiliated>) AppContext.getInstance().get("affiliated")) {
                if (affiliated.getSpecialTickets() > 0) {
                    this.participants.add(affiliated);
                }
            }
        }
        HBox[] containersArray = {this.hboxRow1, this.hboxRow2, this.hboxRow3, this.hboxRow4, this.hboxRow5};
        for(HBox container : containersArray) {
            this.containers.add(container);
        }
        setupLabels();
    }

    @Override
    public void initialize() {

    }

    public void setupLabels() {
        Random numGenerator = new Random();
        Integer participantsNum = 0, currentContainer = 0;
        for(Affiliated participant : this.participants) {
            if(participantsNum >= 26) {
                new Mensaje().show(Alert.AlertType.INFORMATION, "CUPOS LLENOS", "Se ha alcanzado el número máximo de participantes (26)");
                break;
            }
            Label lblParticipant = new Label(participant.getName() + participant.getFirstLastName());
            lblParticipant.getStyleClass().add(getRandomStyleClass());
            if(participantsNum % 8 == 1) {
                currentContainer++;
                containers.get(currentContainer).getChildren().add(lblParticipant);
                participantsNum++; currentContainer++;
                continue;
            }
            containers.get(currentContainer).getChildren().add(lblParticipant);
            participantsNum++;
        }
    }

    public void raffle() {
            Random numGenerator = new Random();
            Integer setpsNum = numGenerator.nextInt(this.participants.size() * 2);
            for(HBox container : this.containers) {
                for(Node lblParticipant : (ObservableList<Node>)container.getChildren()) {
                    if(lblParticipant instanceof Label) {
                        lblParticipant.getStyleClass().add("jfx-lbl-raffle-selected");
                        wait(500);
                        lblParticipant.getStyleClass().remove("jfx-lbl-raffle-selected");
                    }
                }
            }
    }
    
    public void wait(int miliseconds) {
        try {
            Thread.sleep(miliseconds);
        }
        catch(InterruptedException exception) {
            exception.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
    
    private String getRandomStyleClass() {
        Random numGenerator = new Random();
        return this.styleClasses[numGenerator.nextInt(4)];
    }
}
