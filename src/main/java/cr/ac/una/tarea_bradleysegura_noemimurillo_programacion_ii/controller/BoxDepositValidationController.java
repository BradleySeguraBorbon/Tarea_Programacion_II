/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Account;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.BoxDeposit;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSpinner;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.controls.models.spinner.IntegerSpinnerModel;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class BoxDepositValidationController extends Controller implements Initializable {

    @FXML
    private MFXTableView<BoxDeposit> tbvBoxDeposits;
    @FXML
    private MFXTableColumn<BoxDeposit> tbcAffiliated;
    @FXML
    private MFXTableColumn<BoxDeposit> tbcFolio;
    @FXML
    private MFXTableColumn<BoxDeposit> tbcAccount;
    @FXML
    private MFXTableColumn<BoxDeposit> tbcAmount;
    @FXML
    private MFXSpinner<Integer> spnrCincoColones;
    @FXML
    private MFXSpinner<Integer> spnrDiezColones;
    @FXML
    private MFXSpinner<Integer> spnrVeinticincoColones;
    @FXML
    private MFXSpinner<Integer> spnrCincuentaColones;
    @FXML
    private MFXSpinner<Integer> spnrCienColones;
    @FXML
    private MFXSpinner<Integer> spnrQuinientosColones;
    @FXML
    private MFXSpinner<Integer> spnrMilColones;
    @FXML
    private MFXSpinner<Integer> spnrDosMilColones;
    @FXML
    private MFXSpinner<Integer> spnrCincoMilColones;
    @FXML
    private MFXSpinner<Integer> spnrDiezMilColones;
    @FXML
    private MFXSpinner<Integer> spnrVeinteMilColones;
    @FXML
    private MFXButton btnValidateDeposit;
    @FXML
    private MFXButton btnModifyDeposit;
    @FXML
    private MFXButton btnSaveCorrections;
    @FXML
    private MFXButton btnCancel;
    @FXML
    private MFXButton btnExit;

    private ArrayList<Affiliated> affiliatedList;
    private ArrayList<BoxDeposit> boxDeposits;
    private BoxDeposit selectedDeposit;
    private HashMap<BoxDeposit.Denomination, MFXSpinner> spinners;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        setupTbvBoxDeposits();
        if (AppContext.getInstance().get("affiliated") != null) {
            this.affiliatedList = (ArrayList<Affiliated>) AppContext.getInstance().get("affiliated");
        }
        this.btnCancel.setDisable(true);
    }

    @Override
    public void initialize() {

    }

    public void setupTbvBoxDeposits() {
        this.tbcAffiliated.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getAffiliatedName));
        this.tbcFolio.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getAffiliatedFolio));
        this.tbcAccount.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getAccountType));
        this.tbcAmount.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getAmount));

        this.spinners = new HashMap<>();
        this.spinners.put(BoxDeposit.Denomination.CINCO, spnrCincoColones);
        this.spinners.put(BoxDeposit.Denomination.DIEZ, spnrDiezColones);
        this.spinners.put(BoxDeposit.Denomination.VEINTICINCO, spnrVeinticincoColones);
        this.spinners.put(BoxDeposit.Denomination.CINCUENTA, spnrCincuentaColones);
        this.spinners.put(BoxDeposit.Denomination.CIEN, spnrCienColones);
        this.spinners.put(BoxDeposit.Denomination.QUINIENTOS, spnrQuinientosColones);
        this.spinners.put(BoxDeposit.Denomination.MIL, spnrMilColones);
        this.spinners.put(BoxDeposit.Denomination.DOSMIL, spnrDosMilColones);
        this.spinners.put(BoxDeposit.Denomination.CINCOMIL, spnrCincoMilColones);
        this.spinners.put(BoxDeposit.Denomination.DIEZMIL, spnrDiezMilColones);
        this.spinners.put(BoxDeposit.Denomination.VEINTEMIL, spnrVeinteMilColones);

        for (MFXSpinner<Integer> spinner : this.spinners.values()) {
            spinner.setSpinnerModel(new IntegerSpinnerModel(0));
        }
        if (AppContext.getInstance().get("boxDeposits") != null) {
            boxDeposits = (ArrayList<BoxDeposit>) AppContext.getInstance().get("boxDeposits");
            this.tbvBoxDeposits.setItems(FXCollections.observableArrayList(boxDeposits));
        }
    }

    public void modifyDeposit() {
        selectedDeposit = this.tbvBoxDeposits.getSelectionModel().getSelectedValue();
        if (selectedDeposit != null) {
            for (BoxDeposit.Denomination denomination : this.spinners.keySet()) {
                spinners.get(denomination).setDisable(false);
                spinners.get(denomination).setValue(this.selectedDeposit.getSpecificDenomination(denomination));
            }
            setCorrectionPhase(true); // Habilita y deshabilita botones para realizar correcciones
        } else {
            new Mensaje().show(Alert.AlertType.WARNING, "NO HAY DEPÓSITO SELECCIONADO", "Selecciona un depósito de la lista para corregir su monto");
        }
    }

    public void saveCorrections() {
        selectedDeposit = this.tbvBoxDeposits.getSelectionModel().getSelectedValue();
        if (selectedDeposit != null) {
            for (BoxDeposit.Denomination denomination : this.spinners.keySet()) {
                this.selectedDeposit.setDenomination(denomination, (Integer) spinners.get(denomination).getSpinnerModel().getValue());
                spinners.get(denomination).setDisable(true);
            }
            setCorrectionPhase(false);
        }
        this.selectedDeposit.calculateTotal();
        this.tbvBoxDeposits.update();
    }

    public void cancelCorrections() {
        if (!btnSaveCorrections.isDisable()) {
            for (MFXSpinner spinner : this.spinners.values()) {
                spinner.setDisable(true);
                spinner.setValue(0);
            }
            setCorrectionPhase(false);
        } else {
            new Mensaje().show(Alert.AlertType.ERROR, "NO HAY MODIFICACIONES POR CANCELAR", "Realiza modificaciones en un depósito de buzón para continuar");
        }
    }

    public void setCorrectionPhase(Boolean isCorrecting) { 
        this.btnValidateDeposit.setDisable(isCorrecting);   // Deshabilita los botones de validación y modificación si se entra en modo corrección
        this.btnModifyDeposit.setDisable(isCorrecting);
        this.btnCancel.setDisable(!isCorrecting);  // Deshabilita los botones de cancelación y guardado si se sale del modo corrección
        this.btnSaveCorrections.setDisable(!isCorrecting);
        if (isCorrecting) {   //Brinda al frente del StackPane el botón adecuado en relación con el modo corrección
            this.btnSaveCorrections.toFront();
        } else {
            this.btnModifyDeposit.toFront();
        }   
    }

    public void validateDeposit() {
        BoxDeposit selectedDeposit = tbvBoxDeposits.getSelectionModel().getSelectedValue();
        if (selectedDeposit == null) {
            new Mensaje().show(Alert.AlertType.ERROR, "NO HAY UN DEPÓSITO SELECCIONADO", "Selecciona un depósito de buzón para continuar");
            return;
        }
        Affiliated depositAffiliated = null;
        for (Affiliated affiliated : this.affiliatedList) {
            if (affiliated.getFolio().equals(selectedDeposit.getAffiliatedFolio())) {
                depositAffiliated = affiliated;
                break;
            }
        }
        if (depositAffiliated == null) {
            new Mensaje().show(Alert.AlertType.ERROR, "PERSONA AFILIADA NO ENCONTRADA", "La persona afiliada que recibe el depósito no se encuentra registrada en el sistema");
            return;
        }
        for (Account account : depositAffiliated.getAccounts()) {
            if (account.getType().equals(selectedDeposit.getAccountType())) {
                account.makeTransaction(selectedDeposit);
                depositAffiliated.addSpecialTickets(1);
                this.tbvBoxDeposits.getItems().remove(selectedDeposit);
                this.boxDeposits.remove(selectedDeposit);
                this.tbvBoxDeposits.update();
                new Mensaje().show(Alert.AlertType.INFORMATION, "DEPÓSITO DE BUZÓN REALIZADO EXITOSAMENTE", "El depósito de buzón fue exitosamente completado");
                return;
            }
        }
        new Mensaje().show(Alert.AlertType.ERROR, "LA CUENTA INDICADA NO EXISTE", "La persona afiliada no posee una cuenta del tipo indicado");
    }

    public void exit() {
        getStage().close();
    }
}
