/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class CooperativeManagementController extends Controller implements Initializable {

    private String cooperativeName;
    private String cooperativeLogoDir;
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
        //MFXThemeManager.addOn(imvCooperativeLogoEditor.getScene(), Themes.DEFAULT, Themes.LEGACY);
        // TODO
    }

    @Override
    public void initialize() {

    }

    public void modifyCooperativeLogo() {
        FileChooser imageSelector = new FileChooser();
        imageSelector.setTitle("SELECCIÓN DE LOGO DE LA COOPERATIVA");

        FileChooser.ExtensionFilter imageSelectorFilter = new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg");
        imageSelector.getExtensionFilters().add(imageSelectorFilter);

        File selectedImageFile = imageSelector.showOpenDialog(null);
        if (selectedImageFile != null) {
            Image selectedImage = new Image(selectedImageFile.toURI().toString());
            imvCooperativeLogoEditor.setImage(selectedImage);
        }
    }

    public void makeChanges() {
        String modifiedCooperativeName = txtCooperativeNameEditor.getText();
        if (!modifiedCooperativeName.isBlank()) {
            this.cooperativeName = modifiedCooperativeName;
            ((TeachersMainController) FlowController.getInstance().getController("TeachersMainView")).setCooperativeLogo(this.imvCooperativeLogoEditor.getImage());
            ((TeachersMainController) FlowController.getInstance().getController("TeachersMainView")).setCooperativeName(modifiedCooperativeName);
        }
    }
}
