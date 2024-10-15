package tp3.entregable3.repositories;

import jakarta.persistence.NamedQuery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import tp3.entregable3.DTOs.carrera.CarreraReporteDTO;
import tp3.entregable3.DTOs.carrera.OutputCarreraXInscriptosDTO;
import tp3.entregable3.entities.Carrera;

import java.util.List;

public interface CarreraRepository extends JpaRepository<Carrera, Integer> {
    @Query("SELECT new tp3.entregable3.DTOs.carrera.OutputCarreraXInscriptosDTO(c, COUNT(CASE WHEN i IS NULL THEN null ELSE 1 END)) " +
            "FROM Carrera c LEFT JOIN Inscripcion i ON c.id_carrera = i.carrera.id_carrera " +
            "GROUP BY c " +
            "ORDER BY COUNT(i)")
    List<OutputCarreraXInscriptosDTO> findCarrerasOrderByCantInscriptos();


    @Query(
            value = "SELECT new tp3.entregable3.DTOs.carrera.CarreraReporteDTO(c.nombre, YEAR(i.fechaInscripcion), " +
                    "COALESCE(COUNT(i), 0), " +
                    "COALESCE(SUM(CASE WHEN i.seGraduo = TRUE THEN 1 ELSE 0 END), 0)) " +
                    "FROM Inscripcion i " +
                    "JOIN i.carrera c " +
                    "GROUP BY c.nombre, YEAR(i.fechaInscripcion) " +
                    "ORDER BY c.nombre, YEAR(i.fechaInscripcion)"
    )
    List<CarreraReporteDTO> reporteDeCarreras();
}
