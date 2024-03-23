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
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

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
    @FXML
    private HBox mainHBox;
    @FXML
    private VBox teachersMainMenuVBox;
    

    DataManager dataBank;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        String currentDirectory = System.getProperty("user.dir");
        String relativePath = "src/main/java/cr/ac/una/tarea_bradleysegura_noemimurillo_programacion_ii/service/SystemData.ser";
        String absolutePath = Paths.get(currentDirectory, relativePath).toString();

        try {
            if (new File(absolutePath).isFile()) {
                dataBank = DataManager.load(absolutePath);
                System.out.println(dataBank.getAvailableAccounts());
                cooperativeNameLabel.setText(dataBank.getCooperativeName());
                cooperativeLogoImageView.setImage(new Image("file:src/main/resources/cr/ac/una/cooperativeNameLabel/resources/" + dataBank.getCooperativeIcon()));
                System.out.println("SAVED DATAMANAGER WAS LOADED");
            } else {
                dataBank = new DataManager();
                if(cooperativeLogoImageView.getImage() != null) {
                    dataBank.setCooperativeIcon(cooperativeLogoImageView.getImage().toString());
                    dataBank.setCooperativeName(cooperativeNameLabel.getText());
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize() {

    }

    public void openAccountTypeManagementView() {
        FlowController.getInstance().goView("AccountTypeManagementView");
        AccountTypeManagementController accountTypeManagementController = (AccountTypeManagementController) FlowController.getInstance().getController("AccountTypeManagementView");
        accountTypeManagementController.setDataManager(this.dataBank);
    }

    public void openCooperativeManagementView() {
        FlowController.getInstance().goView("CooperativeManagementView");
    }

    public void exit() {
        if(mainHBox.getChildren().contains(this.teachersMainMenuVBox)) {
            ((Stage)(this.exitButton.getScene().getWindow())).close();
        }
        else {
            FlowController.getInstance().goMain();
        }
    }

}
