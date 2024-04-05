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
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class BoxDepositValidationController extends Controller implements Initializable {

    /*@FXML
    private MFXTableView<BoxDeposit> tbvBoxDeposits;
    @FXML
    private MFXButton amountCorrectionButton;
    @FXML
    private MFXTextField amountCorrectionTextField;
    @FXML
    private MFXButton applyCorrectionButton;*/
    @FXML
    private MFXButton depositValidationButton;
    @FXML
    private MFXButton btnClose;
    /*@FXML
    private MFXTableColumn tbcDepositId;
    @FXML
    private MFXTableColumn tbcDate;
    @FXML
    private MFXTableColumn tbcAmount;
    @FXML
    private MFXTableColumn tbcAffiliated;
    @FXML
    private MFXTableColumn tbcFolio;
    @FXML
    private MFXTableColumn tbc5Colones;
    @FXML
    private MFXTableColumn tbc10Colones;
    @FXML
    private MFXTableColumn tbc25Colones;
    @FXML
    private MFXTableColumn tbc50Colones;
    @FXML
    private MFXTableColumn tbc100Colones;
    @FXML
    private MFXTableColumn tbc500Colones;
    @FXML
    private MFXTableColumn tbc1000Colones;
    @FXML
    private MFXTableColumn tbc2000Colones;
    @FXML
    private MFXTableColumn tbc5000Colones;
    @FXML
    private MFXTableColumn tbc10000Colones;
    @FXML
    private MFXTableColumn tbc20000Colones;*/

    @FXML
    private TableView<BoxDeposit> tbvBoxDeposits;
    @FXML
    private TableColumn<BoxDeposit, String> tbcDepositId;
    @FXML
    private TableColumn<BoxDeposit, String> tbcDate;
    @FXML
    private TableColumn<BoxDeposit, String> tbcAmount;
    @FXML
    private TableColumn<BoxDeposit, String> tbcAffiliated;
    @FXML
    private TableColumn<BoxDeposit, String> tbcFolio;
    @FXML
    private TableColumn<BoxDeposit, String> tbc5Colones;
    @FXML
    private TableColumn<BoxDeposit, String> tbc10Colones;
    @FXML
    private TableColumn<BoxDeposit, String> tbc25Colones;
    @FXML
    private TableColumn<BoxDeposit, String> tbc50Colones;
    @FXML
    private TableColumn<BoxDeposit, String> tbc100Colones;
    @FXML
    private TableColumn<BoxDeposit, String> tbc500Colones;
    @FXML
    private TableColumn<BoxDeposit, String> tbc1000Colones;
    @FXML
    private TableColumn<BoxDeposit, String> tbc2000Colones;
    @FXML
    private TableColumn<BoxDeposit, String> tbc5000Colones;
    @FXML
    private TableColumn<BoxDeposit, String> tbc10000Colones;
    @FXML
    private TableColumn<BoxDeposit, String> tbc20000Colones;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        setupTbvBoxDeposits();
    }

    @Override
    public void initialize() {

    }

    public void setupTbvBoxDeposits() {
        this.tbcDepositId.setCellValueFactory(new PropertyValueFactory<BoxDeposit, String>("transactionID"));
        this.tbcDate.setCellValueFactory(new PropertyValueFactory<BoxDeposit, String>("transactionTime"));
        this.tbcAmount.setCellValueFactory(new PropertyValueFactory<BoxDeposit, String>("amount"));
        this.tbcAffiliated.setCellValueFactory(boxDeposit -> new SimpleStringProperty(boxDeposit.getValue().getAffiliated().getFullName()));
        this.tbcFolio.setCellValueFactory(boxDeposit -> new SimpleStringProperty(boxDeposit.getValue().getAffiliated().getFolio()));

        this.tbc5Colones.setCellValueFactory(new DenominationCellValueFactory(BoxDeposit.Denomination.CINCO));
        this.tbc10Colones.setCellValueFactory(new DenominationCellValueFactory(BoxDeposit.Denomination.DIEZ));
        this.tbc25Colones.setCellValueFactory(new DenominationCellValueFactory(BoxDeposit.Denomination.VEINTICINCO));
        this.tbc50Colones.setCellValueFactory(new DenominationCellValueFactory(BoxDeposit.Denomination.CINCUENTA));
        this.tbc100Colones.setCellValueFactory(new DenominationCellValueFactory(BoxDeposit.Denomination.CIEN));
        this.tbc500Colones.setCellValueFactory(new DenominationCellValueFactory(BoxDeposit.Denomination.QUINIENTOS));
        this.tbc1000Colones.setCellValueFactory(new DenominationCellValueFactory(BoxDeposit.Denomination.MIL));
        this.tbc2000Colones.setCellValueFactory(new DenominationCellValueFactory(BoxDeposit.Denomination.DOSMIL));
        this.tbc5000Colones.setCellValueFactory(new DenominationCellValueFactory(BoxDeposit.Denomination.CINCOMIL));
        this.tbc10000Colones.setCellValueFactory(new DenominationCellValueFactory(BoxDeposit.Denomination.DIEZMIL));
        this.tbc20000Colones.setCellValueFactory(new DenominationCellValueFactory(BoxDeposit.Denomination.VEINTEMIL));

        //EJEMPLO
        BoxDeposit depositA = new BoxDeposit(0.d, new Affiliated("Noemi", "Murillo", "Godinez", 22, Affiliated.Sexo.FEMENINO, "Coope"), new Account("Prueba"), Transaction.Action.DEPOSITO);
        depositA.setDenomination(BoxDeposit.Denomination.CINCO, 50);
        depositA.setDenomination(BoxDeposit.Denomination.DIEZ, 100);
        depositA.setDenomination(BoxDeposit.Denomination.CINCUENTA, 20);
        depositA.calculateTotal();

        BoxDeposit depositB = new BoxDeposit(0.d, new Affiliated("Bradley", "Segura", "Borbon", 18, Affiliated.Sexo.MASCULINO, "Coope"), new Account("Prueba"), Transaction.Action.DEPOSITO);
        depositB.setDenomination(BoxDeposit.Denomination.CINCO, 10);
        depositB.setDenomination(BoxDeposit.Denomination.DIEZ, 20);
        depositB.setDenomination(BoxDeposit.Denomination.CINCUENTA, 100);
        depositB.calculateTotal();

        ArrayList<BoxDeposit> boxDepositsArray = new ArrayList();
        boxDepositsArray.add(depositA);
        boxDepositsArray.add(depositB);

        //FIN EJEMPLO
        this.tbvBoxDeposits.setItems(FXCollections.observableArrayList(boxDepositsArray));
        this.tbvBoxDeposits.setEditable(true);
        this.tbc5Colones.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tbc10Colones.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tbc25Colones.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tbc50Colones.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tbc100Colones.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tbc500Colones.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tbc1000Colones.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tbc2000Colones.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tbc5000Colones.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tbc10000Colones.setCellFactory(TextFieldTableCell.forTableColumn());
        this.tbc20000Colones.setCellFactory(TextFieldTableCell.forTableColumn());
    }

    public class DenominationCellValueFactory implements Callback<TableColumn.CellDataFeatures<BoxDeposit, String>, ObservableValue<String>> {

        private final BoxDeposit.Denomination denomination;

        public DenominationCellValueFactory(BoxDeposit.Denomination denomination) {
            this.denomination = denomination;
        }

        @Override
        public ObservableValue<String> call(TableColumn.CellDataFeatures<BoxDeposit, String> parameter) {
            BoxDeposit boxDeposit = parameter.getValue();
            if (boxDeposit != null && boxDeposit.getDepositDenomination().containsKey(denomination)) {
                return new SimpleStringProperty(String.valueOf(boxDeposit.getDepositDenomination().get(denomination)));
            } else {
                return new SimpleStringProperty("");
            }
        }
    }

    public void modifyDenomination(CellEditEvent edittedCell) {
        BoxDeposit selectedDeposit = tbvBoxDeposits.getSelectionModel().getSelectedItem();
        BoxDeposit.Denomination selectedDenomination = null;

        switch (edittedCell.getTableColumn().getId()) {
            case "tbc5Colones" -> selectedDenomination = BoxDeposit.Denomination.CINCO;
            case "tbc10Colones" -> selectedDenomination = BoxDeposit.Denomination.DIEZ;
            case "tbc25Colones" -> selectedDenomination = BoxDeposit.Denomination.VEINTICINCO;
            case "tbc50Colones" -> selectedDenomination = BoxDeposit.Denomination.CINCUENTA;
            case "tbc100Colones" -> selectedDenomination = BoxDeposit.Denomination.CIEN;
            case "tbc500Colones" -> selectedDenomination = BoxDeposit.Denomination.QUINIENTOS;
            case "tbc1000Colones" -> selectedDenomination = BoxDeposit.Denomination.MIL;
            case "tbc2000Colones" -> selectedDenomination = BoxDeposit.Denomination.DOSMIL;
            case "tbc5000Colones" -> selectedDenomination = BoxDeposit.Denomination.CINCOMIL;
            case "tbc10000Colones" -> selectedDenomination = BoxDeposit.Denomination.DIEZMIL;
            case "tbc20000Colones" -> selectedDenomination = BoxDeposit.Denomination.VEINTEMIL;
        }
        selectedDeposit.setDenomination(selectedDenomination, Integer.valueOf(edittedCell.getNewValue().toString()));
        selectedDeposit.calculateTotal();     
        this.tbvBoxDeposits.refresh();
        System.out.println(selectedDeposit.toString());
    }
    
    public void close() {
        getStage().close();
    }

//    public void setuptbvBoxDeposits() {
//        this.tbcDepositId.setComparator(Comparator.comparing(BoxDeposit::getTransactionID));
//        this.tbcDate.setComparator(Comparator.comparing(BoxDeposit::getTransactionTime));
//        this.tbcAmount.setComparator(Comparator.comparing(BoxDeposit::getAmount));
//        this.tbcAffiliated.setComparator(Comparator.comparing(BoxDeposit::getAffiliatedName));
//        this.tbcFolio.setComparator(Comparator.comparing(BoxDeposit::getAffiliatedFolio));
//        this.tbc5Colones.setComparator(Comparator.comparing(BoxDeposit::getCincoColonesDenomination));
//        this.tbc10Colones.setComparator(Comparator.comparing(BoxDeposit::getDiezColonesDenomination));
//        this.tbc25Colones.setComparator(Comparator.comparing(BoxDeposit::getVeinticincoColonesDenomination));
//        this.tbc50Colones.setComparator(Comparator.comparing(BoxDeposit::getCincuentaColonesDenomination));
//        this.tbc100Colones.setComparator(Comparator.comparing(BoxDeposit::getCienColonesDenomination));
//        this.tbc500Colones.setComparator(Comparator.comparing(BoxDeposit::getQuinientosColonesDenomination));
//        this.tbc1000Colones.setComparator(Comparator.comparing(BoxDeposit::getMilColonesDenomination));
//        this.tbc2000Colones.setComparator(Comparator.comparing(BoxDeposit::getDosMilColonesDenomination));
//        this.tbc5000Colones.setComparator(Comparator.comparing(BoxDeposit::getCincoMilColonesDenomination));
//        this.tbc10000Colones.setComparator(Comparator.comparing(BoxDeposit::getDiezMilColonesDenomination));
//        this.tbc20000Colones.setComparator(Comparator.comparing(BoxDeposit::getVeinteMilColonesDenomination));
//
//        tbcDepositId.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getTransactionID));
//        tbcDate.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getTransactionTime));
//        tbcAmount.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getAmount));
//        tbcAffiliated.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getAffiliatedName));
//        tbcFolio.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getAffiliatedFolio));
//        tbc5Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getCincoColonesDenomination));
//        tbc10Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getDiezColonesDenomination));
//        tbc25Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getVeinticincoColonesDenomination));
//        tbc50Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getCincuentaColonesDenomination));
//        tbc100Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getCienColonesDenomination));
//        tbc500Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getQuinientosColonesDenomination));
//        tbc1000Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getMilColonesDenomination));
//        tbc2000Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getDosMilColonesDenomination));
//        tbc5000Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getCincoMilColonesDenomination));
//        tbc10000Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getDiezMilColonesDenomination));
//        tbc20000Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getVeinteMilColonesDenomination));
//
//        //EJEMPLO
//        BoxDeposit depositA = new BoxDeposit(0.d, new Affiliated("Noemi", "Murillo", "Godinez", 22, Affiliated.Sexo.FEMENINO, "Coope"), new Account("Prueba"), Transaction.Action.DEPOSITO);
//        depositA.setDenomination(BoxDeposit.Denomination.CINCO, 50);
//        depositA.setDenomination(BoxDeposit.Denomination.DIEZ, 100);
//        depositA.setDenomination(BoxDeposit.Denomination.CINCUENTA, 20);
//        depositA.calculateTotal();
//
//        BoxDeposit depositB = new BoxDeposit(0.d, new Affiliated("Bradley", "Segura", "Borbon", 18, Affiliated.Sexo.MASCULINO, "Coope"), new Account("Prueba"), Transaction.Action.DEPOSITO);
//        depositB.setDenomination(BoxDeposit.Denomination.CINCO, 10);
//        depositB.setDenomination(BoxDeposit.Denomination.DIEZ, 20);
//        depositB.setDenomination(BoxDeposit.Denomination.CINCUENTA, 100);
//        depositB.calculateTotal();
//
//        ArrayList<BoxDeposit> boxDepositsArray = new ArrayList();
//        boxDepositsArray.add(depositA);
//        boxDepositsArray.add(depositB);
//
//        this.tbvBoxDeposits.setItems(FXCollections.observableArrayList(boxDepositsArray));
//        //AppContext.getInstance().set("BoxDepositA", depositA);
//    }
//
//    @FXML
//    public void setCorrectionElement() {
//        if (tbvBoxDeposits.getSelectionModel().getSelectedValue() != null) {
//            BoxDeposit selectedDeposit = (BoxDeposit) tbvBoxDeposits.getSelectionModel().getSelectedValue();
//            amountCorrectionTextField.setText(Double.toString(selectedDeposit.getAmount()));
//        }
//    }
//
//    public void applyCorrection() {
//        String correctedAmount = amountCorrectionTextField.getText();
//        if (tbvBoxDeposits.getSelectionModel().getSelectedValue() != null && !correctedAmount.isBlank()) {
//            ObservableList<BoxDeposit> deposits = tbvBoxDeposits.getItems();
//            for (BoxDeposit deposit : deposits) {
//                if (deposit == tbvBoxDeposits.getSelectionModel().getSelectedValue()) {
//                    deposit.setAmount(Double.parseDouble(correctedAmount));
//                }
//            }
//            tbvBoxDeposits.setItems(deposits);
//        }
//    }
//
}
