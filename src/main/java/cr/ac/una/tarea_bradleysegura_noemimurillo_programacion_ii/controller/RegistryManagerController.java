/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.ImageConverter;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated.Sexo;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Formato;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXSpinner;
import io.github.palexdev.materialfx.controls.MFXTableColumn;
import io.github.palexdev.materialfx.controls.MFXTableView;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.controls.cell.MFXTableRowCell;
import io.github.palexdev.materialfx.controls.models.spinner.IntegerSpinnerModel;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.WARNING;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.RadioButton;
import javafx.scene.control.SpinnerValueFactory;
import javafx.scene.control.Toggle;
import javafx.stage.FileChooser;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javax.imageio.ImageIO;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class RegistryManagerController extends Controller implements Initializable {

    @FXML
    private MFXButton btnAddUser;
    @FXML
    private MFXButton btnModify;
    @FXML
    private MFXTextField txtSurname;
    @FXML
    private MFXTextField txtName;
    @FXML
    private MFXTextField txtSecondSurname;
    @FXML
    private MFXSpinner<Integer> spnAge;
    @FXML
    private ToggleGroup IdentityGroup;
    @FXML
    private MFXButton btnDeleteUser;
    @FXML
    private MFXButton btnSaveChanges;
    @FXML
    private MFXTableView<Affiliated> tbvUsersList;
    @FXML
    private MFXTableColumn<Affiliated> tbcAffiliated;
    @FXML
    private MFXTableColumn<Affiliated> tbcFolio;
    @FXML
    private ImageView imgvUsersFace;

    Image imgDefault;

    String valorspinner = "";

    ArrayList<Affiliated> newAffiliates;
    String convertedImg = "";

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // this.newAffiliates = (ArrayList<Affiliated>) AppContext.getInstance().get("afiliated");
        this.spnAge.setSpinnerModel(new IntegerSpinnerModel(0));
        spnAge.setTextTransformer((focused, text) -> (!focused || !spnAge.isEditable()) ? text + " años" : text);
        txtSurname.delegateSetTextFormatter(Formato.getInstance().letrasFormat(20));
        txtName.delegateSetTextFormatter(Formato.getInstance().letrasFormat(20));
        txtSecondSurname.delegateSetTextFormatter(Formato.getInstance().letrasFormat(20));

        // Manejar el evento de liberación de teclas para el MFXSpinner
        spnAge.addEventFilter(KeyEvent.KEY_RELEASED, event -> {

            // Obtener el texto ingresado, que es solo un dígito
            String inputText = event.getText();
            //En caso de que sea una letra se genera un mensaje de error
            if (inputText.matches("[a-zA-Z]")) {
                Mensaje mensaje = new Mensaje();
                mensaje.show(WARNING, "No ha ingresado un valor válido", "Por favor ingrese números en la casilla de Edad");

            } else {

                // Validar si el texto ingresado es un número positivo
                if (inputText.matches("\\d+")) {
                    // Concatenar solo si es un número
                    valorspinner += inputText;
                } else if (event.getCode() == KeyCode.BACK_SPACE) {
                    // Si se presiona la tecla de retroceso, eliminar el último dígito
                    if (valorspinner.length() > 0) {
                        valorspinner = valorspinner.substring(0, valorspinner.length() - 1);
                    }
                }
//                System.out.println("Texto insertado: " + inputText);
//                System.out.println("El valor del spinner es " + valorspinner);

                //Si el valorspinner está en vacío entonces el valor queda en cero
                if (valorspinner.equals("")) {
                    spnAge.setValue(0);
                } else {
                    //Si tiene un valor se le pone el valor al spinner
                    spnAge.setValue(Integer.valueOf(valorspinner));
                }
            }

        });

        initialize();
        this.imgDefault = imgvUsersFace.getImage();
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

        //Se limpian todos los campos
        clean();
    }

    @FXML
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

            //Se crea un bufferedImage con el archivo seleccionado
            BufferedImage image = ImageIO.read(file);

            // Verificar si se seleccionó un archivo
            if (file != null) {
                // Crear un string a partir del archivo seleccionado para más adelante usarlo en el constructor del afiliado, para este método es el bufferedImage anterior.
                this.convertedImg = ImageConverter.toBase64(image, ImageConverter.getFormat(file));
                System.out.println(ImageConverter.getFormat(file));

                //Se vuelve a convertir el string de la imagen de base64 a imagen
                Image bs64ToImg = ImageConverter.fromBase64(this.convertedImg);
                // Mostrar la imagen en el ImageView
                imgvUsersFace.setImage(bs64ToImg);
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @FXML
    public void addNewUser() {
        btnDeleteUser.setDisable(false);
        btnSaveChanges.setDisable(false);

        //Se crea una instancia de mensaje para las advertencias en caso de que algún espacio esté vació o si se agregó el usuario exitosamente.
        Mensaje msje = new Mensaje();

        //Estos if validan si los espacios están llenos y si no se salen del método.
        if (convertedImg.equals("")) {
            msje.show(WARNING, "Imagen vacía", "La imagen del nuevo usuario está vacía");
            return;
        }
        if (txtName.getText().equals("")) {
            msje.show(WARNING, "Nombre vacío", "La casilla de nombre del nuevo usuario está vacía");
            return;
        }
        if (txtSurname.getText().equals("")) {
            msje.show(WARNING, "Primer apellido vacío", "La casilla del primer apellido del nuevo usuario está vacía");
            return;
        }
        if (txtSecondSurname.getText().equals("")) {
            msje.show(WARNING, "Segundo apellido vacío", "La casilla del segundo apellido del nuevo usuario está vacía");
            return;
        }
        if (this.spnAge.getValue() <= 0) {
            msje.show(WARNING, "Edad no ingresada", "Ingresa tu edad para continuar");
            return;
        }
        if (getNewSex() == null) {
            msje.show(WARNING, "Sexo vacío", "La casilla de sexo del nuevo usuario está vacía");
            return;
        }
        //Si ningún if se cumplió entonces se crea un afiliado
        Affiliated nuevo = new Affiliated(txtName.getText(), txtSurname.getText(), txtSecondSurname.getText(), spnAge.getValue(), getNewSex(), this.convertedImg, (String) AppContext.getInstance().get("cooperativeName"));

        this.newAffiliates.add(nuevo);

        //Mensaje de nuevo usuario agregado exitosamente e indica el FOLIO del nuevo usuario.
        msje.show(INFORMATION, "Nuevo Afiliado", "¡Se ha añadido un nuevo afiliado exitosamente! El folio del nuevo usuario es: " + nuevo.getFolio());

        //Se limpian los espacios de texto y todo lo demás
        clean();
        //Se actualiza la lista para que salga en la lista de usuarios inmediatamente
        setupTbvUsersList();
    }

    //Este método nos devuelve el radioButton seleccionado
    @FXML
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

    //En este método por medio de la tableView se seleciona un afiliado y se despliegan los datos en los campos establecidos (TextFields, ImageView, y demás)
    @FXML
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

    //Este método es para guardar los cambios que se hicieron en la información del afiliado, en caso de que algún espacio esté vació manda una advertencia porque no puede haber ninguno en blanco.
    @FXML
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
        //Se limpia todo
        clean();
        //Se vuelve a cargar la tableView
        setupTbvUsersList();
    }

    //Este es para remover el afiliado selecionado en la tableview.
    @FXML
    public void removeAffiliated() {
        Affiliated selection = this.tbvUsersList.getSelectionModel().getSelectedValue();
        if (selection != null) {
            if (selection.BalanceCero()) {
                this.newAffiliates.remove(selection);
                clean();
                setupTbvUsersList();
                new Mensaje().show(Alert.AlertType.INFORMATION, "USUARIO ELIMINADO", "El asociado " + selection.getFullName().toUpperCase() + "con el número de folio: " + selection.getFolio() + " ha sido eliminado exitosamente.");
            } else {
                new Mensaje().show(Alert.AlertType.ERROR, "BALANCE DISPONIBLE", "El asociado tiene dinero en una o más cuentas, por favor haz el retiro antes de eliminarlo.");
            }
        }
    }

    @FXML
    public void saveNumber() {
        Mensaje mensj = new Mensaje();
        mensj.show(WARNING, "Sexo vacío", "La casilla de sexo del nuevo usuario está vacía");
    }

    //Este método es para limpiar todos los espacios en donde se escribe/despliega la informacipon del afiliado.
    @FXML
    public void clean() {
        this.txtName.clear();
        this.txtSurname.clear();
        this.txtSecondSurname.clear();
        if (this.IdentityGroup.getSelectedToggle() != null) {
            this.IdentityGroup.getSelectedToggle().setSelected(false);
        }
        this.spnAge.setValue(0);
        this.imgvUsersFace.setImage(this.imgDefault);
        this.convertedImg = "";
        this.btnAddUser.setDisable(false);
        this.btnModify.setDisable(false);
        this.btnDeleteUser.setDisable(true);
        this.btnSaveChanges.setDisable(true);
    }

    //Este método es para cargar la información de los afiliados en el tableview.
    @FXML
    public void setupTbvUsersList() {
        if (this.newAffiliates != null) {
            this.tbvUsersList.setItems(FXCollections.observableArrayList(this.newAffiliates));
        }
    }
}
