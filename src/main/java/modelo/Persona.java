package modelo;

import lombok.Data;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Persona {

	private int cedula;

	private String primerNombre;

	private String segundoNombre;

	private String primerApellido;

	private String segundoApellido;

	private String email;

	private String celular;

	private String direccion;

	private EstadoPersona estadoPersona;

	private String fechaVinculacion;

	private TipoVinculo tipoVinculo;

	private List<Mascota> mascotas;

	public int getCodigoEstadoPersona(EstadoPersona estado) {
		switch (estado) {
		case ACTIVO:
			return 0; // código para ACTIVO
		case INACTIVO:
			return 1; // código para INACTIVO
		case RETIRADO:
			return 2; // código para RETIRADO
		case EN_MORA:
			return 3; // código para EN_MORA
		default:
			throw new IllegalArgumentException("Estado de persona no válido: " + estado);
		}
	}

	public EstadoPersona getEstadoPersonaPorCodigo(int codigo) {
		switch (codigo) {
		case 0:
			return EstadoPersona.ACTIVO;
		case 1:
			return EstadoPersona.INACTIVO;
		case 2:
			return EstadoPersona.RETIRADO;
		case 3:
			return EstadoPersona.EN_MORA;
		default:
			throw new IllegalArgumentException("Código de estado no válido: " + codigo);
		}
	}

	public int getCodigoTipoVinculo(TipoVinculo tipoVinculo) {
		switch (tipoVinculo) {
		case AFILIADO:
			return 0; // código para AFILIADO
		case PARTICULAR:
			return 1; // código para PARTICULAR
		default:
			throw new IllegalArgumentException("Tipo de vínculo no válido: " + tipoVinculo);
		}
	}
	
	public TipoVinculo getTipoVinculoPorCodigo(int codigo) {
	    switch (codigo) {
	        case 0:
	            return TipoVinculo.AFILIADO;
	        case 1:
	            return TipoVinculo.PARTICULAR;
	        default:
	            throw new IllegalArgumentException("Código de tipo de vínculo no válido: " + codigo);
	    }
	}
}
