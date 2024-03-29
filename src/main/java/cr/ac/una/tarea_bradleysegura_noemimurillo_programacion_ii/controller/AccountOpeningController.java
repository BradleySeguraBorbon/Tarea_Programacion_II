/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Account;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.DraggableMaker;
import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
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

    private Affiliated selectedAffiliated;
    private DraggableMaker dragFactory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dragFactory = new DraggableMaker();
        dragFactory.makeDraggable(accountLabel1, rootAnchorPane, openingAccountsFlowPane, openedAccountsFlowPane);

        this.selectedAffiliated = (Affiliated) AppContext.getInstance().get("selectedAffiliated");

    }

    public void setAccountLabels() {
        if (this.selectedAffiliated == null) {
            //TODO
            return;
        }
        for (String accountType : this.selectedAffiliated.getAccountTypes()) {
            Label accountLabel = new Label(accountType);
            accountLabel.setPrefSize(140, 60);
            openedAccountsFlowPane.getChildren().add(accountLabel);
        }
        for (String account : (ArrayList<String>) AppContext.getInstance().get("availableAccounts")) {
            if (!this.selectedAffiliated.getAccountTypes().contains(account)) {
                Label accountLabel = new Label(account);
                accountLabel.setPrefSize(140, 60);
                openingAccountsFlowPane.getChildren().add(accountLabel);
            }
        }
    }

    @Override
    public void initialize() {
    }

    public void createAccount(Node labelNode) {
        String accountType = ((Label)labelNode).getText();
        if(!selectedAffiliated.getAccountTypes().contains(accountType)) {
            this.selectedAffiliated.addAccount(new Account(accountType));
        }
    }
    
    public void removeAccount(Node labelNode) {
         String accountType = ((Label)labelNode).getText();
         selectedAffiliated.removeAccount(accountType);
    }
    

}
