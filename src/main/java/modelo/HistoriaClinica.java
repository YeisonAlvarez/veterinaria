package modelo;

import java.sql.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class HistoriaClinica {
	private int codigo;

	private String antecedentes;

	private Date fechaApertura;

	private Date fechaUltimaConsulta;

}
