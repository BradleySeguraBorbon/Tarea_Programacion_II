/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Afiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.BoxDeposit;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class MoneyManagementController extends Controller implements Initializable {
    
    @FXML
    private MFXFilterComboBox selectAfiliatedDepositBox;
    @FXML
    private MFXFilterComboBox selectAccountDepositBox;
    @FXML
    private MFXTextField depositAmountTextField;
    @FXML
    private MFXButton depositButton;
    @FXML
    private MFXTableView depositBoxTableView;
    @FXML
    private MFXButton depositValidationButton;

    @FXML
    private MFXFilterComboBox selectAccountComboBox;
    
    private ArrayList<String> afiliatedNames;
    private ArrayList<BoxDeposit> boxDeposits;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.afiliatedNames = new ArrayList();
        for(Afiliated afiliated : (ArrayList<Afiliated>)AppContext.getInstance().get("afiliated")) {
            afiliatedNames.add(afiliated.getFullName());
        }
        this.selectAfiliatedDepositBox.setItems(FXCollections.observableArrayList(afiliatedNames));
        this.boxDeposits = (ArrayList<BoxDeposit>) AppContext.getInstance().get("boxDeposits");
    }

    @Override
    public void initialize() {

    }
    
    public void setupDepositBoxTableView() {
        
    }

    public void selectElement() {
        System.out.println(selectAccountComboBox.selectionProperty().getName() +  " was selected");
    }

}
