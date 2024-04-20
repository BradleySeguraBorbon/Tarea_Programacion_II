/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.ImageConverter;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated.Sexo;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXSpinner;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.controls.models.spinner.IntegerSpinnerModel;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Comparator;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Toggle;
import javafx.stage.FileChooser;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class RegistryManagerController extends Controller implements Initializable {

    @FXML
    private MFXButton btnAddUser; //
    @FXML
    private MFXTextField txtSurname;//
    @FXML
    private MFXTextField txtName;//
    @FXML
    private MFXTextField txtSecondSurname;//
    @FXML
    private MFXSpinner<Integer> spnAge;//
    @FXML
    private ToggleGroup IdentityGroup;//
    @FXML
    private MFXButton btnDeleteUser;//
    @FXML
    private MFXButton btnSaveChanges;//
    @FXML
    private MFXButton UpImage;//
    @FXML
    private MFXTableView tbvUsersList;
    @FXML
    private MFXTableColumn<Affiliated> tbcAffiliated;
    @FXML
    private MFXTableColumn<Affiliated> tbcFolio;
    @FXML
    private ImageView imgvUsersFace;//

    ArrayList<Affiliated> newAffiliates;
    String convertedImg = "";

    /**
     * Initializablelizes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // this.newAffiliates = (ArrayList<Affiliated>) AppContext.getInstance().get("afiliated");
        this.spnAge.setSpinnerModel(new IntegerSpinnerModel(0));
        initialize();

    }

    @Override
    public void initialize() {
        if (AppContext.getInstance().get("affiliated") != null) {
            newAffiliates = (ArrayList<Affiliated>) AppContext.getInstance().get("affiliated");
            setuptbvUsersList();
        }

    }

    public void saveNewImage() {
        try {
            // Crear un FileChooser para seleccionar el archivo de imagen
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar Imagen");

            // Filtrar solo archivos de imagen
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.gif");
            fileChooser.getExtensionFilters().add(extFilter);

            // Mostrar el diálogo de selección de archivo
            File file = fileChooser.showOpenDialog(null);

            BufferedImage image = ImageIO.read(file);
            // Verificar si se seleccionó un archivo
            if (file != null) {
                // Crear una imagen a partir del archivo seleccionado
                //Image image = new Image(file.toURI().toString());
                //String image64 = file.getAbsolutePath();
                //String image64 = "C:\\Users\\Fiorella\\Pictures\\20181031_170733.jpg";
                //convertedImg = convertImageToBase64(image64);

                this.convertedImg = ImageConverter.toBase64(image, ImageConverter.getFormat(file));
                System.out.println(ImageConverter.getFormat(file));

                Image bs64ToImg = ImageConverter.fromBase64(this.convertedImg);
                // Mostrar la imagen en el ImageView
                imgvUsersFace.setImage(bs64ToImg);
            }

        } catch (IOException E) {

        }

    }

    public void addNewUser() {
        btnDeleteUser.setDisable(false);
        btnSaveChanges.setDisable(false);
        //Se crea una instancia de mensaje para las advertencias en caso de que algún espacio esté vació o si se agregó el usuario exitosamente.
        Mensaje msj = new Mensaje();

        //Estos if validan si los espacios están llenos y si no se salen del método.
        if (convertedImg.equals("")) {
            msj.show(ERROR, "Imagen vacía", "La imagen del nuevo usuario está vacía");
            return;
        }
        if (txtName.getText().equals("")) {
            msj.show(ERROR, "Nombre vacío", "La casilla de nombre del nuevo usuario está vacía");
            return;
        }
        if (txtSurname.getText().equals("")) {
            msj.show(ERROR, "Primer apellido vacío", "La casilla del primer apellido del nuevo usuario está vacía");
            return;
        }
        if (txtSecondSurname.getText().equals("")) {
            msj.show(ERROR, "Segundo apellido vacío", "La casilla del segundo apellido del nuevo usuario está vacía");
            return;
        }
        //Falta EDAD

        if (getNewSex() == null) {
            msj.show(ERROR, "Sexo vacío", "La casilla de sexo del nuevo usuario está vacía");
            return;
        }
        Affiliated nuevo = new Affiliated(txtName.getText(), txtSurname.getText(), txtSecondSurname.getText(), spnAge.getValue(), getNewSex(), (String) AppContext.getInstance().get("cooperativeName"), this.convertedImg);
        //Si todos los espacios están llenos, se salta los if y se crea el nuevo usuario
        tbvUsersList.getItems().add(nuevo);
        //Affiliated actualUser = newAffiliates.getLast();

        //Mensaje que indica el folio del nuevo usuario.
        msj.show(INFORMATION, "Nuevo Folio", "El folio del nuevo usuario es: " + nuevo.getFolio());
        //Mensaje de nuevo usuario agregado exitosamente.
        msj.show(INFORMATION, "Nuevo Afiliado", "¡Se ha añadido un nuevo afiliado exitosamente!");

        clean();
        this.tbvUsersList.setItems(FXCollections.observableArrayList(this.newAffiliates));
        this.tbvUsersList.update();
    }

    public Sexo getNewSex() {
        Toggle seleccionado = IdentityGroup.getSelectedToggle();
        if (seleccionado != null) {
            String valor = ((RadioButton) seleccionado).getText();
            if (valor.equals("Masculino")) {
                return Sexo.MASCULINO;
            } else if (valor.equals("Femenino")) {
                return Sexo.FEMENINO;
            }
        }
        return null;
    }

    public void modifyUser() {
        btnDeleteUser.setOpacity(1);
        btnSaveChanges.setOpacity(1);
        btnAddUser.setOpacity(0);
        btnAddUser.setDisable(false);

    }

    public void clean() {
        //Esto es para limpiar todos los campos
        this.txtName.clear();
        this.txtSurname.clear();
        this.txtSecondSurname.clear();
        this.IdentityGroup.getSelectedToggle().setSelected(false);
        this.spnAge.setValue(0);
        this.imgvUsersFace.setImage(new Image(getClass().getResourceAsStream("../resources/User.jpg")));
        this.convertedImg = "";
    }

    public void setuptbvUsersList() {
        this.tbcFolio.setRowCellFactory(affiliated -> new MFXTableRowCell<>(Affiliated::getFolio));
        this.tbcAffiliated.setRowCellFactory(affiliated -> new MFXTableRowCell<>(Affiliated::getFullName));

        if (AppContext.getInstance().get("affiliated") != null) {
            this.tbvUsersList.setItems(FXCollections.observableArrayList(this.newAffiliates));
        }
    }

}
