package services;

import DTOs.alumno.AlumnoDTO;
import DTOs.alumno.AlumnoMapper;
import entities.Alumno;
import repositorios.interfaces.RepositorioAlumno;

import java.util.List;

public class ServicioAlumno {
    private AlumnoMapper alumnoMapper;
    private RepositorioAlumno repositorioAlumno;

    public ServicioAlumno(RepositorioAlumno repositorioAlumno) {
        alumnoMapper = new AlumnoMapper();
        this.repositorioAlumno = repositorioAlumno;
    }

    public void altaAlumno(Alumno alumno) {
        repositorioAlumno.altaAlumno(alumno);
        alumno.setNro_libreta(alumno.getNro_libreta());
    }

    public void bajaAlumno(int nroLibreta) {
        repositorioAlumno.bajaAlumno(nroLibreta);
    }

    public void modificarAlumno(AlumnoDTO alumno) {
        Alumno modificado = repositorioAlumno.recuperarAlumnoPorNroLib(alumno.getNro_libreta());
        modificado.setNombre(alumno.getNombre());
        modificado.setApellido(alumno.getApellido());
        modificado.setEdad(alumno.getEdad());
        modificado.setGenero(alumno.getGenero());
        modificado.setCiudad_residencia(alumno.getCiudad_residencia());

        repositorioAlumno.modificarAlumno(modificado);
    }

    public List<AlumnoDTO> listarAlumnos() {
        return repositorioAlumno.listarAlumnos()
                .stream()
                .map(alumnoMapper)
                .toList();
    }

    public List<AlumnoDTO> listarAlumnosPorGenero(String genero) {
        return repositorioAlumno.recuperarAlumnosPorGenero(genero)
                .stream()
                .map(alumnoMapper)
                .toList();
    }

    public List<AlumnoDTO> listarAlumnosOrdenadosByApellido() {
        return repositorioAlumno
                .recuperarAlumnosSortByApellido()
                .stream()
                .map(alumnoMapper)
                .toList();
    }

    public AlumnoDTO recuperarAlumnoPorNroLib(int nroLibreta) {
        Alumno al = repositorioAlumno.recuperarAlumnoPorNroLib(nroLibreta);
        return new AlumnoDTO(al);
    }

    public List<AlumnoDTO> listarAlumnos(String nombreCarrera, String ciudad) {
        return repositorioAlumno.recuperarAlumnosPorCarrerayCiudad(nombreCarrera, ciudad)
                .stream()
                .map(alumnoMapper)
                .toList();
    }
}
