package cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii;

import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.model.Affiliated;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.AppContext;
import cr.ac.una.tarea_bradleysegura_noemimurillo_programacion_ii.util.FlowController;
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
        
        ArrayList<Affiliated> afiliated = new ArrayList();
        AppContext.getInstance().set("afiliated", afiliated);
        /*
        afiliated.add(new Affiliated("Bradley", "Segura", "Borbon", 18, Affiliated.Sexo.MASCULINO, "Coope"));
        AppContext.getInstance().set("afiliated", afiliated);
        afiliated.add(new Affiliated("Noemi", "Murillo", "Godinez", 22, Affiliated.Sexo.FEMENINO, "Coope", null));
        
        System.out.println("Lista Local");
        for(Affiliated user : afiliated) {
            System.out.println(user.toString());
        }
        
        System.out.println("Lista de AppContext");
        for(Affiliated user : (ArrayList<Affiliated>)AppContext.getInstance().get("afiliated")) {
            System.out.println(user.toString());
        }*/
        
        //MFXThemeManager.addOn(FlowController.getInstance().getMainScene(), Themes.DEFAULT, Themes.LEGACY);
    }

    public static void main(String[] args) {
        launch();
    }

}