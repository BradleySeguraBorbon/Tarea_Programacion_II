/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.controller;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
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
        //this.btnCaptureImage.setOpacity(0);
        this.btnRetryCapture.setDisable(false);
        //this.btnRetryCapture.setOpacity(1);
    }

    public synchronized void retryCapture() {
        this.videoTaker.isThreadSuspended = false;
        notify();
        this.btnCaptureImage.setDisable(false);
        //this.btnCaptureImage.setOpacity(1);
        this.btnRetryCapture.setDisable(true);
        //this.btnRetryCapture.setOpacity(0);
    }

    public void close() {
        this.webcam.close();
        this.videoTaker.isThreadSuspended = true;
        AppContext.getInstance().set("takenImage", capturedImage);
        ((Stage) this.btnExit.getScene().getWindow()).close();
    }

    private class VideoTaker extends Thread {

        public volatile Boolean isThreadSuspended = false;

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(80);
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
