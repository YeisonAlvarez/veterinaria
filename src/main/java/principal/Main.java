package principal;

import java.sql.Connection;

import conexionBD.ConexionBD;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) {
        try {
            // Llamar a la conexión a la base de datos
            initDatabaseConnection();
            
            // Cargar el archivo FXML que contiene la interfaz de usuario
            Parent root = FXMLLoader.load(getClass().getResource("/vista/Principal.fxml"));
            
            // Configurar la escena y el escenario
            primaryStage.setTitle("Veterinaria ISA PETS");
            primaryStage.setScene(new Scene(root));
            primaryStage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para inicializar la conexión a la base de datos
    private void initDatabaseConnection() {
        try {
            // Obtener la instancia de la conexión
            ConexionBD conexionBD = ConexionBD.getInstance();
            Connection connection = conexionBD.getConnection();

            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            } else {
                System.out.println("Error al conectar con la base de datos.");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método principal para iniciar la aplicación
    public static void main(String[] args) {
        launch(args);
    }
}
