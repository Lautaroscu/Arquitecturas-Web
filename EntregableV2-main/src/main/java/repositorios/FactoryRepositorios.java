package repositorios;

import jakarta.persistence.EntityManager;
import repositorios.implementaciones.RepositorioAlumnoImpl;
import repositorios.implementaciones.RepositorioCarreraImpl;
import repositorios.implementaciones.RepositorioInscripcionImpl;

public class FactoryRepositorios {
    private static RepositorioAlumnoImpl repositorioAlumno;
    private static RepositorioInscripcionImpl repositorioInscripcion;
    private static RepositorioCarreraImpl repositorioCarrera;

    public static RepositorioAlumnoImpl getRepositorioAlumno(EntityManager em) {
        if (repositorioAlumno == null) {
            repositorioAlumno = RepositorioAlumnoImpl.getInstancia(em);
        } else {
            repositorioAlumno.setEm(em);
        }
        return repositorioAlumno;
    }

    public static RepositorioInscripcionImpl getRepositorioInscripcion(EntityManager em) {
        if (repositorioInscripcion == null) {
            repositorioInscripcion = RepositorioInscripcionImpl.getInstancia(em);
        } else {
            repositorioInscripcion.setEm(em);
        }
        return repositorioInscripcion;
    }

    public static RepositorioCarreraImpl getRepositorioCarrera(EntityManager em) {
        if (repositorioCarrera == null) {
            repositorioCarrera = RepositorioCarreraImpl.getInstance(em);
        } else {
            repositorioCarrera.setEm(em);
        }
        return repositorioCarrera;
    }
}


