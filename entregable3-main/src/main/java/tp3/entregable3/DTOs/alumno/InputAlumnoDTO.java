package tp3.entregable3.DTOs.alumno;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
public class InputAlumnoDTO implements Serializable {
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String ciudad_residencia;
}
