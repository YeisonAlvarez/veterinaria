package modelo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.text.NumberFormat;
import java.util.List;
import java.util.Locale;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Plan {


    private int codigo;

    private String nombre;

    private String descripcion;

    private double costoMensual;
    
    @Override
    public String toString() {
        // Crear un formateador para la moneda de Colombia
        NumberFormat formatoMoneda = NumberFormat.getCurrencyInstance(new Locale("es", "CO"));
        
        return nombre + " - " + descripcion + " - Costo Mensual: " + formatoMoneda.format(costoMensual);
    }


}
