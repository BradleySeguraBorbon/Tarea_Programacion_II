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
    private AnchorPane root;
    @FXML
    private FlowPane fpOpeningAccounts;
    @FXML
    private FlowPane fpOpenedAccounts;
    @FXML
    private Label lblSelectedAffiliated;

    private Affiliated selectedAffiliated;
    private DraggableMaker dragFactory;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        dragFactory = new DraggableMaker();
        this.selectedAffiliated = (Affiliated) AppContext.getInstance().get("selectedAffiliated");
        this.lblSelectedAffiliated.setText(this.selectedAffiliated.getFullName());
        setAccountLabels();
    }

    @Override
    public void initialize() {

    }

    public void setAccountLabels() {
        if (this.selectedAffiliated == null) {
            return;
        }
        for (String accountType : this.selectedAffiliated.getAccountTypes()) {
            Label accountLabel = new Label(accountType);
            accountLabel.setPrefSize(140, 60);
            dragFactory.makeDraggable(accountLabel, root, fpOpeningAccounts, fpOpenedAccounts);
            fpOpenedAccounts.getChildren().add(accountLabel);
        }
        for (String account : (ArrayList<String>) AppContext.getInstance().get("availableAccounts")) {
            if (!this.selectedAffiliated.getAccountTypes().contains(account)) {
                Label accountLabel = new Label(account);
                accountLabel.setPrefSize(140, 60);
                dragFactory.makeDraggable(accountLabel, root, fpOpeningAccounts, fpOpenedAccounts);
                fpOpeningAccounts.getChildren().add(accountLabel);
            }
        }
    }

    public void createAccount(Node labelNode) {
        String accountType = ((Label) labelNode).getText();
        if (!selectedAffiliated.getAccountTypes().contains(accountType)) {
            this.selectedAffiliated.addAccount(new Account(accountType));
        }
        printAvailableAccounts();
    }

    public void removeAccount(Node labelNode) {
        String accountType = ((Label) labelNode).getText();
        selectedAffiliated.removeAccount(accountType);
        printAvailableAccounts();
    }
    
    //aux method
    public void printAvailableAccounts() {
        System.out.print("CUENTAS ABIERTAS: ");
        for(Account account : this.selectedAffiliated.getAccounts()) {
            System.out.println(account.getType() + " | ");
        }
    }
}
