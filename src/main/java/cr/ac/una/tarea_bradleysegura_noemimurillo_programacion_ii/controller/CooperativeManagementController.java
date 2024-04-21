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
        initialize();
    }
    
    @Override
    public void initialize() {
        if ((this.cooperativeName = (String) AppContext.getInstance().get("CooperativeName")) != null
                && (this.cooperativeIcon = (String) AppContext.getInstance().get("CooperativeLogo")) != null) {
            this.imvCooperativeLogoEditor.setImage(ImageConverter.fromBase64(cooperativeIcon));
            this.txtCooperativeNameEditor.setText(this.cooperativeName);
        }
        this.txtCooperativeNameEditor.delegateSetTextFormatter(Formato.getInstance().letrasFormat(20));
    }
    
    public void modifyCooperativeLogo() {
        try {
            FileChooser imageSelector = new FileChooser();
            imageSelector.setTitle("SELECCIÓN DE LOGO DE LA COOPERATIVA");
            
            FileChooser.ExtensionFilter imageSelectorFilter = new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg","*.jpeg");
            imageSelector.getExtensionFilters().add(imageSelectorFilter);
            
            File selectedImageFile = imageSelector.showOpenDialog(null);
            
            if (selectedImageFile != null) {
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
        if (modifiedCooperativeName.isBlank()) {
            msj.show(Alert.AlertType.WARNING, "NOMBRE DE COOPERATIVA NO INDICADO", "Escribe el nombre de la cooperativa para continuar");
            this.txtCooperativeNameEditor.requestFocus();
        } else if (this.cooperativeIcon == null) {
            msj.show(Alert.AlertType.WARNING, "LOGO NO MODIFICADO", "Ingresa el nuevo logo de la cooperativa para continuar");
        } else {
            this.cooperativeName = modifiedCooperativeName;
            AppContext.getInstance().set("CooperativeName", this.cooperativeName);
            AppContext.getInstance().set("CooperativeLogo", this.cooperativeIcon);
            ((MainController) FlowController.getInstance().getController("MainView")).updateCooperativeInfo();
        }
    }
}
