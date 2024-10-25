package controlador;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

import javafx.scene.input.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import dao.MascotaDAO;
import dao.PersonaDAO;
import javafx.beans.property.SimpleStringProperty;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import modelo.Persona;
import modelo.EstadoPersona;
import modelo.Mascota;
import modelo.RazaMascota;
import modelo.TipoMascota;
import modelo.TipoVinculo;
import modelo.Persona;

public class GestionPersona implements Initializable {

	@FXML
	private TableColumn<Persona, String> colApellidos;

	@FXML
	private TableColumn<Persona, Integer> colEmail;

	@FXML
	private TableColumn<Persona, String> colCelular;

	@FXML
	private TableColumn<Persona, String> colNombres;

	@FXML
	private TableColumn<Persona, String> colVinculo;

	@FXML
	private TableColumn<Persona, String> colFechaVinculacion;

	@FXML
	private TableColumn<Persona, String> colEstado;

	@FXML
	private Label label_Error;

	@FXML
	private Label label_Fecha;

	@FXML
	private TableView<Persona> tablaContenedor;

	@FXML
	private TableView<Mascota> tablaMascotas;

	@FXML
	private ToggleButton toggleEstatus;

	@FXML
	private TextField txtPrimerNombre;

	@FXML
	private TextField txtSegundoNombre;

	@FXML
	private TextField txtPrimerApellido;

	@FXML
	private TextField txtSegundoApellido;

	@FXML
	private TextField txtDireccion;

	@FXML
	private TextField txtEmail;

	@FXML
	private TextField txtCelular;

	@FXML
	private TextField txtCedula;

	@FXML
	private TextField txtNombreMascota;

	@FXML
	private DatePicker dateFechaMascota;

	@FXML
	private TextField txtPesoMascota;

	@FXML
	private ComboBox<TipoMascota> comboTipoMascota;
	@FXML
	private ComboBox<TipoVinculo> comboVinculo;

	@FXML
	private ComboBox<RazaMascota> comboRaza;

	@FXML
	private TableColumn<Mascota, String> colNombreMascota;
	@FXML
	private TableColumn<Mascota, String> colEspecie;
	@FXML
	private TableColumn<Mascota, String> colRaza;
	@FXML
	private TableColumn<Mascota, String> peso;
	@FXML
	private TableColumn<Mascota, String> colFechaNacimiento;
	@FXML
	private TableColumn<Mascota, String> colCedulaPropietario;

	@FXML
	private TableColumn<Mascota, String> colPeso;

	@FXML
	private Button btnVolver;

	Persona persona;
	Mascota mascota;
	private ObservableList<Persona> datosListPersonas = FXCollections.observableArrayList();
	private ObservableList<Mascota> datosListMascota = FXCollections.observableArrayList();

	String fechaHoraFormateada;

	@FXML
	void btnEstatus(ActionEvent event) {
		if (toggleEstatus.getText().equals("Inactivo")) {
			toggleEstatus.getStyleClass().remove("estatus-text");
			toggleEstatus.getStyleClass().add("estatus-cambio");
			toggleEstatus.setText("Activo");
		} else {
			toggleEstatus.getStyleClass().remove("estatus-cambio");
			toggleEstatus.getStyleClass().add("estatus-text");
			toggleEstatus.setText("Inactivo");
		}

		asignarListenerTablaPersona();
	}

	@FXML
	void btnAgregarPersona(ActionEvent event) {
		// Verificar si hay mascotas en la lista
		if (datosListMascota == null || datosListMascota.isEmpty()) {
			// Mostrar una alerta si no hay mascotas
			Alert alerta = new Alert(Alert.AlertType.WARNING);
			alerta.setTitle("Mascotas requeridas");
			alerta.setHeaderText("No se ha encontrado ninguna mascota");
			alerta.setContentText("Para afiliar a una persona, debe tener al menos una mascota.");
			alerta.showAndWait(); // Mostrar la alerta y esperar a que el usuario la cierre
			return; // Salir del método si no hay mascotas
		}

		// Verificar otros campos de la persona
		if (VerificarCamposPersona()) {
			label_Error.setVisible(false);

			// --------------------- Logica para la persona----------------------

			// Obtener los datos del formulario y crear objeto Afiliado
			persona = mapearPersona();

			// Insertar el nuevo afiliado en la base de datos
			PersonaDAO personaDAO = new PersonaDAO();
			personaDAO.afiliar(persona); // Llama al método afiliar del DAO

			// Actualizar la tabla con el nuevo afiliado
			datosListPersonas.add(persona);

			// Configurar las columnas de la tabla
			colNombres.setCellValueFactory(new PropertyValueFactory<>("primerNombre"));
			colApellidos.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
			colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
			colCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
			colFechaVinculacion.setCellValueFactory(new PropertyValueFactory<>("fechaVinculacion"));
			colEstado.setCellValueFactory(new PropertyValueFactory<>("estadoPersona"));
			colVinculo.setCellValueFactory(new PropertyValueFactory<>("tipoVinculo"));

			// ------------------------Logica para la mascota-------------------
			int idMascota = 0;

			for (Mascota mascota : datosListMascota) {
				MascotaDAO mascotaDAO = new MascotaDAO();
				idMascota = mascotaDAO.obtenerUltimoIdMascota();
				idMascota++;

				mascota.setCodigo(idMascota);
				mascotaDAO.agregarMascota(mascota);
			}
		} else {
			label_Error.setVisible(true);
		}

		asignarListenerTablaPersona();
	}

	@FXML
	void btnEliminarPersona(ActionEvent event) {
		Persona personaSeleccionada = tablaContenedor.getSelectionModel().getSelectedItem();

		if (personaSeleccionada != null) {
			// Crear una alerta de confirmación
			Alert confirmacion = new Alert(AlertType.CONFIRMATION);
			confirmacion.setTitle("Confirmación de Eliminación");
			confirmacion.setHeaderText(null);
			confirmacion.setContentText("¿Está seguro de que desea eliminar a " + personaSeleccionada.getPrimerNombre()
					+ " " + personaSeleccionada.getPrimerApellido() + "?");

			// Mostrar la alerta y esperar la respuesta del usuario
			Optional<ButtonType> resultado = confirmacion.showAndWait();

			// Verificar si el usuario confirmó la eliminación
			if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
				// Eliminar de la base de datos
				PersonaDAO personaDAO = new PersonaDAO();
				personaDAO.eliminarPersona(personaSeleccionada.getCedula());

				// Eliminar de la lista observable y refrescar la tabla
				datosListPersonas.remove(personaSeleccionada);
				tablaContenedor.refresh();

				// Mostrar alerta de eliminación exitosa
				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Eliminación exitosa");
				alerta.setHeaderText(null);
				alerta.setContentText("La persona " + personaSeleccionada.getPrimerNombre() + " "
						+ personaSeleccionada.getPrimerApellido() + " ha sido eliminada exitosamente.");
				alerta.showAndWait(); // Espera hasta que el usuario cierre la alerta
			}
		} else {
			// Mostrar alerta si no se seleccionó un afiliado
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Advertencia");
			alerta.setHeaderText(null);
			alerta.setContentText("Por favor, seleccione una persona para eliminar.");
			alerta.showAndWait();
		}
	}

	@FXML
	void btnAgregarMascota(ActionEvent event) {

		if (VerificarCamposMascota() && VerificarCamposPersona()) {

			label_Error.setVisible(false);
			Mascota mascota = mapearMascota();
			datosListMascota.add(mascota);
			tablaMascotas.setItems(datosListMascota);

			// Configurar las columnas de la tabla
			colNombreMascota.setCellValueFactory(new PropertyValueFactory<>("nombre"));
			colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
			colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
			colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
			colRaza.setCellValueFactory(new PropertyValueFactory<>("raza"));
			colCedulaPropietario.setCellValueFactory(new PropertyValueFactory<>("cedulaPropietario"));

		} else {
			label_Error.setVisible(true);
		}

		asignarListenerTablaPersona();

	}

	@FXML
	void btnActualizarPersona(ActionEvent event) {
		Persona personaSeleccionada = tablaContenedor.getSelectionModel().getSelectedItem();

		if (personaSeleccionada != null) {
			// Crear una alerta de confirmación
			Alert confirmacion = new Alert(AlertType.CONFIRMATION);
			confirmacion.setTitle("Confirmación de Modificación");
			confirmacion.setHeaderText(null);
			confirmacion.setContentText("¿Está seguro de que desea modificar a " + personaSeleccionada.getPrimerNombre()
					+ " " + personaSeleccionada.getPrimerApellido() + "?");

			// Mostrar la alerta y esperar la respuesta del usuario
			Optional<ButtonType> resultado = confirmacion.showAndWait();

			// Verificar si el usuario confirmó la modificación
			if (resultado.isPresent() && resultado.get() == ButtonType.OK) {
				// Utilizar mapearPersona para obtener los nuevos valores
				Persona nuevaPersona = mapearPersona();

				// Modificar la persona en la base de datos
				PersonaDAO personaDAO = new PersonaDAO();
				personaDAO.modificarPersona(nuevaPersona);

				// Actualizar la persona en la lista observable
				personaSeleccionada.setPrimerNombre(nuevaPersona.getPrimerNombre());
				personaSeleccionada.setSegundoNombre(nuevaPersona.getSegundoNombre());
				personaSeleccionada.setPrimerApellido(nuevaPersona.getPrimerApellido());
				personaSeleccionada.setSegundoApellido(nuevaPersona.getSegundoApellido());
				personaSeleccionada.setEmail(nuevaPersona.getEmail());
				personaSeleccionada.setCelular(nuevaPersona.getCelular());
				personaSeleccionada.setDireccion(nuevaPersona.getDireccion());
				personaSeleccionada.setMascotas(nuevaPersona.getMascotas());

				// agregar una nueva mascota
				int idMascota = 0;
				Mascota mascota = mapearMascota();
				MascotaDAO mascotaDAO = new MascotaDAO();
				idMascota = mascotaDAO.obtenerUltimoIdMascota();
				idMascota++;

				mascota.setCodigo(idMascota);
				mascotaDAO.agregarMascota(mascota);

				// Refrescar la tabla
				tablaContenedor.refresh();

				// Mostrar alerta de modificación exitosa
				Alert alerta = new Alert(AlertType.INFORMATION);
				alerta.setTitle("Modificación exitosa");
				alerta.setHeaderText(null);
				alerta.setContentText("Los datos de la persona " + personaSeleccionada.getPrimerNombre() + " "
						+ personaSeleccionada.getPrimerApellido() + " han sido modificados exitosamente.");
				alerta.showAndWait(); // Espera hasta que el usuario cierre la alerta
			}
		} else {
			// Mostrar alerta si no se seleccionó una persona
			Alert alerta = new Alert(AlertType.WARNING);
			alerta.setTitle("Advertencia");
			alerta.setHeaderText(null);
			alerta.setContentText("Por favor, seleccione una persona para modificar.");
			alerta.showAndWait();
		}
	}

	@FXML
	void btnLimpiar(ActionEvent event) {

		LimpiarCampos();
		asignarListenerTablaPersona();
	}

	@FXML
	void btnLimpiarMascota(ActionEvent event) {

		limpiarCamposMascota();
		asignarListenerTablaPersona();
	}

	@Override
	public void initialize(URL url, ResourceBundle rb) {

		asignarListenerTablaPersona();
		Fecha();
		label_Error.setVisible(false);
		comboTipoMascota.setItems(FXCollections.observableArrayList(TipoMascota.values()));
		comboRaza.setItems(FXCollections.observableArrayList(RazaMascota.values()));
		comboVinculo.setItems(FXCollections.observableArrayList(TipoVinculo.values()));

		datosListPersonas = listarPersonas(); // Ya es una ObservableList

		// Asignar la lista observable a la tabla
		tablaContenedor.setItems(datosListPersonas);

		// Configurar las columnas de la tabla
		colNombres.setCellValueFactory(new PropertyValueFactory<>("primerNombre"));
		colApellidos.setCellValueFactory(new PropertyValueFactory<>("primerApellido"));
		colEmail.setCellValueFactory(new PropertyValueFactory<>("email"));
		colCelular.setCellValueFactory(new PropertyValueFactory<>("celular"));
		colFechaVinculacion.setCellValueFactory(new PropertyValueFactory<>("fechaVinculacion"));
		colEstado.setCellValueFactory(new PropertyValueFactory<>("estadoPersona"));
		colVinculo.setCellValueFactory(new PropertyValueFactory<>("tipoVinculo"));

	}

	public ObservableList<Persona> listarPersonas() {
		PersonaDAO personaDAO = new PersonaDAO();
		// Obtener la lista desde la base de datos (ArrayList)
		List<Persona> listaPersonas = personaDAO.listarPersonas();

		// Convertir la lista a una ObservableList
		ObservableList<Persona> observableListPersonas = FXCollections.observableArrayList(listaPersonas);

		return observableListPersonas;
	}

	private void Fecha() {
		Date fec = new Date();
		SimpleDateFormat fecha = new SimpleDateFormat("dd-MM-YYYY");
		label_Fecha.setText(fecha.format(fec));

		SimpleDateFormat formatoFechaHoraMySQL = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		fechaHoraFormateada = formatoFechaHoraMySQL.format(new Date());
	}

	private boolean VerificarCamposPersona() {
		boolean primerNombre = !txtPrimerNombre.getText().isBlank();
		boolean primerApellido = !txtPrimerApellido.getText().isBlank();
		boolean email = !txtEmail.getText().isBlank();
		boolean celular = !txtCelular.getText().isBlank();
		boolean cedula = !txtCedula.getText().isBlank();
		boolean direccion = !txtDireccion.getText().isBlank();

		return primerNombre && primerApellido && email && celular && cedula && direccion;
	}

	private boolean VerificarCamposMascota() {
		boolean nombreMascota = !txtNombreMascota.getText().isBlank();
		boolean peso = !txtPesoMascota.getText().isBlank();
		boolean fechaNacimiento = dateFechaMascota.getValue() != null;
		boolean raza = comboRaza.getSelectionModel().getSelectedItem() != null;
		boolean especie = comboTipoMascota.getSelectionModel().getSelectedItem() != null;

		return nombreMascota && peso && fechaNacimiento && raza && especie;
	}

	private Persona mapearPersona() {

		persona = new Persona();
		persona.setPrimerNombre(txtPrimerNombre.getText().trim());
		persona.setSegundoNombre(txtSegundoNombre.getText().trim());
		persona.setPrimerApellido(txtPrimerApellido.getText().trim());
		persona.setSegundoApellido(txtSegundoApellido.getText().trim());
		persona.setEmail(txtEmail.getText().trim());
		persona.setCelular(txtCelular.getText().trim());
		persona.setCedula(Integer.parseInt(txtCedula.getText().trim()));
		persona.setFechaVinculacion(fechaHoraFormateada);
		persona.setDireccion(txtDireccion.getText());
		persona.setTipoVinculo(comboVinculo.getSelectionModel().getSelectedItem());

		boolean estatus = toggleEstatus.isSelected();
		if (estatus) {
			persona.setEstadoPersona(EstadoPersona.ACTIVO);
		} else {
			persona.setEstadoPersona(EstadoPersona.INACTIVO);
		}

		return persona;
	}

	private Mascota mapearMascota() {

		mascota = new Mascota();
		mascota.setNombre(txtNombreMascota.getText().trim());
		mascota.setCedulaPropietario(Integer.parseInt(txtCedula.getText().trim()));
		mascota.setEspecie(comboTipoMascota.getSelectionModel().getSelectedItem());
		mascota.setRaza(comboRaza.getSelectionModel().getSelectedItem());
		mascota.setPeso(Double.parseDouble(txtPesoMascota.getText().trim()));
		mascota.setFechaNacimiento(dateFechaMascota.getValue().toString());

		return mascota;
	}

	private void LimpiarCampos() {

		txtCedula.clear();
		txtCelular.clear();
		txtEmail.clear();
		txtPrimerApellido.clear();
		txtPrimerNombre.clear();
		txtSegundoApellido.clear();
		txtSegundoNombre.clear();
		label_Error.setVisible(false);
		toggleEstatus.isSelected();
		txtDireccion.clear();
		comboVinculo.getSelectionModel().clearSelection();
		limpiarCamposMascota();
		asignarListenerTablaPersona();
	}

	public void limpiarCamposMascota() {

		txtNombreMascota.clear();
		txtPesoMascota.clear();
		dateFechaMascota.setValue(null);
		comboRaza.getSelectionModel().clearSelection();
		comboTipoMascota.getSelectionModel().clearSelection();
		datosListMascota.clear();

	}

	private void mapearRegistroSeleccionadoPersona(Persona persona) {

		txtPrimerNombre.setText(persona.getPrimerNombre());
		txtSegundoNombre.setText(persona.getSegundoNombre());
		txtPrimerApellido.setText(persona.getPrimerApellido());
		txtSegundoApellido.setText(persona.getSegundoApellido());
		txtEmail.setText(persona.getEmail());
		txtCelular.setText(persona.getCelular());
		txtCedula.setText(persona.getCedula() + "");
		txtDireccion.setText(persona.getDireccion());
		comboVinculo.setValue(persona.getTipoVinculo());
		if (persona.getEstadoPersona().equals(EstadoPersona.ACTIVO)) {
			toggleEstatus.isSelected();
		}

	}

	// Método que asigna el Listener a la tabla
	public void asignarListenerTablaPersona() {
		tablaContenedor.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, seleccionado) -> {
			if (seleccionado != null) {
				mapearRegistroSeleccionadoPersona(seleccionado);
				mapearMascostasPersona(seleccionado);
			}
		});
	}

	private void mapearMascostasPersona(Persona seleccionado) {
		// Limpiar la lista de mascotas antes de agregar nuevas
		datosListMascota.clear();

		// Verificar si la persona seleccionada tiene mascotas
		if (seleccionado != null) {
			// Obtener la lista de mascotas de la persona seleccionada
			List<Mascota> mascotas = seleccionado.getMascotas();

			// Si la persona tiene mascotas, agregarlas a la lista observable
			if (mascotas != null) {
				datosListMascota.addAll(mascotas);
			}
		}

		// Asignar la lista observable a la tabla
		tablaMascotas.setItems(datosListMascota);

		// Configurar las columnas de la tabla
		colNombreMascota.setCellValueFactory(new PropertyValueFactory<>("nombre"));
		colPeso.setCellValueFactory(new PropertyValueFactory<>("peso"));
		colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
		colEspecie.setCellValueFactory(new PropertyValueFactory<>("especie"));
		colRaza.setCellValueFactory(new PropertyValueFactory<>("raza"));
		colCedulaPropietario.setCellValueFactory(new PropertyValueFactory<>("cedulaPropietario"));

	}

	// Método para manejar la acción del botón "Salir"
	@FXML
	void btnVolverEvent() {
		cambiarPantalla("vista/Principal.fxml"); // Cambiar a la pantalla de Login
	}

	// Método para cambiar de pantalla
	private void cambiarPantalla(String fxmlFile) {
		try {
			// Cargar el nuevo FXML
			AnchorPane nuevaPantalla = FXMLLoader.load(getClass().getResource("/" + fxmlFile));
			Scene nuevaEscena = new Scene(nuevaPantalla);
			Stage ventana = (Stage) btnVolver.getScene().getWindow(); // Obtener la ventana actual
			ventana.setScene(nuevaEscena);
			ventana.show();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@FXML
	private void handleMouseClick(MouseEvent event) {
		Persona selectedPersona = tablaContenedor.getSelectionModel().getSelectedItem();
		mapearMascostasPersona(selectedPersona);
	}

}
