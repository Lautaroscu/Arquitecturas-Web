package tp3.entregable3.entities;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.util.Objects;

@Embeddable
@Getter
@Setter
public class InscripcionId implements Serializable {
    private int idCarrera;  // Se usa el ID de Carrera, no la entidad Carrera
    private int nroLibreta; // Se usa el ID de Alumno, no la entidad Alumno

    public InscripcionId() {
    }

    public InscripcionId(int nroLibreta, int idCarrera) {
        this.nroLibreta = nroLibreta;
        this.idCarrera = idCarrera;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        InscripcionId that = (InscripcionId) o;
        return idCarrera == that.idCarrera && nroLibreta == that.nroLibreta;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCarrera, nroLibreta);
    }
}