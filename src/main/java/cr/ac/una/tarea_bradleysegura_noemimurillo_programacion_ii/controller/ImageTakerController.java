/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import io.github.palexdev.materialfx.controls.MFXButton;
import io.github.palexdev.materialfx.utils.SwingFXUtils;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author Bradley
 */
public class ImageTakerController extends Controller implements Initializable {

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
    private VideoTaker videoTaker;
    private Image capturedImage;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.webcam = Webcam.getDefault();
        this.webcam.setViewSize(WebcamResolution.VGA.getSize());
        this.webcam.open();
        this.videoTaker = new VideoTaker();
        this.videoTaker.start();
        this.videoTaker.isThreadSuspended = false;
    }

    @Override
    public void initialize() {

    }

    public void capture() {
        capturedImage = SwingFXUtils.toFXImage(this.webcam.getImage(), null);
        this.videoTaker.isThreadSuspended = true;
        this.imvAffiliatedImage.setImage(capturedImage);
        this.btnCaptureImage.setDisable(true);
        this.btnRetryCapture.setDisable(false);
        this.btnSave.setDisable(false);
        this.btnRetryCapture.toFront();
    }

    public synchronized void retryCapture() {
        this.videoTaker.isThreadSuspended = false;
        synchronized (this.videoTaker) {
            this.videoTaker.notify();
        }
        this.btnCaptureImage.setDisable(false);
        this.btnRetryCapture.setDisable(true);
        this.btnSave.setDisable(true);
        this.btnCaptureImage.toFront();
    }

    public void goBack() {
        ((AffiliatedRegisterController) FlowController.getInstance().getController("AffiliatedRegisterView")).recoverFocus(null);
        close();
    }
    
    public void save() {
        ((AffiliatedRegisterController) FlowController.getInstance().getController("AffiliatedRegisterView")).recoverFocus(capturedImage);
        close();
    }

    public void close() {
        this.webcam.close();
        this.videoTaker.isThreadSuspended = true;
        ((Stage) this.btnExit.getScene().getWindow()).close();
    }

    private class VideoTaker extends Thread {

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
                    BufferedImage frame = webcam.getImage();
                    imvAffiliatedImage.setImage(SwingFXUtils.toFXImage(frame, null));
                } catch (InterruptedException exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

}
