/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Account;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.BoxDeposit;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Transaction;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.ImageConverter;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.util.StringConverter;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class MoneyManagementController extends Controller implements Initializable {

    @FXML
    private MFXFilterComboBox<Affiliated> fcbSelectAffiliated;
    @FXML
    private MFXFilterComboBox fcbSelectDepositAccount;
    @FXML
    private MFXTextField txtDepositAmount;
    @FXML
    private MFXButton btnDeposit;
    @FXML
    private MFXButton btnOpenDepositBox;
    @FXML
    private MFXTextField txtAffiliatedFolio;
    @FXML
    private MFXButton btnSearchAffiliated;
    @FXML
    private MFXFilterComboBox fcbSelectWithdrawAccount;
    @FXML
    private MFXTextField txtWithdrawAmount;
    @FXML
    private MFXButton btnWithdraw;
    @FXML
    private Label validateAffiliatedLabel;
    @FXML
    private ImageView imvAffiliatedImage;
    @FXML
    private Label lblAffiliatedName;
    @FXML
    private MFXButton btnValidateAffiliated;

    private ArrayList<Affiliated> afiliatedList;
    private Affiliated selectedAffiliated;
    private Account selectedAccount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initialize();
    }

    @Override
    public void initialize() {
        this.afiliatedList = (ArrayList<Affiliated>) AppContext.getInstance().get("affiliated");
        StringConverter<Affiliated> affiliatedConverter = FunctionalStringConverter.to(afiliated -> (afiliated == null) ? "" : afiliated.getFullName());
        this.fcbSelectAffiliated.setConverter(affiliatedConverter);
        StringConverter<Account> accountConverter = FunctionalStringConverter.to(account -> (account == null) ? "" : account.getType());
        fcbSelectDepositAccount.setConverter(accountConverter);
        this.fcbSelectAffiliated.setItems(FXCollections.observableArrayList(afiliatedList));
    }

    public void selectAffiliated() {
        clearDepositTab();
        selectedAffiliated = this.fcbSelectAffiliated.getSelectedItem();
        fcbSelectDepositAccount.setItems(FXCollections.observableArrayList(selectedAffiliated.getAccounts()));       
        fcbSelectDepositAccount.setDisable(false);
    }

    public void selectDepositAccount() {
        selectedAccount = (Account) fcbSelectDepositAccount.getSelectedItem();
        txtDepositAmount.setDisable(false);
        btnDeposit.setDisable(false);
    }

    public void deposit() {
        String depositAmount = txtDepositAmount.getText();
        if (!depositAmount.isBlank()) {
            Transaction deposit = new Transaction(Double.valueOf(depositAmount), this.selectedAffiliated.getFolio(), this.selectedAffiliated.getFullName(), this.selectedAccount.getType(), Transaction.Action.DEPOSITO);
            this.selectedAccount.makeTransaction(deposit);
            this.fcbSelectAffiliated.getSelectionModel().clearSelection();
            clearDepositTab();
            new Mensaje().show(Alert.AlertType.INFORMATION, "DEPÓSITO EXITOSO", "El depósito ha sido exitoso");
        } else {
            new Mensaje().show(Alert.AlertType.WARNING, "DEPÓSITO INVALIDO", "Debes indicar el monto a depositar");
        }
    }

    public void browseAffiliated() {
        clearWithdrawalTab();
        String typedFolio = txtAffiliatedFolio.getText();
        if (!typedFolio.isBlank()) {
            for (Affiliated afiliated : afiliatedList) {
                if (afiliated.getFolio().equals(typedFolio)) {
                    selectedAffiliated = afiliated;
                    break;
                }
            }
            if (selectedAffiliated != null) {
                imvAffiliatedImage.setImage(ImageConverter.fromBase64(selectedAffiliated.getProfileImage()));
                lblAffiliatedName.setText(selectedAffiliated.getFullName());
                validateAffiliatedLabel.setOpacity(1);
                btnValidateAffiliated.setDisable(false);
                btnValidateAffiliated.setOpacity(1);
            } else {
                new Mensaje().show(Alert.AlertType.WARNING, "AFILIADO NO ENCONTRADO", "No se ha encontrado el afiliado con el folio digitado");
                validateAffiliatedLabel.setOpacity(0);
                btnValidateAffiliated.setDisable(true);
            }
        }
    }

    public void validateAffiliated() {
        validateAffiliatedLabel.setOpacity(0);
        btnValidateAffiliated.setDisable(true);

        StringConverter<Account> converter = FunctionalStringConverter.to(account -> (account == null) ? "" : account.getType());
        fcbSelectWithdrawAccount.setItems(FXCollections.observableArrayList(selectedAffiliated.getAccounts()));
        fcbSelectWithdrawAccount.setConverter(converter);
        fcbSelectWithdrawAccount.setDisable(false);
    }

    public void selectWithdrawalAccount() {
        selectedAccount = (Account) fcbSelectWithdrawAccount.getSelectedItem();
        txtWithdrawAmount.setDisable(false);
        btnWithdraw.setDisable(false);
    }

    public void withdraw() {
        String withdrawalAmount = txtWithdrawAmount.getText();
        if (!withdrawalAmount.isBlank()) {
            Transaction withdraw = new Transaction(Double.valueOf(withdrawalAmount), this.selectedAffiliated.getFolio(), this.selectedAffiliated.getFullName(), this.selectedAccount.getType(), Transaction.Action.RETIRO);
            this.selectedAccount.makeTransaction(withdraw);
            clearWithdrawalTab();
            new Mensaje().show(Alert.AlertType.INFORMATION, "RETIRO EXITOSO", "El retiro ha sido exitoso");
        } else {
            new Mensaje().show(Alert.AlertType.WARNING, "RETIRO INVALIDO", "Debes indicar el monto a retirar");
        }
    }

    public void clearDepositTab() {
        this.fcbSelectDepositAccount.clear();
        this.fcbSelectDepositAccount.getSelectionModel().clearSelection();
        this.fcbSelectDepositAccount.setDisable(true);
        this.txtDepositAmount.setText("");
        this.txtDepositAmount.setDisable(true);
        this.btnDeposit.setDisable(true);
    }

    public void clearWithdrawalTab() {
        this.fcbSelectWithdrawAccount.clear();
        this.fcbSelectWithdrawAccount.setDisable(true);
        this.txtWithdrawAmount.clear();
        this.txtWithdrawAmount.setDisable(true);
        btnWithdraw.setDisable(true);
        this.btnValidateAffiliated.setOpacity(0);
        this.imvAffiliatedImage.setImage(null);
        this.lblAffiliatedName.setText("usuario");
        this.btnValidateAffiliated.setDisable(true);

    }

    public void openBoxDepositValidation() {
        FlowController.getInstance().goViewInWindow("BoxDepositValidationView");
    }
}
