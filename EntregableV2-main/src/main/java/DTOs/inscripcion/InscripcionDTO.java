package DTOs.inscripcion;

import entities.Inscripcion;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@EqualsAndHashCode
public class InscripcionDTO implements Serializable {
    private int id_carrera;
    private int nro_libreta;
    private LocalDate fechaInscripcion;
    private boolean seGraduo;

    public InscripcionDTO(Inscripcion i) {
        id_carrera = i.getCarrera().getId_carrera();
        nro_libreta = i.getAlumno().getNro_libreta();
        this.fechaInscripcion = i.getFechaInscripcion();
        this.seGraduo = i.isSeGraduo();
    }
}
