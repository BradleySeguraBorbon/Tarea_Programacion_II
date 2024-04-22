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
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Formato;
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
        this.txtAddAccountType.delegateSetTextFormatter(Formato.getInstance().letrasFormat(20));  
    }

    @Override
    public void initialize() {
        //Se agregan todas las cuentas al arrayList desde el appContext
        if(AppContext.getInstance().get("availableAccounts") != null) {
            this.availableAccounts = (ArrayList<String>)AppContext.getInstance().get("availableAccounts");
            lstvAccountTypes.setItems(FXCollections.observableArrayList(this.availableAccounts));
        }
    }
   
    //Con este método es que se agregan cuentas para todos los afiliados
    public void addAccount() {
        try {
            //Se obtiene el nuevo nombre desde el textfield
            String newAccountType = txtAddAccountType.getText();
            if(!newAccountType.isBlank()) {
                //Se limpia cualquier selección que pueda tener anteriormente el arrayList
                this.lstvAccountTypes.getSelectionModel().clearSelection();
                //Se crea un observableList para guardar los nombres de las cuentas que ya existen y se agrega una con el nuevo nombre.
                ObservableList<String> modifiedList = lstvAccountTypes.getItems();
                modifiedList.add(newAccountType);
                //Se vuelve a cargar en la ListView para que aparezca la nueva cuenta 
                lstvAccountTypes.setItems(modifiedList);
                //Se borra el nombre del textField
                txtAddAccountType.setText("");
                //Se le agrega al arrayList principal
                this.availableAccounts.add(newAccountType);
                //Se actualiza el arrayList principal en el AppContext
                AppContext.getInstance().set("availableAccounts", this.availableAccounts);
            }
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }  
    
    //Este método espara eliminar una cuenta existente
    public void deleteSelectedAccountType() {
        //Se remueve del arrayList principal, se actualiza el ListView para que ya no aparezca más y se limpia la selección del ListView
        this.availableAccounts.remove(this.lstvAccountTypes.getSelectionModel().getSelectedValue());
        this.lstvAccountTypes.setItems(FXCollections.observableArrayList(this.availableAccounts));
        this.lstvAccountTypes.getSelectionModel().clearSelection();
    }
}
