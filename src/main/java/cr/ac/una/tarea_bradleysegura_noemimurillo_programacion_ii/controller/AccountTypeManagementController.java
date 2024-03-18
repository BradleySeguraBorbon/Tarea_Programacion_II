/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.DataManager;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class AccountTypeManagementController extends Controller implements Initializable {

    private DataManager dataBank;

    @FXML
    private MFXTextField addAccountTypeTextField;
    @FXML
    private MFXButton addAccountTypeButton;
    @FXML
    private MFXListView<String> accountTypesListView;
    @FXML
    private MFXButton exitButton;
    @FXML
    private Label cooperativeNameLabel;
    @FXML
    private ImageView cooperativeLogoImageView;
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
        cooperativeNameLabel.setText(dataBank.getCooperativeName());
        cooperativeLogoImageView.setImage(dataBank.getCooperativeIcon());
        accountTypesListView.setItems(FXCollections.observableArrayList(dataBank.getAvailableAccounts())); 
    }
    
    public void addAccount() {
        try {
            String newAccountType = addAccountTypeTextField.getText();
            if(!newAccountType.isEmpty()) {
                accountTypesListView.getItems().add(newAccountType);
                addAccountTypeTextField.setText("");
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }
    
    public void exit() throws IOException {
        dataBank.save("src/main/java/cr/ac/una/tarea_bradleysegura_noemimurillo_programacion_ii/services/DataManager.json");
        FlowController.getInstance().goViewInWindow("TeachersMainView");
        System.out.println("COMMAND EXECUTED");
        
    }
   
}
