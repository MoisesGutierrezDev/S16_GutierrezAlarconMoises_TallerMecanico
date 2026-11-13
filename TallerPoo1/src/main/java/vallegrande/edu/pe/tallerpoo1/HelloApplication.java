package vallegrande.edu.pe.tallerpoo1;

import javafx.application.Application;
import javafx.stage.Stage;
import vallegrande.edu.pe.tallerpoo1.view.TallerView;

public class HelloApplication extends Application {
    @Override
    public void start(Stage stage) {
        TallerView vista = new TallerView();
        stage.setTitle("Taller Mecánico - CRUD");
        stage.setScene(vista.crearEscena());
        stage.show();
    }
}