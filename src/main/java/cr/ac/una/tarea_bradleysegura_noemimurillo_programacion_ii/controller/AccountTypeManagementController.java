/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.App;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
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

    private ArrayList<String> availableAccounts;

    @FXML
    private MFXTextField txtAddAccountType;
    @FXML
    private MFXButton btnAddAccountType;
    @FXML
    private MFXListView<String> lstvAccountTypes;
    @FXML
    private MFXButton btnRemoveAccountType;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.availableAccounts = new ArrayList<String>();
        // TODO
        
    }

    @Override
    public void initialize() {
        if(AppContext.getInstance().get("availableAccounts") != null) {
            this.availableAccounts = (ArrayList<String>)AppContext.getInstance().get("availableAccounts");
            lstvAccountTypes.setItems(FXCollections.observableArrayList(this.availableAccounts));
        }
    }
   
    public void addAccount() {
        try {
            String newAccountType = txtAddAccountType.getText();
            if(!newAccountType.isBlank()) {
                this.lstvAccountTypes.getSelectionModel().clearSelection();
                ObservableList<String> modifiedList = lstvAccountTypes.getItems();
                modifiedList.add(newAccountType);
                lstvAccountTypes.setItems(modifiedList);
                txtAddAccountType.setText("");
                this.availableAccounts.add(newAccountType);
                AppContext.getInstance().set("availableAccounts", this.availableAccounts);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }  
    
    public void deleteSelectedAccountType() {
        this.availableAccounts.remove(this.lstvAccountTypes.getSelectionModel().getSelectedValue());
        this.lstvAccountTypes.setItems(FXCollections.observableArrayList(this.availableAccounts));
        this.lstvAccountTypes.getSelectionModel().clearSelection();
    }
}
