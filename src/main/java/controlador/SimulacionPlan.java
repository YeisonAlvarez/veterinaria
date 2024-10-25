package controlador;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import java.io.IOException;
import java.net.URL;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.BeneficioDAO;
import dao.MascotaDAO;
import dao.PersonaDAO;
import dao.PlanDAO;
import dao.SimulacionDAO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import javafx.util.Callback;
import modelo.Persona;
import modelo.Plan;
import modelo.Beneficio;
import modelo.EstadoPersona;
import modelo.Mascota;
import modelo.RazaMascota;
import modelo.TipoMascota;
import modelo.TipoVinculo;

public class SimulacionPlan implements Initializable {

	@FXML
	private TableView<Beneficio> tablaBeneficios;

	@FXML
	private TableView<Beneficio> tablaBeneficiosSimulacion;

	private ObservableList<Beneficio> datosListBeneficiosSimula = FXCollections.observableArrayList();
	private ObservableList<Beneficio> datosListBeneficios = FXCollections.observableArrayList();

	@FXML
	private ComboBox<Plan> comboPlanes;

	@FXML
	private Label label_costoTotal;
	
	@FXML
	private Button btnAtras;

	@FXML
	private TableColumn<Beneficio, Integer> colCodBene;
	@FXML
	private TableColumn<Beneficio, String> colNombreBene;
	@FXML
	private TableColumn<Beneficio, String> colDetalleBene;
	@FXML
	private TableColumn<Beneficio, Integer> colTipoBene;
	@FXML
	private TableColumn<Beneficio, Double> colPrecioBene;

	// Beneficios adicionados a la simulacion actual
	@FXML
	private TableColumn<Beneficio, String> colNombreBeneSimula;
	@FXML
	private TableColumn<Beneficio, Integer> colTipoBeneSimula;
	@FXML
	private TableColumn<Beneficio, Double> colCostoBeneSimu;

	@FXML
	void btnSimular(ActionEvent event) {
		Plan planSeleccionado = comboPlanes.getSelectionModel().getSelectedItem();

		SimulacionDAO simulacionDAO = new SimulacionDAO();
		double costoTotal = simulacionDAO.simular(planSeleccionado, datosListBeneficiosSimula);

		// Formatear el costo total como moneda colombiana
		NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
		String costoFormateado = formatoMoneda.format(costoTotal);

		label_costoTotal.setText(costoFormateado);
	}


	@FXML
	void btnAdicionarBeneficioSimulacion(ActionEvent event) {
		colNombreBeneSimula.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colTipoBeneSimula.setCellValueFactory(new PropertyValueFactory<>("tipoBeneficio"));
		colCostoBeneSimu.setCellValueFactory(new PropertyValueFactory<>("costo"));

		colCostoBeneSimu.setCellFactory(
				(Callback<TableColumn<Beneficio, Double>, TableCell<Beneficio, Double>>) new Callback<TableColumn<Beneficio, Double>, TableCell<Beneficio, Double>>() {
					@Override
					public TableCell<Beneficio, Double> call(TableColumn<Beneficio, Double> param) {
						return new TableCell<Beneficio, Double>() {
							@Override
							protected void updateItem(Double item, boolean empty) {
								super.updateItem(item, empty);
								if (empty || item == null) {
									setText(null); // No mostrar texto si está vacío o es nulo
								} else {
									// Formatear el número como pesos colombianos
									NumberFormat formatoMoneda = NumberFormat
											.getCurrencyInstance(new Locale("es", "CO"));
									setText(formatoMoneda.format(item)); // Establecer el texto formateado
								}
							}
						};
					}
				});

		Beneficio beneficioSeleccionado = tablaBeneficios.getSelectionModel().getSelectedItem();
		Plan planSeleccionado = comboPlanes.getSelectionModel().getSelectedItem();

		// Verificar si hay un plan seleccionado
		if (planSeleccionado == null) {
			Alert alerta = new Alert(Alert.AlertType.WARNING);
			alerta.setTitle("Selección requerida");
			alerta.setHeaderText("No se ha seleccionado ningún plan");
			alerta.setContentText("Por favor, seleccione un plan antes de agregar un beneficio.");
			alerta.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
			return; // Salir del método si no hay un plan seleccionado
		}

		// Verificar si hay un beneficio seleccionado
		if (beneficioSeleccionado != null) {
			// Verificar si el beneficio ya fue añadido
			if (datosListBeneficiosSimula.contains(beneficioSeleccionado)) {
				// Mostrar una alerta si el beneficio ya ha sido añadido
				Alert alerta = new Alert(Alert.AlertType.WARNING);
				alerta.setTitle("Beneficio ya añadido");
				alerta.setHeaderText("Este beneficio ya ha sido añadido a la simulación");
				alerta.setContentText("Por favor, seleccione un beneficio diferente.");
				alerta.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
			} else {
				// Añadir el beneficio seleccionado a la lista observable de simulación
				datosListBeneficiosSimula.add(beneficioSeleccionado);

				// Actualizar la tabla de simulación de beneficios
				tablaBeneficiosSimulacion.setItems(datosListBeneficiosSimula);
			}
		} else {
			// Mostrar una alerta si no se seleccionó un beneficio
			Alert alerta = new Alert(Alert.AlertType.WARNING);
			alerta.setTitle("Selección requerida");
			alerta.setHeaderText("No se ha seleccionado ningún beneficio");
			alerta.setContentText("Por favor, seleccione un beneficio de la tabla antes de agregarlo.");
			alerta.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
		}
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		llenarComboPlanes();
		llenarListaBeneficios();

	}

	public void llenarComboPlanes() {

		PlanDAO planDAO = new PlanDAO();
		List<Plan> planes = planDAO.listarPlanes();
		comboPlanes.getItems().addAll(planes); // Llena el ComboBox con la lista de planes
	}

	@FXML
	private void btnDeseleccionarBeneficio(ActionEvent event) {
		Beneficio beneficioSeleccionado = tablaBeneficiosSimulacion.getSelectionModel().getSelectedItem();

		if (beneficioSeleccionado != null) {
			// Crear una alerta de confirmación
			Alert confirmacion = new Alert(AlertType.CONFIRMATION);
			confirmacion.setTitle("Confirmación de Eliminación");
			confirmacion.setHeaderText(null);
			confirmacion.setContentText(
					"¿Está seguro de que no desea incluir a su simulaciòn " + beneficioSeleccionado.getNombre() + "?");

			// Mostrar la alerta y esperar la respuesta del usuario
			Optional<ButtonType> resultado = confirmacion.showAndWait();

			// Verificar si el usuario confirmó la eliminación
			if (resultado.isPresent() && resultado.get() == ButtonType.OK) {

				// Eliminar de la lista observable y refrescar la tabla
				datosListBeneficiosSimula.remove(beneficioSeleccionado);
				tablaBeneficiosSimulacion.refresh();
			}
		} else {
			// Mostrar alerta si no se seleccionó un afiliado
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Advertencia");
			alerta.setHeaderText(null);
			alerta.setContentText("No has seleccionado un beneficio de tu simulaciòn.");
			alerta.showAndWait();
		}

	}

	public void llenarListaBeneficios() {

		datosListBeneficios = listarBeneficios(); // Ya es una ObservableList

		// Asignar la lista observable a la tabla
		tablaBeneficios.setItems(datosListBeneficios);

		// Configurar las columnas de la tabla
		colCodBene.setCellValueFactory(new PropertyValueFactory<>("codigo"));
		colNombreBene.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colDetalleBene.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
		colPrecioBene.setCellValueFactory(new PropertyValueFactory<>("costo"));
		// Aplicar el CellFactory para formatear el precio a pesos colombianos
		colPrecioBene.setCellFactory(
				(Callback<TableColumn<Beneficio, Double>, TableCell<Beneficio, Double>>) new Callback<TableColumn<Beneficio, Double>, TableCell<Beneficio, Double>>() {
					@Override
					public TableCell<Beneficio, Double> call(TableColumn<Beneficio, Double> param) {
						return new TableCell<Beneficio, Double>() {
							@Override
							protected void updateItem(Double item, boolean empty) {
								super.updateItem(item, empty);
								if (empty || item == null) {
									setText(null); // No mostrar texto si está vacío o es nulo
								} else {
									// Formatear el número como pesos colombianos
									NumberFormat formatoMoneda = NumberFormat
											.getCurrencyInstance(new Locale("es", "CO"));
									setText(formatoMoneda.format(item)); // Establecer el texto formateado
								}
							}
						};
					}
				});

		colTipoBene.setCellValueFactory(new PropertyValueFactory<>("tipoBeneficio"));

	}

	public ObservableList<Beneficio> listarBeneficios() {

		BeneficioDAO beneficioDAO = new BeneficioDAO();
		// Obtener la lista desde la base de datos (ArrayList)
		List<Beneficio> listaBeneficios = beneficioDAO.listarBeneficios();

		// Convertir la lista a una ObservableList
		ObservableList<Beneficio> observableListBeneficios = FXCollections.observableArrayList(listaBeneficios);

		return observableListBeneficios;
	}
	public void btnAtras (ActionEvent event) {
		
    try {
        // Cargar la página Principal.fxml
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/vista/Principal.fxml"));
        Scene scene = new Scene(loader.load());

        // Obtener el escenario actual y establecer la nueva escena
        Stage stage = (Stage) btnAtras.getScene().getWindow();
        stage.setScene(scene);
        stage.setTitle("Página Principal"); // Cambia el título si lo deseas
        stage.show();
    } catch (IOException e) {
        e.printStackTrace(); // Manejo básico de excepciones
    }
}

//	// Método que asigna el Listener a la tabla
//	public void asignarListenerTablaPersona() {
//		tablaContenedorBeneficios.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, seleccionado) -> {
//			if (seleccionado != null) {
//				mapearRegistroSeleccionadoPersona(seleccionado);
//			}
//		});
//	}

}
