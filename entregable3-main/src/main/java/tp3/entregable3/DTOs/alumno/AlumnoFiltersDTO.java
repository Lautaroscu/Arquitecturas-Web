package tp3.entregable3.DTOs.alumno;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class AlumnoFiltersDTO {
    private String genero, ciudadRecidencia, carrera;

    public AlumnoFiltersDTO(String genero, String ciudadRecidencia, String carrera) {
        this.genero = genero;
        this.ciudadRecidencia = ciudadRecidencia;
        this.carrera = carrera;
    }
}
