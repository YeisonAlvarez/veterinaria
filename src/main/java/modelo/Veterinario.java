package modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Veterinario {

	private Persona veterinario;

	private String telefono;

	private String especialidad;

	private List<Consulta> consultas;

}
