package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexionBD.ConexionBD;
import modelo.Mascota;
import modelo.Persona;

public class PersonaDAO {

	public void afiliar(Persona persona) {

		String sql = "INSERT INTO persona (cedula, primerNombre, segundoNombre, primerApellido, segundoApellido, email, celular, direccion,fecha_vinculacion, codigo_estado, codigo_vinculo) VALUES (?, ?, ?, ?, ?, ?, ?, ?,?,?,?)";

		try (Connection connection = ConexionBD.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, persona.getCedula());
			preparedStatement.setString(2, persona.getPrimerNombre());
			preparedStatement.setString(3, persona.getSegundoNombre());
			preparedStatement.setString(4, persona.getPrimerApellido());
			preparedStatement.setString(5, persona.getSegundoApellido());
			preparedStatement.setString(6, persona.getEmail());
			preparedStatement.setString(7, persona.getCelular());
			preparedStatement.setString(8, persona.getDireccion());
			preparedStatement.setString(9, persona.getFechaVinculacion());
			preparedStatement.setInt(10, (persona.getCodigoEstadoPersona(persona.getEstadoPersona())));
			preparedStatement.setInt(11, (persona.getCodigoTipoVinculo(persona.getTipoVinculo())));

			preparedStatement.executeUpdate(); // Ejecuta la inserción

			System.out.println("UsuarioDAO :registrarUsuario--->Exitoso");

		} catch (SQLException e) {
			e.printStackTrace(); // Manejo básico de excepciones
		}
	}

	public List<Persona> listarPersonas() {

		List<Persona> listaPersonas = new ArrayList<>();

		// Consulta SQL para obtener usuarios afiliados
		String sql = "SELECT * FROM persona";

		// Asegúrate de usar try-with-resources para manejar correctamente la conexión y
		// los recursos
		try (Connection connection = ConexionBD.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			// Iterar sobre los resultados de la consulta para obtener solo las personas
			while (resultSet.next()) {
				Persona persona = new Persona();

				persona.setCedula(resultSet.getInt("cedula"));
				persona.setPrimerNombre(resultSet.getString("primerNombre"));
				persona.setSegundoNombre(resultSet.getString("segundoNombre"));
				persona.setPrimerApellido(resultSet.getString("primerApellido"));
				persona.setSegundoApellido(resultSet.getString("segundoApellido"));
				persona.setEmail(resultSet.getString("email"));
				persona.setCelular(resultSet.getString("celular"));
				persona.setDireccion(resultSet.getString("direccion"));
				persona.setFechaVinculacion(resultSet.getString("fecha_vinculacion"));
				persona.setEstadoPersona(persona.getEstadoPersonaPorCodigo(resultSet.getInt("codigo_estado")));
				persona.setTipoVinculo(persona.getTipoVinculoPorCodigo(resultSet.getInt("codigo_vinculo")));

				// Agregar la persona a la lista sin mascotas aún
				listaPersonas.add(persona);
			}

		} catch (SQLException e) {
			e.printStackTrace(); // Manejo de excepciones
		}

		// Ahora que tenemos todas las personas, recorremos la lista para obtener sus
		// mascotas
		MascotaDAO mascotaDAO = new MascotaDAO();

		for (Persona persona : listaPersonas) {
			// Obtener la lista de mascotas para cada persona y asignarla
			List<Mascota> mascotas = mascotaDAO.listarMascotasPorPropietario(persona.getCedula());
			persona.setMascotas(mascotas);
		}

		return listaPersonas; // Devolver la lista completa de personas con sus mascotas asignadas
	}

	public void eliminarPersona(int cedula) {
		String sqlMascotas = "DELETE FROM mascota WHERE cedula_persona = ?";
		String sqlPersona = "DELETE FROM persona WHERE cedula = ?";
		Connection connection = null;

		try {
			connection = ConexionBD.getInstance().getConnection();
			// Desactivar el autocommit para manejar transacciones
			connection.setAutoCommit(false);

			// Eliminar las mascotas asociadas a la persona
			try (PreparedStatement preparedStatementMascotas = connection.prepareStatement(sqlMascotas)) {
				preparedStatementMascotas.setInt(1, cedula);
				preparedStatementMascotas.executeUpdate(); // Ejecuta la eliminación de mascotas
			}

			// Eliminar la persona
			try (PreparedStatement preparedStatementPersona = connection.prepareStatement(sqlPersona)) {
				preparedStatementPersona.setInt(1, cedula);
				preparedStatementPersona.executeUpdate(); // Ejecuta la eliminación de la persona
			}

			// Hacer commit de la transacción
			connection.commit();
			System.out.println("UsuarioDAO: eliminar--->Exitoso");

		} catch (SQLException e) {
			// Si ocurre un error, revertir los cambios
			if (connection != null) {
				try {
					connection.rollback();
				} catch (SQLException rollbackEx) {
					rollbackEx.printStackTrace();
				}
			}
			e.printStackTrace(); // Manejo básico de excepciones
		} finally {
			// Restablecer el autocommit y cerrar la conexión
			try {
				if (connection != null) {
					connection.setAutoCommit(true);
					connection.close(); // Cerrar la conexión
				}
			} catch (SQLException autoCommitEx) {
				autoCommitEx.printStackTrace();
			}
		}
	}

	public void modificarPersona(Persona nuevaPersona) {
		String sql = "UPDATE persona SET email = ?, celular = ?, direccion = ?, codigo_estado = ?, codigo_vinculo = ? WHERE cedula = ?";

		try (Connection connection = ConexionBD.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			// Establecer los parámetros para la sentencia SQL
			preparedStatement.setString(1, nuevaPersona.getEmail());
			preparedStatement.setString(2, nuevaPersona.getCelular());
			preparedStatement.setString(3, nuevaPersona.getDireccion());
			preparedStatement.setInt(4, nuevaPersona.getCodigoEstadoPersona(nuevaPersona.getEstadoPersona()));
			preparedStatement.setInt(5, nuevaPersona.getCodigoTipoVinculo(nuevaPersona.getTipoVinculo()));
			preparedStatement.setInt(6, nuevaPersona.getCedula());

			// Ejecutar la actualización
			int filasActualizadas = preparedStatement.executeUpdate();

			if (filasActualizadas > 0) {
				System.out.println("UsuarioDAO : modificarPersona ---> Actualización exitosa");
			} else {
				System.out.println("UsuarioDAO : modificarPersona ---> No se encontró la persona con esa cédula");
			}

		} catch (SQLException e) {
			e.printStackTrace(); // Manejo básico de excepciones
		}
	}

}
