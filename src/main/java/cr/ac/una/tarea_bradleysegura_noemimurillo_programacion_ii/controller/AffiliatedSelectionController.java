/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Account;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class AffiliatedSelectionController extends Controller implements Initializable {

    @FXML
    private MFXFilterComboBox afiliatedSelectionComboBox;
    @FXML
    private Label afiliatedNameLabel;
    @FXML
    private Label afiliatedFolioLabel;
    @FXML
    private MFXButton continueButton;
    
    private ArrayList<String> afiliatedNames;
    private Affiliated selectedAffiliated;
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*//PRUEBA
        ArrayList<Affiliated> affiliated = new ArrayList<>();
        Affiliated affiliated1 = new Affiliated("Bradley", "Segura", "Borbon", 18, Affiliated.Sexo.MASCULINO, "Coope");
        affiliated1.addAccount(new Account("Ahorro Navideño"));
        affiliated.add(affiliated1);
        System.out.println("FOLIO DE BRADLEY: " + affiliated1.getFolio());
        AppContext.getInstance().set("afiliated", affiliated);
        //FIN PRUEBA    */
        
        //Se agregan todos loa afiliados desde el appContext a un arrayList local
        afiliatedNames = new ArrayList();
        for(Affiliated afiliated : (ArrayList<Affiliated>)AppContext.getInstance().get("affiliated")) {
            afiliatedNames.add(afiliated.getFullName());
        }
        
        //Se cargan todos los afiliados al FilterComboBox
        afiliatedSelectionComboBox.setItems(FXCollections.observableArrayList(afiliatedNames));      
    }    
    
    @Override
    public void initialize() {
        
    }
 
    //Este afiliado es para cargar el afiliado seleccionado a la variable local que guarda el afiliado seleccionado
    public void setSelectedAffiliated() {
        //Se carga el string con el nombre del afiliado seleccionado
        String selectedAffiliatedName = (String)afiliatedSelectionComboBox.getSelectionModel().getSelectedItem();   
        //Se busca el afiliado
        for(Affiliated afiliated : (ArrayList<Affiliated>)AppContext.getInstance().get("affiliated")) {
            //Se valida si el afiliado actual del for coincide con el nombre del afiliado seleccionado desde el filterComboBox
            if(afiliated.getFullName().equals(selectedAffiliatedName)) {
                this.selectedAffiliated = afiliated;
                break;
            }
        }
        //Si había un afiliado seleccionado entonces se despliega la información
        if(selectedAffiliated != null) {
            displayAffiliatedInfo();
        }
    }
 
    //Este método despliega la información del afiliado en las labels
    public void displayAffiliatedInfo() {
        this.afiliatedNameLabel.setText(this.selectedAffiliated.getFullName());
        this.afiliatedFolioLabel.setText(this.selectedAffiliated.getFolio());  
    }
    
    //Se agrega el afiliado selecionado al appContext y se cierra la ventana
    public void close() {
        if(selectedAffiliated != null) {
            AppContext.getInstance().set("selectedAffiliated", selectedAffiliated);
            getStage().close();
        }
    }
}
