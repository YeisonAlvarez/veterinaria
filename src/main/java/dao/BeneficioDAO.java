package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexionBD.ConexionBD;
import modelo.Beneficio;

public class BeneficioDAO {

	public List<Beneficio> listarBeneficios() {
		List<Beneficio> listaBeneficios = new ArrayList<>();

		// Consulta SQL para obtener todos los beneficios
		String sql = "SELECT * FROM veterinaria.beneficio";

		try (Connection connection = ConexionBD.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql);
				ResultSet resultSet = preparedStatement.executeQuery()) {

			// Iterar sobre los resultados de la consulta
			while (resultSet.next()) {
				Beneficio beneficio = new Beneficio();

				beneficio.setCodigo(resultSet.getInt("codigo"));
				beneficio.setNombre(resultSet.getString("nombre"));
				beneficio.setDescripcion(resultSet.getString("descripcion"));
				beneficio.setCosto(resultSet.getInt("costo"));

				// Guardamos temporalmente el ID del tipo de beneficio
				int idTipoBeneficio = resultSet.getInt("idTipo_Beneficio");

				beneficio.setTipoBeneficio(idTipoBeneficio + "");

				// Agregar el beneficio a la lista temporal
				listaBeneficios.add(beneficio);
			}

		} catch (SQLException e) {
			e.printStackTrace(); // Manejo de excepciones
		}

		// Aquí, fuera del ResultSet, obtenemos los tipos de beneficio
		for (Beneficio beneficio : listaBeneficios) {
			beneficio.setTipoBeneficio(obtenerBeneficioPorCodigo(Integer.parseInt(beneficio.getTipoBeneficio())));
		}

		return listaBeneficios;
	}

	public String obtenerBeneficioPorCodigo(int codigo) {
		String nombreBeneficio = null;
		String sql = "SELECT descripcion FROM veterinaria.tipo_beneficio WHERE idTipo_Beneficio = ?";

		try (Connection connection = ConexionBD.getInstance().getConnection();
				PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

			preparedStatement.setInt(1, codigo); // Asignar el parámetro antes de ejecutar la consulta
			ResultSet resultSet = preparedStatement.executeQuery();

			if (resultSet.next()) {
				nombreBeneficio = resultSet.getString("descripcion");
			}
		} catch (SQLException e) {
			e.printStackTrace(); // Puedes implementar un manejo de excepciones más robusto aquí
		}

		return nombreBeneficio;
	}

}
