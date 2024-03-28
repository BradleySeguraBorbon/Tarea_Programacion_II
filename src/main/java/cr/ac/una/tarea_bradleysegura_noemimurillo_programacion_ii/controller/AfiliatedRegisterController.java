/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Afiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Afiliated.Sexo;
import static cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Afiliated.Sexo.FEMENINO;
import static cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Afiliated.Sexo.MASCULINO;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafx.scene.control.Alert.AlertType.ERROR;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.scene.control.ToggleGroup;

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
    private RadioButton btnFem;
    @FXML
    private RadioButton bntMas;
    @FXML
    private ToggleGroup SexGroup;
    
    
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
    
    public void addNewUser(){
        Mensaje msj = new Mensaje();
        if (txtName == null){
            msj.show(ERROR, "Nombre vacío", "La casilla de nombre del nuevo usuario está vacía");
        }
        if(txtSurname == null){
            msj.show(ERROR, "Primer apellido vacío", "La casilla del primer apellido del nuevo usuario está vacía");
        }
        if(txtSecondSurname == null){
            msj.show(ERROR, "Segundo apellido vacío", "La casilla del segundo apellido del nuevo usuario está vacía");
        }
        if (getNewSex() == null){
            msj.show(ERROR, "Sexo vacío", "La casilla de sexo del nuevo usuario está vacía"); 
        }else{
            Afiliated NewUser = new Afiliated(txtName.getText(), txtSurname.getText(), txtSecondSurname.getText(), Integer.valueOf(txtAge.getText()), getNewSex(), (String)AppContext.getInstance().get("cooperativeName"));
        }
    }
    
    public Sexo getNewSex(){
    Toggle seleccionado = SexGroup.getSelectedToggle();
    if(seleccionado != null){
        if(seleccionado.getUserData().equals("Femenino")){
            return FEMENINO;
        } else{
            return MASCULINO;
        }
    }else
        return null;
    }
    
}
