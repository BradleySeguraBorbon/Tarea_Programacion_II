/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Image;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.ColumnText;
import com.itextpdf.text.pdf.PdfContentByte;
import com.itextpdf.text.pdf.PdfWriter;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.App;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Formato;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.controls.MFXTextField;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.net.URL;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import static javafx.scene.control.Alert.AlertType.ERROR;
import static javafx.scene.control.Alert.AlertType.INFORMATION;
import javafx.scene.control.Button;
import javafx.scene.image.ImageView;
import org.apache.pdfbox.Loader;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.rendering.ImageType;
import org.apache.pdfbox.rendering.PDFRenderer;

/**
 * FXML Controller class
 *
 * @author Fiorella
 */
public class CardPrintController extends Controller implements Initializable {

    @FXML
    private MFXButton btnFolio;
    @FXML
    private MFXButton btnPrint;
    @FXML
    private MFXTextField txtFolio;
    @FXML
    private MFXTextField txtNameUser;
    @FXML
    private ImageView imvCard;

    private Affiliated selectedAffiliated;
    private String cooperativeName;
    private String cooperativeLogo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Obtención de información de cooperativa
        this.cooperativeName = (String) AppContext.getInstance().get("cooperativeName");
        this.cooperativeLogo = (String) AppContext.getInstance().get("cooperativeLogo");

        // Restricción de tamaño para Folio
        this.txtFolio.delegateSetTextFormatter(Formato.getInstance().capsFormat(6));
    }

    @Override
    public void initialize() {

    }

    //Este método es para buscar al afiliado.
    public void browseUser() {
        Mensaje msj = new Mensaje();
        txtNameUser.clear();
        String selectedAffiliatedFolio = txtFolio.getText();

        for (Affiliated afiliated : (ArrayList<Affiliated>) AppContext.getInstance().get("affiliated")) {
            if (afiliated.getFolio().equals(selectedAffiliatedFolio)) {
                //Se guarda el afiliado 
                this.selectedAffiliated = afiliated;
                break;
            }
        }
        //En caso de que sí haya un afiliado se despliega la información y si no se genera un mensaje de error
        if (selectedAffiliated != null) {
            displayAffiliatedInfo();
        } else {
            msj.show(ERROR, "Error folio", "No se ha encontrado ningún usuario relacionado con este folio.");
        }
    }

    //Este método es para mostrar el nombre del afiliado en un textfied.
    @FXML
    public void displayAffiliatedInfo() {
        this.txtNameUser.setText(this.selectedAffiliated.getFullName());
    }

    @FXML
    public void printPDF() {
        try {
            //Se verifica que exista un afiliado seleccionado
            if (this.selectedAffiliated != null) {
                //Se crea un documento y el tamaño de página, que este se guardará en descargas y que es un pdf por medio de PdfWriter
                Document document = new Document(PageSize.ID_1);
                String homeDir = System.getProperty("user.home");
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(homeDir + "/Downloads/" + this.selectedAffiliated.getFolio() + ".pdf"));

                //Se abre el documento
                document.open();

                //Se define un tipo de letra para el texto
                Font font = FontFactory.getFont(FontFactory.TIMES, 6, BaseColor.BLACK);

                //Se carga de imagen del fondo desde resources
                Path backgroundPath = Paths.get(App.class.getResource("resources/CardBackground.png").toURI());
                Image imgBackground = Image.getInstance(backgroundPath.toAbsolutePath().toString());
                imgBackground.setAbsolutePosition(0, 0);
                imgBackground.scaleAbsolute(243, 154);

                //Se carga de imagen del usuario(foto de perfil) desde base64 a una variable de tipo Image.
                Image img = Image.getInstance(Base64.getDecoder().decode(this.selectedAffiliated.getProfileImage()));
                img.setAbsolutePosition(160, 80);
                img.scaleAbsolute(64, 64);

                //Se carga toda la información del afiliado en un string 
                String[] info = {
                    "Nombre: " + this.selectedAffiliated.getName(),
                    "Primer Apellido: " + this.selectedAffiliated.getFirstLastName(),
                    "Segundo Apellido: " + this.selectedAffiliated.getSecondLastName(),
                    "Folio: " + this.selectedAffiliated.getFolio(),
                    "Edad: " + this.selectedAffiliated.getAge(),
                    "Sexo: " + this.selectedAffiliated.getSexo().toString()
                };
                final int x = 15;
                int y = 120;
                Phrase line;
                PdfContentByte canvas;

                //Utilizando el String de la información se sube todo al PDF
                for (String attribute : info) {
                    line = new Phrase(attribute, font);
                    canvas = writer.getDirectContent();
                    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, line, x, y, 0);
                    y -= 15;
                }

                //Se escribe el nombre de la cooperativa
                Phrase cooperativeName = new Phrase(this.cooperativeName, font);
                canvas = writer.getDirectContent();
                ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, cooperativeName, 160, 20, 0);

                //Se carga de imagen del logo de la cooperativa desde base64 a una variable de tipo Image.
                Image imgLogo = Image.getInstance(Base64.getDecoder().decode(this.cooperativeLogo));
                imgLogo.setAbsolutePosition(200, 10);
                imgLogo.scaleAbsolute(32, 32);

                //Se le agrega al documento la imagen de fondo, la imagen del usuario y la imagen del logo de la cooperativa
                document.add(imgBackground);
                document.add(img);
                document.add(imgLogo);

                //Se cierra el documento
                document.close();

                //Se despliega un mensaje de que se ha impreso exitosamente 
                new Mensaje().show(Alert.AlertType.INFORMATION, "CARNET IMPRESO EXITOSAMENTE", "El carnet de afiliado fue impreso correctamente, fue descargado y lo puedes encontrar en tu carpeta de DESCARGAS.");
                //Luego se muestra el PDF en el programa
                showPrintedPDF();
            } else {
                //Si no hay afiliado genera este mensaje de alerta.
                new Mensaje().show(Alert.AlertType.WARNING, "NO HAY AFILIADO SELECCIONADO", "Selecciona a un afiliado para continuar");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //Este método es para mostrar el PDF en el programa.
    public void showPrintedPDF() {
        //Acá se obtiene la ubicación del archivo 
        String homeDir = System.getProperty("user.home");
        //Acá se transforma el PDF a un bufferedImage para mostrarla en in image view y que el usuario la pueda ver en el programa directamente.
        try (PDDocument pdfDoc = Loader.loadPDF(new File(homeDir + "/Downloads/" + this.selectedAffiliated.getFolio() + ".pdf"))) {
            PDFRenderer pdfRndr = new PDFRenderer(pdfDoc);
            BufferedImage pdfImg = pdfRndr.renderImageWithDPI(0, 300, ImageType.RGB);
            this.imvCard.setImage(SwingFXUtils.toFXImage(pdfImg, null));
            System.out.println("PDF TRANSFORMED TO IMAGE SUCCESFULLY");
            
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
