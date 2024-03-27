/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Afiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
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
public class AfiliatedSelectionController implements Initializable {

    @FXML
    private MFXFilterComboBox afiliatedSelectionComboBox;
    @FXML
    private Label afiliatedNameLabel;
    @FXML
    private Label afiliatedFolioLabel;
    
    private ArrayList<String> afiliatedNames;
    private Afiliated selectedAfiliated;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        afiliatedNames = new ArrayList();
        for(Afiliated afiliated : (ArrayList<Afiliated>)AppContext.getInstance().get("afiliated")) {
            afiliatedNames.add(afiliated.getFullName());
        }
        afiliatedSelectionComboBox.setItems(FXCollections.observableArrayList(afiliatedNames));      
    }    
    
    public void setSelectedAfiliated() {
        String selectedAfiliatedName = afiliatedSelectionComboBox.getSelectedText();
        
        for(Afiliated afiliated : (ArrayList<Afiliated>)AppContext.getInstance().get("afilated")) {
            if(afiliated.getName().equals(selectedAfiliatedName)) {
                this.selectedAfiliated = afiliated;
                break;
            }
        }
        if(selectedAfiliated != null) {
            displayAfiliatedInfo();
        }
    }
    
    public void displayAfiliatedInfo() {
        this.afiliatedNameLabel.setText(this.selectedAfiliated.getFullName());
        this.afiliatedFolioLabel.setText(this.selectedAfiliated.getFolio());  
    }
    
    public void close() {
        if(selectedAfiliated != null) {
            AppContext.getInstance().set("selectedAfiliated", selectedAfiliated);
        }
    }
    
}
