/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.ImageConverter;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class TopMainController extends Controller implements Initializable {

    @FXML
    private Label lblCooperativeName;
    @FXML
    private ImageView imvCooperativeLogo;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

    @Override
    public void initialize() {
           updateCooperativeInfo();
    }

    //Este método es para actualizar el nombre y logo de la cooperativa 
    public void updateCooperativeInfo() {
        String cooperativeLogo = (String) AppContext.getInstance().get("cooperativeLogo");
        String cooperativeName = (String) AppContext.getInstance().get("cooperativeName");

        System.out.println("COOP LOGO: " + cooperativeLogo);
        System.out.println("COOP NAME: " + cooperativeName);

        if (cooperativeLogo != null) {
            Platform.runLater(() -> {
                this.imvCooperativeLogo.getStyleClass().clear();
                this.imvCooperativeLogo.setImage(ImageConverter.fromBase64(cooperativeLogo));
            });

        }
        if (cooperativeName != null) {
            Platform.runLater(() -> {
                this.lblCooperativeName.setText(cooperativeName);
            });
        }
        System.out.println("Coop's Logo and Name Modified" + cooperativeName);
    }

}
