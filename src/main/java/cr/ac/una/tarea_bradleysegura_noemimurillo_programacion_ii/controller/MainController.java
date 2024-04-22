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
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.ListChangeListener;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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
    private VBox vboxCenter;
    @FXML
    private Label lblCooperativeName;
    @FXML
    private ImageView imvCooperativeLogo;
    @FXML
    private MFXButton btnExit;
    @FXML
    private ImageView imvIcon0;
    @FXML
    private ImageView imvIcon1;
    @FXML
    private ImageView imvIcon2;
    @FXML
    private ImageView imvIcon3;
    @FXML
    private ImageView imvIcon4;

    private ArrayList<ImageView> iconViews;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        String absolutePath = System.getProperty("user.dir") + "/SystemData.json";
//        String currentDirectory = System.getProperty("user.dir");
//        String relativePath = "src/main/resources/cr/ac/una/tarea_bradleysegura_noemimurillo_programacion_ii/resources/SystemData.json";
//        String absolutePath = Paths.get(currentDirectory, relativePath).toString();
        /*String absolutePath =getClass().getResource("../resources").getPath();
        absolutePath += "/SystemData.json";
        System.out.println(absolutePath);*/

        AppContext.getInstance().set("CooperativeName", this.lblCooperativeName.getText());
        AppContext.getInstance().set("CooperativeLogo", ImageConverter.toBase64(SwingFXUtils.fromFXImage(this.imvCooperativeLogo.getImage(), null), "PNG"));
        AppContext.getInstance().set("inMainMenu", true);
        updateCooperativeInfo();

        this.vboxCenter.getChildren().addListener((ListChangeListener<Node>) event -> {
            try {
                setDecoration();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        this.iconViews = new ArrayList();
        ImageView[] iconsArray = {imvIcon0, imvIcon1, imvIcon2, imvIcon3, imvIcon4};
        for (ImageView imageIcon : iconsArray) {
            iconViews.add(imageIcon);
        }

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
        //updateCooperativeInfo();
    }

    //Universal Methods
    public void updateCooperativeInfo() {
        String cooperativeLogo = (String) AppContext.getInstance().get("CooperativeLogo");
        String cooperativeName = (String) AppContext.getInstance().get("CooperativeName");

        System.out.println("PPP -- COOP LOGO: " + cooperativeLogo);
        System.out.println("PPP -- COOP NAME: " + cooperativeName);

        if (cooperativeLogo != null && cooperativeName != null) {
            this.imvCooperativeLogo.setImage(ImageConverter.fromBase64(cooperativeLogo));
            this.lblCooperativeName.setText(cooperativeName);
            System.out.println("Coop's Logo and Name Modified" + cooperativeName);
        }
    }

    public void setDecoration() {
        for (ImageView imvIcon : this.iconViews) {
            imvIcon.getStyleClass().clear();
        }
        Random random = new Random();
        Integer imvFirstIndex = random.nextInt(5), imvSecondIndex;
        do {
            imvSecondIndex = random.nextInt(5);
        } while (Objects.equals(imvSecondIndex, imvFirstIndex));

        Integer iconFirstIndex = random.nextInt(12), iconSecondIndex;
        do {
            iconSecondIndex = random.nextInt(12);
        } while (Objects.equals(iconSecondIndex, iconFirstIndex));

        this.iconViews.get(imvFirstIndex).getStyleClass().add("randomImage" + Integer.toString(iconFirstIndex));
        this.iconViews.get(imvSecondIndex).getStyleClass().add("randomImage" + Integer.toString(iconSecondIndex));
    }

    public void exit() throws IOException {
        String absolutePath = System.getProperty("user.dir") + "/SystemData.json";
//        String currentDirectory = System.getProperty("user.dir");
//        String relativePath = "src/main/resources/cr/ac/una/tarea_bradleysegura_noemimurillo_programacion_ii/resources/SystemData.json";
//        String absolutePath = Paths.get(currentDirectory, relativePath).toString();
        /*String absolutePath =getClass().getResource("../resources").getPath();
        absolutePath += "/SystemData.json"; */
        this.dataBank.save(absolutePath);

        if ((Boolean) AppContext.getInstance().get("inMainMenu")) {
            ((Stage) (this.btnExit.getScene().getWindow())).close();
        } else {
            FlowController.getInstance().goView("MainMenuView");
            AppContext.getInstance().set("inMainMenu", true);
        }
    }

}
