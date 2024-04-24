
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Account;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.BoxDeposit;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Formato;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
import io.github.palexdev.materialfx.controls.MFXSpinner;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.models.spinner.IntegerSpinnerModel;
import io.github.palexdev.materialfx.utils.others.FunctionalStringConverter;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import static javafx.scene.input.KeyCode.ENTER;
import javafx.scene.input.KeyEvent;
import javafx.util.StringConverter;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class BoxDepositController extends Controller implements Initializable {

    @FXML
    private Label lblSelectedAffiliated;
    @FXML
    private MFXTextField txtFolio;
    @FXML
    private MFXButton btnSearchAffiliated;
    @FXML
    private ImageView imvDecoration;
    @FXML
    private MFXFilterComboBox fcbSelectAccount;
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
    private ImageView imvCincoColones;
    @FXML
    private ImageView imvDiezColones;
    @FXML
    private ImageView imvVeinticincoColones;
    @FXML
    private ImageView imvCincuentaColones;
    @FXML
    private ImageView imvCienColones;
    @FXML
    private ImageView imvQuinientosColones;
    @FXML
    private ImageView imvMilColones;
    @FXML
    private ImageView imvDosMilColones;
    @FXML
    private ImageView imvCincoMilColones;
    @FXML
    private ImageView imvDiezMilColones;
    @FXML
    private ImageView imvVeinteMilColones;
    @FXML
    private Label lblTotalAmount;
    @FXML
    private MFXButton btnDone;
    @FXML
    private MFXButton btnClose;

    private Affiliated selectedAffiliated;
    private Account selectedAccount;
    private ArrayList<BoxDeposit> boxDeposits;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        //Inicialización de modelos para los spinner
        this.spnrCincoColones.setSpinnerModel(new IntegerSpinnerModel(0));
        this.spnrDiezColones.setSpinnerModel(new IntegerSpinnerModel(0));
        this.spnrVeinticincoColones.setSpinnerModel(new IntegerSpinnerModel(0));
        this.spnrCincuentaColones.setSpinnerModel(new IntegerSpinnerModel(0));
        this.spnrCienColones.setSpinnerModel(new IntegerSpinnerModel(0));
        this.spnrQuinientosColones.setSpinnerModel(new IntegerSpinnerModel(0));
        this.spnrMilColones.setSpinnerModel(new IntegerSpinnerModel(0));
        this.spnrDosMilColones.setSpinnerModel(new IntegerSpinnerModel(0));
        this.spnrCincoMilColones.setSpinnerModel(new IntegerSpinnerModel(0));
        this.spnrDiezMilColones.setSpinnerModel(new IntegerSpinnerModel(0));
        this.spnrVeinteMilColones.setSpinnerModel(new IntegerSpinnerModel(0));

        //Inicialización de FilterComboBox para selección de cuenta
        StringConverter<Account> converter = FunctionalStringConverter.to(account -> (account == null) ? "" : account.getType());
        this.fcbSelectAccount.setConverter(converter);

        //Obtención de depósitos de buzón desde AppContext
        if (AppContext.getInstance().get("boxDeposits") != null) {
            this.boxDeposits = (ArrayList<BoxDeposit>) AppContext.getInstance().get("boxDeposits");
        } else {
            this.boxDeposits = new ArrayList<>();
        }
        
        //Inicialización de TextField para folio
        txtFolio.delegateSetTextFormatter(Formato.getInstance().capsFormat(6));
    }

    @Override
    public void initialize() {

    }

    //Se busca el afiliado
    @FXML
    public void searchAffiliated() {
        //Se limpian todos los espacios
        clear();
        //Se obtiene el afiliado ingresado por medio del FOLIO
        String typedFolio = txtFolio.getText();
        //Verifica si el folio está en blanco
        if (!typedFolio.isBlank()) {
            //Se busca el afiliado
            for (Affiliated affiliated : (ArrayList<Affiliated>) AppContext.getInstance().get("affiliated")) {
                if (typedFolio.equals(affiliated.getFolio())) {
                    //Si se encuentra el afiliado se guarda en una variable local 
                    this.selectedAffiliated = affiliated;
                    //Se despliega el nombre completo del afiliado
                    this.lblSelectedAffiliated.setText("Afiliado: " + this.selectedAffiliated.getFullName());
                    //Se despliegan las cuentas que tiene el afiliado
                    this.fcbSelectAccount.setDisable(false);
                    this.fcbSelectAccount.setItems(FXCollections.observableArrayList(this.selectedAffiliated.getAccounts()));
                    return;
                }
            }
            //Si no se encuentra en el for se despliega un mensaje de advertencia de que no se encontró
            new Mensaje().show(Alert.AlertType.WARNING, "USUARIO NO ENCONTRADO", "No se encontró el afiliado con el folio digitado");
        } else {
            //Si el textField está en blanco genera un mensaje de advertencia de que no se ha ingresado un número de folio
            new Mensaje().show(Alert.AlertType.WARNING, "FOLIO NO INGRESADO", "Completa el espacio del Folio para continuar");
        }
    }

    //Este método saca la cuenta seleccionada
    @FXML
    public void selectAccount() {
        this.selectedAccount = (Account) this.fcbSelectAccount.getSelectedItem();
    }

  
    //Este método es para actualizar el monto total cada vez que se modifica un spinner
    @FXML
    public void refreshTotal() {
        //Se obtiene el valor del spinner y se multiplica por la denomianción de moneda o billete que les corresponde
        Integer total = (spnrCincoColones.getValue() * 5) + (spnrDiezColones.getValue() * 10) + (spnrVeinticincoColones.getValue() * 25)
                + (spnrCincuentaColones.getValue() * 50) + (spnrCienColones.getValue() * 100) + (spnrQuinientosColones.getValue() * 500)
                + (spnrMilColones.getValue() * 1000) + (spnrDosMilColones.getValue() * 2000) + (spnrCincoMilColones.getValue() * 5000)
                + (spnrDiezMilColones.getValue() * 10000) + (spnrVeinteMilColones.getValue() * 20000);
        this.lblTotalAmount.setText(total.toString());
    }

    //Este método limpia todos los espacios de datos de la pantalla
    @FXML
    public void clear() {
        this.lblSelectedAffiliated.setText("");
        this.fcbSelectAccount.clearSelection();
        this.fcbSelectAccount.clear();
        this.fcbSelectAccount.setDisable(true);
        this.spnrCincoColones.setValue(0);
        this.spnrDiezColones.setValue(0);
        this.spnrVeinticincoColones.setValue(0);
        this.spnrCincuentaColones.setValue(0);
        this.spnrCienColones.setValue(0);
        this.spnrQuinientosColones.setValue(0);
        this.spnrMilColones.setValue(0);
        this.spnrDosMilColones.setValue(0);
        this.spnrCincoMilColones.setValue(0);
        this.spnrDiezMilColones.setValue(0);
        this.spnrVeinteMilColones.setValue(0);
        this.lblTotalAmount.setText("0");
    }
    
    //Este método devyuelve los spinners a 0
    @FXML
    private Boolean spinnersBlank() {
        return this.spnrCincoColones.getValue() == 0 && this.spnrDiezColones.getValue() == 0 && this.spnrVeinticincoColones.getValue() == 0 &&
                this.spnrCincuentaColones.getValue() == 0 && this.spnrCienColones.getValue() == 0 && this.spnrQuinientosColones.getValue() == 0 &&
                this.spnrMilColones.getValue() == 0 && this.spnrDosMilColones.getValue() == 0 && this.spnrCincoMilColones.getValue() == 0 &&
                this.spnrDiezMilColones.getValue() == 0 && this.spnrVeinteMilColones.getValue() == 0;
    }

    //Se guarda la información del depósito
    @FXML
    public void save() {
        //Se verifica que haya un folio, una cuenta y que los spinner no estén en blanco
        if (this.txtFolio.getText().isBlank()) {
            new Mensaje().show(Alert.AlertType.WARNING, "FOLIO NO INGRESADO", "Completa el espacio del Folio para continuar");
            this.txtFolio.requestFocus();
        } else if (this.fcbSelectAccount.getSelectedItem() == null) {
            new Mensaje().show(Alert.AlertType.WARNING, "CUENTA NO SELECCIONADA", "Selecciona la cuenta de afiliado para continuar");
            this.txtFolio.requestFocus();
        } else if (spinnersBlank()) {
            new Mensaje().show(Alert.AlertType.WARNING, "DENOMINACIONES NO INDICADAS", "Agrega las denominaciones para continuar");
            this.spnrCincoColones.requestFocus();
        } else {
            //En este hashMap se guardan todos los número que pueda tener cada spinner
            HashMap<BoxDeposit.Denomination, Integer> boxDepositDenomination = new HashMap<>();

            boxDepositDenomination.put(BoxDeposit.Denomination.CINCO, spnrCincoColones.getSpinnerModel().getValue());
            boxDepositDenomination.put(BoxDeposit.Denomination.DIEZ, spnrDiezColones.getSpinnerModel().getValue());
            boxDepositDenomination.put(BoxDeposit.Denomination.VEINTICINCO, spnrVeinticincoColones.getSpinnerModel().getValue());
            boxDepositDenomination.put(BoxDeposit.Denomination.CINCUENTA, spnrCincuentaColones.getSpinnerModel().getValue());
            boxDepositDenomination.put(BoxDeposit.Denomination.CIEN, spnrCienColones.getSpinnerModel().getValue());
            boxDepositDenomination.put(BoxDeposit.Denomination.QUINIENTOS, spnrQuinientosColones.getSpinnerModel().getValue());
            boxDepositDenomination.put(BoxDeposit.Denomination.MIL, spnrMilColones.getSpinnerModel().getValue());
            boxDepositDenomination.put(BoxDeposit.Denomination.DOSMIL, spnrDosMilColones.getSpinnerModel().getValue());
            boxDepositDenomination.put(BoxDeposit.Denomination.CINCOMIL, spnrCincoMilColones.getSpinnerModel().getValue());
            boxDepositDenomination.put(BoxDeposit.Denomination.DIEZMIL, spnrDiezMilColones.getSpinnerModel().getValue());
            boxDepositDenomination.put(BoxDeposit.Denomination.VEINTEMIL, spnrVeinteMilColones.getSpinnerModel().getValue());

            //Se crea una instancia del BoxDeposit y se le agrega el depósito
            BoxDeposit newBoxDeposit = new BoxDeposit(Double.valueOf(this.lblTotalAmount.getText()), this.selectedAffiliated.getFolio(), this.selectedAffiliated.getFullName(), this.selectedAccount.getType(), BoxDeposit.Action.DEPOSITO);
            newBoxDeposit.setDepositDenomination(boxDepositDenomination);
            //Se agrega el depósito al array local 
            this.boxDeposits.add(newBoxDeposit);
            //Se cargan al appContext
            AppContext.getInstance().set("boxDeposits", this.boxDeposits);
            //Se gener aun mensaje de que el depósito fue generado exitosamente y pronto un funcionario lo verficará
            new Mensaje().show(Alert.AlertType.INFORMATION, "DEPÓSITO GUARDADO EN EL BUZÓN EXITOSAMENTE", "Tu depósito de buzón ha sido guardado, pronto será revisado por las personas funcionarias");
            //Se limpian espacios
            clear();
            //Se cierra la ventana
            getStage().close();
        }
    }
    
    //Se cierra la ventana
    @FXML
    public void close() {
        AppContext.getInstance().set("inMainMenu", true);
        getStage().close();
    }

    /*public void update(KeyEvent event) {
        if(event.getCode() == ENTER) {
            System.out.println("VALUE: " + ((MFXSpinner)event.getSource()).textTransformerProperty().);
            //((MFXSpinner)event.getSource()).setValue(Integer.parseInt(((MFXSpinner)event.getSource()).getPromptText()));
        }
    }*/
}
