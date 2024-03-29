/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXTextField;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author Fiorella
 */
public class CardPrintController extends Controller implements Initializable {

    @FXML
    private Button btnInputFolio;
    @FXML
    private Button btnPrint;
    @FXML
    private MFXTextField txtInFolio;
    @FXML
    private MFXTextField txtNameUser;
    
    private Affiliated selectedAffiliated;
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
    
    public void browseUser(){
        Mensaje msj = new Mensaje();
        txtNameUser.clear();
        String selectedAffiliatedFolio = txtInFolio.getText();
        
        for(Affiliated afiliated : (ArrayList<Affiliated>)AppContext.getInstance().get("afilated")) {
            if(afiliated.getFolio().equals(selectedAffiliatedFolio)) {
                this.selectedAffiliated = afiliated;
                break;
            }
        }
        if(selectedAffiliated != null) {
            displayAffiliatedInfo();
        }else{
             msj.show(ERROR, "Error folio", "No se ha encontrado ningún usuario relacionado con este folio." );
        }
    }
    
    @FXML
    public void displayAffiliatedInfo() {
        this.txtNameUser.setText(this.selectedAffiliated.getFullName());
    }
    
    @FXML
    public void printPDF(){
        
    }
}
