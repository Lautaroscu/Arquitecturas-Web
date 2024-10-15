package tp3.entregable3.DTOs.alumno;

import lombok.Getter;
import lombok.Setter;
import tp3.entregable3.entities.Alumno;

import java.io.Serializable;

@Getter
@Setter

public class OutputAlumnoDTO implements Serializable {
    private int nro_libreta;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String ciudad_residencia;

    public OutputAlumnoDTO(Alumno a) {
        this.nombre = a.getNombre();
        this.apellido = a.getApellido();
        this.edad = a.getEdad();
        this.genero = a.getGenero();
        this.ciudad_residencia = a.getCiudad_residencia();
        this.nro_libreta = a.getNro_libreta();

    }
}
