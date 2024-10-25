package modelo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Particular {

	private Persona particular;

	private int contactoEmergencia;

	private boolean esFrecuente;

	private String comentariosVisita;

	private RazaMascota referido;

}
