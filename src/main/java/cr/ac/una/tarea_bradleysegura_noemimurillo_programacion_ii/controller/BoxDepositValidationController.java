/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class BoxDepositValidationController extends Controller implements Initializable {

    @FXML
    private MFXTableView<BoxDeposit> depositBoxTableView;

    @FXML
    private MFXButton amountCorrectionButton;
    @FXML
    private MFXTextField amountCorrectionTextField;
    @FXML
    private MFXButton applyCorrectionButton;
    @FXML
    private MFXButton depositValidationButton;
    @FXML
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
    private MFXTableColumn tbc20000Colones;

    /**
     * Initializes the controller class.
     */
    public void initialize(URL url, ResourceBundle rb) {
        setupDepositBoxTableView();

    }

    @Override
    public void initialize() {

    }

    public void setupDepositBoxTableView() {
        this.tbcDepositId.setComparator(Comparator.comparing(BoxDeposit::getTransactionID));
        this.tbcDate.setComparator(Comparator.comparing(BoxDeposit::getTransactionTime));
        this.tbcAmount.setComparator(Comparator.comparing(BoxDeposit::getAmount));
        this.tbcAffiliated.setComparator(Comparator.comparing(BoxDeposit::getAffiliatedName));
        this.tbcFolio.setComparator(Comparator.comparing(BoxDeposit::getAffiliatedFolio));
        this.tbc5Colones.setComparator(Comparator.comparing(BoxDeposit::getCincoColonesDenomination));
        this.tbc10Colones.setComparator(Comparator.comparing(BoxDeposit::getDiezColonesDenomination));
        this.tbc25Colones.setComparator(Comparator.comparing(BoxDeposit::getVeinticincoColonesDenomination));
        this.tbc50Colones.setComparator(Comparator.comparing(BoxDeposit::getCincuentaColonesDenomination));
        this.tbc100Colones.setComparator(Comparator.comparing(BoxDeposit::getCienColonesDenomination));
        this.tbc500Colones.setComparator(Comparator.comparing(BoxDeposit::getQuinientosColonesDenomination));
        this.tbc1000Colones.setComparator(Comparator.comparing(BoxDeposit::getMilColonesDenomination));
        this.tbc2000Colones.setComparator(Comparator.comparing(BoxDeposit::getDosMilColonesDenomination));
        this.tbc5000Colones.setComparator(Comparator.comparing(BoxDeposit::getCincoMilColonesDenomination));
        this.tbc10000Colones.setComparator(Comparator.comparing(BoxDeposit::getDiezMilColonesDenomination));
        this.tbc20000Colones.setComparator(Comparator.comparing(BoxDeposit::getVeinteMilColonesDenomination));

        tbcDepositId.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getTransactionID));
        tbcDate.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getTransactionTime));
        tbcAmount.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getAmount));
        tbcAffiliated.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getAffiliatedName));
        tbcFolio.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getAffiliatedFolio));
        tbc5Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getCincoColonesDenomination));
        tbc10Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getDiezColonesDenomination));
        tbc25Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getVeinticincoColonesDenomination));
        tbc50Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getCincuentaColonesDenomination));
        tbc100Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getCienColonesDenomination));
        tbc500Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getQuinientosColonesDenomination));
        tbc1000Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getMilColonesDenomination));
        tbc2000Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getDosMilColonesDenomination));
        tbc5000Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getCincoMilColonesDenomination));
        tbc10000Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getDiezMilColonesDenomination));
        tbc20000Colones.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getVeinteMilColonesDenomination));

        //EJEMPLO
        BoxDeposit depositA = new BoxDeposit(0.d, new Affiliated("Noemi", "Murillo", "Godinez", 22, Affiliated.Sexo.FEMENINO, "Coope"), Transaction.Action.DEPOSITO);
        depositA.addDenomination(BoxDeposit.Denomination.CINCO, 50);
        depositA.addDenomination(BoxDeposit.Denomination.DIEZ, 100);
        depositA.addDenomination(BoxDeposit.Denomination.CINCUENTA, 20);
        depositA.calculateTotal();

        BoxDeposit depositB = new BoxDeposit(0.d, new Affiliated("Bradley", "Segura", "Borbon", 18, Affiliated.Sexo.MASCULINO, "Coope"), Transaction.Action.DEPOSITO);
        depositB.addDenomination(BoxDeposit.Denomination.CINCO, 10);
        depositB.addDenomination(BoxDeposit.Denomination.DIEZ, 20);
        depositB.addDenomination(BoxDeposit.Denomination.CINCUENTA, 100);
        depositB.calculateTotal();

        ArrayList<BoxDeposit> boxDepositsArray = new ArrayList();
        boxDepositsArray.add(depositA);
        boxDepositsArray.add(depositB);

        this.depositBoxTableView.setItems(FXCollections.observableArrayList(boxDepositsArray));

        //AppContext.getInstance().set("BoxDepositA", depositA);
    }

    @FXML
    public void setCorrectionElement() {
        System.out.println("Event Completed");
        if (depositBoxTableView.getSelectionModel().getSelectedValue() != null) {
            BoxDeposit selectedDeposit = (BoxDeposit) depositBoxTableView.getSelectionModel().getSelectedValue();
            amountCorrectionTextField.setText(Double.toString(selectedDeposit.getAmount()));
        }
    }

    public void applyCorrection() {
        String correctedAmount = amountCorrectionTextField.getText();
        if (depositBoxTableView.getSelectionModel().getSelectedValue() != null && !correctedAmount.isBlank()) {
            ObservableList<BoxDeposit> deposits = depositBoxTableView.getItems();
            for (BoxDeposit deposit : deposits) {
                if (deposit == depositBoxTableView.getSelectionModel().getSelectedValue()) {
                    deposit.setAmount(Double.parseDouble(correctedAmount));
                }
            }
            depositBoxTableView.setItems(deposits);
        }
    }

}
