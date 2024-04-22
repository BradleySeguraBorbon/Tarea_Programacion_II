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
    //Se cargan todos los depósitos en el tableView
    public void setupTbvBoxDeposits() {
        //Se define que lleva cada una de las columnas
        this.tbcAffiliated.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getAffiliatedName));
        this.tbcFolio.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getAffiliatedFolio));
        this.tbcAccount.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getAccountType));
        this.tbcAmount.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getAmount));

        //Se define la denominación de cada spinner
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

        //Se inicializan todos los spinners en cero
        for (MFXSpinner<Integer> spinner : this.spinners.values()) {
            spinner.setSpinnerModel(new IntegerSpinnerModel(0));
        }
        //Se cargan los depósitos que hayan realizado los usuarios en la tableView
        if (AppContext.getInstance().get("boxDeposits") != null) {
            boxDeposits = (ArrayList<BoxDeposit>) AppContext.getInstance().get("boxDeposits");
            this.tbvBoxDeposits.setItems(FXCollections.observableArrayList(boxDeposits));
        }
    }

    //En caso de que se deba modificar un depósito
    public void modifyDeposit() {
        //Se toma el depósito seleccionado desde la tableView
        selectedDeposit = this.tbvBoxDeposits.getSelectionModel().getSelectedValue();
        //Se verifica que haya un deposito seleccionado
        if (selectedDeposit != null) {
            //Se cargan los valores correspondientes en los spinners
            for (BoxDeposit.Denomination denomination : this.spinners.keySet()) {
                spinners.get(denomination).setDisable(false);
                spinners.get(denomination).setValue(this.selectedDeposit.getSpecificDenomination(denomination));
            }
            // Habilita o deshabilita botones para realizar correcciones
            setCorrectionPhase(true); 
        } else {
            //Si no hay deposito seleccionado se genera un mensaje 
            new Mensaje().show(Alert.AlertType.WARNING, "NO HAY DEPÓSITO SELECCIONADO", "Selecciona un depósito de la lista para corregir su monto");
        }
    }

    //Guardar en caso de que se modifique algun spinner
    public void saveCorrections() {
        //Se carga el seleccionado desde el tableView
        selectedDeposit = this.tbvBoxDeposits.getSelectionModel().getSelectedValue();
        //Se verifica que haya un seleccionado
        if (selectedDeposit != null) {
            //Se cargan los valores de los spinners modificados
            for (BoxDeposit.Denomination denomination : this.spinners.keySet()) {
                this.selectedDeposit.setDenomination(denomination, (Integer) spinners.get(denomination).getSpinnerModel().getValue());
                //Se deshabilitan los spinners
                spinners.get(denomination).setDisable(true);
            }
            //Se corrigen los botones
            setCorrectionPhase(false);
        }
        //Se calcula el total del deposito
        this.selectedDeposit.calculateTotal();
        //Se actualiza la tabla para eliminar el deposito verificado
        this.tbvBoxDeposits.update();
    }

    //Método para el boton de cancelar
    public void cancelCorrections() {
        if (!btnSaveCorrections.isDisable()) {
            //Devuelve los spinner a cero
            for (MFXSpinner spinner : this.spinners.values()) {
                spinner.setDisable(true);
                spinner.setValue(0);
            }
            //Este método corrige los botones
            setCorrectionPhase(false);
        } else {
            new Mensaje().show(Alert.AlertType.ERROR, "NO HAY MODIFICACIONES POR CANCELAR", "Realiza modificaciones en un depósito de buzón para continuar");
        }
    }

    //Este método habilita o deshabilita los botones según sea necesario
    public void setCorrectionPhase(Boolean isCorrecting) { 
        // Deshabilita los botones de validación y modificación si se entra en modo corrección
        this.btnValidateDeposit.setDisable(isCorrecting);   
        this.btnModifyDeposit.setDisable(isCorrecting);
        // Deshabilita los botones de cancelación y guardado si se sale del modo corrección
        this.btnCancel.setDisable(!isCorrecting);  
        this.btnSaveCorrections.setDisable(!isCorrecting);
        //Brinda al frente del StackPane el botón adecuado en relación con el modo corrección
        if (isCorrecting) {   
            this.btnSaveCorrections.toFront();
        } else {
            this.btnModifyDeposit.toFront();
        }   
    }

    //Este método es para el boton de validar
    public void validateDeposit() {
        //Toma el depósito seleccionado de tableView
        BoxDeposit selectedDeposit = tbvBoxDeposits.getSelectionModel().getSelectedValue();
        //Se valida que exista un seleccionado
        if (selectedDeposit == null) {
            //Si  no hay un deposito seleccionado entonces se despliega un mensaje 
            new Mensaje().show(Alert.AlertType.ERROR, "NO HAY UN DEPÓSITO SELECCIONADO", "Selecciona un depósito de buzón para continuar");
            return;
        }
        //Se busca el afiliado 
        Affiliated depositAffiliated = null;
        for (Affiliated affiliated : this.affiliatedList) {
            //Si el folio del afiliado coincide con el folio del deposito
            if (affiliated.getFolio().equals(selectedDeposit.getAffiliatedFolio())) {
                depositAffiliated = affiliated;
                break;
            }
        }
        //En caso de que el depósito no exista
        if (depositAffiliated == null) {
            //Se despliega un mensaje 
            new Mensaje().show(Alert.AlertType.ERROR, "PERSONA AFILIADA NO ENCONTRADA", "La persona afiliada que recibe el depósito no se encuentra registrada en el sistema");
            return;
        }
        //Se busca la cuenat del depósito
        for (Account account : depositAffiliated.getAccounts()) {
            //Si el nombre de la cuenta coincide con el nombre de la cuenta del depósito
            if (account.getType().equals(selectedDeposit.getAccountType())) {
                //Se le mete el depósito a la cuenta 
                account.makeTransaction(selectedDeposit);
                //Se le asignan los tickets para la rifa 
                depositAffiliated.addSpecialTickets(1);
                //Se quita el depósito de la tableview porque ya está listo 
                this.tbvBoxDeposits.getItems().remove(selectedDeposit);
                //Se elimina del array de depositos por hacer también 
                this.boxDeposits.remove(selectedDeposit);
                //Se vuelve a cargar la table
                this.tbvBoxDeposits.update();
                //Se muestra un mensaje de que el depósito ha sido depositado exitosamente
                new Mensaje().show(Alert.AlertType.INFORMATION, "DEPÓSITO DE BUZÓN REALIZADO EXITOSAMENTE", "El depósito de buzón fue exitosamente completado");
                return;
            }
        }
        //Si en el for no se salió del método entonces tirará un mensaje advirtiendo que la cuenta no existe porque no se encontró en el for
        new Mensaje().show(Alert.AlertType.ERROR, "LA CUENTA INDICADA NO EXISTE", "La persona afiliada no posee una cuenta del tipo indicado");
    }

    //Se cierra la ventana
    public void exit() {
        getStage().close();
    }
}
