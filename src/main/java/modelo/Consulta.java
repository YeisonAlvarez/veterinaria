package modelo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Data;
import java.util.Date;
import java.util.List;

@Data 
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {

    private Long codigo;
    private Date fecha;
    private String motivo;
    private String diagnostico;
    private Mascota mascota;
    private Veterinario veterinario;
}