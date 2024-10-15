package tp3.entregable3.repositories;

import jakarta.persistence.criteria.*;
import org.springframework.data.jpa.domain.Specification;
import tp3.entregable3.entities.Alumno;

import tp3.entregable3.entities.Carrera;
import tp3.entregable3.entities.Inscripcion;

public class AlumnoSpecifications {

    // Otras especificaciones como ciudadEsIgual y carreraEsIgual
    public static Specification<Alumno> ciudadEsIgual(String ciudad) {
        return (Root<Alumno> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (ciudad == null || ciudad.isEmpty()) {
                return builder.conjunction();  // No aplica filtro
            }
            return builder.equal(builder.lower(root.get("ciudad_residencia")), ciudad.toLowerCase());
        };
    }

    public static Specification<Alumno> generoEsIgual(String genero) {
        return (Root<Alumno> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            // Si el valor de género es null o vacío, no agregamos condición
            if (genero == null || genero.isEmpty()) {
                return builder.conjunction();  // No aplica filtro
            }
            // Agregar condición para el género
            return builder.equal(builder.lower(root.get("genero")), genero.toLowerCase());
        };
    }

    public static Specification<Alumno> carreraEsIgual(String carrera) {
        return (Root<Alumno> root, CriteriaQuery<?> query, CriteriaBuilder builder) -> {
            if (carrera == null || carrera.isEmpty()) {
                return builder.conjunction(); // No aplica filtro
            }

            // Crear la subconsulta para buscar los números de libreta en Inscripcion
            Subquery<Integer> subquery = query.subquery(Integer.class);
            Root<Inscripcion> inscripcionRoot = subquery.from(Inscripcion.class);

            // Unir Inscripcion con Carrera
            Join<Inscripcion, Carrera> carreraJoin = inscripcionRoot.join("carrera"); // Asumiendo que el nombre de la propiedad es "carrera"

            // Definir la subconsulta para seleccionar nroLibreta basado en el nombre de la carrera
            subquery.select(inscripcionRoot.get("nroLibreta"))
                    .where(builder.equal(builder.lower(carreraJoin.get("nombre")), carrera.toLowerCase()));

            // Filtrar los alumnos cuya nro_libreta esté en el resultado de la subconsulta
            return root.get("nro_libreta").in(subquery);
        };
    }
}

