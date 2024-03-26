/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
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
    private MFXFilterComboBox selectAccountComboBox;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        ArrayList<String> elementsAL = new ArrayList<String>();
        elementsAL.add("Element A");
        elementsAL.add("Element B");
        elementsAL.add("Element C");

        ObservableList<String> elements = FXCollections.observableArrayList(elementsAL);
        selectAccountComboBox.setItems(elements);
    }

    @Override
    public void initialize() {

    }

    public void selectElement() {
        System.out.println(selectAccountComboBox.selectionProperty().getName() +  " was selected");
    }

}
