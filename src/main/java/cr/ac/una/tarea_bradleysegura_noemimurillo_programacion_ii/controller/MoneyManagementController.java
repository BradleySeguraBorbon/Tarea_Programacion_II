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
    private MFXFilterComboBox<Affiliated> selectAffiliatedDepositBox;
    @FXML
    private MFXFilterComboBox selectAccountDepositBox;
    @FXML
    private MFXTextField depositAmountTextField;
    @FXML
    private MFXButton depositButton;
    @FXML
    private MFXButton openDepositBoxButton;
    @FXML
    private MFXTextField afiliatedFolioTextField;
    @FXML
    private MFXButton browseAffiliatedButton;
    @FXML
    private MFXFilterComboBox selectAccountWithdrawalBox;
    @FXML
    private MFXTextField withdrawalAmountTextField;
    @FXML
    private MFXButton withdrawalButton;
    @FXML
    private Label validateAffiliatedLabel;
    @FXML
    private ImageView afiliatedImageView;
    @FXML
    private Label afiliatedNameLabel;
    @FXML
    private MFXButton validateAffiliatedButton;
    @FXML
    private MFXTableView depositBoxTableView;
    @FXML
    private MFXButton depositValidationButton;
    @FXML
    private MFXTextField amountCorrectionTextField;

    @FXML
    private MFXFilterComboBox selectAccountComboBox;

    private ArrayList<Affiliated> afiliatedList;
    private Affiliated selectedAffiliated;
    private Account selectedAccount;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.afiliatedList = new ArrayList();
        StringConverter<Affiliated> converter = FunctionalStringConverter.to(afiliated -> (afiliated == null) ? "" : afiliated.getFullName());
        this.selectAffiliatedDepositBox.setItems(FXCollections.observableArrayList(afiliatedList));
        this.selectAffiliatedDepositBox.setConverter(converter);
    }

    @Override
    public void initialize() {
            
    }
    
    public void selectAffiliated() {
        selectedAffiliated = this.selectAffiliatedDepositBox.getSelectedItem();      
        
        StringConverter<Account> converter = FunctionalStringConverter.to(account -> (account == null) ? "" : account.getType());
        ArrayList<Account> userAccounts = selectedAffiliated.getAccounts();
        selectAccountDepositBox.setItems(FXCollections.observableArrayList(userAccounts));
        selectAccountDepositBox.setConverter(converter);
        selectAccountDepositBox.setDisable(false);
    }
    
    public void selectDepositAccount() {
        selectedAccount = (Account)selectAccountDepositBox.getSelectedItem();
        depositAmountTextField.setDisable(false);
        depositButton.setDisable(false);
    }
    
    public void deposit() {
        String depositAmount = depositAmountTextField.getText();
        if(!depositAmount.isBlank()) {
            selectedAccount.deposit(Double.parseDouble(depositAmount));
            new Mensaje().show(Alert.AlertType.INFORMATION, "DEPÓSITO EXITOSO", "El depósito ha sido exitoso");
        }
        else {
            new Mensaje().show(Alert.AlertType.WARNING, "DEPÓSITO INVALIDO", "Debes indicar el monto a depositar");
        }
    }
    
    public void browseAffiliated() {
        clearWithdrawalPane();
        String typedFolio = afiliatedFolioTextField.getText();
        if(!typedFolio.isBlank()) {
            for(Affiliated afiliated : afiliatedList) {
                if(afiliated.getFolio().equals(typedFolio)) {
                    selectedAffiliated = afiliated;
                    break;
                }
            }
            if(selectedAffiliated != null) {
                afiliatedImageView.setImage(new Image(selectedAffiliated.getProfileImage()));
                afiliatedNameLabel.setText(selectedAffiliated.getFullName());
                validateAffiliatedLabel.setOpacity(1);
                validateAffiliatedButton.setDisable(false);
            }
            else {
                new Mensaje().show(Alert.AlertType.WARNING, "AFILIADO NO ENCONTRADO", "No se ha encontrado el afiliado con el folio digitado");
                validateAffiliatedLabel.setOpacity(0);
                validateAffiliatedButton.setDisable(true);
            }
        }
    }
    
    public void validateAffiliated() {
         validateAffiliatedLabel.setOpacity(0);
         validateAffiliatedButton.setDisable(true);
        
        StringConverter<Account> converter = FunctionalStringConverter.to(account -> (account == null) ? "" : account.getType());
        selectAccountWithdrawalBox.setItems(FXCollections.observableArrayList(selectedAffiliated.getAccounts()));
        selectAccountWithdrawalBox.setConverter(converter);
        selectAccountWithdrawalBox.setDisable(false);
    }
    
    public void selectWithdrawalAccount() {
        selectedAccount = (Account)selectAccountWithdrawalBox.getSelectedItem();
        withdrawalAmountTextField.setDisable(false);
        withdrawalButton.setDisable(false);
    }
    
    public void withdraw() {
        String withdrawalAmount = withdrawalAmountTextField.getText();
        if(!withdrawalAmount.isBlank()) {
            selectedAccount.withdraw(Double.parseDouble(withdrawalAmount));
        }
        else {
            
        }
    }
    
    public void clearWithdrawalPane() {
        this.selectAccountWithdrawalBox.clear();
        this.selectAccountWithdrawalBox.setDisable(true);
        this.withdrawalAmountTextField.clear();
        this.withdrawalAmountTextField.setDisable(true);
        withdrawalButton.setDisable(true);
        this.validateAffiliatedButton.setOpacity(0);
        this.afiliatedImageView.setImage(null);
        this.afiliatedNameLabel.setText("usuario");
        this.validateAffiliatedButton.setDisable(true);
        
    }

    public void openBoxDepositValidation() {
        FlowController.getInstance().goViewInWindow("BoxDepositValidationView");
    }
}
