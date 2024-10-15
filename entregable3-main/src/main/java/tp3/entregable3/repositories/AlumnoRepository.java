package tp3.entregable3.repositories;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import tp3.entregable3.entities.Alumno;

import java.util.List;

public interface AlumnoRepository extends JpaRepository<Alumno, Integer>, JpaSpecificationExecutor<Alumno> {
    @Query(value = "SELECT a FROM Alumno a WHERE lower( a.genero) = lower( :genero)")
    List<Alumno> listByGenero(@Param("genero") String genero);

    @Query(value = "SELECT a FROM Alumno a WHERE lower( a.ciudad_residencia) = lower( :ciudad) AND a.nro_libreta IN (" +
            "SELECT i.nroLibreta FROM Inscripcion i WHERE LOWER( i.carrera.nombre) = lower( :carrera))")
    List<Alumno> listByCiudadYCarrera(@Param("ciudad") String ciudad, @Param("carrera") String carrera);
}
