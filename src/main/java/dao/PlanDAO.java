package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import conexionBD.ConexionBD;
import modelo.Plan;

public class PlanDAO {
	
	public List<Plan> listarPlanes() {

	    List<Plan> listaPlanes = new ArrayList<Plan>();

	    // Consulta SQL para obtener los planes
	    String sql = "SELECT codigo, nombre, descripcion, costoMensual FROM plan";

	    try (Connection connection = ConexionBD.getInstance().getConnection();
	         PreparedStatement preparedStatement = connection.prepareStatement(sql);
	         ResultSet resultSet = preparedStatement.executeQuery()) {

	        // Iterar sobre los resultados de la consulta
	        while (resultSet.next()) {

	            Plan plan = new Plan();

	            // Asignar los valores obtenidos de la base de datos al objeto Plan
	            plan.setCodigo(resultSet.getInt("codigo"));
	            plan.setNombre(resultSet.getString("nombre"));
	            plan.setDescripcion(resultSet.getString("descripcion"));
	            plan.setCostoMensual(resultSet.getDouble("costoMensual"));

	            // Agregar el plan a la lista de planes
	            listaPlanes.add(plan);
	        }

	    } catch (SQLException e) {
	        e.printStackTrace(); // Manejo de excepciones
	    }
	    
	    return listaPlanes;
	}



}
