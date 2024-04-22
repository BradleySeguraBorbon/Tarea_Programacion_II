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
        initialize();
    }

    @Override
    public void initialize() {
        //Se limpian los espacios
        clearView();
        
        //Se crea una instancia de la clase DraggableMaker
        dragFactory = new DraggableMaker();
        //Se carga el afiliado que venía de la pantalla del Affiliated Selection
        this.selectedAffiliated = (Affiliated) AppContext.getInstance().get("selectedAffiliated");
        //Se carga el nombre del afiliado en la label
        this.lblSelectedAffiliated.setText("Afiliad@: " + this.selectedAffiliated.getFullName());
        //Se cargan las cuentas en las labels
        setAccountLabels();
    }

    //Este método es para generar labels por las cuentas que tiene el afiliado y las disponibles a aperturar 
    @FXML
    public void setAccountLabels() {
        //Se verifica que haya un afiliado seleccionado
        if (this.selectedAffiliated == null) {
            return;
        }
        //Se cargan los nombres de las cuentas del afiliado
        for (String accountType : this.selectedAffiliated.getAccountTypes()) {
            //Se crea una instancia de label
            Label accountLabel = new Label(accountType);
            //accountLabel.setPrefSize(140, 120);
            //Se le agrega una clase de CSS al label
            accountLabel.getStyleClass().add("jfx-lbl-draggable");
            //Se le agrega a la instancia de DraggableMaker el método principal para hacerla draggable con la label
            dragFactory.makeDraggable(accountLabel, root, fpOpeningAccounts, fpOpenedAccounts);
           //Se agregan al Pane de las cuentas que ya tiene el usuario 
            fpOpenedAccounts.getChildren().add(accountLabel);
        }
        //Se cargan los nombres de todas las cuentas que están disponibles para los afiliados
        for (String account : (ArrayList<String>) AppContext.getInstance().get("availableAccounts")) {
            //Se verifica que el afiliado no tenga la cuenta actual
            if (!this.selectedAffiliated.getAccountTypes().contains(account)) {
                //Como no la contiene se crea el label
                Label accountLabel = new Label(account);
                //accountLabel.setPrefSize(140, 120);
                //Se le agrega la clase de CSS
                accountLabel.getStyleClass().add("jfx-lbl-draggable");
            //Se le agrega a la instancia de DraggableMaker el método principal para hacerla draggable con la label
                dragFactory.makeDraggable(accountLabel, root, fpOpeningAccounts, fpOpenedAccounts);
                //Se agregan al Pane de las cuentas que el usuario no tiene, por ende las que puede abrir
                fpOpeningAccounts.getChildren().add(accountLabel);
            }
        }
    }
    
    //Este método es para agregar la cuenta nueva al usuario
    @FXML
    public void createAccount(Node labelNode) {
        //Se gaurda el nombre de la label, que también es el nombre de la cuenta nueva a aperturar
        String accountType = ((Label) labelNode).getText();
        //Verfica que el afiliado no tenga la cuenta activa
        if (!selectedAffiliated.getAccountTypes().contains(accountType)) {
            //Si no la tiene, entonces la activa, es decir que la agrega al arrayList de cuenats que tiene el afiliado 
            this.selectedAffiliated.addAccount(new Account(accountType));
        }
        //La muestra
        printAvailableAccounts();
    }

    //Remueve la cuenta que se movió, este método se llama en el DraggableMaker(DraggableMaker es una subclase de este controlador), ahí es donde se validan más cosas
    @FXML
    public void removeAccount(Node labelNode) {
        String accountType = ((Label) labelNode).getText();
        //Se le remueve la cuenta al afiliado
        selectedAffiliated.removeAccount(accountType);
        printAvailableAccounts();
    }
    
    //Este método limpia todos los espacios
    @FXML
    public void clearView() {
        this.fpOpeningAccounts.getChildren().clear();
        this.fpOpenedAccounts.getChildren().clear();
        this.lblSelectedAffiliated.setText("");
        this.selectedAffiliated = null;
        this.dragFactory = null;
    }

    //aux method, para que salga la información en consola
    @FXML
    public void printAvailableAccounts() {
        System.out.print("CUENTAS ABIERTAS: ");
        for (Account account : this.selectedAffiliated.getAccounts()) {
            System.out.println(account.getType() + " | ");
        }
    }
}
