/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXSpinner;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.models.spinner.IntegerSpinnerModel;
import io.github.palexdev.materialfx.controls.models.spinner.SpinnerModel;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import java.io.File;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class AffiliatedRegisterController extends Controller implements Initializable {

    @FXML
    private ImageView imvAffiliatedImage;
    @FXML
    private MFXTextField txtAffiliatedName;
    @FXML
    private MFXTextField txtAffiliatedFirstLastName;
    @FXML
    private MFXTextField txtAffiliatedSecondLastName;
    @FXML
    private MFXSpinner<Integer> spnrAffiliatedAge;
    @FXML
    private MFXRadioButton rBtnMasculino;
    @FXML
    private MFXRadioButton rBtnFemenino;
    @FXML
    private MFXButton btnRegister;
    @FXML
    private ToggleGroup SexSelection;

    private ArrayList<Affiliated> affiliated;
    private String affiliatedImageDir;

    /**
     * Initializablelizes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initialize();
    }

    @Override
    public void initialize() {
        if (AppContext.getInstance().get("affiliated") != null) {
            this.affiliated = (ArrayList<Affiliated>) AppContext.getInstance().get("affiliated");
            spnrAffiliatedAge.setSpinnerModel(new IntegerSpinnerModel(0));
        }
    }

    public void register() {
        Mensaje alerta = new Mensaje();
        String name = txtAffiliatedName.getText(),
                firstLastName = txtAffiliatedFirstLastName.getText(),
                secondLastName = txtAffiliatedSecondLastName.getText(),
                cooperativeName = (String) AppContext.getInstance().get("cooperativeName");
        Integer age = (int) spnrAffiliatedAge.getSpinnerModel().getValue();

        if (name.isBlank()) {
            alerta.show(Alert.AlertType.WARNING, "NOMBRE INCOMPLETO", "Debes ingresar tu nombre para continuar");
            System.out.println("ERROR");
            txtAffiliatedName.requestFocus();
        } else if (firstLastName.isBlank()) {
            alerta.show(Alert.AlertType.WARNING, "PRIMER APELLIDO INCOMPLETO", "Debes ingresar tu primer apellido para continuar");
            System.out.println("ERROR");
            txtAffiliatedFirstLastName.requestFocus();
        } else if (secondLastName.isBlank()) {
            alerta.show(Alert.AlertType.WARNING, "SEGUNDO APELLIDO INCOMPLETO", "Debes ingresar tu segundo apellido para continuar");
            System.out.println("ERROR");
            txtAffiliatedSecondLastName.requestFocus();
        } else if (spnrAffiliatedAge.getSpinnerModel().getValue() == null) {
            alerta.show(Alert.AlertType.WARNING, "EDAD NO INDICADA", "Debes indicar tu edad para continuar");
            System.out.println("ERROR");
            spnrAffiliatedAge.requestFocus();
        } else if (!rBtnMasculino.isSelected() && !rBtnFemenino.isSelected()) {
            alerta.show(Alert.AlertType.WARNING, "SEXO NO INDICADO", "Debes indicar tu sexo para continuar");
            System.out.println("ERROR");
        } else {
            Affiliated.Sexo sexo = (rBtnMasculino.isSelected()) ? Affiliated.Sexo.MASCULINO : Affiliated.Sexo.FEMENINO;
            this.affiliated.add(new Affiliated(name, firstLastName, secondLastName, age, sexo, affiliatedImageDir, cooperativeName));
            alerta.show(Alert.AlertType.INFORMATION, "REGISTRO EXITOSO", "'¡Bienvenid@ a " + cooperativeName + ", " + name + "!");
            AppContext.getInstance().set("affiliated", this.affiliated);
        }
    }

    public void takePicture() {
        FlowController.getInstance().goViewInWindowModal("imageTakerView", this.getStage(), false);
    }

    public void recoverFocus(Image takenImage) {
        try {
            if (takenImage != null) {
                this.affiliatedImageDir = "src/main/resources/cr/ac/una/tarea_bradleysegura_noemimurillo_programacion_ii/resources/" + (new Random()).nextInt(100000) + ".jpg";
                ImageIO.write(SwingFXUtils.fromFXImage(takenImage, null), "JPG", new File(this.affiliatedImageDir));
                this.imvAffiliatedImage.setImage(takenImage);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        FlowController.getInstance().limpiarLoader("ImageTakerView");
    }

}
