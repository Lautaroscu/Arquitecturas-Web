package services;

import DTOs.inscripcion.InscripcionDTO;
import entities.Alumno;
import entities.Carrera;
import entities.Inscripcion;
import entities.InscripcionId;
import repositorios.interfaces.RepositorioAlumno;
import repositorios.interfaces.RepositorioCarrera;
import repositorios.interfaces.RepositorioInscripcion;

public class ServicioInscripcion {
    private RepositorioInscripcion repositorioInscripcion;
    private RepositorioAlumno repositorioAlumno;
    private RepositorioCarrera repositorioCarrera;


    public ServicioInscripcion(RepositorioInscripcion repositorioInscripcion, RepositorioAlumno repositorioAlumno, RepositorioCarrera repositorioCarrera) {
        this.repositorioInscripcion = repositorioInscripcion;
        this.repositorioAlumno = repositorioAlumno;
        this.repositorioCarrera = repositorioCarrera;
    }

    public InscripcionDTO obtenerInscripcion(InscripcionId id) {
        Inscripcion inscripcion = repositorioInscripcion.obtenerInscripcion(id);
        return new InscripcionDTO(inscripcion);
    }

    public void eliminarInscripcion(InscripcionId id) {
        repositorioInscripcion.eliminarInscripcion(id);
    }

    public void modificarInscripcion(InscripcionDTO inscripcion, InscripcionId id) {
        Inscripcion inscripcionModificada = repositorioInscripcion.obtenerInscripcion(id);
        inscripcionModificada.setAlumno(repositorioAlumno.recuperarAlumnoPorNroLib(inscripcion.getNro_libreta()));
        inscripcionModificada.setCarrera(repositorioCarrera.recuperarCarrera(inscripcion.getId_carrera()));
        inscripcionModificada.setSeGraduo(inscripcion.isSeGraduo());
        inscripcionModificada.setIdCarrera(inscripcion.getId_carrera());
        inscripcionModificada.setNroLibreta(inscripcion.getNro_libreta());

        repositorioInscripcion.modificarInscripcion(inscripcionModificada);
    }

    public void matricularAlumnoCarrera(Alumno alumno, Carrera carrera) {
        repositorioInscripcion.matricularAlumnoCarrera(alumno, carrera);
    }
}
