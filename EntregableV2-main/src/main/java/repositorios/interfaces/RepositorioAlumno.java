package repositorios.interfaces;

import entities.Alumno;

import java.util.List;

public interface RepositorioAlumno {
    void altaAlumno(Alumno alumno);

    void bajaAlumno(int nroLibreta);

    void modificarAlumno(Alumno alumno);

    List<Alumno> listarAlumnos();

    List<Alumno> recuperarAlumnosSortByApellido();

    Alumno recuperarAlumnoPorNroLib(int nroLib);

    List<Alumno> recuperarAlumnosPorGenero(String genero);

    List<Alumno> recuperarAlumnosPorCarrerayCiudad(String carrera, String ciudad);
}
