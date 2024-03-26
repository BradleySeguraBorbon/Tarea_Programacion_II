/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.DraggableMaker;
import java.awt.Color;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class AccountOpeningController extends Controller implements Initializable {
    
    @FXML
    private AnchorPane rootAnchorPane;
    @FXML
    private FlowPane openingAccountsFlowPane;
    @FXML
    private FlowPane openedAccountsFlowPane;
    @FXML
    private Label accountLabel1;
    @FXML
    private Label accountLabel2;
    @FXML
    private Label mainLabel;
    @FXML
    private Label cooperativeNameLabel;
    @FXML
    private ImageView cooperativeLogoImageView;
    @FXML
    private Button exitButton;
    
    private DraggableMaker dragFactory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        dragFactory = new DraggableMaker();
        dragFactory.makeDraggable(accountLabel1, rootAnchorPane, openingAccountsFlowPane, openedAccountsFlowPane);
    }    

    @Override
    public void initialize() {
    }
    
    public void createAccount() {
        
    }
    
}
