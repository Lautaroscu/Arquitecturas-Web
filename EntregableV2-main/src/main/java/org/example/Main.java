package org.example;

import DTOs.alumno.AlumnoDTO;
import DTOs.carrera.CantInscriptosCarreraDTO;
import DTOs.carrera.CarreraReporteDTO;
import DTOs.inscripcion.InscripcionDTO;
import entities.Alumno;
import entities.Carrera;
import entities.InscripcionId;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;
import repositorios.FactoryRepositorios;
import repositorios.implementaciones.RepositorioAlumnoImpl;
import repositorios.implementaciones.RepositorioCarreraImpl;
import repositorios.implementaciones.RepositorioInscripcionImpl;
import services.ServicioAlumno;
import services.ServicioCarrera;
import services.ServicioInscripcion;

import java.util.List;

public class Main {
    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;

        try {
            emf = Persistence.createEntityManagerFactory("TP");
            em = emf.createEntityManager();

            // Instancias singleton repositorios
            RepositorioAlumnoImpl repoAlumnos = FactoryRepositorios.getRepositorioAlumno(em);
            RepositorioInscripcionImpl repoInscripciones = FactoryRepositorios.getRepositorioInscripcion(em);
            RepositorioCarreraImpl repoCarreras = FactoryRepositorios.getRepositorioCarrera(em);

            //Servicios
            ServicioAlumno servicioAlumnos = new ServicioAlumno(repoAlumnos);
            ServicioCarrera servicioCarrera = new ServicioCarrera(repoCarreras);
            ServicioInscripcion servicioInscripcion = new ServicioInscripcion(repoInscripciones, repoAlumnos, repoCarreras);

            //2)
            //A)
            System.out.println("Alta de un estudiante:");
            Alumno alumnoNuevo = new Alumno("Lautaro", "Scuffi", 20, "Masculino", "Juarez");
            Alumno alumnoNuevo1 = new Alumno("Agustin", "Alvarez", 20, "Masculino", "Tandil");
            Alumno alumnoNuevo2 = new Alumno("Pepe", "Carrizo", 20, "Masculino", "Tandil");
            Alumno alumnoNuevo3 = new Alumno("Micaela", "Rodriguez", 22, "Femenino", "Tandil");
            Alumno alumnoNuevo4 = new Alumno("Facundo", "Perez", 23, "Masculino", "Tandil");

            servicioAlumnos.altaAlumno(alumnoNuevo);
            servicioAlumnos.altaAlumno(alumnoNuevo1);
            servicioAlumnos.altaAlumno(alumnoNuevo2);
            servicioAlumnos.altaAlumno(alumnoNuevo3);
            servicioAlumnos.altaAlumno(alumnoNuevo4);

            //Chequeo de que se inserto el dato
            AlumnoDTO alumnoRecuperado = servicioAlumnos.recuperarAlumnoPorNroLib(alumnoNuevo.getNro_libreta());
            System.out.println("Alumno insertado:");
            System.out.println(alumnoRecuperado.getNro_libreta() + ": " + alumnoRecuperado.getApellido() + ", " + alumnoRecuperado.getNombre());

            System.out.println(" ");

            //B)
            System.out.println("Matricular estudiante a una carrera");
            Carrera tudai = new Carrera("tudai");
            Carrera sistemas = new Carrera("ing sistemas");
            Carrera licAmbiental = new Carrera("lic ambiental");

            servicioCarrera.adicionarCarrera(tudai);
            servicioCarrera.adicionarCarrera(sistemas);
            servicioCarrera.adicionarCarrera(licAmbiental);

            servicioInscripcion.matricularAlumnoCarrera(alumnoNuevo, tudai);
            servicioInscripcion.matricularAlumnoCarrera(alumnoNuevo1, tudai);
            servicioInscripcion.matricularAlumnoCarrera(alumnoNuevo1, sistemas);
            servicioInscripcion.matricularAlumnoCarrera(alumnoNuevo2, tudai);
            servicioInscripcion.matricularAlumnoCarrera(alumnoNuevo2, sistemas);
            servicioInscripcion.matricularAlumnoCarrera(alumnoNuevo2, licAmbiental);

            InscripcionId idInsc = new InscripcionId(tudai.getId_carrera(), alumnoRecuperado.getNro_libreta());

            InscripcionDTO inscripcionRecuperada = servicioInscripcion.obtenerInscripcion(idInsc);
            System.out.println("Inscripcion insertada:");
            System.out.println(inscripcionRecuperada);

            System.out.println(" ");

            //C)
            System.out.println("Alumnos ordenados por Apellido:");
            List<AlumnoDTO> alumnosOrdenados = servicioAlumnos.listarAlumnosOrdenadosByApellido();
            for (AlumnoDTO alumno : alumnosOrdenados) {
                System.out.println(alumno.getApellido() + ", " + alumno.getNombre());
            }

            System.out.println(" ");

            //D)
            AlumnoDTO alumnoDTOByNroLibreta = servicioAlumnos.recuperarAlumnoPorNroLib(1);
            System.out.println("Alumno Recuperado por numero de libreta: ");
            System.out.println("Nro libreta: " + alumnoDTOByNroLibreta.getNro_libreta() + ": "
                    + alumnoDTOByNroLibreta.getApellido() + ", " +
                    alumnoDTOByNroLibreta.getNombre() + " - Edad: " + alumnoDTOByNroLibreta.getEdad());

            System.out.println(" ");

            //E)
            System.out.println("Alumnos dado un genero. Ej: Femenino");
            List<AlumnoDTO> alumnosByGenero = servicioAlumnos.listarAlumnosPorGenero("Femenino");
            for (AlumnoDTO alumno : alumnosByGenero) {
                System.out.println(alumno.getApellido() + ", " + alumno.getNombre() + " - genero: " + alumno.getGenero());
            }

            System.out.println(" ");

            //F)
            System.out.println("Carreras y su cantidad de alumnos inscriptos:");
            List<CantInscriptosCarreraDTO> carreras = servicioCarrera.obtenerCarrerasPorCantInscriptos();

            System.out.printf("%-30s %-20s%n", "Carrera", "Cantidad Inscriptos");
            System.out.println("=".repeat(50)); // Ajusté la longitud de la línea para que se vea bien con el formato

            for (CantInscriptosCarreraDTO dto : carreras) {
                System.out.printf("%-30s %-20d%n",
                        dto.getNombre(),
                        dto.getCantInscriptos());
            }

            System.out.println(" ");

            //G)
            System.out.println("Alumnos de una carrera que residen en una ciudad en especifico. Ej: Juarez");
            List<AlumnoDTO> alumnosByCiudadyCarrera = servicioAlumnos.listarAlumnos("tudai", "Juarez");
            for (AlumnoDTO alumno : alumnosByCiudadyCarrera) {
                System.out.println(alumno.getApellido() + ", " + alumno.getNombre() + " - ciudad: " + alumno.getCiudad_residencia());
            }

            System.out.println(" ");

            //3)
            System.out.println("Reporte inscriptos y egresados por carrera");
            //Se setea en true el campo "seGraduo" en una inscripcion para mostrar un graduado en el reporte.
            inscripcionRecuperada.setSeGraduo(true);
            servicioInscripcion.modificarInscripcion(inscripcionRecuperada, idInsc);
            List<CarreraReporteDTO> carreraReporteDTO = servicioCarrera.generarReporteInscriptosEgresados();

            System.out.printf("%-30s %-10s %-20s %-20s%n", "Carrera", "Año", "Cantidad Inscriptos", "Cantidad Egresados");
            System.out.println("=".repeat(85));

            for (CarreraReporteDTO dto : carreraReporteDTO) {
                System.out.printf("%-30s %-10d %-20d %-20d%n",
                        dto.getNombreCarrera(),
                        dto.getAnio(),
                        dto.getCantidadInscriptos(),
                        dto.getCantidadEgresados());
            }
        } catch (Exception e) {
            if (em != null && em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
        } finally {
            if (em != null) {
                em.close();
            }
            if (emf != null) {
                emf.close();
            }
        }
    }
}