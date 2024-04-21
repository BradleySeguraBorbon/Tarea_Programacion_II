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
        this.txtFolio.delegateSetTextFormatter(Formato.getInstance().maxLengthFormat(6));
    }

    @Override
    public void initialize() {

    }

    public void browseUser() {
        Mensaje msj = new Mensaje();
        txtNameUser.clear();
        String selectedAffiliatedFolio = txtFolio.getText();

        for (Affiliated afiliated : (ArrayList<Affiliated>) AppContext.getInstance().get("affiliated")) {
            if (afiliated.getFolio().equals(selectedAffiliatedFolio)) {
                this.selectedAffiliated = afiliated;
                break;
            }
        }
        if (selectedAffiliated != null) {
            displayAffiliatedInfo();
        } else {
            msj.show(ERROR, "Error folio", "No se ha encontrado ningún usuario relacionado con este folio.");
        }
    }

    @FXML
    public void displayAffiliatedInfo() {
        this.txtNameUser.setText(this.selectedAffiliated.getFullName());
    }

    @FXML
    public void printPDF() {
        try {
            if (this.selectedAffiliated != null) {
                Document document = new Document(PageSize.ID_1);
                String homeDir = System.getProperty("user.home");
                PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream(homeDir + "/Downloads/" + this.selectedAffiliated.getFolio() + ".pdf"));
                document.open();
                Font font = FontFactory.getFont(FontFactory.TIMES, 6, BaseColor.BLACK);

                Path backgroundPath = Paths.get(App.class.getResource("resources/CardBackground.png").toURI());
                Image imgBackground = Image.getInstance(backgroundPath.toAbsolutePath().toString());
                imgBackground.setAbsolutePosition(0, 0);
                imgBackground.scaleAbsolute(243, 154);

                Image img = Image.getInstance(Base64.getDecoder().decode(this.selectedAffiliated.getProfileImage()));
                img.setAbsolutePosition(160, 80);
                img.scaleAbsolute(64, 64);

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
                for (String attribute : info) {
                    line = new Phrase(attribute, font);
                    canvas = writer.getDirectContent();
                    ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, line, x, y, 0);
                    y -= 15;
                }
                Phrase cooperativeName = new Phrase(this.cooperativeName, font);
                canvas = writer.getDirectContent();
                ColumnText.showTextAligned(canvas, Element.ALIGN_LEFT, cooperativeName, 160, 20, 0);
                Image imgLogo = Image.getInstance(Base64.getDecoder().decode(this.cooperativeLogo));
                imgLogo.setAbsolutePosition(200, 10);
                imgLogo.scaleAbsolute(32, 32);

                document.add(imgBackground);
                document.add(img);
                document.add(imgLogo);
                document.close();
                new Mensaje().show(Alert.AlertType.INFORMATION, "CARNET IMPRESO EXITOSAMENTE", "El carnet de afiliado fue impreso correctamente");
                showPrintedPDF();
            } else {
                new Mensaje().show(Alert.AlertType.WARNING, "NO HAY AFILIADO SELECCIONADO", "Selecciona a un afiliado para continuar");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void showPrintedPDF() {
        String homeDir = System.getProperty("user.home");
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
