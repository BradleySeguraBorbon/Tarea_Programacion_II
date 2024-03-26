/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Afiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.BoxDeposit;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXFilterComboBox;
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
public class MoneyManagementController extends Controller implements Initializable {

    @FXML
    private MFXFilterComboBox selectAfiliatedDepositBox;
    @FXML
    private MFXFilterComboBox selectAccountDepositBox;
    @FXML
    private MFXTextField depositAmountTextField;
    @FXML
    private MFXButton depositButton;
    @FXML
    private MFXTableView depositBoxTableView;
    @FXML
    private MFXButton depositValidationButton;

    @FXML
    private MFXFilterComboBox selectAccountComboBox;

    private ArrayList<String> afiliatedNames;
    private ArrayList<BoxDeposit> boxDeposits;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.afiliatedNames = new ArrayList();
        for (Afiliated afiliated : (ArrayList<Afiliated>) AppContext.getInstance().get("afiliated")) {
            afiliatedNames.add(afiliated.getFullName());
        }
        this.selectAfiliatedDepositBox.setItems(FXCollections.observableArrayList(afiliatedNames));
        this.boxDeposits = (ArrayList<BoxDeposit>) AppContext.getInstance().get("boxDeposits");
    }

    @Override
    public void initialize() {

    }

    public void setupDepositBoxTableView() {
        MFXTableColumn<BoxDeposit> boxDepositIDColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(BoxDeposit::getTransactionID));
        MFXTableColumn<BoxDeposit> transactionTimeColumn = new MFXTableColumn<>("Fecha", true, Comparator.comparing(BoxDeposit::getTransactionTime));
        MFXTableColumn<BoxDeposit> transactionAmountColumn = new MFXTableColumn<>("Monto", true, Comparator.comparing(BoxDeposit::getAmount));
        MFXTableColumn<BoxDeposit> transactionAfiliatedColumn = new MFXTableColumn<>("Afiliated", true, Comparator.comparing(BoxDeposit::getAfiliatedName));
        
        //Columnas de Denominación
        MFXTableColumn<BoxDeposit> cincoColonesColumn = new MFXTableColumn<>("5 COLONES", true, Comparator.comparing(BoxDeposit::getCincoColonesDenomination));
        MFXTableColumn<BoxDeposit> diezColonesColumn = new MFXTableColumn<>("10 COLONES", true, Comparator.comparing(BoxDeposit::getDiezColonesDenomination));
        MFXTableColumn<BoxDeposit> veinticincoColonesColumn = new MFXTableColumn<>("25 COLONES", true, Comparator.comparing(BoxDeposit::getVeinticincoColonesDenomination));
        MFXTableColumn<BoxDeposit> cincuentaColonesColumn = new MFXTableColumn<>("50 COLONES", true, Comparator.comparing(BoxDeposit::getCincuentaColonesDenomination));
        MFXTableColumn<BoxDeposit> cienColonesColumn = new MFXTableColumn<>("100 COLONES", true, Comparator.comparing(BoxDeposit::getCienColonesDenomination));
        MFXTableColumn<BoxDeposit> quinientosColonesColumn = new MFXTableColumn<>("500 COLONES", true, Comparator.comparing(BoxDeposit::getQuinientosColonesDenomination));
        MFXTableColumn<BoxDeposit> milColonesColumn = new MFXTableColumn<>("1000 COLONES", true, Comparator.comparing(BoxDeposit::getMilColonesDenomination));
        MFXTableColumn<BoxDeposit> dosMilColonesLabel = new MFXTableColumn<>("2000 COLONES", true, Comparator.comparing(BoxDeposit::getDosMilColonesDenomination));
        MFXTableColumn<BoxDeposit> cincoMilColonesLabel = new MFXTableColumn<>("5000 COLONES", true, Comparator.comparing(BoxDeposit::getCincoMilColonesDenomination));
        MFXTableColumn<BoxDeposit> diezMilColonesLabel = new MFXTableColumn<>("10 000 COLONES", true, Comparator.comparing(BoxDeposit::getDiezMilColonesDenomination));
        MFXTableColumn<BoxDeposit> ventieMilColonesLabel = new MFXTableColumn<>("20 000 COLONES", true, Comparator.comparing(BoxDeposit::getVeinteMilColonesDenomination));
        
        boxDepositIDColumn.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getTransactionID));
        transactionTimeColumn.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getTransactionTime));
        transactionAmountColumn.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getAmount));
        transactionAfiliatedColumn.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getAfiliatedName));
        
        cincoColonesColumn.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getCincoColonesDenomination));
        diezColonesColumn.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getDiezColonesDenomination));
        veinticincoColonesColumn.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getVeinticincoColonesDenomination));
        cincuentaColonesColumn.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getCincuentaColonesDenomination));
        cienColonesColumn.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getCienColonesDenomination));
        quinientosColonesColumn.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getQuinientosColonesDenomination));
        milColonesColumn.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getMilColonesDenomination));
        dosMilColonesLabel.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getDosMilColonesDenomination));
        cincoMilColonesLabel.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getCincoMilColonesDenomination));
        diezMilColonesLabel.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getDiezMilColonesDenomination));
        ventieMilColonesLabel.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getVeinteMilColonesDenomination));
       
        
        this.depositBoxTableView.getTableColumns().addAll(boxDepositIDColumn, transactionTimeColumn, transactionAmountColumn, transactionAfiliatedColumn,
        cincoColonesColumn, diezColonesColumn, veinticincoColonesColumn, cincuentaColonesColumn, cienColonesColumn, quinientosColonesColumn, milColonesColumn, 
        dosMilColonesLabel, cincoMilColonesLabel, diezMilColonesLabel, ventieMilColonesLabel);
        
        BoxDeposit depositA = new BoxDeposit();
        depositA.addDenomination(BoxDeposit.Denomination.CINCO, 50);
        depositA.addDenomination(BoxDeposit.Denomination.DIEZ, 100);
        depositA.addDenomination(BoxDeposit.Denomination.CINCUENTA, 20);
        depositA.calculateTotal();
        
        BoxDeposit depositB = new BoxDeposit();
        depositB.addDenomination(BoxDeposit.Denomination.CINCO, 10);
        depositB.addDenomination(BoxDeposit.Denomination.DIEZ, 20);
        depositB.addDenomination(BoxDeposit.Denomination.CINCUENTA, 100);
        depositB.calculateTotal();
        
        ArrayList<BoxDeposit> boxDepositsArray = new ArrayList();
        boxDepositsArray.add(depositA);
        boxDepositsArray.add(depositB);
        
        this.depositBoxTableView.setItems(FXCollections.observableArrayList(boxDepositsArray));
        
    }

    public void selectElement() {
        System.out.println(selectAccountComboBox.selectionProperty().getName() + " was selected");
    }

}
