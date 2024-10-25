package modelo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Factura {


    private Long id;

    private Date fecha;

    private double total;

    private Persona usuario;
    
}