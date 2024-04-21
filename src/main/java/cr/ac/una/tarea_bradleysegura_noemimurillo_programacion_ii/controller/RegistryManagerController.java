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
import static javafx.scene.control.Alert.AlertType.WARNING;
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
    private MFXButton btnModify;
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
    private MFXTableView<Affiliated> tbvUsersList;
    @FXML
    private MFXTableColumn<Affiliated> tbcAffiliated;
    @FXML
    private MFXTableColumn<Affiliated> tbcFolio;
    @FXML
    private ImageView imgvUsersFace;//

    ArrayList<Affiliated> newAffiliates;
    String convertedImg = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // this.newAffiliates = (ArrayList<Affiliated>) AppContext.getInstance().get("afiliated");
        this.spnAge.setSpinnerModel(new IntegerSpinnerModel(0));
        initialize();
    }

    @Override
    public void initialize() {
        //IInicialización de TableView de Afiliados
        if (AppContext.getInstance().get("affiliated") != null) {
            this.newAffiliates = (ArrayList<Affiliated>) AppContext.getInstance().get("affiliated");
        }
        this.tbcFolio.setRowCellFactory(affiliated -> new MFXTableRowCell<>(Affiliated::getFolio));
        this.tbcAffiliated.setRowCellFactory(affiliated -> new MFXTableRowCell<>(Affiliated::getFullName));
        setupTbvUsersList();

        //Inicialización de elementos activos
        clean();
    }

    public void saveNewImage() {
        try {
            // Crear un FileChooser para seleccionar el archivo de imagen
            FileChooser fileChooser = new FileChooser();
            fileChooser.setTitle("Seleccionar Imagen");

            // Filtrar solo archivos de imagen
            FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.jpeg");
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

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void addNewUser() {
        btnDeleteUser.setDisable(false);
        btnSaveChanges.setDisable(false);
        //Se crea una instancia de mensaje para las advertencias en caso de que algún espacio esté vació o si se agregó el usuario exitosamente.
        Mensaje msj = new Mensaje();

        //Estos if validan si los espacios están llenos y si no se salen del método.
        if (convertedImg.equals("")) {
            msj.show(WARNING, "Imagen vacía", "La imagen del nuevo usuario está vacía");
            return;
        }
        if (txtName.getText().equals("")) {
            msj.show(WARNING, "Nombre vacío", "La casilla de nombre del nuevo usuario está vacía");
            return;
        }
        if (txtSurname.getText().equals("")) {
            msj.show(WARNING, "Primer apellido vacío", "La casilla del primer apellido del nuevo usuario está vacía");
            return;
        }
        if (txtSecondSurname.getText().equals("")) {
            msj.show(WARNING, "Segundo apellido vacío", "La casilla del segundo apellido del nuevo usuario está vacía");
            return;
        }
        if (this.spnAge.getValue() <= 0) {
            msj.show(WARNING, "Edad no ingresada", "Ingresa tu edad para continuar");
            return;
        }
        if (getNewSex() == null) {
            msj.show(WARNING, "Sexo vacío", "La casilla de sexo del nuevo usuario está vacía");
            return;
        }
        Affiliated nuevo = new Affiliated(txtName.getText(), txtSurname.getText(), txtSecondSurname.getText(), spnAge.getValue(), getNewSex(), this.convertedImg, (String) AppContext.getInstance().get("cooperativeName"));
        //Si todos los espacios están llenos, se salta los if y se crea el nuevo usuario
        this.newAffiliates.add(nuevo);
        //Affiliated actualUser = newAffiliates.getLast();

        //Mensaje que indica el folio del nuevo usuario.
        msj.show(INFORMATION, "Nuevo Folio", "El folio del nuevo usuario es: " + nuevo.getFolio());
        //Mensaje de nuevo usuario agregado exitosamente.
        msj.show(INFORMATION, "Nuevo Afiliado", "¡Se ha añadido un nuevo afiliado exitosamente!");

        clean();
        setupTbvUsersList();
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

    public void modifyAffiliated() {
        btnDeleteUser.setDisable(false);
        btnSaveChanges.setDisable(false);
        btnAddUser.setDisable(true);
        Affiliated selection = this.tbvUsersList.getSelectionModel().getSelectedValue();
        if (selection != null) {
            this.txtName.setText(selection.getName());
            this.txtSurname.setText(selection.getFirstLastName());
            this.txtSecondSurname.setText(selection.getSecondLastName());
            System.out.println("INDICE 0 : " + IdentityGroup.getToggles().get(0));
            this.IdentityGroup.selectToggle(IdentityGroup.getToggles().get(selection.getSexo() == Affiliated.Sexo.FEMENINO ? 0 : 1));
            this.spnAge.setValue(selection.getAge());
            this.convertedImg = selection.getProfileImage();
            this.imgvUsersFace.setImage(ImageConverter.fromBase64(convertedImg));
            
        } else {
            new Mensaje().show(WARNING, "Afiliado No Seleccionado", "Selecciona un afiliado para modificar su información");
        }
    }

    public void saveChanges() {
        Affiliated selection = this.tbvUsersList.getSelectionModel().getSelectedValue();
        Mensaje msj = new Mensaje();
        if (convertedImg.equals("")) {
            msj.show(WARNING, "Imagen vacía", "La imagen del nuevo usuario está vacía");
            return;
        }
        if (txtName.getText().equals("")) {
            msj.show(WARNING, "Nombre vacío", "La casilla de nombre del nuevo usuario está vacía");
            return;
        }
        if (txtSurname.getText().equals("")) {
            msj.show(WARNING, "Primer apellido vacío", "La casilla del primer apellido del nuevo usuario está vacía");
            return;
        }
        if (txtSecondSurname.getText().equals("")) {
            msj.show(WARNING, "Segundo apellido vacío", "La casilla del segundo apellido del nuevo usuario está vacía");
            return;
        }
        if (this.spnAge.getValue() <= 0) {
            msj.show(WARNING, "Edad no ingresada", "Ingresa tu edad para continuar");
            return;
        }
        if (getNewSex() == null) {
            msj.show(WARNING, "Sexo vacío", "La casilla de sexo del nuevo usuario está vacía");
            return;
        }
        if (selection == null) {
            new Mensaje().show(WARNING, "Afiliado No Seleccionado", "Selecciona un afiliado para modificar su información");
            return;
        }
        selection.setName(txtName.getText());
        selection.setFirstLastName(txtSurname.getText());
        selection.setSecondLastName(txtSecondSurname.getText());
        selection.setSexo(getNewSex());
        selection.setAge(spnAge.getValue());
        selection.setProfileImage(convertedImg);
        
        clean();
        setupTbvUsersList();
    }

    public void removeAffiliated() {
        Affiliated selection = this.tbvUsersList.getSelectionModel().getSelectedValue();
        if (selection != null) {
            this.newAffiliates.remove(selection);
            clean();
            setupTbvUsersList();
        }
    }

    public void clean() {
        //Esto es para limpiar todos los campos
        this.txtName.clear();
        this.txtSurname.clear();
        this.txtSecondSurname.clear();
        if (this.IdentityGroup.getSelectedToggle() != null) {
            this.IdentityGroup.getSelectedToggle().setSelected(false);
        }
        this.spnAge.setValue(0);
        this.imgvUsersFace.setImage(new Image(getClass().getResourceAsStream("../resources/User.jpg")));
        this.convertedImg = "";
        this.btnAddUser.setDisable(false);
        this.btnModify.setDisable(false);
        this.btnDeleteUser.setDisable(true);
        this.btnSaveChanges.setDisable(true);
    }

    public void setupTbvUsersList() {
        if (this.newAffiliates != null) {
            this.tbvUsersList.setItems(FXCollections.observableArrayList(this.newAffiliates));
        }
    }

}
