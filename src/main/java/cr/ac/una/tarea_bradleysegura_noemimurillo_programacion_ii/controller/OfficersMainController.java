/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.DataManager;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class OfficersMainController extends Controller implements Initializable {
    
    @FXML
    private HBox mainHBox;
    @FXML
    private VBox officersMainVBox;
    @FXML
    private Label cooperativeNameLabel;
    @FXML
    private ImageView cooperativeLogoImageView;
    @FXML
    private Button openAfiliatedRegisterButton;
    @FXML
    private Button openCardPrintButton;
    @FXML
    private Button openAccountOpeningButton;
    @FXML
    private Button openMoneyManagementButton;
    @FXML
    private Button exitButton;
    @FXML
    private ImageView mainIconImageView;
    @FXML
    private Label officersWelcomeLabel;
    
    private DataManager dataBank;
    

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String currentDirectory = System.getProperty("user.dir");
        String relativePath = "src/main/java/cr/ac/una/tarea_bradleysegura_noemimurillo_programacion_ii/service/SystemData.json";
        String absolutePath = Paths.get(currentDirectory, relativePath).toString();

        try {
            if (new File(absolutePath).isFile()) {
                dataBank = DataManager.load(absolutePath);
                dataBank.unpackData();
                System.out.println(dataBank.getAvailableAccounts());
                //cooperativeNameLabel.setText((String) AppContext.getInstance().get("cooperativeName"));
                //cooperativeLogoImageView.setImage(new Image(App.class.getResource("resources/" + AppContext.getInstance().get("cooperativeIcon")).toString()));
                System.out.println("SAVED DATAMANAGER WAS LOADED");
            } else {
                dataBank = new DataManager();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    } 
    
    @Override
    public void initialize() {
        
    }
    
    public void openAfiliatedRegister() {
         FlowController.getInstance().goView("AfiliatedRegisterView");
    }
    
    public void openCardPrint() {
         FlowController.getInstance().goView("CardPrintView");
    }
    
    public void openAccountOpening() {
        FlowController.getInstance().goViewInWindowModal("AfiliatedSelectionView", null, false);
        FlowController.getInstance().goView("AccountOpeningView");
    }
    
    public void openMoneyManagement() {
        FlowController.getInstance().goView("MoneyManagementView");
    }
    
    public void exit() throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        String relativePath = "src/main/java/cr/ac/una/tarea_bradleysegura_noemimurillo_programacion_ii/service/SystemData.json";
        String absolutePath = Paths.get(currentDirectory, relativePath).toString();
        dataBank.save(absolutePath);
        if (mainHBox.getChildren().contains(this.officersMainVBox)) {
            ((Stage) (this.exitButton.getScene().getWindow())).close();
        } else {
            FlowController.getInstance().goMain();
        }
    }
}
