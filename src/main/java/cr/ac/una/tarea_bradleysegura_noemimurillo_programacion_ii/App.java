package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage, null);
        stage.setTitle("SISTEMA DE COOPERATIVA FINANCIERA");
        FlowController.getInstance().goViewInWindow("AccountTypeManagementView");
    }

    public static void main(String[] args) {
        launch();
    }

}