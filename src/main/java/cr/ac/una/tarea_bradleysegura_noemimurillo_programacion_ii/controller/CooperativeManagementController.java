/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Formato;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.ImageConverter;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class CooperativeManagementController extends Controller implements Initializable {

    private String cooperativeName;
    private String cooperativeIcon;

    @FXML
    private ImageView imvCooperativeLogoEditor;
    @FXML
    private MFXTextField txtCooperativeNameEditor;
    @FXML
    private MFXButton btnMakeChanges;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }

    @Override
    public void initialize() {
        //Cargamos el nombre y el logo de la coopertaiva desde el appContext
        if ((this.cooperativeName = (String) AppContext.getInstance().get("cooperativeName")) != null
                && (this.cooperativeIcon = (String) AppContext.getInstance().get("cooperativeLogo")) != null) {
            this.imvCooperativeLogoEditor.setImage(ImageConverter.fromBase64(cooperativeIcon));
            this.txtCooperativeNameEditor.setPromptText(this.cooperativeName);
            //this.imvCooperativeLogoEditor.getStyleClass().clear();
        }

        //Se le agrega formato al textfield que recibe el nuevo nombre de la cooperativa
        this.txtCooperativeNameEditor.delegateSetTextFormatter(Formato.getInstance().letrasFormat(20));
    }

    //Al momento de darle click a la imagen se ejecuta este programa para seleccionar una nueva imagen 
    @FXML
    public void modifyCooperativeLogo() {
        try {
            //Se crea una ventana para abrir los archivos y seleccioanr la imagen
            FileChooser imageSelector = new FileChooser();
            imageSelector.setTitle("SELECCIÓN DE LOGO DE LA COOPERATIVA");

            //Tomará en cuenta solo los archivos de imagen 
            FileChooser.ExtensionFilter imageSelectorFilter = new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg");
            imageSelector.getExtensionFilters().add(imageSelectorFilter);

            //Se guarda el archivo seleccionado
            File selectedImageFile = imageSelector.showOpenDialog(null);

            //Si hay una iamgen sleccionada 
            if (selectedImageFile != null) {
                //Se mostrará la imagen en el imageView
                imvCooperativeLogoEditor.setImage(new Image(selectedImageFile.toURI().toString()));
                this.cooperativeIcon = ImageConverter.toBase64(ImageIO.read(selectedImageFile), "PNG");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void makeChanges() {
        Mensaje msj = new Mensaje();
        String modifiedCooperativeName = txtCooperativeNameEditor.getText();
        if (modifiedCooperativeName.isBlank() && this.txtCooperativeNameEditor.getPromptText().isBlank()) {
            msj.show(Alert.AlertType.WARNING, "NOMBRE DE COOPERATIVA NO INDICADO", "Escribe el nombre de la cooperativa para continuar");
        }else if(modifiedCooperativeName.isBlank()) {
             AppContext.getInstance().set("cooperativeName", this.txtCooperativeNameEditor.getPromptText());
        } else if (this.cooperativeIcon == null) {
            msj.show(Alert.AlertType.WARNING, "LOGO NO MODIFICADO", "Ingresa el nuevo logo de la cooperativa para continuar");
        } else {
            this.cooperativeName = modifiedCooperativeName;
            AppContext.getInstance().set("cooperativeName", this.cooperativeName);
            AppContext.getInstance().set("cooperativeLogo", this.cooperativeIcon);
        }
        FlowController.getInstance().goView("TopMainView", "Top", null);
        FlowController.getInstance().goView("MainMenuView");
        new Mensaje().show(Alert.AlertType.INFORMATION, "CAMBIOS REALIZADOS EXITOSAMENTE", "Los cambios han sido completados con éxito");
    }
}

//        if (!modifiedCooperativeName.isBlank() && this.cooperativeIcon != null) {
//            this.cooperativeName = modifiedCooperativeName;
//            AppContext.getInstance().set("CooperativeName", this.cooperativeName);
//            AppContext.getInstance().set("CooperativeLogo", this.cooperativeIcon);
//            msj.show(Alert.AlertType.INFORMATION, "NOMBRE Y LOGO DE COOPERATIVA MODIFICADOS", "¡El nombre y logo de la cooperativa han sido modificados exitosamente!");
//            ((MainController) FlowController.getInstance().getController("MainView")).updateCooperativeInfo();
//
//        } else if (!modifiedCooperativeName.isBlank() && this.cooperativeIcon == null) {
//            this.cooperativeName = modifiedCooperativeName;
//            AppContext.getInstance().set("CooperativeName", this.cooperativeName);
//            msj.show(Alert.AlertType.INFORMATION, "NOMBRE DE COOPERATIVA MODIFICADO", "¡El nombre de la cooperativa ha sido modificado exitosamente!");
//            ((MainController) FlowController.getInstance().getController("MainView")).updateCooperativeInfo();
//
//        } else if (this.cooperativeIcon != null && modifiedCooperativeName.isBlank()) {
//            AppContext.getInstance().set("CooperativeLogo", this.cooperativeIcon);
//            msj.show(Alert.AlertType.INFORMATION, "LOGO NO MODIFICADO", "¡El logo de la cooperativa ha sido modificado exitosamente!");
//            ((MainController) FlowController.getInstance().getController("MainView")).updateCooperativeInfo();
//
//        } 
