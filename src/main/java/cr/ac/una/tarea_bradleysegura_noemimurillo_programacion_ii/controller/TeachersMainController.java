/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.DataManager;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class TeachersMainController extends Controller implements Initializable {

    @FXML
    private Label teachersWelcomeLabel;
    @FXML
    private ImageView teachersWelcomeImageView;
    @FXML
    private MFXButton accountTypeManagementButton;
    @FXML
    private MFXButton cooperativeManagementButton;
    @FXML
    private MFXButton exitButton;
    @FXML
    private Label cooperativeNameLabel;
     @FXML
    private ImageView cooperativeLogoImageView;
     
     DataManager dataBank;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String fileDirection = "src/main/java/cr/ac/una/tarea_bradleysegura_noemimurillo_programacion_ii/services/DataManager.json";
        try {
            if(new File(fileDirection).isFile()) {
                dataBank = DataManager.load(fileDirection);
                cooperativeNameLabel.setText(dataBank.getCooperativeName());
                cooperativeLogoImageView.setImage(dataBank.getCooperativeIcon());
            }
            else
                dataBank = new DataManager();
        }
        catch(Exception e) {
            e.printStackTrace();
        }
    }    

    @Override
    public void initialize() {
        
    }
    
    public void openAccountTypeManagementView() {
        FlowController.getInstance().goViewInWindow("AccountTypeManagementView");
        AccountTypeManagementController accountTypeManagementController = (AccountTypeManagementController)FlowController.getInstance().getController("AccountTypeManagementView");
        accountTypeManagementController.setDataManager(this.dataBank);
    }
    
    public void openCooperativeManagementView() {
        FlowController.getInstance().goViewInWindow("CooperativeManagementView");
    }
    
    public void exit() {
        System.exit(0);
    }
    
    
    
}
