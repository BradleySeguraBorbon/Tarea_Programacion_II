/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.DataManager;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
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
public class AffiliatedMainController extends Controller implements Initializable {

    @FXML
    private HBox mainHBox;
    @FXML
    private VBox mainVBox;
    @FXML
    private MFXButton btnOpenRegisterView;
    @FXML
    private MFXButton btnOpenAccountStatement;
    @FXML
    private MFXButton btnOpenDepositBox;
    @FXML
    private MFXButton exitButton;
    @FXML
    private ImageView imvWelcomeLeft;
    @FXML
    private ImageView imvWelcomeRight;
    @FXML
    private Label lblCooperativeName;
    @FXML
    private ImageView imvCooperativeLogo;

    private DataManager dataBank;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

    public void openRegisterView() {
        FlowController.getInstance().goView("AffiliatedRegisterView");
    }

    public void openAccountStatement() {

    }

    public void openDepositBox() {
        FlowController.getInstance().goViewInWindowModal("BoxDepositView", getStage(), true);
    }

    public void exit() throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        String relativePath = "src/main/java/cr/ac/una/tarea_bradleysegura_noemimurillo_programacion_ii/service/SystemData.json";
        String absolutePath = Paths.get(currentDirectory, relativePath).toString();
        this.dataBank.save(absolutePath);
        if (this.mainHBox.getChildren().contains(this.mainVBox)) {
            ((Stage) (this.exitButton.getScene().getWindow())).close();
        } else {
            FlowController.getInstance().goMain();
        }
    }
}
    
