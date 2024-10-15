package DTOs.alumno;


import entities.Alumno;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class AlumnoDTO implements Serializable {
    private int nro_libreta;
    private String nombre;
    private String apellido;
    private int edad;
    private String genero;
    private String ciudad_residencia;

    public AlumnoDTO(Alumno a) {
        this.nombre = a.getNombre();
        this.apellido = a.getApellido();
        this.edad = a.getEdad();
        this.genero = a.getGenero();
        this.ciudad_residencia = a.getCiudad_residencia();
        this.nro_libreta = a.getNro_libreta();

    }

    public AlumnoDTO(String nombre, String apellido, int edad, String genero, String ciudad_residencia) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.edad = edad;
        this.genero = genero;
        this.ciudad_residencia = ciudad_residencia;
        this.nro_libreta = 0;
    }
}
