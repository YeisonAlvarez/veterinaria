package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexionBD.ConexionBD;
import modelo.Mascota;

public class MascotaDAO {

	public void agregarMascota(Mascota mascota) {

		String sql = "INSERT INTO mascota (codigo, nombre, peso, fechaNacimiento, especie_codigo, Raza_codigo, cedula_persona) VALUES (?, ?, ?, ?, ?, ?, ?)";
		Connection connection = null;

		try {
			// Obtener la conexión
			connection = ConexionBD.getInstance().getConnection();

			// Deshabilitar auto-commit para controlar manualmente las transacciones
			connection.setAutoCommit(false);

			try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
				preparedStatement.setInt(1, mascota.getCodigo()); // Código de la mascota (ID)
				preparedStatement.setString(2, mascota.getNombre()); // Nombre de la mascota
				preparedStatement.setDouble(3, mascota.getPeso()); // Peso de la mascota
				preparedStatement.setString(4, mascota.getFechaNacimiento()); // Fecha de nacimiento
				preparedStatement.setInt(5, mascota.getCodigoTipoMascota(mascota.getEspecie())); // Código de especie
				preparedStatement.setInt(6, mascota.getCodigoRazaMascota(mascota.getRaza())); // Código de raza
				preparedStatement.setInt(7, mascota.getCedulaPropietario()); // Cédula del propietario

				// Ejecutar la inserción
				preparedStatement.executeUpdate();

				// Hacer commit manualmente para confirmar la transacción
				connection.commit();

				System.out.println("MascotaDAO: agregarMascota ---> Exitoso");
			}

		} catch (SQLException e) {
			e.printStackTrace(); // Manejo básico de excepciones
			try {
				if (connection != null) {
					connection.rollback(); // Si ocurre un error, hacer rollback
					System.out.println("Transacción revertida por error.");
				}
			} catch (SQLException rollbackEx) {
				rollbackEx.printStackTrace();
			}
		} finally {
			try {
				if (connection != null) {
					connection.setAutoCommit(true); // Volver a habilitar auto-commit
					connection.close(); // Cerrar la conexión
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	public List<Mascota> listarMascotasPorPropietario(int cedulaPropietario) {
		List<Mascota> listaMascotas = new ArrayList<>();

		String sql = "SELECT * FROM mascota WHERE cedula_persona = ?";

		try (Connection connection = ConexionBD.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, cedulaPropietario);
			try (ResultSet resultSet = preparedStatement.executeQuery()) {
				while (resultSet.next()) {
					Mascota mascota = new Mascota();
					mascota.setCodigo(resultSet.getInt("codigo"));
					mascota.setNombre(resultSet.getString("nombre"));
					mascota.setCedulaPropietario(resultSet.getInt("cedula_persona"));
					mascota.setPeso(resultSet.getDouble("peso"));
					mascota.setFechaNacimiento(resultSet.getString("fechaNacimiento"));
					mascota.setRaza(mascota.getRazaMascotaPorCodigo(resultSet.getInt("Raza_codigo")));
					mascota.setEspecie(mascota.getTipoMascotaPorCodigo(resultSet.getInt("especie_codigo")));
					
					// Aquí agregas todos los atributos necesarios de la mascota
					listaMascotas.add(mascota);
				}
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}

		return listaMascotas;
	}

	public void eliminarMascota(int id) {
		String sql = "DELETE FROM mascotas WHERE id = ?";
		try (Connection connection = ConexionBD.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
			preparedStatement.setInt(1, id);
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			// Manejo de errores, si es necesario
		}
	}

	public int obtenerUltimoIdMascota() {
		String sql = "SELECT MAX(codigo) AS ultimo_id FROM mascota";
		int ultimoId = 0;

		try (Connection connection = ConexionBD.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			if (resultSet.next()) {
				ultimoId = resultSet.getInt("ultimo_id");
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Manejo básico de excepciones
		}

		return ultimoId; // Si no hay registros, retornará 0
	}

}
