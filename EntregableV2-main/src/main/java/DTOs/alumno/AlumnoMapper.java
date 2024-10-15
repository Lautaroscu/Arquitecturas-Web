package DTOs.alumno;

import entities.Alumno;

import java.util.function.Function;

public class AlumnoMapper implements Function<Alumno, AlumnoDTO> {
    @Override
    public AlumnoDTO apply(Alumno alumno) {
        return new AlumnoDTO(alumno);
    }
}
