package entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;

@Entity
@Getter
@ToString
@AllArgsConstructor
@Table(name = "carrera")
@NamedQuery(
        name = Carrera.CARRERASSORTCANTINSC,
        query = "SELECT new DTOs.carrera.CantInscriptosCarreraDTO(i.carrera, COUNT(i)) " +
                "FROM Inscripcion i " +
                "GROUP BY i.carrera " +
                "ORDER BY COUNT(i)"
)
@NamedQuery(
        name = "Carrera.ReporteInscriptosEgresados",
        query = "SELECT new DTOs.carrera.CarreraReporteDTO(c.nombre, YEAR(i.fechaInscripcion), " +
                "COUNT(i), " +
                "SUM(CASE WHEN i.seGraduo = TRUE THEN 1 ELSE 0 END)) " +
                "FROM Inscripcion i " +
                "JOIN i.carrera c " +
                "GROUP BY c.nombre, YEAR(i.fechaInscripcion) " +
                "ORDER BY c.nombre, YEAR(i.fechaInscripcion)"
)
public class Carrera {
    public final static String CARRERASSORTCANTINSC = "Carrera.CarrerasSortCantInsc";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id_carrera;

    @Column
    private String nombre;

    public Carrera() {
    }

    public Carrera(String nombre) {
        this.nombre = nombre;
    }
}
