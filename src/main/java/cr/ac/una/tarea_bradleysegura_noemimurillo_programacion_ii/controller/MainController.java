/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.App;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.DataManager;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.ImageConverter;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class MainController extends Controller implements Initializable {

    //Universal Attributes
    DataManager dataBank;
    @FXML
    private BorderPane root;
    @FXML
    private HBox mainHBox;
    @FXML
    private VBox mainVBox;
    @FXML
    private Label lblCooperativeName;
    @FXML
    private ImageView imvCooperativeLogo;
    @FXML
    private MFXButton btnExit;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {  
        String currentDirectory = System.getProperty("user.dir");
        String relativePath = "src/main/java/cr/ac/una/tarea_bradleysegura_noemimurillo_programacion_ii/service/SystemData.json";
        String absolutePath = Paths.get(currentDirectory, relativePath).toString();

        AppContext.getInstance().set("cooperativeName", this.lblCooperativeName.getText());
        AppContext.getInstance().set("cooperativeLogo", ImageConverter.toBase64(SwingFXUtils.fromFXImage(this.imvCooperativeLogo.getImage(), null), "PNG"));
        AppContext.getInstance().set("inMainMenu", true);
        
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
                AppContext.getInstance().set("affiliated", new ArrayList<Affiliated>());
                AppContext.getInstance().set("cooperativeName", this.lblCooperativeName.getText());
                AppContext.getInstance().set("cooperativeLogo", ImageConverter.toBase64(SwingFXUtils.fromFXImage(this.imvCooperativeLogo.getImage(), null), "PNG"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize() {
        updateCooperativeInfo();
    }

    //Universal Methods
    public void updateCooperativeInfo() {
        String cooperativeLogo = (String) AppContext.getInstance().get("CooperativeLogo");
        String cooperativeName = (String) AppContext.getInstance().get("CooperativeName");
        
        System.out.println("PPP -- COOP LOGO: " + cooperativeLogo);
        System.out.println("PPP -- COOP NAME: " + cooperativeName);
        
        if (cooperativeLogo != null && cooperativeName != null) {
            this.imvCooperativeLogo.setImage(ImageConverter.fromBase64(cooperativeLogo));
            lblCooperativeName.setText(cooperativeName);
            System.out.println("Coop's Logo and Name Modified");
        }
    }

    public void exit() throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        String relativePath = "src/main/java/cr/ac/una/tarea_bradleysegura_noemimurillo_programacion_ii/service/SystemData.json";
        String absolutePath = Paths.get(currentDirectory, relativePath).toString();
        this.dataBank.save(absolutePath);
        
        if ((Boolean)AppContext.getInstance().get("inMainMenu")) {
            ((Stage) (this.btnExit.getScene().getWindow())).close();
        } else {
            FlowController.getInstance().goView("MainMenuView");
            AppContext.getInstance().set("inMainMenu", true);
        }
    }

}
