/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Account;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Transaction;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Formato;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Mensaje;
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
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Fiorella
 */
public class AccountDetailsController extends Controller implements Initializable {

    @FXML
    private MFXTextField txfFolio;
    @FXML
    private MFXTextField txfNameField;
    @FXML
    private MFXTextField txfBalance;
    @FXML
    private MFXFilterComboBox fcbAccounts;
    @FXML
    private MFXTextField txfName;
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
    ArrayList<Transaction> Moves = new ArrayList();
    ArrayList<String> NameAccounts = new ArrayList();

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        txfFolio.delegateSetTextFormatter(Formato.getInstance().capsFormat(6));
        //Se limpian todos los espacios

        initialize();
    }

    @Override
    public void initialize() {
        //Se cargan todos los afiliados a un arrayList.
        if (AppContext.getInstance().get("affiliated") != null) {
            affiliates = (ArrayList<Affiliated>) AppContext.getInstance().get("affiliated");
        }
        Clean();
    }

    //Este método es para cargar las cuentas en el FilterComboBox
    public void loadAccounts() {
        NameAccounts.clear();
        // Obtener el texto del folio y eliminar espacios en blanco
        String folio = txfFolio.getText().trim();
        boolean folioEncontrado = false;

        if (!folio.isEmpty()) {
            for (Affiliated affiliated : this.affiliates) {
                if (affiliated.getFolio().equals(folio)) {
                    //Se muestra el nombre del afiliado para que el usuario verifique.
                    txfName.setText(affiliated.getFullName());
                    //Se guardan todos los nombres de las cuentas en un arrayList para el FilterComboBox.
                    for (String accounts : affiliated.getAccountTypes()) {
                        NameAccounts.add(accounts);
                    }
                    //Se muestran los nombres de las cuentas en el FilterComboBox
                    fcbAccounts.setItems(FXCollections.observableArrayList(NameAccounts));
                    //Sí se encontró el afiliado
                    folioEncontrado = true;
                    break;
                }
            }
            //Si no se encontrara el afiliado entonces tira un error.
            if (!folioEncontrado) {
                new Mensaje().show(Alert.AlertType.ERROR, "FOLIO INCORRECTO", "El número de folio no está registrado en nuestra cooperativa.");
            }
        } else {
            //Si no se ha ingresado un número de folio tira un error.
            new Mensaje().show(Alert.AlertType.ERROR, "FOLIO VACÍO", "No se ha ingresado un número de folio. Por favor, ingrese un número de folio.");
        }
    }

    //Este método es para desplegar toda la información en el tableView que está dentro del accordion
    public void displayAccordion() {
        Moves.clear();
        //Esto nos da el nombre de la cuenta seleccionada en el FilterComboBox
        String selectedAccount = (String) fcbAccounts.getSelectionModel().getSelectedItem();
        //Obtener el texto del folio y eliminar espacios en blanco
        String folio = txfFolio.getText().trim();
        //Se busca el afiliado
        for (Affiliated affiliated : this.affiliates) {
            if (affiliated.getFolio().equals(folio)) {
                //Se busca la cuenta
                for (Account account : affiliated.getAccounts()) {
                    if (account.getType().equals(selectedAccount)) {
                        //Se llenan los espacios con la información de la cuenta(nombre y monto disponible)
                        txfNameField.setText(selectedAccount);
                        txfBalance.setText(account.getBalance().toString());
                        //Se llena un arrayList con la informacipon de las transacciones para cargarlas en el tableview
                        for (Transaction transaction : account.getHistory()) {
                            Moves.add(transaction);
                        }
                        break;
                    }
                }
                break;
            }
        }
        //Se llenan las celdas con la información y se cargan en el tableView
        this.tbcID.setRowCellFactory(transaction -> new MFXTableRowCell<>(Transaction::getTransactionID));
        this.tbcTime.setRowCellFactory(transaction -> new MFXTableRowCell<>(Transaction::getTransactionTime));
        this.tbcAction.setRowCellFactory(transaction -> new MFXTableRowCell<>(Transaction::getAction));
        this.tbcAmount.setRowCellFactory(transaction -> new MFXTableRowCell<>(Transaction::getAmount));
        this.tbvDetails.setItems(FXCollections.observableArrayList(this.Moves));

    }

    //Este método limpia todo cuando el usuario le da al botón de salir y en caso de que no le haya dado click entonces también se hace en el iniciatilize 
    public void Clean() {
        txfFolio.clear();
        txfNameField.clear();
        txfBalance.clear();
        txfName.clear();
        fcbAccounts.getItems().clear();
        Moves.clear();
        NameAccounts.clear();
        this.tbvDetails.setItems(FXCollections.observableArrayList(this.Moves));
    }

}
