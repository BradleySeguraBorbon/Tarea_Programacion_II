/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Account;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class AffiliatedSelectionController extends Controller implements Initializable {

    @FXML
    private MFXFilterComboBox afiliatedSelectionComboBox;
    @FXML
    private Label afiliatedNameLabel;
    @FXML
    private Label afiliatedFolioLabel;
    @FXML
    private MFXButton continueButton;
    
    private ArrayList<String> afiliatedNames;
    private Affiliated selectedAffiliated;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //PRUEBA
        ArrayList<Affiliated> affiliated = new ArrayList<>();
        Affiliated affiliated1 = new Affiliated("Bradley", "Segura", "Borbon", 18, Affiliated.Sexo.MASCULINO, "Coope");
        affiliated1.addAccount(new Account("Ahorro Navideño"));
        affiliated.add(affiliated1);
        System.out.println("FOLIO DE BRADLEY: " + affiliated1.getFolio());
        AppContext.getInstance().set("afiliated", affiliated);
        //FIN PRUEBA
        
        afiliatedNames = new ArrayList();
        for(Affiliated afiliated : (ArrayList<Affiliated>)AppContext.getInstance().get("afiliated")) {
            afiliatedNames.add(afiliated.getFullName());
        }
        afiliatedSelectionComboBox.setItems(FXCollections.observableArrayList(afiliatedNames));      
    }    
    
    @Override
    public void initialize() {
        
    }
 
    public void setSelectedAffiliated() {
        System.out.println("SetSelectedAffiliated() was executed");
        String selectedAffiliatedName = (String)afiliatedSelectionComboBox.getSelectionModel().getSelectedItem();   
        for(Affiliated afiliated : (ArrayList<Affiliated>)AppContext.getInstance().get("afiliated")) {
            if(afiliated.getFullName().equals(selectedAffiliatedName)) {
                this.selectedAffiliated = afiliated;
                break;
            }
        }
        if(selectedAffiliated != null) {
            displayAffiliatedInfo();
        }
    }
 
    public void displayAffiliatedInfo() {
        this.afiliatedNameLabel.setText(this.selectedAffiliated.getFullName());
        this.afiliatedFolioLabel.setText(this.selectedAffiliated.getFolio());  
    }
    
    public void close() {
        if(selectedAffiliated != null) {
            AppContext.getInstance().set("selectedAffiliated", selectedAffiliated);
            getStage().close();
        }
    }
}
