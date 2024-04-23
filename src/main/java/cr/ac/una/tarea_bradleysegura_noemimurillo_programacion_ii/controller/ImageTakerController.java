/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.Mensaje;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import java.awt.Dimension;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.ImageView;
import javafx.embed.swing.SwingNode;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class ImageTakerController extends Controller implements Initializable {

    @FXML
    private SwingNode webcamNode;
    @FXML
    private ImageView imvAffiliatedImage;
    @FXML
    private MFXButton btnCaptureImage;
    @FXML
    private MFXButton btnExit;
    @FXML
    private MFXButton btnRetryCapture;
    @FXML
    private MFXButton btnSave;

    private Webcam webcam;
    private WebcamPanel panel;
    private BufferedImage capturedImage;
    
    //private VideoTaker videoTaker;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        /*is.webcam = Webcam.getDefault();
        Dimension webcamDimension = new Dimension(640, 480);
        this.webcam.setCustomViewSizes(webcamDimension);
        this.panel = new WebcamPanel(this.webcam);
        this.panel.setMaximumSize(webcamDimension);
        this.panel.setMaximumSize(webcamDimension);
        this.webcamNode.setContent(panel);*/

 /*this.webcam = Webcam.getDefault();
        this.webcam.setViewSize(WebcamResolution.VGA.getSize());
        this.webcam.open();
        this.videoTaker = new VideoTaker();
        this.videoTaker.start();
        this.videoTaker.isThreadSuspended = false; */
        //((Stage) this.btnExit.getScene().getWindow()).setOnCloseRequest(event ->  {goBack();});
    }

    @Override
    public void initialize() {
        //Inicialización de Webcam
        this.webcam = Webcam.getDefault();
        this.webcam.setViewSize(WebcamResolution.VGA.getSize());
        
        //Inicialización del Panel
        this.panel = new WebcamPanel(this.webcam);
        this.panel.setMaximumSize(WebcamResolution.VGA.getSize());
        this.panel.setMaximumSize(WebcamResolution.VGA.getSize());
        this.webcamNode.setContent(panel);
        
        /*((Stage)btnSave.getScene().getWindow()).setOnCloseRequest(WindowEvent -> { 
            close();
        });
        if (this.webcamNode.getContent() == null) {
            this.webcam = Webcam.getDefault();
            this.webcam.setViewSize(webcamDimension);
            this.panel = new WebcamPanel(this.webcam);
            this.webcamNode.setContent(panel);
        }*/
 /*
        if (!this.webcam.isOpen()) {
            this.webcam = Webcam.getDefault();
            this.webcam.setViewSize(WebcamResolution.VGA.getSize());
            this.webcam.open();
        }
        if (this.videoTaker == null) {
            this.videoTaker = new VideoTaker();
            this.videoTaker.start();
            retryCapture();
        }*/
    }

    @FXML
    public void capture() {
        this.capturedImage = this.webcam.getImage();
        //this.videoTaker.isThreadSuspended = true;
        if (capturedImage != null) {
            this.imvAffiliatedImage.setImage(SwingFXUtils.toFXImage(capturedImage, null));
            this.btnCaptureImage.setDisable(true);
            this.btnRetryCapture.setDisable(false);
            this.btnSave.setDisable(false);
            this.btnRetryCapture.toFront();
            this.panel.stop();
        }
    }

    @FXML
    public void retryCapture() {
        /*this.videoTaker.isThreadSuspended = false;
        synchronized (this.videoTaker) {
            this.videoTaker.notify();
        }*/
        this.capturedImage = null;
        this.imvAffiliatedImage.setImage(null);
        this.btnCaptureImage.setDisable(false);
        this.btnRetryCapture.setDisable(true);
        this.btnSave.setDisable(true);
        this.btnCaptureImage.toFront();
        this.panel.start();
    }

    @FXML
    public void goBack() {
        ((AffiliatedRegisterController) FlowController.getInstance().getController("AffiliatedRegisterView")).recoverFocus(null);
        close();
    }

    @FXML
    public void save() {
        try {
            if (this.capturedImage != null) {
                ((AffiliatedRegisterController) FlowController.getInstance().getController("AffiliatedRegisterView")).recoverFocus(capturedImage);
                close();
            } else {
                new Mensaje().show(Alert.AlertType.WARNING, "NO SE HA CAPTURADO LA IMAGEN", "Captura la imagen para continuar");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void close() {
        this.panel.stop();
        this.webcamNode.setContent(null);
        /*this.webcam.close();
        this.videoTaker.isThreadSuspended = true;
        this.videoTaker.interrupt();
        this.videoTaker = null;*/
        getStage().close();
    }

    /*private class VideoTaker extends Thread {

        public volatile Boolean isThreadSuspended = false;

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(10);
                    if (isThreadSuspended) {
                        synchronized (this) {
                            while (isThreadSuspended) {
                                wait();
                            }
                        }
                    }
                    BufferedImage currentFrame = webcam.getImage();
                    if (currentFrame != null) {
                        imvAffiliatedImage.setImage(SwingFXUtils.toFXImage(currentFrame, null));
                    }
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    } */
}
