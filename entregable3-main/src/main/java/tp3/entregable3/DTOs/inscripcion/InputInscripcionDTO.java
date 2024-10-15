package tp3.entregable3.DTOs.inscripcion;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import tp3.entregable3.entities.Inscripcion;

import java.time.LocalDate;

@AllArgsConstructor
@Getter
@Setter
public class InputInscripcionDTO {
    private int idCarrera;
    private int nroLibreta;
    private boolean seGraduo;
    private LocalDate fechaInscripcion;

    public InputInscripcionDTO(Inscripcion inscripcion) {
        this.idCarrera = inscripcion.getIdCarrera();
        this.nroLibreta = inscripcion.getNroLibreta();
        this.seGraduo = inscripcion.isSeGraduo();
        this.fechaInscripcion = inscripcion.getFechaInscripcion();
    }
}
