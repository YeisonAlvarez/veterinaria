package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import conexionBD.ConexionBD;
import modelo.Beneficio;
import modelo.Plan;

public class SimulacionDAO {

	// Método para insertar una nueva simulación y sus beneficios asociados
	public double simular(Plan planSeleccionado, List<Beneficio> datosListBeneficiosSimula) {
		String sqlSimulacion = "INSERT INTO veterinaria.simulacion (plan_codigo, costo_simulacion) VALUES (?, ?)";
		String sqlSimulacionBeneficio = "INSERT INTO veterinaria.simulacion_beneficio (simulacion_id, beneficio_codigo) VALUES (?, ?)";
		Connection connection = null; // Declarar la conexión aquí
		double costoTotal = 0.0;

		try {
			connection = ConexionBD.getInstance().getConnection(); // Obtener la conexión
			// Iniciar la transacción
			connection.setAutoCommit(false);

			// 1. Insertar en la tabla simulacion
			PreparedStatement preparedStatementSimulacion = connection.prepareStatement(sqlSimulacion,
					PreparedStatement.RETURN_GENERATED_KEYS);
			preparedStatementSimulacion.setInt(1, planSeleccionado.getCodigo());
			preparedStatementSimulacion.setDouble(2, 0.00); // Costo inicial (se actualizará más tarde)

			// Ejecutar la inserción de la simulación
			preparedStatementSimulacion.executeUpdate();

			// Obtener el ID de la simulación recién insertada
			int simulacionId = 0;
			try (var generatedKeys = preparedStatementSimulacion.getGeneratedKeys()) {
				if (generatedKeys.next()) {
					simulacionId = generatedKeys.getInt(1);
				}
			}

			// 2. Insertar los beneficios asociados a la simulación
			try (PreparedStatement preparedStatementBeneficio = connection.prepareStatement(sqlSimulacionBeneficio)) {
				for (Beneficio beneficio : datosListBeneficiosSimula) {
					preparedStatementBeneficio.setInt(1, simulacionId);
					preparedStatementBeneficio.setInt(2, beneficio.getCodigo());
					preparedStatementBeneficio.addBatch(); // Agregar a batch
				}
				preparedStatementBeneficio.executeBatch(); // Ejecutar el batch
			}

			// 3. Calcular el costo total
			costoTotal = planSeleccionado.getCostoMensual();
			for (Beneficio beneficio : datosListBeneficiosSimula) {
				costoTotal += beneficio.getCosto(); // Sumar el costo de los beneficios
			}

			// Actualizar el costo de la simulación
			String sqlUpdateCosto = "UPDATE veterinaria.simulacion SET costo_simulacion = ? WHERE simulacion_id = ?";
			try (PreparedStatement preparedStatementUpdate = connection.prepareStatement(sqlUpdateCosto)) {
				preparedStatementUpdate.setDouble(1, costoTotal);
				preparedStatementUpdate.setInt(2, simulacionId);
				preparedStatementUpdate.executeUpdate();
			}

			// Confirmar la transacción
			connection.commit();
			System.out.println("Simulación creada exitosamente con ID: " + simulacionId);

		} catch (SQLException e) {
			e.printStackTrace(); // Manejo básico de excepciones
			try {
				if (connection != null) {
					connection.rollback(); // Revertir cambios en caso de error
				}
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace();
			}
		} finally {
			// Cerrar la conexión en el bloque finally
			if (connection != null) {
				try {
					connection.close(); // Cerrar la conexión
				} catch (SQLException closeEx) {
					closeEx.printStackTrace();
				}
			}
		}

		return costoTotal; // Retornar el costo total de la simulación
	}

}
