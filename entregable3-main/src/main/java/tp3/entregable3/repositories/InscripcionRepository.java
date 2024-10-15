package tp3.entregable3.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tp3.entregable3.entities.Inscripcion;
import tp3.entregable3.entities.InscripcionId;

public interface InscripcionRepository extends JpaRepository<Inscripcion, InscripcionId> {
}
