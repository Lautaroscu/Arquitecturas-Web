package tp3.entregable3.DTOs.inscripcion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tp3.entregable3.entities.Alumno;
import tp3.entregable3.entities.Carrera;
import tp3.entregable3.entities.Inscripcion;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
public class OutputInscripcionDTO {
    private int idCarrera;
    private int nroLibreta;
    private boolean seGraduo;
    private LocalDate fechaInscripcion;

    public OutputInscripcionDTO(Inscripcion inscripcion) {
        this.idCarrera = inscripcion.getCarrera().getId_carrera();
        this.nroLibreta = inscripcion.getAlumno().getNro_libreta();
        this.seGraduo = inscripcion.isSeGraduo();
        this.fechaInscripcion = inscripcion.getFechaInscripcion();
    }
}
