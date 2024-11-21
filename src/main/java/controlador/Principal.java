package controlador;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

public class Principal {

	@FXML
	private Button btnGestionClientes;

	@FXML
	private Button btnSimularPlan;

	@FXML
	private Button btnSalir;
	
	@FXML
	private Button btnGestionReportes;

	// Método para manejar la acción del botón "Gestión Clientes"
	@FXML
	void btnGestionClientesEvent() {
		cambiarPantalla("vista/GestionPersona.fxml");
	}
	
	@FXML
	void btnGestionarReportesEvent() {
		cambiarPantalla("vista/GestionarReporte.fxml");
	}

	// Método para manejar la acción del botón "Simular Plan"
	@FXML
	void btnSimularPlanEvent() {
		cambiarPantalla("vista/SimularPlan.fxml");
	}

	// Método para manejar la acción del botón "Salir"
	@FXML
	void btnSalirEvent() {
		cambiarPantalla("vista/Login.fxml"); // Cambiar a la pantalla de Login
	}

	// Método para cambiar de pantalla
	private void cambiarPantalla(String fxmlFile) {
		try {
			// Cargar el nuevo FXML
			AnchorPane nuevaPantalla = FXMLLoader.load(getClass().getResource("/" + fxmlFile));
			Scene nuevaEscena = new Scene(nuevaPantalla);
			Stage ventana = (Stage) btnGestionClientes.getScene().getWindow(); // Obtener la ventana actual
			ventana.setScene(nuevaEscena);
			ventana.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
