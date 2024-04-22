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
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Formato;
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
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class MoneyManagementController extends Controller implements Initializable {

    @FXML
    private AnchorPane apMoneyManagement;
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
    private Image imgDefault;
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
        this.imgDefault = this.imvAffiliatedImage.getImage();
    }

    @Override
    public void initialize() {
        //Inicialización de FilterComboBoxes
        this.afiliatedList = (ArrayList<Affiliated>) AppContext.getInstance().get("affiliated");
        StringConverter<Affiliated> affiliatedConverter = FunctionalStringConverter.to(afiliated -> (afiliated == null) ? "" : afiliated.getFullName());
        this.fcbSelectAffiliated.setConverter(affiliatedConverter);
        StringConverter<Account> accountConverter = FunctionalStringConverter.to(account -> (account == null) ? "" : account.getType());
        fcbSelectDepositAccount.setConverter(accountConverter);
        this.fcbSelectAffiliated.setItems(FXCollections.observableArrayList(afiliatedList));

        //Inicialización de TextFields para montos
        txtDepositAmount.delegateSetTextFormatter(Formato.getInstance().integerFormat());
        txtWithdrawAmount.delegateSetTextFormatter(Formato.getInstance().integerFormat());

        //Inicialización de TextField para folio
        txtAffiliatedFolio.delegateSetTextFormatter(Formato.getInstance().capsFormat(6));
    }

    public void selectAffiliated() {
        //Se limpia todo 
        clearDepositTab();
        //Se toma el afiliado del filterComboBox
        selectedAffiliated = this.fcbSelectAffiliated.getSelectedItem();
        //Si hay un seleccionado
        if (selectedAffiliated != null) {
            //Se suben las cuentas al filterComBox y se habilita 
            fcbSelectDepositAccount.setItems(FXCollections.observableArrayList(selectedAffiliated.getAccounts()));
            fcbSelectDepositAccount.setDisable(false);
        }
    }

    //Habilita espacios para poder realizar los depositos
    public void selectDepositAccount() {
        //Se carga la información de la cuenta seleccionada desde el filterComboBox
        selectedAccount = (Account) fcbSelectDepositAccount.getSelectedItem();
        //Si hay una cuenta seleccionada entonces se habilita el textfield para ingresar la cantidad y el boton de deposito
        if (selectedAccount != null) {
            txtDepositAmount.setDisable(false);
            btnDeposit.setDisable(false);
        }
    }

    //Este es el método para realizar el depósito
    public void deposit() {
        //Se obtiene el monto del textfield
        String depositAmount = txtDepositAmount.getText();
        //Se verifica que no esté en blanco 
        if (!depositAmount.isBlank()) {
            //Se crea una instancia de transacción para subie el depósito
            Transaction deposit = new Transaction(Double.valueOf(depositAmount), this.selectedAffiliated.getFolio(), this.selectedAffiliated.getFullName(), this.selectedAccount.getType(), Transaction.Action.DEPOSITO);
            //Se se agrega la transacción a la cuenta correspondiente
            this.selectedAccount.makeTransaction(deposit);
            //Se agregan los tickets para la rifa de afiliados 
            this.selectedAffiliated.addSpecialTickets(1);
            //Se limpia la selección del filterComboBox
            this.fcbSelectAffiliated.getSelectionModel().clearSelection();
            //Se limpia todo
            clearDepositTab();
            //Se genera un mensaje de que se ha realizado el depósito
            new Mensaje().show(Alert.AlertType.INFORMATION, "DEPÓSITO EXITOSO", "El depósito ha sido exitoso");
        } else {
            //Si no hay un monto a depositar entonces se genera un mensaje
            new Mensaje().show(Alert.AlertType.WARNING, "DEPÓSITO INVALIDO", "Debes indicar el monto a depositar");
        }
    }

    //Busca el afiliado
    public void browseAffiliated() {
        //Se limpia el tab de retiro
        clearWithdrawalTab();
        //Se obtiene el folio del afiliado
        String typedFolio = txtAffiliatedFolio.getText();
        //En caso de que el textField estuviera vacío
        if (!typedFolio.isBlank()) {
            //Se busca el afiliado
            for (Affiliated afiliated : afiliatedList) {
                if (afiliated.getFolio().equals(typedFolio)) {
                    //Se guarda en la variable local de afiliado 
                    selectedAffiliated = afiliated;
                    break;
                }
            }
            //Si se encontró el afiliado 
            if (selectedAffiliated != null) {
                //Se carga una la imagen del afiliado para que el funcionario verifique que es la dueña de la cuenta
                imvAffiliatedImage.setImage(ImageConverter.fromBase64(selectedAffiliated.getProfileImage()));
                //Se carga el nombre la para la verificación
                lblAffiliatedName.setText(selectedAffiliated.getFullName());
                //Se aparece y se habilita el boton de verificar, y se aparece un label que indica que debe verificar el usuario para poder hacer el retiro
                validateAffiliatedLabel.setOpacity(1);
                btnValidateAffiliated.setDisable(false);
                btnValidateAffiliated.setOpacity(1);
            } else {
                //Si no hay un afiliado entonces se genera un mensaje alerta 
                new Mensaje().show(Alert.AlertType.WARNING, "AFILIADO NO ENCONTRADO", "No se ha encontrado el afiliado con el folio digitado");
                //Se desparece el label de indicaciones de verificación y el botón se deshabilita
                validateAffiliatedLabel.setOpacity(0);
                btnValidateAffiliated.setDisable(true);
            }
        }
    }

    //Método para verificar el usuario, este se ejecuta cuando se verifica el usuario 
    public void validateAffiliated() {
        //Se deshabilita el boton y el label porque ya se accionó el botón
        validateAffiliatedLabel.setOpacity(0);
        btnValidateAffiliated.setDisable(true);
        //Se convierte de Account a String
        StringConverter<Account> converter = FunctionalStringConverter.to(account -> (account == null) ? "" : account.getType());
        //Se cargan las cuentas del afiliado al filterComboBox
        fcbSelectWithdrawAccount.setItems(FXCollections.observableArrayList(selectedAffiliated.getAccounts()));
        fcbSelectWithdrawAccount.setConverter(converter);
        //Se habilita el filterComboBox
        fcbSelectWithdrawAccount.setDisable(false);
    }

    //Se habilitan los espacios para hacer el retiro
    public void selectWithdrawalAccount() {
        //Se toma la cuenta seleccionada
        selectedAccount = (Account) fcbSelectWithdrawAccount.getSelectedItem();
        //Se habilita el textfield para digitar el monto y el botón de retiro
        if (this.selectedAccount != null) {
            txtWithdrawAmount.setDisable(false);
            btnWithdraw.setDisable(false);
        }
    }

    //Para hacer el retiro
    public void withdraw() {
        //Se obtiene el monto a retirar del textfield
        String withdrawalAmount = txtWithdrawAmount.getText();
        //Se verifica que tenga un monto
        if (!withdrawalAmount.isBlank()) {
            //Se verifica que el balance en la cuenta sea mayor o igual que la cantidad a retirar
            if (selectedAccount.getBalance() >= Double.valueOf(withdrawalAmount)) {
                //Se crea una instancia de transacción donde se guarda la información del retiro
                Transaction withdraw = new Transaction(Double.valueOf(withdrawalAmount), this.selectedAffiliated.getFolio(), this.selectedAffiliated.getFullName(), this.selectedAccount.getType(), Transaction.Action.RETIRO);
                //Se le agrega la transacción a la cuenta seleccionada
                this.selectedAccount.makeTransaction(withdraw);
                //Se agregan los tickets para la rifa de afiliados
                this.selectedAffiliated.addSpecialTickets(1);
                clearWithdrawalTab();
                new Mensaje().show(Alert.AlertType.INFORMATION, "RETIRO EXITOSO", "El retiro ha sido exitoso");
            } else {
                //Si el monto a retirar es mayor que el balance de la cuenta se despliega un mensaje de error por fondos insuficientes 
                new Mensaje().show(Alert.AlertType.ERROR, "RETIRO INVALIDO", "La cuenta tiene fondos insuficientes");
            }
        } else {
            //Si no teine un monto se despliega un mensaje de alerta para que se digite el monto
            new Mensaje().show(Alert.AlertType.WARNING, "RETIRO INVALIDO", "Debes indicar el monto a retirar");
        }
    }

    //Se limpia cada vez que se cambia de tap
    public void resetDepositTab() {
        this.fcbSelectAffiliated.clearSelection();
        clearDepositTab();
    }

    //Este método limpia las variables locales que transportan datos de un me´todo a otro y se limpia el tab para depósito.
    public void clearDepositTab() {
        this.selectedAffiliated = null;
        this.selectedAccount = null;
        this.fcbSelectDepositAccount.clear();
        this.fcbSelectDepositAccount.clearSelection();
        this.fcbSelectDepositAccount.setDisable(true);
        this.txtDepositAmount.setText("");
        this.txtDepositAmount.setDisable(true);
        this.btnDeposit.setDisable(true);
    }

    //Este método limpia las variables locales que transportan datos de un me´todo a otro y se limpia el tab para retiro.
    public void clearWithdrawalTab() {
        this.selectedAffiliated = null;
        this.selectedAccount = null;
        this.fcbSelectWithdrawAccount.clear();
        this.fcbSelectWithdrawAccount.clearSelection();
        this.fcbSelectWithdrawAccount.setDisable(true);
        this.txtWithdrawAmount.clear();
        this.txtWithdrawAmount.setDisable(true);
        btnWithdraw.setDisable(true);
        this.btnValidateAffiliated.setOpacity(0);
        this.imvAffiliatedImage.setImage(this.imgDefault);
        this.lblAffiliatedName.setText("usuario");
        this.btnValidateAffiliated.setDisable(true);

    }

    //Se abre la venta del BoxDeposit para los depósitos que hace el afiliado solo.
    public void openBoxDepositValidation() {
        FlowController.getInstance().goViewInWindowModal("BoxDepositValidationView", getStage(), true);
    }
}
