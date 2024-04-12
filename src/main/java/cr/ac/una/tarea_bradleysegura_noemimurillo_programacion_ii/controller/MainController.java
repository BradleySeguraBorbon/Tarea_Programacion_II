/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.DataManager;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Paths;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bradley
 */

public class MainController extends Controller implements Initializable {

    //Universal Attributes
    DataManager dataBank;
    private static String cooperativeName;
    @FXML
    private StackPane mainStackPane;
    @FXML
    private HBox mainHBox;
    @FXML
    private VBox mainVBox;
    @FXML
    private Label lblCooperativeName;
    @FXML
    private ImageView imvCooperativeLogo;
    @FXML
    private MFXButton btnExit;

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
    private Button openAffiliatedRegisterButton;
    @FXML
    private Button openCardPrintButton;
    @FXML
    private Button openAccountOpeningButton;
    @FXML
    private Button openMoneyManagementButton;
    @FXML
    private ImageView mainIconImageView;
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
        String currentDirectory = System.getProperty("user.dir");
        String relativePath = "src/main/java/cr/ac/una/tarea_bradleysegura_noemimurillo_programacion_ii/service/SystemData.json";
        String absolutePath = Paths.get(currentDirectory, relativePath).toString();
        System.out.println(absolutePath);
        try {
            if (new File(absolutePath).isFile()) {
                dataBank = DataManager.load(absolutePath);
                dataBank.unpackData();
                System.out.println(dataBank.getAvailableAccounts());
                //cooperativeNameLabel.setText((String) AppContext.getInstance().get("cooperativeName"));
                //cooperativeLogoImageView.setImage(new Image(App.class.getResource("resources/" + AppContext.getInstance().get("cooperativeIcon")).toString()));
                System.out.println("SAVED DATAMANAGER WAS LOADED");

            } else {
                dataBank = new DataManager();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        AppContext.getInstance().set("User", "Officers");
        setTeachersMainView(false);
        setOfficersMainView(false);
        setAffiliatedMainView(false);
        switch((String)AppContext.getInstance().get("User")) {
            case "Teachers" -> setTeachersMainView(true);
            case "Officers" -> setOfficersMainView(true);
           default -> setAffiliatedMainView(true);        
        }
    }

    @Override
    public void initialize() {

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
    }

    public void openCooperativeManagementView() {
        FlowController.getInstance().goView("CooperativeManagementView");
    }

    public void setCooperativeLogo(Image imgCooperativeLogo) {
        this.imvCooperativeLogo.setImage(imgCooperativeLogo);
        System.out.println("Coop Logo Modified");
    }

    public void setCooperativeName(String newCooperativeName) {
        lblCooperativeName.setText(cooperativeName);
        System.out.println("Coop Name Modified");
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
        openAffiliatedRegisterButton.setDisable(!state);
        openAffiliatedRegisterButton.setOpacity(state ? 1 : 0);
        openCardPrintButton.setDisable(!state);
        openCardPrintButton.setOpacity(state ? 1 : 0);
        openAccountOpeningButton.setDisable(!state);
        openAccountOpeningButton.setOpacity(state ? 1 : 0);
        openMoneyManagementButton.setDisable(!state);
        openMoneyManagementButton.setOpacity(state ? 1 : 0);
        mainIconImageView.setOpacity(state ? 1 : 0);
        officersWelcomeLabel.setOpacity(state ? 1 : 0);
    }

    public void openRegistryManager() {
        FlowController.getInstance().goView("RegistryManagerView");
    }

    public void openCardPrint() {
        FlowController.getInstance().goView("CardPrintView");
    }

    public void openAccountOpening() {
        FlowController.getInstance().goViewInWindowModal("AffiliatedSelectionView", null, false);
        if (AppContext.getInstance().get("selectedAffiliated") != null) {
            FlowController.getInstance().goView("AccountOpeningView");
        }
    }

    public void openMoneyManagement() {
        FlowController.getInstance().goView("MoneyManagementView");
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
    }

    public void openAccountStatement() {

    }

    public void openDepositBox() {
        FlowController.getInstance().goViewInWindowModal("BoxDepositView", getStage(), true);
    }

    //Universal Methods
    public void exit() throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        String relativePath = "src/main/java/cr/ac/una/tarea_bradleysegura_noemimurillo_programacion_ii/service/SystemData.json";
        String absolutePath = Paths.get(currentDirectory, relativePath).toString();
        this.dataBank.save(absolutePath);
        if (this.mainStackPane.getChildren().contains(this.vboxTeachers)) {
            ((Stage) (this.btnExit.getScene().getWindow())).close();
        } else {
            FlowController.getInstance().goMain();
        }
    }

}