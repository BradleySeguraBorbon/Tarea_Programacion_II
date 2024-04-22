package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.ImageConverter;
import io.github.palexdev.materialfx.css.themes.MFXThemeManager;
import io.github.palexdev.materialfx.css.themes.Themes;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;

    @Override
    public void start(Stage stage) throws IOException {
        FlowController.getInstance().InitializeFlow(stage, null);
        stage.setTitle("SISTEMA DE COOPERATIVA FINANCIERA");
        FlowController.getInstance().goMain();
        
    }

    public static void main(String[] args) {
        /*if("T".equals(args[0])) {
            AppContext.getInstance().set("User", "Teachers");
        }
        else if("O".equals(args[0])) {
            AppContext.getInstance().set("User", "Officers");
        }
        else {
            AppContext.getInstance().set("User", "Affiliated");
        }*/
        launch();
    }

}