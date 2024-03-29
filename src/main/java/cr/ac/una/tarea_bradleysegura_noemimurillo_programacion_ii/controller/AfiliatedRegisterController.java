/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Afiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ArrayList;
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
    private MFXButton btnAddUser;
    @FXML
    private MFXTextField txtName;
    @FXML
    private MFXTextField txtSurname;
    @FXML
    private MFXTextField txtSecondSurname;
    @FXML
    private MFXTextField txtAge;
    @FXML
    private ToggleGroup SexGroup;
    @FXML
    private MFXButton btnDeleteUser;
    @FXML
    private MFXButton btnSaveChanges;
    /*@FXML
    private MFXTableView tbvUsersList;*/
    ArrayList<Afiliated> newAffiliates = new ArrayList<>();


    /**
     * Initializablelizes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        ArrayList<Afiliated> Afiliated2 = new ArrayList<>();
        AppContext.getInstance().set("afiliated", Afiliated2);
        this.newAffiliates = (ArrayList<Afiliated>) AppContext.getInstance().get("afiliated");

        // TODO
    }

    @Override
    public void initialize() {

    }

    public void addNewUser() {
        //Imagen
        
        //
        Mensaje msj = new Mensaje();
        if (txtName.getText().equals("")) {
            msj.show(ERROR, "Nombre vacío", "La casilla de nombre del nuevo usuario está vacía");
            return;
        }
        if (txtSurname.getText().equals("")) {
            msj.show(ERROR, "Primer apellido vacío", "La casilla del primer apellido del nuevo usuario está vacía");
            return;
        }
        if (txtSecondSurname.getText().equals("")) {
            msj.show(ERROR, "Segundo apellido vacío", "La casilla del segundo apellido del nuevo usuario está vacía");
            return;
        }
        if (getNewSex() == null) {
            msj.show(ERROR, "Sexo vacío", "La casilla de sexo del nuevo usuario está vacía");
            return;
        } 
        if(txtAge.getText().equals("")){
            msj.show(ERROR, "Edad vacía", "La casilla de edad del nuevo usuario está vacía");
            return;
        /*}else {
            String num = txtAge.getText();
            Integer edad = Integer.parseInt(num);
        }*/
        }
        
        newAffiliates.add(new Afiliated(txtName.getText(), txtSurname.getText(), txtSecondSurname.getText(), Integer.parseInt(txtAge.getText()), getNewSex(), "PRUEBA"));

        System.out.println(newAffiliates.toString());
    }

    public String getNewSex(){
    Toggle seleccionado = SexGroup.getSelectedToggle();
    if (seleccionado != null) {
        String valor = ((RadioButton) seleccionado).getText();
        if (valor.equals("Masculino")) {
            return "MASCULINO";
        } else if (valor.equals("Femenino")) {
            return "FEMENINO";
        }
    }
    return null;
    }

    public void modifyUser() {
        btnDeleteUser.setOpacity(1);
        btnSaveChanges.setOpacity(1);
    }
}
