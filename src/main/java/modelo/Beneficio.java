package modelo;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Beneficio {

	private int codigo;

	private String nombre;

	private String descripcion;

	private double costo;

	private String tipoBeneficio;

	public String getCostoFormateado(double costo) {
		NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
		return formatoMoneda.format(costo);
	}

}
