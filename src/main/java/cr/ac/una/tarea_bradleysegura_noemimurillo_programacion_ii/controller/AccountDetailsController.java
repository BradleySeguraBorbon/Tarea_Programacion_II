/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Account;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Transaction;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Accordion;
import javafx.scene.control.Alert;
import javafx.scene.control.TitledPane;

/**
 * FXML Controller class
 *
 * @author Fiorella
 */
public class AccountDetailsController extends Controller implements Initializable {

    @FXML
    private MFXButton btnLogin;
    @FXML
    private MFXTextField txfFolio;
    @FXML
    private MFXTextField txfNameField;
    @FXML
    private MFXTextField txfBalance;
    @FXML
    private MFXFilterComboBox fcbAccounts;
    @FXML
    private Accordion acdSelectedAccount;
    @FXML
    private MFXTextField txfName;
    @FXML
    private TitledPane tpnAccountType;
    @FXML
    private MFXTableView tbvDetails;
    @FXML
    private MFXTableColumn tbcID;
    @FXML
    private MFXTableColumn tbcTime;
    @FXML
    private MFXTableColumn tbcAction;
    @FXML
    private MFXTableColumn tbcAmount;
   

    ArrayList<Affiliated> affiliates;
    ArrayList<Transaction> Moves;
    ArrayList<String> NameAccounts;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        initialize();
    }

    @Override
    public void initialize() {
        if (AppContext.getInstance().get("affiliated") != null) {
            affiliates = (ArrayList<Affiliated>) AppContext.getInstance().get("affiliated");
        }
    }

    public void loadAccounts() {
        String folio = txfFolio.getText().trim(); // Obtener el texto del folio y eliminar espacios en blanco
        boolean folioEncontrado = false;
        if (!folio.isEmpty()) { // Verificar si el folio no está vacío
            for (Affiliated affiliated : this.affiliates) {
                if (affiliated.getFolio().equals(folio)) {
                    txfName.setText(affiliated.getFullName());
                    NameAccounts = new ArrayList();
                    for (String accounts : affiliated.getAccountTypes()) {
                        NameAccounts.add(accounts);
                    }
                    fcbAccounts.setItems(FXCollections.observableArrayList(NameAccounts));
                    folioEncontrado = true;
                    break;
                }
            }
            if (!folioEncontrado) {
                new Mensaje().show(Alert.AlertType.ERROR, "FOLIO INCORRECTO", "El número de folio no está registrado en nuestra cooperativa.");
            }
        } else {
            new Mensaje().show(Alert.AlertType.ERROR, "FOLIO VACÍO", "No se ha ingresado un número de folio. Por favor, ingrese un número de folio.");
        }
    }

    public void displayAccordion() {
        String selectedAccount = (String) fcbAccounts.getSelectionModel().getSelectedItem();
        String folio = txfFolio.getText().trim(); // Obtener el texto del folio y eliminar espacios en blanco
        for (Affiliated affiliated : this.affiliates) {
            if (affiliated.getFolio().equals(folio)) {
                for (Account account : affiliated.getAccounts()) {
                    if (account.getType().equals(selectedAccount)) {
                        txfNameField.setText(selectedAccount);
                        txfBalance.setText(account.getBalance().toString());
                        Moves = new ArrayList();
                        for (Transaction transaction : account.getHistory()) {
                            Moves.add(transaction);
                        }
                        break;
                    }
                }
                break;
            }
        }
        this.tbcID.setRowCellFactory(transaction -> new MFXTableRowCell<>(Transaction::getTransactionID));
        this.tbcTime.setRowCellFactory(transaction -> new MFXTableRowCell<>(Transaction::getTransactionTime));
        this.tbcAction.setRowCellFactory(transaction -> new MFXTableRowCell<>(Transaction::getAction));
        this.tbcAmount.setRowCellFactory(transaction -> new MFXTableRowCell<>(Transaction::getAmount));
        this.tbvDetails.setItems(FXCollections.observableArrayList(this.Moves));
    }

}
