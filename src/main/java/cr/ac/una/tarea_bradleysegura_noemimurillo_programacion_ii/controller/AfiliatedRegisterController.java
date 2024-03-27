/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Afiliated;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Fiorella
 */
public class AfiliatedRegisterController extends Controller implements Initializable {

    @FXML 
    private Button btnAddUser;
    @FXML
    private MFXTextField txtName;
    @FXML
    private MFXTextField txtSurname;
    @FXML      
    private MFXTextField txtSecondSurname;
    @FXML
    private MFXTextField txtAge;
    @FXML        
    private MFXTextField txtSex;
    
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
    
    public void addNewUser)(){
    Afiliated afiliado = new Afiliated()
    }
    
}
