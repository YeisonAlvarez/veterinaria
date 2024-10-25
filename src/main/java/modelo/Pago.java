package modelo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Pago {

	private int codigo;

	private String fechaPago;

	private double valorTotal;

	private Factura factura;

}
