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
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.application.Platform;
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
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javax.imageio.ImageIO;

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
    private MFXButton btnExit;
    @FXML
    private ImageView imvIcon0;
    @FXML
    private ImageView imvIcon1;
    @FXML
    private ImageView imvIcon2;
    @FXML
    private ImageView imvIcon3;

    private ArrayList<ImageView> iconViews;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Se establece la dirección del archivo JSON
        String absolutePath = System.getProperty("user.dir") + "/SystemData.json";

        //Se establecen los valores por defecto
        try {
            AppContext.getInstance().set("cooperativeName", "Cooperativa");    //corregir
            AppContext.getInstance().set("cooperativeLogo", ImageConverter.toBase64(ImageIO.read(new File(System.getProperty("user.dir") + "/defaultCooperativeInfo/companyDefaultLogo.png")), "PNG"));
            AppContext.getInstance().set("inMainMenu", true);
            FlowController.getInstance().goView("TopMainView", "Top", null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Se establece el listener para decoraciones aleatorias
        this.vboxCenter.getChildren().addListener((ListChangeListener<Node>) event -> {
            try {
                setDecoration();
            } catch (Exception e) {
                e.printStackTrace();
            }
        });

        //Se inicializan los nodos de la vista
        this.iconViews = new ArrayList();
        ImageView[] iconsArray = {imvIcon0, imvIcon1, imvIcon2, imvIcon3};
        for (ImageView imageIcon : iconsArray) {
            iconViews.add(imageIcon);
        }

        //Se define el cargado de información desde el archivo JSON (en caso de existir)
        try {
            if (new File(absolutePath).isFile()) {
                dataBank = DataManager.load(absolutePath);
                dataBank.unpackData();
                FlowController.getInstance().goView("TopMainView", "Top", null);
            } else {
                dataBank = new DataManager();
                AppContext.getInstance().set("affiliated", new ArrayList<Affiliated>());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void initialize() {

    }

    //Establecimiento de decoraciones (gifs) aleatorias
    @FXML
    public void setDecoration() {
        for (ImageView imvIcon : this.iconViews) {
            imvIcon.getStyleClass().clear();
        }
        Random random = new Random();
        Integer imvFirstIndex = random.nextInt(4), imvSecondIndex;
        do {
            imvSecondIndex = random.nextInt(4);
        } while (Objects.equals(imvSecondIndex, imvFirstIndex));

        Integer iconFirstIndex = random.nextInt(12), iconSecondIndex;
        do {
            iconSecondIndex = random.nextInt(12);
        } while (Objects.equals(iconSecondIndex, iconFirstIndex));

        this.iconViews.get(imvFirstIndex).getStyleClass().add("randomImage" + Integer.toString(iconFirstIndex));
        this.iconViews.get(imvSecondIndex).getStyleClass().add("randomImage" + Integer.toString(iconSecondIndex));
    }

    //Método de regreso al menú principal 
    @FXML
    public void exit() throws IOException {
        String absolutePath = System.getProperty("user.dir") + "/SystemData.json";
        this.dataBank.save(absolutePath);

        if ((Boolean) AppContext.getInstance().get("inMainMenu")) {
            ((Stage) (this.btnExit.getScene().getWindow())).close();
        } else {
            FlowController.getInstance().goView("MainMenuView");
            AppContext.getInstance().set("inMainMenu", true);
        }
    }

}
