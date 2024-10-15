package DTOs.inscripcion;

import entities.Inscripcion;

import java.util.function.Function;

public class InscripcionMapper implements Function<Inscripcion, InscripcionDTO> {
    @Override
    public InscripcionDTO apply(Inscripcion inscripcion) {
        return new InscripcionDTO(inscripcion);
    }
}