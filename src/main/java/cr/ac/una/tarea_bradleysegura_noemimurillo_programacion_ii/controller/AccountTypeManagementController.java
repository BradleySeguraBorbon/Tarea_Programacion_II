/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.App;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.DataManager;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import java.io.*;
import java.nio.file.Paths;
import javafx.scene.image.Image;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class AccountTypeManagementController extends Controller implements Initializable {

    private DataManager dataBank;
    private ArrayList<String> availableAccounts;

    @FXML
    private MFXTextField addAccountTypeTextField;
    @FXML
    private MFXButton addAccountTypeButton;
    @FXML
    private MFXListView<String> accountTypesListView;
    @FXML
    private MFXButton deleteAccountTypeButton;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
    }

    @Override
    public void initialize() {
  
    }

    public void setDataManager(DataManager dataBank) {
        this.dataBank = dataBank;
        setupListView();
    }
    
    private void setupListView() {
        accountTypesListView.setItems(FXCollections.observableArrayList(dataBank.getAvailableAccounts()));
        System.out.println(dataBank.getAvailableAccounts());
    }
    
    public void addAccount() {
        try {
            String newAccountType = addAccountTypeTextField.getText();
            if(!newAccountType.isEmpty()) {
                ObservableList<String> modifiedList = accountTypesListView.getItems();
                modifiedList.add(newAccountType);
                accountTypesListView.setItems(modifiedList);
                addAccountTypeTextField.setText("");
                dataBank.addAvailableAccount(newAccountType);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }  
}
