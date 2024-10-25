package modelo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class ContratoAfiliacion {

    private int codigo;
    private String fechaInicio;
    private Persona afiliado;
    private Plan plan;
    private Factura pago;

}

