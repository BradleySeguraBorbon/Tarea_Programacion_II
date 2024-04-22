/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

/*import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;*/
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Formato;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.ImageConverter;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXSpinner;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.models.spinner.IntegerSpinnerModel;
import io.github.palexdev.materialfx.controls.models.spinner.SpinnerModel;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import java.awt.image.BufferedImage;
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
    private Image imgDefault;
    @FXML
    private MFXTextField txtName;
    @FXML
    private MFXTextField txtFirstLastName;
    @FXML
    private MFXTextField txtSecondLastName;
    @FXML
    private MFXSpinner<Integer> spnrAge;
    @FXML
    private MFXRadioButton rBtnMasculino;
    @FXML
    private MFXRadioButton rBtnFemenino;
    @FXML
    private MFXButton btnRegister;
    @FXML
    private ToggleGroup SexSelection;

    private ArrayList<Affiliated> affiliated;
    private BufferedImage image;

    /**
     * Initializablelizes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.imgDefault = this.imvAffiliatedImage.getImage();
    }

    @Override
    public void initialize() {
        //Inicialización de lista de afiliados
        if (AppContext.getInstance().get("affiliated") != null) {
            this.affiliated = (ArrayList<Affiliated>) AppContext.getInstance().get("affiliated");
            spnrAge.setSpinnerModel(new IntegerSpinnerModel(0));
        }
        
        //Inicialización de formatos de los TextFields
        txtName.delegateSetTextFormatter(Formato.getInstance().letrasFormat(20));
        txtFirstLastName.delegateSetTextFormatter(Formato.getInstance().letrasFormat(20));
        txtSecondLastName.delegateSetTextFormatter(Formato.getInstance().letrasFormat(20));
    }

    public void register() {
        Mensaje alerta = new Mensaje();
        //Se crean Strings con los textos de cada textfield correspondiente.
        String name = txtName.getText(),
                firstLastName = txtFirstLastName.getText(),
                secondLastName = txtSecondLastName.getText(),
                cooperativeName = (String) AppContext.getInstance().get("cooperativeName");
        //Se crea un int con la información del spinner
        Integer age = (int) spnrAge.getSpinnerModel().getValue();
        
        //Esto es para verificar que todos los textfield o espacios de entrada de datos estén llenos.
        if (name.isBlank()) {
            alerta.show(Alert.AlertType.WARNING, "NOMBRE INCOMPLETO", "Debes ingresar tu nombre para continuar");
            System.out.println("ERROR");
            txtName.requestFocus();
        } else if (firstLastName.isBlank()) {
            alerta.show(Alert.AlertType.WARNING, "PRIMER APELLIDO INCOMPLETO", "Debes ingresar tu primer apellido para continuar");
            System.out.println("ERROR");
            txtFirstLastName.requestFocus();
        } else if (secondLastName.isBlank()) {
            alerta.show(Alert.AlertType.WARNING, "SEGUNDO APELLIDO INCOMPLETO", "Debes ingresar tu segundo apellido para continuar");
            System.out.println("ERROR");
            txtSecondLastName.requestFocus();
        } else if (spnrAge.getSpinnerModel().getValue() == null) {
            alerta.show(Alert.AlertType.WARNING, "EDAD NO INDICADA", "Debes indicar tu edad para continuar");
            System.out.println("ERROR");
            spnrAge.requestFocus();
        } else if (!rBtnMasculino.isSelected() && !rBtnFemenino.isSelected()) {
            alerta.show(Alert.AlertType.WARNING, "SEXO NO INDICADO", "Debes indicar tu sexo para continuar");
            System.out.println("ERROR");
        } else if (image == null) {
            alerta.show(Alert.AlertType.WARNING, "IMAGEN NO CAPTURADA", "Debes capturar una imagen para continuar");
            System.out.println("ERROR");
        } else {
            //Si todos los espacios están llenos entonces se obtiene el sexo del neuvo afiliado.
            Affiliated.Sexo sexo = (rBtnMasculino.isSelected()) ? Affiliated.Sexo.MASCULINO : Affiliated.Sexo.FEMENINO;
            //Se muestra en la consola
            System.out.println("IMAGE IN JSON " + ImageConverter.toBase64(image, "JPG"));
            
            //Se agrega el nuevo afiliado
            this.affiliated.add(new Affiliated(name, firstLastName, secondLastName, age, sexo, ImageConverter.toBase64(image, "JPG"), cooperativeName));
            alerta.show(Alert.AlertType.INFORMATION, "REGISTRO EXITOSO", "'¡Bienvenid@ a " + cooperativeName + ", " + name + "!");
            //Se agrega al AppContext
            AppContext.getInstance().set("affiliated", this.affiliated);
            clear();
            
            //Se devuelve al menú principal
            FlowController.getInstance().goView("MainMenuView");
            AppContext.getInstance().set("inMainMenu", true);
        }
    }

    //Al momento de darle click a la imagen se ejecuta este método que abre una nueva ventana para tomar la foto del nuevo afiliado.
    public void takePicture() {
        FlowController.getInstance().goViewInWindowModal("imageTakerView", this.getStage(), true);
        //FlowController.getInstance().goView("imageTakerView");
    }
    
    //Este método es para que el imageView cargue la imagen que se tomó el usuario, se llama desde el imageTakerController
    public void recoverFocus(BufferedImage takenImage) {
        try {
            if (takenImage != null) {
                this.image = takenImage;
                this.imvAffiliatedImage.setImage(SwingFXUtils.toFXImage(takenImage, null));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    //Este método limpia todos los espacios
    public void clear() {
        this.imvAffiliatedImage.setImage(this.imgDefault);
        this.txtName.setText("");
        this.txtFirstLastName.setText("");
        this.txtSecondLastName.setText("");
        this.spnrAge.setValue(0);
        if(this.SexSelection.getSelectedToggle() != null) {
            this.SexSelection.getSelectedToggle().setSelected(false);
        }
        this.affiliated = null;
        this.image = null;
    }

}
