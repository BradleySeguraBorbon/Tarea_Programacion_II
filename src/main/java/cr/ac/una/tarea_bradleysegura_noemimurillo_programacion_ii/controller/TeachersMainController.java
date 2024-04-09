/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.App;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
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
    private Label lblTeachersWelcome;
    @FXML
    private ImageView imvTeachersWelcome;
    @FXML
    private MFXButton btnOpenAccountManagement;
    @FXML
    private MFXButton btnOpenCooperativeManagement;
    @FXML
    private MFXButton btnExit;
    @FXML
    private Label lblCooperativeName;
    @FXML
    private ImageView imvCooperativeLogo;
    @FXML
    private HBox mainHBox;
    @FXML
    private VBox mainVBox;

    DataManager dataBank;
    private static String cooperativeName;

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
                //lblCooperativeName.setText((String) AppContext.getInstance().get("cooperativeName"));
                //imvCooperativeLogo.setImage(new Image(App.class.getResource("resources/" + AppContext.getInstance().get("cooperativeIcon")).toString()));
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

    public void openAccountTypeManagementView() {
        FlowController.getInstance().goView("AccountTypeManagementView");
    }

    public void openCooperativeManagementView() {
        FlowController.getInstance().goView("CooperativeManagementView");
    }

    public void setCooperativeLogo(Image imgCooperativeLogo) {
        this.imvCooperativeLogo.setImage(imgCooperativeLogo);
        System.out.println("Coop Logo Modified");
    }

    public void setCooperativeName(String newCooperativeName) {
        lblCooperativeName.setText(cooperativeName);
        System.out.println("Coop Name Modified");
    }

    public void exit() throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        String relativePath = "src/main/java/cr/ac/una/tarea_bradleysegura_noemimurillo_programacion_ii/service/SystemData.json";
        String absolutePath = Paths.get(currentDirectory, relativePath).toString();
        dataBank.save(absolutePath);
        if (mainHBox.getChildren().contains(this.mainVBox)) {
            ((Stage) (this.btnExit.getScene().getWindow())).close();
        } else {
            FlowController.getInstance().goMain();
        }
    }

}
