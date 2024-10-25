package modelo;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Ejecutivo {

    private Persona ejecutivo;

    private CargoEjecutivo cargo;

    private String fechaIngreso;

    private double salario;

    private String horarioLaboral;

    
}

