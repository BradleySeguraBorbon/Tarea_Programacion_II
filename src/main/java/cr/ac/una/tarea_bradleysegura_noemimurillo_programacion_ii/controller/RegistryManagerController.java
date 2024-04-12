/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated.Sexo;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXListView;
import io.github.palexdev.materialfx.controls.MFXRadioButton;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
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

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class RegistryManagerController extends Controller implements Initializable {

    @FXML
    private MFXButton btnAddUser;
    @FXML
    private MFXTextField txtName;
    @FXML
     private MFXTextField txtSurname;
    @FXML
    private MFXTextField txtSecondSurname;
    @FXML
    private MFXTextField txtAge;
    @FXML
    private ToggleGroup SexGroup;
    @FXML
    private MFXButton btnDeleteUser;
    @FXML
    private MFXButton btnSaveChanges;
    @FXML
    private MFXListView tbvUsersList;
    @FXML
    private ImageView imgvUsersFace;
    
    ArrayList<Affiliated> newAffiliates = new ArrayList<>();
    String convertedImg = "";

    /**
     * Initializablelizes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        //ArrayList<Affiliated> Affiliated2 = new ArrayList<>();
        //AppContext.getInstance().set("afiliated", Affiliated2);
        this.newAffiliates = (ArrayList<Affiliated>) AppContext.getInstance().get("afiliated");
        // txtAge.setTextFormatter(Formato.getInstance().integerFormat());
       // setUpUserBox();

        // TODO
        initialize();

    }

    @Override
    public void initialize() {

    }

//    public void setUpUserBox() {
//
//        MFXTableColumn<Affiliated> folioColumn = new MFXTableColumn<>("Folio", true, Comparator.comparing(Affiliated::getFolio));
//        MFXTableColumn<Affiliated> nameColumn = new MFXTableColumn<>("Nombre", true, Comparator.comparing(Affiliated::getFullName));
//
//        //Especificar qué se está mostrando y dónde
//        folioColumn.setRowCellFactory(folioNumber -> new MFXTableRowCell<>(Affiliated::getFolio));
//        nameColumn.setRowCellFactory(nameString -> new MFXTableRowCell<>(Affiliated::getFullName));
//
//        this.tbvUsersList.getTableColumns().addAll(folioColumn, nameColumn);
//        this.tbvUsersList.setItems(FXCollections.observableArrayList(newAffiliates));
//
//    }

    public static String convertImageToBase64(String filePath) {
        String base64Image = "";
        try (InputStream inputStream = new FileInputStream(filePath)) {
            byte[] imageBytes = inputStream.readAllBytes();
            base64Image = Base64.getEncoder().encodeToString(imageBytes);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return base64Image;
    }

    public static Image convertBase64ToImage(String base64String) {
        try {
            byte[] decodedBytes = Base64.getDecoder().decode(base64String);
            return new Image(new ByteArrayInputStream(decodedBytes));
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public void saveNewImage() {
        // Crear un FileChooser para seleccionar el archivo de imagen
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Seleccionar Imagen");

        // Filtrar solo archivos de imagen
        FileChooser.ExtensionFilter extFilter = new FileChooser.ExtensionFilter("Archivos de Imagen", "*.png", "*.jpg", "*.gif");
        fileChooser.getExtensionFilters().add(extFilter);

        // Mostrar el diálogo de selección de archivo
        File file = fileChooser.showOpenDialog(null);

        // Verificar si se seleccionó un archivo
        if (file != null) {
            // Crear una imagen a partir del archivo seleccionado
            //Image image = new Image(file.toURI().toString());
            String image64 = file.getAbsolutePath();
            //String image64 = "C:\\Users\\Fiorella\\Pictures\\20181031_170733.jpg";
            convertedImg = convertImageToBase64(image64);

            Image bs64ToImg = convertBase64ToImage(convertedImg);

            // Mostrar la imagen en el ImageView
            imgvUsersFace.setImage(bs64ToImg);

        }

    }

    public void addNewUser() {
        btnDeleteUser.setDisable(false);
        btnSaveChanges.setDisable(false);
        //Se crea una instancia de mensaje para las advertencias en caso de que algún espacio esté vació o si se agregó el usuario exitosamente.
        Mensaje msj = new Mensaje();

        //Estos if validan si los espacios están llenos y si no se salen del método.
        if (convertedImg.equals("")) {
            msj.show(ERROR, "Imagen vacía", "La iamgen del nuevo usuario está vacía");
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
        if (getNewSex() == null) {
            msj.show(ERROR, "Sexo vacío", "La casilla de sexo del nuevo usuario está vacía");
            return;
        }

        //Si todos los espacios están llenos, se salta los if y se crea el nuevo usuario
        newAffiliates.add(new Affiliated(txtName.getText(), txtSurname.getText(), txtSecondSurname.getText(), Integer.parseInt(txtAge.getText()), getNewSex(), (String) AppContext.getInstance().get("cooperativeName"), convertedImg));
        Affiliated actualUser = newAffiliates.getLast();

        //Mensaje que indica el folio del nuevo usuario.
        msj.show(INFORMATION, "Nuevo Folio", "El folio del nuevo usuario es: " + actualUser.getFolio());
        //Mensaje de nuevo usuario agregado exitosamente.
        msj.show(INFORMATION, "Nuevo Afiliado", "¡Se ha añadido un nuevo afiliado exitosamente!");

        clean();

    }

    public Sexo getNewSex() {
        Toggle seleccionado = SexGroup.getSelectedToggle();
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
        txtName.clear();
        txtSurname.clear();
        txtSecondSurname.clear();
        SexGroup.getSelectedToggle().setSelected(false);
        txtAge.clear();
        Image defaultImg = new Image(getClass().getResourceAsStream("/cr/ac/una/tarea_bradleysegura_noemimurillo_programacion_ii/resources/user.png"));
        imgvUsersFace.setImage(defaultImg);
        convertedImg = "";
    }

}
