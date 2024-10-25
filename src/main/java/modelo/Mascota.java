package modelo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Mascota {

	private int codigo;

	private String nombre;

	private double peso;

	private String fechaNacimiento;

	private TipoMascota especie;

	private RazaMascota raza;

	private int cedulaPropietario;

	public int getCodigoTipoMascota(TipoMascota tipoMascota) {
		switch (tipoMascota) {
		case PERRO:
			return 0;
		case GATO:
			return 1;
		case AVE:
			return 2;
		case REPTIL:
			return 3;
		case ROEDOR:
			return 4;
		default:
			throw new IllegalArgumentException("Tipo de mascota no válido: " + tipoMascota);
		}
	}

	public TipoMascota getTipoMascotaPorCodigo(int codigo) {
		switch (codigo) {
		case 0:
			return TipoMascota.PERRO;
		case 1:
			return TipoMascota.GATO;
		case 2:
			return TipoMascota.AVE;
		case 3:
			return TipoMascota.REPTIL;
		case 4:
			return TipoMascota.ROEDOR;
		default:
			throw new IllegalArgumentException("Código de tipo de mascota no válido: " + codigo);
		}
	}

	public int getCodigoRazaMascota(RazaMascota razaMascota) {
		switch (razaMascota) {
		case LABRADOR:
			return 0;
		case PERSA:
			return 1;
		case BEAGLE:
			return 2;
		case SIAMES:
			return 3;
		case HUSKY:
			return 4;
		case CANARIO:
			return 5;
		case IGUANA:
			return 6;
		case CONEJO:
			return 7;
		default:
			throw new IllegalArgumentException("Raza de mascota no válida: " + razaMascota);
		}
	}

	public RazaMascota getRazaMascotaPorCodigo(int codigo) {
		switch (codigo) {
		case 0:
			return RazaMascota.LABRADOR;
		case 1:
			return RazaMascota.PERSA;
		case 2:
			return RazaMascota.BEAGLE;
		case 3:
			return RazaMascota.SIAMES;
		case 4:
			return RazaMascota.HUSKY;
		case 5:
			return RazaMascota.CANARIO;
		case 6:
			return RazaMascota.IGUANA;
		case 7:
			return RazaMascota.CONEJO;
		default:
			throw new IllegalArgumentException("Código de raza de mascota no válido: " + codigo);
		}
	}

}
