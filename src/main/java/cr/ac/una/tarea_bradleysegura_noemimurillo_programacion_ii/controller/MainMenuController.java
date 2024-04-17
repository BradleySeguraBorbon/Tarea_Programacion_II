/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class MainMenuController extends Controller implements Initializable {

    @FXML
    private StackPane mainStackPane;

    //TeachersView Components
    @FXML
    private VBox vboxTeachers;
    @FXML
    private Label lblTeachersWelcome;
    @FXML
    private ImageView imvTeachersWelcome;
    @FXML
    private MFXButton btnOpenAccountManagement;
    @FXML
    private MFXButton btnOpenCooperativeManagement;

    //OfficersView Components
    @FXML
    private HBox hboxOfficers;
    @FXML
    private Button btnOpenAffiliatedRegister;
    @FXML
    private Button btnOpenCardPrint;
    @FXML
    private Button btnOpenAccountOpening;
    @FXML
    private Button btnOpenMoneyManagement;
    @FXML
    private ImageView imvOfficersWelcome;
    @FXML
    private Label officersWelcomeLabel;

    //AffiliatedView Components
    @FXML
    private VBox vboxAffiliated;
    @FXML
    private MFXButton btnOpenRegisterView;
    @FXML
    private MFXButton btnOpenAccountStatement;
    @FXML
    private MFXButton btnOpenDepositBox;
    @FXML
    private ImageView imvWelcomeLeft;
    @FXML
    private ImageView imvWelcomeRight;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        initialize();
    }

    @Override
    public void initialize() {
        AppContext.getInstance().set("User", "Officers");
        setTeachersMainView(false);
        setOfficersMainView(false);
        setAffiliatedMainView(false);
        switch ((String) AppContext.getInstance().get("User")) {
            case "Teachers" ->
                setTeachersMainView(true);
            case "Officers" ->
                setOfficersMainView(true);
            default ->
                setAffiliatedMainView(true);
        }
    }

    //TeachersView Methods
    public void setTeachersMainView(Boolean state) {
        if (state) {
            vboxTeachers.toFront();
            vboxTeachers.setOpacity(1);
        } else {
            vboxTeachers.toBack();
            vboxTeachers.setOpacity(0);
        }
        lblTeachersWelcome.setOpacity(state ? 1 : 0);
        imvTeachersWelcome.setOpacity(state ? 1 : 0);
        btnOpenAccountManagement.setDisable(!state);
        btnOpenAccountManagement.setOpacity(state ? 1 : 0);
        btnOpenCooperativeManagement.setDisable(!state);
        btnOpenCooperativeManagement.setOpacity(state ? 1 : 0);
    }

    public void openAccountTypeManagementView() {
        FlowController.getInstance().goView("AccountTypeManagementView");
        AppContext.getInstance().set("inMainMenu", false);
    }

    public void openCooperativeManagementView() {
        FlowController.getInstance().goView("CooperativeManagementView");
        AppContext.getInstance().set("inMainMenu", false);
    }

    //OfficersView Methods
    public void setOfficersMainView(Boolean state) {
        if (state) {
            hboxOfficers.toFront();
            hboxOfficers.setOpacity(1);
        } else {
            hboxOfficers.toBack();
            hboxOfficers.setOpacity(0);
        }
        btnOpenAffiliatedRegister.setDisable(!state);
        btnOpenAffiliatedRegister.setOpacity(state ? 1 : 0);
        btnOpenCardPrint.setDisable(!state);
        btnOpenCardPrint.setOpacity(state ? 1 : 0);
        btnOpenAccountOpening.setDisable(!state);
        btnOpenAccountOpening.setOpacity(state ? 1 : 0);
        btnOpenMoneyManagement.setDisable(!state);
        btnOpenMoneyManagement.setOpacity(state ? 1 : 0);
        imvOfficersWelcome.setOpacity(state ? 1 : 0);
        officersWelcomeLabel.setOpacity(state ? 1 : 0);
    }

    public void openRegistryManager() {
        FlowController.getInstance().goView("RegistryManagerView");
        AppContext.getInstance().set("inMainMenu", false);
    }

    public void openCardPrint() {
        FlowController.getInstance().goView("CardPrintView");
        AppContext.getInstance().set("inMainMenu", false);
    }

    public void openAccountOpening() {
        FlowController.getInstance().goViewInWindowModal("AffiliatedSelectionView", null, false);
        if (AppContext.getInstance().get("selectedAffiliated") != null) {
            FlowController.getInstance().goView("AccountOpeningView");
            AppContext.getInstance().set("inMainMenu", false);
        }
    }

    public void openMoneyManagement() {
        FlowController.getInstance().goView("MoneyManagementView");
        AppContext.getInstance().set("inMainMenu", false);
    }

    //AffiliatedView Methods
    public void setAffiliatedMainView(Boolean state) {
        if (state) {
            vboxAffiliated.toFront();
            vboxAffiliated.setOpacity(1);
        } else {
            vboxAffiliated.toBack();
            vboxAffiliated.setOpacity(0);
        }
        btnOpenRegisterView.setDisable(!state);
        btnOpenRegisterView.setOpacity(state ? 1 : 0);
        btnOpenAccountStatement.setDisable(!state);
        btnOpenAccountStatement.setOpacity(state ? 1 : 0);
        btnOpenDepositBox.setDisable(!state);
        btnOpenDepositBox.setOpacity(state ? 1 : 0);
        imvWelcomeLeft.setOpacity(state ? 1 : 0);
        imvWelcomeRight.setOpacity(state ? 1 : 0);
    }

    public void openRegisterView() {
        FlowController.getInstance().goView("AffiliatedRegisterView");
        AppContext.getInstance().set("inMainMenu", false);
    }

    public void openAccountStatement() {

    }

    public void openDepositBox() {
        FlowController.getInstance().goViewInWindowModal("BoxDepositView", getStage(), true);
        AppContext.getInstance().set("inMainMenu", false);
    }

}
