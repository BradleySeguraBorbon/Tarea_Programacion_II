/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Afiliated;
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
    private MFXTableView depositBoxTableView;
    @FXML
    private MFXButton amountCorrectionButton;
    @FXML
    private MFXTextField amountCorrectionTextField;
    @FXML
    private MFXButton applyCorrectionButton;
    @FXML
    private MFXButton depositValidationButton;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        setupDepositBoxTableView();
    }    
    
    @Override
    public void initialize() {
      
    }
    
     public void setupDepositBoxTableView() {
        MFXTableColumn<BoxDeposit> boxDepositIDColumn = new MFXTableColumn<>("ID", true, Comparator.comparing(BoxDeposit::getTransactionID));
        MFXTableColumn<BoxDeposit> transactionTimeColumn = new MFXTableColumn<>("Fecha", true, Comparator.comparing(BoxDeposit::getTransactionTime));
        MFXTableColumn<BoxDeposit> transactionAmountColumn = new MFXTableColumn<>("Monto", true, Comparator.comparing(BoxDeposit::getAmount));
        MFXTableColumn<BoxDeposit> transactionAfiliatedColumn = new MFXTableColumn<>("Afiliated", true, Comparator.comparing(BoxDeposit::getAfiliatedName));
        MFXTableColumn<BoxDeposit> transactionFolioColumn = new MFXTableColumn<>("Folio", true, Comparator.comparing(BoxDeposit::getAfiliatedFolio));

        
        //Columnas de Denominaciones
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
        transactionFolioColumn.setRowCellFactory(boxDeposit -> new MFXTableRowCell<>(BoxDeposit::getAfiliatedFolio));
        
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

        this.depositBoxTableView.getTableColumns().addAll(boxDepositIDColumn, transactionTimeColumn, transactionAfiliatedColumn, transactionFolioColumn,
                cincoColonesColumn, diezColonesColumn, veinticincoColonesColumn, cincuentaColonesColumn, cienColonesColumn, quinientosColonesColumn, milColonesColumn,
                dosMilColonesLabel, cincoMilColonesLabel, diezMilColonesLabel, ventieMilColonesLabel, transactionAmountColumn);

        BoxDeposit depositA = new BoxDeposit(0.d, new Afiliated("Noemi", "Murillo", "Godinez", 22, "Coope"), Transaction.Action.DEPOSITO);
        depositA.addDenomination(BoxDeposit.Denomination.CINCO, 50);
        depositA.addDenomination(BoxDeposit.Denomination.DIEZ, 100);
        depositA.addDenomination(BoxDeposit.Denomination.CINCUENTA, 20);
        depositA.calculateTotal();

        BoxDeposit depositB = new BoxDeposit(0.d, new Afiliated("Bradley", "Segura", "Borbon", 18, "Coope"), Transaction.Action.DEPOSITO);
        depositB.addDenomination(BoxDeposit.Denomination.CINCO, 10);
        depositB.addDenomination(BoxDeposit.Denomination.DIEZ, 20);
        depositB.addDenomination(BoxDeposit.Denomination.CINCUENTA, 100);
        depositB.calculateTotal();

        ArrayList<BoxDeposit> boxDepositsArray = new ArrayList();
        boxDepositsArray.add(depositA);
        boxDepositsArray.add(depositB);

        this.depositBoxTableView.setItems(FXCollections.observableArrayList(boxDepositsArray));

        AppContext.getInstance().set("BoxDepositA", depositA);
    }
     
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
              for(BoxDeposit deposit : deposits) {
                  if(deposit == depositBoxTableView.getSelectionModel().getSelectedValue()) {
                      deposit.setAmount(Double.parseDouble(correctedAmount));
                  }
              }
              depositBoxTableView.setItems(deposits);
          }
     }

    
    
}
